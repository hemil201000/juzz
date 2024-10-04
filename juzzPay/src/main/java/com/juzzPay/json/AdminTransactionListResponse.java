package com.juzzPay.json;

import java.util.List;

public class AdminTransactionListResponse {
	
	private List<AdminTransaction> adminTransactionList;
	private Long totalCount;
	
	public List<AdminTransaction> getAdminTransactionList() {
		return adminTransactionList;
	}
	public void setAdminTransactionList(List<AdminTransaction> adminTransactionList) {
		this.adminTransactionList = adminTransactionList;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
