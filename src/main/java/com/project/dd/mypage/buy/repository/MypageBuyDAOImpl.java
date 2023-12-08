package com.project.dd.mypage.buy.repository;

import java.util.List;

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
	public List<BuyDTO> list() {
		
		return mapper.list();
	}

}
