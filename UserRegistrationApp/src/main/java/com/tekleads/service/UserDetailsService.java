package com.tekleads.service;

import java.util.Map;

import com.tekleads.domain.UserDetails;

public interface UserDetailsService {
	
	public Map<Integer,String> getAllCountries();
	
	public Map<Integer, String> getStatesByCountryId(Integer id);
	
	public Map<Integer, String> getCityByStateId(Integer id);
	
	public boolean registerUserDetails(UserDetails user);
	
	public String validateEmail(String email);
	
	public String validateTempPwd(String pazzword);
	
	public String changeAccStatus(String email);
	
	public String changePazzword(String pazzword,String email);
	
	public String searchUserByEmailAndPazzword(String email,String pazzword);
	
	public String getPazzwordByEmail(String email);
}
