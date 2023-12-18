package com.project.dd.communication.inquiry.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.inquiry.domain.InquiryDTO;

public interface InquiryDAO {

	int addInquiry(InquiryDTO dto);

	int getTotalCount();

	List<InquiryDTO> getInquiryList(Map<String, String> map);

	void editAnswer(InquiryDTO dto);

}
