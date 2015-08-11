package com.yang.young.common.sso.persistance.jpa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("serial")
@Entity
@Table(name="CUSTOMER")
public class CustomerEntity extends UserEntity {
	
	@Transient
	PasswordEncoder passwordEncoder;
	
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
	
	@Column(name = "TYPE")
    @NotNull
    @Enumerated(EnumType.STRING)
    private CustomerType type = CustomerType.R;
	
	@Column(name = "CURRENCY")
    @NotEmpty
    private String currency;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="USER_GROUP",
				joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="ID"),
				inverseJoinColumns=@JoinColumn(name="GROUP_ID", referencedColumnName="ID"))
	protected Set<GroupEntity> groups = new HashSet<GroupEntity>();

	public Set<GroupEntity> getGroups() {
		return groups;
	}

	public void setGroups(Set<GroupEntity> groups) {
		this.groups = groups;
	}

	
	
	CustomerEntity(){}
	
	public CustomerEntity(String username, String password, String socialAccountId, int languageId, DateTime lastVisitTime,CustomerType type, String currency) {
		this.username = username;
		setPassword(password);
		this.socialAccountId = socialAccountId;
		this.languageId = languageId;
		this.lastVisitTime = lastVisitTime;
		this.type = type;
		this.currency = currency;
	}
	
	public CustomerEntity(String username, String password, String socialAccountId, int languageId, String currency) {
		this.username = username;
		setPassword(password);
		this.socialAccountId = socialAccountId;
		this.languageId = languageId;
		this.currency = currency;
	}


	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Override
	public String toString() {
		return String.format("Customer{id:%d, type:%s, userName:'%s', status:'%s'}", 
				id, type, username, status);
	}
	
	public static enum CustomerType {
		G,R
	}
	
	public static enum AccountStatus {
		Disabled,Enabled,Locked
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	public boolean matchPassword(String rawPassword) {
		if(passwordEncoder == null) {
			passwordEncoder = new BCryptPasswordEncoder();
		}
		return passwordEncoder.matches(rawPassword, getPassword());
	}

	public void setPassword(String rawPassword) {
		if(passwordEncoder == null) {
			passwordEncoder = new BCryptPasswordEncoder();
		}
		this.password = passwordEncoder.encode(rawPassword);
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
	
	public Set<String> getRoles() {
		Set<String> roles = new HashSet<String>();
		if(this.groups != null) {
			for(GroupEntity group : groups) {
				Set<RoleEntity> roleSet = group.getRoles();
				for(RoleEntity role : roleSet) {
					roles.add(role.getName());
				}
			}
		}
		return roles;
	}

}
