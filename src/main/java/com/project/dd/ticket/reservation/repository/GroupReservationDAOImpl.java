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
 * 그룹 예약과 관련된 데이터베이스 접근을 담당하는 DAO 구현체입니다.
 * @author pega0
 *
 */
@Repository("GroupReservationDAOImpl")
@Primary
public class GroupReservationDAOImpl implements ReservationDAO {
	
	@Autowired
	private TicketReservationMapper mapper;

	/**
     * 그룹 예약 정보를 데이터베이스에 저장합니다.
     *
     * @param dto 예약 정보를 담은 객체
     * @return 데이터베이스에 저장된 행의 수
     */
	@Override
	public int reservation(TicketReservationDTO dto) {
		return mapper.groupReservation(dto);
	}
	
	/**
     * 그룹 예약 정보 중 예약 번호를 조회합니다.
     *
     * @return 조회된 예약 번호
     */
	@Override
	public String getSeq() {
		return mapper.getGroupSeq();
	}
	
	/**
     * 그룹 예약 정보에 대한 사용자 예매 정보를 데이터베이스에 저장합니다.
     *
     * @param map 사용자 예매 정보를 담은 맵
     * @return 데이터베이스에 저장된 행의 수
     */
	@Override
	public int addUserBook(Map<String, String> map) {
		return mapper.addUserGroup(map);
	}
	
	/**
     * 혜택 정보 목록을 조회합니다. (해당 DAO에서는 사용하지 않음)
     *
     * @param string 혜택 유형
     * @return null (사용하지 않는 메서드)
     */
	@Override
	public List<BenefitDTO> getBenefit(String string) {
		return null;
	}
	
	/**
     * 가격 정보를 조회합니다. (해당 DAO에서는 사용하지 않음)
     *
     * @param type 티켓 유형
     * @param age  연령대
     * @return null (사용하지 않는 메서드)
     */
	@Override
	public PriceDTO getPrice(String type, String age) {
		return null;
	}
	
}
