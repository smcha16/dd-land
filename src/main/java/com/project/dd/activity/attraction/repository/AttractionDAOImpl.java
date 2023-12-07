package com.project.dd.activity.attraction.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.mapper.AttractionMapper;

@Primary
@Repository
public class AttractionDAOImpl implements AttractionDAO {

	@Autowired
	AttractionMapper mapper;

	@Override
	public List<AttractionDTO> getAttractionList() {
		return mapper.getAttractionList();
	}

	@Override
	public AttractionDTO getAttraction(String seq) {
		return mapper.getAttraction(seq);
	}

	@Override
	public List<AttractionImgDTO> getAttractionImgList(String seq) {
		return mapper.getAttractionImgList(seq);
	}

}
