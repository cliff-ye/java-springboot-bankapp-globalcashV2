package com.welltech.globalcash.V21.globalcash.controller;

import java.time.LocalDateTime;

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
	
	@PostMapping("/transfer")
	public String makeTransfer(@RequestParam("accountHolder")String account_holder,
							   @RequestParam("reference")String reference,
							   @RequestParam("transfer_amount")String transfer_amount,
							   RedirectAttributes redirectAttributes,HttpSession session) {
		
		//TODO: validate empty fields
		if(account_holder.isEmpty() || reference.isEmpty() || transfer_amount.isEmpty()) {
			redirectAttributes.addFlashAttribute("error","field must not be empty");
			return "redirect:/app/dashboard";
		}
		
		//TODO: get accounts
		Account receipient_account = accountRepository.getUserAcctByAcctNumber(account_holder);
		
		//TODO : Check if receipient account exist
		if(receipient_account == null) {
			redirectAttributes.addFlashAttribute("userNotFoundError","Account does not exist");
			return "redirect:/app/dashboard";
		}
		
		Account sender_account = (Account) session.getAttribute("accountLoggedIn");
		
		//TODO : Check if user is transferring to same account
		if(account_holder.equalsIgnoreCase(sender_account.getAccount_number())) {
			redirectAttributes.addFlashAttribute("transError","Can not transfer into same account");
			return "redirect:/app/dashboard";
		}
		
		//TODO: CONVERT VARS
		double trans_amount = Double.parseDouble(transfer_amount);
		
		//TODO : check for zero values
		if(trans_amount == 0) {
			redirectAttributes.addFlashAttribute("zeroerror","Amount must not be zero");
			return "redirect:/app/dashboard";
		}
		
		//TODO: get date transfer date
		//LocalDateTime transferredAt= LocalDateTime.now();
		
		//TODO : subtract from current balance and update senders acct balance
		double sender_current_acc_bal = accountRepository.getUserBalance(sender_account.getUser_id());
		double sender_new_acct_bal = sender_current_acc_bal - trans_amount;
		accountRepository.updateAcctBalance(sender_new_acct_bal,sender_account.getAccount_id());
		
		//TODO : add to recipient current balance and update recipient acct balance
		double recipient_current_bal = accountRepository.getUserBalance(receipient_account.getUser_id());
		double recipient_new_bal = recipient_current_bal + trans_amount;
		accountRepository.updateAcctBalance(recipient_new_bal,receipient_account.getAccount_id());
		
		
		String msg = "Transfered successfully to "+receipient_account.getAccount_name()
						+". Total Amount is GHS "+trans_amount;
		
		redirectAttributes.addFlashAttribute("successmsg",msg);
		return "redirect:/app/dashboard";
	}

}
