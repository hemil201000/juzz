//package com.juzzPay.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.juzzPay.entity.User;
//import com.juzzPay.repository.UserRepository;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
//    public User registerUser(String username, String password) {
//        String encodedPassword = passwordEncoder.encode(password);
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(encodedPassword);
//        return userRepository.save(user);
//    }
//
//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//}
