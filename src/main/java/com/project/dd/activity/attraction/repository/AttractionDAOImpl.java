package com.project.dd.activity.attraction.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.domain.BookUserDTO;
import com.project.dd.activity.attraction.mapper.AttractionMapper;

@Primary
@Repository
public class AttractionDAOImpl implements AttractionDAO {

	@Autowired
	AttractionMapper mapper;

	@Override
	public List<AttractionDTO> getAttractionList(Map<String, String> map) {
		return mapper.getAttractionList(map);
	}

	@Override
	public AttractionDTO getAttraction(String seq) {
		return mapper.getAttraction(seq);
	}

	@Override
	public List<AttractionImgDTO> getAttractionImgList(String seq) {
		return mapper.getAttractionImgList(seq);
	}

	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}

	@Override
	public int checkLocationDuplication(AttractionDTO dto) {
		return mapper.checkLocationDuplication(dto);
	}

	
	@Override
	public int addAttraction(AttractionDTO dto) {
		return mapper.addAttraction(dto);
	}

	@Override
	public int delAttraction(String seq) {
		return mapper.delAttraction(seq);
	}

	@Override
	public int countAttractionImg(String seq) {
		return mapper.countAttractionImg(seq);
	}

	@Override
	public int delAttractionImg(String seq) {
		return mapper.delAttractionImg(seq);
	}

	@Override
	public int delAttractionLocation(String seq) {
		return mapper.delAttractionLocation(seq);
	}

	@Override
	public int countAttractionLocation(String seq) {
		return mapper.countAttractionLocation(seq);
	}

	@Override
	public int checkNameDuplication(AttractionDTO dto) {
		return mapper.checkNameDuplication(dto);
	}

	@Override
	public String getAttractionSeq() {
		return mapper.getAttractionSeq();
	}

	@Override
	public int addAttractionLocation(AttractionDTO dto) {
		return mapper.addAttractionLocation(dto);
	}

	@Override
	public int addAttractionImg(AttractionImgDTO idto) {
		return mapper.addAttractionImg(idto);
	}

	@Override
	public String getAttractionDefaultImgName(String seq) {
		return mapper.getAttractionDefaultImgName(seq);
	}

	@Override
	public int editAttractionLocation(AttractionDTO dto) {
		return mapper.editAttractionLocation(dto);
	}

	@Override
	public int editAttraction(AttractionDTO dto) {
		return mapper.editAttraction(dto);
	}

	@Override
	public int delAttractionImgByImgSeq(String imgseq) {
		return mapper.delAttractionImgByImgSeq(imgseq);
	}

	@Override
	public List<AttractionImgDTO> getAllAttractionImgList() {
		return mapper.getAllAttractionImgList();
	}

	@Override
	public int checkAvailableCapacity(BookUserDTO dto) {
		return mapper.checkAvailableCapacity(dto);
	}

	@Override
	public int addAttractionBook(BookUserDTO dto) {
		return mapper.addAttractionBook(dto);
	}

	@Override
	public int getAttractionBookCapacity(BookUserDTO dto) {
		return mapper.getAttractionBookCapacity(dto);
	}

}
