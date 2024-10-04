package com.juzzPay.json;

import java.time.LocalDateTime;

public class AdminTransaction {
	
	private long submitTransactionId;
	private long transactionId;
	private String upiId;
	private String transactionNumber;
	private long accId;
	private double amount;
	private LocalDateTime datetimeTransaction;
	private LocalDateTime datetimeSubmitTransaction;
	
	
	
	public long getSubmitTransactionId() {
		return submitTransactionId;
	}
	public void setSubmitTransactionId(long submitTransactionId) {
		this.submitTransactionId = submitTransactionId;
	}
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public String getUpiId() {
		return upiId;
	}
	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public long getAccId() {
		return accId;
	}
	public void setAccId(long accId) {
		this.accId = accId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDateTime getDatetimeTransaction() {
		return datetimeTransaction;
	}
	public void setDatetimeTransaction(LocalDateTime datetimeTransaction) {
		this.datetimeTransaction = datetimeTransaction;
	}
	public LocalDateTime getDatetimeSubmitTransaction() {
		return datetimeSubmitTransaction;
	}
	public void setDatetimeSubmitTransaction(LocalDateTime datetimeSubmitTransaction) {
		this.datetimeSubmitTransaction = datetimeSubmitTransaction;
	}
	
	
	
	
	
	

}
