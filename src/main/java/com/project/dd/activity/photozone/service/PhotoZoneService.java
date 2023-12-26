package com.project.dd.activity.photozone.service;

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

import com.project.dd.activity.photozone.domain.PhotoZoneDTO;
import com.project.dd.activity.photozone.domain.PhotoZoneImgDTO;
import com.project.dd.activity.photozone.repository.PhotoZoneDAO;

/**
 * 
 * 포토존 페이지의 비즈니스 로직을 담당하는 Service 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Service
public class PhotoZoneService {

	@Autowired
	PhotoZoneDAO dao;

	/**
	 * 
	 * 운영중인 포토존 목록을 가져오는 메서드입니다.
	 * 
	 * @param map 페이징을 위한 map 객체
	 * @return 포토존 dto list
	 */
	public List<PhotoZoneDTO> getPhotozoneList(Map<String, String> map) {
		
		List<PhotoZoneDTO> list = dao.getPhotozoneList(map);
		
		for (PhotoZoneDTO dto : list) {
			
			//DB 내 태그 비활성화 처리 '&gt;, &lt;' 처리
			String newInfo = dto.getInfo(); 
			newInfo = newInfo.replace("<", "&lt");
			newInfo = newInfo.replace(">", "&gt");
			
			//DB 개행 -> '<br>' 태그 처리
			newInfo = newInfo.replaceAll("(\r\n|\r|\n)", "<br>");
			
			dto.setInfo(newInfo);
			
		}
		
		return list;
		
		
	}

	/**
	 * 
	 * 특정 포토존의 정보를 가져오는 메서드입니다.
	 * 
	 * @param seq 포토존 번호
	 * @return 포토존 dto
	 */
	public PhotoZoneDTO getPhotozone(String seq) {
		PhotoZoneDTO dto = dao.getPhotozone(seq);
		
		//DB 내 태그 비활성화 처리 '&gt;, &lt;' 처리
		String newInfo = dto.getInfo(); 
		newInfo = newInfo.replace("<", "&lt");
		newInfo = newInfo.replace(">", "&gt");
		
		//DB 개행 -> '<br>' 태그 처리
		newInfo = newInfo.replaceAll("(\r\n|\r|\n)", "<br>");
		
		dto.setInfo(newInfo);
			
		return dto;
	}

	/**
	 * 
	 * 포토존 이미지 목록을 가져오는 메서드입니다.
	 * 
	 * @param seq 포토존 번호
	 * @return 포토존이미지 dto list
	 */
	public List<PhotoZoneImgDTO> getPhotozoneImgList(String seq) {
		return dao.getPhotozoneImgList(seq);
	}

	/**
	 * 
	 * 페이지 번호를 출력하기 위해 DB에 접근하여 포토존 개수를 조회하는 메서드입니다.
	 * 
	 * @param page 페이지 번호
	 * @param solting 사용자/관리자별 한 페이지당 노출 목록 개수 설정
	 * @return 위의 정보가 담긴 map 객체
	 */
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

		int totalPosts = dao.getTotalCount();
		int totalPages = (int)Math.ceil((double)totalPosts / pageSize);

		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));

		return map;

	}

	/**
	 * 
	 * 전체 포토존 이미지 목록을 가져오는 메서드입니다.
	 * 
	 * @return 포토존이미지 dto list
	 */
	public List<PhotoZoneImgDTO> getAllPhotozoneImgList() {
		return dao.getAllPhotozoneImgList();
	}

	/**
	 * 
	 * 포토존 추가를 위해 포토존, 포토존 위치, 포토존 이미지 DB에 접근하는 메서드입니다.
	 * 
	 * @param dto 포토존 dto 객체
	 * @param imgs 첨부할 이미지 멀티파일 객체 배열
	 * @param req HttpServletRequest 객체
	 * @return 테이블에 추가된 행의 개수
	 */
	public int addPhotozone(PhotoZoneDTO dto, MultipartFile[] imgs, HttpServletRequest req) {

		//1. tblPhotozone 추가
		//2. tblPhotozoneLocation 추가
		//3. tblPhotozoneImg 추가
		
		//1.
		int result = dao.addPhotozone(dto);
		
		String seq = dao.getPhotozoneSeq();
		dto.setPhotozone_seq(seq);
		
		//2.
		result = dao.addPhotozoneLocation(dto);
		
		//3.
		dto.setImgList(new ArrayList<PhotoZoneImgDTO>());
		
		if (imgs[0].isEmpty()) {
			
			PhotoZoneImgDTO idto = new PhotoZoneImgDTO();
			
			idto.setImg("photozone.png");
			
			dto.getImgList().add(idto);
			
		} else {
			
			for (MultipartFile file : imgs) {
				
				try {
					
					UUID uuid = UUID.randomUUID();
					
					String filename = uuid + "_" + file.getOriginalFilename();
					
					file.transferTo(new File(req.getRealPath("/resources/files/activity/photozone") + "\\" + filename));
					
					//첨부파일 1개 > PicDTO 1개
					PhotoZoneImgDTO idto = new PhotoZoneImgDTO();
					idto.setImg(filename);
					
					dto.getImgList().add(idto);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}//if
		
		for (PhotoZoneImgDTO idto : dto.getImgList()) {
			
			idto.setPhotozone_seq(seq);
			
			result += dao.addPhotozoneImg(idto);
		}
		
		return result;
	}

	/**
	 * 
	 * 포토존 위치정보의 중복 검사를 진행하는 메서드
	 * 
	 * @param dto 포토존 dto 객체
	 * @return 중복된 레코드의 개수
	 */
	public int checkLocationDuplication(PhotoZoneDTO dto) {
		return dao.checkLocationDuplication(dto);
	}

	/**
	 * 
	 * 포토존명의 중복 검사를 위한 메서드
	 * 
	 * @param dto 포토존 dto 객체
	 * @return 중복된 레코드의 개수
	 */
	public int checkNameDuplication(PhotoZoneDTO dto) {
		return dao.checkNameDuplication(dto);
	}

	/**
	 * 
	 * 포토존 수정을 위해 포토존, 포토존 위치, 포토존 이미지에 접근하는 메서드입니다.
	 * 
	 * @param dto 포토존 dto 객체
	 * @param imgs 추가한 멀티파일 객체 배열
	 * @param req HttpServletRequest 객체
	 * @param deleteImgSeq 삭제할 포토존 번호
	 * @return 수정된 행의 개수
	 */
	public int editPhotozone(PhotoZoneDTO dto, MultipartFile[] imgs, HttpServletRequest req, String[] deleteImgSeq) {
		
		//1. 공통 PhotoZoneDTO 수정
		//2. 파일 첨부 7가지 경우의 수
		
		String seq = dto.getPhotozone_seq();
		int result = 0;
		
		//1.
		result = dao.editPhotozone(dto);
		result = dao.editPhotozoneLocation(dto);
		
		//2.
		if (dto.getImg() != null && dto.getImg().equalsIgnoreCase("photozone.png")) {
			
			if (!imgs[0].isEmpty()) {
				
				dao.delPhotozoneImg(seq);
				
				dto.setImgList(new ArrayList<PhotoZoneImgDTO>());
				
				for (MultipartFile file : imgs) {
					
					try {
						
						UUID uuid = UUID.randomUUID();
						
						String filename = uuid + "_" + file.getOriginalFilename();
						
						file.transferTo(new File(req.getRealPath("/resources/files/activity/photozone") + "\\" + filename));
						
						//첨부파일 1개 > PicDTO 1개
						PhotoZoneImgDTO idto = new PhotoZoneImgDTO();
						idto.setImg(filename);
						
						dto.getImgList().add(idto);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
				for (PhotoZoneImgDTO idto : dto.getImgList()) {
					
					idto.setPhotozone_seq(seq);
					
					result += dao.addPhotozoneImg(idto);
					
				}
				
				return result; //Case2
				
			}//if (!imgs[0].isEmpty())
			
			
			return 1; //Case1
			
		}//if (기존 첨부파일 존재 X)
		
		if (dto.getImg() != null && imgs[0].isEmpty()) { //기존 첨부파일 존재 O + 수정 후 첨부 X
			
			int imgCount = dao.countPhotozoneImg(seq);
			
			if (deleteImgSeq.length > 0 && deleteImgSeq.length < imgCount) { //기존 첨부 일부 삭제
				
				for (String imgseq : deleteImgSeq) {
					result = dao.delPhotozoneImgByImgSeq(imgseq);
				}
				
			} else if (deleteImgSeq.length == imgCount) { //기존 첨부 전체 삭제
				
				for (String imgseq : deleteImgSeq) {
					result = dao.delPhotozoneImgByImgSeq(imgseq);
				}
				
				//'photozone.png' 추가
				PhotoZoneImgDTO idto = new PhotoZoneImgDTO();
				idto.setPhotozone_seq(seq);
				idto.setImg("photozone.png");
				
				dao.addPhotozoneImg(idto);
				
			} else if (deleteImgSeq.length == 0) {
				return 1; //Case3
			}
			
		} else if (dto.getImg() != null && !imgs[0].isEmpty()) { //기존 첨부파일 존재 O + 수정 후 첨부 O
			
			if (deleteImgSeq.length > 0) {
				for (String imgseq : deleteImgSeq) {
					result = dao.delPhotozoneImgByImgSeq(imgseq);
				}
			}
			
			//첨부파일 추가
			dto.setImgList(new ArrayList<PhotoZoneImgDTO>());
			
			for (MultipartFile file : imgs) {
				
				try {
					
					UUID uuid = UUID.randomUUID();
					
					String filename = uuid + "_" + file.getOriginalFilename();
					
					file.transferTo(new File(req.getRealPath("/resources/files/activity/photozone") + "\\" + filename));
					
					//첨부파일 1개 > PicDTO 1개
					PhotoZoneImgDTO idto = new PhotoZoneImgDTO();
					idto.setImg(filename);
					
					dto.getImgList().add(idto);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			//imgList 보내서 DB에 저장하기
			for (PhotoZoneImgDTO idto : dto.getImgList()) {
				idto.setPhotozone_seq(seq);
				result += dao.addPhotozoneImg(idto);
			}
			
		}
		
		return result;
	}

	/**
	 * 
	 * 포토존을 삭제하기 위해 DB에 접근하여 포토존 위치, 포토존 이미지, 포토존을 삭제하는 메서드입니다.
	 * 
	 * @param photozone_seq 포토존 번호
	 * @return 삭제된 행의 개수
	 */
	public int delPhotozone(String[] photozone_seq) {
		
		//배열 돌며 seq 확인하여 > 해당 seq DELETE
		// - tblPhotozoneImg > DELETE
		// - tblPhotozoneLocation > DELETE
		// - tblPhotozone > DELETE
		int result = 0;
		
		for (String seq : photozone_seq) {
			
			// - tblPhotozoneImg > DELETE
			int imgCount = dao.countPhotozoneImg(seq);
			
			if (imgCount > 0) {
				dao.delPhotozoneImg(seq);
			}
			
			// - tblPhotozoneLocation > DELETE
			int locationCount = dao.countPhotozoneLocation(seq);
			
			if (locationCount > 0) {
				dao.delPhotozoneLocation(seq);
			}
			
			// - tblPhotozone > DELETE
			result += dao.delPhotozone(seq);
			
		}
		
		return result;
	}
}
