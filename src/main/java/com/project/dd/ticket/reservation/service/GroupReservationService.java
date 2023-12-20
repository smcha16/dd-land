package com.project.dd.ticket.reservation.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.project.dd.ticket.reservation.domain.TicketReservationDTO;
import com.project.dd.ticket.reservation.repository.ReservationDAO;

/**
 * 그룹 예약과 관련된 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * @author pega0
 *
 */
@Service
public class GroupReservationService {

	@Autowired
	@Qualifier("GroupReservationDAOImpl")
	private ReservationDAO dao;

	/**
     * 우편번호, 기본 주소, 상세 주소를 조합하여 전체 주소를 반환합니다.
     *
     * @param postCode      우편번호
     * @param addressBasis  기본 주소
     * @param addressDetail 상세 주소
     * @return 전체 주소
     */
	public String getAddress(String postCode, String addressBasis, String addressDetail) {

		postCode = postCode.trim();
        addressBasis = addressBasis.trim();
        addressDetail = addressDetail.trim();
		
		return postCode + " " + addressBasis + " " + addressDetail;
	}

	/**
     * 그룹 예약을 수행합니다.
     *
     * @param dto 티켓 예약 정보를 담은 객체
     * @return 예약 성공 여부를 나타내는 값 (1: 성공, 0: 실패)
     */
	public int groupReservation(TicketReservationDTO dto) {
		return dao.reservation(dto);
	}

	/**
     * 예약 그룹의 일련 번호를 반환합니다.
     *
     * @return 예약 그룹의 일련 번호
     */
	public String getGroup() {
		return dao.getSeq();
	}

	/**
     * 사용자의 예약 그룹 정보를 추가합니다.
     *
     * @param map 사용자와 예약 그룹의 매핑 정보
     * @return 사용자 예약 그룹 추가 성공 여부를 나타내는 값 (1: 성공, 0: 실패)
     */
	public int addUserGroup(Map<String, String> map) {
		return dao.addUserBook(map);
	}
	
}
