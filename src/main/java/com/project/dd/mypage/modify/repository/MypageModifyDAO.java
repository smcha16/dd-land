package com.project.dd.mypage.modify.repository;

import java.util.List;

import com.project.dd.mypage.modify.domain.ModifyDTO;

public interface MypageModifyDAO {

	List<ModifyDTO> list(String email);

	int edit(ModifyDTO dto);

}
