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
		userModel.setPassword(entity.getPassword());
		userModel.setRoles(entity.getRoles());
		return userModel;
	}
	
	public UserModel findUser(UserModel userModel) {
		Optional<CustomerEntity> customer = customerService.findCustomerByName(userModel.getUsername());
		return mapEntityToModel(customer, userModel);
	}
	
	public UserModel findUser(String userId) {
		Optional<CustomerEntity> customer = customerService.findCustomerByName(userId);
		UserModel model = new UserModel();
		return mapEntityToModel(customer, model);
	}
	
	private UserModel mapEntityToModel(Optional<CustomerEntity> entity, UserModel model) {
		return entity.map(a -> {
			model.setId(a.getId().toString());
			model.setUsername(a.getUsername());
			model.setPassword(a.getPassword());
			model.setSocialAccountId(a.getSocialAccountId());
			model.setRoles(a.getRoles());
			return model;
		}).orElseThrow(()-> new UserNotFoundException(model.getUsername()));
	}
}

