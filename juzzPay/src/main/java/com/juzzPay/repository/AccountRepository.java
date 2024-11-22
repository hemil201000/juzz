package com.juzzPay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzPay.entity.Account;

@Repository
public interface AccountRepository  extends JpaRepository<Account, Long> {
	
	List<Account> findByIsActive(boolean isActive);

}
