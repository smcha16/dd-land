package com.project.dd.activity.festival.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.festival.service.FestivalService;

@RestController
public class RestFestivalController {

	@Autowired
	private FestivalService service;
	
	@PostMapping(value = "/admin/activity/festival/location")
	public int checkLocation(@RequestBody FestivalDTO dto) {

		return service.checkLocationDuplication(dto);
	}
	
}
