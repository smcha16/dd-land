package com.project.dd.ticket.reservation.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.ticket.reservation.domain.TicketReservationDTO;

public interface TicketReservationMapper {

	int groupReservation(TicketReservationDTO dto);

	String getGroupSeq();

	int addUserGroup(Map<String, String> map);

	List<BenefitDTO> getList(String type);

	@Select("select * from tblticket where ticket_type = #{type} and age = #{age} and person_type = '개인'")
	PriceDTO getPrice(@Param("type") String type, @Param("age") String age);

	int personalReservation(TicketReservationDTO dto);

	String getPersonalSeq();

	int addUserBook(Map<String, String> map);

}
