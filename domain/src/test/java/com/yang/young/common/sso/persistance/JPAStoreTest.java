package com.yang.young.common.sso.persistance;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yang.young.common.sso.Application;
import com.yang.young.common.sso.persistance.jpa.entity.CustomerEntity;
import com.yang.young.common.sso.persistance.jpa.entity.GroupEntity;
import com.yang.young.common.sso.persistance.jpa.entity.RoleEntity;
import com.yang.young.common.sso.persistance.jpa.service.CustomerJPAService;
import com.yang.young.common.sso.persistance.jpa.service.GroupJPAService;
import com.yang.young.common.sso.persistance.jpa.service.RoleJPAService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
public class JPAStoreTest {
	
	@Autowired
	CustomerJPAService customerService;
	
	@Autowired
	GroupJPAService groupService;
	
	@Autowired
	RoleJPAService roleService;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Before
	public void init() {		
			
		CustomerEntity customerEntity = new CustomerEntity("yyang","password","yyang@salmon.com", -1, "USD");
		
		customerEntity.setGroups(setupGroup(setupRole()));;
				
		customerService.saveCustomer(customerEntity);
		
	}
	
	private Set<RoleEntity> setupRole() {
		RoleEntity role1 = new RoleEntity("Seller");
		RoleEntity role2 = new RoleEntity("Customer");
		
		Set<RoleEntity> roleSet = new HashSet<RoleEntity>();
		roleSet.add(roleService.saveRole(role1));
		roleSet.add(roleService.saveRole(role2));
		return roleSet;
	}
	
	private Set<GroupEntity> setupGroup(Set<RoleEntity> roleSet) {
		GroupEntity group = new GroupEntity("customer_group");
		group.setRoles(roleSet);
		Set<GroupEntity> groupSet = new HashSet<GroupEntity>();
		groupSet.add(groupService.saveGroup(group));
		return groupSet;
	}
	

	@Test
	public void applicationTest() {
		Optional<CustomerEntity> customerOptional = customerService.findCustomerByName("yyang");
		CustomerEntity customer = customerOptional.get();
		assertNotNull(customerOptional);
		assertEquals(customer.getUsername(), "yyang");
		//assertEquals(customer.getPassword(),"password");
		assertTrue(encoder.matches("password", customer.getPassword()));
		Set<String> roles = customer.getRoles();
		assertTrue(roles.contains("Seller"));
		assertTrue(roles.contains("Customer"));
	}
}
