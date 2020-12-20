package com.tekleads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tekleads.domain.Pazzword;
import com.tekleads.service.UserDetailsService;

@Controller
public class UnlockUserAccessController {
	
	@Autowired
	private UserDetailsService userService;
	
	@RequestMapping("/validateTempPwd")
	@ResponseBody
	public String validateTempPwd(@RequestParam("pwd") String pazzword) {
		 String isValid = userService.validateTempPwd(pazzword);
		 return isValid;
	}
	
	@PostMapping("/changeAccStatus")
	public String unlockUserAcc(@ModelAttribute("pwd") Pazzword pazzword,Model model) {
		String status = userService.changeAccStatus(pazzword.getEmail());
		 userService.changePazzword(pazzword.getConfirmPwd(),pazzword.getEmail());
		model.addAttribute("status",status);
		return "amazon";
	}

}
