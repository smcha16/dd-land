package com.project.dd.shop.restaurant.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.service.RestaurantService;

/**
 * 유저용 레스토랑 컨트롤러 클래스입니다.
 * @author pega0
 *
 */
@Controller
@RequestMapping("/user/shop/restaurant")
public class UserRestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	/**
     * 레스토랑 목록을 조회하는 메서드입니다.
     *
     * @param page  현재 페이지
     * @param model Spring의 Model 객체
     * @return 레스토랑 목록 뷰 페이지 경로
     */
	@GetMapping(value = "/view.do")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {
		
		Map<String, String> map = restaurantService.paging(page);  //페이징
		
		List<RestaurantDTO> list = restaurantService.getList(map);
		
		int closeCount = restaurantService.getRestaurantCloseCount(list);
		
		model.addAttribute("list", list);
		model.addAttribute("currentPage", page);  //페이징
	    model.addAttribute("map", map);  //페이징
	    model.addAttribute("closeCount", closeCount);

		return "user/shop/restaurant/view";
	}
	
	/**
     * 레스토랑 상세 정보를 조회하는 메서드입니다.
     *
     * @param model Spring의 Model 객체
     * @param seq   레스토랑 일련번호
     * @return 레스토랑 상세 정보 뷰 페이지 경로
     */
	@GetMapping(value = "/detail.do")
	public String detail(Model model, String seq) {

		model.addAttribute("dto", restaurantService.detail(seq));
		model.addAttribute("list", restaurantService.getImg(seq));
		
		return "user/shop/restaurant/detail";
	}
	
}
