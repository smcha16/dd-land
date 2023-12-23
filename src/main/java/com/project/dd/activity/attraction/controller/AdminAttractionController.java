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
import com.project.dd.activity.attraction.domain.BookUserDTO;
import com.project.dd.activity.attraction.service.AttractionService;
import com.project.dd.test.worldcup.attraction.service.WorldCupAttractionService;

/**
 * 
 * 어트랙션 관리(조회/추가/수정/삭제)를 담당하는 관리자 전용 컨트롤러 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Controller
@RequestMapping(value = "/admin/activity/attraction")
public class AdminAttractionController {
	
	@Autowired
	private AttractionService service;

    @Autowired
    private WorldCupAttractionService awcService;
	
    /**
     * 
     * 관리자용 어트랙션 목록을 조회할 수 있는 view 메서드입니다.
     * 
     * @param word 검색어(어트랙션명)
     * @param page 페이지
     * @param model 모델 객체
     * @return jsp 파일명
     */
	@GetMapping(value = "/view.do")
	public String view(String word, @RequestParam(defaultValue = "1") int page, Model model) {
		
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		//Admin 전용 페이징
		Map<String, String> map = service.adminPaging(searchStatus, word, page);
		
		//Attraction 목록(운영종료 제외)
		List<AttractionDTO> list = service.getAllAttractionList(map);
		
		//모달용 AttractionImg 목록
		List<AttractionImgDTO> ilist = service.getAllAttractionImgList();
		
		//페이징 전달
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		
		//개행 확인
		System.out.println("admin view 개행 확인: " + list.toString());
		
		//어트 목록 전달
		model.addAttribute("list", list);
		
		//모달용 AttractionImg 목록 전달
		model.addAttribute("ilist", ilist);
		
		return "admin/activity/attraction/view";
	}
	
	/**
	 * 
	 * 어트랙션을 추가할 수 있는 add 메서드입니다. 
	 * 
	 * @param model 모델 객체
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/add.do")
	public String add(Model model) {
		return "admin/activity/attraction/add";
	}

	/**
	 * 
	 * 추가한 어트랙션을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 addok 메서드입니다.
	 * 
	 * @param model 모델 객체
	 * @param dto 어트랙션 dto 객체
	 * @param imgs 추가한 이미지 멀티파일 객체
	 * @param req HttpServletRequest 객체
	 * @return 이동할 페이지 주소
	 */
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
			String seq = service.getAttractionSeq();
			
			awcService.addAWC(dto, seq);
			awcService.addAWCWin(dto, seq);
			awcService.addAWCFinalWin(dto, seq);
			
			return "redirect:/admin/activity/attraction/view.do";
		} else {
			return "redirect:/admin/activity/attraction/add.do";
		}
		
	}
	
	/**
	 * 
	 * 어트랙션을 수정할 수 있는 edit 메서드입니다. 
	 * 
	 * @param model 객체
	 * @param seq 어트랙션 번호
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {
		
		AttractionDTO dto = service.getAttraction(seq);
		
		//List<AttractionImgDTO> 가져오기
		List<AttractionImgDTO> ilist = service.getAttractionImgList(seq);
		
		
		//UUID 제거(DB에 먼저 넣은 더미 데이터, 구현된 페이지에서 직접 추가한 데이터 > UUID 유무 상이)
		// - 따라서 1. DB에 먼저 넣은 더미 데이터도 UUID를 추가하거나
		// - 		2. 임시로 조건을 걸어서 해당 조건일 경우 UUID(8-4-4-4-12_) 삭제, 아닐 경우 그대로 돌려주기
		// - 		   (img.length > 37 && img.contains("-") && img.contains("_"))
		
		for (AttractionImgDTO idto : ilist) {
			
			if (idto.getImg().length() > 37 && idto.getImg().contains("-") && idto.getImg().contains("_")) {
				
				//UUID 제거
				String originalFileName = idto.getImg().substring(idto.getImg().indexOf("_") + 1);
				idto.setImg(originalFileName);
			}
			
		}
		//ilist > AttractionDTO에 담기
		dto.setImgList(ilist);
		
		//개행 확인
		System.out.println("admin edit 개행 확인: " + dto.toString());
		
		model.addAttribute("dto", dto);
		
		return "admin/activity/attraction/edit";
	}
	
	/**
	 * 
	 * 수정한 어트랙션을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 editok 메서드입니다.
	 * 
	 * @param model 객체
	 * @param dto 어트랙션 dto 객체
	 * @param imgs 추가 첨부한 멀티파일 객체
	 * @param req HttpServletRequest 객체
	 * @param deleteImgSeq 삭제할 이미지 번호 배열
	 * @return 이동할 페이지 주소
	 */
	@PostMapping(value = "/editok.do")
	public String editok(Model model, AttractionDTO dto, MultipartFile[] imgs, HttpServletRequest req, String[] deleteImgSeq) {

//		System.out.println(Arrays.toString(deleteImgSeq));
		
		//img
		AttractionDTO temp = service.getAttraction(dto.getAttraction_seq());
		
		dto.setImg(temp.getImg());

		int result = service.editAttraction(dto, imgs, req, deleteImgSeq);
		
		if (result > 0) {
			return "redirect:/admin/activity/attraction/view.do";
		} else {
			return "redirect:/admin/activity/attraction/edit.do";
		}
		
	}
	
	
	/**
	 * 
	 * 삭제할 어트랙션을 DB에서 처리하고 처리 결과에 따라 이동할 페이지를 호출하는 del 메서드입니다.
	 * 
	 * @param model 객체
	 * @param attraction_seq 어트랙션번호
	 * @return 이동할 페이지 주소
	 */
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
	
	/**
	 * 
	 * 회원의 어트랙션 예약 내역을 전체 조회할 수 있는 메서드입니다.
	 * 
	 * @param model 객체
	 * @return jsp 파일명
	 */
	@GetMapping(value = "/reservation/view.do")
	public String reservationView(Model model) {

		//전체 어트랙션 예약 내역 가져오기
		List<BookUserDTO> list = service.getAttractionBookList();
		
		model.addAttribute("list", list);
		
		return "admin/activity/attraction/reservationView";
		
	}
	
}
