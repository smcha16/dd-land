package com.project.dd.communication.voc.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.communication.voc.domain.VocDTO;
import com.project.dd.communication.voc.mapper.VocMapper;

@Primary
@Repository
public class VocDAOImpl implements VocDAO {
	
	@Autowired
	private VocMapper mapper;
	
	/* 방문일 */
	
	@Override
	public List<String> getVisitDateList(String email) {
		
		return mapper.getVisitDateList(email);
		
	}
	
	/* 추가 */

	@Override
	public int addVoc(VocDTO dto) {
		
		return mapper.addVoc(dto);
		
	}
	
	/* 총 개수 */

	@Override
	public int getTotalCount(Map<String, String> map) {
		
		return mapper.getTotalCount(map);
		
	}
	
	/* 목록 */

	@Override
	public List<VocDTO> getVocList(Map<String, String> map) {
		
		return mapper.getVocList(map);
		
	}
	
	/* 답변 */

	@Override
	public void editAnswer(VocDTO dto) {

		mapper.editAnswer(dto);
		
	}

}
