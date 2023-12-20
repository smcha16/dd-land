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

/**
 * 회원 예약 컨트롤러입니다.
 * @author pega0
 *
 */
@Controller
public class MemberTicketReservationController {
	
	@Autowired
	private SingleReservationService singleService;
	
	@Autowired
	private GroupReservationService groupService;
	
	/**
	 * 개인 예매 페이지를 조회합니다.
	 * @param model 모델 객체
	 * @return 개인 예매 페이지 경로
	 */
	@GetMapping(value = "/member/ticket/personal-reservation/view.do")
	public String singleReservation(Model model) {

		List<BenefitDTO> list = singleService.getBenefit("일반");
		List<BenefitDTO> plist = singleService.getBenefit("카드/통신사");
		
		model.addAttribute("list", list);
		model.addAttribute("plist", plist);
		
		return "member/ticket/personal-reservation/view";
	}
	
	/**
	 * 개인 예매 정보를 확인하는 페이지로 이동합니다.
	 * @param model 모델 객체
	 * @param dto 티켓예약 정보
	 * @param bdto 혜택 정보
	 * @param ticket_type 티켓 타입
	 * @param adult 성인 인원
	 * @param teenager 청소년 인원
	 * @return 개인 예매 확인 페이지 경로
	 */
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
	
	/**
	 * 개인 티켓 예약을 완료하고 메인 페이지로 리다이렉트합니다.
	 * @param model 모델 객체
	 * @param visit_date 방문 날짜
	 * @param adult_ea 성인 인원
	 * @param adult_seq 성인 티켓 번호
	 * @param teenager_ea 청소년 인원
	 * @param teenager_seq 청소년 티켓 번호
	 * @param adult_price 성인 총 가격
	 * @param teenager_price 청소년 총 가격
	 * @param benefit_seq 혜택 번호
	 * @param user_seq 유저 번호
	 * @return 메인 페이지 리다이렉트
	 */
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
	
	/**
	 * 단체 예매 페이지를 조회합니다.
	 * @param model 모델 객체
	 * @return 단체 예매 페이지 경로
	 */
	@GetMapping(value = "/member/ticket/group-reservation/view.do")
	public String groupReservation(Model model) {

		return "member/ticket/group-reservation/view";
	}
	
	/**
	 * 단체 예매를 완료하고 메인 페이지로 리다이렉트합니다.
	 * @param model 모델 객체
	 * @param dto 티켓 예약 정보
	 * @param postCode 우편번호
	 * @param addressBasis 주소
	 * @param addressDetail 주소 상세정보
	 * @param user_seq 유저 번호
	 * @param auth 로그인한 유저 객체
	 * @return 메인 페이지 리다이렉트
	 */
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
