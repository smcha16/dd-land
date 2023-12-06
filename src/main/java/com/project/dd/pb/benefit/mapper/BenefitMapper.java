package com.project.dd.pb.benefit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.project.dd.pb.benefit.domain.BenefitDTO;

public interface BenefitMapper {

	@Select("SELECT benefit_seq, name, type,  TO_CHAR(START_DATE, 'YYYY-MM-DD') AS start_date, TO_CHAR(END_DATE,'YYYY-MM-DD') AS end_date, discount_rate, img FROM TBLBENEFIT where TYPE='카드/통신사'order by BENEFIT_SEQ desc ")
	List<BenefitDTO> cardList();

	@Select("SELECT benefit_seq, name, type,  TO_CHAR(START_DATE, 'YYYY-MM-DD') AS start_date, TO_CHAR(END_DATE,'YYYY-MM-DD') AS end_date, discount_rate, img FROM TBLBENEFIT where TYPE='일반'order by BENEFIT_SEQ desc  ")
	List<BenefitDTO> normalList();
	
	@Select("SELECT benefit_seq, name, type,  TO_CHAR(START_DATE, 'YYYY-MM-DD') AS start_date, TO_CHAR(END_DATE,'YYYY-MM-DD') AS end_date, discount_rate, img FROM TBLBENEFIT order by BENEFIT_SEQ desc ")
	List<BenefitDTO> list();

	
}
