package com.yang.young.common.sso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yang.young.common.sso.persistance.repository.CustomerRepository;
import com.yang.young.common.sso.persistance.repository.RegistrationInfoRepository;


@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	RegistrationInfoRepository regInfoRepository;

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}
