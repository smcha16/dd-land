package com.project.dd.activity.attraction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.repository.AttractionDAO;

@Service
public class AttractionService {

	@Autowired
	AttractionDAO dao;

	public List<AttractionDTO> getAttractionList(String close) {
		return dao.getAttractionList(close);
	}

	public AttractionDTO getAttraction(String seq) {
		return dao.getAttraction(seq);
	}

	public List<AttractionImgDTO> getAttractionImgList(String seq) {
		return dao.getAttractionImgList(seq);
	}
}
