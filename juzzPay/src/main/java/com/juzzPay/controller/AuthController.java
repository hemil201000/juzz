package com.juzzPay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.juzzPay.entity.User;
import com.juzzPay.json.LoginResponse;
import com.juzzPay.json.userRequestJson;
import com.juzzPay.service.JwtService;
import com.juzzPay.service.UserInfoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Slf4j
public class AuthController {

//    @Autowired
//    private UserService userService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody userRequestJson user) {
//        userService.registerUser(user.getUserName(), user.getPassword());
//        return ResponseEntity.ok("User registered successfully");
//    }

	@PostMapping("/login")
	public LoginResponse login(@RequestBody userRequestJson userRequest) {
		LoginResponse login = new LoginResponse();
		try {

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userRequest.getUserName(), userRequest.getPassword()));
			if (authentication.isAuthenticated()) {
				String token = jwtService.generateToken(userRequest.getUserName());
				login.setAccess_token("Bearer " + token);
				login.setIsVerified(true);
				return login;
			} else {
				throw new UsernameNotFoundException("Invalid user request!");
			}

		} catch (Exception e) {
			log.error("Login Error : " + e);
		}
		return login;
	}
}