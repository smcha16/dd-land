package com.project.dd.guide.convenient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.guide.convenient.domain.ConvenientDTO;
import com.project.dd.guide.convenient.repository.ConvenientDAO;

@Service
public class ConvenientService {

	@Autowired
	private ConvenientDAO convenientDao;   //ConvenientDAO 객체 생성

	public List<ConvenientDTO> list() {
		return convenientDao.list();
	}

	public ConvenientDTO one(String seq) {
		return convenientDao.one(seq);
	}
	
	 
	
	
	
	
	
		
}
