package com.project.dd.activity.attraction.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.mapper.AttractionMapper;

@Primary
@Repository
public class AttractionDAOImpl implements AttractionDAO {

	@Autowired
	AttractionMapper mapper;

	@Override
	public List<AttractionDTO> getAttractionList(Map<String, String> map) {
		return mapper.getAttractionList(map);
	}

	@Override
	public AttractionDTO getAttraction(String seq) {
		return mapper.getAttraction(seq);
	}

	@Override
	public List<AttractionImgDTO> getAttractionImgList(String seq) {
		return mapper.getAttractionImgList(seq);
	}

	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}

	@Override
	public int checkLocationDuplication(AttractionDTO dto) {
		return mapper.checkLocationDuplication(dto);
	}

	//어트랙션 추가
	// - 1. tblAttraction 추가
	// - 2. tblAttractionLocation 추가
	// - 3. tblAttractionImg 추가
	@Override
	public int addAttraction(AttractionDTO dto) {
		
		//1. tblAttraction 추가
		int result = mapper.addAttraction(dto);
		
		//방금 등록한 Attraction seq 가져오기 > 근데 만약 추가 못하면? if 절로 result 조건 감싸야하나...? 고민해보기
		int seq = mapper.getAttractionSeq();
		
		dto.setAttraction_seq(seq + "");
		
		//2. tblAttractionLocation 추가
		result = mapper.addAttractionLocation(dto);
		
		//3. tblAttractionImg 추가
		for (AttractionImgDTO idto : dto.getImgList()) {
			
			idto.setAttraction_seq(seq + "");
			
			result += mapper.addAttractionImg(idto);
		}
		
		return result;
	}

}
