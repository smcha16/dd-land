package com.project.dd.pb.benefit.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.benefit.service.BenefitService;
import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.pb.price.service.UserPriceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user/pb/benefit")
@RequiredArgsConstructor
public class UserBenefitController {

	private final BenefitService service;
	private final UserPriceService priceService;

	@GetMapping("/view.do")
	public String view(Model model) {

		List<BenefitDTO> normalList = service.normalList(); // 일반혜택
		List<BenefitDTO> cardList = service.cardList(); // 카드/통신사 혜택
		List<BenefitDTO> list = service.List();

		model.addAttribute("normalList", normalList);
		model.addAttribute("cardList", cardList);
		model.addAttribute("list", list);

		return "user/pb/benefit/view";
	}

	@GetMapping("/detail.do")
	public String list(@RequestParam String seq, Model model) {

		List<PriceDTO> personTypeList = priceService.getPersonTypeList(); //개인것만 가져오기

		List<BenefitDTO> benefitList = service.benefitList();  //혜택리스트 가져오기

		String name = service.getName(String.valueOf(seq));  //혜택 이름 가져오기

		List<PriceDTO> groupList = priceService.getGroupTypeList(); // 단체 리스트 가져오기
		
		
		// 할인율 적용 , 금액에 , 찍기
		 BenefitService.Result discountResult = service.applyDiscount(personTypeList, groupList, benefitList, seq);
		
		// seq로 받은 해당 혜택 리스트의 정보 가져오기
	        List<BenefitDTO> benefitInfoList = service.benefitInfo(String.valueOf((seq)));
	        
	        
	        
		 
		 model.addAttribute("benefitInfoList",benefitInfoList);
		 model.addAttribute("list",personTypeList);
		 model.addAttribute("benefitList",benefitList);
		 model.addAttribute("seq",seq);
		 model.addAttribute("name",name);
		 model.addAttribute("groupList",groupList);
		 
		 model.addAttribute("discountList1Day1",discountResult.discountList1Day.get(0)); //개인 성인 1Day
		 model.addAttribute("discountList1Day2",discountResult.discountList1Day.get(1)); // 개인 청소년 1Day
	
		 
		 model.addAttribute("discountListAfter41",discountResult.discountListAfter4.get(0));
		 model.addAttribute("discountListAfter42",discountResult.discountListAfter4.get(1));
		 
		 model.addAttribute("groupDiscount1Day1",discountResult.groupDiscount1Day.get(0));
		 model.addAttribute("groupDiscount1Day2",discountResult.groupDiscount1Day.get(1));
		 
		 model.addAttribute("groupDiscountAfter41",discountResult.groupDiscountAfter4.get(0));
		 model.addAttribute("groupDiscountAfter42",discountResult.groupDiscountAfter4.get(1));

		 

		return "user/pb/benefit/detail";
	}

}
