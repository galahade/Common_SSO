package com.yang.young.common.sso.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yang.young.common.sso.persistance.jpa.repository.CustomerRepository;
import com.yang.young.common.sso.persistance.jpa.service.CustomerJPAService;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.yang.young.common.sso.persistance.jpa.repository")
@EntityScan(basePackages="com.yang.young.common.sso.persistance.jpa.entity")
public class Application {
	
	@Autowired
	CustomerRepository service;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
