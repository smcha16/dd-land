package com.project.dd.communication.faq.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.communication.faq.domain.FaqDTO;
import com.project.dd.communication.faq.mapper.FaqMapper;

@Primary
@Repository
public class FaqDAOImpl implements FaqDAO {
	
	@Autowired
	private FaqMapper mapper;
	
	@Override
	public int getTotalCount(String type) {

		return mapper.getTotalCount(type);

	}

	@Override
	public List<FaqDTO> getFaqList(Map<String, String> map) {
		
		return mapper.getFaqList(map);
		
	}

}
