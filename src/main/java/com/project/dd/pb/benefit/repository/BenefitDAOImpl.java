package com.project.dd.pb.benefit.repository;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.pb.benefit.domain.BenefitDTO;
import com.project.dd.pb.benefit.mapper.BenefitMapper;

import lombok.RequiredArgsConstructor;

/**
 * 혜택 정보에 대한 데이터 액세스를 담당하는 DAO 구현 클래스.
 * @author 김형우
 */
@Primary
@Repository
@RequiredArgsConstructor
public class BenefitDAOImpl implements BenefitDAO {

    private final BenefitMapper mapper;
    
    /**
     *  카드 혜택 목록을 가져오는 메서드
     *  return 카드목록을 리턴합니다.
     */
    @Override
    public List<BenefitDTO> cardList() {
        return mapper.cardList();
    }

    /**
     * 일반 혜택 목록을 가져오는 메서드.
     * return 일반 목록을 리턴합니다.
     */
    @Override
    public List<BenefitDTO> normalList() {
        return mapper.normalList();
    }

    /**
     * 전체 혜택 목록을 가져오는 메서드.
     * return  혜택 목록을 리턴합니다.
     */
    @Override
    public List<BenefitDTO> list() {
        return mapper.list();
    }

    /**
     * 혜택 목록을 가져오는 메서드.
     * return 혜택 목록을 리턴합니다.
     */
    @Override
    public List<BenefitDTO> benefitList() {
        return mapper.benefitList();
    }

    /**
     * 특정 혜택의 이름을 가져오는 메서드.
     * return  이름을 리턴합니다.
     */
    @Override
    public String getName(String seq) {
        return mapper.getName(seq);
    }

    /**
     * 특정 혜택의 정보를 가져오는 메서드.
     * return  혜택 정보를 리턴합니다.
     */
    @Override
    public List<BenefitDTO> benefitInfo(String seq) {
        return mapper.benefitInfo(seq);
    }

    /**
     * 혜택을 추가하는 메서드.
     * return 혜택추가에 대한 결과를 반환합니다.
     */
    @Override
    public int addBenefit(BenefitDTO benefitDTO) {
        return mapper.addBenefit(benefitDTO);
    }
    /**
     * 혜택 삭제 메서드
     * return 혜택삭제에 대한 결과를 반환합니다.
     */
	@Override
	public int del(String benefit_seq) {
		return mapper.del(benefit_seq);
	}
	/**
	 * 혜택 개수 반환 메서드
	 * return 혜택 총개수 반환.
	 */
	@Override
	public int getTotalCount(String type) {
		return mapper.getTotalCount(type);
	}
	/**
	 * 혜택 리스트 메서드
	 * return 혜택 리스트 반환
	 */
	@Override
	public List<BenefitDTO> getBenefitList(Map<String, String> map) {
		return mapper.getBenefitList(map);
	}
}
