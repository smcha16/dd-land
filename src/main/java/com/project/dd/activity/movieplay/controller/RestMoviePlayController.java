package com.project.dd.activity.movieplay.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.dd.activity.movieplay.domain.MoviePlayDTO;
import com.project.dd.activity.movieplay.service.MoviePlayService;

/**
 * 
 * 사용자 영화 상세 조회 화면에서 상영 영화관 선택 시, 해당하는 상영 정보 및 위치를 가져오는 ajax 처리 서버 컨트롤러 클래스입니다.
 * 
 * @author 박나래
 *
 */
@RestController
public class RestMoviePlayController {

	@Autowired
	private MoviePlayService service;
	
	/**
	 * 
	 * 해당 날짜에 상영중인 영화의 영화 상영 정보 목록 값을 반환하는 메서드입니다.
	 * 
	 * @param map Map 객체
	 * @return MoviePlayDTO 객체
	 */
	@PostMapping(value = "/user/activity/movieplay")
	public MoviePlayDTO getMoviePlayBySeqDate(@RequestBody Map<String, String> map) {

		return service.getMoviePlayBySeqDate(map);
	}
}
