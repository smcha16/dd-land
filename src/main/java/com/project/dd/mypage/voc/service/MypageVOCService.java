package com.project.dd.mypage.voc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.mypage.voc.domain.VOCDTO;
import com.project.dd.mypage.voc.repository.MypageVOCDAO;

@Service
public class MypageVOCService {
	
	@Autowired
	private MypageVOCDAO dao;

	/**
	 * 칭찬/불편/건의 리스트를 조회하는 메서드입니다.
	 *
	 * @param map           조회할 리스트의 페이지 관련 정보가 담긴 Map
	 * @return              칭찬/불편/건의 리스트
	 */
	public List<VOCDTO> list(Map<String, String> map) {
		
		return dao.list(map);
	}

	/**
	 * 칭찬/불편/건의의 페이징 정보를 생성하는 메서드입니다.
	 *
	 * @param page          현재 페이지 번호
	 * @return              생성된 페이징 정보가 담긴 Map
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
	 * 칭찬/불편/건의를 삭제하는 메서드입니다.
	 *
	 * @param selectedVOC   삭제할 칭찬/불편/건의의 ID 배열
	 * @return              삭제된 칭찬/불편/건의의 총 개수
	 */
	public int delete(String[] selectedVOC) {
		
		int totalDeleted = 0;

		for (String seq : selectedVOC) {

			int deleted = dao.delete(seq);
			totalDeleted += deleted;

		}

		return totalDeleted;
	}

}
