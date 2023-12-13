package com.project.dd.mypage.modify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.mypage.modify.domain.ModifyDTO;
import com.project.dd.mypage.modify.repository.MypageModifyDAO;

@Service
public class MypageModifyService {
	
	@Autowired
	private MypageModifyDAO dao;

	public List<ModifyDTO> list(String email) {
		
		return dao.list(email);
	}

	public int edit(ModifyDTO dto) {
		
		return dao.edit(dto);
	}

	

}
