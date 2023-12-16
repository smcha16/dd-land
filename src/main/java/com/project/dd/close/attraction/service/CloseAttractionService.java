package com.project.dd.close.attraction.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.close.attraction.domain.CloseAttractionDTO;
import com.project.dd.close.attraction.repository.CloseAttractionDAO;

@Service
public class CloseAttractionService {
	
	@Autowired
	private CloseAttractionDAO closeAttrDao;

	public List<CloseAttractionDTO> list(Map<String, String> map) {
		
		return closeAttrDao.list(map);
	}

	public Map<String, String> paging(int page) {  //페이징 메서드
		int pageSize = 9;  //나타났으면 하는 개수
		
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;
		
		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = closeAttrDao.getTotalCount();
		int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		return map;
	}

	public void del(String[] closeAttraction_seq) {
		for(String seq : closeAttraction_seq) {
			closeAttrDao.del(seq);
		}
		
	}

	public List<AttractionDTO> attlist() {
		
		return closeAttrDao.attlist();
	}

	public int addCloseAtt(CloseAttractionDTO dto) {
		
		return closeAttrDao.addCloseAtt(dto);
	}

	public CloseAttractionDTO getOne(String seq) {
		
		CloseAttractionDTO dto = closeAttrDao.getOne(seq);
		
		//운휴 시작일, 종료일 가공해서 가져오기
		String start_date=dto.getStart_date();
		String end_date=dto.getEnd_date();
		
		start_date=start_date.substring(0, 10);
		end_date=end_date.substring(0, 10);
		
		dto.setStart_date(start_date);
		dto.setEnd_date(end_date);
		
		
		return dto;
	}

	public int editClose(CloseAttractionDTO dto) {
		
		return closeAttrDao.editClose(dto);
	}

}
