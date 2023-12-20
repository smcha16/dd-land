package com.project.dd.login.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.login.mapper.LoginMapper;
import com.project.dd.register.domain.MemberDTO;
/**
 * 로그인  DAO 클래스입니다. 
 * @author 김형우
 *
 */
@Primary
@Repository
public class LoginDAOImpl implements LoginDAO {

	private final LoginMapper mapper;

	private static final Map<String, MemberDTO> store = new ConcurrentHashMap<>();
/**
 *  회원 정보를 데이터 베이스에서 불러와 map에 저장힙니다.
 * @param mapper  Mapper 객체입니다.
 */
	@Autowired
	public LoginDAOImpl(LoginMapper mapper) {
		this.mapper = mapper;

		List<MemberDTO> list = mapper.list();

		for (int i = 0; i < list.size(); i++) {

			store.put(list.get(i).getEmail(), list.get(i));
		}
	}
	/**
	 *  회원 정보를 맵에 저장합니다.
	 * @param memberDTO
	 * @return
	 */
	public MemberDTO save(MemberDTO memberDTO) {
		store.put(memberDTO.getUser_seq(), memberDTO);
		return memberDTO;
	}
	/**
	 * 아이디찾기메서드입니다.
	 * @param loginId 로그인한 객체정보입니다.
	 * @return   회원 객체를 리턴합니다.
	 */
	public Optional<MemberDTO> findByLoginId(String loginId) {
		
		return findAll().stream().filter(m -> m.getEmail().equals(loginId)).findFirst();

	}
	/**
	 *  회원 전체 찾기 메서드입니다.
	 * @return  전체 리스트를 반환합니다.
	 */
	public List<MemberDTO> findAll() {
	
		return new ArrayList<>(store.values());
	}
	/** 
	 *   아이디찾기 메서드입니다.
	 * @param memberDTO 회원 객체입니다.
	 * @return 찾은 회원을 리턴합니다.
	 */
	public MemberDTO findId(@Valid MemberDTO memberDTO) {
		
		return mapper.findId(memberDTO);
	}
/** 회원 찾기 메서드입니다.
 * 
 * @param user_seq  회원 시퀀스번호 입니다.
 * @return 찾은 회원을 반환합니다.
 */
	public MemberDTO findMember(String user_seq) {
		return mapper.findMember(user_seq);
	}
	/**
	 *  비밀번호 변경 메서드입ㄴ디ㅏ.
	 * @param pw  회원 비밀번호입니다.
	 * @param user_seq 회원 시퀀스 번호입니다.
	 * @return  수정 결과를 반환합니다.
	 */
	public int changePw(String pw, String user_seq) {
		return mapper.changePw(pw,user_seq);
	}
	/**
	 *   비밀번호 인코딩 메서드입니다.
	 * @param memberDTO  회원객체입니다.
	 */
	public void encoderPw(MemberDTO memberDTO) {
			mapper.encoderPw(memberDTO);
	}
	 /**
	  *   회원번호 찾기 메서드입니다.
	  * @param memberDTO  회원 객체입니다.
	  * @return  회원 번호를 반환합니다.
	  */
	public String findSeq(@Valid MemberDTO memberDTO) {
		return mapper.findSeq(memberDTO);
	}
	/**
	 *  회원 리스트찾기 메서드입니다.
	 * @return 회원 리스트를 반환합니다.
	 */
	public List<MemberDTO> list() {
		return mapper.list();
	}

	

}
