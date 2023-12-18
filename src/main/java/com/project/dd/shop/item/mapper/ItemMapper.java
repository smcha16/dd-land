package com.project.dd.shop.item.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.dd.shop.item.domain.ItemDTO;
import com.project.dd.shop.item.domain.ItemImgDTO;

public interface ItemMapper {

	@Select("select count(*) from tblitem where shop_seq = #{seq} and price != 0")
	int getTotalCount(@Param("seq") String seq);

	List<ItemDTO> getList(Map<String, String> map);

	@Select("select * from tblItem where item_seq = #{seq}")
	ItemDTO getItem(@Param("seq") String seq);

	@Select("select * from tblItemImg where item_seq = #{seq}")
	List<ItemImgDTO> getImg(@Param("seq") String seq);

	List<ItemDTO> getFullList(Map<String, String> map);

	@Select("select * from tblItemImg")
	List<ItemImgDTO> getImgList();

	@Select("select count(*) from tblitem where price != 0")
	int getTotalCounts();

	ItemDTO checkCart(ItemDTO dto);
	
	int addCart(ItemDTO dto);

	int editCart(ItemDTO dto);

	@Select("select max(cart_seq) as cart_seq from tblcart")
	String getCartSeq();

	int addUserCart(ItemDTO dto);

	int delItem(String seq);

	@Select("select cart_seq from tblCart where item_seq = #{seq}")
	String[] getItemSeqs(@Param("seq") String seq);

	@Delete("delete from tblusercart where cart_seq = #{seq}")
	void delUserCart(@Param("seq") String cart_seq);

}
