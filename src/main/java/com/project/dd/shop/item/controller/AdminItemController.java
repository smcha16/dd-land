package com.project.dd.shop.item.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.shop.item.domain.ItemDTO;
import com.project.dd.shop.item.domain.ItemImgDTO;
import com.project.dd.shop.item.service.ItemService;

/**
 * 관리자용 상품 컨트롤러 클래스입니다.
 * @author pega0
 *
 */
@Controller
public class AdminItemController {

	@Autowired
	private ItemService service;
	
	/**
	 * 상품 목록 페이지를 조회하는 메서드입니다.
	 *
	 * @param page 현재 페이지 번호
	 * @param word 검색어
	 * @param model Model 객체
	 * @return 상품 목록 페이지 경로
	 */
	@GetMapping(value = "/admin/shop/item/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, String word, Model model) {

		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		String solting = "admin";
		Map<String, String> map = service.paging(page, word, searchStatus, solting);
		
		List<ItemDTO> list = service.getFullList(map);
		
		List<ItemImgDTO> ilist = service.getImgList();
		
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);

		model.addAttribute("list", list);

		model.addAttribute("ilist", ilist);
		
		return "admin/shop/item/view";
	}
	
	/**
	 * 선택된 상품을 삭제하는 메서드입니다.
	 *
	 * @param model Model 객체
	 * @param item_seq 삭제할 상품 번호 배열
	 * @return 상품 목록 페이지로 리다이렉트
	 */
	@PostMapping(value = "/admin/shop/item/del.do")
	public String del(Model model, String[] item_seq) {
		
		int result = service.delItem(item_seq);

		return "redirect:/admin/shop/item/view.do";
	}
	
}
