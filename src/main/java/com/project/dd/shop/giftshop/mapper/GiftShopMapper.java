package com.project.dd.shop.giftshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.project.dd.shop.giftshop.domain.GiftshopDTO;

public interface GiftshopMapper {

	@Select("select * from vwGiftshop where lat != 0")
	public List<GiftshopDTO> getList();

}
