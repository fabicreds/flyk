package com.tcc.flyk.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("teste") && password.equals("123456");
	}

}
