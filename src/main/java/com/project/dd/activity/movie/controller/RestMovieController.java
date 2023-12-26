package com.project.dd.activity.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movie.service.MovieService;

/**
 * 
 * 영화 추가 시, 유효성 검사를 담당하는 ajax 처리 서버 컨트롤러 클래스입니다.
 * 
 * @author 박나래
 *
 */
@RestController
public class RestMovieController {

	@Autowired
	private MovieService service;
	
	//영화명 중복 확인(SELECT > GET)
	//1. 주소: http://localhost:8090/dd/admin/activity/movie
	//2. 행동(Method): POST
	//3. 반환값(Return): int(가능: 0, 불가: 1 이상)
	
	/**
	 * 
	 * 입력받은 영화명을 DB에서 중복여부를 확인하고 처리 결과에 대한 값을 반환하는 메서드입니다.
	 * 
	 * @param dto 영화 dto 객체
	 * @return 중복된 레코드의 개수
	 */
	@PostMapping(value = "/admin/activity/movie")
	public int checkMovieNameDuplication(@RequestBody MovieDTO dto) {

		System.out.println(dto.toString());
		System.out.println(dto.getName() != null);
		
		return service.checkMovieNameDuplication(dto);
	}
	
	
	
}