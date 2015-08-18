package com.yang.young.common.sso.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.yang.young.common.sso.persistance.jpa.repository.CustomerRepository;

@Configuration
@EnableJpaRepositories(basePackages="com.yang.young.common.sso.persistance.jpa.repository")
@EntityScan(basePackages="com.yang.young.common.sso.persistance.jpa.entity")
@ComponentScan(basePackages={"com.yang.young.common.sso.persistance.jpa.service","com.yang.young.common.sso.rest"})
@EnableAutoConfiguration
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
