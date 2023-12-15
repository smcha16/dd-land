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
		
		//addTheater
		//1. tblTheater INSERT
		//2. tblTheaterLocation INSERT
		
		//1.
		int result = dao.addTheater(dto);
		
		//방금 등록한 Theater_seq 가져오기
		String seq = dao.getTheaterSeq();
		dto.setTheater_seq(seq);
		
		//2.
		result = dao.addTheaterLocation(dto);
		
		return result;
	}

	public TheaterDTO getTheater(String seq) {
		return dao.getTheater(seq);
	}

	public int checkLocationDuplication(TheaterDTO dto) {
		return dao.checkLocationDuplication(dto);
	}

	public int checkNameDuplication(TheaterDTO dto) {
		return dao.checkNameDuplication(dto);
	}

	public int editTheater(TheaterDTO dto) {
		
		int result = dao.editTheater(dto);
			
		result = dao.editTheaterLocation(dto);
		
		return result;
	}

	public int delTheater(String[] theater_seq) {
		
		int result = 0;
		
		//삭제할 영화관의 seq 뽑아내기
		for (String seq : theater_seq) {
			
			//tblTheaterLocaion의 레코드가 존재 O > tblTheaterLocation DELETE
			//tblTheaterLocaion의 레코드가 존재 X > tblTheater UPDATE
			int locationCount = dao.countTheaterLocation(seq);
			
			if (locationCount > 0) {
				dao.delTheaterLocation(seq);
			}
			
			result += dao.delTheater(seq);
			
		}
		
		return result;
	}
}
