package com.project.dd.activity.festival.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.festival.domain.FestivalImgDTO;
import com.project.dd.activity.festival.mapper.FestivalMapper;

@Primary
@Repository
public class FestivalDAOImpl implements FestivalDAO {

	@Autowired
	FestivalMapper mapper;

	@Override
	public List<FestivalDTO> getFestivalList(String date) {
		return mapper.getFestivalList(date);
	}

	@Override
	public FestivalDTO getFestival(String seq) {
		return mapper.getFestival(seq);
	}

	@Override
	public List<FestivalImgDTO> getFestivalImgList(String seq) {
		return mapper.getFestivalImgList(seq);
	}
}
