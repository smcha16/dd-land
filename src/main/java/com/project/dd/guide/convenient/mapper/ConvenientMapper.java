package com.project.dd.guide.convenient.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.guide.convenient.domain.ConvenientDTO;

public interface ConvenientMapper {

	List<ConvenientDTO> list(Map<String, String> map);

	ConvenientDTO one(String seq);

	int getTotalCount();

	

}
