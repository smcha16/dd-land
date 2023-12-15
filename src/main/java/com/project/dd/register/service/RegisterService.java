package com.project.dd.register.service;

import java.text.DecimalFormat;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dd.register.domain.MemberDTO;
import com.project.dd.register.repository.RegisterDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService {
	
	private final RegisterDAO dao;
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	
	
	public int check(String email) {
		return dao.check(email);
	}
	public String formatPhoneNumber(String phoneNumber) {
	    // 정규식을 사용하여 숫자만 추출
	    String cleanedNumber = phoneNumber.replaceAll("[^0-9]", "");

	    // 번호가 10자리 이상이면 하이픈 추가
	    if (cleanedNumber.length() >= 10) {
	        cleanedNumber = cleanedNumber.substring(0, 3) + "-"
	                + cleanedNumber.substring(3, 7) + "-"
	                + cleanedNumber.substring(7);
	    }

	    return cleanedNumber;
	}


	
	public int register(MemberDTO memberDTO) {
		 // 비밀번호 암호화
	    String encodedPassword = passwordEncoder.encode(memberDTO.getPw());
	    memberDTO.setPw(encodedPassword);
		
		return dao.register(memberDTO);
	}
	
	public String extracted(String postcode, String addressbasis, String addressdetail) {
		String address;
		if (addressdetail == null) {
			address =  (postcode+" "+addressbasis);
		}else {
			address =  (postcode+" "+addressbasis +" "+addressdetail);
		}
		return address;
	}
	
	public String addLeadingZero(String number) {
	    // String을 int로 변환
	    int intNumber = Integer.parseInt(number);

	    // DecimalFormat 사용
	    DecimalFormat decimalFormat = new DecimalFormat("00");
	    return decimalFormat.format(intNumber);
	}

	public String dayChange(MemberDTO memberDTO, String mm, String dd) {
	    String formattedDay = dd;

	    if (Integer.valueOf(dd) < 10) {
	        formattedDay = addLeadingZero(dd);
	    }

	    return memberDTO.getBirth() + mm + formattedDay;
	}

	
	

}
