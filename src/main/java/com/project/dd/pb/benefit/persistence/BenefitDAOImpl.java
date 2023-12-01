package com.project.dd.pb.benefit.persistence;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.project.dd.pb.benefit.domain.BenefitDTO;

import lombok.RequiredArgsConstructor;

@Repository
public class BenefitDAOImpl implements BenefitDAO{
	
    private  SqlSessionTemplate template;


	@Override
	public List<BenefitDTO> list() {
		
		return template.selectList("");
	}
	


}
