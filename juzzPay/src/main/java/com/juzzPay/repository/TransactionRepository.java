package com.juzzPay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juzzPay.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
