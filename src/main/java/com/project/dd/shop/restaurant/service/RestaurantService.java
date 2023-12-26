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

/**
 * 레스토랑 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * @author pega0
 *
 */
@Service
public class RestaurantService {

	@Autowired
	private RestaurantDAO dao;

	/**
     * 레스토랑 목록을 조회하는 메서드입니다.
     *
     * @param map 조회 조건을 담은 Map 객체
     * @return 레스토랑 목록
     */
	public List<RestaurantDTO> getList(Map<String, String> map) {
		return dao.restaurantList(map);
	}

	/**
     * 레스토랑 상세 정보를 조회하는 메서드입니다.
     *
     * @param seq 레스토랑 일련번호
     * @return 레스토랑 상세 정보
     */
	public RestaurantDTO detail(String seq) {
		return dao.restaurantDetail(seq);
	}

	/**
     * 레스토랑 이미지 목록을 조회하는 메서드입니다.
     *
     * @param seq 레스토랑 일련번호
     * @return 레스토랑 이미지 목록
     */
	public List<RestaurantImageDTO> getImg(String seq) {
		return dao.restaurantImg(seq);
	}

	/**
     * 페이징 정보를 반환하는 메서드입니다.
     *
     * @param page 현재 페이지 번호
     * @return 페이징 정보를 담은 Map 객체
     */

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

	/**
     * 페이징 정보를 반환하는 메서드입니다.
     *
     * @param page         현재 페이지 번호
     * @param word         검색어
     * @param searchStatus 검색 상태
     * @param solting      정렬 기준
     * @return 페이징 정보를 담은 Map 객체
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
     * 레스토랑 이미지 목록을 조회하는 메서드입니다.
     *
     * @return 레스토랑 이미지 목록
     */
	public List<RestaurantImageDTO> getImgList() {
		return dao.getImgList();
	}

	/**
     * 레스토랑 이름 중복 여부를 체크하는 메서드입니다.
     *
     * @param dto 레스토랑 정보 객체
     * @return 중복 여부 (1: 중복, 0: 미중복)
     */
	public int checkNameDuplication(RestaurantDTO dto) {
		return dao.checkNameDuplication(dto);
	}

	/**
     * 레스토랑을 추가하는 메서드입니다.
     *
     * @param dto 추가할 레스토랑 정보 객체
     * @return 추가된 레스토랑 수
     */
	public int addRestaurant(RestaurantDTO dto) {
		return dao.addRestaurant(dto);
	}

	/**
     * 최근 추가된 레스토랑의 일련번호를 조회하는 메서드입니다.
     *
     * @return 최근 추가된 레스토랑의 일련번호
     */
	public String getSeq() {
		return dao.getSeq();
	}

	/**
     * 레스토랑 위치 정보를 추가하는 메서드입니다.
     *
     * @param dto 레스토랑 정보 객체
     */
	public void addRestaurantLocation(RestaurantDTO dto) {
		dao.addRestaurantLocation(dto);

	}

	/**
     * 레스토랑 이미지를 추가하는 메서드입니다.
     *
     * @param dto 레스토랑 정보 객체
     */
	public void addRestaurantImg(RestaurantDTO dto) {
		dao.addRestaurantImg(dto);
	}

	/**
     * 레스토랑을 수정하는 메서드입니다.
     *
     * @param dto           수정할 레스토랑 정보 객체
     * @param image         새로운 이미지 파일
     * @param req           HttpServletRequest 객체
     * @param deleteImgSeq  삭제할 이미지 일련번호
     * @return 수정된 레스토랑 수
     */
	public int editRestaurant(RestaurantDTO dto, MultipartFile image, HttpServletRequest req, String deleteImgSeq) {
		String seq = dto.getRestaurant_seq();
		int result = 0;
		
		
		return 0;
	}

	/**
     * 레스토랑을 삭제하는 메서드입니다.
     *
     * @param restaurant_seq 삭제할 레스토랑 일련번호 배열
     * @return 삭제된 레스토랑 수
     */
	public int delRestaurant(String[] restaurant_seq) {
		int result = 0;
		
		for (String seq : restaurant_seq) {
			result += dao.delRestaurant(seq);
		}
		
		return result;
	}
	
	/**
     * 폐업한 레스토랑의 수를 반환하는 메서드입니다.
     *
     * @param list 레스토랑 목록
     * @return 폐업한 레스토랑의 수
     */
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
