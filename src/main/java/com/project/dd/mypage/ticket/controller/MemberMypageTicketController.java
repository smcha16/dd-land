package com.project.dd.mypage.ticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.mypage.ticket.domain.TicketDTO;
import com.project.dd.mypage.ticket.mapper.MypageTicketMapper;

@Controller
@RequestMapping("/member/mypage/ticket")
public class MemberMypageTicketController {
	
	@Autowired
	private MypageTicketMapper mapper;
	
	@GetMapping(value = "/view.do")
	public String view(Model model) {
		
		List<TicketDTO> list = mapper.list();
		
		model.addAttribute("list", list);

		return "mypage/ticket/view";
	}
	
	@PostMapping(value = "/delete.do")
    public String deleteTickets(@RequestParam(value = "selectedTickets", required = false) List<String> selectedTickets) {
        // selectedTickets를 이용하여 선택된 티켓을 삭제하는 로직을 구현
        if (selectedTickets != null && !selectedTickets.isEmpty()) {
            for (String ticketId : selectedTickets) {
                // 선택된 티켓 삭제하는 작업
                mapper.delete(ticketId); // 예시: mapper를 통해 티켓 삭제
            }
        }
        return "redirect:/member/mypage/ticket/view.do"; // 처리 후 보여줄 페이지로 리다이렉트
    }

}
