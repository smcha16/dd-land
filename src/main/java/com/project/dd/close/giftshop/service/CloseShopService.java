package com.project.dd.close.giftshop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.close.giftshop.domain.CloseGiftShopDTO;
import com.project.dd.close.giftshop.repository.CloseShopDAO;


@Service
public class CloseShopService {
	
	@Autowired
	private CloseShopDAO dao;

	public Map<String, String> paging(int page) {
		int pageSize = 9;  //나타났으면 하는 개수
		
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

	public List<CloseGiftShopDTO> list(Map<String, String> map) {
		
		return dao.list(map);
	}

	public List<CloseGiftShopDTO> shoplist() {
		
		return dao.shoplist();
	}

	public int addCloseShop(CloseGiftShopDTO dto) {
		
		return dao.addCloseShop(dto);
	}

	public CloseGiftShopDTO getOne(String seq) {
		
		CloseGiftShopDTO dto = dao.getOne(seq);
		
		//운휴 시작일, 종료일 가공
		String start_date=dto.getStart_date();
		String end_date=dto.getEnd_date();
		
		start_date=start_date.substring(0, 10);
		end_date=end_date.substring(0, 10);
		
		dto.setStart_date(start_date);
		dto.setEnd_date(end_date);
		
		return dto;
	}

	public int editClose(CloseGiftShopDTO dto) {
		
		return dao.editClose(dto);
	}

	public void del(String[] closeShop_seq) {
		for(String seq : closeShop_seq) {
			dao.del(seq);
		}
		
	}

}
