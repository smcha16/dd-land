package com.project.dd.login.domain;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User {

	private LoginDTO dto;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(LoginDTO dto) {
		super(dto.getEmail(), dto.getPw(), Collections.singletonList(new SimpleGrantedAuthority(dto.getAuth())));
	
		
		this.dto = dto;
		
	}
	
}
