package com.project.dd.guide.repository;

import java.util.List;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.theater.domain.TheaterDTO;
import com.project.dd.guide.domain.ConvenientDTO;
import com.project.dd.shop.giftshop.domain.ShopDTO;
import com.project.dd.shop.restaurant.domain.RestaurantDTO;

public interface GuideMapDAO {

	List<AttractionDTO> attrlist();

	List<RestaurantDTO> foodlist();

	List<ConvenientDTO> convlist();

	List<ShopDTO> giftlist();

	List<TheaterDTO> movielist();

	List<PhotoZoneDTO> photolist();

	List<FestivalDTO> festlist();



}
