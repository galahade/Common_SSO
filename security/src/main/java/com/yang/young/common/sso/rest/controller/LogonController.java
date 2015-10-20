package com.yang.young.common.sso.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yang.young.common.sso.rest.model.UserModel;
import com.yang.young.common.sso.rest.service.CustomerService;


@RestController
@RequestMapping("/rest")
public class LogonController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(method=RequestMethod.POST,value="logon")
	public UserResource logon(@RequestBody UserModel user) {
		
		return new UserResource(customerService.findUser(user));
	}
	
	@RequestMapping(method=RequestMethod.POST,value="register")
	public UserResource register(@RequestBody UserModel user) {
		
		return new UserResource(customerService.storeUser(user));
	}
	
	

}


class UserResource extends ResourceSupport {

	private final UserModel user;

    public UserResource(UserModel user) {
    	this.user = user;
        this.add(new Link("/rest/"+user.getUsername(), "user-account"));
    }

	public UserModel getUser() {
		return user;
	}

}
