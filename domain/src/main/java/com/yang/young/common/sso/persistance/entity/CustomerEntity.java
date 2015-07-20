package com.yang.young.common.sso.persistance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

@Entity
@Table(name="CUSTOMER")
public class CustomerEntity extends UserEntity {
	
	@Column(name = "TYPE")
    @NotNull
    @Enumerated(EnumType.STRING)
    private CustomerType type = CustomerType.R;
	
	@Column(name = "CURRENCY")
    @NotEmpty
    private String currency;
	
	@OneToOne
	private RegistrationInfoEntity regInfo;
	
	CustomerEntity(){}
	
	public CustomerEntity(int languageId, DateTime lastVisitTime,CustomerType type, String currency, RegistrationInfoEntity regInfo) {
		this.languageId = languageId;
		this.lastVisitTime = lastVisitTime;
		this.type = type;
		this.currency = currency;
		this.regInfo = regInfo;
	}
	
	public CustomerEntity(int languageId, String currency, RegistrationInfoEntity regInfo) {
		this.languageId = languageId;
		this.currency = currency;
		this.regInfo = regInfo;
	}

	public RegistrationInfoEntity getRegInfo() {
		return regInfo;
	}

	public void setRegInfo(RegistrationInfoEntity regInfo) {
		this.regInfo = regInfo;
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
				id, type, regInfo == null ? "null" : regInfo.getUsername(), regInfo == null ? "null" : regInfo.getStatus());
	}
	
	public static enum CustomerType {
		G,R
	}

}
