package com.yang.young.common.sso.persistance.jpa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@Table(name="RGROUP")
public class GroupEntity extends BaseEntity {

	@Column(name="GROUP_NAME")
	@NotEmpty
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="GROUP_ROLE",
				joinColumns=@JoinColumn(name="GROUP_ID",referencedColumnName="id"),
				inverseJoinColumns=@JoinColumn(name="ROLE_ID",referencedColumnName="id")
			)
	private Set<RoleEntity> roles = new HashSet<RoleEntity>();
	
	@ManyToMany(mappedBy="groups")
	private Set<CustomerEntity> users  = new HashSet<CustomerEntity>();
	
	GroupEntity(){}
	
	public GroupEntity(String name) {
		this.name = name;
	}

	public Set<CustomerEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<CustomerEntity> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}
}
