package com.project.dd.mypage.inquiry.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.mypage.inquiry.domain.InquiryDTO;

public interface MypageInquiryDAO {

	List<InquiryDTO> list(Map<String, String> map);

	int getTotalCount();

	int delete(String seq);

}
