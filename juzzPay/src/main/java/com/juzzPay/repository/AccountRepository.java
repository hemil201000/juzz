package com.juzzPay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juzzPay.entity.Accounts;

@Repository
public interface AccountRepository  extends JpaRepository<Accounts, Long> {

}
