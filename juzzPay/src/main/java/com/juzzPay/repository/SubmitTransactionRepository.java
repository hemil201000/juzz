package com.juzzPay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzPay.entity.SubmitTransaction;

@Repository
public interface SubmitTransactionRepository extends JpaRepository<SubmitTransaction, Long>{

}
