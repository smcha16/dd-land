package com.project.dd.login.service;


import java.util.List;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dd.login.repository.LoginDAOImpl;
import com.project.dd.register.domain.MemberDTO;

import lombok.RequiredArgsConstructor;
/**
 *  LoginService 클래스 입니다.
 *  로그인 서비스를 담당합니다.
 * @author 김형우
 *
 */
@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final LoginDAOImpl dao;
	private final BCryptPasswordEncoder passwordEncoder;
	/**
	 *  로그인 메서드입니다.
	 * @param email 회원 이메일입니다.
	 * @param pw   회원비밀번호 입니다.
	 * @return  회원 객체를 반환합니다. 
	 */
	public MemberDTO login(String email, String pw) {
		
		return dao.findByLoginId(email)
				.filter(m -> m.getPw().equals(pw))
				.orElse(null);
	}
	/**
	 *  아이디찾기 메서드입니다.
	 * @param memberDTO 회원 객체입니다.
	 * @return  찾은 회원을 리턴합니다.
	 */
	public MemberDTO findId(@Valid MemberDTO memberDTO) {
		
		
		return dao.findId(memberDTO);
	}
	/**
	 *  seq 로 회원찾기 메서드입니다.
	 * @param user_seq  회원 seq 입니다.
	 * @return 찾은 회원을리턴 합니다.
	 */
	public MemberDTO findMember(String user_seq) {
		return dao.findMember(user_seq);
	}
	/**
	 *  비밀번호변경 메서드입니다.
	 * @param pw   회원 비밀번호입니다.
	 * @param user_seq  회원 번호입니다.
	 * @return  수정된 결과를 반환합니다.
	 */
	public int changePw(String pw, String user_seq) {
		return dao.changePw(pw,user_seq);
	}
	/**
	 * 비밀번호 암호화 메서드입니다.
	 * @param memberDTO 회원 객체입니다.
	 */
	public void passwordEncoder(MemberDTO memberDTO) {
		String encodedPassword = passwordEncoder.encode(memberDTO.getPw());
	    memberDTO.setPw(encodedPassword);
	    dao.encoderPw(memberDTO);
	}
	/**
	 *  회원번호 찾기 메서드입니다. 
	 * @param memberDTO 회원객체 입니다.
	 * @return  찾은 번호를 리턴합니다.
	 */
	public String findSeq(@Valid MemberDTO memberDTO) {
		return dao.findSeq(memberDTO);
	}
	/**
	 *  회원 전체 리스트 조회 메서드입니다.
	 * @return  회원 전체 리스트를 반환합니다.
	 */
	public List<MemberDTO> list() {
		return dao.list();
	}

	
	
	

}
