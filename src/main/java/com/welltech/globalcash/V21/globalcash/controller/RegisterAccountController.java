package com.welltech.globalcash.V21.globalcash.controller;

import java.time.LocalDateTime;

import com.welltech.globalcash.V21.globalcash.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.welltech.globalcash.V21.globalcash.helper.GenerateAcctNumber;
import com.welltech.globalcash.V21.globalcash.model.Account;
import com.welltech.globalcash.V21.globalcash.model.User;
import com.welltech.globalcash.V21.globalcash.repository.AccountRepository;
import com.welltech.globalcash.V21.globalcash.repository.TransactionHistoryRepo;
import com.welltech.globalcash.V21.globalcash.repository.TransferHistoryRepo;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class RegisterAccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TransactionHistoryRepo transactionHistoryRepo;
	@Autowired
	private TransferHistoryRepo transferHistoryRepo;
	@Autowired
	private SmsService sms;

	@GetMapping("/reg-acct")
	public String showRegisterAccount() {
		return "register-account"; 
	}
	
	@PostMapping("/reg-acct")
	public String createAcount(ModelMap model,@ModelAttribute("account") @Valid Account account,
								BindingResult result, RedirectAttributes redirectAttributes,
								HttpSession session) {
		
		//check for errors/ emptiness
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("erromsg","Field cannot be empty");
			return "redirect:/reg-acct";
		}
		//TODO: Get logged in user's id
		User user = (User) session.getAttribute("userLoggedIn");
		
		//TODO: Generate acct number
		String setAcctNumber = Integer.toString(GenerateAcctNumber.generateAccountNumber());
		
		//TODO : Date time
		// DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime createdAt= LocalDateTime.now();
		account.setAccount_name(user.getFirst_name()+" "+user.getLast_name());

        //TODO : ADD ACCOUNT
        int res = accountRepository.addAccount(user.getUser_id(),setAcctNumber,account.getAccount_name(),account.getAccount_type(),account.getAccount_balance(),createdAt);
		String message2 = "Your global cash account number : "+setAcctNumber+"\nEnjoy Global banking experience with globalcash";

		if(res == 1) {
        	account = accountRepository.getUserAcctByAcctNumber(setAcctNumber);
			sms.sendSms(user.getPhone(), message2);
        	transactionHistoryRepo.logTransaction(account.getAccount_id(), "deposit", account.getAccount_balance(), "success", "activation deposit", createdAt);
        	transferHistoryRepo.logTransfers(account.getAccount_id(),account.getAccount_name(), setAcctNumber, account.getAccount_balance(), "activation", "success", "activation", createdAt);
        	return "redirect:/reg-acc-success";
        }
        else {
        	redirectAttributes.addFlashAttribute("failedmsg","Failed to create account");
        	return "redirect:/reg-acct";
        }
		
	}

}
