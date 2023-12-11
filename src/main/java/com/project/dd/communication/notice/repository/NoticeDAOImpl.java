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
	
	// 총 개수

	@Override
	public int getTotalCount() {
		
		return mapper.getTotalCount();
		
	}
	
	// 목록

	@Override
	public List<NoticeDTO> getNoticeList(Map<String, String> map) {
		
		return mapper.getNoticeList(map);
		
	}
	
	// 상세

	@Override
	public NoticeDTO getNotice(String seq) {

		return mapper.getNotice(seq);
		
	}
	
	// 추가

	@Override
	public int addNotice(NoticeDTO dto) {
		
		return mapper.addNotice(dto);
		
	}
	
	// 기존 파일명
	
	@Override
	public String getFileName(String seq) {
		
		return mapper.getFileName(seq);
		
	}
	
	// 수정

	@Override
	public int editNotice(NoticeDTO dto) {
		
		return mapper.editNotice(dto);
		
	}
	
	// 삭제

	@Override
	public void deleteNotice(String seq) {

		mapper.deleteNotice(seq);
		
	}

}
