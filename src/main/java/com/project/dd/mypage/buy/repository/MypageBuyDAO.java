package com.project.dd.mypage.buy.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.mypage.buy.domain.BuyDTO;

public interface MypageBuyDAO {

	List<BuyDTO> list(Map<String, String> map);

	int getTotalCount(String email);

	int delete(String seq);

	int pGetTotalCount(String email);

	List<BuyDTO> plist(Map<String, String> map);

}
