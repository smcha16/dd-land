package com.project.dd.close.theater.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.close.attraction.domain.CloseAttractionDTO;
import com.project.dd.close.theater.domain.CloseTheaterDTO;
import com.project.dd.close.theater.repository.CloseTheaterDAO;

/**
 * 영화관 운휴 DB에 접근하여 실행된 레코드의 수를 반환하는 Service 클래스
 * @author leeje
 *
 */

@Service
public class CloseTheaterService {

	@Autowired
	private CloseTheaterDAO dao;
	
	/**
	 * 페이지 번호를 출력하기 위해 DB에 접근하여 운휴 중인 영화관의 개수를 조회하는 메서드
	 * @param page 페이지 번호
	 * @return 위의 정보가 담긴 map 객체
	 */
	public Map<String, String> paging(int page) {
		int pageSize = 9;  //나타났으면 하는 개수
		
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

	/**
	 * 운휴 중인 영화관 목록을 가져오는 메서드
	 * @param map 객체
	 * @return 영화관 운휴 DTO가 담긴 list
	 */
	public List<CloseTheaterDTO> list(Map<String, String> map) {
		//목록 리스트 가져오기
		return dao.list(map);
	}

	/**
	 * 영화관 목록을 조회하는 메서드
	 * @return 영화관 운휴 DTO가 담긴 list
	 */
	public List<CloseTheaterDTO> theaterlist() {
		
		return dao.theaterlist();
	}

	/**
	 * 영화관의 운휴 정보를 추가하는 메서드
	 * @param dto 추가할 영화관의 운휴 정보를 담은 CloseTheaterDTO 객체
	 * @return 실행된 레코드의 수
	 */
	public int addCloseTheater(CloseTheaterDTO dto) {
		
		return dao.addCloseTheater(dto);
	}

	/**
	 * 특정 운휴 정보를 조회하는 메서드
	 * @param seq 조회할 운휴 정보의 시퀀스
	 * @return 조회된 운휴 정보를 담은 CloseTheaterDTO 객체
	 */
	public CloseTheaterDTO getOne(String seq) {
		
		CloseTheaterDTO dto = dao.getOne(seq);
		
		//운휴 시작일, 종료일 가공
		String start_date=dto.getStart_date();
		String end_date=dto.getEnd_date();
		
		start_date=start_date.substring(0, 10);
		end_date=end_date.substring(0, 10);
		
		dto.setStart_date(start_date);
		dto.setEnd_date(end_date);
		
		return dto;
	}

	/**
	 * 영화관의 운휴 정보를 수정하는 메서드
	 * @param dto 수정할 영화관의 운휴 정보를 담은 CloseTheaterDTO 객체
	 * @return 실행된 레코드의 수
	 */
	public int editClose(CloseTheaterDTO dto) {
		
		return dao.editClose(dto);
	}

	/**
	 * 영화관의 운휴 정보를 삭제하는 메서드
	 * @param closeTheater_seq 삭제할 영화관의 운휴 정보 시퀀스 배열
	 */
	public void del(String[] closeTheater_seq) {
		for(String seq : closeTheater_seq) {
			dao.del(seq);
		}
	}

}
