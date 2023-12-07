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

@Controller
@RequestMapping(value = "/user/activity/photozone")
public class UserPhotozoneController {

	@Autowired
	private PhotoZoneMapper mapper;
	
	@GetMapping(value = "/view.do")
	public String view(Model model) {

		model.addAttribute("list", mapper.getPhotozoneList());
		
		return "user/activity/photozone/view";
	}

	@GetMapping(value = "/detail.do")
	public String detail(Model model, String seq) {
		
		PhotoZoneDTO dto = mapper.getPhotozone(seq); //List<PhotozoneImgDTO> 빼고 dto에 다 담긴 상태
		
		List<PhotoZoneImgDTO> ilist = mapper.getPhotozoneImgList(seq); //List<PhotozoneImgDTO> 가져와서
		
		dto.setImgList(ilist); //List<PhotozoneImgDTO>까지 dto에 넣어주기
		
		model.addAttribute("dto", dto);
		
		return "user/activity/photozone/detail";
	}
}
