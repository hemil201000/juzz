package com.juzzPay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juzzPay.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
//    Optional<User> findByUsername1(String username);
}
