package com.project.dd.cart.mapper;

import java.util.List;

import com.project.dd.cart.domain.CartDTO;

/**
 * 장바구니 데이터에 접근하는 매퍼 인터페이스입니다.
 * @author pega0
 *
 */
public interface CartMapper {

	/**
     * 사용자의 장바구니 목록을 조회합니다.
     *
     * @param user_seq 사용자의 일련번호
     * @return 사용자의 장바구니 목록
     */
	List<CartDTO> getUserList(String user_seq);

	/**
     * 선택한 장바구니 항목을 삭제합니다.
     *
     * @param seq 삭제할 장바구니 항목의 일련번호
     * @return 삭제된 행의 수
     */
	int delCart(String seq);

}
