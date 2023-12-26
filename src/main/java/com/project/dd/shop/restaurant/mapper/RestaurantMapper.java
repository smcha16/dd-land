package com.project.dd.shop.restaurant.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.dd.shop.restaurant.domain.RestaurantDTO;
import com.project.dd.shop.restaurant.domain.RestaurantImageDTO;

/**
 * 레스토랑과 관련된 데이터베이스 작업을 수행하는 매퍼 인터페이스입니다.
 * @author pega0
 *
 */
public interface RestaurantMapper {

	/**
     * 레스토랑 목록을 조회하는 메서드입니다.
     *
     * @param map 조회 조건을 담은 Map 객체
     * @return 레스토랑 목록
     */
	List<RestaurantDTO> list(Map<String, String> map);

	/**
     * 레스토랑 상세 정보를 조회하는 메서드입니다.
     *
     * @param seq 레스토랑 일련번호
     * @return 레스토랑 상세 정보
     */
	@Select("select * from vwRestaurant where restaurant_seq = #{seq}")
	RestaurantDTO detail(@Param("seq") String seq);

	/**
     * 레스토랑 이미지 목록을 조회하는 메서드입니다.
     *
     * @param seq 레스토랑 일련번호
     * @return 레스토랑 이미지 목록
     */
	@Select("select * from tblRestaurantImg where restaurant_seq = #{seq}")
	List<RestaurantImageDTO> image(@Param("seq") String seq);

	/**
     * 전체 레스토랑 수를 조회하는 메서드입니다.
     *
     * @param map 조회 조건을 담은 Map 객체
     * @return 전체 레스토랑 수
     */
	int getTotalCount(Map<String, String> map);

	/**
     * 레스토랑 이미지 목록을 조회하는 메서드입니다.
     *
     * @return 레스토랑 이미지 목록
     */
	@Select("select * from tblRestaurantImg")
	List<RestaurantImageDTO> getImgList();
	
	/**
     * 레스토랑 이름 중복을 체크하는 메서드입니다.
     *
     * @param dto 레스토랑 정보 객체
     * @return 중복 여부 (1: 중복, 0: 미중복)
     */
	int checkNameDuplication(RestaurantDTO dto);

	/**
     * 레스토랑을 추가하는 메서드입니다.
     *
     * @param dto 추가할 레스토랑 정보 객체
     * @return 추가된 레스토랑 수
     */
	int addRestaurant(RestaurantDTO dto);

	/**
     * 최근 추가된 레스토랑의 일련번호를 조회하는 메서드입니다.
     *
     * @return 최근 추가된 레스토랑의 일련번호
     */
	@Select("select max(restaurant_seq) as restaurant_seq from tblRestaurant")
	String getSeq();

	/**
     * 레스토랑 위치 정보를 추가하는 메서드입니다.
     *
     * @param dto 레스토랑 정보 객체
     */
	void addRestaurantLocation(RestaurantDTO dto);

	/**
     * 레스토랑 이미지를 추가하는 메서드입니다.
     *
     * @param dto 레스토랑 정보 객체
     */
	void addRestaurantImg(RestaurantDTO dto);

	/**
     * 레스토랑을 삭제하는 메서드입니다.
     *
     * @param seq 삭제할 레스토랑 일련번호
     * @return 삭제된 레스토랑 수
     */
	int delRestaurant(String seq);

}
