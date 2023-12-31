package com.project.dd.mypage.unregister.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.mypage.unregister.service.UnregisterService;

@Controller
@RequestMapping("/member/mypage/unregister")
public class MemberMypageUnregisterController {
	
	@Autowired
	private UnregisterService service;
	
	/**
	 * 회원 탈퇴 페이지를 보여주는 메서드입니다.
	 *
	 * @param model    데이터를 뷰에 전달하기 위한 Model 객체
	 * @return         회원 탈퇴 페이지 뷰
	 */
	@GetMapping(value = "/view.do")
	public String view(Model model) {

		return "mypage/unregister/view";
	}
	
	/**
	 * 회원 탈퇴 성공 페이지를 보여주는 메서드입니다.
	 *
	 * @param model    데이터를 뷰에 전달하기 위한 Model 객체
	 * @return         회원 탈퇴 성공 페이지 뷰
	 */
	@GetMapping(value = "/success.do")
	public String success(Model model) {

		return "mypage/unregister/success";
	}
	
	/**
	 * 회원 탈퇴 실패 페이지를 보여주는 메서드입니다.
	 *
	 * @param model    데이터를 뷰에 전달하기 위한 Model 객체
	 * @return         회원 탈퇴 실패 페이지 뷰
	 */
	@GetMapping(value = "/failure.do")
	public String failure(Model model) {

		return "mypage/unregister/failure";
	}
	
	/**
	 * 회원을 탈퇴시키는 메서드입니다.
	 *
	 * @param model    데이터를 뷰에 전달하기 위한 Model 객체
	 * @param email    탈퇴하려는 회원의 이메일
	 * @return         회원 탈퇴 결과에 따른 페이지 이동
	 */
	@PostMapping(value = "/unregister.do")
	public String unregister(Model model, String email) {
		
		// 이메일과 비밀번호를 사용하여 사용자 확인
        boolean isValidUser = service.isValidUser(email);

        if (isValidUser) {
            // 사용자가 확인되면 회원 탈퇴 처리
            boolean isDeleted = service.deleteMember(email);
            
            if (isDeleted) {
                // 회원 탈퇴 성공
                return "redirect:/member/mypage/unregister/success.do"; // 성공 페이지로 이동
            } else {
                // 회원 탈퇴 실패
                model.addAttribute("error", "회원 탈퇴에 실패했습니다.");
                return "redirect:/member/mypage/unregister/failure.do"; // 실패 페이지로 이동
            }
        } else {
            // 사용자가 확인되지 않음
            model.addAttribute("error", "이메일이 일치하지 않습니다.");
            model.addAttribute("invalidEmail", true);
            return "mypage/unregister/view";
        }
	}

}
