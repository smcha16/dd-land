package com.project.dd.test.worldcup.attraction.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.test.worldcup.attraction.service.WorldCupAttractionService;

@Controller
public class UserWorldCupAttractionController {

    @Autowired
    private WorldCupAttractionService attractionService;

    @GetMapping(value = "/user/test/worldcup/attraction/view.do")
    public String view(Model model, String close, String isTest) {
        isTest = "Y";

        if (close == null || close.equals("")) {
            close = "n";
        }

        // 월드컵에 등록된 운영중인 어트랙션 리스트
        model.addAttribute("listAttraction", attractionService.getAllAttractions(close));

        // 어트랙션 월드컵에 등록된 어트랙션 리스트
        model.addAttribute("listAWC", attractionService.getAllAWC(isTest));

        // 어트랙션 월드컵 승리 리스트
        model.addAttribute("listAWCWin", attractionService.getAllAWCWin());

        // 어트랙션 월드컵 최종 승리 리스트
        model.addAttribute("listAWCFinalWin", attractionService.getAllAWCFinalWin());

        return "user/test/worldcup/attraction/view";
    }

    @PostMapping("/user/test/worldcup/attraction/view.do")
    public String handleAttractionSelection(@RequestParam String attractionSeq, Model model) {
        // 어트랙션 선택 처리 로직 구현
        // 이 부분에서 선택된 어트랙션을 처리하고 새로운 어트랙션을 가져와서 비교할 수 있도록 로직을 작성합니다.
        // 이를 위해 attractionService의 메서드를 사용할 수 있습니다.

        // 예시: 선택된 어트랙션 처리 후 새로운 어트랙션 리스트를 모델에 추가
        List<AttractionDTO> newAttractions = attractionService.getRemainingAttractions(Arrays.asList(attractionSeq));
        model.addAttribute("listAttraction", newAttractions);

        return "redirect:/user/test/worldcup/attraction/view.do";
    }
}
