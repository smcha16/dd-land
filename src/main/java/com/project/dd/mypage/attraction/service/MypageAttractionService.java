package com.project.dd.mypage.attraction.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.mypage.attraction.domain.AttractionDTO;
import com.project.dd.mypage.attraction.repositorty.MypageAttractionDAO;

@Service
public class MypageAttractionService {

	@Autowired
	private MypageAttractionDAO dao;

	public List<AttractionDTO> list(Map<String, String> map) {

		return dao.list(map);
	}

	public int delete(String[] selectedAttraction) {

		int totalDeleted = 0;

		for (String seq : selectedAttraction) {

			int deleted = dao.delete(seq);
			totalDeleted += deleted;

		}

		return totalDeleted;
	}

	public Map<String, String> paging(int page) {

		int pageSize = 9; // 나타났으면 하는 개수

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

	public List<AttractionDTO> plist(Map<String, String> map) {

		return dao.plist(map);
	}

}
