package com.project.dd.test.mbti.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.mbti.domain.MBTIDTO;
import com.project.dd.test.mbti.repository.MBTIDAO;

@Service
@Primary
public class MBTIServiceImpl implements MBTIService {

    @Autowired
    private MBTIDAO dao;

    @Override
    public int getTotalCount() {
    	return dao.getTotalCount();
    }
    
	// 페이징 메서드
	public Map<String, String> paging(int page) {
		int pageSize = 10; // 조회할 글 개수

		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = getTotalCount();
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}
	
    @Override
    public List<MBTIDTO> getAllMBTI(Map<String, String> map) {
        return dao.getAllMBTI(map);
    }

    @Override
	public MBTIDTO getMBTI(String seq) {
		return dao.getMBTI(seq);
	}
    
}
