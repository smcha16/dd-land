package com.project.dd.pb.price.service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.project.dd.pb.price.domain.PriceDTO;
import com.project.dd.pb.price.repository.PriceDAO;

import lombok.RequiredArgsConstructor;

/**
 * 가격 정보를 처리하는 서비스 클래스.
 * @author 김형우
 */
@Service
@RequiredArgsConstructor
public class UserPriceService {

    private final PriceDAO dao;

    /**
     * 가격 목록의 금액에 쉼표를 추가하는 메서드.
     * 
     * @param priceList 가격 목록.
     */
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

    /**
     * 개인 유형의 가격 목록을 조회하는 메서드.
     * 
     * @return 개인 유형의 가격 목록.
     */
    public List<PriceDTO> getPersonTypeList() {
        return dao.personTypeList();
    }

    /**
     * 단체 유형의 가격 목록을 조회하는 메서드.
     * 
     * @return 단체 유형의 가격 목록.
     */
    public List<PriceDTO> getGroupTypeList() {
        return dao.groupTypeList();
    }

    /**
     * 전체 가격 목록을 조회하는 메서드.
     * 
     * @return 전체 가격 목록.
     */
    public List<PriceDTO> list() {
        return dao.list();
    }

    /**
     * 티켓 유형 목록을 조회하는 메서드.
     * 
     * @return 티켓 유형 목록.
     */
    public List<PriceDTO> ticketTypeList() {
        return dao.tikcetTypeList();
    }

    /**
     * 나이 목록을 조회하는 메서드.
     * 
     * @return 나이 목록.
     */
    public List<PriceDTO> ageList() {
        return dao.ageList();
    }

    /**
     * 특정 티켓에 대한 가격 정보를 조회하는 메서드.
     * 
     * @param ticket_seq 티켓 일련번호.
     * @return 티켓에 대한 가격 정보.
     */
    public PriceDTO getPriceInfo(String ticket_seq) {
        return dao.getPriceInfo(ticket_seq);
    }

    /**
     * 가격 정보를 수정하는 메서드.
     * 
     * @param priceDTO 수정할 가격 정보를 담은 DTO 객체.
     * @return 수정된 행의 수.
     */
    public int edit(PriceDTO priceDTO) {
        return dao.edit(priceDTO);
    }
}
