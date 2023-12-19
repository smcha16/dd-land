package com.project.dd.shop.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.service.RestaurantService;

@RestController
public class RestRestaurantController {

	@Autowired
	private RestaurantService service;
	
	@PostMapping(value = "/admin/shop/restaurant/name")
	public int checkName(@RequestBody RestaurantDTO dto) {

		return service.checkNameDuplication(dto);
	}
	
}
