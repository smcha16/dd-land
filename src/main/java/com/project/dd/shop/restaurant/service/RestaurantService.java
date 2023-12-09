package com.project.dd.shop.restaurant.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.domain.RestaurantImageDTO;
import com.project.dd.shop.restaurant.repository.RestaurantDAO;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantDAO dao;

	public List<RestaurantDTO> getList(Map<String, String> map) {
		return dao.restaurantList(map);
	}

	public RestaurantDTO detail(String seq) {
		return dao.restaurantDetail(seq);
	}

	public List<RestaurantImageDTO> getImg(String seq) {
		return dao.restaurantImg(seq);
	}

	public Map<String, String> paging(int page) {  //페이징 메서드
	      int pageSize = 6;  //나타났으면 하는 개수
	      
	      int startIndex = (page - 1) * pageSize + 1;
	      int endIndex = startIndex + pageSize - 1;
	      
	      Map<String, String> map = new HashMap<String, String>();

	      map.put("startIndex", String.format("%d", startIndex));
	      map.put("endIndex", String.format("%d", endIndex));
	      
	      int totalPosts = dao.getTotalCount();
	      int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
	      
	      map.put("totalPosts", String.format("%d", totalPosts));
	      map.put("totalPages", String.format("%d", totalPages));
	      
	      return map;
	   }
	
}
