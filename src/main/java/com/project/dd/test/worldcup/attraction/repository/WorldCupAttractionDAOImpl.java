package com.project.dd.test.worldcup.attraction.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.attraction.mapper.WorldCupAttractionMapper;

@Repository
@Primary
public class WorldCupAttractionDAOImpl implements WorldCupAttractionDAO {

	@Autowired
	private WorldCupAttractionMapper mapper;

	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}
	
	@Override
	public int getTestCount() {
		return mapper.getTestCount();
	}
	
	// 모든 어트랙션 리스트 가져오기
	@Override
	public List<AttractionDTO> getAllAttraction(Map<String, String> map) {
		return mapper.getAllAttraction(map);
	}
	
	@Override
	public List<AttractionDTO> getAttractionList() {
		return mapper.getAttractionList();
	}

	@Override
	public void updateAttractionStatus(Map<String, String> map) {
		mapper.updateAttractionStatus(map);
	}
	
	@Override
	public int getAWCFinalWinTotalCount() {
		return mapper.getAWCFinalWinTotalCount();
	}

	@Override
	public int addAWC(AttractionDTO dto) {
		return mapper.addAWC(dto);
	}
	
	@Override
	public int addAWCWin(AttractionDTO dto) {
		return mapper.addAWCWin(dto);
	}
	
	@Override
	public int addAWCFinalWin(AttractionDTO dto) {
		return mapper.addAWCFinalWin(dto);
	}

	@Override
	public void updateAWCMatchCount(String attractionSeq) {
		mapper.updateAWCMatchCount(attractionSeq);
	}
	
	@Override
	public void updateAWCWinCount(String attractionSeq) {
		mapper.updateAWCWinCount(attractionSeq);
	}
	
	@Override
	public void updateAWCFinalWinCount(String attractionSeq) {
		mapper.updateAWCFinalWinCount(attractionSeq);
	}
	
	@Override
	public List<AttractionDTO> getAttractionNameList() {
		return mapper.getAttractionNameList();
	}
	
}