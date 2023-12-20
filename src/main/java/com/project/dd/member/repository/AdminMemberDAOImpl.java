package com.project.dd.member.repository;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.member.mapper.AdminMemberMapper;
import com.project.dd.register.domain.MemberDTO;

import lombok.RequiredArgsConstructor;

/**
 * 관리자가 회원 정보를 데이터베이스에서 처리하는 DAO 구현체 클래스
 * 
 * @author 김형우
 */
@Primary
@Repository
@RequiredArgsConstructor
public class AdminMemberDAOImpl implements AdminMemberDAO {
	
	private final AdminMemberMapper mapper;

	/**
	 * 회원을 검색하는 메서드
	 * 
	 * @param query 검색어
	 * @return 검색된 회원 목록
	 */
	@Override
	public List<MemberDTO> search(String query) {
		return mapper.search(query);
	}

	/**
	 * 전체 회원 수를 조회하는 메서드
	 * 
	 * @param type 회원 유형
	 * @return 전체 회원 수
	 */
	@Override
	public int getTotalCount(String type) {
		return mapper.getTotalCount(type);
	}

	/**
	 * 페이징 처리된 회원 목록을 조회하는 메서드
	 * 
	 * @param map 페이징 및 검색 조건
	 * @return 페이징 처리된 회원 목록
	 */
	@Override
	public List<MemberDTO> getMemberList(Map<String, String> map) {
		return mapper.getMemberList(map);
	}

	/**
	 * 회원을 삭제하는 메서드
	 * 
	 * @param user_seq 삭제할 회원의 일련번호
	 * @return 삭제 결과 (영향받은 행 수)
	 */
	@Override
	public int del(String user_seq) {
		return mapper.del(user_seq);
	}

	/**
	 * 회원 정보를 수정하는 메서드
	 * 
	 * @param memberDTO 수정된 회원 정보
	 * @return 수정 결과 (영향받은 행 수)
	 */
	@Override
	public int edit(MemberDTO memberDTO) {
		return mapper.edit(memberDTO);
	}
}
