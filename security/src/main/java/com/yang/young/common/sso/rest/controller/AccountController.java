package com.yang.young.common.sso.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yang.young.common.sso.rest.model.UserModel;
import com.yang.young.common.sso.rest.service.CustomerService;

@RestController
@RequestMapping("/rest")
public class AccountController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(method=RequestMethod.GET,value="account/{userId}") 
	public UserResource getAccountInfo(@PathVariable(value="userId") String userId){
		
		return null;//new UserResource(customerService.findUser(userId));
	}
	
	@RequestMapping(method=RequestMethod.GET,value="account/detail") 
	public UserResource getAccountInfo(){
		UserDetails userDetails = 
		(UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new UserResource(userDetails);// new UserResource();
	}
	
	class UserResource extends ResourceSupport {

		private final UserDetails user;

	    public UserResource(UserDetails user) {
	    	this.user = user;
	        this.add(new Link("/rest/"+user.getUsername(), "user-account"));
	    }

		public UserDetails getUser() {
			return user;
		}

}
}
