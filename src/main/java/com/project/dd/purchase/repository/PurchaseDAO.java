package com.project.dd.purchase.repository;

import java.util.Map;

import com.project.dd.purchase.domain.PurchaseDTO;
import com.project.dd.shop.item.domain.ItemDTO;

public interface PurchaseDAO {

	int addBuy(PurchaseDTO dto);

	String getSeq();

	int addUserBuy(Map<String, String> map);

	ItemDTO getCart(String seq);

}
