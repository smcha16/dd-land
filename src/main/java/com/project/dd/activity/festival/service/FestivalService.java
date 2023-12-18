package com.project.dd.activity.festival.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.festival.domain.FestivalDTO;
import com.project.dd.activity.festival.domain.FestivalImgDTO;
import com.project.dd.activity.festival.repository.FestivalDAO;

@Service
public class FestivalService {

	@Autowired
	FestivalDAO dao;

	public List<FestivalDTO> getFestivalList(String date) {
		return dao.getFestivalList(date);
	}

	public FestivalDTO getFestival(String seq) {
		return dao.getFestival(seq);
	}

	public List<FestivalImgDTO> getFestivalImgList(String seq) {
		return dao.getFestivalImgList(seq);
	}

	public List<FestivalDTO> getFestivalListAll(Map<String, String> map) {
		return dao.getFestivalListAll(map);
	}

	public Map<String, String> paging(int page, String solting) {
		
		int pageSize = 0;
		
		//user or admin 노출 목록 개수 설정
		if (solting.equalsIgnoreCase("user")) {
			pageSize = 9;  //나타났으면 하는 개수(user)
			
		} else if (solting.equalsIgnoreCase("admin")) {
			pageSize = 10;  //나타났으면 하는 개수(admin)
		}
		
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = dao.getTotalCount(solting);
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		return map;
	}

	public List<FestivalImgDTO> getAllFestivalImgList() {
		return dao.getAllFestivalImgList();
	}

	public int addFestival(FestivalDTO dto, MultipartFile[] imgs, HttpServletRequest req) {

		//Add Festival
		// - 1. tblFestival INSERT
		// - 2. tblFestivalLocation INSERT
		// - 3. tblFestivalImg INSERT
		
		// - 1. tblFestival INSERT
		int result = dao.addFestival(dto);
		
		//get Festival_seq
		String seq = dao.getFestivalSeq();
		dto.setFestival_seq(seq);
		
		// - 2. tblFestivalLocation INSERT
		result = dao.addFestivalLocation(dto);
		
		// - 3. tblFestivalImg INSERT
		dto.setImgList(new ArrayList<FestivalImgDTO>()); //첨부 파일 배열
		
		if (imgs[0].isEmpty()) {
			
			FestivalImgDTO idto = new FestivalImgDTO();
			
			idto.setImg("festival.png");
			
			dto.getImgList().add(idto);
			
		} else {
			
			for (MultipartFile file : imgs) {
				
				try {
					
					UUID uuid = UUID.randomUUID();
					
					String filename = uuid + "_" + file.getOriginalFilename();
					
					file.transferTo(new File(req.getRealPath("/resources/files/activity/festival") + "\\" + filename));
					
					//첨부파일 1개 > PicDTO 1개
					FestivalImgDTO idto = new FestivalImgDTO();
					idto.setImg(filename);
					
					dto.getImgList().add(idto);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}//if
		
		for (FestivalImgDTO idto : dto.getImgList()) {
			
			idto.setFestival_seq(seq);
			
			result += dao.addFestivalImg(idto);
		}
		
		return result;
	}

	public int editFestival(FestivalDTO dto, MultipartFile[] imgs, HttpServletRequest req, String[] deleteImgSeq) {

		//Editing Festival's 7 Cases
		String seq = dto.getFestival_seq();
		int result = 0;
		
		//mandatory procedure
		// - tblFestival UPDATE
		// - tblFestivalLocation UPDATE
		result = dao.editFestival(dto);
		result = dao.editFestivalLocation(dto);
		
		//optional procedure?! not only optional..though..
		if (dto.getImg() != null && dto.getImg().equalsIgnoreCase("festival.png")) { //not exist attached file > Case 1, Case 2
			
			if (!imgs[0].isEmpty()) {
				
				dao.delFestivalImg(seq);
				
				dto.setImgList(new ArrayList<FestivalImgDTO>());
				
				for (MultipartFile file : imgs) {
					
					try {
						
						UUID uuid = UUID.randomUUID();
						
						String filename = uuid + "_" + file.getOriginalFilename();
						
						file.transferTo(new File(req.getRealPath("/resources/files/activity/festival") + "\\" + filename));
						
						//1 attached file > 1 FestivalImgDTO
						FestivalImgDTO idto = new FestivalImgDTO();
						idto.setImg(filename);
						
						dto.getImgList().add(idto);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}//for
				
				for (FestivalImgDTO idto : dto.getImgList()) {
					
					idto.setFestival_seq(seq);
					
					result += dao.addFestivalImg(idto); //Case 2
				}
				
				return result;
			}
			
			return 1; //Case 1
			
		} 
		
		// - exist attached files > Case 3 ~ Case 7
		if (dto.getImg() != null && imgs[0].isEmpty()) { //Case 3, Case 4, Case 5
			
			int imgCount = dao.countFestivalImg(seq);
			
			if (deleteImgSeq.length > 0 && deleteImgSeq.length < imgCount) { //partially delete
				
				for (String imgseq : deleteImgSeq) {
					result = dao.delFestivalImgByImgSeq(imgseq); //Case 4
				}
				
			} else if (deleteImgSeq.length == imgCount) { //all delete
				
				for (String imgseq : deleteImgSeq) {
					result = dao.delFestivalImgByImgSeq(imgseq); //Case 5
				}
				
				//'festival.png' 추가
				FestivalImgDTO idto = new FestivalImgDTO();
				idto.setFestival_seq(seq);
				idto.setImg("festival.png");
				
				dao.addFestivalImg(idto);
				
			} else if (deleteImgSeq.length == 0) {
				return 1; //Case 3
			}
			
		} else if (dto.getImg() != null && !imgs[0].isEmpty()) { //Case 6, Case 7
			
			//optional procedure > delete existed files
			if (deleteImgSeq.length > 0) {
				for (String imgseq : deleteImgSeq) {
					result = dao.delFestivalImgByImgSeq(imgseq);
				}
			}
			
			//mandatory procedure > add new attached files
			dto.setImgList(new ArrayList<FestivalImgDTO>());
			
			for (MultipartFile file : imgs) {
				
				try {
					
					UUID uuid = UUID.randomUUID();
					
					String filename = uuid + "_" + file.getOriginalFilename();
					
					file.transferTo(new File(req.getRealPath("/resources/files/activity/festival") + "\\" + filename));
					
					//1 attached file > 1 FestivalImgDTO
					FestivalImgDTO idto = new FestivalImgDTO();
					idto.setImg(filename);
					
					dto.getImgList().add(idto);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			for (FestivalImgDTO idto : dto.getImgList()) {
				
				idto.setFestival_seq(seq);
				
				result += dao.addFestivalImg(idto);
			}
			
		}
		
		return result;
	}

	public int delFestival(String[] festival_seq) {
		
		//Delete Festival
		// - 1. tblFestivalImg DELETE
		// - 2. tblFestivalLocation DELETE
		// - 3. tblFestival DELETE
		
		int result = 0;
		
		for (String seq : festival_seq) {
			
			// - 1. tblFestivalImg DELETE
			int imgCount = dao.countFestivalImg(seq);
			
			if (imgCount > 0) {
				dao.delFestivalImg(seq);
			}
			
			// - 2. tblFestivalLocation DELETE
			int locationCount = dao.countFestivalLocation(seq);
			
			if (locationCount > 0) {
				dao.delFestivalLocation(seq);
			}
			
			// - 3. tblFestival DELETE
			result += dao.delFestival(seq);
		}
		
		return result;
	}

	public int checkLocationDuplication(FestivalDTO dto) {
		return dao.checkLocationDuplication(dto);
	}

}
