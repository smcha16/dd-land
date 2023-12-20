package com.project.dd.activity.festival.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.festival.domain.FestivalImgDTO;
import com.project.dd.activity.festival.mapper.FestivalMapper;
import com.project.dd.activity.festival.service.FestivalService;

/**
 * 
 * 페스티벌 조회(목록, 상세)를 담당하는 모든 회원 전용 컨트롤러 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Controller
@RequestMapping(value = "/user/activity/festival")
public class UserFestivalController {
	@Autowired
	private FestivalService service;
	
	/**
	 * 
	 * 운영중인 페스티벌 목록을 불러오는 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param date 날짜
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/view.do")
	public String view(Model model, @RequestParam(defaultValue = "sysdate") String date) {

		//Festival 목록(금일 기준 공연 & 공연종료 제외)
		List<FestivalDTO> list = service.getFestivalList(date);
		
		model.addAttribute("list", list);
		model.addAttribute("date", date);
		
		return "user/activity/festival/view";
	}

	/**
	 * 
	 * 선택한 특정 페스티벌의 상세 내용을 불러오는 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param seq 페스티벌 번호
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/detail.do")
	public String detail(Model model, String seq) {
		
		//Festival 1개(List<FestivalImgDTO> 제외)
		FestivalDTO dto = service.getFestival(seq);
		
		//List<FestivalImgDTO> 가져오기
		List<FestivalImgDTO> ilist = service.getFestivalImgList(seq);
		
		//ilist > FestivalDTO에 담기
		dto.setImgList(ilist);
		
		model.addAttribute("dto", dto);
		
		return "user/activity/festival/detail";
	}
	
	
}
