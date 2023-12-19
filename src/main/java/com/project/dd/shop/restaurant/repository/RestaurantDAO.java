package com.project.dd.shop.restaurant.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.domain.RestaurantImageDTO;

public interface RestaurantDAO {

	List<RestaurantDTO> restaurantList(Map<String, String> map);

	RestaurantDTO restaurantDetail(String seq);

	List<RestaurantImageDTO> restaurantImg(String seq);

	int getTotalCount(Map<String, String> map);

	List<RestaurantImageDTO> getImgList();

	int checkNameDuplication(RestaurantDTO dto);

	int addRestaurant(RestaurantDTO dto);

	String getSeq();

	void addRestaurantLocation(RestaurantDTO dto);

	void addRestaurantImg(RestaurantDTO dto);

	int delRestaurant(String seq);

}
