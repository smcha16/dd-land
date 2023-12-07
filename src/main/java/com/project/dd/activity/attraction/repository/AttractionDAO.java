package com.project.dd.activity.attraction.repository;

import java.util.List;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;

public interface AttractionDAO {

	List<AttractionDTO> getAttractionList(String close);

	AttractionDTO getAttraction(String seq);

	List<AttractionImgDTO> getAttractionImgList(String seq);

}
