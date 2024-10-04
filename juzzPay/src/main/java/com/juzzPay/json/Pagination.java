package com.juzzPay.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagination {

	@JsonProperty("start")
	private int start;

	@JsonProperty("limit")
	private int limit;

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

	public static Pagination getDefaultPagination() {
		return getDefaultPagination(20);
	}

	public static Pagination getDefaultPagination(int limit) {
		Pagination pagination = new Pagination();
		pagination.setStart(0);
		pagination.setLimit(limit);
		return pagination;
	}

}
