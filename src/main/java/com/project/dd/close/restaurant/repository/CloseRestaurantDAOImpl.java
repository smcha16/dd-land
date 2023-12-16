package com.project.dd.close.restaurant.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.close.restaurant.domain.CloseRestaurantDTO;
import com.project.dd.close.restaurant.mapper.CloseRestaurantMapper;
import com.project.dd.close.restaurant.service.CloseRestaurantService;


@Primary
@Repository
public class CloseRestaurantDAOImpl implements CloseRestaurantDAO {
	
	@Autowired
	private CloseRestaurantMapper mapper;
	
	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}

	@Override
	public List<CloseRestaurantDTO> list(Map<String, String> map) {
		
		return mapper.list(map);
	}

	@Override
	public List<CloseRestaurantDTO> restaurantlist() {
		
		return mapper.restaurantlist();
	}

	@Override
	public int addCloseRestaurant(CloseRestaurantDTO dto) {
		
		return mapper.addCloseRestaurant(dto);
	}

	@Override
	public CloseRestaurantDTO getOne(String seq) {
		
		return mapper.getOne(seq);
	}

	@Override
	public int editClose(CloseRestaurantDTO dto) {
		
		return mapper.editClose(dto);
	}

	@Override
	public void del(String seq) {
		mapper.del(seq);
	}

}
