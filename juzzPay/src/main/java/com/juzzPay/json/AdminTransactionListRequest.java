package com.juzzPay.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminTransactionListRequest {
	
	@JsonProperty("filter")
	private FilterJson filterJson;

	public FilterJson getFilterJson() {
		return filterJson;
	}

	public void setFilterJson(FilterJson filterJson) {
		this.filterJson = filterJson;
	}
	
	

}
