package com.yang.young.common.sso.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yang.young.common.sso.persistance.jpa.entity.CustomerEntity;
import com.yang.young.common.sso.persistance.jpa.entity.CustomerEntity.AccountStatus;
import com.yang.young.common.sso.persistance.jpa.service.CustomerJPAService;



public class DBUserDetailsServiceHandler implements UserDetailsService {

	@Autowired
	private CustomerJPAService customerService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Optional<CustomerEntity> customerOptional = customerService.findCustomerByName(username);
		User user = customerOptional.map(a -> new User(a.getUsername(), a.getPassword(), a.getStatus() == AccountStatus.Enabled, 
						a.isExpired(), true, a.getStatus() != AccountStatus.Locked,
						AuthorityUtils.createAuthorityList(a.getRoles().toArray(new String[0]))))
						.orElseThrow(()->new UsernameNotFoundException("could not find the user '"+ username + "'"));
		
		return user;
	}

}
