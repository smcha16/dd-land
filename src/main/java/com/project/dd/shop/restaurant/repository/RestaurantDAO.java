package com.project.dd.shop.restaurant.repository;

import java.util.List;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.domain.RestaurantImageDTO;

public interface RestaurantDAO {

	List<RestaurantDTO> restaurantList();

	RestaurantDTO restaurantDetail(String seq);

	List<RestaurantImageDTO> restaurantImg(String seq);

}
