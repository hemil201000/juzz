package com.juzzPay.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juzzPay.json.AdminTransactionListRequest;
import com.juzzPay.json.AdminTransactionListResponse;

@RestController
@RequestMapping("/admin")
public class AdminTransactionListController {
	
	@Autowired
	public AdminTransactionService adminTransactionService;
	
	@PostMapping("/transaction/list")
	public AdminTransactionListResponse getSurveysList(@RequestBody AdminTransactionListRequest adminTransactionListRequest) throws ParseException {
		return adminTransactionService.getList(adminTransactionListRequest);
	}

}
