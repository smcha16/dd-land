package com.project.dd.shop.giftshop.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.shop.giftshop.domain.GiftshopImageDTO;
import com.project.dd.shop.giftshop.domain.ShopDTO;
import com.project.dd.shop.giftshop.mapper.ShopMapper;

/**
 * 선물샵 관련 데이터베이스 처리를 위한 DAO 구현 클래스입니다.
 * @author pega0
 *
 */
@Repository
@Primary
public class GiftshopDAOImpl implements GiftshopDAO {

	@Autowired
	private ShopMapper mapper;
	
	/**
     * 기프트샵 목록을 가져오는 메서드입니다.
     *
     * @param map 페이징 및 검색 조건을 담은 Map 객체
     * @return 기프트샵 목록
     */
	@Override
	public List<ShopDTO> getList(Map<String, String> map) {
		return mapper.getList(map);
	}
	
	/**
     * 선택된 기프트샵의 상세 정보를 가져오는 메서드입니다.
     *
     * @param seq 선택된 기프트샵 번호
     * @return 선택된 기프트샵의 상세 정보
     */
	@Override
	public ShopDTO shopDetail(String seq) {
		return mapper.detail(seq);
	}
	
	/**
     * 선택된 기프트샵의 이미지 목록을 가져오는 메서드입니다.
     *
     * @param seq 선택된 기프트샵 번호
     * @return 선택된 기프트샵의 이미지 목록
     */
	@Override
	public List<GiftshopImageDTO> shopImg(String seq) {
		return mapper.image(seq);
	}
	
	/**
     * 기프트샵의 총 개수를 가져오는 메서드입니다.
     *
     * @param map 페이징 및 검색 조건을 담은 Map 객체
     * @return 기프트샵의 총 개수
     */
	@Override
	public int getTotalCount(Map<String, String> map) {
		return mapper.getTotalCount(map);
	}
	
	/**
     * 기프트샵 이미지 목록을 가져오는 메서드입니다.
     *
     * @return 기프트샵 이미지 목록
     */
	@Override
	public List<GiftshopImageDTO> getImgList() {
		return mapper.getImgList();
	}
	
	/**
     * 기프트샵에 연결된 상품들의 가격을 0으로 초기화하는 메서드입니다.
     *
     * @param seq 선택된 기프트샵 번호
     */
	@Override
	public void delItems(String seq) {
		mapper.delItems(seq);
	}
	
	/**
     * 기프트샵 위치 정보를 초기화하는 메서드입니다.
     *
     * @param seq 선택된 기프트샵 번호
     * @return 초기화된 선물샵 위치 정보의 개수
     */
	@Override
	public int delGiftshop(String seq) {
		return mapper.delGiftshop(seq);
	}
	
}
