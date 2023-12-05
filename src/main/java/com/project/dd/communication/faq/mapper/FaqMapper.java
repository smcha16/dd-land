package com.project.dd.communication.faq.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.faq.domain.FaqDTO;

public interface FaqMapper {

	int getTotalCount(Map<String, String> map);

	List<FaqDTO> getFaqList(Map<String, String> map);

}
