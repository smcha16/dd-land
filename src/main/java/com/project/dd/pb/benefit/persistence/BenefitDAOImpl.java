package com.project.dd.pb.benefit.persistence;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.dd.pb.benefit.domain.BenefitDTO;


@Repository
public class BenefitDAOImpl implements BenefitDAO{
	
	@Autowired
    private  SqlSessionTemplate template;


	@Override
	public List<BenefitDTO> list() {
		
		
		
		return template.selectList("benefit.list");
	}
	


}