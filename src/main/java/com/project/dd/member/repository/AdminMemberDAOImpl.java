package com.project.dd.member.repository;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.member.mapper.AdminMemberMapper;
import com.project.dd.register.domain.MemberDTO;

import lombok.RequiredArgsConstructor;

@Primary
@Repository
@RequiredArgsConstructor
public class AdminMemberDAOImpl implements AdminMemberDAO{
	
	private final AdminMemberMapper mapper;

	@Override
	public List<MemberDTO> search(String query) {
		return mapper.search(query);
	}

	
	@Override
	public int getTotalCount(String type) {
		return mapper.getTotalCount(type);
	}


	@Override
	public List<MemberDTO> getMemberList(Map<String, String> map) {
		return mapper.getMemberList(map);
	}


	@Override
	public int del(String user_seq) {
		return mapper.del(user_seq);
	}


	@Override
	public int edit(MemberDTO memberDTO) {
		return mapper.edit(memberDTO);
	}


	
	
	
	
}
