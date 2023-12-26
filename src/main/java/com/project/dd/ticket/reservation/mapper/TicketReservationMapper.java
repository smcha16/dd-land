package com.project.dd.ticket.reservation.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.ticket.reservation.domain.TicketReservationDTO;

public interface TicketReservationMapper {

	/**
	 * 단체 예약 정보를 데이터베이스에 저장합니다.
	 * @param dto 티켓 예약 정보
	 * @return 데이터베이스에 저장된 행의 수
	 */
	int groupReservation(TicketReservationDTO dto);

	/**
	 * 그룹 예약 정보 중 예약 번호를 조회합니다.
	 * @return 조회된 예약 번호
	 */
	String getGroupSeq();

	/**
     * 그룹 예약 정보에 대한 사용자 예매 정보를 데이터베이스에 저장합니다.
     *
     * @param map 사용자 예매 정보를 담은 맵
     * @return 데이터베이스에 저장된 행의 수
     */
	int addUserGroup(Map<String, String> map);

	/**
     * 혜택 정보 목록을 조회합니다.
     *
     * @param type 혜택 유형
     * @return 조회된 혜택 정보 목록
     */
	List<BenefitDTO> getList(String type);

	/**
     * 가격 정보를 조회합니다.
     *
     * @param type 티켓 유형
     * @param age 연령대
     * @return 조회된 가격 정보
     */
	@Select("select * from tblticket where ticket_type = #{type} and age = #{age} and person_type = '개인'")
	PriceDTO getPrice(@Param("type") String type, @Param("age") String age);

	/**
     * 개별 예약 정보를 데이터베이스에 저장합니다.
     *
     * @param dto 예약 정보를 담은 객체
     * @return 데이터베이스에 저장된 행의 수
     */
	int personalReservation(TicketReservationDTO dto);

	/**
     * 개별 예약 정보 중 예약 번호를 조회합니다.
     *
     * @return 조회된 예약 번호
     */
	String getPersonalSeq();

	/**
     * 개별 예약 정보에 대한 사용자 예매 정보를 데이터베이스에 저장합니다.
     *
     * @param map 사용자 예매 정보를 담은 맵
     * @return 데이터베이스에 저장된 행의 수
     */
	int addUserBook(Map<String, String> map);

}
