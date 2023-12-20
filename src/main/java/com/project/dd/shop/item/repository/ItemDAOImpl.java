package com.project.dd.shop.item.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.shop.item.domain.ItemDTO;
import com.project.dd.shop.item.domain.ItemImgDTO;
import com.project.dd.shop.item.mapper.ItemMapper;

/**
 * 아이템 관련 데이터베이스 처리를 위한 DAO 구현 클래스입니다.
 * @author pega0
 *
 */
@Repository
@Primary
public class ItemDAOImpl implements ItemDAO {
	
	@Autowired
	private ItemMapper mapper;
	
	/**
     * 특정 상점의 존재하는 상품의 총 개수를 반환하는 메서드입니다.
     *
     * @param seq 조회할 상품이 속한 상점의 번호
     * @return 상품의 총 개수
     */
	@Override
	public int getTotalCount(String seq) {
		return mapper.getTotalCount(seq);
	}
	
	/**
     * 특정 상점의 상품 목록을 조회하는 메서드입니다.
     *
     * @param map 페이징 및 검색 조건이 포함된 맵
     * @return 상품 목록
     */
	@Override
	public List<ItemDTO> getList(Map<String, String> map) {
		return mapper.getList(map);
	}
	
	/**
     * 특정 상품의 상세 정보를 조회하는 메서드입니다.
     *
     * @param seq 조회할 상품의 번호
     * @return 상품의 상세 정보
     */
	@Override
	public ItemDTO getItem(String seq) {
		return mapper.getItem(seq);
	}
	
	/**
     * 특정 상품의 이미지 목록을 조회하는 메서드입니다.
     *
     * @param seq 조회할 상품의 번호
     * @return 상품의 이미지 목록
     */
	@Override
	public List<ItemImgDTO> getImg(String seq) {
		return mapper.getImg(seq);
	}
	
	/**
     * 페이징 및 검색 조건이 포함된 전체 상품 목록을 조회하는 메서드입니다.
     *
     * @param map 페이징 및 검색 조건이 포함된 맵
     * @return 전체 상품 목록
     */
	@Override
	public List<ItemDTO> getFullList(Map<String, String> map) {
		return mapper.getFullList(map);
	}
	
	/**
     * 상품 이미지 목록을 조회하는 메서드입니다.
     *
     * @return 상품 이미지 목록
     */
	@Override
	public List<ItemImgDTO> getImgList() {
		return mapper.getImgList();
	}
	
	/**
     * 페이징 및 검색 조건이 포함된 전체 상품의 총 개수를 반환하는 메서드입니다.
     *
     * @param map 페이징 및 검색 조건이 포함된 맵
     * @return 전체 상품의 총 개수
     */
	@Override
	public int getTotalCounts(Map<String, String> map) {
		return mapper.getTotalCounts(map);
	}
	
	/**
     * 장바구니에 상품이 이미 존재하는지 확인하는 메서드입니다.
     *
     * @param dto 장바구니에 추가할 상품 정보
     * @return 장바구니에 상품이 이미 존재하면 해당 상품의 정보, 그렇지 않으면 null
     */
	@Override
	public ItemDTO checkCart(ItemDTO dto) {
		return mapper.checkCart(dto);
	}
	
	/**
     * 장바구니에 상품을 추가하는 메서드입니다.
     *
     * @param dto 장바구니에 추가할 상품 정보
     * @return 장바구니 추가 결과 (1: 성공, 0: 실패)
     */
	@Override
	public int addCart(ItemDTO dto) {
		return mapper.addCart(dto);
	}
	
	/**
     * 장바구니에 상품 수량을 수정하는 메서드입니다.
     *
     * @param dto 장바구니에 수정할 상품 정보
     * @return 장바구니 수정 결과 (1: 성공, 0: 실패)
     */
	@Override
	public int editCart(ItemDTO dto) {
		return mapper.editCart(dto);
	}
	
	/**
     * 장바구니에 사용될 새로운 번호를 조회하는 메서드입니다.
     *
     * @return 새로운 장바구니 번호
     */
	@Override
	public String getSeq() {
		return mapper.getCartSeq();
	}
	
	/**
     * 사용자의 장바구니에 상품을 추가하는 메서드입니다.
     *
     * @param dto 사용자의 장바구니에 추가할 상품 정보
     * @return 사용자 장바구니 추가 결과 (1: 성공, 0: 실패)
     */
	@Override
	public int addUserCart(ItemDTO dto) {
		return mapper.addUserCart(dto);
	}
	
	/**
     * 상품을 삭제하는 메서드입니다.
     *
     * @param seq 삭제할 상품의 번호
     * @return 상품 삭제 결과 (1: 성공, 0: 실패)
     */
	@Override
	public int delItem(String seq) {
		return mapper.delItem(seq);
	}
	
	/**
	 * 
	 * @param seq 기프트샵의 번호
	 */
	@Override
	public String[] getItemSeqs(String seq) {
		return mapper.getItemSeqs(seq);
	}
	
	/**
     * 특정 상품 번호 배열에 해당하는 사용자 장바구니 정보를 삭제하는 메서드입니다.
     *
     * @param seq 특정 상품 번호 배열
     */
	@Override
	public void delUserCart(String cart_seq) {
		mapper.delUserCart(cart_seq);
	}
}
