package com.welltech.globalcash.V21.globalcash.controller;

import com.welltech.globalcash.V21.globalcash.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.Session;
import com.welltech.globalcash.V21.globalcash.helper.Token;
import com.welltech.globalcash.V21.globalcash.repository.AccountRepository;
import com.welltech.globalcash.V21.globalcash.repository.UserRepository;
import com.welltech.globalcash.V21.globalcash.repository.service.AuthService;

import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class AuthController {
	
	@Autowired
	private AuthService authservice;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	private User user;
	
	@GetMapping("/login")
	public String getLoginPage(ModelMap model) {
		//needed for the user session
		String token = Token.generateToken();
		model.addAttribute("token", token);
		
		return "login";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam("email")String email,
			                @RequestParam("password")String password,
			                @RequestParam("token")String token, ModelMap model,HttpSession session) {
		
		//TODO: validate input fields
		if(authservice.checkEmptiness(email, password)) {
			model.addAttribute("errormsg", "email or password required!!");
			return "login";
		}
		
		//TODO : check if email exists
		String getDbEmail = userRepository.getEmail(email);
		
		//get password
		String getDbPassword = userRepository.getPassword(getDbEmail);
		
		if(getDbEmail == null || getDbPassword==null) {
			model.addAttribute("errorDetail","Oops!...Something went wrong");
			return "error";
		}
		
		//verify/validate password with bcrypt
		//bcrypt.checkpw return a boolean
		if(!BCrypt.checkpw(password, getDbPassword)) {
			model.addAttribute("errorMsg1","incorrect email or password");
			return "login";
		}
		
		//get logged in user
		user = userRepository.getUserDetails(getDbEmail);
		
		//set session
		session.setAttribute("userLoggedIn",user);
		session.setAttribute("token",token);
		
		//System.out.print(session.getAttribute("userLoggedIn"));
		
		//TODO : check if the user has an account or not. to display which page
		String acct_number = accountRepository.getAcctNumber(user.getUser_id());
		
		if(acct_number == null) {
			//model.put("link","/no-acct");
			return "success";
		}
		
		//model.addAttribute("user",user);
		return "redirect:/yes-success";
		
		//return "login";
	}
	
	@GetMapping("/logout-user")
	public String logout(HttpSession session ) {
		session.invalidate();
		//redirectAttributes.addFlashAttribute("logged_out","Logged out successfully");
		return "redirect:/show-logout";
	}
}
