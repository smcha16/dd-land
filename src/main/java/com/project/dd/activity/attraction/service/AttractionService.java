package com.project.dd.activity.attraction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.repository.AttractionDAO;

@Service
public class AttractionService {

	@Autowired
	AttractionDAO dao;

	public List<AttractionDTO> getAttractionList() {
		return dao.getAttractionList();
	}

	public AttractionDTO getAttraction(String seq) {
		return dao.getAttraction(seq);
	}

	public List<AttractionImgDTO> getAttractionImgList(String seq) {
		return dao.getAttractionImgList(seq);
	}

	public int getAttractionCloseCount(List<AttractionDTO> list) {

		int closeCount = 0;

		//금일 운휴 어트랙션 개수 세기
		for (AttractionDTO dto : list) {

			if (dto.getClose().equalsIgnoreCase("y")) { //운휴
				closeCount++;
			}
		}
		
		return closeCount;
	}
}
