package com.welltech.globalcash.V21.globalcash.repository.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
	
	public boolean checkEmptiness(String email,String password) {
		
		boolean isNotEmpty = email.isEmpty() || email==null || password.isEmpty() || password == null?true:false;
		
		return isNotEmpty;
	}

}
