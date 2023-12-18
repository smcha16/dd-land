package com.project.dd.test.worldcup.attraction.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.notice.domain.NoticeDTO;
import com.project.dd.test.worldcup.attraction.service.WorldCupAttractionService;

@Controller
@RequestMapping("/admin/test/worldcup/attraction")
public class AdminWorldCupAttractionController {

	@Autowired
	private WorldCupAttractionService awcService;

	@GetMapping(value = "/view.do")
	public String view(String word, @RequestParam(defaultValue = "1") int page, Model model) {

		String solting = "admin";
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";

		Map<String, String> map = awcService.paging(solting, searchStatus, word, page); // 페이징

		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("listAttraction", awcService.getAllAttraction(map));
		model.addAttribute("awcFinalWinTotalCount", awcService.getAWCFinalWinTotalCount());

		return "admin/test/worldcup/attraction/view";
	}

	@PostMapping(value = "/view.do")
	public String updateAttractionStatus(@RequestParam String attractionSeq, @RequestParam String isTest, Model model) {
	    //System.out.println("seq:" + attractionSeq + " check:" + isTest);
	    
	    Map<String, String> map = new HashMap<>();
	    map.put("isTest", isTest);
	    map.put("attractionSeq", attractionSeq);

	    awcService.updateAttractionStatus(map);
	    
	    return "redirect:/admin/test/worldcup/attraction/view.do"; 
	}

}
