package com.project.dd.activity.attraction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.BookUserDTO;
import com.project.dd.activity.attraction.service.AttractionService;

/**
 * 
 * @author 박나래
 * 
 * 어트랙션 추가 시, 유효성 검사를 담당하는 ajax 처리 서버 컨트롤러 클래스입니다.
 *
 */
@RestController
public class RestAttractionController {

	@Autowired
	private AttractionService service;
	
	/**
	 * 
	 * 입력받은 위치 정보를 DB에서 중복여부를 확인하고 처리 결과에 대한 값을 반환하는 메서드입니다.
	 * 
	 * @param dto 어트랙션 dto 객체
	 * @return 중복된 레코드의 개수
	 */
	@PostMapping(value = "/admin/activity/attraction/location")
	public int checkLocation(@RequestBody AttractionDTO dto) {
		
		return service.checkLocationDuplication(dto);
	}

	/**
	 * 
	 * 입력받은 어트랙션명을 DB에서 중복여부를 확인하고 처리 결과에 대한 값을 반환하는 메서드입니다.
	 * 
	 * @param dto 어트랙션 dto 객체
	 * @return 중복된 레코드의 개수
	 */
	@PostMapping(value = "/admin/activity/attraction/name")
	public int checkName(@RequestBody AttractionDTO dto) {
		
		return service.checkNameDuplication(dto);
	}
	
	/**
	 * 
	 * 선택한 시간대의 어트랙션 예약 가능 인원을 확인하고 처리 결과에 대한 값을 반환하는 메서드입니다.
	 * 
	 * @param dto 회원어트랙션예약 dto 객체
	 * @return 예약 가능 인원
	 */
	@PostMapping(value = "/activity/attraction/reservation")
	public int checkCapacity(@RequestBody BookUserDTO dto) {

		//시간대별 배정된 예약 인원 제한(dto.attraction_book_seq)
		int capacity = service.getAttractionBookCapacity(dto);
		
		//실제 예약된 인원
		int reserved = service.checkAvailableCapacity(dto);
		
//		System.out.println("capacity: " + capacity);
//		System.out.println("reserved: " + reserved);
		
		//예약 가능 인원 반환
		return capacity - reserved; 
	}
	
	@PostMapping(value = "/activity/attraction/ticket")
	public int checkTichet(@RequestBody BookUserDTO dto) {

		//System.out.println(dto.getUser_seq());
		
		return service.checkTicket(dto.getUser_seq());
	}

}
