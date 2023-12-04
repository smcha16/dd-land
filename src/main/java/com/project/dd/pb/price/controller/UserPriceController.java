package com.project.dd.pb.price.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.pb.price.mapper.PriceMapper;

@Controller
@RequestMapping("/user/pb")
public class UserPriceController {
	
	@Autowired
	private PriceMapper mapper;
	
	@GetMapping(value = "/view.do")
	public String view(Model model) {
		
		List<PriceDTO> personTypeList = mapper.personTypeList();
		
		
		List<PriceDTO> groupTypeList = mapper.groupTypeList();
		
	

		model.addAttribute("groupTyepList",groupTypeList);
		model.addAttribute("personTypeList",personTypeList);
		
		return "user/pb/price/view";
	}
}
