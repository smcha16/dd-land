package com.project.dd.ticket.reservation.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.ticket.reservation.domain.TicketBookDTO;
import com.project.dd.ticket.reservation.domain.TicketReservationDTO;
import com.project.dd.ticket.reservation.repository.ReservationDAO;

/**
 * 개인 예약과 관련된 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * @author pega0
 *
 */
@Service
public class SingleReservationService {

	@Autowired
	@Qualifier("UserReservationDAOImpl")
	private ReservationDAO dao;

	/**
     * 특정 유형의 혜택 정보를 가져옵니다.
     *
     * @param string 혜택 유형
     * @return 혜택 정보 리스트
     */
	public List<BenefitDTO> getBenefit(String string) {
		
		List<BenefitDTO> list = dao.getBenefit(string);
		
		for (BenefitDTO dto : list) {
			dto.setStart_date(dto.getStart_date().substring(0, 10));
			dto.setEnd_date(dto.getEnd_date().substring(0, 10));
		}
		
		return list;
	}

	/**
     * 특정 티켓 유형과 연령에 해당하는 가격 정보를 가져옵니다.
     *
     * @param type 티켓 유형
     * @param age  연령
     * @return 가격 정보
     */
	public PriceDTO getPrice(String type, String age) {
		return dao.getPrice(type, age);
	}

	/**
     * 개인 예약을 수행합니다.
     *
     * @param adto 티켓 예약 정보를 담은 객체
     * @return 예약 성공 여부를 나타내는 값 (1: 성공, 0: 실패)
     */
	public int personalReservation(TicketReservationDTO adto) {
		return dao.reservation(adto);
	}

	/**
     * 예약 개인의 일련 번호를 반환합니다.
     *
     * @return 예약 개인의 일련 번호
     */
	public String getPersonal() {
		return dao.getSeq();
	}

	/**
     * 사용자의 예약 개인 정보를 추가합니다.
     *
     * @param map 사용자와 예약 개인의 매핑 정보
     * @return 사용자 예약 개인 추가 성공 여부를 나타내는 값 (1: 성공, 0: 실패)
     */
	public int addUserBook(Map<String, String> map) {
		return dao.addUserBook(map);
	}
	
}
