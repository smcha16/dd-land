package com.project.dd.mypage.voc.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.mypage.voc.domain.VOCDTO;

public interface MypageVOCDAO {

	List<VOCDTO> list(Map<String, String> map);

	int getTotalCount();

	int delete(String selectedVOC);

}
