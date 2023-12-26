package com.project.dd.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.cart.domain.CartDTO;
import com.project.dd.cart.repository.CartDAO;

/**
 * 장바구니 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 * @author pega0
 *
 */
@Service
public class CartService {

	@Autowired
	private CartDAO dao;
	
	/**
     * 사용자의 장바구니 목록을 조회합니다.
     *
     * @param user_seq 사용자의 일련번호
     * @return 사용자의 장바구니 목록
     */
	public List<CartDTO> getUserList(String user_seq) {
		return dao.getUserList(user_seq);
	}

	/**
     * 선택한 장바구니 항목을 삭제합니다.
     *
     * @param cart_seq 삭제할 장바구니 항목의 일련번호 배열
     * @return 삭제된 장바구니 항목의 총 수
     */
	public int delCart(String[] cart_seq) {
		int result = 0;
		
		for (String seq : cart_seq) {
			result += dao.delCart(seq);
		}
		
		return result;
	}

}
