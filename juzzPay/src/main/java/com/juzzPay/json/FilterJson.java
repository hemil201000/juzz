package com.juzzPay.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilterJson {

	@JsonProperty("sort_by")
	private String sortBy;

	@JsonProperty("sort_order")
	private boolean sortOrder;

	@JsonProperty("keyword")
	private String keyword;

	@JsonProperty("pagination")
	private Pagination pagination;

	@JsonProperty("search")
	private List<SearchJson> searchJsons;
	
	public enum Comparision {
		EQ, LE, LT, GE, GT, NE, iLKS_ANYWHERE, iLKS ,iNN;
	}

	public enum Type {
		STRING, LIST, NUMERIC, DATE, LONG, BOOLEAN, NULL, OR, OBJECT;
	}


}
