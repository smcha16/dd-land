package com.project.dd.shop.restaurant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.service.RestaurantService;

/**
 * 레스토랑 관련 RESTful API를 처리하는 컨트롤러 클래스입니다.
 * @author pega0
 *
 */
@RestController
public class RestRestaurantController {

	@Autowired
	private RestaurantService service;
	
	/**
     * 레스토랑 이름 중복을 체크하는 메서드입니다.
     *
     * @param dto 레스토랑 정보 DTO
     * @return 중복 여부에 따른 결과값 (1: 중복, 0: 중복 아님)
     */
	@PostMapping(value = "/admin/shop/restaurant/name")
	public int checkName(@RequestBody RestaurantDTO dto) {

		return service.checkNameDuplication(dto);
	}
	
}
