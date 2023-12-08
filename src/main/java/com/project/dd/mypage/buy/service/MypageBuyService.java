package com.project.dd.mypage.buy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.mypage.buy.domain.BuyDTO;
import com.project.dd.mypage.buy.repository.MypageBuyDAO;

@Service
public class MypageBuyService {
	
	@Autowired
	private MypageBuyDAO dao;

	public List<BuyDTO> list() {
		
		return dao.list();
	}

}
