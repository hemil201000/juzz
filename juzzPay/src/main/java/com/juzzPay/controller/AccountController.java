package com.juzzPay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juzzPay.entity.Account;
import com.juzzPay.json.TransactionRequest;
import com.juzzPay.json.TransactionResponse;
import com.juzzPay.service.AccountService;
import com.juzzPay.service.TransactionService;

@RestController
@RequestMapping("/test")
public class AccountController {
	
	@Autowired
	private AccountService accountService; 
	
	@Autowired
	private TransactionService transactionService; 
	
	@GetMapping("/accounts")
    public List<Account> fetchAccountsList()
    {
        return accountService.fetchAccountList();
    }
	
	@PostMapping("/generateQR")
	public void saveAccount(@RequestBody TransactionRequest transactionRequest)
	    {
			TransactionResponse transactionResponse = new TransactionResponse();
			transactionResponse = transactionService.getQrImage(transactionRequest);
			
	    }

}
