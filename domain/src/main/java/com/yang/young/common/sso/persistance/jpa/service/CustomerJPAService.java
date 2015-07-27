package com.yang.young.common.sso.persistance.jpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.young.common.sso.persistance.jpa.entity.CustomerEntity;
import com.yang.young.common.sso.persistance.jpa.repository.CustomerRepository;

@Service
public class CustomerJPAService {
	
	private final CustomerRepository customerRepository;
	
	@Autowired
	public CustomerJPAService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public Optional<CustomerEntity> findCustomerByName(String username) {
		return customerRepository.findByUsername(username);
	}

}
