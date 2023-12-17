package com.project.dd.activity.photozone.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.photozone.domain.PhotoZoneImgDTO;
import com.project.dd.activity.photozone.mapper.PhotoZoneMapper;

@Primary
@Repository
public class PhotoZoneDAOImpl implements PhotoZoneDAO {

	@Autowired
	PhotoZoneMapper mapper;

	@Override
	public List<PhotoZoneDTO> getPhotozoneList(Map<String, String> map) {
		return mapper.getPhotozoneList(map);
	}

	@Override
	public PhotoZoneDTO getPhotozone(String seq) {
		return mapper.getPhotozone(seq);
	}

	@Override
	public List<PhotoZoneImgDTO> getPhotozoneImgList(String seq) {
		return mapper.getPhotozoneImgList(seq);
	}

	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}

	@Override
	public List<PhotoZoneImgDTO> getAllPhotozoneImgList() {
		return mapper.getAllPhotozoneImgList();
	}

	@Override
	public int addPhotozone(PhotoZoneDTO dto) {
		return mapper.addPhotozone(dto);
	}

	@Override
	public int checkLocationDuplication(PhotoZoneDTO dto) {
		return mapper.checkLocationDuplication(dto);
	}

	@Override
	public int checkNameDuplication(PhotoZoneDTO dto) {
		return mapper.checkNameDuplication(dto);
	}

	@Override
	public String getPhotozoneSeq() {
		return mapper.getPhotozoneSeq();
	}

	@Override
	public int addPhotozoneLocation(PhotoZoneDTO dto) {
		return mapper.addPhotozoneLocation(dto);
	}

	@Override
	public int addPhotozoneImg(PhotoZoneImgDTO idto) {
		return mapper.addPhotozoneImg(idto);
	}

	@Override
	public int editPhotozone(PhotoZoneDTO dto) {
		return mapper.editPhotozone(dto);
	}

	@Override
	public int editPhotozoneLocation(PhotoZoneDTO dto) {
		return mapper.editPhotozoneLocation(dto);
	}

	@Override
	public int delPhotozoneImg(String seq) {
		return mapper.delPhotozoneImg(seq);
	}

	@Override
	public int countPhotozoneImg(String seq) {
		return mapper.countPhotozoneImg(seq);
	}

	@Override
	public int delPhotozoneImgByImgSeq(String imgseq) {
		return mapper.delPhotozoneImgByImgSeq(imgseq);
	}

	@Override
	public int countPhotozoneLocation(String seq) {
		return mapper.countPhotozoneLocation(seq);
	}

	@Override
	public int delPhotozoneLocation(String seq) {
		return mapper.delPhotozoneLocation(seq);
	}

	@Override
	public int delPhotozone(String seq) {
		return mapper.delPhotozone(seq);
	}
}
