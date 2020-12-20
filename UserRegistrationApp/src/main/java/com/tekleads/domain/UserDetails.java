package com.tekleads.domain;

import java.util.Date;


import lombok.Data;

@Data
public class UserDetails {
	private String fname;
	private String lname;
	private String email;
	private Long phNo;
	private String gender;
	private Date dob;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
}
