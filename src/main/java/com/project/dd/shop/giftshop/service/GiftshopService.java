package com.project.dd.shop.giftshop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.shop.giftshop.domain.GiftshopImageDTO;
import com.project.dd.shop.giftshop.domain.ShopDTO;
import com.project.dd.shop.giftshop.repository.GiftshopDAO;

/**
 * 기프트샵 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * @author pega0
 *
 */
@Service
public class GiftshopService {
	
	@Autowired
	private GiftshopDAO dao;

	/**
	 * 기프트샵 목록을 가져오는 메서드입니다.
	 *
	 * @param map 페이징 및 검색 조건을 담은 Map 객체
	 * @return 기프트샵 목록
	 */
	public List<ShopDTO> getList(Map<String, String> map) {
		return dao.getList(map);
	}

	/**
	 * 선택된 기프트샵의 상세 정보를 가져오는 메서드입니다.
	 *
	 * @param seq 선택된 기프트샵 번호
	 * @return 선택된 기프트샵의 상세 정보
	 */
	public ShopDTO detail(String seq) {
		return dao.shopDetail(seq);
	}

	/**
	 * 선택된 기프트샵의 이미지 목록을 가져오는 메서드입니다.
	 *
	 * @param seq 선택된 기프트샵 번호
	 * @return 선택된 기프트샵의 이미지 목록
	 */
	public List<GiftshopImageDTO> getImg(String seq) {
		return dao.shopImg(seq);
	}

	/**
	 * 기프트샵 페이징 정보를 생성하는 메서드입니다.
	 *
	 * @param page 현재 페이지 번호
	 * @return 페이징 정보를 담은 Map 객체
	 */
	public Map<String, String> paging(int page) {  //페이징 메서드
	      int pageSize = 6;  //나타났으면 하는 개수
	      
	      int startIndex = (page - 1) * pageSize + 1;
	      int endIndex = startIndex + pageSize - 1;
	      
	      Map<String, String> map = new HashMap<String, String>();

	      map.put("startIndex", String.format("%d", startIndex));
	      map.put("endIndex", String.format("%d", endIndex));
	      
	      int totalPosts = dao.getTotalCount(map);
	      int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
	      
	      map.put("totalPosts", String.format("%d", totalPosts));
	      map.put("totalPages", String.format("%d", totalPages));
	      
	      return map;
	   }

	/**
	 * 기프트샵 검색 및 페이징 정보를 생성하는 메서드입니다.
	 *
	 * @param page         현재 페이지 번호
	 * @param word         검색어
	 * @param searchStatus 검색 여부를 나타내는 문자열("y" 또는 "n")
	 * @param solting      정렬 기준
	 * @return 검색 및 페이징 정보를 담은 Map 객체
	 */
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

	/**
	 * 기프트샵 이미지 목록을 가져오는 메서드입니다.
	 *
	 * @return 기프트샵 이미지 목록
	 */
	public List<GiftshopImageDTO> getImgList() {
		return dao.getImgList();
	}

	/**
	 * 선택된 기프트샵 및 연결된 상품 정보를 삭제하는 메서드입니다.
	 *
	 * @param shop_seq 삭제할 기프트샵 번호 배열
	 * @return 삭제된 기프트샵 및 연결된 상품 정보의 개수
	 */
	public int delGiftshop(String[] shop_seq) {
		
		int result = 0;
		
		for (String seq : shop_seq) {
			dao.delItems(seq);
			
			result += dao.delGiftshop(seq);
		}
		
		return result;
	}

	/**
	 * 기프트샵 중 폐점된 상품의 개수를 가져오는 메서드입니다.
	 *
	 * @param list 기프트샵 목록
	 * @return 폐점된 상품의 개수
	 */
	public int getShopCloseCount(List<ShopDTO> list) {
		int totalCount = 0;
		
		for (ShopDTO dto : list) {
			if (dto.getClose().equalsIgnoreCase("y")) {
				totalCount++;
			}
		}

		return totalCount;
	}

}
