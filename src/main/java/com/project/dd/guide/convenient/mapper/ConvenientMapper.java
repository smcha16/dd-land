package com.project.dd.guide.convenient.mapper;

import java.util.List;

import com.project.dd.guide.convenient.domain.ConvenientDTO;

public interface ConvenientMapper {

	List<ConvenientDTO> list();

	ConvenientDTO one(String seq);

}
