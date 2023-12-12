package com.project.dd.ticket.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.dd.login.domain.CustomUser;
import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.ticket.reservation.domain.TicketBookDTO;
import com.project.dd.ticket.reservation.domain.TicketReservationDTO;
import com.project.dd.ticket.reservation.service.GroupReservationService;
import com.project.dd.ticket.reservation.service.SingleReservationService;

@Controller
public class MemberTicketReservationController {
	
	@Autowired
	private SingleReservationService singleService;
	
	@Autowired
	private GroupReservationService groupService;
	
	@GetMapping(value = "/member/ticket/personal-reservation/view.do")
	public String singleReservation(Model model) {

		List<BenefitDTO> list = singleService.getBenefit("일반");
		List<BenefitDTO> plist = singleService.getBenefit("카드/통신사");
		
		model.addAttribute("list", list);
		model.addAttribute("plist", plist);
		
		return "member/ticket/personal-reservation/view";
	}
	
	@PostMapping(value = "/member/ticket/personal-reservation/check.do")
	public String check(Model model, TicketReservationDTO dto, BenefitDTO bdto, String ticket_type, String adult, String teenager) {
		
		System.out.println(dto);
		System.out.println(bdto);
		System.out.println(adult);
		System.out.println(teenager);
		
		PriceDTO adto = singleService.getPrice(ticket_type, "성인");
		PriceDTO tdto = singleService.getPrice(ticket_type, "청소년");
		
		model.addAttribute("dto", dto);
		model.addAttribute("bdto", bdto);
		model.addAttribute("adto", adto);
		model.addAttribute("tdto", tdto);
		model.addAttribute("adult", adult);
		model.addAttribute("teenager", teenager);

		return "member/ticket/personal-reservation/check";
	}
	
	@PostMapping(value = "/member/ticket/personal-reservation/ok.do")
	public String addOk(Model model, 
			String visit_date,
			String adult_ea,
			String adult_seq,
			String teenager_ea,
			String teenager_seq,
			String adult_price,
			String teenager_price,
			String benefit_seq,
			String user_seq) {
		
		TicketReservationDTO adto = new TicketReservationDTO();
		TicketReservationDTO tdto = new TicketReservationDTO();
		
		adto.setVisit_date(visit_date);
		adto.setEa(adult_ea);
		adto.setTicket_seq(adult_seq);
		adto.setPrice(adult_price);
		adto.setBenefit_seq(benefit_seq);
		
		tdto.setVisit_date(visit_date);
		tdto.setEa(teenager_ea);
		tdto.setTicket_seq(teenager_seq);
		tdto.setPrice(teenager_price);
		tdto.setBenefit_seq(benefit_seq);
		
		if (!adto.getEa().equals("0")) {
			int result = singleService.personalReservation(adto);
			
			if (result == 1) {
				String seq = singleService.getPersonal();
				
				Map<String, String> map = new HashMap<>();
				
				map.put("seq", seq);
				map.put("user_seq", user_seq);
				
				result = singleService.addUserBook(map);
			}
		}
		
		if (!tdto.getEa().equals("0")) {
			int result = singleService.personalReservation(tdto);
			
			if (result == 1) {
				String seq = singleService.getPersonal();
				
				Map<String, String> map = new HashMap<>();
				
				map.put("seq", seq);
				map.put("user_seq", user_seq);
				
				result = singleService.addUserBook(map);
			}
		}
		
		return "redirect:/index.do";
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
