package com.project.dd.close.attraction.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.close.attraction.domain.CloseAttractionDTO;
import com.project.dd.close.attraction.mapper.CloseAttractionMapper;

@Primary
@Repository
public class CloseAttractionDAOImpl implements CloseAttractionDAO{

	@Autowired
	private CloseAttractionMapper mapper;
	
	@Override
	public List<CloseAttractionDTO> list(Map<String, String> map) {
		return mapper.list(map);
	}

	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}

	@Override
	public void del(String seq) {
		mapper.delCloseAttr(seq);
	}

	@Override
	public List<AttractionDTO> attlist() {
		return mapper.attlist();
	}

}
