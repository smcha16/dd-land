package com.project.dd.purchase.repository;

import java.util.Map;

import com.project.dd.purchase.domain.PurchaseDTO;

public interface PurchaseDAO {

	int addBuy(PurchaseDTO dto);

	String getSeq();

	int addUserBuy(Map<String, String> map);

}
