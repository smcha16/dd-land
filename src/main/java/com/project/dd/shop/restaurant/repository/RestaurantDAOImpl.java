package com.project.dd.shop.restaurant.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.domain.RestaurantImageDTO;
import com.project.dd.shop.restaurant.mapper.RestaurantMapper;

/**
 * 레스토랑 관련 데이터베이스 처리를 위한 DAO 구현 클래스입니다.
 * @author pega0
 *
 */
@Repository
@Primary
public class RestaurantDAOImpl implements RestaurantDAO {
	
	@Autowired
	private RestaurantMapper mapper;
	
	/**
     * 레스토랑 목록을 조회하는 메서드입니다.
     *
     * @param map 조회 조건을 담은 Map 객체
     * @return 레스토랑 목록
     */
	@Override
	public List<RestaurantDTO> restaurantList(Map<String, String> map) {
		return mapper.list(map);
	}
	
	/**
     * 레스토랑 상세 정보를 조회하는 메서드입니다.
     *
     * @param seq 레스토랑 일련번호
     * @return 레스토랑 상세 정보
     */
	@Override
	public RestaurantDTO restaurantDetail(String seq) {
		return mapper.detail(seq);
	}
	
	/**
     * 레스토랑 이미지 목록을 조회하는 메서드입니다.
     *
     * @param seq 레스토랑 일련번호
     * @return 레스토랑 이미지 목록
     */
	@Override
	public List<RestaurantImageDTO> restaurantImg(String seq) {
		return mapper.image(seq);
	}
	
	/**
     * 전체 레스토랑 수를 조회하는 메서드입니다.
     *
     * @param map 조회 조건을 담은 Map 객체
     * @return 전체 레스토랑 수
     */
	@Override
	public int getTotalCount(Map<String, String> map) {
		return mapper.getTotalCount(map);
	}
	
	/**
     * 레스토랑 이미지 목록을 조회하는 메서드입니다.
     *
     * @return 레스토랑 이미지 목록
     */
	@Override
	public List<RestaurantImageDTO> getImgList() {
		return mapper.getImgList();
	}
	
	/**
     * 레스토랑 이름 중복을 체크하는 메서드입니다.
     *
     * @param dto 레스토랑 정보 객체
     * @return 중복 여부 (1: 중복, 0: 미중복)
     */
	@Override
	public int checkNameDuplication(RestaurantDTO dto) {
		return mapper.checkNameDuplication(dto);
	}
	
	/**
     * 레스토랑을 추가하는 메서드입니다.
     *
     * @param dto 추가할 레스토랑 정보 객체
     * @return 추가된 레스토랑 수
     */
	@Override
	public int addRestaurant(RestaurantDTO dto) {
		return mapper.addRestaurant(dto);
	}
	
	/**
     * 최근 추가된 레스토랑의 일련번호를 조회하는 메서드입니다.
     *
     * @return 최근 추가된 레스토랑의 일련번호
     */
	@Override
	public String getSeq() {
		return mapper.getSeq();
	}
	
	/**
     * 레스토랑 위치 정보를 추가하는 메서드입니다.
     *
     * @param dto 레스토랑 정보 객체
     */
	@Override
	public void addRestaurantLocation(RestaurantDTO dto) {
		mapper.addRestaurantLocation(dto);
	}
	
	/**
     * 레스토랑 이미지를 추가하는 메서드입니다.
     *
     * @param dto 레스토랑 정보 객체
     */
	@Override
	public void addRestaurantImg(RestaurantDTO dto) {
		mapper.addRestaurantImg(dto);
	}
	
	/**
     * 레스토랑을 삭제하는 메서드입니다.
     *
     * @param seq 삭제할 레스토랑 일련번호
     * @return 삭제된 레스토랑 수
     */
	@Override
	public int delRestaurant(String seq) {
		return mapper.delRestaurant(seq);
	}

}
