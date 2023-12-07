package com.project.dd.activity.attraction.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.repository.AttractionDAO;

@Service
public class AttractionService {

	@Autowired
	AttractionDAO dao;

	public List<AttractionDTO> getAttractionList(Map<String, String> map) {
		return dao.getAttractionList(map);
	}

	public AttractionDTO getAttraction(String seq) {
		return dao.getAttraction(seq);
	}

	public List<AttractionImgDTO> getAttractionImgList(String seq) {
		return dao.getAttractionImgList(seq);
	}

	public int getAttractionCloseCount(List<AttractionDTO> list) {

		int closeCount = 0;

		//금일 운휴 어트랙션 개수 세기
		for (AttractionDTO dto : list) {

			if (dto.getClose().equalsIgnoreCase("y")) { //운휴
				closeCount++;
			}
		}
		
		return closeCount;
	}

	public Map<String, String> paging(int page) {
		
		int pageSize = 9;  //나타났으면 하는 개수
	      
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
}
