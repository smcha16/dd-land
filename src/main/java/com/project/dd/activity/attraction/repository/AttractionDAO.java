package com.project.dd.activity.attraction.repository;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.domain.BookUserDTO;

public interface AttractionDAO {

	List<AttractionDTO> getAttractionList(Map<String, String> map);

	AttractionDTO getAttraction(String seq);

	List<AttractionImgDTO> getAttractionImgList(String seq);

	int getTotalCount(Map<String, String> map);

	int checkLocationDuplication(AttractionDTO dto);

	int addAttraction(AttractionDTO dto);

	int delAttraction(String seq);

	int countAttractionImg(String seq);

	int delAttractionImg(String seq);

	int delAttractionLocation(String seq);

	int countAttractionLocation(String seq);

	int checkNameDuplication(AttractionDTO dto);

	String getAttractionSeq();

	int addAttractionLocation(AttractionDTO dto);

	int addAttractionImg(AttractionImgDTO idto);

	String getAttractionDefaultImgName(String seq);

	int editAttractionLocation(AttractionDTO dto);

	int editAttraction(AttractionDTO dto);

	int delAttractionImgByImgSeq(String imgseq);

	List<AttractionImgDTO> getAllAttractionImgList();

	int checkAvailableCapacity(BookUserDTO dto);

	int addAttractionBook(BookUserDTO dto);

	int getAttractionBookCapacity(BookUserDTO dto);

	List<BookUserDTO> getAttractionBookList();

	List<AttractionDTO> getAllAttractionList();

}
