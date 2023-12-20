package com.project.dd.guide.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.guide.domain.ConvenientDTO;
import com.project.dd.guide.service.ConvenientService;
import com.project.dd.guide.service.GuideMapService;

/**
 * 회원, 비회원 모두가 볼 수 있는 이용가이드를 나타내는 클래스
 * @author leeje
 *
 */

@Controller
@RequestMapping(value = "/user/guide")
public class UserConvenientController {
	
	@Autowired
	private ConvenientService convenientService;   //ConvenientService 객체 생성
	
	@Autowired
	private GuideMapService guideMapService;   //GuideMapService 객체 생성
	
	/**
	 * 편의시설 목록을 조회하여 보여주는 메서드
	 * @param category 카테고리
	 * @param word 검색어
	 * @param page 페이지
	 * @param model 객체
	 * @return 편의시설 view 페이지
	 */
	@GetMapping(value = "/convenient/view.do")
	public String view(String category, String word, @RequestParam(defaultValue = "1") int page, Model model) {
		
		String solting = "admin";
		
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";
		
		Map<String, String> map = convenientService.paging(solting, searchStatus, category, word, page);  //페이징
		
		List<ConvenientDTO> list = convenientService.list(map);   //편의시설 list불러오는 것을 service에게 위임
		
		model.addAttribute("currentPage", page);  //페이징
		model.addAttribute("map", map);  //페이징
		model.addAttribute("list", list);
		
		return "user/guide/convenient/view";
	}

	/**
	 * 편의시설 상세정보를 보는 클래스
	 * @param model 객체
	 * @param seq 상세 정보를 볼 편의 시설의 일련 번호
	 * @return 선택한 편의시설의 상세정보 페이지
	 */
	@GetMapping(value = "/convenient/detail.do")
	public String detail(Model model, String seq) {  //detail 보여줄 seq같이 가져오기
		
		ConvenientDTO dto = convenientService.one(seq);   //선택한 편의시설 데이터 1개
		
		model.addAttribute("dto", dto);  //dto담아서 보내기
		
		return "user/guide/convenient/detail";
	}
	
	/**
	 * use-guide의 페이지로 이동하는 클래스
	 * @param model 객체
	 * @return use-guide의 페이지
	 */
	//밑으로는 정적페이지
	@GetMapping(value = "/use-guide/view.do")
	public String use_guide_view(Model model) {  
		//파크 이용안내
 
		return "user/guide/use-guide/view";
	}
	
	/**
	 *  location의 페이지로 이동하는 클래스
	 * @param model 객체
	 * @return location의 페이지
	 */
	@GetMapping(value = "/location/view.do")
	public String location_view(Model model) {
		//오시는길

		return "user/guide/location/view";
	}
	
	/**
	 * guide-map의 페이지로 이동하는 클래스
	 * @param model 객체
	 * @return guide-map의 페이지
	 */
	@GetMapping(value = "/guide-map/view.do")
	public String guide_map_view(Model model) {
		//여러시설의 위치를 볼 수 있는 가이드맵(어트, 식당, 편의시설, 기프트샵, 영화관, 포토존, 페스티벌)
		
		model.addAttribute("attrlist", guideMapService.getAttrList()); //어트랙션 리스트
		model.addAttribute("foodlist", guideMapService.getFoodList());  //식당 리스트
		model.addAttribute("convlist", guideMapService.getConvList());  //편의시설 리스트
		model.addAttribute("giftlist", guideMapService.getGiftList());  //기프트샵 리스트
		model.addAttribute("movielist", guideMapService.getMovieList());  //영화관 리스트
		model.addAttribute("photolist", guideMapService.getPhotoList());  //포토존 리스트
		model.addAttribute("festlist", guideMapService.getFestList());  //페스티벌 리스트
	
		return "user/guide/guide-map/view";
	}

	
}
