package com.project.dd.close.theater.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.close.attraction.domain.CloseAttractionDTO;
import com.project.dd.close.theater.domain.CloseTheaterDTO;
import com.project.dd.close.theater.service.CloseTheaterService;

/**
 * 영화관의 운휴 일정을 조회, 추가, 수정, 삭제할 수 있는 관리자 전용 컨트롤러 클래스
 * 
 * @author leeje
 *
 */

@Controller
@RequestMapping(value = "/admin/close/theater")
public class AdminCloseTheaterController {

	@Autowired
	private CloseTheaterService service;
	
	/**
	 * 관리자가 영화관의 운휴 일정을 목록 볼 수 있는 메서드
	 * @param page 페이지
	 * @param model 모델 객체
	 * @return 목록을 보여주는 jsp 파일명
	 */
	
	//목록보기
	@GetMapping(value = "/view.do") 
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
			
		Map<String, String> map = service.paging(page);  //페이징
			
		List<CloseTheaterDTO> list = service.list(map); 
			
		model.addAttribute("currentPage", page);  //페이징
		model.addAttribute("map", map);  //페이징
		model.addAttribute("list", list);
			
		return "admin/close/theater/view"; 
	}
	
	/**
	 * 영화관의 운휴를 추가할 수 있는 add 메서드
	 * @param model 모델 객체
	 * @return 해당 jsp 파일명
	 */
		
	//추가하기
	@GetMapping(value = "/add.do")
	public String add(Model model) {
			
		model.addAttribute("theaterlist", service.theaterlist());  

		return "admin/close/theater/add";
	}
	
	/**
	 *  추가한 영화관의 운휴일정을 DB에서 처리.
	 *  처리 결과에 따라 이동할 페이지를 호출하는 addok 메서드.
	 * @param dto 영화관 운휴일정 dto 객체
	 * @return 이동할 페이지 주소
	 */
		
	@PostMapping(value = "/addok.do")
	public String addok(CloseTheaterDTO dto) {

		//System.out.println(dto);
			
		int result = service.addCloseTheater(dto);
			
		if (result > 0) {
			return "redirect:/admin/close/theater/view.do";
		} else {  //실패
			return "redirect:/admin/close/theater/add.do";
		}
			
	}
	
	/**
	 * 영화관 운휴를 수정할 수 있는 edit 메서드
	 * @param model 모델 객체
	 * @param seq 수정하기 위해 선택한 영화관 운휴의 seq
	 * @return 해당 jsp파일
	 */

	//수정하기
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {
			
		CloseTheaterDTO dto = service.getOne(seq); 
			
		//System.out.println(dto);
			
		model.addAttribute("dto", dto);
			
		return "admin/close/theater/edit";
	}
	
	/**
	 * 수정한 운휴를 DB에서 처리하고 처리결과에 따라 이동할 페이지를 호출하는 editok 메서드
	 * @param dto 영화관 운휴 dto 객체
	 * @return 이동할 페이지 주소
	 */
		
	@PostMapping(value = "/editok.do")
	public String editok(CloseTheaterDTO dto) {

		//System.out.println(dto);
		int result = service.editClose(dto);
					
		if (result == 1) {
		 	return "redirect:/admin/close/theater/view.do";
		} else 
		 	return "redirect:/admin/close/theater/edit.do";
	}
	
	/**
	 * 삭제할 운휴를 DB에서 처리하고 처리결과에 따라 이동할 페이지를 호출하는 del 메서드
	 * @param model 객체
	 * @param 삭제할 운휴의 seq
	 * @return 이동할 페이지의 주소
	 */
		
	//삭제하기
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] closeTheater_seq) {

		service.del(closeTheater_seq);
			
		return "redirect:/admin/close/theater/view.do";
	}
	
}
