package com.project.dd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.dd.login.domain.CustomUser;
import com.project.dd.login.domain.LoginDTO;
import com.project.dd.login.mapper.LoginMapper;

/**
 * Spring Security의 UserDetailsService를 구현한 사용자 정의 서비스 클래스입니다.
 * @author pega0
 *
 */
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginMapper mapper;
	
	/**
     * 주어진 사용자 이름(username)에 해당하는 사용자 정보를 가져와 UserDetails 객체로 변환합니다.
     *
     * @param username 사용자 이름
     * @return UserDetails 객체 (사용자 정보를 포함)
     * @throws UsernameNotFoundException 주어진 사용자 이름에 해당하는 사용자를 찾을 수 없을 때 발생하는 예외
     */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//DB 상에서 회원 정보를 인증 객체에 대입
		//username == 아이디
		LoginDTO dto = mapper.read(username);
		
		//MemberDTO > 시큐리티에서 사용(변환) > CustomUser
		
		return dto != null ? new CustomUser(dto) : null;
	}
	
}
