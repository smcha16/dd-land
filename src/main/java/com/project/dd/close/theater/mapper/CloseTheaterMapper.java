package com.project.dd.close.theater.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.close.theater.domain.CloseTheaterDTO;

public interface CloseTheaterMapper {

	int getTotalCount();

	List<CloseTheaterDTO> list(Map<String, String> map);

	List<CloseTheaterDTO> theaterlist();

	int addCloseTheater(CloseTheaterDTO dto);

	CloseTheaterDTO getOne(String seq);

	int editClose(CloseTheaterDTO dto);

	void del(String seq);

}
