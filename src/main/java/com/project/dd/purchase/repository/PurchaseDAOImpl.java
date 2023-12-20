package com.project.dd.purchase.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.purchase.domain.PurchaseDTO;
import com.project.dd.purchase.mapper.PurchaseMapper;
import com.project.dd.shop.item.domain.ItemDTO;

/**
 * 구매 정보에 대한 데이터베이스 처리를 위한 DAO 구현체입니다.
 * @author pega0
 *
 */
@Repository
@Primary
public class PurchaseDAOImpl implements PurchaseDAO {

	@Autowired
	private PurchaseMapper mapper;
	
	/**
     * 구매 정보를 데이터베이스에 추가합니다.
     *
     * @param dto 구매 정보를 담고 있는 PurchaseDTO 객체
     * @return 데이터베이스에 추가된 행의 수
     */
	@Override
	public int addBuy(PurchaseDTO dto) {
		return mapper.addBuy(dto);
	}
	
	/**
     * 최근에 추가된 구매 정보의 일련번호를 조회합니다.
     *
     * @return 최근에 추가된 구매 정보의 일련번호
     */
	@Override
	public String getSeq() {
		return mapper.getSeq();
	}
	
	/**
     * 사용자의 구매 정보를 데이터베이스에 추가합니다.
     *
     * @param map 사용자 일련번호와 구매 일련번호를 담고 있는 Map 객체
     * @return 데이터베이스에 추가된 행의 수
     */
	@Override
	public int addUserBuy(Map<String, String> map) {
		return mapper.addUserBuy(map);
	}
	
	/**
     * 카트에서 해당 구매 정보를 조회합니다.
     *
     * @param seq 구매 정보의 일련번호
     * @return 조회된 구매 정보를 담고 있는 ItemDTO 객체
     */
	@Override
	public ItemDTO getCart(String seq) {
		return mapper.getCart(seq);
	}
	
}
