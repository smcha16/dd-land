package com.project.dd.ticket.reservation.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.ticket.reservation.domain.TicketReservationDTO;

public interface TicketReservationMapper {

	int groupReservation(TicketReservationDTO dto);

	String getGroupSeq();

	int addUserGroup(Map<String, String> map);

	List<BenefitDTO> getList(String type);

}
