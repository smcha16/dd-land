package com.project.dd.activity.festival.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.festival.domain.FestivalImgDTO;

public interface FestivalDAO {

	List<FestivalDTO> getFestivalList(String date);

	FestivalDTO getFestival(String seq);

	List<FestivalImgDTO> getFestivalImgList(String seq);

	List<FestivalDTO> getAllFestivalList(Map<String, String> map);

	int getTotalCount(String solting);

	List<FestivalImgDTO> getAllFestivalImgList();

	int addFestival(FestivalDTO dto);

	String getFestivalSeq();

	int addFestivalLocation(FestivalDTO dto);

	int addFestivalImg(FestivalImgDTO idto);

	int countFestivalImg(String seq);

	int delFestivalImg(String seq);

	int countFestivalLocation(String seq);

	int delFestivalLocation(String seq);

	int delFestival(String seq);

	int editFestival(FestivalDTO dto);

	int editFestivalLocation(FestivalDTO dto);

	int delFestivalImgByImgSeq(String imgseq);

	int checkLocationDuplication(FestivalDTO dto);

	int getAdminPagingTotalPosts(Map<String, String> map);

}
