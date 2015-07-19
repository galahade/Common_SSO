package com.yang.young.common.sso.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="REGINFO")
public class RegistrationInfoEntity extends BaseEntity {
	
	@Column(name = "USER_NAME")
	@NotEmpty
	private String userName;
	
	@Column(name = "PASSWORD")
	@NotEmpty
	private String password;
	
	@Column(name = "STATUS")
	@NotEmpty
	private int status;
	
	@Column(name = "EXPIRED")
	@NotEmpty
	private boolean expired;
	
	@Column(name = "SOCIAL_ACCOUNT_ID")
	private String socialAccountId;
	
	RegistrationInfoEntity() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public String getSocialAccountId() {
		return socialAccountId;
	}

	public void setSocialAccountId(String socialAccountId) {
		this.socialAccountId = socialAccountId;
	}
	

}
