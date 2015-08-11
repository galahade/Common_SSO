package com.yang.young.common.sso.rest.controller;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest")
public class LogonController {
	
	@RequestMapping(method=RequestMethod.GET,value="logon/{userId}")
	public LogonResource logon(@PathVariable String userId) {
		
		return new LogonResource(userId);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="logon/{userId}")
	public LogonResource logon() {
		return null;
	}

}


class LogonResource extends ResourceSupport {


    public LogonResource(String username) {
        this.add(new Link("/rest/"+username, "user-account"));
    }

}
