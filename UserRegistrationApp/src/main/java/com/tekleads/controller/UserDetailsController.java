package com.tekleads.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tekleads.domain.Pazzword;
import com.tekleads.domain.UserDetails;
import com.tekleads.service.UserDetailsService;

@Controller
public class UserDetailsController {

	@Autowired
	private UserDetailsService userService;
	
	@GetMapping("/loadForm")
	public String loadForm(Model model) {
		UserDetails user = new UserDetails();
		Map<Integer, String> countries = userService.getAllCountries();
		model.addAttribute("country", countries);
		model.addAttribute("userDetails", user);
		return "registration";
	}

	@PostMapping("/loadForm")
	public String saveUserDetails(@ModelAttribute(name = "userDetails") UserDetails user, Model model) {
		boolean userDetails = userService.registerUserDetails(user);
		if (userDetails)
			model.addAttribute("msg", "congratulations your registration is almost done");
		return "registration_success";
	}
	
																																																																																																																																																																													
	@RequestMapping("/getState")
	public @ResponseBody Map<Integer, String> getStatesByCountryId(@RequestParam("cid") Integer countryId) {
		return userService.getStatesByCountryId(countryId);
	}

	@RequestMapping("/getCity")
	public @ResponseBody Map<Integer, String> getCitiesByStateId(@RequestParam("sid") Integer stateId) {
		return userService.getCityByStateId(stateId);
	}

	@RequestMapping("/validateEmail")
	public @ResponseBody String validateEmail(@RequestParam("email") String email) {
		return userService.validateEmail(email);
	}
	
	@RequestMapping("/unlockAcc")
	public String handleUnlockAcc(@RequestParam("email") String email,Model model) {
		Pazzword pazzword=new Pazzword();
		pazzword.setEmail(email);
		model.addAttribute("pwd", pazzword);
		return "unlock_userAcc";
	}

}
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																