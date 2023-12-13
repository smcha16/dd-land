package com.project.dd.communication.lost.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.communication.lost.domain.LostPropertyDTO;
import com.project.dd.communication.lost.mapper.LostPropertyMapper;

@Primary
@Repository
public class LostPropertyDAOImpl implements LostPropertyDAO {
	
	@Autowired
	private LostPropertyMapper mapper;
	
	/* 총 개수 */

	@Override
	public int getTotalCount() {
		
		return mapper.getTotalCount();
		
	}
	
	/* 목록 */

	@Override
	public List<LostPropertyDTO> getLostPropertyList(Map<String, String> map) {

		return mapper.getLostPropertyList(map);
		
	}
	
	/* 추가 */

	@Override
	public int addLostProperty(LostPropertyDTO dto) {

		return mapper.addLostProperty(dto);
		
	}
	
	/* 상세 */

	@Override
	public LostPropertyDTO getLostProperty(String seq) {
		
		return mapper.getLostProperty(seq);
		
	}
	
	/* 기존 파일명 */

	@Override
	public String getFileName(String seq) {

		return mapper.getFileName(seq);
		
	}
	
	/* 수정 */

	@Override
	public int editLostProperty(LostPropertyDTO dto) {

		return mapper.editLostProperty(dto);
		
	}
	
	/* 삭제 */

	@Override
	public void deleteLostProperty(String seq) {

		mapper.deleteLostProperty(seq);
		
	}

}
