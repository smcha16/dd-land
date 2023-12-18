package com.project.dd.purchase.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.purchase.domain.PurchaseDTO;
import com.project.dd.purchase.repository.PurchaseDAO;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseDAO dao;

	public int addBuy(PurchaseDTO dto) {
		return dao.addBuy(dto);
	}

	public String getSeq() {
		return dao.getSeq();
	}

	public int addUserBuy(Map<String, String> map) {
		return dao.addUserBuy(map);
	}
	
}
