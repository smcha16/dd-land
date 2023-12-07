package com.project.dd.activity.photozone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.photozone.domain.PhotoZoneImgDTO;
import com.project.dd.activity.photozone.mapper.PhotoZoneMapper;
import com.project.dd.activity.photozone.service.PhotoZoneService;

@Controller
@RequestMapping(value = "/user/activity/photozone")
public class UserPhotozoneController {

	@Autowired
	private PhotoZoneService service;
	
	@GetMapping(value = "/view.do")
	public String view(Model model) {

		List<PhotoZoneDTO> list = service.getPhotozoneList();
		
		model.addAttribute("list", list);
		
		return "user/activity/photozone/view";
	}

	@GetMapping(value = "/detail.do")
	public String detail(Model model, String seq) {
		
		PhotoZoneDTO dto = service.getPhotozone(seq);
		
		List<PhotoZoneImgDTO> ilist = service.getPhotozoneImgList(seq);
		
		dto.setImgList(ilist);
		
		model.addAttribute("dto", dto);
		
		return "user/activity/photozone/detail";
	}
}
