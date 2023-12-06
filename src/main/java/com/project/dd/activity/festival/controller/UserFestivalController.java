package com.project.dd.activity.festival.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.festival.domain.FestivalImgDTO;
import com.project.dd.activity.festival.mapper.FestivalMapper;

@Controller
@RequestMapping(value = "/user/activity/festival")
public class UserFestivalController {
	@Autowired
	private FestivalMapper mapper;
	
	@GetMapping(value = "/view.do")
	public String view(Model model, String date) {

		//date: 선택한 일자에 페스티벌 진행 유무 확인 가능한 변수
		if (date == null || date.equals("")) {
			date = "sysdate";
		}
		
		model.addAttribute("list", mapper.getFestivalList(date));
		model.addAttribute("date", date);
		
		return "user/activity/festival/view";
	}

	@GetMapping(value = "/detail.do")
	public String detail(Model model, String seq) {
		
		FestivalDTO dto = mapper.getFestival(seq); //List<FestivalImgDTO> 빼고 dto에 다 담긴 상태
		
		List<FestivalImgDTO> ilist = mapper.getFestivalImgList(seq); //List<FestivalImgDTO> 가져와서
		
		dto.setImgList(ilist); //List<FestivalImgDTO>까지 dto에 넣어주기
		
		model.addAttribute("dto", dto);
		
		return "user/activity/festival/detail";
	}
	
	
}
