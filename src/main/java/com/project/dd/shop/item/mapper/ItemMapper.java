package com.project.dd.shop.item.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.dd.shop.item.domain.ItemDTO;
import com.project.dd.shop.item.domain.ItemImgDTO;

public interface ItemMapper {

	@Select("select count(*) from tblitem where shop_seq = #{seq} and name not like '%종료%'")
	int getTotalCount(@Param("seq") String seq);

	List<ItemDTO> getList(Map<String, String> map);

	@Select("select * from tblItem where item_seq = #{seq}")
	ItemDTO getItem(@Param("seq") String seq);

	@Select("select * from tblItemImg where item_seq = #{seq}")
	List<ItemImgDTO> getImg(@Param("seq") String seq);

	List<ItemDTO> getFullList(Map<String, String> map);

	@Select("select * from tblItemImg")
	List<ItemImgDTO> getImgList();

	@Select("select count(*) from tblitem where name not like '%종료%'")
	int getTotalCounts();

}
