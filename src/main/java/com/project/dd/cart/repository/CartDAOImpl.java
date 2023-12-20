package com.project.dd.cart.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.cart.domain.CartDTO;
import com.project.dd.cart.mapper.CartMapper;

/**
 * 장바구니 데이터에 접근하는 DAO 구현체입니다.
 * @author pega0
 *
 */
@Repository
@Primary
public class CartDAOImpl implements CartDAO {

	@Autowired
	private CartMapper mapper;
	
	/**
     * 사용자의 장바구니 목록을 조회합니다.
     *
     * @param user_seq 사용자의 일련번호
     * @return 사용자의 장바구니 목록
     */
	@Override
	public List<CartDTO> getUserList(String user_seq) {
		return mapper.getUserList(user_seq);
	}
	
	/**
     * 선택한 장바구니 항목을 삭제합니다.
     *
     * @param seq 삭제할 장바구니 항목의 일련번호
     * @return 삭제된 행의 수
     */
	@Override
	public int delCart(String seq) {
		return mapper.delCart(seq);
	}
	
}
