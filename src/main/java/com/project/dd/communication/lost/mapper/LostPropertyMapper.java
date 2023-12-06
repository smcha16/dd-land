package com.project.dd.communication.lost.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.lost.domain.LostPropertyDTO;
import com.project.dd.communication.notice.domain.NoticeDTO;

public interface LostPropertyMapper {

	int getTotalCount(Map<String, String> map);

	List<LostPropertyDTO> getLostPropertyList(Map<String, String> map);

}
