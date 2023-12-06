package com.project.dd.activity.attraction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.mapper.AttractionMapper;

@Controller
@RequestMapping(value = "/user/activity/attraction")
public class UserAttractionController {

	@Autowired
	private AttractionMapper mapper;
	
	@GetMapping(value = "/view.do")
	public String view(Model model, String close) {
		
		//운영: close=n, 운휴: close=y
		if (close == null || close.equals("")) {
			close = "n";
		} 

		model.addAttribute("list", mapper.getAttractionList(close));
		model.addAttribute("close", close);

		return "user/activity/attraction/view";
	}

	@GetMapping(value = "/detail.do")
	public String detail(Model model, String seq) {
		
		AttractionDTO dto = mapper.getAttraction(seq); //List<AttractionImgDTO> 빼고 dto에 다 담긴 상태
		
		List<AttractionImgDTO> ilist = mapper.getAttractionImgList(seq); //List<AttractionImgDTO> 가져와서
		
		dto.setImgList(ilist); //List<AttractionImgDTO>까지 dto에 넣어주기
		
		model.addAttribute("dto", dto);
		
		return "user/activity/attraction/detail";
	}
}
