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

@Controller
public class AdminItemController {

	@Autowired
	private ItemService service;
	
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
	
	@PostMapping(value = "/admin/shop/item/del.do")
	public String del(Model model, String[] item_seq) {
		
		int result = service.delItem(item_seq);

		return "redirect:/admin/shop/item/view.do";
	}
	
}
