package com.project.dd.communication.notice.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.communication.notice.domain.NoticeDTO;

public interface NoticeDAO {

	int getTotalCount();

	List<NoticeDTO> getNoticeList(Map<String, String> map);

	NoticeDTO getNotice(String seq);

}
