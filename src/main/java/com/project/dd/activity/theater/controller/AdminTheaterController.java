package com.project.dd.activity.theater.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.activity.theater.domain.TheaterDTO;
import com.project.dd.activity.theater.service.TheaterService;

/**
 * 
 * 영화관 관리(조회/추가/수정/삭제)를 담당하는 관리자 전용 컨트롤러 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Controller
@RequestMapping(value = "/admin/activity/theater")
public class AdminTheaterController {
	
	@Autowired
	private TheaterService service;
	
	/**
	 * 
	 * 관리자용 영화관 목록을 조회할 수 있는 view 메서드입니다.
	 * 
	 * @param page 페이지 번호
	 * @param model 모델 객체
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {

		//페이징
		Map<String, String> map = service.paging(page);
		
		//Theater 목록(운영종료 제외)
		List<TheaterDTO> list = service.getTheaterList(map);
		
		//페이징 전달
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		
		//영화관 목록 전달
		model.addAttribute("list", list);
		
		return "admin/activity/theater/view";
	}
	
	/**
	 * 
	 * 영화관을 추가할 수 있는 add 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/add.do")
	public String add(Model model) {
		return "admin/activity/theater/add";
	}
	
	/**
	 * 
	 * 추가한 영화관을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 addok 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param dto 영화관 dto 객체
	 * @return 이동할 페이지 주소
	 */
	@PostMapping(value = "/addok.do")
	public String addok(Model model, TheaterDTO dto) {

		//tblTheater 추가
		//tblTheaterLocation 추가
		
		int result = service.addTheater(dto);
		
		if (result > 0) {
			return "redirect:/admin/activity/theater/view.do";
		} else {
			return "redirect:/admin/activity/theater/add.do";
		}
		
	}
	
	/**
	 * 
	 * 영화관을 수정할 수 있는 edit 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param seq 영화관 번호
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {

		TheaterDTO dto = service.getTheater(seq);
		
		model.addAttribute("dto", dto);
		
		return "admin/activity/theater/edit";
	}
	
	/**
	 * 
	 * 수정한 영화관을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 editok 메서드이빈다.
	 *  
	 * @param model 모델 객체
	 * @param dto 영화관 dto 객체
	 * @return 이동할 페이지 주소
	 */
	@PostMapping(value = "/editok.do")
	public String editok(Model model, TheaterDTO dto) {

		//서버측 유효성 검사 생략 > 관리자 페이지니까 악의성 없다고 간주
		//- tblTheater 수정
		//- tblTheaterLocation 수정
		
		int result = service.editTheater(dto);
		
		if (result > 0) {
			return "redirect:/admin/activity/theater/view.do";
		} else {
			return "redirect:/admin/activity/theater/edit.do";
		}
	}
	
	/**
	 * 
	 * 삭제할 영화관을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 del 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param theater_seq 영화관 번호
	 * @return 이동할 페이지 주소
	 */
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] theater_seq) {

		//영화관 삭제 > "(운영종료)" 문구 UPDATE로 구현
		// - tblTheater > UPDATE
		// - tblTheaterLocation > DELETE
		
		int result = service.delTheater(theater_seq);
		
		return "redirect:/admin/activity/theater/view.do";
	
	
	}

}
