package com.project.dd.guide.convenient.repository;

import java.util.List;

import com.project.dd.guide.convenient.domain.ConvenientDTO;

public interface ConvenientDAO {

	List<ConvenientDTO> list();

	ConvenientDTO one(String seq);

}
