package com.project.dd.shop.item.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.dd.shop.item.domain.ItemDTO;

public interface ItemMapper {

	@Select("select count(*) from tblitem where item_seq = #{seq} and name not like '%종료%'")
	int getTotalCount(@Param("seq") String seq);

	List<ItemDTO> getList(Map<String, String> map);

	@Select("select * from tblItem where item_seq = #{seq}")
	ItemDTO getItem(@Param("seq") String seq);

}
