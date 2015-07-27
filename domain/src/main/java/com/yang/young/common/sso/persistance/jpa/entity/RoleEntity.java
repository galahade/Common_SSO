package com.yang.young.common.sso.persistance.jpa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@Table(name="ROLE")
public class RoleEntity extends BaseEntity {

	@Column(name="ROLE_NAME")
	@NotEmpty
	private String name ;
	
	@ManyToMany(mappedBy="roles")
	private Set<GroupEntity> groups = new HashSet<GroupEntity>();
	
	RoleEntity() {}
	
	public RoleEntity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<GroupEntity> getGroups() {
		return groups;
	}

	public void setGroups(Set<GroupEntity> groups) {
		this.groups = groups;
	}
	
	
}
