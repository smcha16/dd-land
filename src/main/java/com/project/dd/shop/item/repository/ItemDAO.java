package com.project.dd.shop.item.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.shop.item.domain.ItemDTO;
import com.project.dd.shop.item.domain.ItemImgDTO;

/**
 * 아이템 관련 데이터베이스 처리를 위한 DAO 인터페이스입니다.
 * @author pega0
 *
 */
public interface ItemDAO {

	/**
     * 특정 상점의 존재하는 상품의 총 개수를 반환하는 메서드입니다.
     *
     * @param seq 조회할 상품이 속한 상점의 시퀀스
     * @return 상품의 총 개수
     */
	int getTotalCount(String seq);

	/**
     * 특정 상점의 상품 목록을 조회하는 메서드입니다.
     *
     * @param map 페이징 및 검색 조건이 포함된 맵
     * @return 상품 목록
     */
	List<ItemDTO> getList(Map<String, String> map);

	/**
     * 특정 상품의 상세 정보를 조회하는 메서드입니다.
     *
     * @param seq 조회할 상품의 시퀀스
     * @return 상품의 상세 정보
     */
	ItemDTO getItem(String seq);

	/**
     * 특정 상품의 이미지 목록을 조회하는 메서드입니다.
     *
     * @param seq 조회할 상품의 시퀀스
     * @return 상품의 이미지 목록
     */
	List<ItemImgDTO> getImg(String seq);

	/**
     * 전체 상품 목록을 조회하는 메서드입니다.
     *
     * @param map 페이징 및 검색 조건이 포함된 맵
     * @return 전체 상품 목록
     */
	List<ItemDTO> getFullList(Map<String, String> map);

	/**
     * 전체 상품 이미지 목록을 조회하는 메서드입니다.
     *
     * @return 전체 상품 이미지 목록
     */
	List<ItemImgDTO> getImgList();

	/**
	 * 아이템의 페이징을 위해 개수를 조회하는 메서드입니다.
	 * @param map 아이템 페이징 정보
	 * @return 총 개수
	 */
	int getTotalCounts(Map<String, String> map);
	
	/**
     * 특정 상품의 장바구니에 담긴 상태인지 확인하는 메서드입니다.
     *
     * @param dto 확인할 상품 정보가 담긴 DTO
     * @return 장바구니에 담겨있으면 해당 상품의 정보, 아니면 null 반환
     */
	ItemDTO checkCart(ItemDTO dto);

	/**
     * 장바구니에 상품을 추가하는 메서드입니다.
     *
     * @param dto 추가할 상품 정보가 담긴 DTO
     * @return 상품 추가 결과 (성공 시 1, 실패 시 0)
     */
	int addCart(ItemDTO dto);

	/**
     * 장바구니에 담긴 상품의 수량을 수정하는 메서드입니다.
     *
     * @param dto 수정할 상품 정보가 담긴 DTO
     * @return 상품 수량 수정 결과 (성공 시 1, 실패 시 0)
     */
	int editCart(ItemDTO dto);

	/**
     * 장바구니에 담긴 상품의 시퀀스를 반환하는 메서드입니다.
     *
     * @return 장바구니에 담긴 상품의 시퀀스
     */
	String getSeq();

	/**
     * 사용자의 장바구니에 상품을 추가하는 메서드입니다.
     *
     * @param dto 추가할 상품 정보가 담긴 DTO
     * @return 사용자 장바구니에 상품 추가 결과 (성공 시 1, 실패 시 0)
     */
	int addUserCart(ItemDTO dto);

	/**
     * 특정 상품을 삭제하는 메서드입니다.
     *
     * @param seq 삭제할 상품의 시퀀스
     * @return 상품 삭제 결과 (성공 시 1, 실패 시 0)
     */
	int delItem(String seq);

	/**
     * 특정 상품의 장바구니 시퀀스 배열을 반환하는 메서드입니다.
     *
     * @param seq 조회할 상품의 시퀀스
     * @return 상품의 장바구니 시퀀스 배열
     */
	String[] getItemSeqs(String seq);

	/**
     * 특정 상품이 장바구니에서 삭제될 때 사용자의 장바구니도 함께 삭제하는 메서드입니다.
     *
     * @param cart_seq 삭제할 상품의 장바구니 시퀀스
     */
	void delUserCart(String cart_seq);

}
