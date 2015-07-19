package com.yang.young.common.sso.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="CUSTOMER")
public class CustomerEntity extends UserEntity {
	
	@Column(name = "TYPE")
    @NotEmpty
    private String type;
	
	@Column(name = "CURRENCY")
    @NotEmpty
    private String currency;
	
	@OneToOne
	private RegistrationInfoEntity regInfo;
	
	CustomerEntity(){}

	public RegistrationInfoEntity getRegInfo() {
		return regInfo;
	}

	public void setRegInfo(RegistrationInfoEntity regInfo) {
		this.regInfo = regInfo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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
				id, type, regInfo == null ? "null" : regInfo.getUserName(), regInfo == null ? "null" : regInfo.getStatus());
	}
	
	public static enum CustomerType {
		G,R
	}

}
