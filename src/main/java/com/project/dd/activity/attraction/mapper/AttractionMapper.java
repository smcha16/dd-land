package com.project.dd.activity.attraction.mapper;

import java.util.List;
import java.util.Map;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.domain.BookUserDTO;

public interface AttractionMapper {

	List<AttractionDTO> getOpenAttractionList(Map<String, String> map);

	//어트랙션 상세 정보
	AttractionDTO getAttraction(String seq);
	
	//어트랙션 이미지 List
	List<AttractionImgDTO> getAttractionImgList(String seq);

	int getAdminPagingTotalPosts(Map<String, String> map);

	int checkLocationDuplication(AttractionDTO dto);

	int addAttraction(AttractionDTO dto);

	String getAttractionSeq();
	
	int addAttractionLocation(AttractionDTO dto);

	int addAttractionImg(AttractionImgDTO idto);

	int delAttraction(String seq);

	int countAttractionImg(String seq);

	int delAttractionImg(String seq);

	int delAttractionLocation(String seq);

	int countAttractionLocation(String seq);

	int checkNameDuplication(AttractionDTO dto);

	String getAttractionDefaultImgName(String seq);

	int editAttractionLocation(AttractionDTO dto);

	int editAttraction(AttractionDTO dto);

	int delAttractionImgByImgSeq(String imgseq);

	List<AttractionImgDTO> getAllAttractionImgList();

	int checkAvailableCapacity(BookUserDTO dto);

	int addAttractionBook(BookUserDTO dto);

	int getAttractionBookCapacity(BookUserDTO dto);

	List<BookUserDTO> getAttractionBookList(Map<String, String> map);

	List<AttractionDTO> getAllAttractionList(Map<String, String> map);

	int getUserPagingTotalPosts();

	List<AttractionDTO> getCloseAttractionList();

	int getReservationAdminPagingTotalPosts(Map<String, String> map);

	int checkTicket(String seq);

}
