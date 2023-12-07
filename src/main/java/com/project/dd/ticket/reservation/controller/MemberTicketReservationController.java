package com.project.dd.ticket.reservation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.dd.login.domain.CustomUser;
import com.project.dd.ticket.reservation.domain.TicketGroupReservationDTO;
import com.project.dd.ticket.reservation.mapper.TicketReservationMapper;

@Controller
public class MemberTicketReservationController {

	@Autowired
	private TicketReservationMapper mapper;
	
	@GetMapping(value = "/member/ticket/group-reservation/view.do")
	public String groupReservation(Model model) {

		return "member/ticket/group-reservation/view";
	}
	
	@PostMapping(value = "/member/ticket/group-reservation/view.do")
	public String groupReservationOk(Model model, TicketGroupReservationDTO dto, String postCode, String addressBasis, String addressDetail, String user_seq, Authentication auth) {
		
		System.out.println(((CustomUser)auth.getPrincipal()).getDto());
		
		String address = "";
		
		postCode = postCode.trim();
        addressBasis = addressBasis.trim();
        addressDetail = addressDetail.trim();
        address = postCode + " " + addressBasis + " " + addressDetail;
        
        dto.setAddress(address);
        
        System.out.println(dto);
		
        int result = mapper.groupReservation(dto);
        
        if (result == 1) {
        	String seq = mapper.getGroup();
        	
        	Map<String, String> map = new HashMap<>();
        	
        	map.put("seq", seq);
        	map.put("user_seq", user_seq);
        	
        	result = mapper.usergroup(map);
        }

		return "redirect:/index.do";
	}
	
}
