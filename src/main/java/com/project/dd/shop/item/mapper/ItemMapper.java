package com.project.dd.shop.item.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.dd.shop.item.domain.ItemDTO;
import com.project.dd.shop.item.domain.ItemImgDTO;

/**
 * 상품과 관련된 데이터베이스 작업을 수행하는 매퍼 인터페이스입니다.
 * @author pega0
 *
 */
public interface ItemMapper {

	/**
     * 특정 상품 카테고리의 상품 수를 조회하는 메서드입니다.
     *
     * @param seq 상품 카테고리 번호
     * @return 상품 수
     */
	@Select("select count(*) from tblitem where shop_seq = #{seq} and price != 0")
	int getTotalCount(@Param("seq") String seq);

	/**
     * 상품 목록을 조회하는 메서드입니다.
     *
     * @param map 조회에 필요한 파라미터를 담은 맵
     * @return 상품 목록
     */
	List<ItemDTO> getList(Map<String, String> map);

	/**
     * 특정 상품의 상세 정보를 조회하는 메서드입니다.
     *
     * @param seq 조회할 상품의 번호
     * @return 상품의 상세 정보
     */
	@Select("select * from tblItem where item_seq = #{seq}")
	ItemDTO getItem(@Param("seq") String seq);

	/**
     * 특정 상품의 이미지 목록을 조회하는 메서드입니다.
     *
     * @param seq 조회할 상품의 번호
     * @return 상품 이미지 목록
     */
	@Select("select * from tblItemImg where item_seq = #{seq}")
	List<ItemImgDTO> getImg(@Param("seq") String seq);

	/**
     * 모든 상품의 목록을 조회하는 메서드입니다.
     *
     * @param map 조회에 필요한 파라미터를 담은 맵
     * @return 모든 상품의 목록
     */
	List<ItemDTO> getFullList(Map<String, String> map);

	/**
     * 모든 상품 이미지의 목록을 조회하는 메서드입니다.
     *
     * @return 모든 상품 이미지의 목록
     */
	@Select("select * from tblItemImg")
	List<ItemImgDTO> getImgList();

	/**
     * 특정 검색 조건에 맞는 상품 수를 조회하는 메서드입니다.
     *
     * @param map 조회에 필요한 파라미터를 담은 맵
     * @return 상품 수
     */
	int getTotalCounts(Map<String, String> map);

	/**
     * 장바구니에 상품이 이미 담겨있는지 확인하는 메서드입니다.
     *
     * @param dto 장바구니에 담을 상품 정보
     * @return 장바구니에 담겨있는 경우 해당 상품의 정보, 그렇지 않은 경우 null
     */
	ItemDTO checkCart(ItemDTO dto);
	
	/**
     * 장바구니에 상품을 추가하는 메서드입니다.
     *
     * @param dto 장바구니에 담을 상품 정보
     * @return 상품 추가 결과 (성공 시 1, 실패 시 0)
     */
	int addCart(ItemDTO dto);

	/**
     * 장바구니에 담긴 상품의 수량을 수정하는 메서드입니다.
     *
     * @param dto 장바구니에 담긴 상품 정보
     * @return 상품 수정 결과 (성공 시 1, 실패 시 0)
     */
	int editCart(ItemDTO dto);

	/**
     * 사용자의 장바구니에 담긴 상품의 번호를 조회하는 메서드입니다.
     *
     * @param seq 상품 번호
     * @return 장바구니에 담긴 상품의 번호 배열
     */
	@Select("select max(cart_seq) as cart_seq from tblcart")
	String getCartSeq();

	/**
     * 특정 상품이 사용자의 장바구니에 담겨있는 경우 해당 상품을 삭제하는 메서드입니다.
     *
     * @param seq 상품 번호
     */
	int addUserCart(ItemDTO dto);

	/**
     * 특정 상품의 장바구니에서 삭제하는 메서드입니다.
     *
     * @param seq 삭제할 상품의 번호
     * @return 상품 삭제 결과 (성공 시 1, 실패 시 0)
     */
	int delItem(String seq);

	/**
     * 특정 상품의 장바구니에 담긴 상품 번호 배열을 반환하는 메서드입니다.
     *
     * @param seq 조회할 상품의 번호
     * @return 상품 번호 배열
     */
	@Select("select cart_seq from tblCart where item_seq = #{seq}")
	String[] getItemSeqs(@Param("seq") String seq);

	/**
     * 특정 상품의 장바구니에서 삭제되었을 때 사용자 장바구니도 함께 삭제하는 메서드입니다.
     *
     * @param cart_seq 삭제할 상품의 장바구니 번호
     */
	@Delete("delete from tblusercart where cart_seq = #{seq}")
	void delUserCart(@Param("seq") String cart_seq);

}
