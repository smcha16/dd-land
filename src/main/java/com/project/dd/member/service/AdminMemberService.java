package com.project.dd.member.service;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.dd.member.repository.AdminMemberDAO;
import com.project.dd.register.domain.MemberDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminMemberService {
	
	private final AdminMemberDAO dao;
	public List<MemberDTO> search(String query) {
		
		return dao.search(query);
	}
	
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

	public List<MemberDTO> getMemberList(Map<String, String> map) {
		return dao.getMemberList(map);
	}

}
