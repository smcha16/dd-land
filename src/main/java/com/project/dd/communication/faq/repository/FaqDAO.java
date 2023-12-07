package com.project.dd.communication.faq.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.faq.domain.FaqDTO;

public interface FaqDAO {

	int getTotalCount(String type);

	List<FaqDTO> getFaqList(Map<String, String> map);

}
