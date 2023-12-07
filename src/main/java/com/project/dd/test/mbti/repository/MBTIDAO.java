package com.project.dd.test.mbti.repository;

import java.util.List;

import com.project.dd.test.mbti.domain.MBTIDTO;

public interface MBTIDAO {

    // 모든 MBTI별 추천 정보 가져오기
    List<MBTIDTO> getAllMBTI();
    
}
