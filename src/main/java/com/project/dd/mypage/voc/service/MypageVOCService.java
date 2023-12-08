package com.project.dd.mypage.voc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.mypage.voc.domain.VOCDTO;
import com.project.dd.mypage.voc.repository.MypageVOCDAO;

@Service
public class MypageVOCService {
	
	@Autowired
	private MypageVOCDAO dao;

	public List<VOCDTO> list() {
		
		return dao.list();
	}

}
