package com.project.dd.ticket.reservation.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.ticket.reservation.domain.TicketReservationDTO;
import com.project.dd.ticket.reservation.mapper.TicketReservationMapper;

/**
 * 사용자 예약과 관련된 데이터 액세스를 위한 구현 클래스입니다.
 * @author pega0
 *
 */
@Repository("UserReservationDAOImpl")
@Primary
public class UserReservationDAOImpl implements ReservationDAO {

	@Autowired
	private TicketReservationMapper mapper;
	
	/**
     * 지정된 유형의 혜택 목록을 반환합니다.
     *
     * @param type 혜택 유형
     * @return 혜택 목록
     */
	@Override
	public List<BenefitDTO> getBenefit(String type) {
		return mapper.getList(type);
	}
	
	/**
     * 지정된 유형과 나이에 해당하는 티켓 가격 정보를 반환합니다.
     *
     * @param type 티켓 유형
     * @param age  나이
     * @return 티켓 가격 정보
     */
	@Override
	public PriceDTO getPrice(String type, String age) {
		return mapper.getPrice(type, age);
	}
	
	/**
     * 티켓 예약을 수행합니다.
     *
     * @param dto 티켓 예약 정보를 담은 객체
     * @return 예약 성공 여부를 나타내는 값 (1: 성공, 0: 실패)
     */
	@Override
	public int reservation(TicketReservationDTO dto) {
		return mapper.personalReservation(dto);
	}

	/**
     * 예약 개인의 일련 번호를 반환합니다.
     *
     * @return 예약 개인의 일련 번호
     */
	@Override
	public String getSeq() {
		return mapper.getPersonalSeq();
	}

	/**
     * 사용자의 예약 정보를 추가합니다.
     *
     * @param map 사용자와 예약 개인의 매핑 정보
     * @return 사용자 예약 추가 성공 여부를 나타내는 값 (1: 성공, 0: 실패)
     */
	@Override
	public int addUserBook(Map<String, String> map) {
		return mapper.addUserBook(map);
	}
}
