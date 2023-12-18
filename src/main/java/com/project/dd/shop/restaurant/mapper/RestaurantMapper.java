package com.project.dd.shop.restaurant.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.domain.RestaurantImageDTO;

public interface RestaurantMapper {

	List<RestaurantDTO> list(Map<String, String> map);

	@Select("select * from vwRestaurant where restaurant_seq = #{seq}")
	RestaurantDTO detail(@Param("seq") String seq);

	@Select("select * from tblRestaurantImg where restaurant_seq = #{seq}")
	List<RestaurantImageDTO> image(@Param("seq") String seq);

	@Select("select count(*) from vwRestaurant where lat != '0'")
	int getTotalCount();

	@Select("select * from tblRestaurantImg")
	List<RestaurantImageDTO> getImgList();
	
	int checkNameDuplication(RestaurantDTO dto);

	int addRestaurant(RestaurantDTO dto);

	@Select("select max(restaurant_seq) as restaurant_seq from tblRestaurant")
	String getSeq();

	void addRestaurantLocation(RestaurantDTO dto);

	void addRestaurantImg(RestaurantDTO dto);

	int delRestaurant(String seq);

}
