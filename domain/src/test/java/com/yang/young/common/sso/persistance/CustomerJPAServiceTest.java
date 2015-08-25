package com.yang.young.common.sso.persistance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yang.young.common.sso.Application;
import com.yang.young.common.sso.persistance.jpa.entity.CustomerEntity;
import com.yang.young.common.sso.persistance.jpa.service.CustomerJPAService;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=Application.class)
public class CustomerJPAServiceTest {
	
	@Autowired
	CustomerJPAService customerService;
	
	@Test
	public void testSaveCustomerWithDefaultRole() {
//		CustomerEntity customerEntity = new CustomerEntity("yyang","password","yyang@salmon.com", -1, "USD");
//		customerEntity = customerService.saveCustomerWithDefaultRole(customerEntity);
//		
//		assertNotNull(customerEntity);
//		assertNotNull(customerEntity.getId());
//		assertEquals(2, customerEntity.getRoles().size());
		//assert
		
		
				
	}

}
