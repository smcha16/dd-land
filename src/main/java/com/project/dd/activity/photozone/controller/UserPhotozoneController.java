package com.project.dd.activity.photozone.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.photozone.domain.PhotoZoneImgDTO;
import com.project.dd.activity.photozone.service.PhotoZoneService;

/**
 * 
 * 포토존 조회(목록, 상세)를 담당하는 모든 회원 전용 컨트롤러 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Controller
@RequestMapping(value = "/user/activity/photozone")
public class UserPhotozoneController {

	@Autowired
	private PhotoZoneService service;
	
	/**
	 * 
	 * 운영중인 포토존 목록을 불러오는 메서드입니다.
	 * 
	 * @param page 페이지 번호
	 * @param model 모델 객체
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {

		//페이징
		String solting = "user";
		Map<String, String> map = service.paging(page, solting);
		
		List<PhotoZoneDTO> list = service.getPhotozoneList(map);
		
		model.addAttribute("currentPage", page);  //페이징
	      model.addAttribute("map", map);  //페이징
		
		model.addAttribute("list", list);
		
		return "user/activity/photozone/view";
	}

	/**
	 * 
	 * 선택한 특정 포토존의 상세 내용을 불러오는 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param seq 포토존 번호
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/detail.do")
	public String detail(Model model, String seq) {
		
		PhotoZoneDTO dto = service.getPhotozone(seq);
		
		List<PhotoZoneImgDTO> ilist = service.getPhotozoneImgList(seq);
		
		dto.setImgList(ilist);
		
		model.addAttribute("dto", dto);
		
		return "user/activity/photozone/detail";
	}
}
