package com.project.dd.activity.attraction.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;

public interface AttractionDAO {

	List<AttractionDTO> getAttractionList(Map<String, String> map);

	AttractionDTO getAttraction(String seq);

	List<AttractionImgDTO> getAttractionImgList(String seq);

	int getTotalCount();

	int checkLocationDuplication(AttractionDTO dto);

	int addAttraction(AttractionDTO dto);

	int delAttraction(String seq);

	int countAttractionImg(String seq);

	int delAttractionImg(String seq);

	int delAttractionLocation(String seq);

	int countAttractionLocation(String seq);

	int checkNameDuplication(AttractionDTO dto);

}
