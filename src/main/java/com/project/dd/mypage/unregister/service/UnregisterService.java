package com.project.dd.mypage.unregister.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.mypage.unregister.repository.UnregisterDAO;

@Service
public class UnregisterService {
	
	@Autowired
	private UnregisterDAO dao;

	public boolean isValidUser(String email) {
		
	    int count = dao.isValidUser(email);
	    
	    // 반환된 사용자 수(count)가 1 이상이면 true를 반환하고, 그렇지 않으면 false를 반환합니다.
	    return count > 0;
	}

	public boolean deleteMember(String email) {
		
		return dao.deleteMember(email);
	}

}
