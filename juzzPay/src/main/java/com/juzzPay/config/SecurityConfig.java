//package com.juzzPay.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable() // Disable CSRF for simplicity; adjust based on your needs
//            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                .requestMatchers("/api/login", "/api/register").permitAll() // Allow access to login and register
//                .anyRequest().authenticated() // Require authentication for all other requests
//            )
//            .httpBasic(); // Use Basic Auth for simplicity
//
//        return http.build();
//    }
//}