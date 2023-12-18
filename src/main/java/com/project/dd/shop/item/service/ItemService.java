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
		int pageSize = 10;  //나타났으면 하는 개수
	      
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

	public int addCart(ItemDTO dto) {
		ItemDTO temp = dao.checkCart(dto);
		
		if (temp == null) {
			int result = dao.addCart(dto);
			
			if (result == 1) {
				String seq = dao.getSeq();
				dto.setCart_seq(seq);
				
				result = dao.addUserCart(dto);
			}
			
			return result;
		} else {
			dto.setCart_seq(temp.getCart_seq());
			dto.setEa("" + (Integer.parseInt(temp.getEa()) + Integer.parseInt(dto.getEa())));
			
			return dao.editCart(dto);
		}
		
		
	}

	public int delItem(String[] item_seq) {
		int result = 0;
		
		for (String seq : item_seq) {
			String[] cart_seqs = dao.getItemSeqs(seq);
			
			for (String cart_seq : cart_seqs) {
				dao.delUserCart(cart_seq);
			}
			
			result += dao.delItem(seq);
		}
		
		return 0;
	}

}
