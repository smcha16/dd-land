package com.project.dd.activity.theater.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.theater.domain.TheaterDTO;

public interface TheaterMapper {

	int getTotalCount();

	List<TheaterDTO> getTheaterList(Map<String, String> map);

	int addTheater(TheaterDTO dto);

	TheaterDTO getTheater(String seq);

}
