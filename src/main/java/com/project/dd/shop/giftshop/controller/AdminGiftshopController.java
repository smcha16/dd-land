package com.project.dd.shop.giftshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.shop.giftshop.domain.GiftshopImageDTO;
import com.project.dd.shop.giftshop.domain.ShopDTO;
import com.project.dd.shop.giftshop.service.GiftshopService;

/**
 * 관리자용 선물샵 컨트롤러 클래스입니다.
 * @author pega0
 *
 */
@Controller
public class AdminGiftshopController {

	@Autowired
	private GiftshopService service;

	/**
     * 선물샵 상품 목록을 조회하여 관리자 페이지에 표시합니다.
     *
     * @param page 현재 페이지 번호
     * @param word 검색어
     * @param model Spring MVC의 Model 객체
     * @return 선물샵 관리자 페이지 경로
     */
	@GetMapping(value = "/admin/shop/gift-shop/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, String word, Model model) {

		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		String solting = "admin";
		Map<String, String> map = service.paging(page, word, searchStatus, solting);

		List<ShopDTO> list = service.getList(map);

		List<GiftshopImageDTO> ilist = service.getImgList();

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);

		model.addAttribute("list", list);

		model.addAttribute("ilist", ilist);

		return "admin/shop/gift-shop/view";
	}
	
	/**
     * 선택된 선물샵 상품을 삭제합니다.
     *
     * @param model Spring MVC의 Model 객체
     * @param shop_seq 삭제할 선물샵 상품 번호 배열
     * @return 선물샵 관리자 페이지로 리다이렉트
     */
	@PostMapping(value = "/admin/shop/gift-shop/del.do")
	public String del(Model model, String[] shop_seq) {
		
		int result = service.delGiftshop(shop_seq);
		
		if (result > 0) {
			return "redirect:/admin/shop/gift-shop/view.do";
		} else {
			return "redirect:/admin/shop/gift-shop/view.do";
		}
	}

}
