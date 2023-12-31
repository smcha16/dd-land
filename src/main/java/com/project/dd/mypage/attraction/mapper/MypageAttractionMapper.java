package com.project.dd.mypage.attraction.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.mypage.attraction.domain.AttractionDTO;

public interface MypageAttractionMapper {

	List<AttractionDTO> list(Map<String, String> map);

	int delete(String seq);
	
	int getTotalCount(String email);

	List<AttractionDTO> plist(Map<String, String> map);

	int pGetTotalCount(String email);
	
	

}
