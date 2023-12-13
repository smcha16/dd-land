package com.project.dd.activity.theater.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.theater.domain.TheaterDTO;
import com.project.dd.activity.theater.mapper.TheaterMapper;

@Primary
@Repository
public class TheaterDAOImpl implements TheaterDAO {

	@Autowired
	TheaterMapper mapper;

	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}

	@Override
	public List<TheaterDTO> getTheaterList(Map<String, String> map) {
		return mapper.getTheaterList(map);
	}

	@Override
	public int addTheater(TheaterDTO dto) {
		return mapper.addTheater(dto);
	}

	@Override
	public TheaterDTO getTheater(String seq) {
		return mapper.getTheater(seq);
	}
}
