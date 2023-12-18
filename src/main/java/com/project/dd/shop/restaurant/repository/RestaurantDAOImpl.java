package com.project.dd.shop.restaurant.repository;

import java.util.List;
import java.util.Map;

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
	public List<RestaurantDTO> restaurantList(Map<String, String> map) {
		return mapper.list(map);
	}
	
	@Override
	public RestaurantDTO restaurantDetail(String seq) {
		return mapper.detail(seq);
	}
	
	@Override
	public List<RestaurantImageDTO> restaurantImg(String seq) {
		return mapper.image(seq);
	}
	
	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}
	
	@Override
	public List<RestaurantImageDTO> getImgList() {
		return mapper.getImgList();
	}
	
	@Override
	public int checkNameDuplication(RestaurantDTO dto) {
		return mapper.checkNameDuplication(dto);
	}
	
	@Override
	public int addRestaurant(RestaurantDTO dto) {
		return mapper.addRestaurant(dto);
	}
	
	@Override
	public String getSeq() {
		return mapper.getSeq();
	}
	
	@Override
	public void addRestaurantLocation(RestaurantDTO dto) {
		mapper.addRestaurantLocation(dto);
	}
	
	@Override
	public void addRestaurantImg(RestaurantDTO dto) {
		mapper.addRestaurantImg(dto);
	}
	
	@Override
	public int delRestaurant(String seq) {
		return mapper.delRestaurant(seq);
	}

}
