package com.project.dd.activity.attraction.mapper;

import java.util.List;

import com.project.dd.activity.attraction.domain.AttractionDTO;

public interface AttractionMapper {

	//(금일 기준) 운영 어트랙션 List
	List<AttractionDTO> list(String close);
	
}
