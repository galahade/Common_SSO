package com.yang.young.common.sso.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackages="com.yang.young.common.sso.persistance.jpa.repository")
@EntityScan(basePackages="com.yang.young.common.sso.persistance.jpa.entity")
@ComponentScan(basePackages={"com.yang.young.common.sso.persistance.jpa.service","com.yang.young.common.sso.rest","com.yang.young.common.sso.config"})
@EnableAutoConfiguration
public class Application {
	
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

