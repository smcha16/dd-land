package com.project.dd.login.service;


import org.springframework.stereotype.Service;

import com.project.dd.login.repository.LoginDAOImpl;
import com.project.dd.register.domain.MemberDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final LoginDAOImpl dao;

	public MemberDTO login(String email, String pw) {
		
		return dao.findByLoginId(email)
				.filter(m -> m.getPw().equals(pw))
				.orElse(null);
	}
	
	

}
