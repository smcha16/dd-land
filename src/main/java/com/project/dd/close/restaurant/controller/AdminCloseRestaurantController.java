package com.project.dd.close.restaurant.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.close.restaurant.domain.CloseRestaurantDTO;
import com.project.dd.close.restaurant.service.CloseRestaurantService;

/**
 * 식당의 운휴 일정을 조회, 추가, 수정, 삭제할 수 있는 관리자 전용 컨트롤러 클래스
 * 
 * @author leeje
 *
 */

@Controller
@RequestMapping(value = "/admin/close/restaurant")
public class AdminCloseRestaurantController {
	
	@Autowired
	private CloseRestaurantService service;
	
	/**
	 * 관리자가 식당의 운휴 일정을 목록 볼 수 있는 메서드
	 * @param page 페이지
	 * @param model 모델 객체
	 * @return 목록을 보여주는 jsp 파일명
	 */
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		Map<String, String> map = service.paging(page);  //페이징
		
		List<CloseRestaurantDTO> list = service.list(map); 
		
		model.addAttribute("currentPage", page);  //페이징
		model.addAttribute("map", map);  //페이징
		model.addAttribute("list", list);
		
		return "admin/close/restaurant/view";
	}
	
	/**
	 * 식당의 운휴를 추가할 수 있는 add 메서드
	 * @param model 모델 객체
	 * @return 해당 jsp 파일명
	 */
	
	//추가하기
	@GetMapping(value = "/add.do")
	public String add(Model model) {
				
		model.addAttribute("restaurantlist", service.restaurantlist());  

		return "admin/close/restaurant/add";
	}
	
	/**
	 *  추가한 식당의 운휴일정을 DB에서 처리.
	 *  처리 결과에 따라 이동할 페이지를 호출하는 addok 메서드.
	 * @param dto 식당 운휴일정 dto 객체
	 * @return 이동할 페이지 주소
	 */
			
	@PostMapping(value = "/addok.do")
	public String addok(CloseRestaurantDTO dto) {
		
		int result = service.addCloseRestaurant(dto);
				
		if (result > 0) {
			return "redirect:/admin/close/restaurant/view.do";
		} else {  //실패
			return "redirect:/admin/close/restaurant/add.do";
		}
				
	}
	
	/**
	 * 식당 운휴를 수정할 수 있는 edit 메서드
	 * @param model 모델 객체
	 * @param seq 수정하기 위해 선택한 식당 운휴의 seq
	 * @return 해당 jsp파일
	 */

	//수정하기
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {
				
		CloseRestaurantDTO dto = service.getOne(seq); 
				
		model.addAttribute("dto", dto);
				
		return "admin/close/restaurant/edit";
	}
	
	/**
	 * 수정한 운휴를 DB에서 처리하고 처리결과에 따라 이동할 페이지를 호출하는 editok 메서드
	 * @param dto 식당 운휴 dto 객체
	 * @return 이동할 페이지 주소
	 */
	
	@PostMapping(value = "/editok.do")
	public String editok(CloseRestaurantDTO dto) {

		int result = service.editClose(dto);
						
		if (result == 1) {
		 	return "redirect:/admin/close/restaurant/view.do";
		} else 
		 	return "redirect:/admin/close/restaurant/edit.do";
	}
	
	/**
	 * 삭제할 운휴를 DB에서 처리하고 처리결과에 따라 이동할 페이지를 호출하는 del 메서드
	 * @param model 객체
	 * @param 삭제할 운휴의 seq
	 * @return 이동할 페이지의 주소
	 */
	
	//삭제하기
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] closeRestaurant_seq) {

		service.del(closeRestaurant_seq);
				
		return "redirect:/admin/close/restaurant/view.do";
	}
	
}
