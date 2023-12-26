package com.project.dd.shop.giftshop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.dd.shop.giftshop.domain.GiftshopImageDTO;
import com.project.dd.shop.giftshop.domain.ShopDTO;

/**
 * 기프트샵 관련 데이터베이스 쿼리를 처리하는 매퍼 인터페이스입니다.
 * @author pega0
 *
 */
public interface ShopMapper {
	
	/**
     * 기프트샵 목록을 가져오는 메서드입니다.
     *
     * @param map 페이징 및 검색 조건을 담은 Map 객체
     * @return 선물샵 상품 목록
     */
	public List<ShopDTO> getList(Map<String, String> map);

	/**
     * 선택된 기프트샵의 상세 정보를 가져오는 메서드입니다.
     *
     * @param seq 선택된 기프트샵  번호
     * @return 선택된 기프트샵의 상세 정보
     */
	@Select("select * from vwGiftshop where shop_seq = #{seq}")
	public ShopDTO detail(@Param("seq") String seq);

	/**
     * 선택된 기프트샵의 이미지 목록을 가져오는 메서드입니다.
     *
     * @param seq 선택된 기프트샵 번호
     * @return 선택된 기프트샵의 이미지 목록
     */
	@Select("select * from tblshopimg where shop_seq = #{seq}")
	public List<GiftshopImageDTO> image(@Param("seq") String seq);
	
	/**
     * 기프트샵의 총 개수를 가져오는 메서드입니다.
     *
     * @param map 페이징 및 검색 조건을 담은 Map 객체
     * @return 기프트샵의 총 개수
     */
	public int getTotalCount(Map<String, String> map);

	/**
     * 기프트샵 이미지 목록을 가져오는 메서드입니다.
     *
     * @return 기프트샵 이미지 목록
     */
	@Select("select * from tblShopImg")
	public List<GiftshopImageDTO> getImgList();

	/**
     * 기프트샵에 연결된 상품들의 가격을 0으로 초기화하는 메서드입니다.
     *
     * @param seq 선택된 기프트샵 번호
     */
	@Update("update tblitem set price = 0 where shop_seq = #{seq}")
	public void delItems(@Param("seq") String seq);

	/**
     * 기프트샵 위치 정보를 초기화하는 메서드입니다.
     *
     * @param seq 선택된 기프트샵 번호
     * @return 초기화된 기프트샵 위치 정보의 개수
     */
	@Update("update tblShopLocation set lat = 0, lng = 0 where shop_seq = #{seq}")
	public int delGiftshop(@Param("seq") String seq);
}
