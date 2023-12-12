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

@Service
public class GuideMapService {

	@Autowired
	private GuideMapDAO mapDao;

	public List<AttractionDTO> getAttrList() {
		return mapDao.attrlist();
	}

	public List<RestaurantDTO> getFoodList() {
		return mapDao.foodlist();
	}

	public List<ConvenientDTO> getConvList() {
		return mapDao.convlist();
	}

	public List<ShopDTO> getGiftList() {
		return mapDao.giftlist();
	}

	public List<TheaterDTO> getMovieList() {
		return mapDao.movielist();
	}

	public List<PhotoZoneDTO> getPhotoList() {
		return mapDao.photolist();
	}

	public List<FestivalDTO> getFestList() {
		return mapDao.festlist();
	}
	
	
	


}
