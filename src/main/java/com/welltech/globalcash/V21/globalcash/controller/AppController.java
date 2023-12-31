package com.welltech.globalcash.V21.globalcash.controller;

import java.math.BigDecimal;
import java.util.List;

import com.welltech.globalcash.V21.globalcash.helper.NumberFormatter;
import com.welltech.globalcash.V21.globalcash.model.TransactionHistory;
import com.welltech.globalcash.V21.globalcash.model.TransferHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.welltech.globalcash.V21.globalcash.model.Account;
import com.welltech.globalcash.V21.globalcash.model.User;
import com.welltech.globalcash.V21.globalcash.repository.AccountRepository;
import com.welltech.globalcash.V21.globalcash.repository.TransactionHistoryRepo;
import com.welltech.globalcash.V21.globalcash.repository.TransferHistoryRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class AppController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionHistoryRepo transactionHistoryRepo;
	
	@Autowired
	private TransferHistoryRepo transferHistoryRepo;
	
	public AppController(AccountRepository accountRepository,TransactionHistoryRepo transactionHistoryRepo,
			TransferHistoryRepo transferHistoryRepo) {
		this.accountRepository = accountRepository;
		this.transactionHistoryRepo=transactionHistoryRepo;
		this.transferHistoryRepo = transferHistoryRepo;
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
		
		//DISPLAY TOTAL DEPOSITS
		double totalDeposits = transactionHistoryRepo.getTotalDeposits(0,account.getAccount_id(),"success");
			
		//DISPLAY TOTAL DEPOSITS
		double totalWithdrawals = Math.abs(transactionHistoryRepo.getTotalWithdrawals(1,account.getAccount_id(),"success"));
			
		double totalTransfers= transferHistoryRepo.getTotalTransfers(account.getAccount_id(), "success");

		List<TransactionHistory> recentTransactionList = transactionHistoryRepo.getRecentTransactions(account.getAccount_id());
		List<TransferHistory> recentTransferList = transferHistoryRepo.getRecentTransfers(account.getAccount_id());

		//TODO: add objects to dashboard
		getDashboardPage.addObject("userAcct", account);
		getDashboardPage.addObject("totalAcctBal", NumberFormatter.formatNum(totalAcctBalance));
		getDashboardPage.addObject("totalDeps",NumberFormatter.formatNum(totalDeposits));
		getDashboardPage.addObject("totalWithd",NumberFormatter.formatNum(totalWithdrawals));
		getDashboardPage.addObject("totalTransfer",NumberFormatter.formatNum(totalTransfers));
		getDashboardPage.addObject("recentTransactions",recentTransactionList);
		getDashboardPage.addObject("recentTransfers",recentTransferList);
		return getDashboardPage;
	}
}
