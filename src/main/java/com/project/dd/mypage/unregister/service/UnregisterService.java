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

	/**
	 * 입력된 이메일로 등록된 사용자가 있는지 확인하는 메서드입니다.
	 *
	 * @param email    확인하려는 사용자의 이메일
	 * @return         사용자가 있는 경우 true, 그렇지 않으면 false를 반환합니다.
	 */
	public boolean isValidUser(String email) {
		
	    int count = dao.isValidUser(email);
	    
	    // 반환된 사용자 수(count)가 1 이상이면 true를 반환하고, 그렇지 않으면 false를 반환합니다.
	    return count > 0;
	}

	/**
	 * 입력된 이메일로 등록된 사용자를 삭제하는 메서드입니다.
	 *
	 * @param email    삭제하려는 사용자의 이메일
	 * @return         사용자 삭제 작업의 성공 여부를 반환합니다.
	 */
	public boolean deleteMember(String email) {
		
		return dao.deleteMember(email);
	}

}
