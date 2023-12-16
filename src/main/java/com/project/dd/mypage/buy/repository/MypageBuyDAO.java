package com.project.dd.mypage.buy.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.mypage.buy.domain.BuyDTO;

public interface MypageBuyDAO {

	List<BuyDTO> list(Map<String, String> map);

	int getTotalCount();

	int delete(String selectedItem);

}
