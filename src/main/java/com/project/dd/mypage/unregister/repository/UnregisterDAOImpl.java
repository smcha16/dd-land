package com.project.dd.mypage.unregister.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.mypage.unregister.mapper.UnregisterMapper;

@Primary
@Repository
public class UnregisterDAOImpl implements UnregisterDAO{
	
	@Autowired
	private UnregisterMapper mapper;
	
	@Override
	public boolean deleteMember(String email) {
		
		return mapper.deleteMember(email);
	}
	
	@Override
	public int isValidUser(String email) {
		
		return mapper.isValidUser(email);
	}

}
