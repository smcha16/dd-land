package com.project.dd.shop.giftshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.shop.giftshop.domain.ShopDTO;
import com.project.dd.shop.giftshop.service.GiftshopService;

/**
 * 사용자용 선물샵 컨트롤러 클래스입니다.
 * @author pega0
 *
 */
@Controller
public class UserGiftshopController {

	@Autowired
	private GiftshopService service;
	
	/**
     * 선물샵 상품 목록을 조회하여 사용자 페이지에 표시합니다.
     *
     * @param page 현재 페이지 번호
     * @param model Spring MVC의 Model 객체
     * @return 선물샵 사용자 페이지 경로
     */
	@GetMapping(value = "/user/shop/gift-shop/view.do")
	public String userview(@RequestParam(defaultValue = "1") int page, Model model) {
		
		Map<String, String> map = service.paging(page);  //페이징
		List<ShopDTO> list = service.getList(map);
		
		int closeCount = service.getShopCloseCount(list);
		
		model.addAttribute("list", list);
		model.addAttribute("currentPage", page);  //페이징
	    model.addAttribute("map", map);  //페이징
	    model.addAttribute("closeCount", closeCount);

		return "user/shop/gift-shop/view";
	}
	
	/**
     * 선택된 선물샵 상품의 상세 정보를 조회하여 사용자 페이지에 표시합니다.
     *
     * @param model Spring MVC의 Model 객체
     * @param seq 선택된 선물샵 상품 번호
     * @return 선물샵 사용자 상세 페이지 경로
     */
	@GetMapping(value = "/user/shop/gift-shop/detail.do")
	public String detail(Model model, String seq) {
		
		model.addAttribute("dto", service.detail(seq));
		model.addAttribute("list", service.getImg(seq));

		return "user/shop/gift-shop/detail";
	}
	
}
