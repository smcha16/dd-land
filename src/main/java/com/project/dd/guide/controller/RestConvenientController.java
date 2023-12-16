package com.project.dd.guide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.guide.service.ConvenientService;

@RestController
public class RestConvenientController {  //편의시설 수정시 이름과 위치 중복 확인
	
	@Autowired
	private ConvenientService sevice;
	
	

}
