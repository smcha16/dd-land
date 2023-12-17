package com.project.dd.test.mbti.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.test.mbti.domain.MBTIDTO;
import com.project.dd.test.mbti.service.MBTIService;
import com.project.dd.test.worldcup.attraction.service.WorldCupAttractionService;
import com.project.dd.test.worldcup.course.service.WorldCupCourseService;

@Controller
public class AdminMBTIController {

    @Autowired
    private MBTIService mbtiService;

	@Autowired
	private WorldCupAttractionService awcService;

	@Autowired
	private WorldCupCourseService cwcService;
	
	@GetMapping(value = "/admin/test/mbti/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		Map<String, String> map = mbtiService.paging(page);

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("listMBTI", mbtiService.getAllMBTI(map));
		
		return "admin/test/mbti/view";
	}
	
	@GetMapping(value = "/admin/test/mbti/add.do")
	public String add(Model model) {

		model.addAttribute("attractionList", awcService.getAttractionNameList());
		model.addAttribute("courseList", cwcService.getCourseNameList());
		
		return "admin/test/mbti/add";
	}

	@PostMapping(value = "/admin/test/mbti/addok.do")
	public String addok(Model model, MBTIDTO dto, MultipartFile image, HttpServletRequest req) {
		
		int result = mbtiService.addMBTI(dto, image, req);
		
		if (result > 0) {
			return "redirect:/admin/test/mbti/view.do";
		} else {
			model.addAttribute("alertMessage", "MBTI별 추천 추가에 실패했습니다.");
			return "redirect:/admin/test/mbti/add.do";
		}
	}
	
}
