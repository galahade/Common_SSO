package com.yang.young.common.sso.persistance.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public class UserEntity extends BaseEntity {

	@Column(name = "language_Id")
	@NotNull
	protected int languageId;
	
	@Column(name = "LAST_VISIT_TIME")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	protected DateTime lastVisitTime = DateTime.now();

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public DateTime getLastVisitTime() {
		return lastVisitTime;
	}

	public void setLastVisitTime(DateTime lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}

}
