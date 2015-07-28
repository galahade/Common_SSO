package com.yang.young.common.sso.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class TestAuthorityService {

	@PreAuthorize("authenticated")
	public String getMessage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		return "Hello " + auth;
	}
}
