package com.juzzPay.util;


import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.List;

import com.juzzPay.json.FilterJson;
import com.juzzPay.json.SearchJson;
import com.juzzPay.json.FilterInfo;
import com.juzzPay.json.FilterInfo.Comparision;
import com.juzzPay.json.FilterInfo.Type;
import com.juzzPay.json.FindInfo;
import com.juzzPay.json.FindInfo.SortOrder;
public class FilterHelper {

	/**
	 * Returns true if given {@code fieldName} is exist in {@code entityClass}
	 * 
	 * @param fieldName
	 * @param entityClass
	 * @return
	 */
	public static boolean isValidField(String fieldName, Class<?> entityClass) {
		String[] split = fieldName.split("\\.");
		for (int i = 0; i < split.length; i++) {
			String s = split[i];
			Field[] declaredFields = entityClass.getDeclaredFields();
			for (Field f : declaredFields) {
				if (f.getName().equals(s)) {
					if (i == split.length - 1) {
						return true;
					} else {
						entityClass = f.getType();
						break;
					}
				}
			}
		}
		return false;
	}

	public static FindInfo setSort(FindInfo findInfo, String fieldName, boolean sortOrder, Class<?> entityClass) {
		if (fieldName != null && FilterHelper.isValidField(fieldName, entityClass)) {
			findInfo.sortBy(fieldName, sortOrder ? SortOrder.ASC : SortOrder.DESC);
		}
		return findInfo;
	}

	public static FindInfo filter(FindInfo findInfo, String keyword, String... fields) {
		if (keyword != null && !keyword.trim().isEmpty()) {
			keyword = keyword.trim();
			FilterInfo searchInfo = new FilterInfo(Type.OR);
			for (String field : fields) {
				searchInfo.addFilterInfo(Type.STRING, keyword, field, Comparision.iLKS);
			}
			findInfo.addFilterInfo(searchInfo);
		}
		return findInfo;
	}

	public static FindInfo setSort(FindInfo findInfo, String fieldName, boolean sortOrder) {
		if (fieldName != null) {
			findInfo.sortBy(fieldName, sortOrder ? SortOrder.ASC : SortOrder.DESC);
		}
		return findInfo;
	}

	public static FindInfo setFilter(FindInfo findInfo, FilterJson filterJson, Class<?> entityClass, String... fields) {
		if (filterJson != null) {
			filter(findInfo, filterJson.getKeyword(), fields);
			setSort(findInfo, findInfo.getOrderBy(), filterJson.isSortOrder(), entityClass);
		}
		return findInfo;
	}

	public static void setSearch(FindInfo findInfo, List<SearchJson> list, Class<?> entityClass) throws ParseException {
		if (list != null) {
			for (SearchJson search : list) {
				if (search.getText() != null && !search.getText().toString().isEmpty()) {
					Type type = search.getType(entityClass);
					Object obj = null;
					if(Type.DATE.equals(type)){
						obj = DateUtil.parse(search.getText() + "", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
					} else if(Type.BOOLEAN.equals(type)){
						if(search.getText().toString().equalsIgnoreCase("TRUE")){
							obj = true;
						} else if(search.getText().toString().equalsIgnoreCase("FALSE")){
							obj = false;
						} else {
							obj = search.getText();
						}
					} else {
						obj = search.getText();
					}

					findInfo.addFilterInfo(type,
							//Type.DATE.equals(type) ? DateUtil.parse(search.getText() + "", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd") : search.getText(),
							obj,
							search.getColumn(),
							search.comparision());
				}
			}
		}
	}

	public static FindInfo filter(FindInfo findInfo, FilterJson filterJson, Class<?> entityClass) throws ParseException {
		if (filterJson != null) {
			setSort(findInfo, filterJson.getSortBy(), filterJson.isSortOrder());
			findInfo.setPagination(filterJson.getPagination());
			setSearch(findInfo, filterJson.getSearchJsons(), entityClass);
		}
		return findInfo;
	}
}
