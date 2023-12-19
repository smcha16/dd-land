package com.project.dd.purchase.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.purchase.domain.PurchaseDTO;
import com.project.dd.purchase.mapper.PurchaseMapper;

@Repository
@Primary
public class PurchaseDAOImpl implements PurchaseDAO {

	@Autowired
	private PurchaseMapper mapper;
	
	@Override
	public int addBuy(PurchaseDTO dto) {
		return mapper.addBuy(dto);
	}
	
	@Override
	public String getSeq() {
		return mapper.getSeq();
	}
	
	@Override
	public int addUserBuy(Map<String, String> map) {
		return mapper.addUserBuy(map);
	}
	
}
