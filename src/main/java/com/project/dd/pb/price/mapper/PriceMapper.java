package com.project.dd.pb.price.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.project.dd.pb.price.domain.PriceDTO;
@Mapper
public interface PriceMapper {

	@Select("select * from TBLTICKET where PERSON_TYPE ='개인' order by PRICE desc")
	List<PriceDTO> personTypeList();

	@Select("select * from TBLTICKET where PERSON_TYPE ='단체' order by PRICE desc")
	List<PriceDTO> groupTypeList();

	


}
