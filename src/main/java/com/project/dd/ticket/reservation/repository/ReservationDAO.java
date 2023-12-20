package com.project.dd.ticket.reservation.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.ticket.reservation.domain.TicketReservationDTO;

/**
 * 예약과 관련된 데이터 액세스를 위한 인터페이스입니다.
 * @author pega0
 *
 */
public interface ReservationDAO {

	/**
     * 티켓 예약을 수행합니다.
     *
     * @param dto 티켓 예약 정보를 담은 객체
     * @return 예약 성공 여부를 나타내는 값 (1: 성공, 0: 실패)
     */
	int reservation(TicketReservationDTO dto);

	/**
     * 예약의 일련 번호를 반환합니다.
     *
     * @return 예약의 일련 번호
     */
	String getSeq();

	/**
     * 사용자의 예약 정보를 추가합니다.
     *
     * @param map 사용자와 예약 그룹의 매핑 정보
     * @return 사용자 예약 추가 성공 여부를 나타내는 값 (1: 성공, 0: 실패)
     */
	int addUserBook(Map<String, String> map);

	/**
     * 지정된 유형의 혜택 목록을 반환합니다.
     *
     * @param type 혜택 유형
     * @return 혜택 목록
     */
	List<BenefitDTO> getBenefit(String string);

	/**
     * 지정된 유형과 나이에 해당하는 티켓 가격 정보를 반환합니다.
     *
     * @param type 티켓 유형
     * @param age  나이
     * @return 티켓 가격 정보
     */
	PriceDTO getPrice(String type, String age);

}
