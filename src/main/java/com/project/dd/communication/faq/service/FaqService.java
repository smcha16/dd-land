package com.project.dd.communication.faq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.communication.faq.domain.FaqDTO;
import com.project.dd.communication.faq.repository.FaqDAO;

/**
 * FAQ 서비스 클래스입니다.
 * 
 * @author sumin
 */
@Service
public class FaqService {
	
	@Autowired
	private FaqDAO dao;
	
	/**
	 * 페이징 처리를 위한 맵을 생성하는 메서드입니다.
	 *
	 * @param type FAQ 유형
	 * @param searchStatus 검색 상태
	 * @param word 검색어
	 * @param page 페이지 번호
	 * @return 페이징 정보를 담은 Map 객체
	 */
	public Map<String, String> paging(String type, String searchStatus, String word, int page) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("type", type);
		map.put("searchStatus", searchStatus);
		map.put("word", word);

		int pageSize = 10;
		
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = dao.getTotalCount(map);
		int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		return map;
		
	}

	/**
	 * FAQ 목록을 가져오는 메서드입니다.
	 *
	 * @param map 페이징 정보를 포함한 Map 객체
	 * @return FAQ 목록
	 */
	public List<FaqDTO> getFaqList(Map<String, String> map) {

		List<FaqDTO> list = dao.getFaqList(map);
		
		for (FaqDTO dto : list) {
			
			String answer = dto.getAnswer();

			answer = answer.replace("<", "&lt;");
			answer = answer.replace(">", "&gt;");
			answer = answer.replace("\\r\\n", "<br>");

			dto.setAnswer(answer);
			
		}
		
		return list;
		
	}

	/**
	 * FAQ를 추가하는 메서드입니다.
	 *
	 * @param dto 추가할 FAQ의 DTO 객체
	 * @return 추가 결과 (1: 성공, 0: 실패)
	 */
	public int addFaq(FaqDTO dto) {
		
		return dao.addFaq(dto);
		
	}

	/**
	 * 특정 FAQ의 상세 내용을 가져오는 메서드입니다.
	 *
	 * @param seq 조회할 FAQ의 일련번호
	 * @return 조회된 FAQ의 DTO 객체
	 */
	public FaqDTO getFaq(String seq) {
		
		FaqDTO dto = dao.getFaq(seq);
		
		String answer = dto.getAnswer();

		answer = answer.replace("<", "&lt;");
		answer = answer.replace(">", "&gt;");
		answer = answer.replace("\\r\\n", "<br>");

		dto.setAnswer(answer);
		
		return dto;
		
	}

	/**
	 * FAQ를 수정하는 메서드입니다.
	 *
	 * @param dto 수정할 FAQ의 DTO 객체
	 * @return 수정 결과 (1: 성공, 0: 실패)
	 */
	public int editFaq(FaqDTO dto) {
		
		return dao.editFaq(dto);
		
	}

	/**
	 * 여러 개의 FAQ를 삭제하는 메서드입니다.
	 *
	 * @param seqList 삭제할 FAQ의 일련번호 배열
	 */
	public void deleteFaq(String[] seqList) {

		for (String seq : seqList) {
			
			dao.deleteFaq(seq);
			
		}
		
	}

}
