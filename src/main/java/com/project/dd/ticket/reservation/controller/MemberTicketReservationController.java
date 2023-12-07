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
import com.project.dd.ticket.reservation.domain.TicketReservationDTO;
import com.project.dd.ticket.reservation.service.GroupReservationService;
import com.project.dd.ticket.reservation.service.SingleReservationService;

@Controller
public class MemberTicketReservationController {
	
	@Autowired
	private SingleReservationService singleService;
	
	@Autowired
	private GroupReservationService groupService;
	
	@GetMapping(value = "/member/ticket/single-reservation/view.do")
	public String singleReservation(Model model) {

		return "member/ticket/single-reservation/view";
	}
	
	@GetMapping(value = "/member/ticket/group-reservation/view.do")
	public String groupReservation(Model model) {

		return "member/ticket/group-reservation/view";
	}
	
	@PostMapping(value = "/member/ticket/group-reservation/view.do")
	public String groupReservationOk(Model model, TicketReservationDTO dto, String postCode, String addressBasis, String addressDetail, String user_seq, Authentication auth) {
		
		System.out.println(((CustomUser)auth.getPrincipal()).getDto());
		
		String address = groupService.getAddress(postCode, addressBasis, addressDetail);
        
        dto.setAddress(address);
		
        int result = groupService.groupReservation(dto);
        
        if (result == 1) {
        	String seq = groupService.getGroup();
        	
        	Map<String, String> map = new HashMap<>();
        	
        	map.put("seq", seq);
        	map.put("user_seq", user_seq);
        	
        	result = groupService.addUserGroup(map);
        }

		return "redirect:/index.do";
	}
	
}
