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

/**
 * 어트랙션 운휴 DB에 접근하여 실행된 레코드의 수를 반환하는 Service 클래스
 * @author leeje
 *
 */

@Service
public class CloseAttractionService {
	
	@Autowired
	private CloseAttractionDAO closeAttrDao;

	/**
	 * 운휴중인 어트랙션의 list를 가져오는 메서드
	 * @param map 객체
	 * @return 어트랙션 운휴 dto가 담긴 list
	 */
	public List<CloseAttractionDTO> list(Map<String, String> map) {
		
		return closeAttrDao.list(map);
	}

	/**
	 * 페이지 번호를 출력하기 위해 DB에 접근하여 운휴중인 어트랙션의 개수를 조회하는 메서드
	 * @param page 페이지 번호
	 * @return 위의 정보가 담긴 map 객체
	 */
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

	/**
	 * 운휴를 삭제하기 위해 DB에 접근하여 어트랙션 운휴를 삭제하는 메서드
	 * @param closeAttraction_seq 삭제하기 위한 어트랙션의 운휴 seq
	 */
	public void del(String[] closeAttraction_seq) {
		for(String seq : closeAttraction_seq) {
			closeAttrDao.del(seq);
		}
		
	}

	/**
	 * 추가할 어트랙션 목록을 조회하는 메서드
	 * @return 어트랙션 정보를 담은 AttractionDTO 객체의 리스트
	 */
	public List<AttractionDTO> attlist() {
		
		return closeAttrDao.attlist();
	}

	/**
	 * 어트랙션의 운휴 정보를 추가하는 메서드
	 * @param dto 추가할 어트랙션의 운휴 정보를 담은 CloseAttractionDTO 객체
	 * @return 실행된 레코드의 수
	 */
	public int addCloseAtt(CloseAttractionDTO dto) {
		
		return closeAttrDao.addCloseAtt(dto);
	}

	/**
	 * 특정 운휴 정보를 조회하는 메서드
	 * @param seq 조회할 운휴 정보의 시퀀스
	 * @return 조회된 운휴 정보를 담은 CloseAttractionDTO 객체
	 */
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

	/**
	 * 어트랙션의 운휴 정보를 수정하는 메서드
	 * @param dto 수정할 어트랙션의 운휴 정보를 담은 CloseAttractionDTO 객체
	 * @return 실행된 레코드의 수
	 */
	public int editClose(CloseAttractionDTO dto) {
		
		return closeAttrDao.editClose(dto);
	}

}
