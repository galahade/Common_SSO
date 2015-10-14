package com.yang.young.common.sso.rest.controller;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class AdminController {
	
	
	@RequestMapping(method=RequestMethod.GET,value="admin") 
	public AdminResource getAccountInfo(){
		
		return new AdminResource();
	}

}

class AdminResource extends ResourceSupport {

    public AdminResource() {
        this.add(new Link("/rest/admin", "admin-url"));
    }

}
