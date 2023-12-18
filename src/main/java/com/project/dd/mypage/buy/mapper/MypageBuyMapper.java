package com.project.dd.mypage.buy.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.mypage.buy.domain.BuyDTO;

public interface MypageBuyMapper {

	List<BuyDTO> list(Map<String, String> map);

	int getTotalCount();

	int delete(String selectedItem);

}
