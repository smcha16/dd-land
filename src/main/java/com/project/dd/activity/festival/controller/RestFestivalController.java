package com.project.dd.activity.festival.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.festival.service.FestivalService;

/**
 * 
 * 페스티벌 추가 시, 유효성 검사를 담당하는 ajax 처리 서버 컨트롤러 클래스입니다.
 * 
 * @author 박나래
 *
 */
@RestController
public class RestFestivalController {

	@Autowired
	private FestivalService service;
	
	/**
	 * 
	 * 입력받은 위치 정보를 DB에서 중복여부를 확인하고 처리 결과에 대한 값을 반환하는 메서드입니다.
	 * 
	 * @param dto 페스티벌 dto 객체
	 * @return 중복된 레코드의 개수
	 */
	@PostMapping(value = "/admin/activity/festival/location")
	public int checkLocation(@RequestBody FestivalDTO dto) {

		return service.checkLocationDuplication(dto);
	}
	
}
