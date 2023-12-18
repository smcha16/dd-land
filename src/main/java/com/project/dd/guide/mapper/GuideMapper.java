package com.project.dd.guide.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.theater.domain.TheaterDTO;
import com.project.dd.guide.domain.ConvenientDTO;
import com.project.dd.shop.giftshop.domain.ShopDTO;
import com.project.dd.shop.restaurant.domain.RestaurantDTO;

public interface GuideMapper {
	
	//편의시설

	List<ConvenientDTO> list(Map<String, String> map);

	ConvenientDTO one(String seq);

	int getTotalCount();
	
	//가이드맵

	List<AttractionDTO> getAttrList();

	List<RestaurantDTO> getFoodList();

	List<ConvenientDTO> getConvList();

	List<ShopDTO> getGiftList();

	List<TheaterDTO> getMovieList();

	List<PhotoZoneDTO> getPhotoList();

	List<FestivalDTO> getFestList();

	//관리자 편의시설 CRUD
	
	int checkLocationDuplication(ConvenientDTO dto);

	int checkNameDuplication(ConvenientDTO dto);
	
	int checkTelDuplication(ConvenientDTO dto);

	int addConv(ConvenientDTO dto);

	String getConvSeq();

	int addConvLocation(ConvenientDTO dto);

	int countConvenientLocation(String seq);

	int delConvenientLocation(String seq);

	int delConvenient(String seq);

	ConvenientDTO getConvenient(String seq);

	String getFileName(String convenient_seq);

	int editConv(ConvenientDTO convenient);

	int editConvLocation(ConvenientDTO convenient);

	

}
