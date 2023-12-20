package com.project.dd.shop.giftshop.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.shop.giftshop.domain.GiftshopImageDTO;
import com.project.dd.shop.giftshop.domain.ShopDTO;

/**
 * 선물샵 관련 데이터베이스 처리를 위한 DAO 인터페이스입니다.
 * @author pega0
 *
 */
public interface GiftshopDAO {

	/**
     * 기프트샵샵 목록을 가져오는 메서드입니다.
     *
     * @param map 페이징 및 검색 조건을 담은 Map 객체
     * @return 기프트샵 목록
     */
	List<ShopDTO> getList(Map<String, String> map);

	/**
     * 선택된 기프트샵의 상세 정보를 가져오는 메서드입니다.
     *
     * @param seq 선택된 기프트샵 번호
     * @return 선택된 기프트샵의 상세 정보
     */
	ShopDTO shopDetail(String seq);

	/**
     * 선택된 기프트샵의 이미지 목록을 가져오는 메서드입니다.
     *
     * @param seq 선택된 기프트샵 번호
     * @return 선택된 기프트샵의 이미지 목록
     */
	List<GiftshopImageDTO> shopImg(String seq);

	/**
     * 기프트샵의 총 개수를 가져오는 메서드입니다.
     *
     * @param map 페이징 및 검색 조건을 담은 Map 객체
     * @return 기프트샵의 총 개수
     */
	int getTotalCount(Map<String, String> map);

	/**
     * 기프트샵 이미지 목록을 가져오는 메서드입니다.
     *
     * @return 기프트샵 이미지 목록
     */
	List<GiftshopImageDTO> getImgList();

	/**
     * 기프트샵에 연결된 상품들의 가격을 0으로 초기화하는 메서드입니다.
     *
     * @param seq 선택된 기프트샵 번호
     */
	void delItems(String seq);

	/**
     * 기프트샵 위치 정보를 초기화하는 메서드입니다.
     *
     * @param seq 선택된 기프트샵 번호
     * @return 초기화된 기프트샵 위치 정보의 개수
     */
	int delGiftshop(String seq);

}
