package com.juzzPay.web.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juzzPay.json.AdminTransactionListRequest;
import com.juzzPay.json.AdminTransactionListResponse;
import com.juzzPay.service.AdminTransactionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin")
@Slf4j
@CrossOrigin
public class AdminTransactionListController {
	
	@Autowired
	public AdminTransactionService adminTransactionService;
	
	@PostMapping("/transaction/list")
	public AdminTransactionListResponse getSurveysList(@RequestBody AdminTransactionListRequest adminTransactionListRequest) throws ParseException {
		AdminTransactionListResponse adminTransactionListResponse = new AdminTransactionListResponse();
		try {
			adminTransactionListResponse = adminTransactionService.getList(adminTransactionListRequest);
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Error Fetching Transaction List" + e);
		}
		return adminTransactionListResponse;
	}

}
