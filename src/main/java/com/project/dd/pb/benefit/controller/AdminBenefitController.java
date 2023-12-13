package com.project.dd.pb.benefit.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.benefit.service.BenefitService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/pb/benefit")
public class AdminBenefitController {

	private final BenefitService service;

	@GetMapping("/view.do")
	public String view(Model model) {

		List<BenefitDTO> list = service.List();

		model.addAttribute("list", list);

		return "admin/pb/benefit/view";

	}

	@GetMapping("/add.do")
	public String add(Model model) {

		return "admin/pb/benefit/add";
	}

	@PostMapping("/add.do")
	public String addok(@Valid BenefitDTO benefitDTO, BindingResult bindingResult, MultipartFile attach,
			HttpServletRequest req,Model model) {

		if (bindingResult.hasErrors()) {
			return "admin/pb/benefit/add";
		}

		System.out.println(benefitDTO.toString());
		
		int result = service.addBenefit(benefitDTO,attach,req);
		
		if (result == 0 ) {
			bindingResult.reject("addFail"," 혜택 추가에 실패 했습니다.");
			return "admin/pb/benefit/add";
		}
		
		return "redirect:admin/pb/benefit/view";
	}

}
