package com.project.dd.guide.convenient.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.guide.convenient.domain.ConvenientDTO;
import com.project.dd.guide.convenient.repository.ConvenientDAO;

@Service
public class ConvenientService {

	@Autowired
	private ConvenientDAO convenientDao;   //ConvenientDAO 객체 생성

	public List<ConvenientDTO> list(Map<String, String> map) {
		return convenientDao.list(map);
	}

	public ConvenientDTO one(String seq) {
		return convenientDao.one(seq);
	}

	public Map<String, String> paging(int page) {  //페이징 메서드
		int pageSize = 9;  //나타났으면 하는 개수
		
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;
		
		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = convenientDao.getTotalCount();
		int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		return map;
	}
	
	
		
}
