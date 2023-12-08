package com.project.dd.shop.giftshop.repository;

import java.util.List;

import com.project.dd.shop.giftshop.domain.GiftshopImageDTO;
import com.project.dd.shop.giftshop.domain.ShopDTO;

public interface GiftshopDAO {

	List<ShopDTO> getList();

	ShopDTO shopDetail(String seq);

	List<GiftshopImageDTO> shopImg(String seq);

}
