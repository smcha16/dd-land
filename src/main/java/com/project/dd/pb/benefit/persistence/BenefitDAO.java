package com.project.dd.pb.benefit.persistence;

import java.util.List;

import com.project.dd.pb.benefit.domain.BenefitDTO;

public interface BenefitDAO {

	List<BenefitDTO> list();

}