package com.welltech.globalcash.V21.globalcash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.welltech.globalcash.V21.globalcash.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexController {

	@GetMapping("/index")
	public String showIndexPage() {
		return "index";
	}
	
	@GetMapping("/no-acct")
	public String showNoAcctPage(ModelMap model) {
		return "no-account";
	}
	
	
	
	

}
