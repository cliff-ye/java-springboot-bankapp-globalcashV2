package com.welltech.globalcash.V21.globalcash.controlleradvisor;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.welltech.globalcash.V21.globalcash.model.Account;
import com.welltech.globalcash.V21.globalcash.model.User;

@ControllerAdvice
public class ControllerAdvisor {
	
	@ModelAttribute("user")
	public User getUserBean() {
		return new User();
	}
	
	@ModelAttribute("account")
	public Account getAccountBean() {
		return new Account();
	}

}
