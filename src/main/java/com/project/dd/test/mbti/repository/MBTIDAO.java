package com.project.dd.test.mbti.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.test.mbti.domain.MBTIDTO;

public interface MBTIDAO {

    // 페이징
	int getTotalCount();
	
    // 모든 MBTI별 추천 정보 가져오기
    List<MBTIDTO> getAllMBTI(Map<String, String> map);

	MBTIDTO getMBTI(String seq);
    
}
