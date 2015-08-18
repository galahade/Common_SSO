package com.yang.young.common.sso.persistance.jpa.service;

import java.util.HashSet;
import java.util.Set;

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
	
	public Set<RoleEntity> createDefaultRoles() {
		RoleEntity role1 = new RoleEntity("buyer");
		RoleEntity role2 = new RoleEntity("seller");
		
		Set<RoleEntity> roles = new HashSet<RoleEntity>();
		repository.save(role1);
		repository.save(role2);
		roles.add(role1);
		roles.add(role2);
		return roles;
	}
	 
}
