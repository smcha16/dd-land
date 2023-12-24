package com.project.dd.activity.movieplay.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movieplay.domain.MoviePlayDTO;
import com.project.dd.activity.movieplay.service.MoviePlayService;

/**
 * 
 * 영화 상영 조회(목록, 상세)를 담당하는 사용자 전용 컨트롤러 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Controller
@RequestMapping(value = "/user/activity/movie")
public class UserMoviePlayController {
	
	@Autowired
	private MoviePlayService service;
	
	/**
	 * 
	 * 특정 일자에 상영하는 영화의 목록을 가져오는 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param date 상영 일자
	 * @return 호출할 jsp 파일명
	 */
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model, @RequestParam(defaultValue = "sysdate") String date) {

		//User 전용 페이징
		Map<String, String> map = service.userPaging(page, date);
		
		//날짜별 상영 영화 목록
		List<MovieDTO> list = service.getMoviePlayList(map, date);
		
		//페이징
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		
		//날짜, 영화목록 전달
		model.addAttribute("list", list);
		model.addAttribute("date", date);
		
		return "user/activity/movie/view";
	}
	
	/**
	 * 
	 * 해당 날짜에 상영중인 영화 중 선택한 특정 영화의 상세 내용을 가져오는 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param seq 영화 번호
	 * @param date 상영 일자
	 * @return 호출할 jsp 파일명
	 */
	@GetMapping(value = "/detail.do")
	public String detail(Model model, String seq, String date) {

		MovieDTO dto = service.getMovie(seq);
		
		List<MoviePlayDTO> plist = service.getMoviePlayListBySeqDate(seq, date);
		
		//dto에 plist 담기
		dto.setMoviePlayList(plist);
		
		model.addAttribute("dto", dto);
		model.addAttribute("date", date);
		
		//상영 영화관 개수 전달
		model.addAttribute("plistNum", plist.size());
		return "user/activity/movie/detail";
	}

}

