package com.project.dd.guide.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.guide.domain.ConvenientDTO;

public interface ConvenientDAO {

	int getTotalCount();
	
	List<ConvenientDTO> list(Map<String, String> map);

	ConvenientDTO one(String seq);
	

}
