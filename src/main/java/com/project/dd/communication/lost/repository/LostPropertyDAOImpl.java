package com.project.dd.communication.lost.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.communication.lost.domain.LostPropertyDTO;
import com.project.dd.communication.lost.mapper.LostPropertyMapper;

@Primary
@Repository
public class LostPropertyDAOImpl implements LostPropertyDAO {
	
	@Autowired
	private LostPropertyMapper mapper;
	
	// 총 개수

	@Override
	public int getTotalCount() {
		
		return mapper.getTotalCount();
		
	}
	
	// 목록

	@Override
	public List<LostPropertyDTO> getLostPropertyList(Map<String, String> map) {

		return mapper.getLostPropertyList(map);
		
	}
	
	// 추가

	@Override
	public int addLostProperty(LostPropertyDTO dto) {

		return mapper.addLostProperty(dto);
		
	}

}
