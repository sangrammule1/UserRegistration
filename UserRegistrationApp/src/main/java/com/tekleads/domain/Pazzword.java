package com.tekleads.domain;

import lombok.Data;

@Data
public class Pazzword {
	
	private String email;
	private String oldPwd;
	private String newPwd;
	private String confirmPwd;

}
