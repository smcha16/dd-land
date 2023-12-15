package com.project.dd.close.theater.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.close.theater.domain.CloseTheaterDTO;

public interface CloseTheaterDAO {

	int getTotalCount();

	List<CloseTheaterDTO> list(Map<String, String> map);

	List<CloseTheaterDTO> theaterlist();

	int addCloseTheater(CloseTheaterDTO dto);

	CloseTheaterDTO getOne(String seq);

	int editClose(CloseTheaterDTO dto);

	void del(String seq);

}
