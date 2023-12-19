package com.project.dd.shop.giftshop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.dd.shop.giftshop.domain.GiftshopImageDTO;
import com.project.dd.shop.giftshop.domain.ShopDTO;

public interface ShopMapper {
	
	public List<ShopDTO> getList(Map<String, String> map);

	@Select("select * from vwGiftshop where shop_seq = #{seq}")
	public ShopDTO detail(@Param("seq") String seq);

	@Select("select * from tblshopimg where shop_seq = #{seq}")
	public List<GiftshopImageDTO> image(@Param("seq") String seq);
	
	public int getTotalCount(Map<String, String> map);

	@Select("select * from tblShopImg")
	public List<GiftshopImageDTO> getImgList();

	@Update("update tblitem set price = 0 where shop_seq = #{seq}")
	public void delItems(@Param("seq") String seq);

	@Update("update tblShopLocation set lat = 0, lng = 0 where shop_seq = #{seq}")
	public int delGiftshop(@Param("seq") String seq);
}
