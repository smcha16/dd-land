package com.project.dd.mypage.attraction.repositorty;

import java.util.List;
import java.util.Map;

import com.project.dd.mypage.attraction.domain.AttractionDTO;

public interface MypageAttractionDAO {

	List<AttractionDTO> list(Map<String, String> map);

	int getTotalCount();

	List<AttractionDTO> plist(Map<String, String> map);

	int delete(String seq);

}
