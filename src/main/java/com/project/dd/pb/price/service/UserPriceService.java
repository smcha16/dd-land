package com.project.dd.pb.price.service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.pb.price.mapper.PriceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPriceService {

    private final PriceMapper mapper;

    public void formatPrices(List<PriceDTO> priceList) {
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
        return mapper.personTypeList();
    }

    public List<PriceDTO> getGroupTypeList() {
        return mapper.groupTypeList();
    }
}
