package com.project.dd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.dd.login.domain.CustomUser;
import com.project.dd.login.domain.LoginDTO;
import com.project.dd.login.mapper.LoginMapper;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//DB 상에서 회원 정보를 인증 객체에 대입
		//username == 아이디
		LoginDTO dto = mapper.read(username);
		
		//MemberDTO > 시큐리티에서 사용(변환) > CustomUser
		
		return dto != null ? new CustomUser(dto) : null;
	}
	
}
