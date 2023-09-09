package com.welltech.globalcash.V21.globalcash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.welltech.globalcash.V21.globalcash.model.Account;
import com.welltech.globalcash.V21.globalcash.model.User;
import com.welltech.globalcash.V21.globalcash.repository.AccountRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/transact")
public class TransactController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping("/deposit")
	public String makeDeposit(@RequestParam("amount")String depositAmt,
							  HttpSession session, RedirectAttributes redirectAttributes) {
		
		//TODO: Check for empty/null values
		if(depositAmt.isEmpty() || depositAmt == null) {
			redirectAttributes.addFlashAttribute("error","field must not be empty");
			return "redirect:/app/dashboard";
		}
		
		//TODO : get current user from session
		User user = (User) session.getAttribute("userLoggedIn");
		
		//TODO: GET account id from session
		Account account = (Account) session.getAttribute("accountLoggedIn");
		
		//TODO : Get current account balance
		double deposit_amount = Double.parseDouble(depositAmt);
		
		//TODO : Check if deposit amt is a real number
		if(deposit_amount == 0) {
			redirectAttributes.addFlashAttribute("zeroerror","Amount must not be zero");
			return "redirect:/app/dashboard";
		}
		
		//TODO : UPDATE ACCOUNT TABLE
		double current_acc_bal = accountRepository.getUserBalance(user.getUser_id());
		double new_balance = current_acc_bal + deposit_amount;
		
		int res = accountRepository.updateAcctBalance(new_balance,account.getAccount_id());
		
		if(res <= 0) {
			redirectAttributes.addFlashAttribute("failedmsg","Failed to deposit");
			return "redirect:/app/dashboard";
		}
		
		redirectAttributes.addFlashAttribute("successmsg","Deposited successfully");
		return "redirect:/app/dashboard";
	}

}
