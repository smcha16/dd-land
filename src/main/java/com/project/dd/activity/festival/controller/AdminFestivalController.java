package com.project.dd.activity.festival.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.festival.domain.FestivalImgDTO;
import com.project.dd.activity.festival.service.FestivalService;

/**
 * 
 * 페스티벌 관리(조회/추가/수정/삭제)를 담당하는 관리자 전용 컨트롤러 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Controller
@RequestMapping(value = "/admin/activity/festival")
public class AdminFestivalController {

	@Autowired
	private FestivalService service;
	
	/**
	 * 
	 * 관리자용 페스티벌 목록을 조회할 수 있는 view 메서드입니다.
	 * 
	 * @param page 페이지
	 * @param model 모델 객체
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/view.do")
	public String view(String word, @RequestParam(defaultValue = "1") int page, Model model) {

		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		//Admin 전용 페이징
		Map<String, String> map = service.adminPaging(searchStatus, word, page);
		
		//Festival 목록 전체
		List<FestivalDTO> list = service.getAllFestivalList(map);
		
		//모달용 FestivalImg 목록
		List<FestivalImgDTO> ilist = service.getAllFestivalImgList();
		
		//페이징 전달
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		
		//Festival 목록 전달
		model.addAttribute("list", list);
		
		//모달용 FestivalImg 목록 전달
		model.addAttribute("ilist", ilist);
		
		return "admin/activity/festival/view";
	}
	
	/**
	 * 
	 * 페스티벌을 추가할 수 있는 add 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/add.do")
	public String add(Model model) {

		return "admin/activity/festival/add";
	}
	
	/**
	 * 
	 * 추가한 페스티벌을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 addok 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param dto 페스티벌 dto 객체
	 * @param imgs 추가한 이미지 멀티파일 객체
	 * @param req HttpServletRequest 객체
	 * @return 이동할 페이지 주소
	 */
	@PostMapping(value = "/addok.do")
	public String addok(Model model, FestivalDTO dto, MultipartFile[] imgs, HttpServletRequest req) {

		int result = service.addFestival(dto, imgs, req);
		
		if (result > 0) {
			return "redirect:/admin/activity/festival/view.do";
		} else {
			return "redirect:/admin/activity/festival/add.do";
		}
		
	}
	
	/**
	 * 
	 * 페스티벌을 수정할 수 있는 edit 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param seq 어트랙션 번호
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {

		FestivalDTO dto = service.getFestival(seq);
		
		List<FestivalImgDTO> ilist = service.getFestivalImgList(seq);
		
		//UUID 제거(DB에 먼저 넣은 더미 데이터, 구현된 페이지에서 직접 추가한 데이터 > UUID 유무 상이)
		// - 따라서 1. DB에 먼저 넣은 더미 데이터도 UUID를 추가하거나
		// - 		2. 임시로 조건을 걸어서 해당 조건일 경우 UUID(8-4-4-4-12_) 삭제, 아닐 경우 그대로 돌려주기
		// - 		   (img.length > 37 && img.contains("-") && img.contains("_"))

		for (FestivalImgDTO idto : ilist) {
			
			if (idto.getImg().length() > 37 && idto.getImg().contains("-") && idto.getImg().contains("_")) {
				
				//UUID 제거
				String originalFileName = idto.getImg().substring(idto.getImg().indexOf("_") + 1);
				idto.setImg(originalFileName);
			}
			
		}
		//ilist > FestivalDTO에 담기
		dto.setImgList(ilist);
		
		//날짜 유효성 검사용
		List<FestivalDTO> dlist = new ArrayList<FestivalDTO>();
		dlist.add(dto);
		
		model.addAttribute("dto", dto);
		model.addAttribute("dlist", dlist);
		
		return "admin/activity/festival/edit";
	}
	
	/**
	 * 
	 * 수정한 페스티벌을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 editok 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param dto 페스티벌 dto 객체
	 * @param imgs 추가 첨부한 멀티파일 객체
	 * @param req HttpServletRequest 객체
	 * @param deleteImgSeq 삭제할 이미지 번호 배열
	 * @return 이동할 페이지 주소
	 */
	@PostMapping(value = "/editok.do")
	public String editok(Model model, FestivalDTO dto, MultipartFile[] imgs, HttpServletRequest req, String[] deleteImgSeq) {

		//수정 시, 기본 등록 이미지 처리를 위한 절차
		FestivalDTO temp = service.getFestival(dto.getFestival_seq());
		dto.setImg(temp.getImg());
		
		int result = service.editFestival(dto, imgs, req, deleteImgSeq);
		
		if (result > 0) {
			return "redirect:/admin/activity/festival/view.do";
		} else {
			return "redirect:/admin/activity/festival/edit.do";
		}
		
	}
	
	/**
	 * 
	 * 삭제할 페스티벌을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 del 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param festival_seq 페스티벌 번호
	 * @return 이동할 페이지 주소
	 */
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] festival_seq) {

		int result = service.delFestival(festival_seq);
		
		return "redirect:/admin/activity/festival/view.do";
	}
	
	
}
