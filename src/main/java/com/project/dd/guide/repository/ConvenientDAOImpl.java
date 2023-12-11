package com.project.dd.guide.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.guide.domain.ConvenientDTO;
import com.project.dd.guide.mapper.GuideMapper;

@Primary
@Repository
public class ConvenientDAOImpl implements ConvenientDAO{
	
	@Autowired
	private GuideMapper mapper;
	
	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}

	@Override
	public List<ConvenientDTO> list(Map<String, String> map) {
		
		return mapper.list(map);
	}

	@Override
	public ConvenientDTO one(String seq) {
		return mapper.one(seq);
	}

	
	
}
