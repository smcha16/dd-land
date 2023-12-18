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

@Controller
@RequestMapping(value = "/admin/activity/photozone")
public class AdminPhotozoneController {

	@Autowired
	private PhotoZoneService service;
	
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
	
	@GetMapping(value = "/add.do")
	public String add(Model model) {

		return "admin/activity/photo-zone/add";
	}
	
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
	
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] photozone_seq) {

		int result = service.delPhotozone(photozone_seq);
		
		return "redirect:/admin/activity/photozone/view.do";
	}
}
