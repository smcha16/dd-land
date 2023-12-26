package com.project.dd.shop.item.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.shop.item.domain.ItemDTO;
import com.project.dd.shop.item.domain.ItemImgDTO;
import com.project.dd.shop.item.repository.ItemDAO;

/**
 * 아이템 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * @author pega0
 *
 */
@Service
public class ItemService {

	@Autowired
	private ItemDAO dao;

	/**
     * 페이징에 사용되는 맵을 생성하여 반환하는 메서드입니다.
     *
     * @param page 현재 페이지 번호
     * @param seq  조회할 상품이 속한 상점의 시퀀스
     * @return 페이징에 필요한 정보를 담은 맵
     */
	public Map<String, String> paging(int page, String seq) {
		int pageSize = 10; // 나타났으면 하는 개수

		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = dao.getTotalCounts(map);
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}
	
	/**
     * 페이징 및 검색 조건에 사용되는 맵을 생성하여 반환하는 메서드입니다.
     *
     * @param page         현재 페이지 번호
     * @param word         검색어
     * @param searchStatus 검색 상태 (y: 검색 중, n: 검색 아님)
     * @param solting      정렬 기준
     * @return 페이징 및 검색에 필요한 정보를 담은 맵
     */
	public Map<String, String> paging(int page, String word, String searchStatus, String solting) {
		int pageSize = 10; // 나타났으면 하는 개수

		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		Map<String, String> map = new HashMap<String, String>();

		map.put("searchStatus", searchStatus);
		map.put("word", word);

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = dao.getTotalCounts(map);
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}

	/**
     * 특정 상점의 상품 목록을 조회하는 메서드입니다.
     *
     * @param map 페이징 및 검색 조건이 포함된 맵
     * @return 상품 목록
     */
	public List<ItemDTO> getList(Map<String, String> map) {
		return dao.getList(map);
	}

	/**
     * 특정 상품의 상세 정보를 조회하는 메서드입니다.
     *
     * @param seq 조회할 상품의 시퀀스
     * @return 상품의 상세 정보
     */
	public ItemDTO getItem(String seq) {
		return dao.getItem(seq);
	}

	/**
     * 특정 상품의 이미지 목록을 조회하는 메서드입니다.
     *
     * @param seq 조회할 상품의 시퀀스
     * @return 상품의 이미지 목록
     */
	public List<ItemImgDTO> getImg(String seq) {
		return dao.getImg(seq);
	}

	/**
     * 페이징 및 검색 조건이 포함된 전체 상품 목록을 조회하는 메서드입니다.
     *
     * @param map 페이징 및 검색 조건이 포함된 맵
     * @return 전체 상품 목록
     */
	public List<ItemDTO> getFullList(Map<String, String> map) {
		return dao.getFullList(map);
	}

	/**
     * 상품 이미지 목록을 조회하는 메서드입니다.
     *
     * @return 상품 이미지 목록
     */
	public List<ItemImgDTO> getImgList() {
		return dao.getImgList();
	}

	/**
     * 장바구니에 상품을 추가하거나 수정하는 메서드입니다.
     * 이미 장바구니에 존재하는 상품인 경우 수량을 증가시킵니다.
     *
     * @param dto 장바구니에 추가 또는 수정할 상품 정보
     * @return 장바구니 추가 또는 수정 결과 (1: 성공, 0: 실패)
     */
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

	/**
     * 선택한 상품을 삭제하는 메서드입니다.
     *
     * @param item_seq 삭제할 상품 시퀀스 배열
     * @return 삭제 결과 (삭제된 상품 수)
     */
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
