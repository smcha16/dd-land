package com.project.dd.shop.giftshop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.shop.giftshop.domain.GiftshopImageDTO;
import com.project.dd.shop.giftshop.domain.ShopDTO;
import com.project.dd.shop.giftshop.repository.GiftshopDAO;

@Service
public class GiftshopService {
	
	@Autowired
	private GiftshopDAO dao;

	public List<ShopDTO> getList(Map<String, String> map) {
		return dao.getList(map);
	}

	public ShopDTO detail(String seq) {
		return dao.shopDetail(seq);
	}

	public List<GiftshopImageDTO> getImg(String seq) {
		return dao.shopImg(seq);
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

	public Map<String, String> paging(int page, String solting) {
		int pageSize = 0;

		// user or admin 노출 목록 개수 설정
		if (solting.equalsIgnoreCase("user")) {
			pageSize = 9; // 나타났으면 하는 개수(user)

		} else if (solting.equalsIgnoreCase("admin")) {
			pageSize = 10; // 나타났으면 하는 개수(admin)
		}

		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = dao.getTotalCount();
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}

	public List<GiftshopImageDTO> getImgList() {
		return dao.getImgList();
	}

	public int delGiftshop(String[] shop_seq) {
		
		int result = 0;
		
		for (String seq : shop_seq) {
			dao.delItems(seq);
			
			result += dao.delGiftshop(seq);
		}
		
		return result;
	}

}
