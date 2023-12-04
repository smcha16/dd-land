package com.project.dd.pb.price.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.pb.price.persistence.PriceDAO;
import com.project.dd.pb.price.persistence.PriceDAOImpl;

@Controller
@RequestMapping("/admin/pb")
public class AdminPriceController {
	
	
	@GetMapping("/price/view.do")
	public String view(Model model) {
	
		
		PriceDAO dao = new PriceDAOImpl();
		
		
		
		List<PriceDTO> personTypeList = dao.personTypeList();	 // 티켓에서 개인 리스트
		
		List<PriceDTO> groupTypeList = dao.groupTypeList();   // 티켓에서 단체 리스트 
		
		
		
		model.addAttribute("personTypeList",personTypeList);
		model.addAttribute("gourpTypeList",groupTypeList);
		
	
		return "/admin/pb/price/view";
	}
	
	
}
