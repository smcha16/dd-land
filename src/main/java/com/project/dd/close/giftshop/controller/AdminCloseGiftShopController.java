package com.project.dd.close.giftshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.close.giftshop.domain.CloseGiftShopDTO;
import com.project.dd.close.giftshop.service.CloseShopService;

@Controller
@Repository(value = "/admin/close/gift-shop")
public class AdminCloseGiftShopController {
	
	@Autowired
	private CloseShopService service;
	
	//목록보기
	@GetMapping(value = "/view.do") 
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
				
		Map<String, String> map = service.paging(page);  //페이징
				
		List<CloseGiftShopDTO> list = service.list(map); 
				
		model.addAttribute("currentPage", page);  //페이징
		model.addAttribute("map", map);  //페이징
		model.addAttribute("list", list);
				
		return "admin/close/gift-shop/view"; 
	}
			
	//추가하기
	@GetMapping(value = "/add.do")
	public String add(Model model) {
				
		model.addAttribute("shoplist", service.shoplist());  

		return "admin/close/gift-shop/add";
	}
			
	@PostMapping(value = "/addok.do")
	public String addok(CloseGiftShopDTO dto) {

		//System.out.println(dto);
				
		int result = service.addCloseShop(dto);
				
		if (result > 0) {
			return "redirect:/admin/close/gift-shop/view.do";
		} else {  //실패
			return "redirect:/admin/close/gift-shop/add.do";
		}
				
	}

	//수정하기
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {
				
		CloseGiftShopDTO dto = service.getOne(seq); 
				
		//System.out.println(dto);
				
		model.addAttribute("dto", dto);
				
		return "admin/close/gift-shop/edit";
	}
			
	@PostMapping(value = "/editok.do")
	public String editok(CloseGiftShopDTO dto) {

		//System.out.println(dto);
		int result = service.editClose(dto);
						
		if (result == 1) {
		 	return "redirect:/admin/close/gift-shop/view.do";
		} else 
		 	return "redirect:/admin/close/gift-shop/edit.do";
	}
			
	//삭제하기
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] closeShop_seq) {

		service.del(closeShop_seq);
				
		return "redirect:/admin/close/gift-shop/view.do";
	}

}
