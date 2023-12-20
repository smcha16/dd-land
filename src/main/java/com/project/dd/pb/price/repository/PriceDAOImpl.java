package com.project.dd.pb.price.repository;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.pb.price.mapper.PriceMapper;

import lombok.RequiredArgsConstructor;

/**
 * 가격 정보에 대한 데이터 액세스 객체(DAO) 구현 클래스.
 * @author 김형우
 */
@Primary
@Repository
@RequiredArgsConstructor
public class PriceDAOImpl implements PriceDAO {

	private final PriceMapper mapper;

	/**
	 * 개인 유형의 가격 목록을 조회하는 메서드.
	 * 
	 * @return 개인 유형의 가격 목록.
	 */
	@Override
	public List<PriceDTO> personTypeList() {
		return mapper.personTypeList();
	}

	/**
	 * 단체 유형의 가격 목록을 조회하는 메서드.
	 * 
	 * @return 단체 유형의 가격 목록.
	 */
	@Override
	public List<PriceDTO> groupTypeList() {
		return mapper.groupTypeList();
	}

	/**
	 * 전체 가격 목록을 조회하는 메서드.
	 * 
	 * @return 전체 가격 목록.
	 */
	@Override
	public List<PriceDTO> list() {
		return mapper.list();
	}

	/**
	 * 티켓 유형 목록을 조회하는 메서드.
	 * 
	 * @return 티켓 유형 목록.
	 */
	@Override
	public List<PriceDTO> tikcetTypeList() {
		return mapper.ticketTypeList();
	}

	/**
	 * 나이 목록을 조회하는 메서드.
	 * 
	 * @return 나이 목록.
	 */
	@Override
	public List<PriceDTO> ageList() {
		return mapper.ageList();
	}

	/**
	 * 특정 티켓에 대한 가격 정보를 조회하는 메서드.
	 * 
	 * @param ticket_seq 티켓 일련번호.
	 * @return 티켓에 대한 가격 정보.
	 */
	@Override
	public PriceDTO getPriceInfo(String ticket_seq) {
		return mapper.getPriceInfo(ticket_seq);
	}

	/**
	 * 가격 정보를 수정하는 메서드.
	 * 
	 * @param priceDTO 수정할 가격 정보를 담은 DTO 객체.
	 * @return 수정된 행의 수.
	 */
	@Override
	public int edit(PriceDTO priceDTO) {
		return mapper.edit(priceDTO);
	}
}
