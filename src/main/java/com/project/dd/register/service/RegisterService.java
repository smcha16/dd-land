package com.project.dd.register.service;

import java.text.DecimalFormat;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dd.register.domain.MemberDTO;
import com.project.dd.register.repository.RegisterDAO;

import lombok.RequiredArgsConstructor;

/**
 * 사용자 등록 서비스 클래스.
 * @author 김형우
 */
@Service
@RequiredArgsConstructor
public class RegisterService {
	
	private final RegisterDAO dao;
	private final BCryptPasswordEncoder passwordEncoder;
	
	/**
	 * 이메일 중복 체크를 수행하는 메서드.
	 *
	 * @param email 확인할 이메일.
	 * @return 중복된 경우 1, 그렇지 않은 경우 0.
	 */
	public int check(String email) {
		return dao.check(email);
	}

	/**
	 * 전화번호 형식을 정형화하는 메서드.
	 *
	 * @param phoneNumber 정형화할 전화번호.
	 * @return 정형화된 전화번호.
	 */
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

	/**
	 * 회원 등록을 수행하는 메서드.
	 *
	 * @param memberDTO 등록할 회원 정보 DTO 객체.
	 * @return 등록 성공 시 1, 실패 시 0.
	 */
	public int register(MemberDTO memberDTO) {
		 // 비밀번호 암호화
	    String encodedPassword = passwordEncoder.encode(memberDTO.getPw());
	    memberDTO.setPw(encodedPassword);
		
		return dao.register(memberDTO);
	}

	/**
	 * 주소 정보를 조합하는 메서드.
	 *
	 * @param postcode 우편번호.
	 * @param addressbasis 기본 주소.
	 * @param addressdetail 상세 주소.
	 * @return 조합된 주소.
	 */
	public String extracted(String postcode, String addressbasis, String addressdetail) {
		String address;
		if (addressdetail == null) {
			address =  (postcode+" "+addressbasis);
		} else {
			address =  (postcode+" "+addressbasis +" "+addressdetail);
		}
		return address;
	}

	/**
	 * 날짜의 일 부분을 두 자리로 변환하는 메서드.
	 *
	 * @param number 변환할 숫자.
	 * @return 변환된 두 자리 숫자 문자열.
	 */
	public String addLeadingZero(String number) {
	    // String을 int로 변환
	    int intNumber = Integer.parseInt(number);

	    // DecimalFormat 사용
	    DecimalFormat decimalFormat = new DecimalFormat("00");
	    return decimalFormat.format(intNumber);
	}

	/**
	 * 생년월일과 일을 조합하는 메서드.
	 *
	 * @param memberDTO 회원 정보 DTO 객체.
	 * @param mm 월.
	 * @param dd 일.
	 * @return 조합된 생년월일과 일.
	 */
	public String dayChange(MemberDTO memberDTO, String mm, String dd) {
	    String formattedDay = dd;

	    if (Integer.valueOf(dd) < 10) {
	        formattedDay = addLeadingZero(dd);
	    }

	    return memberDTO.getBirth() + mm + formattedDay;
	}
}
