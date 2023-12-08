package com.project.dd.mypage.inquiry.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.mypage.inquiry.domain.InquiryDTO;
import com.project.dd.mypage.inquiry.mapper.MypageInquiryMapper;

@Primary
@Repository
public class MypageInquiryDAOImpl implements MypageInquiryDAO{
	
	@Autowired
	private MypageInquiryMapper mapper;
	
	@Override
	public List<InquiryDTO> list() {
		
		return mapper.list();
	}

}
