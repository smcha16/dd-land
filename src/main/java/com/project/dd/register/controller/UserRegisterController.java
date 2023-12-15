package com.project.dd.register.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.register.domain.MemberDTO;
import com.project.dd.register.service.RegisterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/register")
public class UserRegisterController {

	private final RegisterService service;
	
	@GetMapping("/view.do")
	public String view() {
		
		
		return"user/register/view";
	}
	
	@PostMapping("/view.do")
	public String register(MemberDTO memberDTO ,@RequestParam(name = "post-code") String postcode ,
			@RequestParam(name = "address-basis") String addressbasis ,@RequestParam(name = "address-detail") String addressdetail,
			@RequestParam(name="mm")String mm,@RequestParam(name="dd")String dd) {
		
		String address = service.extracted(postcode, addressbasis, addressdetail);
		
		String birthday = service.dayChange(memberDTO, mm, dd);
		
		String formattedPhoneNumber1 = service.formatPhoneNumber(memberDTO.getTel());
		
		
		memberDTO.setTel(formattedPhoneNumber1);
		memberDTO.setAddress(address);
		memberDTO.setBirth(birthday);
		
		System.out.println(memberDTO.toString());
		
		int result = service.register(memberDTO);
	
		
		return "main";
	}
	
	 @GetMapping("/checkEmailDuplicate")
	    public ResponseEntity<String> checkEmailDuplicate(@RequestParam String email) {
	        int result = service.check(email);
	        return ResponseEntity.ok(result > 0 ? "DUPLICATED" : "AVAILABLE");
	    }
	 

	

	
	
	
	
	
}
