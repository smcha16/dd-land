package com.project.dd.purchase.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.project.dd.purchase.domain.PurchaseDTO;
import com.project.dd.shop.item.domain.ItemDTO;

public interface PurchaseMapper {
	
	int addBuy(PurchaseDTO dto);

	@Select("select max(buy_seq) as seq from tblBuy")
	String getSeq();

	int addUserBuy(Map<String, String> map);

	ItemDTO getCart(String seq);

}
