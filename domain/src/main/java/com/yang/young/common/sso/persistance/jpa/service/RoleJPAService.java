package com.yang.young.common.sso.persistance.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.young.common.sso.persistance.jpa.entity.RoleEntity;
import com.yang.young.common.sso.persistance.jpa.repository.RoleRepository;

@Service
public class RoleJPAService {

	private final RoleRepository repository;
	
	@Autowired
	public RoleJPAService(RoleRepository repository) {
		this.repository = repository;
	}
	
	public RoleEntity saveRole(RoleEntity role) {
		return repository.save(role);
	}
	 
}
