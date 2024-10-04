package com.juzzPay.json;

import com.juzzPay.json.Pagination;
import com.juzzPay.json.FilterInfo.Comparision;
import com.juzzPay.json.FilterInfo.Type;

import java.util.ArrayList;
import java.util.List;

public class FindInfo {
	
	public enum SortOrder {
		ASC, DESC
	};
	
	private String orderBy;
	private SortOrder sortOrder = SortOrder.DESC;
	private int start;
	private int limit;
	
	private List<FilterInfo> filterInfoList = new ArrayList<>();
	
	public FindInfo() {
		orderBy = "id";
		start = 0;
		limit = 2000;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public List<FilterInfo> getFilterInfoList() {
		return filterInfoList;
	}
		
	public void addFilterInfo(FilterInfo filterInfo) {
		getFilterInfoList().add(filterInfo);		
	}
	
	public void addFilterInfo(Type type, Object value, String field, Comparision comparison){
		FilterInfo filterInfo = new FilterInfo(type, value, field, comparison);
		addFilterInfo(filterInfo);
	}

	public void sortBy(String propertyName, SortOrder sortOrder) {
		setOrderBy(propertyName);
		setSortOrder(sortOrder);		
	}

	public void paginate(int start, int limit) {
		setLimit(limit);
		setStart(start);
	}

	public void removeFilterInfo(FilterInfo filterInfo) {
		getFilterInfoList().remove(filterInfo);
	}
	
	public void setPagination(Pagination pagination) {
		if (pagination != null) {
			this.paginate(pagination.getStart(), pagination.getLimit());
		}
	}
	
}
