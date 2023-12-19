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

/**
 * 사용자 등록을 처리하는 컨트롤러 클래스.
 * @author 김형우
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/user/register")
public class UserRegisterController {

    private final RegisterService service;

    /**
     * 등록 페이지를 보여주는 메서드.
     *
     * @return 등록 페이지 뷰 이름.
     */
    @GetMapping("/view.do")
    public String view() {
        return "user/register/view";
    }

    /**
     * 회원 등록을 처리하는 메서드.
     *
     * @param memberDTO    회원 정보 DTO 객체.
     * @param postcode     우편번호.
     * @param addressbasis 기본 주소.
     * @param addressdetail 상세 주소.
     * @param mm           생년월일 월 부분.
     * @param dd           생년월일 일 부분.
     * @return 메인 페이지 뷰 이름.
     */
    @PostMapping("/view.do")
    public String register(MemberDTO memberDTO, @RequestParam(name = "post-code") String postcode,
                           @RequestParam(name = "address-basis") String addressbasis,
                           @RequestParam(name = "address-detail") String addressdetail,
                           @RequestParam(name = "mm") String mm, @RequestParam(name = "dd") String dd) {

        String address = service.extracted(postcode, addressbasis, addressdetail);
        String birthday = service.dayChange(memberDTO, mm, dd);
        String formattedPhoneNumber = service.formatPhoneNumber(memberDTO.getTel());

        memberDTO.setTel(formattedPhoneNumber);
        memberDTO.setAddress(address);
        memberDTO.setBirth(birthday);

        System.out.println(memberDTO.toString());

        int result = service.register(memberDTO);

        return "main";
    }

    /**
     * 이메일 중복 확인을 처리하는 메서드.
     *
     * @param email 이메일.
     * @return 중복 여부에 따라 "DUPLICATED" 또는 "AVAILABLE"을 반환하는 ResponseEntity.
     */
    @GetMapping("/checkEmailDuplicate")
    public ResponseEntity<String> checkEmailDuplicate(@RequestParam String email) {
        int result = service.check(email);
        return ResponseEntity.ok(result > 0 ? "DUPLICATED" : "AVAILABLE");
    }
}
