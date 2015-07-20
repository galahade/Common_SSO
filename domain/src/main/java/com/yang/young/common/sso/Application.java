package com.yang.young.common.sso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yang.young.common.sso.persistance.entity.CustomerEntity;
import com.yang.young.common.sso.persistance.entity.RegistrationInfoEntity;
import com.yang.young.common.sso.persistance.repository.CustomerRepository;
import com.yang.young.common.sso.persistance.repository.RegistrationInfoRepository;


@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	RegistrationInfoRepository regInfoRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	

	@Override
	public void run(String... arg0) throws Exception {
		//save customer
		RegistrationInfoEntity regEntity = regInfoRepository.save(new RegistrationInfoEntity("yyang", "123456"));
		customerRepository.save(new CustomerEntity(-1, "USD", regEntity));
		
		//fetch all customer
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for(CustomerEntity customer : customerRepository.findAll()) {
			System.out.println(customer);
		}
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
