package com.project.dd.activity.theater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.activity.theater.domain.TheaterDTO;
import com.project.dd.activity.theater.service.TheaterService;

@RestController
public class RestTheaterController {

	@Autowired
	private TheaterService service;
	
	@PostMapping(value = "/admin/activity/theater/location")
	public int checkLocation(@RequestBody TheaterDTO dto) {
	
		return service.checkLocationDuplication(dto);
	}
	
	@PostMapping(value = "/admin/activity/theater/name")
	public int checkName(@RequestBody TheaterDTO dto) {

		return service.checkNameDuplication(dto);
	}
}
