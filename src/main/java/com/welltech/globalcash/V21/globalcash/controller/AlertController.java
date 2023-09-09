package com.welltech.globalcash.V21.globalcash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AlertController {
	
	@GetMapping("/reg-success")
	public String showRegSuccessPage() {
		return "reg-success";
	}
	
	@GetMapping("/reg-acc-success")
	public String showRegSuccessPage2() {
		return "reg-acc-success";
	}
	
	@GetMapping("/success")
	public String showSuccessPage() {
		
		return "success";
	}
	
	@GetMapping("/yes-success")
	public String showSuccessPage2() {
		return "yes-acct-success";
	}
	
	@GetMapping("/show-logout")
	public String showLogoutAlert() {
		return "loggedout-success";
	}

}
