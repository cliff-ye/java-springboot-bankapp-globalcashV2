package com.welltech.globalcash.V21.globalcash.helper;

import java.util.Random;

public class GenerateAcctNumber {
	
	public static int generateAccountNumber() {
		
		Random random = new Random();
		int bound = 1000000000;
		int accountNumber = random.nextInt(bound);
		return  accountNumber;
	}

}
