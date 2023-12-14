package com.project.dd.mypage.voc.repository;

import java.util.List;
import java.util.Map;

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
	public List<VOCDTO> list(Map<String, String> map) {
		
		return mapper.list(map);
	}
	
	@Override
	public int getTotalCount() {
		
		return mapper.getTotalCount();
	}
	
	@Override
	public int delete(String seq) {
		
		return mapper.delete(seq);
	}
}
