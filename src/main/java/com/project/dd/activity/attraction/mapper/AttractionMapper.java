package com.project.dd.activity.attraction.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;

public interface AttractionMapper {

	//(운영종료 제외) 운영/운휴 어트랙션 List
	List<AttractionDTO> getAttractionList(Map<String, String> map);

	//어트랙션 상세 정보
	AttractionDTO getAttraction(String seq);
	
	//어트랙션 이미지 List
	List<AttractionImgDTO> getAttractionImgList(String seq);

	int getTotalCount();

	int checkLocationDuplication(AttractionDTO dto);

	int addAttraction(AttractionDTO dto);

	int getAttractionSeq();

	int addAttractionLocation(AttractionDTO dto);

	int addAttractionImg(AttractionImgDTO idto);

	int delAttraction(String seq);

	int countAttractionImg(String seq);

	int delAttractionImg(String seq);

	int delAttractionLocation(String seq);

	int countAttractionLocation(String seq);

	int checkNameDuplication(AttractionDTO dto);

	String getAttractionDefaultImgName(String seq);

	int editAttractionLocation(AttractionDTO dto);

	int editAttraction(AttractionDTO dto);

	int delAttractionImgByImgSeq(String imgseq);

	List<AttractionImgDTO> getAllAttractionImgList();

}
