package com.project.dd.purchase.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.project.dd.purchase.domain.PurchaseDTO;

public interface PurchaseMapper {
	
	int addBuy(PurchaseDTO dto);

	@Select("select max(buy_seq) as seq from tblBuy")
	String getSeq();

	int addUserBuy(Map<String, String> map);

}
