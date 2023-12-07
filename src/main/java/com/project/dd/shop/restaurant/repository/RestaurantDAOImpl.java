package com.project.dd.shop.restaurant.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.domain.RestaurantImageDTO;
import com.project.dd.shop.restaurant.mapper.RestaurantMapper;

@Repository
@Primary
public class RestaurantDAOImpl implements RestaurantDAO {
	
	@Autowired
	private RestaurantMapper mapper;
	
	@Override
	public List<RestaurantDTO> restaurantList() {
		return mapper.list();
	}
	
	@Override
	public RestaurantDTO restaurantDetail(String seq) {
		return mapper.detail(seq);
	}
	
	@Override
	public List<RestaurantImageDTO> restaurantImg(String seq) {
		return mapper.image(seq);
	}

}
