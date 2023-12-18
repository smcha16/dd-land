package com.project.dd.shop.item.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.shop.item.domain.ItemDTO;
import com.project.dd.shop.item.domain.ItemImgDTO;
import com.project.dd.shop.item.repository.ItemDAO;

@Service
public class ItemService {
	
	@Autowired
	private ItemDAO dao;

	public Map<String, String> paging(int page, String seq) {
		int pageSize = 6;  //나타났으면 하는 개수
	      
	      int startIndex = (page - 1) * pageSize + 1;
	      int endIndex = startIndex + pageSize - 1;
	      
	      Map<String, String> map = new HashMap<String, String>();

	      map.put("startIndex", String.format("%d", startIndex));
	      map.put("endIndex", String.format("%d", endIndex));
	      
	      int totalPosts = dao.getTotalCounts();
	      int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
	      
	      map.put("totalPosts", String.format("%d", totalPosts));
	      map.put("totalPages", String.format("%d", totalPages));
	      
	      return map;
	}

	public List<ItemDTO> getList(Map<String, String> map) {
		return dao.getList(map);
	}

	public ItemDTO getItem(String seq) {
		return dao.getItem(seq);
	}

	public List<ItemImgDTO> getImg(String seq) {
		return dao.getImg(seq);
	}

	public List<ItemDTO> getFullList(Map<String, String> map) {
		return dao.getFullList(map);
	}

	public List<ItemImgDTO> getImgList() {
		return dao.getImgList();
	}

}
