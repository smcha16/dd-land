package com.project.dd.activity.photozone.controller;

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

import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.photozone.domain.PhotoZoneImgDTO;
import com.project.dd.activity.photozone.service.PhotoZoneService;

/**
 * 
 * 포토존 관리(조회/추가/수정/삭제)를 담당하는 관리자 전용 컨트롤러 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Controller
@RequestMapping(value = "/admin/activity/photozone")
public class AdminPhotozoneController {

	@Autowired
	private PhotoZoneService service;
	
	/**
	 * 
	 * 관리자용 포토존 목록을 조회할 수 있는 view 메서드입니다.
	 * 
	 * @param page 페이지 번호
	 * @param model 모델 객체
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {

		//페이징
		String solting = "admin";
		Map<String, String> map = service.paging(page, solting);
		
		//Photozone 목록(운영종료 제외)
		List<PhotoZoneDTO> list = service.getPhotozoneList(map);
		
		//모달용 PhotozoneImg 목록
		List<PhotoZoneImgDTO> ilist = service.getAllPhotozoneImgList();
		
		//페이징 전달
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		
		//포토존 목록 전달
		model.addAttribute("list", list);
		
		//모달용 PhotozoneImg 목록 전달
		model.addAttribute("ilist", ilist);
		
		return "admin/activity/photo-zone/view";
	}
	
	/**
	 * 
	 * 포토존을 추가할 수 있는 add 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/add.do")
	public String add(Model model) {

		return "admin/activity/photo-zone/add";
	}
	
	/**
	 * 
	 * 추가한 포토존을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 addok 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param dto 포토존 dto 객체
	 * @param imgs 추가한 이미지 멀티파일 객체
	 * @param req HttpServletRequest 객체
	 * @return 이동할 페이지 주소
	 */
	@PostMapping(value = "/addok.do")
	public String addok(Model model, PhotoZoneDTO dto, MultipartFile[] imgs, HttpServletRequest req) {

		//1. tblPhotozone 추가
		//2. tblPhotozoneLocation 추가
		//3. tblPhotozoneImg 추가
		
		int result = service.addPhotozone(dto, imgs, req);
		
		if (result > 0) {
			return "redirect:/admin/activity/photozone/view.do";
		} else {
			return "redirect:/admin/activity/photozone/add.do";
		}
	}
	
	/**
	 * 
	 * 포토존을 수정할 수 있는 edit 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param seq 포토존 번호
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {

		PhotoZoneDTO dto = service.getPhotozone(seq);
		
		List<PhotoZoneImgDTO> ilist = service.getPhotozoneImgList(seq);
		
		//UUID 제거(DB에 먼저 넣은 더미 데이터, 구현된 페이지에서 직접 추가한 데이터 > UUID 유무 상이)
		// - 따라서 1. DB에 먼저 넣은 더미 데이터도 UUID를 추가하거나
		// - 		2. 임시로 조건을 걸어서 해당 조건일 경우 UUID(8-4-4-4-12_) 삭제, 아닐 경우 그대로 돌려주기
		// - 		   (img.length > 37 && img.contains("-") && img.contains("_"))
		
		for (PhotoZoneImgDTO idto : ilist) {
			
			if (idto.getImg().length() > 37 && idto.getImg().contains("-") && idto.getImg().contains("_")) {
				
				//UUID 제거
				String originalFileName = idto.getImg().substring(idto.getImg().indexOf("_") + 1);
				idto.setImg(originalFileName);
			}
			
		}
		
		//ilist > PhotoZoneDTO 담기
		dto.setImgList(ilist);
		
		model.addAttribute("dto", dto);
		
		return "admin/activity/photo-zone/edit";
	}
	
	/**
	 * 
	 * 수정한 포토존을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 editok 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param dto 포토존 dto 객체
	 * @param imgs 추가 첨부한 멀티파일 객체
	 * @param req HttpServletRequest 객체
	 * @param deleteImgSeq 삭제할 이미지 번호 배열
	 * @return 이동할 페이지 주소
	 */
	@PostMapping(value = "/editok.do")
	public String editok(Model model, PhotoZoneDTO dto, MultipartFile[] imgs, HttpServletRequest req, String[] deleteImgSeq) {

		//기본 img DTO에 담기
		PhotoZoneDTO temp = service.getPhotozone(dto.getPhotozone_seq());
		dto.setImg(temp.getImg());
		
		int result = service.editPhotozone(dto, imgs, req, deleteImgSeq);
		
		if (result > 0) {
			return "redirect:/admin/activity/photozone/view.do";
		} else {
			return "redirect:/admin/activity/photozone/edit.do";
		}
		
	}
	
	/**
	 * 
	 * 삭제할 포토존을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 del 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param photozone_seq 포토존 번호
	 * @return 이동할 페이지 주소
	 */
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] photozone_seq) {

		int result = service.delPhotozone(photozone_seq);
		
		return "redirect:/admin/activity/photozone/view.do";
	}
}
