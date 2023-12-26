package com.project.dd.guide.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.guide.domain.ConvenientDTO;
import com.project.dd.guide.repository.GuideMapDAO;
import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.theater.domain.TheaterDTO;
import com.project.dd.shop.giftshop.domain.ShopDTO;
import com.project.dd.shop.restaurant.domain.RestaurantDTO;

/**
 * use-guide와 관련된 정보를 처리하는 Service 클래스
 * @author leeje
 *
 */

@Service
public class GuideMapService {

	@Autowired
	private GuideMapDAO mapDao;

	/**
	 * 어트랙션 목록을 조회하는 메서드
	 * @return 어트랙션 목록
	 */
	public List<AttractionDTO> getAttrList() {
		return mapDao.attrlist();
	}

	/**
     * 음식점 목록을 조회하는 메서드
     * 
     * @return 음식점 목록
     */
	public List<RestaurantDTO> getFoodList() {
		return mapDao.foodlist();
	}
	
	/**
     * 편의시설 목록을 조회하는 메서드
     * 
     * @return 편의시설 목록
     */
	public List<ConvenientDTO> getConvList() {
		return mapDao.convlist();
	}

	/**
     * 기프트샵 목록을 조회하는 메서드
     * 
     * @return 기프트샵 목록
     */
	public List<ShopDTO> getGiftList() {
		return mapDao.giftlist();
	}

	/**
     * 영화관 목록을 조회하는 메서드
     * 
     * @return 영화관 목록
     */
	public List<TheaterDTO> getMovieList() {
		return mapDao.movielist();
	}

	/**
     * 포토존 목록을 조회하는 메서드
     * 
     * @return 포토존 목록
     */
	public List<PhotoZoneDTO> getPhotoList() {
		return mapDao.photolist();
	}

	/**
     * 페스티벌 목록을 조회하는 메서드
     * 
     * @return 페스티벌
     */
	public List<FestivalDTO> getFestList() {
		return mapDao.festlist();
	}
	
}
