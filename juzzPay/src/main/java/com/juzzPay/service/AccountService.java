package com.juzzPay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzPay.entity.Accounts;
import com.juzzPay.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public List<Accounts> fetchAccountList()
    {
        return (List<Accounts>)
        		accountRepository.findAll();
    }

}
