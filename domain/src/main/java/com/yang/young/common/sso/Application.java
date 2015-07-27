package com.yang.young.common.sso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yang.young.common.sso.persistance.jpa.entity.CustomerEntity;
import com.yang.young.common.sso.persistance.jpa.entity.GroupEntity;
import com.yang.young.common.sso.persistance.jpa.entity.RoleEntity;
import com.yang.young.common.sso.persistance.jpa.repository.CustomerRepository;
import com.yang.young.common.sso.persistance.jpa.repository.GroupRepository;
import com.yang.young.common.sso.persistance.jpa.repository.RoleRepository;


@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	GroupRepository groupRepository;	

	@Override
	public void run(String... arg0) throws Exception {
		//save customer
		RoleEntity role1 = new RoleEntity("Seller");
		RoleEntity role2 = new RoleEntity("Customer");
		roleRepository.save(role1);
		roleRepository.save(role2);
		
		GroupEntity group = new GroupEntity("customer_group");
		
		group.getRoles().add(role1);
		group.getRoles().add(role2);
		groupRepository.save(group);
		
		
		CustomerEntity customerEntity = new CustomerEntity("yyang","password","yyang@salmon.com", -1, "USD");
		customerEntity.getGroups().add(group);
		
		customerRepository.save(customerEntity);
		
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
