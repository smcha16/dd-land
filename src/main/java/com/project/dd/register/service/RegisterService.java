package com.project.dd.register.service;

import org.springframework.stereotype.Service;

import com.project.dd.register.repository.RegisterDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService {
	
	private final RegisterDAO dao;
	
	public int check(String email) {
		return dao.check(email);
	}
	
	
	

}
