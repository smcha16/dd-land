package com.project.dd.activity.attraction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.service.AttractionService;

@RestController
public class RestAttractionController {

	@Autowired
	private AttractionService service;
	
	@PostMapping(value = "/admin/activity/attraction/location")
	public int checkLocation(@RequestBody AttractionDTO dto) {
		
		return service.checkLocationDuplication(dto);
	}

	@PostMapping(value = "/admin/activity/attraction/name")
	public int checkName(@RequestBody AttractionDTO dto) {
		
		return service.checkNameDuplication(dto);
	}

}