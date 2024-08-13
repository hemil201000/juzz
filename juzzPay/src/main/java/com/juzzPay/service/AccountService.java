package com.juzzPay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juzzPay.entity.Account;
import com.juzzPay.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public List<Account> fetchAccountList()
    {
        return (List<Account>)
        		accountRepository.findAll();
    }
	
	public Account fetchEnabledAccount()
    {
        return accountRepository.findByIsActive(true);
    }

}
