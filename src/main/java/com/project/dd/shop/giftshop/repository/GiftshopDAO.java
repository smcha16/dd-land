package com.project.dd.shop.giftshop.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.shop.giftshop.domain.GiftshopImageDTO;
import com.project.dd.shop.giftshop.domain.ShopDTO;

public interface GiftshopDAO {

	List<ShopDTO> getList(Map<String, String> map);

	ShopDTO shopDetail(String seq);

	List<GiftshopImageDTO> shopImg(String seq);

	int getTotalCount();

}
