package com.project.dd.communication.notice.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.communication.notice.domain.NoticeDTO;
import com.project.dd.communication.notice.mapper.NoticeMapper;

@Primary
@Repository
public class NoticeDAOImpl implements NoticeDAO {
	
	@Autowired
	private NoticeMapper mapper;

	@Override
	public int getTotalCount(Map<String, String> map) {
		
		return mapper.getTotalCount(map);
		
	}

	@Override
	public List<NoticeDTO> getNoticeList(Map<String, String> map) {
		
		return mapper.getNoticeList(map);
		
	}

	@Override
	public NoticeDTO getNoticeList(String seq) {

		return mapper.getNotice(seq);
		
	}

}
