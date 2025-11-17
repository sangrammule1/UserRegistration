/**
 * 
 */
package com.tekleads.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekleads.domain.UserDetails;
import com.tekleads.entity.CityMasterEntity;
import com.tekleads.entity.CountryMasterEntity;
import com.tekleads.entity.StateMasterEntity;
import com.tekleads.entity.UserDetailsEntity;
import com.tekleads.repository.CityMasterRepository;
import com.tekleads.repository.CountryMasterRepository;
import com.tekleads.repository.StateMasterRepository;
import com.tekleads.repository.UserDetailsRepository;
import com.tekleads.utils.EmailUtils;
import com.tekleads.utils.PasswordUtils;

/**
 * @author sangram
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsRepository userRepo;

	@Autowired
	private CountryMasterRepository countryRepo;

	@Autowired
	private StateMasterRepository stateRepo;

	@Autowired
	private CityMasterRepository cityRepo;

	@Autowired
	private EmailUtils email;

	@Override
	public Map<Integer, String> getAllCountries() {
		Map<Integer, String> countries = new LinkedHashMap<Integer, String>();
		List<CountryMasterEntity> countryEntity = countryRepo.findAll();
		countryEntity.forEach(entity -> {
			countries.put(entity.getCountryId(), entity.getCountryName());
		});
		return countries;
	}

	@Override
	public Map<Integer, String> getStatesByCountryId(Integer id) {
		Map<Integer, String> states = new LinkedHashMap<Integer, String>();
		List<StateMasterEntity> stateEntity = stateRepo.findByCountryId(id);
		stateEntity.forEach(entity -> {
			states.put(entity.getStateId(), entity.getStateName());
		});
		return states;
	}

	@Override
	public Map<Integer, String> getCityByStateId(Integer id) {
		Map<Integer, String> cities = new LinkedHashMap<Integer, String>();
		List<CityMasterEntity> cityEntity = cityRepo.findByStateId(id);
		cityEntity.forEach(entity -> {
			cities.put(entity.getCityId(), entity.getCityName());
		});
		return cities;
	}

	@Override
	public boolean registerUserDetails(UserDetails user) {
		UserDetailsEntity entity = new UserDetailsEntity();
		BeanUtils.copyProperties(user, entity);
		entity.setAccStatus("Locked");
		entity.setPazzword(PasswordUtils.getAlphaNumericString(6));
		UserDetailsEntity savedUser = userRepo.save(entity);
		email.sendEmail(savedUser);
		return savedUser.getUserId() != null;
	}

	@Override
	public String validateEmail(String email) {
		UserDetailsEntity userEntity = userRepo.findByEmail(email);
		String mail = userEntity.getEmail();
		if (mail == null)
			return "UNIQUE";
		return "DUPLICATE";
	}
	
	public String validateEmailId(String email) {
		String lowerCase = email != null ? email.toLowerCase() : "";
		Object invalidObject = new Integer(10);   // not a String
String result = (String) (invalidObject instanceof String ? invalidObject : "");
		//String obj = (Object)lowerCase.toString();
		return result;
	}

	@Override
	public String validateTempPwd(String pazzword) {
		try {
			String pwd = "";
			UserDetailsEntity userEntity = userRepo.findByPazzword(pazzword);
			pwd = userEntity.getPazzword();
			if (pwd.trim().equals(pazzword.trim()))
				return "MATCH";
		} catch (Exception e) {
			e.printStackTrace();
			return "NOTMATCH";
		}
		return "NOTMATCH";
	}

	@Override
	public String changeAccStatus(String email) {
		userRepo.updateAccStatus(email);
		return "user acc status updated";
	}

	@Override
	public String changePazzword(String pazzword, String email) {
		userRepo.updatePazzword(pazzword, email);
		return "user pazzword updated";
	}

	@Override
	public String searchUserByEmailAndPazzword(String email, String pazzword) {
		UserDetailsEntity userEntity = userRepo.findByEmailAndPazzword(email, pazzword);
		if (userEntity != null) {
			if (userEntity.getAccStatus().equalsIgnoreCase("Unlocked"))
				return "success";
			return "Account is locked";
		}
		return "Invalid Creadentials";
	}

	@Override
	public String getPazzwordByEmail(String email) {
		UserDetailsEntity userEntity = userRepo.findByEmail(email);
		return userEntity.getPazzword();
	}
}