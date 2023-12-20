package com.project.dd.close.attraction.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.close.attraction.domain.CloseAttractionDTO;
import com.project.dd.close.attraction.service.CloseAttractionService;

/**
 * 어트랙션의 운휴 일정을 조회, 추가, 수정, 삭제할 수 있는 관리자 전용 컨트롤러 클래스
 * 
 * @author leeje
 *
 */

@Controller
@RequestMapping(value="/admin/close/attraction")
public class AdminCloseAttractionController {
	
	@Autowired private CloseAttractionService closeAttrService;
	
	/**
	 * 관리자가 어트랙션의 운휴 일정 목록을 볼 수 있는 메서드
	 * @param page 페이지
	 * @param model 모델 객체
	 * @return 목록을 보여주는 jsp 파일명
	 */
	 
	//목록보기
	@GetMapping(value = "/view.do") 
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		Map<String, String> map = closeAttrService.paging(page);  //페이징
		
		List<CloseAttractionDTO> list = closeAttrService.list(map); 
		
		model.addAttribute("currentPage", page);  //페이징
		model.addAttribute("map", map);  //페이징
		model.addAttribute("list", list);
		
		return "admin/close/attraction/view"; 
	}
	
	/**
	 * 어트랙션의 운휴를 추가할 수 있는 add 메서드
	 * @param model 모델 객체
	 * @return 해당 jsp 파일명
	 */
	
	//추가하기
	@GetMapping(value = "/add.do")
	public String add(Model model) {
		
		model.addAttribute("attlist", closeAttrService.attlist());  //추가할 어트랙션 리스트 가져오기

		return "admin/close/attraction/add";
	}
	
	/**
	 *  추가한 어트랙션의 운휴일정을 DB에서 처리.
	 *  처리 결과에 따라 이동할 페이지를 호출하는 addok 메서드.
	 * @param dto 어트랙션 운휴일정 dto 객체
	 * @return 이동할 페이지 주소
	 */
	
	@PostMapping(value = "/addok.do")
	public String addok(CloseAttractionDTO dto) {

		//System.out.println(dto);
		
		int result = closeAttrService.addCloseAtt(dto);
		
		if (result > 0) {
			return "redirect:/admin/close/attraction/view.do";
		} else {  //실패
			return "redirect:/admin/close/attraction/add.do";
		}
		
	}

	/**
	 * 어트랙션 운휴를 수정할 수 있는 edit 메서드
	 * @param model 모델 객체
	 * @param seq 수정하기 위해 선택한 어트랙션 운휴의 seq
	 * @return 해당 jsp파일
	 */
	
	//수정하기
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {
		
		CloseAttractionDTO dto = closeAttrService.getOne(seq); 
		
		//System.out.println(dto);
		
		model.addAttribute("dto", dto);
		
		return "admin/close/attraction/edit";
	}
	
	/**
	 * 수정한 운휴를 DB에서 처리하고 처리결과에 따라 이동할 페이지를 호출하는 editok 메서드
	 * @param dto 어트랙션 운휴 dto 객체
	 * @return 이동할 페이지 주소
	 */
	
	@PostMapping(value = "/editok.do")
	public String editok(CloseAttractionDTO dto) {

		//System.out.println(dto);
		int result = closeAttrService.editClose(dto);
		
		if (result == 1) {
	 		return "redirect:/admin/close/attraction/view.do";
	 	} else 
	 		return "redirect:/admin/close/attraction/edit.do";
	}
	
	/**
	 * 삭제할 운휴를 DB에서 처리하고 처리결과에 따라 이동할 페이지를 호출하는 del 메서드
	 * @param model 객체
	 * @param 삭제할 운휴의 seq
	 * @return 이동할 페이지의 주소
	 */
	
	//삭제하기
	@PostMapping(value = "/del.do")
	public String del(Model model, String[] closeAttraction_seq) {

		closeAttrService.del(closeAttraction_seq);
		
		return "redirect:/admin/close/attraction/view.do";
	}


	 

}
