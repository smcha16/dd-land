package com.project.dd.login.service;


import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dd.login.repository.LoginDAOImpl;
import com.project.dd.register.domain.MemberDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final LoginDAOImpl dao;
	private final BCryptPasswordEncoder passwordEncoder;

	public MemberDTO login(String email, String pw) {
		
		return dao.findByLoginId(email)
				.filter(m -> m.getPw().equals(pw))
				.orElse(null);
	}

	public MemberDTO findId(@Valid MemberDTO memberDTO) {
		
		
		return dao.findId(memberDTO);
	}

	public MemberDTO findMember(String user_seq) {
		return dao.findMember(user_seq);
	}

	public int changePw(String pw, String user_seq) {
		return dao.changePw(pw,user_seq);
	}

	public void passwordEncoder(MemberDTO memberDTO) {
		String encodedPassword = passwordEncoder.encode(memberDTO.getPw());
	    memberDTO.setPw(encodedPassword);
	    dao.encoderPw(memberDTO);
	}

	public String findSeq(@Valid MemberDTO memberDTO) {
		return dao.findSeq(memberDTO);
	}

	
	
	

}
