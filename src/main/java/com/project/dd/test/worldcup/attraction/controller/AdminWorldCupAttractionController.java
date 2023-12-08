package com.project.dd.test.worldcup.attraction.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.test.worldcup.attraction.service.WorldCupAttractionService;

@Controller
public class AdminWorldCupAttractionController {

	@Autowired
	private WorldCupAttractionService attractionService;

	@GetMapping(value = "/admin/test/worldcup/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model,
			@RequestParam(defaultValue = "n") String close, @RequestParam(defaultValue = "Y") String isTest) {

		Map<String, String> map = attractionService.paging(page); // 페이징

		model.addAttribute("currentPage", page); // 페이징
		model.addAttribute("map", map); // 페이징
		model.addAttribute("listAttraction", attractionService.getAllAttraction(map));
		
		//System.out.println(attractionService.getAllAttraction(map));
		
		return "admin/test/worldcup/view";
	}
	
	@PostMapping(value = "/admin/test/worldcup/updateStatus.do")
    public String updateAttractionStatus(@RequestParam String attractionSeq, @RequestParam String isChecked) {
        attractionService.updateAttractionStatus(attractionSeq, isChecked);

        return "상태가 성공적으로 업데이트되었습니다";
    }
}
