package com.yang.young.common.sso.persistance.jpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.young.common.sso.persistance.jpa.entity.GroupEntity;
import com.yang.young.common.sso.persistance.jpa.repository.GroupRepository;

@Service
public class GroupJPAService {

	private final GroupRepository repository;
	
	@Autowired
	private RoleJPAService roleService;
	
	@Autowired
	public GroupJPAService(GroupRepository repository) {
		this.repository = repository;
	}
	
	public GroupEntity saveGroup(GroupEntity group) {
		return repository.save(group);
	}
	
	public GroupEntity getCustomerGroup() {
		Optional<GroupEntity> optional = repository.findByName("customer_group");
		return optional.get();
	}
	
	public GroupEntity creatDefaultGroups() {
		GroupEntity group = new GroupEntity("customer");
		group.setRoles(roleService.createDefaultRoles());
		group = repository.save(group);
		return group;
	}
}
