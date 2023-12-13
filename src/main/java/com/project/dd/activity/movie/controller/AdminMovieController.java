package com.project.dd.activity.movie.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.activity.movie.domain.MovieDTO;
import com.project.dd.activity.movie.service.MovieService;

@Controller
@RequestMapping(value = "/admin/activity/movie")
public class AdminMovieController {

	@Autowired
	private MovieService service;
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		//페이징
		String solting = "admin";
		Map<String, String> map = service.paging(page, solting);

		//Movie 목록(상영종료 제외)
		List<MovieDTO> list = service.getMovieListAll(map);
		
		//페이징 전달
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		
		//영화 목록 전달
		model.addAttribute("list", list);
		
		return "admin/activity/movie/view";
	}
	
	
	@GetMapping(value = "/add.do")
	public String add(Model model) {
		return "admin/activity/movie/add";
	}
	
	@PostMapping(value = "/addok.do")
	public String addok(Model model, MovieDTO dto, MultipartFile imgs, HttpServletRequest req) {

		// - tblMovie 테이블 추가
		
		int result = service.addMovie(dto, imgs, req);
		
		if (result > 0) {
			return "redirect:/admin/activity/movie/view.do";
		} else {
			model.addAttribute("alertMessage", "영화 추가에 실패했습니다.");
			return "redirect:/admin/activity/movie/add.do";
		}
		
	}
	
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {

		MovieDTO dto = service.getMovie(seq);
		
		//UUID 제거(DB에 먼저 넣은 더미 데이터, 구현된 페이지에서 직접 추가한 데이터 > UUID 유무 상이)
		// - 따라서 1. DB에 먼저 넣은 더미 데이터도 UUID를 추가하거나
		// - 		2. 임시로 조건을 걸어서 해당 조건일 경우 UUID(8-4-4-4-12_) 삭제, 아닐 경우 그대로 돌려주기
		// - 		   (img.length > 37 && img.contains("-") && img.contains("_"))
		String img = dto.getImg();
		
		if (img.length() > 37 && img.contains("-") && img.contains("_")) {
			
			//UUID 제거
			String originalFileName = img.substring(img.indexOf("_") + 1);
			dto.setImg(originalFileName);
		}
		
		model.addAttribute("dto", dto);
		
		return "admin/activity/movie/edit";
	}
	
	@PostMapping(value = "/editok.do")
	public String editok(Model model, MovieDTO dto, MultipartFile imgs, HttpServletRequest req) {

		int result = service.editMovie(dto, imgs, req);
		
		if (result > 0) {
			return "redirect:/admin/activity/movie/view.do";
		} else {
			return "redirect:/admin/activity/movie/edit.do";
		}
	}
	
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] movie_seq) {

		//영화 삭제 > "(상영종료)" 문구 UPDATE로 구현
		// - tblMovie > UPDATE
		
		int result = service.delMovie(movie_seq);
		
		if (result > 0) {
			return "redirect:/admin/activity/movie/view.do";
		} else {
			model.addAttribute("alertMessage", "영화 삭제에 실패했습니다.");
			return "redirect:/admin/activity/movie/view.do";
		}
		
	}
	
}
