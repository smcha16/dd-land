package com.project.dd.shop.item.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.shop.item.domain.ItemDTO;

public interface ItemDAO {

	int getTotalCount(String seq);

	List<ItemDTO> getList(Map<String, String> map);

	ItemDTO getItem(String seq);

}
