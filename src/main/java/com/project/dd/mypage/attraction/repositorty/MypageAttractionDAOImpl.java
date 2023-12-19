package com.project.dd.mypage.attraction.repositorty;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.mypage.attraction.domain.AttractionDTO;
import com.project.dd.mypage.attraction.mapper.MypageAttractionMapper;

@Primary
@Repository
public class MypageAttractionDAOImpl implements MypageAttractionDAO{
	
	@Autowired
	private MypageAttractionMapper mapper;
	
	@Override
	public List<AttractionDTO> list(Map<String, String> map) {
		
		return mapper.list(map);
	}
	
	@Override
	public int delete(String seq) {
		
		return mapper.delete(seq);
	}
	
	@Override
	public int getTotalCount(String email) {
		
		return mapper.getTotalCount(email);
	}
	
	@Override
	public List<AttractionDTO> plist(Map<String, String> map) {
		
		return mapper.plist(map);
	}
	
	@Override
	public int pGetTotalCount(String email) {
		
		return mapper.pGetTotalCount(email);
	}

}
