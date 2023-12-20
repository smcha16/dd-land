package com.project.dd.purchase.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.project.dd.purchase.domain.PurchaseDTO;
import com.project.dd.shop.item.domain.ItemDTO;

/**
 * 구매 정보에 대한 데이터베이스 처리를 위한 Mapper 인터페이스입니다.
 * @author pega0
 *
 */
public interface PurchaseMapper {
	
	/**
     * 구매 정보를 데이터베이스에 등록합니다.
     *
     * @param dto 구매 정보를 담고 있는 PurchaseDTO 객체
     * @return 데이터베이스에 등록된 행 수
     */
	int addBuy(PurchaseDTO dto);

	/**
     * 현재까지의 최대 구매 일련번호를 조회합니다.
     *
     * @return 최대 구매 일련번호
     */
	@Select("select max(buy_seq) as seq from tblBuy")
	String getSeq();

	/**
     * 사용자의 구매 내역을 등록합니다.
     *
     * @param map 사용자 일련번호와 구매 일련번호를 담은 Map 객체
     * @return 데이터베이스에 등록된 행 수
     */
	int addUserBuy(Map<String, String> map);

	/**
     * 장바구니에서 선택한 상품의 정보를 조회합니다.
     *
     * @param seq 장바구니 일련번호
     * @return 장바구니에서 선택한 상품의 정보를 담은 ItemDTO 객체
     */
	ItemDTO getCart(String seq);

}
