package com.yang.young.common.sso.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="REGINFO")
public class RegistrationInfo {
	
	@Column(name = "USER_NAME")
	@NotEmpty
	private String userName;
	
	@Column(name = "password")
	@NotEmpty
	private String password;
	
	@Column(name = "status")
	@NotEmpty
	private int status;
	
	@Column(name = "expired")
	@NotEmpty
	private boolean expired;
	

}
