package com.welltech.globalcash.V21.globalcash.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.welltech.globalcash.V21.globalcash.helper.SMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.welltech.globalcash.V21.globalcash.model.User;
import com.welltech.globalcash.V21.globalcash.repository.UserRepository;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;

@Controller
public class RegisterUserController {
	
	private UserRepository userRepository;
	@Autowired
	private SMS sms;

	//autowire via constructor
	public RegisterUserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/reguser")
	public String showRegisterUserPage() {
		return "register-user";
	}
	
	
	@PostMapping("/reguser")
	public ModelAndView regUserDetails(ModelMap model,@ModelAttribute("user") @Valid  User user, BindingResult result,
			@RequestParam("confirm_password") String confirm_password) {
		
		ModelAndView regPage = new ModelAndView("register-user");
		ModelAndView loginPage = new ModelAndView("login");
		ModelAndView alertsuccess = new ModelAndView("reg-success");

		if(result.hasErrors() && confirm_password.isEmpty()) {
			model.put("message", result.getAllErrors());
			regPage.addObject("confirm_pass","field cannot be empty");
			return regPage;
		}
		
		// TODO: check for password match
		if(!user.getPassword().equals(confirm_password)) {
			regPage.addObject("passMismatch","password mismatch");
			return regPage;
		}
		
		// TODO: hash password
		String hashed_password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		
		//TODO : Date time
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
         LocalDateTime createdAt= LocalDateTime.now();
		
		//TODO : REGISTER USER
		int res = userRepository.addUser(user.getFirst_name(), user.getLast_name(), user.getPhone(), user.getEmail(), hashed_password,createdAt);

		//TODO : CHECK IF IT WENT TO INTO DB
		if(res == 1) {
			//SUCCESS MSG
			String msg = "Registered successfully. Proceed to login";
			loginPage.addObject("successmessage", msg);
//			String message2 = "Dear "+user.getFirst_name()+",\nYou have successfully registered with global cash. Login to add an account and Enjoy Global banking experience with globalcash";
//			sms.sendSMS(user.getPhone(), message2);
			return loginPage;
		}
		else {
			//FAILED MSG
			String msg = "Failed to register user";
			regPage.addObject("failedmessage", msg);
			return regPage;
		}
	
		
	}
}
