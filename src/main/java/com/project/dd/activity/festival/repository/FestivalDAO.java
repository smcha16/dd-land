package com.project.dd.activity.festival.repository;

import java.util.List;

import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.festival.domain.FestivalImgDTO;

public interface FestivalDAO {

	List<FestivalDTO> getFestivalList(String date);

	FestivalDTO getFestival(String seq);

	List<FestivalImgDTO> getFestivalImgList(String seq);

}
