package com.project.dd.test.mbti.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.test.mbti.domain.MBTIDTO;
import com.project.dd.test.worldcup.course.domain.CourseDTO;

@Service
public interface MBTIService {

	int getTotalCount();
	
	Map<String, String> paging(String solting, String searchStatus, String word, int page, int pageSize);
	
    List<MBTIDTO> getAllMBTI(Map<String, String> map);

	MBTIDTO getMBTI(String seq);

	int addMBTI(MBTIDTO dto, MultipartFile image, HttpServletRequest req);

	int checkMBTINameDuplication(MBTIDTO dto);

	int editMBTI(MBTIDTO dto, MultipartFile image, HttpServletRequest req);

	int delMBTI(String[] mbti_seq);

}