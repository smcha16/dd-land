package com.project.dd.mypage.inquiry.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.mypage.inquiry.domain.InquiryDTO;

public interface MypageInquiryMapper {

	List<InquiryDTO> list(Map<String, String> map);

	int getTotalCount();

	int delete(String seq);

}
