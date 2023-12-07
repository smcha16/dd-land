package com.project.dd.shop.giftshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.shop.giftshop.domain.ShopDTO;
import com.project.dd.shop.giftshop.repository.GiftshopDAO;

@Service
public class GiftshopService {
	
	@Autowired
	private GiftshopDAO dao;

	public List<ShopDTO> getList() {
		return dao.getList();
	}

}
