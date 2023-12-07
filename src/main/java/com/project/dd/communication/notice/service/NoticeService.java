package com.project.dd.communication.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.communication.notice.domain.NoticeDTO;
import com.project.dd.communication.notice.repository.NoticeDAO;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeDAO dao;

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

	public List<NoticeDTO> getNoticeList(Map<String, String> map) {
		
		List<NoticeDTO> list = dao.getNoticeList(map);

		for (NoticeDTO dto : list) {
			
			String regdate = dto.getRegdate();
			
			regdate = regdate.substring(0, 10);
			
			dto.setRegdate(regdate);
			
		}
		
		return list;
		
	}

	public NoticeDTO getNotice(String seq) {
		
		NoticeDTO dto = dao.getNotice(seq);

		String regdate = dto.getRegdate();
		
		regdate = regdate.substring(0, 10);
		
		dto.setRegdate(regdate);
		
		return dto;
		
	}

}
