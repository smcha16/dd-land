package com.project.dd.pb.price.persistence;

import java.util.List;

import com.project.dd.pb.price.domain.PriceDTO;

public interface PriceDAO {

	List<PriceDTO> list();

}
