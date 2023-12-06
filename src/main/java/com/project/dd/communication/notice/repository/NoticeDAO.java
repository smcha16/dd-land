package com.project.dd.communication.notice.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.notice.domain.NoticeDTO;

public interface NoticeDAO {

	int getTotalCount(Map<String, String> map);

	List<NoticeDTO> getNoticeList(Map<String, String> map);

	NoticeDTO getNoticeList(String seq);

}
