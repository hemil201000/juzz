package com.juzzPay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzPay.entity.Accounts;
import com.juzzPay.json.TransactionRequest;
import com.juzzPay.json.TransactionResponse;

@Service
public class TransactionService {
	
	@Autowired
	public AccountService accountService;

	public TransactionResponse getQrImage(TransactionRequest transactionRequest) {
		
		Accounts account = accountService.fetchEnabledAccount();
		String Upi = account.getAccountUpi();
		String Name = account.getAccountHolderName();
		Integer amount = transactionRequest.getAmount();
		String upiUrl = "upi://pay?pa=" + Upi + "&pn=" + Name + "&am="+amount + "&cu=INR";
		System.out.println(upiUrl);
		return null;
	}

}
