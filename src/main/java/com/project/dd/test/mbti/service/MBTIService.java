package com.project.dd.test.mbti.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.test.mbti.domain.MBTIDTO;

@Service
public interface MBTIService {

	Map<String, String> paging(int page);

	int getTotalCount();
	
    List<MBTIDTO> getAllMBTI(Map<String, String> map);

	MBTIDTO getMBTI(String seq);

	int addMBTI(MBTIDTO dto, MultipartFile image, HttpServletRequest req);

	int checkMBTINameDuplication(MBTIDTO dto);

}