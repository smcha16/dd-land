package com.project.dd.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.dd.member.repository.AdminMemberDAO;
import com.project.dd.register.domain.MemberDTO;

import lombok.RequiredArgsConstructor;

/**
 * 회원 정보를 관리하는 서비스 클래스
 * 
 * @author 김형우
 */
@Service
@RequiredArgsConstructor
public class AdminMemberService {
	
	private final AdminMemberDAO dao;

	/**
	 * 회원을 검색하는 메서드
	 * 
	 * @param query 검색어
	 * @return 검색된 회원 목록
	 */
	public List<MemberDTO> search(String query) {
		return dao.search(query);
	}

	/**
	 * 회원 목록을 페이징 처리하여 반환하는 메서드
	 * 
	 * @param type 회원 유형
	 * @param page 현재 페이지
	 * @return 페이징 처리된 회원 목록 정보 (시작 페이지, 끝 페이지, 현재 페이지, 총 페이지 수 등)
	 */
	public Map<String, String> paging(String type, int page) {
		Map<String, String> map = new HashMap<>();

		map.put("type", type);

		int pageSize = 10;

		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = dao.getTotalCount(type);
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}

	/**
	 * 페이징 처리된 회원 목록을 조회하는 메서드
	 * 
	 * @param map 페이징 및 검색 조건
	 * @return 페이징 처리된 회원 목록
	 */
	public List<MemberDTO> getMemberList(Map<String, String> map) {
		return dao.getMemberList(map);
	}

	/**
	 * 회원을 삭제하는 메서드
	 * 
	 * @param user_seq 삭제할 회원의 일련번호
	 * @return 삭제 결과 (영향받은 행 수)
	 */
	public int del(String user_seq) {
		return dao.del(user_seq);
	}

	/**
	 * 회원 정보를 수정하는 메서드
	 * 
	 * @param memberDTO 수정된 회원 정보
	 * @return 수정 결과 (영향받은 행 수)
	 */
	public int edit(MemberDTO memberDTO) {
		return dao.edit(memberDTO);
	}
}
