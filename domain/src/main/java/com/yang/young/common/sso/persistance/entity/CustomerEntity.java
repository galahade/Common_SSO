package com.yang.young.common.sso.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="CUSTOMER")
public class CustomerEntity extends UserEntity {
	
	@Column(name = "TYPE")
    @NotEmpty
    protected String type;
	
	@Column(name = "CURRENCY")
    @NotEmpty
    protected String currency;

}
