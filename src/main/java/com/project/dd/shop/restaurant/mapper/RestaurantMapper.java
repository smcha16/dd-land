package com.project.dd.shop.restaurant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.domain.RestaurantImageDTO;

public interface RestaurantMapper {

	@Select("select * from vwRestaurant where lat != 0")
	List<RestaurantDTO> list();

	@Select("select * from vwRestaurant where restaurant_seq = #{seq}")
	RestaurantDTO detail(@Param("seq") String seq);

	@Select("select * from tblRestaurantImg where restaurant_seq = #{seq}")
	List<RestaurantImageDTO> image(@Param("seq") String seq);

}
