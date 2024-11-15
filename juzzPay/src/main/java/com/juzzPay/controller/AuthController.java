//package com.juzzPay.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//import com.juzzPay.entity.User;
//import com.juzzPay.json.userRequestJson;
//import com.juzzPay.service.UserService;
//import com.juzzPay.util.JwtUtil;
//
//@RestController
//@RequestMapping("/api")
//public class AuthController {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody userRequestJson user) {
//        userService.registerUser(user.getUserName(), user.getPassword());
//        return ResponseEntity.ok("User registered successfully");
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody userRequestJson userRequest) {
//        User user = userService.findByUsername(userRequest.getUserName());
//        if (user != null && new BCryptPasswordEncoder().matches(userRequest.getPassword(), user.getPassword())) {
//            String token = jwtUtil.generateToken(userRequest.getUserName());
//            return ResponseEntity.ok().header("Authorization", "Bearer " + token).body("Login successful");
//        }
//        return ResponseEntity.status(401).body("Login failed. Please check your credentials.");
//    }
//}