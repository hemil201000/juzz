package com.juzzPay.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.juzzPay.entity.SubmitTransaction;
import com.juzzPay.entity.Transaction;
import com.juzzPay.json.AdminTransaction;
import com.juzzPay.json.AdminTransactionListRequest;
import com.juzzPay.json.AdminTransactionListResponse;
import com.juzzPay.json.FindInfo;
import com.juzzPay.repository.SubmitTransactionRepository;
import com.juzzPay.repository.TransactionRepository;
import com.juzzPay.repository.specs.SpecificationImpl;
import com.juzzPay.util.FilterHelper;

@Service
public class AdminTransactionService {
	
	@Autowired
	private SubmitTransactionRepository submitTransactionRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	public AdminTransactionListResponse getList(AdminTransactionListRequest adminTransactionListRequest) throws ParseException {

		AdminTransactionListResponse adminTransactionListResponse = new AdminTransactionListResponse();
		List<SubmitTransaction> adminTransactionList = null;
		FindInfo findInfo = new FindInfo();
		findInfo.setPagination(adminTransactionListRequest.getFilterJson().getPagination());
		FilterHelper.filter(findInfo, adminTransactionListRequest.getFilterJson(), SubmitTransaction.class);
		SpecificationImpl<SubmitTransaction> specificationImpl = new SpecificationImpl<>();
		

		specificationImpl.setFindInfo(findInfo);
		int start = findInfo.getStart();
		int limit = findInfo.getLimit();
		start = start / limit;
		Pageable pageable = PageRequest.of(start, limit);
		List<Object[]> test;
		adminTransactionList =  submitTransactionRepository.findAll(specificationImpl, pageable).getContent();
		
		adminTransactionListResponse.setAdminTransactionList(convertToTransactionList(adminTransactionList));
		adminTransactionListResponse.setTotalCount(submitTransactionRepository.count(specificationImpl));

		return adminTransactionListResponse;
	}

	public List<AdminTransaction> convertToTransactionList(List<SubmitTransaction> submitTransactionList) {
		List<AdminTransaction> adminTransactionList = new ArrayList<>();
		submitTransactionList.forEach(transaction -> {
			adminTransactionList.add(convertToTransactionJson(transaction));
		});

		return adminTransactionList;
	}

	private AdminTransaction convertToTransactionJson(SubmitTransaction submitTransaction) {
		
		Optional<Transaction> getTransaction = transactionRepository.findById(submitTransaction.getTransaction().getId());
		AdminTransaction adminTransaction = new AdminTransaction();
		
//		transaction = transactionRepository.findById(submitTransaction.getTransaction().getId());
		Transaction transaction = new Transaction();
		if(getTransaction.isPresent()) {
			transaction = getTransaction.get();
		}
		if(transaction != null) {
			adminTransaction.setAccId(transaction.getAccount().getId());
			adminTransaction.setAmount(transaction.getAmount());
			adminTransaction.setTransactionId(transaction.getTransactionUqNumber());
			adminTransaction.setDatetimeTransaction(transaction.getCreatedDatetime());
		}
		
		if(submitTransaction.getTransactionNumber() != null) {
			adminTransaction.setTransactionNumber(submitTransaction.getTransactionNumber());
		}
		if(submitTransaction.getUpiID() != null) {
			adminTransaction.setUpiId(submitTransaction.getUpiID());
		}
		adminTransaction.setClientTransactionId(submitTransaction.getSubmitTransactionUqNumber());
		adminTransaction.setDatetimeSubmitTransaction(submitTransaction.getCreatedDatetime());
		return adminTransaction;
	}

}
