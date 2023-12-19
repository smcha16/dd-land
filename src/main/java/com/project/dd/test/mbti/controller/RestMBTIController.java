package com.project.dd.test.mbti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.test.mbti.domain.MBTIDTO;
import com.project.dd.test.mbti.service.MBTIService;

@RestController
public class RestMBTIController {

    @Autowired
    private MBTIService mbtiService;

	@PostMapping(value = "/admin/test/mbti/name", produces = "application/json")
	public int checkMBTIName(@RequestBody MBTIDTO dto) {
		
		return mbtiService.checkMBTINameDuplication(dto);
	}
	
}
