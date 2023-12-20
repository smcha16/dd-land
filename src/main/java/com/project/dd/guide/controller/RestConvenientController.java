package com.project.dd.guide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.guide.domain.ConvenientDTO;
import com.project.dd.guide.service.ConvenientService;

/**
 * 이름, 위치, 전화번호의 중복 유효성 검사를 하는 클래스
 * @author leeje
 *
 */

@RestController
public class RestConvenientController {  //편의시설 추가 및 수정시 이름, 위치, 전화번호 중복 확인
	
	@Autowired
	private ConvenientService service;
	
	/**
	 * 위치 중복 확인을 위한 RESTful API
	 * @param dto 확인할 편의시설 정보를 담은 ConvenientDTO 객체
	 * @return 중복 여부에 따른 결과값 (0: 중복 없음, 1: 중복)
	 */
	@PostMapping(value = "/admin/convenient/location")
	public int checkLocation(@RequestBody ConvenientDTO dto) {
		
		return service.checkLocationDuplication(dto);
	}

	/**
	 * 이름 중복 확인을 위한 RESTful API
	 * @param dto 확인할 편의시설 정보를 담은 ConvenientDTO 객체
	 * @return 중복 여부에 따른 결과값 (0: 중복 없음, 1: 중복)
	 */
	@PostMapping(value = "/admin/convenient/name")
	public int checkName(@RequestBody ConvenientDTO dto) {
		
		return service.checkNameDuplication(dto);
	}
	
	/**
	 * 전화번호 중복 확인을 위한 RESTful API
	 * @param dto 확인할 편의시설 정보를 담은 ConvenientDTO 객체
	 * @return 중복 여부에 따른 결과값 (0: 중복 없음, 1: 중복)
	 */
	@PostMapping(value = "/admin/convenient/tel")
	public int checkTel(@RequestBody ConvenientDTO dto) {
		
		return service.checkTelDuplication(dto);
	}

}
