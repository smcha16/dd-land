package com.project.dd.close.restaurant.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.close.restaurant.domain.CloseRestaurantDTO;

public interface CloseRestaurantMapper {

	int getTotalCount();

	List<CloseRestaurantDTO> list(Map<String, String> map);

	List<CloseRestaurantDTO> restaurantlist();

	int addCloseRestaurant(CloseRestaurantDTO dto);

	CloseRestaurantDTO getOne(String seq);

	int editClose(CloseRestaurantDTO dto);

	void del(String seq);

}
