package com.yang.young.common.sso.rest.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;

public class UserModel {
	
	public interface WithoutPasswordView {}
	
	private String id;
	
	private String username;
	
	private String password;
	
	private String socialAccountId;
	
	private Set<String> roles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	@JsonView(WithoutPasswordView.class)
	public String getSocialAccountId() {
		return socialAccountId;
	}

	public void setSocialAccountId(String socialAccountId) {
		this.socialAccountId = socialAccountId;
	}
	@JsonView(WithoutPasswordView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
