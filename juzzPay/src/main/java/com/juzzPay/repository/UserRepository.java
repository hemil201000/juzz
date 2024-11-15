package com.juzzPay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juzzPay.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
