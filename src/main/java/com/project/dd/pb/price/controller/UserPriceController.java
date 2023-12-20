package com.project.dd.pb.price.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.pb.price.service.UserPriceService;

import lombok.RequiredArgsConstructor;

/**
 * 사용자 가격 정보를 처리하는 컨트롤러 클래스.
 *  @author 김형우
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/user/pb/price")
public class UserPriceController {
	
    private final UserPriceService priceService;

	/**
	 * 가격 정보를 뷰에 전달하는 메서드.
	 * 
	 * @param model Spring MVC Model 객체.
	 * @return 사용자 가격 정보를 보여주는 뷰 페이지.
	 */
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
