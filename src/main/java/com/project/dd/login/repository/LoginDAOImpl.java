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

@Primary
@Repository
public class LoginDAOImpl implements LoginDAO {

	private final LoginMapper mapper;

	private static final Map<String, MemberDTO> store = new ConcurrentHashMap<>();

	@Autowired
	public LoginDAOImpl(LoginMapper mapper) {
		this.mapper = mapper;

		List<MemberDTO> list = mapper.list();

		for (int i = 0; i < list.size(); i++) {

			store.put(list.get(i).getEmail(), list.get(i));
		}
	}

	public MemberDTO save(MemberDTO memberDTO) {
		store.put(memberDTO.getUser_seq(), memberDTO);
		return memberDTO;
	}

	public Optional<MemberDTO> findByLoginId(String loginId) {
		
		return findAll().stream().filter(m -> m.getEmail().equals(loginId)).findFirst();

	}
	public List<MemberDTO> findAll() {
	
		return new ArrayList<>(store.values());
	}

	public MemberDTO findId(@Valid MemberDTO memberDTO) {
		
		return mapper.findId(memberDTO);
	}

	public MemberDTO findMember(String user_seq) {
		return mapper.findMember(user_seq);
	}

	public int changePw(String pw, String user_seq) {
		return mapper.changePw(pw,user_seq);
	}

	public void encoderPw(MemberDTO memberDTO) {
			mapper.encoderPw(memberDTO);
	}

	public String findSeq(@Valid MemberDTO memberDTO) {
		return mapper.findSeq(memberDTO);
	}

	public List<MemberDTO> list() {
		return mapper.list();
	}

	

}
