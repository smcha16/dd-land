package com.project.dd.activity.theater.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.activity.theater.domain.TheaterDTO;
import com.project.dd.activity.theater.repository.TheaterDAO;

/**
 * 
 * 영화관 DB에 접근하여 실행된 레코드의 수를 반환하는 Service 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Service
public class TheaterService {

	@Autowired
	TheaterDAO dao;

	/**
	 * 
	 * 페이지 번호를 출력하기 위해 DB에 접근하여 영화관 개수를 조회하는 메서드입니다.
	 * 
	 * @param page 페이지 번호
	 * @return 위의 정보가 담긴 map 객체
	 */
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

	/**
	 * 
	 * 영화관 목록을 가져오는 메서드입니다.
	 * 
	 * @param map 페이징을 위한 map 객체
	 * @return 영화관 dto list
	 */
	public List<TheaterDTO> getTheaterList(Map<String, String> map) {
		return dao.getTheaterList(map);
	}

	/**
	 * 
	 * 영화관 추가를 위해 영화관 DB에 접근하는 메서드입니다.
	 * 
	 * @param dto 영화관 dto 객체
	 * @return 테이블에 추가된 행의 개수
	 */
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

	/**
	 * 
	 * 특정 영화관의 정보를 가져오는 메서드입니다.
	 * 
	 * @param seq 영화관 번호
	 * @return 영화관 dto 객체
	 */
	public TheaterDTO getTheater(String seq) {
		return dao.getTheater(seq);
	}

	/**
	 * 
	 * 영화관 위치정보의 중복 검사를 진행하는 메서드
	 * 
	 * @param dto 영화관 dto 객체
	 * @return 중복된 레코드의 개수
	 */
	public int checkLocationDuplication(TheaterDTO dto) {
		return dao.checkLocationDuplication(dto);
	}

	/**
	 * 
	 * 영화관명의 중복 검사를 위한 메서드
	 * 
	 * @param dto 영화관 dto 객체
	 * @return 중복된 레코드의 개수
	 */
	public int checkNameDuplication(TheaterDTO dto) {
		return dao.checkNameDuplication(dto);
	}

	/**
	 * 
	 * 영화관 수정을 위해 영화관 테이블에 접근하는 메서드입니다.
	 * 
	 * @param dto 영화관 dto 객체
	 * @return 수정된 행의 개수
	 */
	public int editTheater(TheaterDTO dto) {
		
		int result = dao.editTheater(dto);
			
		result = dao.editTheaterLocation(dto);
		
		return result;
	}

	/**
	 * 
	 * 영화관을 삭제하기 위해 DB에 접근하는 메서드입니다.
	 * 
	 * @param theater_seq 영화관 번호
	 * @return 삭제된 행의 개수
	 */
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
