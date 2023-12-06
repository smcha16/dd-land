package com.project.dd.pb.price.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.pb.price.service.UserPriceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/pb/price")
public class UserPriceController {
	
    private final UserPriceService priceService;

	
	@GetMapping(value = "/view.do")
	public String view(Model model) {
		
	
	        List<PriceDTO> personTypeList = priceService.getPersonTypeList();
	        priceService.formatPrices(personTypeList);

	        List<PriceDTO> groupTypeList = priceService.getGroupTypeList();
	        priceService.formatPrices(groupTypeList);

	        model.addAttribute("groupTypeList", groupTypeList);
	        model.addAttribute("personTypeList", personTypeList);

	        return "user/pb/price/view";
	    }
	
}
