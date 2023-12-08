package com.project.dd.shop.giftshop.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.shop.giftshop.domain.GiftshopImageDTO;
import com.project.dd.shop.giftshop.domain.ShopDTO;
import com.project.dd.shop.giftshop.mapper.ShopMapper;

@Repository
@Primary
public class GiftshopDAOImpl implements GiftshopDAO {

	@Autowired
	private ShopMapper mapper;
	
	@Override
	public List<ShopDTO> getList(Map<String, String> map) {
		return mapper.getList(map);
	}
	
	@Override
	public ShopDTO shopDetail(String seq) {
		return mapper.detail(seq);
	}
	
	@Override
	public List<GiftshopImageDTO> shopImg(String seq) {
		return mapper.image(seq);
	}
	
	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}
	
}
