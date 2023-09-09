package com.welltech.globalcash.V21.globalcash.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.welltech.globalcash.V21.globalcash.model.Account;
import com.welltech.globalcash.V21.globalcash.model.User;
import com.welltech.globalcash.V21.globalcash.repository.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class AppController {
	
	private AccountRepository accountRepository;
	
	public AppController(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@GetMapping("/dashboard")
	public ModelAndView getDashboard(HttpSession session) {
		ModelAndView getDashboardPage = new ModelAndView("dashboard");
		
//		//TODO : GET DETAILS OF LOGGED IN USER
		User user = (User) session.getAttribute("userLoggedIn");
		
		System.out.print(user);
//		// TODO: GET ACCOUNT OF LOGGED IN USER
		Account account = accountRepository.getUserAccount(user.getUser_id());
		
		session.setAttribute("accountLoggedIn", account);

		
//		// TODO: GET BALANCE OF LOGGED IN USER
		double totalAcctBalance = accountRepository.getUserBalance(user.getUser_id());
		
//		//TODO: add objects to dashboard
		getDashboardPage.addObject("userAcct", account);
		getDashboardPage.addObject("totalAcctBal",totalAcctBalance);
		
		return getDashboardPage;
	}
}
