package com.project.dd.test.worldcup.attraction.mapper;

import java.util.List;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.attraction.domain.WorldCupAttractionDTO;

public interface WorldCupAttractionMapper {

	//월드컵에 등록된 운영중인 어트랙션 리스트 조회
	List<AttractionDTO> listAttraction(String close);
	
	//어트랙션 월드컵에 등록된 어트랙션 리스트 조회
	List<WorldCupAttractionDTO> listAWC(String isTest);
	
	//어트랙션 월드컵 승리 리스트 조회
	List<WorldCupAttractionDTO> listAWCWin();
	
	//어트랙션 월드컵 최종 승리 리스트 조회
	List<WorldCupAttractionDTO> listAWCFinalWin();
	
}
