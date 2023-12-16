package com.project.dd.activity.theater.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.activity.theater.domain.TheaterDTO;
import com.project.dd.activity.theater.repository.TheaterDAO;

@Service
public class TheaterService {

	@Autowired
	TheaterDAO dao;

	public Map<String, String> paging(int page) {

		int pageSize = 10; //나타났으면 하는 개수
		
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = dao.getTotalCount();
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		return map;
		
	}

	public List<TheaterDTO> getTheaterList(Map<String, String> map) {
		return dao.getTheaterList(map);
	}

	public int addTheater(TheaterDTO dto) {
		return dao.addTheater(dto);
	}

	public TheaterDTO getTheater(String seq) {
		return dao.getTheater(seq);
	}
}
