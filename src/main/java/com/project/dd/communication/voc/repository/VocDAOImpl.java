package com.project.dd.communication.voc.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.communication.voc.domain.VocDTO;
import com.project.dd.communication.voc.mapper.VocMapper;

@Primary
@Repository
public class VocDAOImpl implements VocDAO {
	
	@Autowired
	private VocMapper mapper;
	
	@Override
	public List<String> getVisitDateList(String email) {
		
		return mapper.getVisitDateList(email);
		
	}

	@Override
	public int addVoc(VocDTO dto) {
		
		return mapper.addVoc(dto);
		
	}

}
