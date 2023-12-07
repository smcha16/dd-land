package com.project.dd.test.worldcup.attraction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.attraction.mapper.WorldCupAttractionMapper;

@Controller
public class UserWorldCupAttractionController {

	@Autowired
	private WorldCupAttractionMapper mapper;
	
	@GetMapping(value = "/user/test/worldcup/attraction/view.do")
	public String view(Model model, String close, String isTest) {
		
	    if (close == null || close.equals("")) {
	        close = "n";
	    }
	    isTest = "Y";
	    
	    List<AttractionDTO> listAttraction = mapper.listAttraction(close);
	    //System.out.println("listAttraction: " + listAttraction);
	    
	    //월드컵에 등록된 운영중인 어트랙션 리스트
	    model.addAttribute("listAttraction", listAttraction);

	    //어트랙션 월드컵에 등록된 어트랙션 리스트
	    model.addAttribute("listAWC", mapper.listAWC(isTest));
	    
	    //어트랙션 월드컵 승리 리스트
	    model.addAttribute("listAWCWin", mapper.listAWCWin());
	    
	    //어트랙션 월드컵 최종 승리 리스트
	    model.addAttribute("listAWCFinalWin", mapper.listAWCFinalWin());

	    return "user/test/worldcup/attraction/view";
	}
	
}
