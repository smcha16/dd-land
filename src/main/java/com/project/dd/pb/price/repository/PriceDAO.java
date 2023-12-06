package com.project.dd.pb.price.repository;

import java.util.List;

import com.project.dd.pb.price.domain.PriceDTO;



public interface PriceDAO {

	List<PriceDTO> personTypeList();

	List<PriceDTO> groupTypeList();


}
