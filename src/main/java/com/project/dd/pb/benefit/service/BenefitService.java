package com.project.dd.pb.benefit.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.benefit.repository.BenefitDAO;
import com.project.dd.pb.price.domain.PriceDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BenefitService {

	private final BenefitDAO dao;

	public List<BenefitDTO> cardList() {

		return dao.cardList();

	}

	public List<BenefitDTO> normalList() {
		return dao.normalList();
	}

	public List<BenefitDTO> List() {
		return dao.list();
	}

	public java.util.List<BenefitDTO> benefitList() {
		return dao.benefitList();
	}

	public String getName(String seq) {
		return dao.getName(seq);
	}

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

    public static class Result {
        public final ArrayList<String> discountList1Day;
        public final ArrayList<String> discountListAfter4;
        public final ArrayList<String> groupDiscount1Day;
        public final ArrayList<String> groupDiscountAfter4;

        public Result(ArrayList<String> discountList1Day, ArrayList<String> discountListAfter4, ArrayList<String> groupDiscount1Day, ArrayList<String> groupDiscountAfter4) {
            this.discountList1Day = discountList1Day;
            this.discountListAfter4 = discountListAfter4;
            this.groupDiscount1Day = groupDiscount1Day;
            this.groupDiscountAfter4 = groupDiscountAfter4;
        }
    }

	public java.util.List<BenefitDTO> benefitInfo(String seq) {
		
		
		return dao.benefitInfo(seq);
	}

	public int addBenefit(BenefitDTO benefitDTO, MultipartFile attach, HttpServletRequest req) {
		
		try {
			UUID uuid = UUID.randomUUID();
			
			String filename = uuid + "_" +attach.getOriginalFilename();
			
			attach.transferTo(new File(req.getRealPath("/resources/files/benefit") + "\\" + filename));
			
			benefitDTO.setImg(filename);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao.addBenefit(benefitDTO);
	}

}
