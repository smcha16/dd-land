package com.project.dd.mypage.inquiry.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.mypage.inquiry.domain.InquiryDTO;
import com.project.dd.mypage.inquiry.repository.MypageInquiryDAO;

@Service
public class MypageInquiryService {
	
	@Autowired
	private MypageInquiryDAO dao;

	/**
	 * 문의 목록을 가져오는 메서드입니다.
	 *
	 * @param map    페이징 처리를 위한 맵
	 * @return       문의 목록
	 */
	public List<InquiryDTO> list(Map<String, String> map) {
		
		return dao.list(map);
	}

	/**
	 * 문의 목록의 페이징을 처리하는 메서드입니다.
	 *
	 * @param page    현재 페이지 번호
	 * @return        페이징 처리를 위한 맵
	 */
	public Map<String, String> paging(int page) {
		
		  int pageSize = 12;  //나타났으면 하는 개수
	      
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
	 * 선택된 문의를 삭제하는 메서드입니다.
	 *
	 * @param selectedInquiry    삭제할 문의 배열
	 * @return                   삭제된 총 개수
	 */
	public int delete(String[] selectedInquiry) {
		
		int totalDeleted = 0;

		for (String seq : selectedInquiry) {

			int deleted = dao.delete(seq);
			totalDeleted += deleted;

		}

		return totalDeleted;
		
	}

}
