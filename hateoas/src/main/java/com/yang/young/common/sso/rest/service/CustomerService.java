package com.yang.young.common.sso.rest.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.young.common.sso.persistance.jpa.entity.CustomerEntity;
import com.yang.young.common.sso.persistance.jpa.service.CustomerJPAService;
import com.yang.young.common.sso.rest.controller.exception.UserNotFoundException;
import com.yang.young.common.sso.rest.model.UserModel;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerJPAService customerService;
	
	public UserModel storeUser(UserModel userModel) {
		CustomerEntity entity = new CustomerEntity(userModel.getUsername(), userModel.getPassword(), userModel.getSocialAccountId(), -1, "USD");
		customerService.saveCustomerWithDefaultRole(entity);
		userModel.setId(entity.getId().toString());
		userModel.setRoles(entity.getRoles());
		return userModel;
	}
	
	public UserModel findUser(UserModel userModel) {
		Optional<CustomerEntity> customer = customerService.findCustomerByName(userModel.getPassword());
		return customer.map(a-> {
			userModel.setId(a.getId().toString());
			userModel.setSocialAccountId(a.getSocialAccountId());
			userModel.setRoles(a.getRoles());
			return userModel;
		}).orElseThrow(() -> new UserNotFoundException(userModel.getUsername()));
	}
}

