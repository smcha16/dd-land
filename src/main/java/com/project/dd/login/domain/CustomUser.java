package com.project.dd.login.domain;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
/**
 *  커스텀 회원 클래스입니다.
 * @author 김형우
 *
 */
@Getter
public class CustomUser extends User {

	private LoginDTO dto;
	/**
	 *  권한부여 메서드입니다.
	 * @param username  회원 이름 입니다.
	 * @param password  회원 비밀번호 입니다.
	 * @param authorities  Collection 객체입니다.
	 */
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
		/**
		 *  로그인처리 메서드입니다.
		 * @param dto 로그인객체입니다.
		 */
	public CustomUser(LoginDTO dto) {
		super(dto.getEmail(), dto.getPw(), Collections.singletonList(new SimpleGrantedAuthority(dto.getAuth())));
	
		
		this.dto = dto;
		
	}
	
}
