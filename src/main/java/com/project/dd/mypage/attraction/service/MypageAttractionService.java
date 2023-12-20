package com.project.dd.mypage.attraction.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.mypage.attraction.domain.AttractionDTO;
import com.project.dd.mypage.attraction.repositorty.MypageAttractionDAO;

@Service
public class MypageAttractionService {

	@Autowired
	private MypageAttractionDAO dao;
	
	 /**
     * 회원의 어트랙션 예약을 조회합니다.
     *
     * @param map   조회에 사용되는 맵
     * @return      조회된 관심 목록
     */
	public List<AttractionDTO> list(Map<String, String> map) {

		return dao.list(map);
	}

	 /**
     * 선택된 어트랙션 예약을 삭제합니다.
     *
     * @param selectedAttraction   삭제할 관심 목록 배열
     * @return                     삭제된 항목의 총 개수
     */
	public int delete(String[] selectedAttraction) {

		int totalDeleted = 0;

		for (String seq : selectedAttraction) {

			int deleted = dao.delete(seq);
			totalDeleted += deleted;

		}

		return totalDeleted;
	}

	/**
     * 페이징 처리에 사용되는 맵을 생성합니다.
     *
     * @param page   현재 페이지 번호
     * @param email  사용자 이메일
     * @return       페이징 처리에 필요한 맵
     */
	public Map<String, String> paging(int page, String email) {

		int pageSize = 12; // 나타났으면 하는 개수

		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = dao.getTotalCount(email);
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}

	/**
     * 회원의 이전 어트랙션 예약을 조회합니다.
     *
     * @param map   조회에 사용되는 맵
     * @return      조회된 다른 종류의 관심 목록
     */
	public List<AttractionDTO> plist(Map<String, String> map) {

		return dao.plist(map);
	}

	/**
     * 다른 종류의 관심 목록을 위한 페이징 처리에 사용되는 맵을 생성합니다.
     *
     * @param page   현재 페이지 번호
     * @param email  사용자 이메일
     * @return       다른 종류의 관심 목록을 위한 페이징 처리에 필요한 맵
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

}
