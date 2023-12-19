package com.project.dd.activity.festival.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.festival.domain.FestivalImgDTO;
import com.project.dd.activity.festival.mapper.FestivalMapper;

@Primary
@Repository
public class FestivalDAOImpl implements FestivalDAO {

	@Autowired
	FestivalMapper mapper;

	@Override
	public List<FestivalDTO> getFestivalList(String date) {
		return mapper.getFestivalList(date);
	}

	@Override
	public FestivalDTO getFestival(String seq) {
		return mapper.getFestival(seq);
	}

	@Override
	public List<FestivalImgDTO> getFestivalImgList(String seq) {
		return mapper.getFestivalImgList(seq);
	}

	@Override
	public List<FestivalDTO> getFestivalListAll(Map<String, String> map) {
		return mapper.getFestivalListAll(map);
	}

	@Override
	public int getTotalCount(String solting) {
		return mapper.getTotalCount(solting);
	}

	@Override
	public List<FestivalImgDTO> getAllFestivalImgList() {
		return mapper.getAllFestivalImgList();
	}

	@Override
	public int addFestival(FestivalDTO dto) {
		return mapper.addFestival(dto);
	}

	@Override
	public String getFestivalSeq() {
		return mapper.getFestivalSeq();
	}

	@Override
	public int addFestivalLocation(FestivalDTO dto) {
		return mapper.addFestivalLocation(dto);
	}

	@Override
	public int addFestivalImg(FestivalImgDTO idto) {
		return mapper.addFestivalImg(idto);
	}

	@Override
	public int countFestivalImg(String seq) {
		return mapper.countFestivalImg(seq);
	}

	@Override
	public int delFestivalImg(String seq) {
		return mapper.delFestivalImg(seq);
	}

	@Override
	public int countFestivalLocation(String seq) {
		return mapper.countFestivalLocation(seq);
	}

	@Override
	public int delFestivalLocation(String seq) {
		return mapper.delFestivalLocation(seq);
	}

	@Override
	public int delFestival(String seq) {
		return mapper.delFestival(seq);
	}

	@Override
	public int editFestival(FestivalDTO dto) {
		return mapper.editFestival(dto);
	}

	@Override
	public int editFestivalLocation(FestivalDTO dto) {
		return mapper.editFestivalLocation(dto);
	}

	@Override
	public int delFestivalImgByImgSeq(String imgseq) {
		return mapper.delFestivalImgByImgSeq(imgseq);
	}

	@Override
	public int checkLocationDuplication(FestivalDTO dto) {
		return mapper.checkLocationDuplication(dto);
	}


}
