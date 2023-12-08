package com.project.dd.mypage.attraction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.mypage.attraction.domain.AttractionDTO;
import com.project.dd.mypage.attraction.repositorty.MypageAttractionDAO;

@Service
public class MypageAttractionService {
	
	@Autowired
	private MypageAttractionDAO dao;

	public List<AttractionDTO> list() {
		
		return dao.list();
	}

}
