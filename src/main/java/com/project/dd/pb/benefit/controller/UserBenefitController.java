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

/**
 * 사용자 혜택 정보를 처리하는 컨트롤러 클래스.
 * @author 김형우
 */
@Controller
@RequestMapping("/user/pb/benefit")
@RequiredArgsConstructor
public class UserBenefitController {

	private final BenefitService service;
	private final UserPriceService priceService;

	/**
	 * 사용자에게 혜택 목록을 보여주는 메서드.
	 * 일반혜택, 카드/통신사 혜택, 전체 혜택 목록을 가져와 뷰에 전달합니다.
	 * 
	 * @param model 뷰에 전달할 데이터를 담는 모델.
	 * @return 사용자 혜택 목록을 표시하는 뷰 이름.
	 */
	@GetMapping("/view.do")
	public String view(Model model) {
		List<BenefitDTO> normalList = service.normalList();
		List<BenefitDTO> cardList = service.cardList();
		List<BenefitDTO> list = service.List();

		model.addAttribute("normalList", normalList);
		model.addAttribute("cardList", cardList);
		model.addAttribute("list", list);

		return "user/pb/benefit/view";
	}

	/**
	 * 특정 혜택에 대한 상세 정보를 보여주는 메서드.
	 * 
	 * @param seq   조회할 혜택의 일련번호.
	 * @param model 뷰에 전달할 데이터를 담는 모델.
	 * @return 혜택의 상세 정보를 표시하는 뷰 이름.
	 */
	@GetMapping("/detail.do")
	public String list(@RequestParam String seq, Model model) {
		// 필요한 데이터를 서비스를 통해 가져옴
		List<PriceDTO> personTypeList = priceService.getPersonTypeList();
		List<BenefitDTO> benefitList = service.benefitList();
		String name = service.getName(String.valueOf(seq));
		List<PriceDTO> groupList = priceService.getGroupTypeList();
		
		// 할인율 적용 결과를 계산
		BenefitService.Result discountResult = service.applyDiscount(personTypeList, groupList, benefitList, seq);
		
		// 혜택에 대한 상세 정보 가져오기
		List<BenefitDTO> benefitInfoList = service.benefitInfo(String.valueOf(seq));
		
		// 모델에 데이터 전달
		model.addAttribute("benefitInfoList", benefitInfoList);
		model.addAttribute("list", personTypeList);
		model.addAttribute("benefitList", benefitList);
		model.addAttribute("seq", seq);
		model.addAttribute("name", name);
		model.addAttribute("groupList", groupList);
		model.addAttribute("discountList1Day1", discountResult.discountList1Day.get(0));
		model.addAttribute("discountList1Day2", discountResult.discountList1Day.get(1));
		model.addAttribute("discountListAfter41", discountResult.discountListAfter4.get(0));
		model.addAttribute("discountListAfter42", discountResult.discountListAfter4.get(1));
		model.addAttribute("groupDiscount1Day1", discountResult.groupDiscount1Day.get(0));
		model.addAttribute("groupDiscount1Day2", discountResult.groupDiscount1Day.get(1));
		model.addAttribute("groupDiscountAfter41", discountResult.groupDiscountAfter4.get(0));
		model.addAttribute("groupDiscountAfter42", discountResult.groupDiscountAfter4.get(1));

		return "user/pb/benefit/detail";
	}
}
