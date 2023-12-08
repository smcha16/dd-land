package com.project.dd.communication.faq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.communication.faq.domain.FaqDTO;
import com.project.dd.communication.faq.repository.FaqDAO;

@Service
public class FaqService {
	
	@Autowired
	private FaqDAO dao;

	public Map<String, String> paging(String type, int page) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("type", type);

		int pageSize = 10;
		
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = dao.getTotalCount(type);
		int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		return map;
		
	}

	public List<FaqDTO> getFaqList(Map<String, String> map) {

		List<FaqDTO> list = dao.getFaqList(map);
		
		return list;
		
	}

}
