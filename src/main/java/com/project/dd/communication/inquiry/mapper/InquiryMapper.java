package com.project.dd.communication.inquiry.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.inquiry.domain.InquiryDTO;

public interface InquiryMapper {
	
	int addInquiry(InquiryDTO dto);

	int getTotalCount();

	List<InquiryDTO> getInquiryList(Map<String, String> map);

	void editAnswer(InquiryDTO dto);

}
