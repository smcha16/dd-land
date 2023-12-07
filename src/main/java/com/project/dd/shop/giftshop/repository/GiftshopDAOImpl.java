package com.project.dd.shop.giftshop.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.shop.giftshop.domain.ShopDTO;
import com.project.dd.shop.giftshop.mapper.ShopMapper;

@Repository
@Primary
public class GiftshopDAOImpl implements GiftshopDAO {

	@Autowired
	private ShopMapper mapper;
	
	@Override
	public List<ShopDTO> getList() {
		return mapper.getList();
	}
	
}
