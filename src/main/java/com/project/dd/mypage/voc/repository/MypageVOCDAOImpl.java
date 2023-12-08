package com.project.dd.mypage.voc.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.mypage.voc.domain.VOCDTO;
import com.project.dd.mypage.voc.mapper.MypageVOCMapper;

@Primary
@Repository
public class MypageVOCDAOImpl implements MypageVOCDAO{

	@Autowired
	private MypageVOCMapper mapper;
	
	@Override
	public List<VOCDTO> list() {
		
		return mapper.list();
	}
}
