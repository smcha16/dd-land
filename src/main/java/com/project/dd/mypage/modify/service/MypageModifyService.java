package com.project.dd.mypage.modify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.mypage.modify.domain.ModifyDTO;
import com.project.dd.mypage.modify.repository.MypageModifyDAO;

@Service
public class MypageModifyService {
	
	@Autowired
	private MypageModifyDAO dao;

	/**
	 * 사용자의 마이페이지 수정 정보를 조회하는 메서드입니다.
	 *
	 * @param email     사용자 이메일
	 * @return          사용자의 마이페이지 수정 정보 목록
	 */
	public List<ModifyDTO> list(String email) {
		
		return dao.list(email);
	}

	/**
	 * 사용자의 마이페이지 수정 정보를 변경하는 메서드입니다.
	 *
	 * @param dto       수정할 정보를 담은 ModifyDTO 객체
	 * @return          수정 결과를 반환 (성공 시 1, 실패 시 0)
	 */
	public int edit(ModifyDTO dto) {
		
		return dao.edit(dto);
	}

	

}
