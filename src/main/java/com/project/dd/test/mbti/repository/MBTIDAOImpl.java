package com.project.dd.test.mbti.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.test.mbti.domain.MBTIDTO;
import com.project.dd.test.mbti.mapper.MBTIMapper;

@Repository
@Primary
public class MBTIDAOImpl implements MBTIDAO {

	@Autowired
	private MBTIMapper mapper;
	
	@Override
	public List<MBTIDTO> getAllMBTI() {

		return mapper.getAllMBTI();
	}
	
}
