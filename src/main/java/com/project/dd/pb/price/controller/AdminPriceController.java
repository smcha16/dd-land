package com.project.dd.pb.price.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.pb.price.service.UserPriceService;

import lombok.RequiredArgsConstructor;

/**
 * AdminPriceContoller클래스입니다 관리자 요금페이지를 담당합니다.
 * 
 * @author 김형우
 *
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/pb/price")
public class AdminPriceController {

	private final UserPriceService service;

	/**
	 * 뷰호출 메서드입니다.
	 * 
	 * @param model Model 객체입니다.
	 * @return 뷰를 호출합니다.
	 */
	@GetMapping("/view.do")
	public String view(Model model) {

		List<PriceDTO> list = service.list();

		service.formatPrices(list);

		List<PriceDTO> ticketTypeList = service.ticketTypeList();

		List<PriceDTO> ageList = service.ageList();

		model.addAttribute("ageList", ageList);
		model.addAttribute("ticketTypeList", ticketTypeList);
		model.addAttribute("list", list);

		return "admin/pb/price/view";
	}

	/**
	 * 수정페이지 호출 메서드입니다.
	 * 
	 * @param model Model 객체입니다.
	 * @return 수정페이지를 리턴합니다.
	 */
	@GetMapping("/edit.do")
	public String edit(Model model) {

		return "admin/pb/price/edit";

	}

	/**
	 * 수정메서드입니다.
	 * 
	 * @param priceDTO 수정할 티켓객체를 받습니다.
	 * @return 수정성공시 view를 반환하고 , 실패시 수정페이지로 돌아갑니다.
	 */
	@PostMapping("/edit.do")
	public String edit(@Valid PriceDTO priceDTO, BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			
			return "admin/pb/price/edit";	

		}

		service.edit(priceDTO);

		return "redirect:/admin/pb/price/view.do";

	}

}
