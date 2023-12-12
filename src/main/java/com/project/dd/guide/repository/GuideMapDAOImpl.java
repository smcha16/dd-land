package com.project.dd.guide.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.theater.domain.TheaterDTO;
import com.project.dd.guide.domain.ConvenientDTO;
import com.project.dd.guide.mapper.GuideMapper;
import com.project.dd.shop.giftshop.domain.ShopDTO;
import com.project.dd.shop.restaurant.domain.RestaurantDTO;

@Primary
@Repository
public class GuideMapDAOImpl implements GuideMapDAO{

	@Autowired
	private GuideMapper mapper;
	
	@Override
	public List<AttractionDTO> attrlist() {
		return mapper.getAttrList();
	}

	@Override
	public List<RestaurantDTO> foodlist() {
		return mapper.getFoodList();
	}

	@Override
	public List<ConvenientDTO> convlist() {
		return mapper.getConvList();
	}

	@Override
	public List<ShopDTO> giftlist() {
		return mapper.getGiftList();
	}

	@Override
	public List<TheaterDTO> movielist() {
		return mapper.getMovieList();
	}

	@Override
	public List<PhotoZoneDTO> photolist() {
		return mapper.getPhotoList();
	}

	@Override
	public List<FestivalDTO> festlist() {
		return mapper.getFestList();
	}

	
}
