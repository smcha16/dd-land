package com.project.dd.shop.item.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.shop.item.domain.ItemDTO;
import com.project.dd.shop.item.service.ItemService;

/**
 * 사용자가 상품 관련 기능을 수행하는 컨트롤러 클래스입니다.
 * @author pega0
 *
 */
@Controller
public class UserItemController {
	
	@Autowired
	private ItemService service;
	
	/**
	 * 상품 목록을 조회하여 사용자에게 보여주는 메서드입니다.
	 *
	 * @param page 현재 페이지 번호
	 * @param seq 상품 카테고리 시퀀스
	 * @param model 데이터를 전달하는 Spring의 Model 객체
	 * @return 상품 목록을 보여주는 뷰 페이지
	 */
	@GetMapping(value = "/user/shop/gift-shop/item/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, String seq, Model model) {

		Map<String, String> map = service.paging(page, seq);
		
		map.put("seq", seq);
		
		model.addAttribute("list", service.getList(map));
		model.addAttribute("currentPage", page);  //페이징
	    model.addAttribute("map", map);  //페이징
	    model.addAttribute("seq", seq);
		
		return "user/shop/gift-shop/item/view";
	}
	
	/**
	 * 상품 상세 정보를 조회하여 사용자에게 보여주는 메서드입니다.
	 *
	 * @param model 데이터를 전달하는 Spring의 Model 객체
	 * @param seq 조회할 상품의 시퀀스
	 * @return 상품 상세 정보를 보여주는 뷰 페이지
	 */
	@GetMapping(value = "/user/shop/gift-shop/item/detail.do")
	public String detail(Model model, String seq) {
		
		model.addAttribute("dto", service.getItem(seq));
		model.addAttribute("list", service.getImg(seq));

		return "user/shop/gift-shop/item/detail";
	}

}
