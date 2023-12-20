package com.project.dd.test.mbti.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.test.mbti.domain.MBTIDTO;

public interface MBTIDAO {

	int getTotalCount();
	
    List<MBTIDTO> getAllMBTI(Map<String, String> map);

	MBTIDTO getMBTI(String seq);

	int addMBTI(MBTIDTO dto);

	int checkMBTINameDuplication(MBTIDTO dto);

	int editMBTI(MBTIDTO dto);

	String getMBTIImgFileName(String mbtiSeq);

	int delMBTI(String seq);

}
