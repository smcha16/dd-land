package com.project.dd.shop.item.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.shop.item.domain.ItemDTO;
import com.project.dd.shop.item.mapper.ItemMapper;

@Repository
@Primary
public class ItemDAOImpl implements ItemDAO {
	
	@Autowired
	private ItemMapper mapper;
	
	@Override
	public int getTotalCount(String seq) {
		return mapper.getTotalCount(seq);
	}
	
	@Override
	public List<ItemDTO> getList(Map<String, String> map) {
		return mapper.getList(map);
	}
	
	@Override
	public ItemDTO getItem(String seq) {
		return mapper.getItem(seq);
	}
	
}
