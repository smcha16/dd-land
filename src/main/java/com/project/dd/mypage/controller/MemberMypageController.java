package com.project.dd.mypage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.dd.mypage.modify.domain.ModifyDTO;


@Controller
public class MemberMypageController {

	@GetMapping(value = "/member/mypage/view.do")
	public String name(Model model) {
		
		return "mypage";
	}
}
