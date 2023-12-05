package com.project.dd.communication.notice.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.notice.domain.NoticeDTO;

public interface NoticeMapper {
	
	int getTotalCount(Map<String, String> map);

	List<NoticeDTO> getNoticeList(Map<String, String> map);

	NoticeDTO getNotice(String seq);

}
