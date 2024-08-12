package com.juzzPay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzPay.entity.Accounts;

@Repository
public interface AccountRepository  extends JpaRepository<Accounts, Long> {
	
//	Accounts findByisActive();
	
//	List<Accounts> findByIsActive(boolean isActive);
	
	Accounts findByIsActive(boolean isActive);

}
