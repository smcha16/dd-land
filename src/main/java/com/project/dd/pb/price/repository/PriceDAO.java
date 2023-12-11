package com.project.dd.pb.price.repository;

import java.util.List;

import com.project.dd.pb.price.domain.PriceDTO;



public interface PriceDAO {

	List<PriceDTO> personTypeList();

	List<PriceDTO> groupTypeList();

	List<PriceDTO> list();

	List<PriceDTO> tikcetTypeList();

	List<PriceDTO> ageList();

	PriceDTO getPriceInfo(String ticket_seq);


	int edit(PriceDTO priceDTO);



}
