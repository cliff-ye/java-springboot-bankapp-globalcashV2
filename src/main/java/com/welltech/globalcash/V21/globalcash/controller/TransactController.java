package com.welltech.globalcash.V21.globalcash.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.welltech.globalcash.V21.globalcash.helper.SMS;
import com.welltech.globalcash.V21.globalcash.model.Account;
import com.welltech.globalcash.V21.globalcash.model.TransactionHistory;
import com.welltech.globalcash.V21.globalcash.model.User;
import com.welltech.globalcash.V21.globalcash.repository.AccountRepository;
import com.welltech.globalcash.V21.globalcash.repository.TransactionHistoryRepo;
import com.welltech.globalcash.V21.globalcash.repository.TransferHistoryRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/transact")
public class TransactController {
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TransactionHistoryRepo transactionHistoryRepo;
	@Autowired
	private TransferHistoryRepo transferHistoryRepo;
	@Autowired
	private SMS sms;
	
	
	public TransactController(AccountRepository accountRepository,TransactionHistoryRepo transactionHistoryRepo,
			TransferHistoryRepo transferHistoryRepo,RestTemplate restTemplate) {
		this.accountRepository = accountRepository;
		this.transactionHistoryRepo=transactionHistoryRepo;
		this.transferHistoryRepo=transferHistoryRepo;
		this.sms = sms;
	}
	
	LocalDateTime createdAt= LocalDateTime.now();
	DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
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
		
		//TODO : Get deposit amt balance
		double deposit_amount = Double.parseDouble(depositAmt);
		
		//TODO : Check if deposit amt is a real number
		if(deposit_amount <= 0) {
			redirectAttributes.addFlashAttribute("zeroerror","Amount must not be zero or less");
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
		
//		String apiUrl = "https://sms.nalosolutions.com/smsbackend/clientapi/Resl_Nalo/send-message/?username=cliff_ye&password=cliff20$$$&type=0&destination=233546571831&dlr=1&source=GBL-C+ALERT&message=Deposited+successfully+globalcash";
//		restTemplate.getForObject(apiUrl, String.class);
		String message = "Hi "+user.getFirst_name()+"\nYour account has been credited GHS "+deposit_amount+"\nDate: "+createdAt.format(format)+"\nBal: GHS "+new_balance;
		sms.sendSMS(user.getPhone(), message);
		
		int logResult = transactionHistoryRepo.logTransaction(account.getAccount_id(), "deposit", deposit_amount, "success", "deposited successfully", createdAt);
		
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
		if(trans_amount <= 0) {
			redirectAttributes.addFlashAttribute("zeroerror","Amount must not be zero or less");
			return "redirect:/app/dashboard";
		}
		
		//TODO: get date transfer date
		//LocalDateTime transferredAt= LocalDateTime.now();
		
		//TODO : subtract from current balance and update senders acct balance
		double sender_current_acc_bal = accountRepository.getUserBalance(sender_account.getUser_id());
		
		if(sender_current_acc_bal < trans_amount) {
			transferHistoryRepo.logTransfers(sender_account.getAccount_id(), receipient_account.getAccount_name(), receipient_account.getAccount_number(), trans_amount, reference, "failed", "insufficient Funds", createdAt);
			redirectAttributes.addFlashAttribute("insufficientFunds","Insufficient funds to perform transfer");
			return "redirect:/app/dashboard";
		}
		
		double sender_new_acct_bal = sender_current_acc_bal - trans_amount;
		accountRepository.updateAcctBalance(sender_new_acct_bal,sender_account.getAccount_id());
		
		//TODO : add to recipient current balance and update recipient acct balance
		double recipient_current_bal = accountRepository.getUserBalance(receipient_account.getUser_id());
		double recipient_new_bal = recipient_current_bal + trans_amount;
		accountRepository.updateAcctBalance(recipient_new_bal,receipient_account.getAccount_id());
		
		transferHistoryRepo.logTransfers(sender_account.getAccount_id(), receipient_account.getAccount_name(), receipient_account.getAccount_number(), trans_amount, reference, "success", "Transfer successful", createdAt);
		
		String msg = "Transfered successfully to "+receipient_account.getAccount_name()
						+". Total Amount is GHS "+trans_amount;
		
		redirectAttributes.addFlashAttribute("successmsg",msg);
		return "redirect:/app/dashboard";
	}
	
	@PostMapping("/withdraw")
	public String makeWithdrawal(@RequestParam("amount")String withdrawal_amt,
								HttpSession session, RedirectAttributes redirectAttributes) {
		
		if(withdrawal_amt.isEmpty()) {
			redirectAttributes.addFlashAttribute("error","field must not be empty");
			return "redirect:/app/dashboard";
		}
			
			
		//TODO : get current user from session
		User user = (User) session.getAttribute("userLoggedIn");
				
		// GET account id from session
		Account account = (Account) session.getAttribute("accountLoggedIn");
				
		//convert withdrawal amt
		double withdrawalAmt = Double.parseDouble(withdrawal_amt);
		
		//TODO : Check if deposit amt is a real number
		if(withdrawalAmt <= 0) {
			redirectAttributes.addFlashAttribute("zeroerror","Amount must not be zero or less");
			return "redirect:/app/dashboard";
		  }
		
		//TODO : UPDATE ACCOUNT TABLE
		double current_acc_bal = accountRepository.getUserBalance(user.getUser_id());
		
		if(current_acc_bal < withdrawalAmt) {
			transactionHistoryRepo.logTransaction(account.getAccount_id(), "withdrawal", -withdrawalAmt, "failed", "insufficient Funds", createdAt);
			redirectAttributes.addFlashAttribute("insufficientFunds","Insufficient funds to perform withdrawal");
			return "redirect:/app/dashboard";
		}
		
		double new_balance = current_acc_bal - withdrawalAmt;
				
		int res = accountRepository.updateAcctBalance(new_balance,account.getAccount_id());
		
				
		if(res <= 0) {
		   redirectAttributes.addFlashAttribute("failedmsg","Failed to withdraw");
		   return "redirect:/app/dashboard";
		 }
			
		transactionHistoryRepo.logTransaction(account.getAccount_id(), "withdrawal", -withdrawalAmt, "success", "withdrawal successful", createdAt);
		
		 redirectAttributes.addFlashAttribute("successmsg","withdraw successful");
		 return "redirect:/app/dashboard";
				
		
	}

}
