package com.project.dd.close.theater.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.close.theater.domain.CloseTheaterDTO;
import com.project.dd.close.theater.mapper.CloseTheaterMapper;

@Primary
@Repository
public class CloseTheaterDAOImpl implements CloseTheaterDAO {

	@Autowired
	private CloseTheaterMapper mapper;
	
	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}

	@Override
	public List<CloseTheaterDTO> list(Map<String, String> map) {
		
		return mapper.list(map);
	}

	@Override
	public List<CloseTheaterDTO> theaterlist() {
		
		return mapper.theaterlist();
	}

	@Override
	public int addCloseTheater(CloseTheaterDTO dto) {

		return mapper.addCloseTheater(dto);
	}

	@Override
	public CloseTheaterDTO getOne(String seq) {
		
		return mapper.getOne(seq);
	}

	@Override
	public int editClose(CloseTheaterDTO dto) {
		
		return mapper.editClose(dto);
	}

	@Override
	public void del(String seq) {
		mapper.del(seq);
	}

}
