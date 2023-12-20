package com.project.dd.mypage.buy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.mypage.buy.domain.BuyDTO;
import com.project.dd.mypage.buy.repository.MypageBuyDAO;

@Service
public class MypageBuyService {
	
	@Autowired
	private MypageBuyDAO dao;

	/**
	 * 구매 목록을 조회하는 메서드입니다.
	 *
	 * @param map    페이지 번호와 이메일을 포함한 맵
	 * @return       구매 목록
	 */
	public List<BuyDTO> list(Map<String, String> map) {
		
		return dao.list(map);
	}
	
	/**
	 * 이전 구매 목록을 조회하는 메서드입니다.
	 *
	 * @param map    페이지 번호와 이메일을 포함한 맵
	 * @return       페이징된 구매 목록
	 */
	public List<BuyDTO> plist(Map<String, String> map) {
		
		return dao.plist(map);
	}

	/**
	 * 페이지 정보를 생성하여 반환하는 메서드입니다.
	 *
	 * @param page   현재 페이지 번호
	 * @param email  사용자 이메일
	 * @return       페이지 정보를 담은 맵
	 */
	public Map<String, String> paging(int page, String email) {
		
		  int pageSize = 12;  //나타났으면 하는 개수
	      
	      int startIndex = (page - 1) * pageSize + 1;
	      int endIndex = startIndex + pageSize - 1;
	      
	      Map<String, String> map = new HashMap<String, String>();

	      map.put("startIndex", String.format("%d", startIndex));
	      map.put("endIndex", String.format("%d", endIndex));
	      
	      int totalPosts = dao.getTotalCount(email);
	      int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
	      
	      map.put("totalPosts", String.format("%d", totalPosts));
	      map.put("totalPages", String.format("%d", totalPages));
	      
	      return map;
	}
	
	/**
	 * 페이징된 페이지 정보를 생성하여 반환하는 메서드입니다.
	 *
	 * @param page   현재 페이지 번호
	 * @param email  사용자 이메일
	 * @return       페이징된 페이지 정보를 담은 맵
	 */
	public Map<String, String> pPaging(int page, String email) {
		
			int pageSize = 12; // 나타났으면 하는 개수
	
			int startIndex = (page - 1) * pageSize + 1;
			int endIndex = startIndex + pageSize - 1;
	
			Map<String, String> map = new HashMap<String, String>();
	
			map.put("startIndex", String.format("%d", startIndex));
			map.put("endIndex", String.format("%d", endIndex));
	
			int totalPosts = dao.pGetTotalCount(email);
			int totalPages = (int) Math.ceil((double) totalPosts / pageSize);
	
			map.put("totalPosts", String.format("%d", totalPosts));
			map.put("totalPages", String.format("%d", totalPages));
	
			return map;
	}

	/**
	 * 선택된 항목을 삭제하는 메서드입니다.
	 *
	 * @param selectedItem  삭제할 항목 배열
	 * @return              삭제된 항목의 총 개수
	 */
	public int delete(String[] selectedItem) {
		
		int totalDeleted = 0;

		for (String seq : selectedItem) {

			int deleted = dao.delete(seq);
			totalDeleted += deleted;

		}

		return totalDeleted;
	}

	

}
