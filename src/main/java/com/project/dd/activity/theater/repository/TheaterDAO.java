package com.project.dd.activity.theater.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.theater.domain.TheaterDTO;

public interface TheaterDAO {

	int getTotalCount();

	List<TheaterDTO> getTheaterList(Map<String, String> map);

	int addTheater(TheaterDTO dto);

	TheaterDTO getTheater(String seq);

	int checkLocationDuplication(TheaterDTO dto);

	int checkNameDuplication(TheaterDTO dto);

	int editTheater(TheaterDTO dto);

	int editTheaterLocation(TheaterDTO dto);

	int countTheaterLocation(String seq);

	int delTheaterLocation(String seq);

	int delTheater(String seq);

	String getTheaterSeq();

	int addTheaterLocation(TheaterDTO dto);

}
