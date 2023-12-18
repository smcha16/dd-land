package com.project.dd.shop.restaurant.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.domain.RestaurantImageDTO;

public interface RestaurantDAO {

	List<RestaurantDTO> restaurantList(Map<String, String> map);

	RestaurantDTO restaurantDetail(String seq);

	List<RestaurantImageDTO> restaurantImg(String seq);

	int getTotalCount();

	List<RestaurantImageDTO> getImgList();

}
