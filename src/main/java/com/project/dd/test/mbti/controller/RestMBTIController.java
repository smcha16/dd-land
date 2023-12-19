package com.project.dd.test.mbti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.test.mbti.domain.MBTIDTO;
import com.project.dd.test.mbti.service.MBTIService;

/**
 * MBTI 테스트의 관리와 관련된 RESTful API를 제공하는 컨트롤러입니다.
 * 
 * 1. MBTI 테스트 이름 중복 체크
 * 
 * @author 이승원
 */
@RestController
@RequestMapping("/admin/test/mbti")
public class RestMBTIController {

    @Autowired
    private MBTIService mbtiService;

    /**
	 * 입력된 MBTI 테스트 이름이 중복되는지 체크하고 결과를 반환합니다.
	 * 
	 * @param dto MBTI 테스트 정보를 담고 있는 DTO 
     * @return    MBTI 이름 중복 여부를 나타내는 정수 값
	 */
	@PostMapping(value = "/name", produces = "application/json")
	public int checkMBTIName(@RequestBody MBTIDTO dto) {
		
		return mbtiService.checkMBTINameDuplication(dto);
	}
	
}
