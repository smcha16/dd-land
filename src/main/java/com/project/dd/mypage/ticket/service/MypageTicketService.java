package com.project.dd.mypage.ticket.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.mypage.ticket.domain.TicketDTO;
import com.project.dd.mypage.ticket.repository.MypageTicketDAO;

@Service
public class MypageTicketService {

	@Autowired
	private MypageTicketDAO dao;
	
	/**
	 * 티켓 예매 목록을 조회하는 메서드입니다.
	 *
	 * @param map    페이지 정보를 포함하는 Map 객체
	 * @return       티켓 예매 목록
	 */
	public List<TicketDTO> list(Map<String, String> map) {
		
		return dao.list(map);
	}

	/**
	 * 선택한 예매를 취소하는 메서드입니다.
	 *
	 * @param selectedTickets    삭제할 티켓의 ID 목록
	 * @return                   삭제된 티켓의 수
	 */
	public int delete(String[] selectedTickets) {
		
		int totalDeleted = 0;

		for (String seq : selectedTickets) {

			int deleted = dao.delete(seq);
			totalDeleted += deleted;

		}

		return totalDeleted;
	}

	/**
	 * 페이징을 위한 메서드입니다.
	 *
	 * @param page    현재 페이지 번호
	 * @param email   사용자 이메일
	 * @return        페이징 처리를 위한 정보를 담은 Map 객체
	 */
	public Map<String, String> paging(int page, String email) {  //페이징 메서드
		
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
	 * 이전 예매 목록을 조회하는 메서드입니다.
	 *
	 * @param map    페이지 정보를 포함하는 Map 객체
	 * @return       페이징 처리된 예매 목록
	 */
	public List<TicketDTO> plist(Map<String, String> map) {
		
		return dao.plist(map);
	}

	/**
	 * 페이징을 위한 메서드입니다.
	 *
	 * @param page    현재 페이지 번호
	 * @param email   사용자 이메일
	 * @return        페이징 처리를 위한 정보를 담은 Map 객체
	 */
	public Map<String, String> pPaging(int page, String email) {
		
		  int pageSize = 12;  //나타났으면 하는 개수
	      
	      int startIndex = (page - 1) * pageSize + 1;
	      int endIndex = startIndex + pageSize - 1;
	      
	      Map<String, String> map = new HashMap<String, String>();

	      map.put("startIndex", String.format("%d", startIndex));
	      map.put("endIndex", String.format("%d", endIndex));
	      
	      int totalPosts = dao.pGetTotalCount(email);
	      int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
	      
	      map.put("totalPosts", String.format("%d", totalPosts));
	      map.put("totalPages", String.format("%d", totalPages));
	      
	      return map;
	}

	
	
}
