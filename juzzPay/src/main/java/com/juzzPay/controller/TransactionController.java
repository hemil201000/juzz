package com.juzzPay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juzzPay.json.SubmitTransactionRequest;
import com.juzzPay.json.SubmitTransactionResponse;
import com.juzzPay.json.TransactionRequest;
import com.juzzPay.json.TransactionResponse;
import com.juzzPay.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1")
@Slf4j
@CrossOrigin
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/generateQR")
	public TransactionResponse saveAccount(@RequestBody TransactionRequest transactionRequest) {
		TransactionResponse transactionResponse = new TransactionResponse();
		try {
			transactionResponse = transactionService.getQrImage(transactionRequest);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error creating QR : " + e);
		}

		return transactionResponse;
	}

	@PostMapping("/submit/transaction")
	public SubmitTransactionResponse submitTransaction(@RequestBody SubmitTransactionRequest submitTransactionRequest) {
		SubmitTransactionResponse response = new SubmitTransactionResponse();
		try {
			response = transactionService.submitTransaction(submitTransactionRequest);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error Submitting Payment :" + e);
		}

		return response;
	}

}
