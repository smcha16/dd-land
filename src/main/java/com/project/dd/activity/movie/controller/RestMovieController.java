package com.project.dd.activity.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movie.service.MovieService;

@RestController
public class RestMovieController {

	@Autowired
	private MovieService service;
	
	//영화명 중복 확인(SELECT > GET)
	//1. 주소: http://localhost:8090/dd/admin/activity/movie
	//2. 행동(Method): POST
	//3. 반환값(Return): int(가능: 0, 불가: 1 이상)
	
	@PostMapping(value = "/admin/activity/movie")
	public int checkMovieNameDuplication(@RequestBody MovieDTO dto) {

		System.out.println(dto.toString());
		System.out.println(dto.getName() != null);
		
		return service.checkMovieNameDuplication(dto);
	}
	
	
	
}