package com.project.dd.pb.price.service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.pb.price.repository.PriceDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPriceService {

    private final PriceDAO dao;
    
    
   

    public void formatPrices(List<PriceDTO> priceList) {  // 금액에 , 찍기
        for (int i = 0; i < priceList.size(); i++) {
            int money = Integer.valueOf(priceList.get(i).getPrice());

            // NumberFormat 객체 생성
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

            // 숫자를 형식화하여 출력
            String formattedMoney = numberFormat.format(money);

            priceList.get(i).setPrice(formattedMoney);
        }
    }

    public List<PriceDTO> getPersonTypeList() {
        return dao.personTypeList();
    }

    public List<PriceDTO> getGroupTypeList() {
        return dao.groupTypeList();
    }

	public List<PriceDTO> list() {
		return dao.list();
	}

	public List<PriceDTO> ticketTypeList() {
		return dao.tikcetTypeList();
	}

	public List<PriceDTO> ageList() {
		return dao.ageList();
	}

	public PriceDTO getPriceInfo(String ticket_seq) {
		
		return dao.getPriceInfo(ticket_seq);
	}

	

	public int edit(PriceDTO priceDTO) {
		return dao.edit(priceDTO);
	}

	
}
