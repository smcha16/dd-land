package com.project.dd.communication.inquiry.repository;

import java.util.List;
import java.util.Map;

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
	
	/* 추가 */

	@Override
	public int addInquiry(InquiryDTO dto) {
		
		return mapper.addInquiry(dto);
		
	}
	
	/* 총 개수 */

	@Override
	public int getTotalCount(Map<String, String> map) {
		
		return mapper.getTotalCount(map);
		
	}
	
	/* 목록 */

	@Override
	public List<InquiryDTO> getInquiryList(Map<String, String> map) {
		
		return mapper.getInquiryList(map);
		
	}
	
	/* 답변 */

	@Override
	public void editAnswer(InquiryDTO dto) {

		mapper.editAnswer(dto);
		
	}

}
