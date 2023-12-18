package com.project.dd.shop.restaurant.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.domain.RestaurantImageDTO;
import com.project.dd.shop.restaurant.service.RestaurantService;

@Controller
public class AdminRestaurantController {

	@Autowired
	private RestaurantService service;

	@GetMapping(value = "/admin/shop/restaurant/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {

		String solting = "admin";
		Map<String, String> map = service.paging(page, solting);

		List<RestaurantDTO> list = service.getList(map);

		List<RestaurantImageDTO> ilist = service.getImgList();

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);

		model.addAttribute("list", list);

		model.addAttribute("ilist", ilist);

		return "admin/shop/restaurant/view";
	}

	@GetMapping(value = "/admin/shop/restaurant/add.do")
	public String add(Model model) {

		return "admin/shop/restaurant/add";
	}

	@PostMapping(value = "/admin/shop/restaurant/addok.do")
	public String addok(Model model, RestaurantDTO dto, MultipartFile image, HttpServletRequest req) {

		try {

			UUID uuid = UUID.randomUUID();

			String filename = uuid + "_" + image.getOriginalFilename();

			image.transferTo(new File(req.getRealPath("resources/files/restaurant") + "\\" + filename));

			dto.setImg(filename);

			int result = service.addRestaurant(dto);

			if (result == 1) {
				String seq = service.getSeq();

				dto.setRestaurant_seq(seq);

				service.addRestaurantLocation(dto);
				service.addRestaurantImg(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/admin/shop/restaurant/view.do";
	}

	@GetMapping(value = "/admin/shop/restaurant/edit.do")
	public String edit(Model model, String seq) {

		RestaurantDTO dto = service.detail(seq);

		List<RestaurantImageDTO> ilist = service.getImg(seq);

		for (RestaurantImageDTO idto : ilist) {
			if (idto.getImg().length() > 37 && idto.getImg().contains("-") && idto.getImg().contains("_")) {

				// UUID 제거
				String originalFileName = idto.getImg().substring(idto.getImg().indexOf("_") + 1);
				idto.setImg(originalFileName);
			}
		}
		
		model.addAttribute("dto", dto);
		model.addAttribute("ilist", ilist);

		return "admin/shop/restaurant/edit";
	}
	
	@PostMapping(value = "/admin/shop/restaurant/editok.do")
	public String editok(Model model, RestaurantDTO dto, MultipartFile image, HttpServletRequest req, String deleteImgSeq) {

		int result = service.editRestaurant(dto, image, req, deleteImgSeq);
		
		if (result > 0) {
			return "redirect:/admin/shop/restaurant/view.do";
		} else {
			return "redirect:/admin/shop/restaurant/edit.do";
		}
	}

	@PostMapping(value = "/admin/shop/restaurant/del.do")
	public String del(Model model, String[] restaurant_seq) {
		
		int result = service.delRestaurant(restaurant_seq);

		if (result > 0) {
			return "redirect:/admin/shop/restaurant/view.do";
		} else {
			return "redirect:/admin/shop/restaurant/view.do";
		}
	}
	
}
