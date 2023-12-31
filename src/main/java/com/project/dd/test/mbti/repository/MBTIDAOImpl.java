package com.project.dd.test.mbti.repository;

import java.util.List;
import java.util.Map;

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
	public int getTotalCount() {
		return mapper.getTotalCount();
	}
	
	@Override
	public List<MBTIDTO> getAllMBTI(Map<String, String> map) {
		return mapper.getAllMBTI(map);
	}
	
	@Override
	public MBTIDTO getMBTI(String seq) {
		return mapper.getMBTI(seq);
	}
	
	@Override
	public int addMBTI(MBTIDTO dto) {
		return mapper.addMBTI(dto);
	}
	
	@Override
	public int checkMBTINameDuplication(MBTIDTO dto) {
		return mapper.checkMBTINameDuplication(dto);
	}
	
	@Override
	public int editMBTI(MBTIDTO dto) {
		return mapper.editMBTI(dto);
	}
	
	@Override
	public String getMBTIImgFileName(String mbtiSeq) {
		return mapper.getMBTIImgFileName(mbtiSeq);
	}

	@Override
	public int delMBTI(String seq) {
		return mapper.delMBTI(seq);
	}
	
}
