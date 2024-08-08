package com.juzzPay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juzzPay.entity.Accounts;
import com.juzzPay.service.AccountService;

@RestController
@RequestMapping("/test")
public class AccountController {
	
	@Autowired
	private AccountService accountService; 
	
	@GetMapping("/accounts")
    public List<Accounts> fetchAccountsList()
    {
        return accountService.fetchAccountList();
    }

}
