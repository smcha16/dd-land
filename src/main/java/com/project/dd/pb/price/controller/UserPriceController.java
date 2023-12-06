package com.project.dd.pb.price.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.pb.price.mapper.PriceMapper;

@Controller
@RequestMapping("/user/pb")
public class UserPriceController {
	
	@Autowired
	private PriceMapper mapper;
	
	@GetMapping(value = "/view.do")
	public String view(Model model) {
		
		List<PriceDTO> personTypeList = mapper.personTypeList(); // 개인 티켓 리스트
		
		
		for(int i=0; i<personTypeList.size(); i++) {  // 가격 ,붙히기
		
			int money =  Integer.valueOf(personTypeList.get(i).getPrice()) ;
			
			// NumberFormat 객체 생성
	        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

	        // 숫자를 형식화하여 출력
	        String formattedMoney = numberFormat.format(money);
	        
	        
	        personTypeList.get(i).setPrice(formattedMoney);
			
		}
		
		
		
		
		
		List<PriceDTO> groupTypeList = mapper.groupTypeList(); //단체 티켓 리스트
		
		for(int i=0; i<groupTypeList.size(); i++) { //가격 , 붙히기
		
			
			int money =  Integer.valueOf(groupTypeList.get(i).getPrice()) ;
			
			// NumberFormat 객체 생성
	        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

	        // 숫자를 형식화하여 출력
	        String formattedMoney = numberFormat.format(money);
	        
	        
	        personTypeList.get(i).setPrice(formattedMoney);
			
		}
		
		

		model.addAttribute("groupTypeList",groupTypeList);
		model.addAttribute("personTypeList",personTypeList);
		
		return "user/pb/price/view";
	}
}
