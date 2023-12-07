package com.project.dd.shop.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.domain.RestaurantImageDTO;
import com.project.dd.shop.restaurant.repository.RestaurantDAO;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantDAO dao;

	public List<RestaurantDTO> getList() {
		return dao.restaurantList();
	}

	public RestaurantDTO detail(String seq) {
		return dao.restaurantDetail(seq);
	}

	public List<RestaurantImageDTO> getImg(String seq) {
		return dao.restaurantImg(seq);
	}
	
}
