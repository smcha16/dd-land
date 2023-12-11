package com.project.dd.pb.price.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.project.dd.pb.price.domain.PriceDTO;

@Mapper
public interface PriceMapper {

	@Select("select * from TBLTICKET where PERSON_TYPE ='개인' order by PRICE desc")
	List<PriceDTO> personTypeList();

	@Select("select * from TBLTICKET where PERSON_TYPE ='단체' order by PRICE desc")
	List<PriceDTO> groupTypeList();

	@Select("select distinct TICKET_TYPE  from TBLTICKET where PERSON_TYPE = '개인'")
	List<PriceDTO> ticketTypeList();

	@Select("select * from TBLTICKET")
	List<PriceDTO> list();

	@Select("select distinct age  from TBLTICKET where PERSON_TYPE = '개인'")
	List<PriceDTO> ageList();
	

	PriceDTO getPriceInfo(String ticket_seq);

	
	@Update("update TBLTICKET set PRICE = #{price} where PERSON_TYPE = #{person_type} and TICKET_TYPE = #{ticket_type} and age = #{age}")
	int edit(PriceDTO priceDTO);

}
