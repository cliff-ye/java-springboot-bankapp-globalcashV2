package com.welltech.globalcash.V21.globalcash.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.welltech.globalcash.V21.globalcash.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.welltech.globalcash.V21.globalcash.model.Account;
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
	private SmsService smsService;

	
	public TransactController(AccountRepository accountRepository,TransactionHistoryRepo transactionHistoryRepo,
			TransferHistoryRepo transferHistoryRepo,RestTemplate restTemplate) {
		this.accountRepository = accountRepository;
		this.transactionHistoryRepo=transactionHistoryRepo;
		this.transferHistoryRepo=transferHistoryRepo;
		this.smsService = smsService;
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

		String message = "Hi "+user.getFirst_name()+"\nYour account has been credited GHS "+deposit_amount+"\nDate: "+createdAt.format(format)+"\nBal: GHS "+new_balance;
		smsService.sendSms(user.getPhone(), message);
		
		int logResult = transactionHistoryRepo.logTransaction(account.getAccount_id(), "deposit", deposit_amount, "success", "deposited successfully", createdAt);
		
		redirectAttributes.addFlashAttribute("successmsg","Deposited successfully");
		return "redirect:/show-trans-success";
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

		String sender_phone =  accountRepository.getRecipientNumber(receipient_account.getUser_id());
		String message = "Hi "+receipient_account.getAccount_name()+"\nYou have received GHS "+trans_amount+" from "+sender_account.getAccount_name()+"\nDate: "+createdAt.format(format)+"\nCurrent Bal: GHS "+recipient_new_bal;
		smsService.sendSms(sender_phone, message);

		transferHistoryRepo.logTransfers(sender_account.getAccount_id(), receipient_account.getAccount_name(), receipient_account.getAccount_number(), trans_amount, reference, "success", "Transfer successful", createdAt);
		
		String msg = "Transfered successfully to "+receipient_account.getAccount_name()
						+". Total Amount is GHS "+trans_amount;
		
		redirectAttributes.addFlashAttribute("successmsg",msg);
		return "redirect:/show-gc-trans-success";
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

		String user_phone = user.getPhone();
		String message = "Hi "+user.getFirst_name()+"\nYour account has been debited GHS "+withdrawalAmt+"\nDate: "+createdAt.format(format)+"\nBal: GHS "+new_balance;
		smsService.sendSms(user_phone, message);
			
		transactionHistoryRepo.logTransaction(account.getAccount_id(), "withdrawal", -withdrawalAmt, "success", "withdrawal successful", createdAt);
		
		 redirectAttributes.addFlashAttribute("successmsg","withdraw successful");
		 return "redirect:/show-trans-success";
	}

	@PostMapping("/airtime")
	public String sendAirtime(@RequestParam("mobileNumber") String phone,
							  @RequestParam("amount") String amount,
							  RedirectAttributes redirectAttributes,HttpSession session){

		if(phone.isEmpty() || amount.isEmpty()){
			redirectAttributes.addFlashAttribute("error","field must not be empty");
			return "redirect:/app/dashboard";
		}

		Double airtime_amount = Double.parseDouble(amount);

		if(airtime_amount<= 0){
			redirectAttributes.addFlashAttribute("zeroerror","Amount must not be zero or less");
			return "redirect:/app/dashboard";
		}

		User user = (User) session.getAttribute("userLoggedIn");

		Account account = (Account) session.getAttribute("accountLoggedIn");

		double current_acc_bal = accountRepository.getUserBalance(user.getUser_id());

		if(current_acc_bal < airtime_amount){
			transactionHistoryRepo.logTransaction(account.getAccount_id(), "Airtime", airtime_amount, "failed", "insufficient Funds", createdAt);
			redirectAttributes.addFlashAttribute("insufficientFunds","Insufficient funds to perform withdrawal");
			return "redirect:/app/dashboard";
		}

		double new_balance = current_acc_bal - airtime_amount;

		accountRepository.updateAcctBalance(new_balance,account.getAccount_id());

//		//sender's message
//	    String user_phone = user.getPhone();
//		String message1 = "Hi "+user.getFirst_name()+"\nPayment made for GHS "+airtime_amount+" AIRTIME to "+phone+" has been completed\nDate: "+createdAt.format(format)+"\nBal: GHS "+new_balance;
//	    sms.sendSMS(user_phone, message1);

		//recipient message
		String message2 = "Hello, You have received GHS "+airtime_amount+" AIRTIME from "+user.getPhone()+"\nDate: "+createdAt.format(format)+"\nGlobal cash";
		smsService.sendSms(phone, message2);

		transactionHistoryRepo.logTransaction(account.getAccount_id(), "Airtime", airtime_amount, "success", "Airtime purchase successful", createdAt);

		redirectAttributes.addFlashAttribute("successmsg","Airtime sent");
		return "redirect:/show-gc-trans-success";
	}

	@PostMapping("/transfer-momo")
	public String makeTransferMomo(@RequestParam("mobNumber")String phone,
							   @RequestParam("reference")String reference,
							   @RequestParam("transfer_amount")String transfer_amount,
							   RedirectAttributes redirectAttributes,HttpSession session) {

		//TODO: validate empty fields
		if(phone.isEmpty() || reference.isEmpty() || transfer_amount.isEmpty()) {
			redirectAttributes.addFlashAttribute("error","field must not be empty");
			return "redirect:/app/dashboard";
		}

		Account sender_account = (Account) session.getAttribute("accountLoggedIn");

		//TODO : Check if user is transferring to same account
		if(phone.equalsIgnoreCase(sender_account.getAccount_number())) {
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
			transferHistoryRepo.logTransfers(sender_account.getAccount_id(), phone, phone, trans_amount, reference, "failed", "insufficient Funds", createdAt);
			redirectAttributes.addFlashAttribute("insufficientFunds","Insufficient funds to perform transfer");
			return "redirect:/app/dashboard";
		}

		double sender_new_acct_bal = sender_current_acc_bal - trans_amount;
		accountRepository.updateAcctBalance(sender_new_acct_bal,sender_account.getAccount_id());

		String message2 = "Hello, You have received GHS "+trans_amount+" from "+sender_account.getAccount_name()+"\nDate: "+createdAt.format(format)+"\nGlobal cash";
		smsService.sendSms(phone, message2);

		transferHistoryRepo.logTransfers(sender_account.getAccount_id(), phone,phone, trans_amount, reference, "success", "Transfer successful", createdAt);

		String msg = "Transfered successfully to "+phone
				+". Total Amount is GHS "+trans_amount;

		redirectAttributes.addFlashAttribute("successmsg",msg);
		return "redirect:/show-gc-trans-success";
	}


}
