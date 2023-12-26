package com.project.dd.activity.photozone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.photozone.service.PhotoZoneService;

/**
 * 
 * 포토존 추가 시, 유효성 검사를 담당하는 ajax 처리 서버 컨트롤러 클래스입니다.
 * 
 * @author 박나래
 *
 */
@RestController
public class RestPhotozoneController {

	@Autowired
	private PhotoZoneService service;
	
	/**
	 * 
	 * 입력받은 위치 정보를 DB에서 중복여부를 확인하고 처리 결과에 대한 값을 반환하는 메서드입니다.
	 * 
	 * @param dto 포토존 dto 객체
	 * @return 중복된 레코드의 개수
	 */
	@PostMapping(value = "/admin/activity/photozone/location")
	public int checkLocation(@RequestBody PhotoZoneDTO dto) {

		return service.checkLocationDuplication(dto);
	}
	
	/**
	 * 
	 * 입력받은 포토존명을 DB에서 중복여부를 확인하고 처리 결과에 대한 값을 반환하는 메서드입니다.
	 * 
	 * @param dto 포토존 dto 객체
	 * @return 중복된 레코드의 개수
	 */
	@PostMapping(value = "/admin/activity/photozone/name")
	public int checkName(@RequestBody PhotoZoneDTO dto) {
		
		return service.checkNameDuplication(dto);
	}
}
