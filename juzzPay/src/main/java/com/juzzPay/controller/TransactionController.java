package com.juzzPay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juzzPay.json.SubmitTransactionRequest;
import com.juzzPay.json.TransactionRequest;
import com.juzzPay.json.TransactionResponse;
import com.juzzPay.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService; 
	
	@PostMapping("/generateQR")
	public void saveAccount(@RequestBody TransactionRequest transactionRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse = transactionService.getQrImage(transactionRequest);

	}
	
	@PostMapping("/submit/transaction")
	public String submitTransaction(@RequestBody SubmitTransactionRequest submitTransactionRequest ) {
		
		String response = transactionService.submitTransaction(submitTransactionRequest);
		return "DONE";
	}

}
