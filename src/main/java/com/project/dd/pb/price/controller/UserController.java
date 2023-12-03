package com.project.dd.pb.price.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.pb.price.mapper.PriceMapper;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private PriceMapper dao;

	@GetMapping(value = "/view.do")
	public String name(Model model) {
		
	List<PriceDTO> list = dao.list();
		
	model.addAttribute("list", list);

		return "price";
	}
}
