package com.project.dd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.dd.login.domain.CustomUser;
import com.project.dd.login.domain.LoginDTO;
import com.project.dd.login.mapper.LoginMapper;

/**
 * 사용자 정의 UserDetailsService. 클래스입니다.
 *  
 */
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginMapper mapper;
	/**
	 * 입력받은 username에 해당하는 사용자 정보를 조회하고, UserDetails 객체로 변환하여 반환.
	 * @param username 사용자 아이디
	 * @return UserDetails 객체
	 * @throws UsernameNotFoundException 사용자가 존재하지 않는 경우 예외 발생
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// DB 상에서 회원 정보를 인증 객체에 대입
		// username == 아이디
		
		LoginDTO dto = mapper.read(username);
		
		// MemberDTO > 시큐리티에서 사용(변환) > CustomUser
		
		return dto != null ? new CustomUser(dto) : null;
		
	}
	
}
