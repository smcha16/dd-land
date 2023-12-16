package com.project.dd.activity.attraction.controller;

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

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.service.AttractionService;
import com.project.dd.test.worldcup.attraction.service.WorldCupAttractionService;


@Controller
@RequestMapping(value = "/admin/activity/attraction")
public class AdminAttractionController {
	
	@Autowired
	private AttractionService service;

    @Autowired
    private WorldCupAttractionService awcService;
	
	@GetMapping(value = "/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		//페이징
		String solting = "admin";
		Map<String, String> map = service.paging(page, solting);
		
		//Attraction 목록(운영종료 제외)
		List<AttractionDTO> list = service.getAttractionList(map);
		
		//페이징 전달
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		
		//어트 목록 전달
		model.addAttribute("list", list);
		
		return "admin/activity/attraction/view";
	}
	
	@GetMapping(value = "/add.do")
	public String add(Model model) {
		return "admin/activity/attraction/add";
	}

	@PostMapping(value = "/addok.do")
	public String addok(Model model, AttractionDTO dto, MultipartFile[] imgs, HttpServletRequest req) {
		
//		System.out.println(imgs[0].isEmpty());
//		System.out.println(dto.toString());
		
		//0. 어트랙션 위치 중복 검사 > ajax + server
		//0. 어트랙션 이름 중복 검사 > ajax + server
		//1. 어트랙션 추가
		//2. 어트랙션 위치 추가
		//3. 어트랙션 이미지 추가
		
		int result = service.addAttraction(dto, imgs, req);
		
		if (result > 0) {
			
			//어트랙션 월드컵 관련 insert
			String seq = service.getAttractionSeq() + "";
			
			awcService.addAWC(dto, seq);
			awcService.addAWCWin(dto, seq);
			awcService.addAWCFinalWin(dto, seq);
			
			return "redirect:/admin/activity/attraction/view.do";
		} else {
			return "redirect:/admin/activity/attraction/add.do";
		}
		
	}
	
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {
		
		AttractionDTO dto = service.getAttraction(seq);
		
		//List<AttractionImgDTO> 가져오기
		List<AttractionImgDTO> ilist = service.getAttractionImgList(seq);
		
		//ilist > AttractionDTO에 담기
		dto.setImgList(ilist);
		
		//UUID 제거(DB에 먼저 넣은 더미 데이터, 구현된 페이지에서 직접 추가한 데이터 > UUID 유무 상이)
		// - 따라서 1. DB에 먼저 넣은 더미 데이터도 UUID를 추가하거나
		// - 		2. 임시로 조건을 걸어서 해당 조건일 경우 UUID(8-4-4-4-12_) 삭제, 아닐 경우 그대로 돌려주기
		// - 		   (img.length > 37 && img.contains("-") && img.contains("_"))
		
		for (AttractionImgDTO idto : ilist) {
			
			if (idto.getImg().length() > 37 && idto.getImg().contains("-") && idto.getImg().contains("_")) {
				
				//UUID 제거
				String originalFileName = idto.getImg().substring(idto.getImg().indexOf("_") + 1);
				dto.setImg(originalFileName);
			}
			
		}
		
		model.addAttribute("dto", dto);
		
		return "admin/activity/attraction/edit";
	}
	
	@PostMapping(value = "/editok.do")
	public String editok(Model model, AttractionDTO dto, MultipartFile[] imgs, HttpServletRequest req, String[] deleteImgSeq) {

//		System.out.println(Arrays.toString(deleteImgSeq));
		
		//img
		AttractionDTO temp = service.getAttraction(dto.getAttraction_seq());
		
		dto.setImg(temp.getImg());

		int result = service.editAttraction(dto, imgs, req, deleteImgSeq);
		
//		if (result > 0) {
//			return "redirect:/admin/activity/attraction/view.do";
//		} else {
//			return "redirect:/admin/activity/attraction/edit.do";
//		}
		
		return "redirect:/admin/activity/attraction/view.do";
		
	}
	
	
	
	
	
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] attraction_seq) {

		//어트랙션 삭제 > "(운영종료)" 문구 UPDATE로 구현
		// - tblAttractionImg > DELETE
		// - tblAttractionLocation > DELETE
		// - tblAttraction > UPDATE
		
		int result = service.delAttraction(attraction_seq);
		
		if (result > 0) {
			return "redirect:/admin/activity/attraction/view.do";
		} else {
			return "redirect:/admin/activity/attraction/view.do";
		}
	}
	
	
	

}
