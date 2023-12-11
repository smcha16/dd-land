package com.project.dd.communication.notice.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.notice.domain.NoticeDTO;

public interface NoticeMapper {
	
	int getTotalCount();

	List<NoticeDTO> getNoticeList(Map<String, String> map);

	NoticeDTO getNotice(String seq);

	int addNotice(NoticeDTO dto);
	
	String getFileName(String seq);

	int editNotice(NoticeDTO dto);

	void deleteNotice(String seq);

}
