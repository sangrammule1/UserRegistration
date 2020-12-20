package com.tekleads.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.tekleads.service.UserDetailsService;
import com.tekleads.utils.EmailUtils;

@Controller
public class UserSignInController {
	
	@Autowired
	private UserDetailsService userService;
	
	@Autowired
	private EmailUtils emailUtil;
	
	@GetMapping
	public String loadForm() {
		return "user_signin";
	}

	@GetMapping("/signin")
	public String processSignIn(HttpServletRequest req,Model model) {
		String email = req.getParameter("email");
		String pazzword = req.getParameter("pwd");
		String user = userService.searchUserByEmailAndPazzword(email, pazzword);
		if(user.equals("success"))		
			return "amazon";
		if(user.equals("UnLocked"))
			model.addAttribute("msg","account is locked");
		if(user.equals("Invalid Creadentials"))
			model.addAttribute("msg", "Invalid Creadentials");
		return "user_signin";
	}
	
	@GetMapping("/forgotPwd")
	public String forgotPwd() {
		
		return "forgot_pazzword";
	}
	
	@GetMapping("/verifyPwd")
	public String forgotPwd(HttpServletRequest req) {
		String email = req.getParameter("email");
		String pazzword = userService.getPazzwordByEmail(email);
		emailUtil.sendEmail2(email, pazzword);
		return "forgot_success";
	}
}
