package com.project.dd.activity.photozone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.photozone.service.PhotoZoneService;

@RestController
public class RestPhotozoneController {

	@Autowired
	private PhotoZoneService service;
	
	@PostMapping(value = "/admin/activity/photozone/location")
	public int checkLocation(@RequestBody PhotoZoneDTO dto) {

		return service.checkLocationDuplication(dto);
	}
	
	@PostMapping(value = "/admin/activity/photozone/name")
	public int checkName(@RequestBody PhotoZoneDTO dto) {
		
		return service.checkNameDuplication(dto);
	}
}
