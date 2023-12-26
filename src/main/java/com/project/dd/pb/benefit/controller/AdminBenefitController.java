package com.project.dd.pb.benefit.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.communication.faq.domain.FaqDTO;
import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.benefit.service.BenefitService;
import com.project.dd.register.domain.MemberDTO;

import lombok.RequiredArgsConstructor;

/**
 * 혜택 관리를 위한 관리자 컨트롤러.
 * 
 * @author 김형우
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/pb/benefit")
public class AdminBenefitController {

    private final BenefitService service;

    /**
     * 혜택 목록을 뷰에 표시하는 메서드.
     * 
     * @param model 혜택 목록을 담는 모델.
     * @param page 페이지 숫자.
     * @return 혜택 목록을 표시하는 뷰 이름.
     */
    @GetMapping("/view.do")
    public String view(Model model , @RequestParam(defaultValue = "1") int page) {
    	// 페이징 정보 및 회원 목록을 가져오는 서비스 호출
    			String type = "없음";
    			Map<String, String> map = service.paging(type, page);
    			List<BenefitDTO> list = service.getBenefitList(map);

    			// 페이징 계산 로직 추가
    			int totalPages = Integer.parseInt(map.get("totalPages"));
    			int startPage = 1;
    			int endPage = Math.min(totalPages, 10);

    			if (page > 10) {
    				startPage = Math.max(1, (page - 1) / 10 * 10 + 1);
    				endPage = Math.min(totalPages, startPage + 9);
    			}

    	
//        List<BenefitDTO> list = service.List();
        
    			// 모델에 속성 추가
    			model.addAttribute("currentPage", page);
    			model.addAttribute("map", map);
    			model.addAttribute("list", list);
    			model.addAttribute("startPage", startPage);
    			model.addAttribute("endPage", endPage);
        
        return "admin/pb/benefit/view";
    }

    /**
     * 새로운 혜택 추가 양식 페이지로 이동하는 메서드.
     * 
     * @param model 데이터를 렌더링하는 데 사용되는 모델.
     * @return 혜택 추가 양식 페이지 뷰 이름.
     */
    @GetMapping("/add.do")
    public String add(Model model) {
        return "admin/pb/benefit/add";
    }

    /**
     * 혜택을 추가하는 메서드.
     * 
     * @param benefitDTO    추가할 혜택 데이터.
     * @param bindingResult 유효성 검사 결과 처리.
     * @param attach        파일 업로드 처리를 위한 MultipartFile.
     * @param req           HTTP 요청을 처리하기 위한 HttpServletRequest.
     * @param model         데이터를 렌더링하는 데 사용되는 모델.
     * @return 추가 성공 시 혜택 목록 페이지로 리디렉션, 실패 시 추가 양식 페이지로 이동.
     */
    @PostMapping("/add.do")
    public String addok(@Valid BenefitDTO benefitDTO, BindingResult bindingResult, MultipartFile attach,
            HttpServletRequest req, Model model) {

        if (bindingResult.hasErrors()) {
            return "admin/pb/benefit/add";
        }

        System.out.println(benefitDTO.toString());

        int result = service.addBenefit(benefitDTO, attach, req);

        if (result == 0) {
            bindingResult.reject("addFail", " 혜택 추가에 실패 했습니다.");
            return "admin/pb/benefit/add";
        }

        return "redirect:/view.do";
    }
    /**
     * 혜택 삭제 메서드
     * @param benefit_seq  삭제할 혜택 시퀀스 번호
     * @return 혜택 관리자 페이지로 이동
     */
    @PostMapping("/del.do")
    public String del(String benefit_seq) {
    	int result = service.del(benefit_seq);
    	
    	System.out.println(benefit_seq);
    	return "redirect:/view.do";
    }
}
