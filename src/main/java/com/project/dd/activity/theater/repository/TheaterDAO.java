package com.project.dd.activity.theater.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.theater.domain.TheaterDTO;

public interface TheaterDAO {

	int getTotalCount();

	List<TheaterDTO> getTheaterList(Map<String, String> map);

	int addTheater(TheaterDTO dto);

	TheaterDTO getTheater(String seq);

}
