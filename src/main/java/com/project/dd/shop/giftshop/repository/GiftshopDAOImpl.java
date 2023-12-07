package com.project.dd.shop.giftshop.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.shop.giftshop.domain.GiftshopDTO;
import com.project.dd.shop.giftshop.mapper.GiftshopMapper;

@Repository
@Primary
public class GiftshopDAOImpl implements GiftshopDAO {

	@Autowired
	private GiftshopMapper mapper;
	
	@Override
	public List<GiftshopDTO> getList() {
		return mapper.getList();
	}
	
}
