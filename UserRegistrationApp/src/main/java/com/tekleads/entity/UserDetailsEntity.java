package com.tekleads.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_DETAILS")
public class UserDetailsEntity {
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "FNAME")
	private String fname;
	
	@Column(name = "LNAME")
	private String lname;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PHNO")
	private Long phNo;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "DOB")
	private Date dob;
	
	@Column(name = "COUNTRY_ID")
	private Integer countryId;
	
	@Column(name = "STATE_ID")
	private Integer stateId;
	
	@Column(name = "CITY_ID")
	private Integer cityId;
	
	@Column(name = "PAZZWORD")
	private String pazzword;
	
	@Column(name = "ACC_STATUS")
	private String accStatus;
	
	@Column(name = "CREATED_DATE",updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name = "UPDATED_DATE",insertable = false)
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	private Date updatedDate;

}
