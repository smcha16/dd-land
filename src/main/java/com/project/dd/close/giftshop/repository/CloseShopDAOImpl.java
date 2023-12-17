package com.project.dd.close.giftshop.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.close.giftshop.domain.CloseGiftShopDTO;
import com.project.dd.close.giftshop.mapper.CloseShopMapper;

@Primary
@Repository
public class CloseShopDAOImpl  implements CloseShopDAO{
	
	@Autowired
	private CloseShopMapper mapper;

	@Override
	public int getTotalCount() {
		
		return mapper.getTotalCount();
	}

	@Override
	public List<CloseGiftShopDTO> list(Map<String, String> map) {
		
		return mapper.list(map);
	}

	@Override
	public List<CloseGiftShopDTO> shoplist() {
		
		return mapper.shoplist();
	}

	@Override
	public int addCloseShop(CloseGiftShopDTO dto) {
		
		return mapper.addCloseShop(dto);
	}

	@Override
	public CloseGiftShopDTO getOne(String seq) {
		
		return mapper.getOne(seq);
	}

	@Override
	public int editClose(CloseGiftShopDTO dto) {
		
		return mapper.editClose(dto);
	}

	@Override
	public void del(String seq) {
		mapper.del(seq);
	}

}
