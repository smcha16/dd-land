package com.project.dd.shop.restaurant.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

	public Map<String, String> paging(int page) { // 페이징 메서드
		int pageSize = 6; // 나타났으면 하는 개수

		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = dao.getTotalCount(map);
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}

	public Map<String, String> paging(int page, String word, String searchStatus, String solting) {
		int pageSize = 10;

		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		Map<String, String> map = new HashMap<String, String>();

		map.put("searchStatus", searchStatus);
		map.put("word", word);
		
		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = dao.getTotalCount(map);
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}

	public List<RestaurantImageDTO> getImgList() {
		return dao.getImgList();
	}

	public int checkNameDuplication(RestaurantDTO dto) {
		return dao.checkNameDuplication(dto);
	}

	public int addRestaurant(RestaurantDTO dto) {
		return dao.addRestaurant(dto);
	}

	public String getSeq() {
		return dao.getSeq();
	}

	public void addRestaurantLocation(RestaurantDTO dto) {
		dao.addRestaurantLocation(dto);

	}

	public void addRestaurantImg(RestaurantDTO dto) {
		dao.addRestaurantImg(dto);
	}

	public int editRestaurant(RestaurantDTO dto, MultipartFile image, HttpServletRequest req, String deleteImgSeq) {
		String seq = dto.getRestaurant_seq();
		int result = 0;
		
		
		return 0;
	}

	public int delRestaurant(String[] restaurant_seq) {
		int result = 0;
		
		for (String seq : restaurant_seq) {
			result += dao.delRestaurant(seq);
		}
		
		return result;
	}

	public int getRestaurantCloseCount(List<RestaurantDTO> list) {
		int closeCount = 0;
		
		for (RestaurantDTO dto : list) {
			if (dto.getClose().equalsIgnoreCase("y")) {
				closeCount++;
			}
		}
		
		return closeCount;
	}

}
