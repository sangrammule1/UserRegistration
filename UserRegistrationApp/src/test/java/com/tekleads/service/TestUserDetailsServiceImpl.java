package com.tekleads.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.tekleads.domain.UserDetails;
import com.tekleads.entity.CityMasterEntity;
import com.tekleads.entity.CountryMasterEntity;
import com.tekleads.entity.StateMasterEntity;
import com.tekleads.entity.UserDetailsEntity;
import com.tekleads.repository.CityMasterRepository;
import com.tekleads.repository.CountryMasterRepository;
import com.tekleads.repository.StateMasterRepository;
import com.tekleads.repository.UserDetailsRepository;

@SpringBootTest
public class TestUserDetailsServiceImpl {

	@InjectMocks
	private UserDetailsServiceImpl service;

	@Mock
	private CountryMasterRepository countryRepo;
	
	@Mock
	private StateMasterRepository stateRepo;
	
	@Mock
	private CityMasterRepository cityRepo;
	
	@Mock
	private UserDetailsRepository userRepo;

	public static List<CountryMasterEntity> listCountry = null;

	public static List<StateMasterEntity> listStates = null;

	public static List<CityMasterEntity> listCities = null;
	
	public static UserDetails userDetails=null;

	public static UserDetailsEntity userDetailsEntity=null;

	@BeforeAll
	public static void init() {

		listCountry = new ArrayList<CountryMasterEntity>();

		CountryMasterEntity entity1 = new CountryMasterEntity();
		entity1.setCountryId(1);
		entity1.setCountryName("INDIA");

		CountryMasterEntity entity2 = new CountryMasterEntity();
		entity2.setCountryId(2);
		entity2.setCountryName("USA");

		listCountry.add(entity1);
		listCountry.add(entity2);

		listStates = new ArrayList<StateMasterEntity>();

		StateMasterEntity state1 = new StateMasterEntity();
		state1.setStateId(1);
		state1.setStateName("MH");

		StateMasterEntity state2 = new StateMasterEntity();
		state2.setStateId(2);
		state2.setStateName("TS");

		listStates.add(state1);
		listStates.add(state2);
		
		listCities = new ArrayList<CityMasterEntity>();

		CityMasterEntity city1 = new CityMasterEntity();
		city1.setCityId(1);
		city1.setCityName("PUNE");

		CityMasterEntity city2 = new CityMasterEntity();
		city2.setCityId(2);
		city2.setCityName("MUMBAI");

		listCities.add(city1);
		listCities.add(city2);
		
		userDetails=new UserDetails();
		userDetails.setCityId(5);
		userDetails.setCountryId(1);
		userDetails.setEmail("san@gmail.com");
		userDetails.setFname("Ram");
		userDetails.setLname("Mule");
		
		userDetailsEntity=new UserDetailsEntity();
		BeanUtils.copyProperties(userDetails, userDetailsEntity);
		userDetailsEntity.setUserId(111);

	}

	@Test
	public void testGetAllCountries() {
		when(countryRepo.findAll()).thenReturn(listCountry);

		Map<Integer, String> countries = service.getAllCountries();
		assertNotNull(countries);

	}
	
	@Test
	public void testGetStatesByCountryId() {
		when(stateRepo.findByCountryId(1)).thenReturn(listStates);

		Map<Integer, String> states = service.getStatesByCountryId(1);
		assertNotNull(states);

	}
	
	@Test
	public void testGetCityByStateId() {
		when(cityRepo.findByStateId(4)).thenReturn(listCities);
		
		Map<Integer, String> cities = service.getCityByStateId(4);
		assertNotNull(cities);
		
	}

	@Test
	@Disabled
	public void testRegisterUserDetails() {
		when(userRepo.save(userDetailsEntity)).thenReturn(userDetailsEntity);
		
		boolean isRegisterd = service.registerUserDetails(userDetails);
		assertEquals(true, isRegisterd);
	}
}
