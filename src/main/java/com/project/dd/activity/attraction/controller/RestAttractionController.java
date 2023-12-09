package com.project.dd.activity.attraction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.service.AttractionService;

@RestController
public class RestAttractionController {

	@Autowired
	private AttractionService service;
	
	@GetMapping(value = "/dd/admin/activity/attraction/add")
	public int checkLocation(@RequestBody String lat, @RequestBody String lng) {
		
		System.out.println(lat);
		System.out.println(lng);
		
		AttractionDTO dto = new AttractionDTO();
		
		//일단 임시로 만들고 나중에는 String lat, lng으로 보내기
		dto.setLat(lat);
		dto.setLng(lng);
		
		
		
		return service.checkLocationDuplication(dto);
	}
}
