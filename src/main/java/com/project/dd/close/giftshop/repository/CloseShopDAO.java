package com.project.dd.close.giftshop.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.close.giftshop.domain.CloseGiftShopDTO;

public interface CloseShopDAO {

	int getTotalCount();

	List<CloseGiftShopDTO> list(Map<String, String> map);

	List<CloseGiftShopDTO> shoplist();

	int addCloseShop(CloseGiftShopDTO dto);

	CloseGiftShopDTO getOne(String seq);

	int editClose(CloseGiftShopDTO dto);

	void del(String seq);

}
