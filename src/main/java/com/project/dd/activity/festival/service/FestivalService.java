package com.project.dd.activity.festival.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.festival.domain.FestivalImgDTO;
import com.project.dd.activity.festival.repository.FestivalDAO;

@Service
public class FestivalService {

	@Autowired
	FestivalDAO dao;

	public List<FestivalDTO> getFestivalList(String date) {
		return dao.getFestivalList(date);
	}

	public FestivalDTO getFestival(String seq) {
		return dao.getFestival(seq);
	}

	public List<FestivalImgDTO> getFestivalImgList(String seq) {
		return dao.getFestivalImgList(seq);
	}
}
