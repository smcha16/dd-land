package com.project.dd.shop.item.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.shop.item.domain.ItemDTO;
import com.project.dd.shop.item.domain.ItemImgDTO;

public interface ItemDAO {

	int getTotalCount(String seq);

	List<ItemDTO> getList(Map<String, String> map);

	ItemDTO getItem(String seq);

	List<ItemImgDTO> getImg(String seq);

	List<ItemDTO> getFullList(Map<String, String> map);

	List<ItemImgDTO> getImgList();

	int getTotalCounts();
	
	ItemDTO checkCart(ItemDTO dto);

	int addCart(ItemDTO dto);

	int editCart(ItemDTO dto);

	String getSeq();

	int addUserCart(ItemDTO dto);

	int delItem(String seq);

	String[] getItemSeqs(String seq);

	void delUserCart(String cart_seq);

}
