package com.project.dd.communication.inquiry.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.communication.inquiry.domain.InquiryDTO;
import com.project.dd.communication.inquiry.mapper.InquiryMapper;

@Primary
@Repository
public class InquiryDAOImpl implements InquiryDAO {
	
	@Autowired
	private InquiryMapper mapper;

	@Override
	public int addInquiry(InquiryDTO dto) {
		
		return mapper.addInquiry(dto);
		
	}

}
