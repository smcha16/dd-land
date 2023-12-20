package com.project.dd.pb.benefit.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.benefit.repository.BenefitDAO;
import com.project.dd.pb.price.domain.PriceDTO;

import lombok.RequiredArgsConstructor;
/**
 *  혜택 정보를 처리하는 서비스 클래스.
 * @author 김형우
 *
 */
@Service
@RequiredArgsConstructor
public class BenefitService {

	private final BenefitDAO dao;
	/**
	 * 카드 혜택 목록을 가져오는 메서드.
	 * @return 카드 혜택 리스트 
	 */
	public List<BenefitDTO> cardList() {
		
		return dao.cardList();

	}
	/**
	 * 일반 혜택 목록을 가져오는 메서드.
	 * @return 일반혜택 리스트
	 */
	public List<BenefitDTO> normalList() {
		return dao.normalList();
	}
	/**
	 * 전체 혜택 목록을 가져오는 메서드.
	 * @return 혜택 리스트 반환
	 */
	public List<BenefitDTO> List() {
		return dao.list();
	}
	/**
	 * 혜택 목록을 가져오는 메서드.
	 * @return 혜택 리스트 반환
	 */
	public java.util.List<BenefitDTO> benefitList() {
		return dao.benefitList();
	}
	/**
	 *  특정 혜택의 이름을 가져오는 메서드.
	 * @param seq 회원 시퀀스번호
	 * @return 회원 이름 반환
	 */
	public String getName(String seq) {
		return dao.getName(seq);
	}
	/**
	 * 할인율을 적용하여 계산한 결과를 반환하는 메서드.
	 * 
	 * @param list        개인 가격 목록.
	 * @param groupList   단체 가격 목록.
	 * @param benefitList 혜택 목록.
	 * @param seq         혜택 일련번호.
	 * @return 할인율 계산 결과를 담은 Result 객체.
	 */
	public Result applyDiscount(List<PriceDTO> list, List<PriceDTO> groupList, List<BenefitDTO> benefitList, String seq) {
        // 할인율 적용 로직 구현
		

        int percent = -1;

        for (int i = 0; i < benefitList.size(); i++) {
            if (benefitList.get(i).getBenefit_seq().equals(seq + "")) {
                percent = Integer.parseInt(benefitList.get(i).getDiscount_rate());
            }
        }

        double calculate = (double) (100 - percent) / 100;

        ArrayList<String> discountList1Day = new ArrayList<>();
        ArrayList<String> discountListAfter4 = new ArrayList<>();
        ArrayList<String> groupDiscount1Day = new ArrayList<>();
        ArrayList<String> groupDiscountAfter4 = new ArrayList<>();
        
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getTicket_type().equals("1Day")) {

                int discount = (int) (((double) Integer.parseInt(list.get(i).getPrice())) * calculate); // 금액 * 계산된 할인율 적용


                String str = String.format("%,d", discount);

                discountList1Day.add(str);



            } else if (list.get(i).getTicket_type().equals("After4")) {

                int discount = (int) (((double) Integer.parseInt(list.get(i).getPrice())) * calculate); // 금액 * 계산된 할인율 적용


                String str = String.format("%,d", discount);

                discountListAfter4.add(str);

            }

        }
        for (int i = 0; i < groupList.size(); i++) {
            if (groupList.get(i).getTicket_type().equals("1Day")) {
                int groupDiscount = (int) (((double) Integer.parseInt(groupList.get(i).getPrice())) * calculate);
                String str2 = String.format("%,d", groupDiscount);

                groupDiscount1Day.add(str2);

            } else if (groupList.get(i).getTicket_type().equals("After4")) {
                int groupDiscount = (int) (((double) Integer.parseInt(groupList.get(i).getPrice())) * calculate);
                String str2 = String.format("%,d", groupDiscount);
                groupDiscountAfter4.add(str2);
            }
        }
        // 금액 , 찍기 로직 시작

        for (int i = 0; i < list.size(); i++) {

            String result = String.format("%,d", Integer.parseInt(list.get(i).getPrice()));

            list.get(i).setPrice(result);
        }

        // 금액 , 찍기 끝

        return new Result(discountList1Day, discountListAfter4, groupDiscount1Day, groupDiscountAfter4);
    }
	/**
	 * 할인율 계산 결과를 저장하는 내부 클래스.
	 * 
	 *
	 */
    public static class Result {
        public final ArrayList<String> discountList1Day;
        public final ArrayList<String> discountListAfter4;
        public final ArrayList<String> groupDiscount1Day;
        public final ArrayList<String> groupDiscountAfter4;
        /**
         *  결과 저장 생성자
         * @param discountList1Day 개인 1일권
         * @param discountListAfter4 개인 오후4시권
         * @param groupDiscount1Day 단체  1일권
         * @param groupDiscountAfter4 단체	오후4시권
         */
        public Result(ArrayList<String> discountList1Day, ArrayList<String> discountListAfter4, ArrayList<String> groupDiscount1Day, ArrayList<String> groupDiscountAfter4) {
            this.discountList1Day = discountList1Day;
            this.discountListAfter4 = discountListAfter4;
            this.groupDiscount1Day = groupDiscount1Day;
            this.groupDiscountAfter4 = groupDiscountAfter4;
        }
    }
    /**
     *  혜택에 대한 상세 정보를 가져오는 메서드.
     * @param seq 혜택 번호
     * @return 혜택 상세 정보
     */
	public java.util.List<BenefitDTO> benefitInfo(String seq) {
		
		
		return dao.benefitInfo(seq);
	}
	/**
	 * 혜택을 추가하는 메서드.
	 * 
	 * @param benefitDTO 혜택 정보를 담은 DTO 객체.
	 * @param attach     혜택 이미지를 업로드하는 MultipartFile 객체.
	 * @param req        HTTPServletRequest 객체.
	 * @return 혜택 추가 성공 여부를 나타내는 결과 코드.
	 */
	public int addBenefit(BenefitDTO benefitDTO, MultipartFile attach, HttpServletRequest req) {
		
		try {
			UUID uuid = UUID.randomUUID();
			
			String filename = uuid + "_" + attach.getOriginalFilename();
			
			attach.transferTo(new File(req.getRealPath("/resources/files/benefit") + "\\" + filename));
			
			benefitDTO.setImg(filename);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.addBenefit(benefitDTO);
	}
	/**
	 * 혜택 삭제하는 메서드
	 * @param benefit_seq 혜택 시퀀스 번호
	 * @return 삭제결과를 1또는 0으로 반환
	 */
	public int del(String benefit_seq) {
		return dao.del(benefit_seq);
	}
	/**
	 * 회원 목록을 페이징 처리하여 반환하는 메서드
	 * 
	 * @param type 혜택 유형
	 * @param page 현재 페이지
	 * @return 페이징 처리된 회원 목록 정보 (시작 페이지, 끝 페이지, 현재 페이지, 총 페이지 수 등)
	 */
	public Map<String, String> paging(String type, int page) {
		Map<String, String> map = new HashMap<>();

		map.put("type", type);

		int pageSize = 10;

		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));

		int totalPosts = dao.getTotalCount(type);
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;
	}
	/**
	 * 페이징 처리된 혜택 목록을 조회하는 메서드
	 * @param map 페이징 및 검색 조건
	 * @return 페이징 처리된 혜택 목록
	 */
	public java.util.List<BenefitDTO> getBenefitList(Map<String, String> map) {

		
		return dao.getBenefitList(map);
	}

}
