package com.project.dd.close.giftshop.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.close.giftshop.domain.CloseGiftShopDTO;

public interface CloseShopMapper {

	int getTotalCount();

	List<CloseGiftShopDTO> list(Map<String, String> map);

	List<CloseGiftShopDTO> shoplist();

	int addCloseShop(CloseGiftShopDTO dto);

	CloseGiftShopDTO getOne(String seq);

	int editClose(CloseGiftShopDTO dto);

	void del(String seq);

}
