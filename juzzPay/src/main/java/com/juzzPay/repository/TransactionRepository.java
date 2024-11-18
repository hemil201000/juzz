package com.juzzPay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzPay.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	Optional<Transaction> findByTransactionUqNumber(String number);
	
	Optional<Transaction> findById(Long id);

}
