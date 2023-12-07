package com.project.dd.communication.lost.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.communication.lost.domain.LostPropertyDTO;
import com.project.dd.communication.lost.repository.LostPropertyDAO;

@Service
public class LostPropertyService {
	
	@Autowired
	private LostPropertyDAO dao;

	public Map<String, String> paging(int page) {

		int pageSize = 10;
		
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;
		
		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = dao.getTotalCount();
		int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		return map;
		
	}

	public List<LostPropertyDTO> getLostPropertyList(Map<String, String> map) {
		
		List<LostPropertyDTO> list = dao.getLostPropertyList(map);

		for (LostPropertyDTO dto : list) {
			
			String lostDate = dto.getLost_property_date();
			
			lostDate = lostDate.substring(0, 10);
			
			dto.setLost_property_date(lostDate);
			
		}
		
		return list;
		
	}

}
