package com.juzzPay.repository.specs;

import com.juzzPay.json.FilterInfo;
import com.juzzPay.json.FilterInfo.Comparision;
import com.juzzPay.json.FilterInfo.Type;
import com.juzzPay.json.FindInfo;
import com.juzzPay.util.DateUtil;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class SpecificationImpl<T> implements Specification<T> {
	
	private FindInfo findInfo;
	
	public SpecificationImpl() {
	
	}
	
	public SpecificationImpl(FindInfo findInfo){
		this.findInfo = findInfo;	
	}

	public void setFindInfo(FindInfo findInfo) {
		this.findInfo = findInfo;
	}

//    private Specification<T> nameLike(String name){
//        return new Specification<T>() {
//            @Override
//            public Predicate toPredicate(Root<T> root,
//                                         CriteriaQuery<?> query,
//                                         CriteriaBuilder criteriaBuilder) {
//                String fullAlias = "surveyPrequalQuestions.question.id";
//                Object retObject = getLastAlias(fullAlias, root);
//                return criteriaBuilder.like(root.get((String) retObject), "%"+name+"%");
//            }
//        };
//    }

    @Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(!findInfo.getFilterInfoList().isEmpty()) {
			for(FilterInfo filterInfo: findInfo.getFilterInfoList()) {
				Type type = filterInfo.getType();
				if(type.equals(Type.STRING) || type.equals(Type.BOOLEAN) || type.equals(Type.LONG)
						|| type.equals(Type.NUMERIC) || type.equals(Type.OBJECT)) {
					
					String fullAlias = filterInfo.getField();
					Object retObject = getLastAlias(fullAlias, root);
                    Path path = null;
                    if (retObject instanceof String) {
                        path = root.get((String) retObject);
                    } else {
                        path = (Path) retObject;
                    }
                    setValues(predicates, filterInfo.getComparison(), builder, filterInfo.getValue(), path);	
				}else if(type.equals(Type.LIST)) {
					Object retObject = getLastAlias(filterInfo.getField(), root);
                    Path path = null;
                    if (retObject instanceof String) {
                        path = root.get((String) retObject);
                    } else {
                        path = (Path) retObject;
                    }
                    List<Object> arr = ((List<Object>) filterInfo.getValue());
                    predicates.add(builder.in(path.in(arr)));
				}else if(type.equals(Type.DATE)) {
					setDateFilter(root, builder, predicates, filterInfo);
				}else if(type.equals(Type.NULL)) {
					Object retObject = getLastAlias(filterInfo.getField(), root);
                    Path path = null;
                    if (retObject instanceof String) {
                        path = root.get((String) retObject);
                    } else {
                        path = (Path) retObject;
                    }
                    if (filterInfo.getComparison().equals(Comparision.EQ)) {
                        predicates.add(builder.isNull(path));
                    } else if (filterInfo.getComparison().equals(Comparision.NE)) {
                        predicates.add(builder.isNotNull(path));
                    }
				}
				if (Type.OR.equals(filterInfo.getType())) {
                    List<Predicate> innerPredicateList = setOrFilter(root, builder, filterInfo);
                    if (!innerPredicateList.isEmpty()) {
                        predicates.add(builder.or(innerPredicateList.toArray(new Predicate[innerPredicateList.size()])));
                    }
	            }
			}
		}
		setOrderBy(builder, root, query);
		return builder.and(predicates.toArray(new Predicate[predicates.size()]));
	}
	
	private Object getLastAlias(String fullAlias, Root<T> root) {
        if (!fullAlias.contains(".")) {
            return fullAlias;
        }
        String[] aliasSplit = fullAlias.split("\\.");
        Path<?> path = root;
        for (String s : aliasSplit) {
            path = path.get(s);
        }
        return path;
    }
	
	private void setOrderBy(CriteriaBuilder builder, Root<T> root, CriteriaQuery<?> query) {
        String orderBy = findInfo.getOrderBy();
        Object retObject = getLastAlias(orderBy, root);
        Path path = null;
        if (retObject instanceof String) {
            path = root.get((String) retObject);
        } else {
            path = (Path) retObject;
        }
        query.orderBy(FindInfo.SortOrder.ASC.equals(findInfo.getSortOrder()) ? builder.asc(path) : builder.desc(path), builder.asc(root.get("id")));
    }
	
	//TODO Improvement
    private List<Predicate> setOrFilter(Root<T> root, CriteriaBuilder builder, FilterInfo filterInfo) {
        List<Predicate> innerPredicateList = new ArrayList<>();
        for (FilterInfo info : filterInfo.getFilterInfoList()) {
        	
        	if(!info.getFilterInfoList().isEmpty()){
//				List<Criterion> conditionsInner = new ArrayList<>();
//				getCriterionsForOR(criteria, info.getFilterInfoList(),aliases,conditionsInner);
//				Criterion criterionInner = Restrictions.and(conditionsInner.toArray(new Criterion[conditionsInner.size()]));
//				conditions.add(criterionInner);
			} else {
        	
	            Object retObject = getLastAlias(info.getField().replace("Data", ""),  root);
	            Path path = null;
	            if (retObject instanceof String) {
	                path = root.get((String) retObject);
	            } else {
	                path = (Path) retObject;
	            }
	            if (info.getComparison().equals(Comparision.EQ)) {
	                if (info.getValue() instanceof Collection<?>) {
	                    Object[] array = ((Collection<?>) info.getValue()).toArray();
	                    innerPredicateList.add(builder.in(path.in(array)));
	                } else {
	                    innerPredicateList.add(builder.equal(path, info.getValue()));
	                }
	            } else if (info.getComparison().equals(Comparision.GT)) {
	                innerPredicateList.add(builder.greaterThan(path, info.getValue().toString()));
	            } else if (info.getComparison().equals(Comparision.GE)) {
	                innerPredicateList.add(builder.greaterThanOrEqualTo(path, info.getValue().toString()));
	            } else if (info.getComparison().equals(Comparision.LT)) {
	                innerPredicateList.add(builder.lessThan(path, info.getValue().toString()));
	            } else if (info.getComparison().equals(Comparision.LE)) {
	                innerPredicateList.add(builder.lessThanOrEqualTo(path, info.getValue().toString()));
	            } else if (info.getComparison().equals(Comparision.NE)) {
	                if (info.getValue() instanceof Collection<?>) {
	                    innerPredicateList.add(builder.in(path.in(((Collection<?>) info.getValue()).toArray())).not());
	                } else {
	                    innerPredicateList.add(builder.notEqual(path, info.getValue()));
	                }
	            } else if (info.getComparison().equals(Comparision.iLKS)) {
	                innerPredicateList.add(builder.like(path, info.getValue() + "%"));
	            } else if (info.getComparison().equals(Comparision.iLKS_ANYWHERE)) {
	                innerPredicateList.add(builder.like(path, "%" + info.getValue() + "%"));
	            }
        	}
        }
        return innerPredicateList;
    }
	
	private List<Predicate> setValues(List<Predicate> predicates, Comparision comparision, CriteriaBuilder builder, Object value, Path x) {
        if (comparision.equals(Comparision.EQ)) {
            predicates.add(builder.equal(x, value));
        } else if (comparision.equals(Comparision.NE)) {
            predicates.add(builder.notEqual(x, value));
        } else if (comparision.equals(Comparision.GT)) {
            predicates.add(builder.greaterThan(x, value.toString()));
        } else if (comparision.equals(Comparision.GE)) {
            //TODO For Number Verify once
            predicates.add(builder.greaterThanOrEqualTo(x, value.toString()));
        } else if (comparision.equals(Comparision.LT)) {
            //TODO For Number Verify once
            predicates.add(builder.lessThan(x, value.toString()));
        } else if (comparision.equals(Comparision.LE)) {
            //TODO For Number Verify once
            predicates.add(builder.lessThanOrEqualTo(x, value.toString()));
        } else if (comparision.equals(Comparision.iLKS)) {
            predicates.add(builder.like(x, value + "%"));
        } else {
            predicates.add(builder.like(x, "%" + value + "%"));
        }
        return predicates;
    }
	
	@SuppressWarnings("unchecked")
	private List<Predicate> setDateFilter(Root<T> root, CriteriaBuilder builder, List<Predicate> predicates, FilterInfo filterInfo) {
        String fullAlias = filterInfo.getField().replace("Data", "");
        Object retObject = getLastAlias(fullAlias, root);
        Path path = null;
        if (retObject instanceof String) {
            path = root.get((String) retObject);
        } else {
            path = (Path) retObject;
        }
        try {
            Date dt;
            Object val = filterInfo.getValue();
            if (val instanceof Date) {
                dt = (Date) val;
            } else {
                dt = new SimpleDateFormat("MM/dd/yyyy").parse("" + val);
            }

            if (filterInfo.getComparison().equals(Comparision.EQ)) {
                predicates.add(builder.lessThanOrEqualTo(path, DateUtil.getDateEOD(dt)));
                predicates.add(builder.greaterThanOrEqualTo(path, DateUtil.getDateSOD(dt)));
            } else if (filterInfo.getComparison().equals(Comparision.GT)) {
                predicates.add(builder.greaterThan(path, dt));
            } else if (filterInfo.getComparison().equals(Comparision.GE)) {
                predicates.add(builder.greaterThanOrEqualTo(path, dt));
            } else if (filterInfo.getComparison().equals(Comparision.LT)) {
                predicates.add(builder.lessThan(path, dt));
            } else if (filterInfo.getComparison().equals(Comparision.LE)) {
                predicates.add(builder.lessThanOrEqualTo(path, dt));
            } else {
                predicates.add(builder.between(path, DateUtil.getDateSOD(dt), DateUtil.getDateEOD(dt)).not());
            }
        } catch (ParseException e) {
            // logger.info("Cannot parse date on Filter:" + filterInfo);
        }

        return predicates;
    }

}
