package com.project.dd.pb.price.persistence;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.dd.pb.price.domain.PriceDTO;

@Repository
public class PriceDAOImpl implements PriceDAO{
	
	@Autowired
	private SqlSessionTemplate template;
	
	@Override
	public List<PriceDTO> list() {
		
		
		
		return template.selectList("price.list");
	}

}
