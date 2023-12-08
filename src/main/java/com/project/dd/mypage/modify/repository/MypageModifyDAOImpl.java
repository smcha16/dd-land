package com.project.dd.mypage.modify.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.mypage.modify.domain.ModifyDTO;
import com.project.dd.mypage.modify.mapper.MypageModifyMapper;

@Primary
@Repository
public class MypageModifyDAOImpl implements MypageModifyDAO{
	
	@Autowired
	private MypageModifyMapper mapper;
	
	@Override
	public List<ModifyDTO> list() {
		
		return mapper.list();
	}

}
