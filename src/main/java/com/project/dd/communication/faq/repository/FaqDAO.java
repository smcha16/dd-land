package com.project.dd.communication.faq.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.faq.domain.FaqDTO;

public interface FaqDAO {

	int getTotalCount(Map<String, String> map);

	List<FaqDTO> getFaqList(Map<String, String> map);

	int addFaq(FaqDTO dto);

	FaqDTO getFaq(String seq);

	int editFaq(FaqDTO dto);

	void deleteFaq(String seq);

}
