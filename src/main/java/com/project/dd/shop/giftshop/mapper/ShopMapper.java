package com.project.dd.shop.giftshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.project.dd.shop.giftshop.domain.ShopDTO;

public interface ShopMapper {
	@Select("select * from vwGiftshop where lat != 0")
	public List<ShopDTO> getList();
}
