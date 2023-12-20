package com.project.dd.mypage.buy.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.mypage.buy.domain.BuyDTO;
import com.project.dd.mypage.buy.mapper.MypageBuyMapper;

@Primary
@Repository
public class MypageBuyDAOImpl implements MypageBuyDAO{
	
	@Autowired
	private MypageBuyMapper mapper;
	
	@Override
	public List<BuyDTO> list(Map<String, String> map) {
		
		return mapper.list(map);
	}
	
	@Override
	public int getTotalCount(String email) {
		
		return mapper.getTotalCount(email);
	}
	
	@Override
	public int delete(String seq) {
		
		return mapper.delete(seq);
	}
	
	@Override
	public int pGetTotalCount(String email) {
		
		return mapper.pGetTotalCount(email);
	}
	
	@Override
	public List<BuyDTO> plist(Map<String, String> map) {
		
		return mapper.plist(map);
	}

}
