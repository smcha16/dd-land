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
	public int getTotalCount(Map<String, String> map) {
		return mapper.getTotalCount(map);
	}

	@Override
	public List<ConvenientDTO> list(Map<String, String> map) {
		
		return mapper.list(map);
	}

	@Override
	public ConvenientDTO one(String seq) {
		return mapper.one(seq);
	}

	@Override
	public int checkLocationDuplication(ConvenientDTO dto) {
		
		return mapper.checkLocationDuplication(dto);
	}

	@Override
	public int checkNameDuplication(ConvenientDTO dto) {
		
		return mapper.checkNameDuplication(dto);
	}
	
	@Override
	public int checkTelDuplication(ConvenientDTO dto) {
		
		return mapper.checkTelDuplication(dto);
	}

	@Override
	public int addConv(ConvenientDTO dto) {
		
		return mapper.addConv(dto);
	}

	@Override
	public String getConvSeq() {
		
		return mapper.getConvSeq();
	}

	@Override
	public int addConvLocation(ConvenientDTO dto) {
		
		return mapper.addConvLocation(dto);
	}

	@Override
	public int countConvenientLocation(String seq) {
		
		return mapper.countConvenientLocation(seq);
	}

	@Override
	public int delConvenientLocation(String seq) {
		return mapper.delConvenientLocation(seq);
	}

	@Override
	public int delConvenient(String seq) {
		
		return mapper.delConvenient(seq);
	}

	@Override
	public String getFileName(String convenient_seq) {
		
		return mapper.getFileName(convenient_seq);
	}

	@Override
	public int editConv(ConvenientDTO convenient) {
		
		return mapper.editConv(convenient);
	}

	@Override
	public int editConvLocation(ConvenientDTO convenient) {
		
		return mapper.editConvLocation(convenient);
	}

	

	

	
	
}
