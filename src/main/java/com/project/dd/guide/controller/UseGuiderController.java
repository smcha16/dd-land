package com.project.dd.guide.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user/guide")
public class UseGuiderController {  //정적 페이지 담당
	
	@GetMapping(value = "/use-guide/view.do")
	public String use_guide_view(Model model) {  
		//파크 이용안내
 
		return "user/guide/use-guide/view";
	}
	
	@GetMapping(value = "/location/view.do")
	public String location_view(Model model) {
		//오시는길

		return "user/guide/location/view";
	}
	
	@GetMapping(value = "/guide-map/view.do")
	public String guide_map_view(Model model) {
		//여러시설의 위치를 볼 수 있는 가이드맵
		
		return "user/guide/guide-map/view";
	}



}
