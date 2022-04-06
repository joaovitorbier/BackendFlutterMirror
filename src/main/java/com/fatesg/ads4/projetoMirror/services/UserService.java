package com.fatesg.ads4.projetoMirror.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.fatesg.ads4.projetoMirror.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch (Exception e) {
			return null;
		}
	}
	
	
	
}
