package com.tekleads.controller;

import com.tekleads.service.UserDetailsServiceImpl;

public class Jme {
	public static void main(String[] args) {
		UserDetailsServiceImpl u = new UserDetailsServiceImpl();
		String s = u.validateEmailId(null);
		System.out.println(s);
	}
}
