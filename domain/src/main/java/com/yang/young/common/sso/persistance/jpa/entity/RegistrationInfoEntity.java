package com.yang.young.common.sso.persistance.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="REGINFO")
public class RegistrationInfoEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "USER_NAME")
	@NotEmpty
	private String username;
	
	@Column(name = "PASSWORD")
	@NotEmpty
	private String password;
	
	@Column(name = "STATUS")
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private AccountStatus status = AccountStatus.Enabled;
	
	@Column(name = "EXPIRED")
	@NotNull
	private boolean expired = false;
	
	@Column(name = "SOCIAL_ACCOUNT_ID")
	private String socialAccountId;
	
	RegistrationInfoEntity() {
		
	}
	
	public RegistrationInfoEntity(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public RegistrationInfoEntity(String username, String password, AccountStatus status, boolean expired) {
		this.username = username;
		this.password = password;
		this.status = status;
		this.expired = expired;
	}

	public String getUsername() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
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
	
	public static enum AccountStatus {
		Disabled,Enabled
	}
	

}
