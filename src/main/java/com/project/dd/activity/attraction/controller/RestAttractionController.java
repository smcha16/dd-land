package com.project.dd.activity.attraction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.BookUserDTO;
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
	
	@PostMapping(value = "/activity/attraction/reservation")
	public int checkCapacity(@RequestBody BookUserDTO dto) {

		//시간대별 배정된 예약 인원 제한(dto.attraction_book_seq)
		int capacity = service.getAttractionBookCapacity(dto);
		
		//실제 예약된 인원
		int reserved = service.checkAvailableCapacity(dto);
		
		System.out.println("capacity: " + capacity);
		System.out.println("reserved: " + reserved);
		
		//예약 가능 인원 반환
		return capacity - reserved; 
	}

}
