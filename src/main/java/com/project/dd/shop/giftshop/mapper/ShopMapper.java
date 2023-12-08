package com.project.dd.shop.giftshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.dd.shop.giftshop.domain.GiftshopImageDTO;
import com.project.dd.shop.giftshop.domain.ShopDTO;

public interface ShopMapper {
	@Select("select * from vwGiftshop where lat != 0")
	public List<ShopDTO> getList();

	@Select("select * from vwGiftshop where shop_seq = #{seq}")
	public ShopDTO detail(@Param("seq") String seq);

	@Select("select * from tblshopimg where shop_seq = #{seq}")
	public List<GiftshopImageDTO> image(@Param("seq") String seq);
}
