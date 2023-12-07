package com.project.dd.pb.price.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dd.pb.price.domain.PriceDTO;

@Controller
@RequestMapping("/admin/pb/price")
public class AdminPriceController {
	
	
	@GetMapping("/view.do")
	public String view(Model model) {
	
		
		
		

		
	
		return "/admin/pb/price/view";
	}
	
	
}
