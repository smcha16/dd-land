package com.project.dd.mypage.inquiry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.mypage.inquiry.domain.InquiryDTO;
import com.project.dd.mypage.inquiry.repository.MypageInquiryDAO;

@Service
public class MypageInquiryService {
	
	@Autowired
	private MypageInquiryDAO dao;

	public List<InquiryDTO> list() {
		
		return dao.list();
	}

}
