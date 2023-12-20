package com.project.dd.activity.attraction.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.domain.BookUserDTO;
import com.project.dd.activity.attraction.repository.AttractionDAO;
import com.project.dd.login.domain.CustomUser;


/**
 * 
 * 어트랙션 DB에 접근하여 실행된 레코드의 수를 반환하는 Service 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Service
public class AttractionService {

	@Autowired
	AttractionDAO dao;

	/**
	 * 
	 * 페이지 번호를 출력하기위해 DB에 접근하여 어트랙션 개수 및 검색 결과값을 조회하는 메서드입니다. 
	 * 
	 * @param searchStatus 검색여부
	 * @param word 검색어
	 * @param page 페이지 번호
	 * @param solting 사용자/관리자별 한 페이지당 노출 목록 개수 설정
	 * @return 위의 정보가 담긴 map 객체
	 */
	public Map<String, String> paging(String searchStatus, String word, int page, String solting) {
		
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

		//검색 관련 추가
		map.put("searchStatus", searchStatus);
		map.put("word", word);
		
		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = dao.getTotalCount(map);
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		
		return map;
		
	}
	
	/**
	 * 
	 * 운영중인 어트랙션 목록을 가져오는 메서드
	 * 
	 * @param map 객체
	 * @return 어트랙션 dto 객체가 담긴 list
	 */
	public List<AttractionDTO> getAttractionList(Map<String, String> map) {
		return dao.getAttractionList(map);
	}

	/**
	 * 
	 * 특정 번호의 어트랙션 정보를 가져오는 메서드
	 * 
	 * @param seq 어트랙션 번호
	 * @return 어트랙션 dto 객체
	 */
	public AttractionDTO getAttraction(String seq) {
		return dao.getAttraction(seq);
	}

	/**
	 * 
	 * 전체 어트랙션 이미지를 가져오는 메서드
	 * 
	 * @param seq 어트랙션 번호
	 * @return 어트랙션 이미지 dto 객체가 담긴 list
	 */
	public List<AttractionImgDTO> getAttractionImgList(String seq) {
		return dao.getAttractionImgList(seq);
	}

	/**
	 * 
	 * 금일 운휴 어트랙션 개수를 세는 메서드
	 * 
	 * @param list 어트랙션 dto 객체가 담긴 list
	 * @return 금일 운휴인 어트랙션 개수
	 */
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

	/**
	 * 
	 * 어트랙션 위치정보의 중복 검사를 진행하는 메서드
	 * 
	 * @param dto 어트랙션 dto 객체
	 * @return 중복된 레코드의 개수
	 */
	public int checkLocationDuplication(AttractionDTO dto) {
		return dao.checkLocationDuplication(dto);
	}

	
	/**
	 * 
	 * 어트랙션 추가를 위해 어트랙션, 어트랙션 위치, 어트랙션 이미지 DB에 접근하는 메서드
	 * 
	 * @param dto 어트랙션 dto 객체
	 * @param imgs 첨부할 이미지 멀티파일 객체 배열
	 * @param req HttpServletRequest 객체
	 * @return 테이블에 추가된 행의 개수
	 */
	//어트랙션 추가
	// - 1. tblAttraction 추가
	// - 2. tblAttractionLocation 추가
	// - 3. tblAttractionImg 추가
	public int addAttraction(AttractionDTO dto, MultipartFile[] imgs, HttpServletRequest req) {
		
		//server측에서 중복 검사 한번 더 진행(1 이상: 중복, 0: 가능)
		// - 어트랙션명 중복 검사
		int result = dao.checkNameDuplication(dto);
		
		if (result > 0) {
			return -1;
		}
		
		// - 위치 중복 검사
		result = dao.checkLocationDuplication(dto);
		
		if (result > 0) {
			return -1;
		}
		
		//1. tblAttraction 추가
		// - '제한사항'컬럼 미 입력 시 > '제한 없음' default 문구 추가
		if (dto.getRestriction() == null || dto.getRestriction().equals("")) {
			dto.setRestriction("제한 없음");
		}
		
		result = dao.addAttraction(dto);
		
		//방금 등록한 Attraction seq 가져오기
		String seq = dao.getAttractionSeq();
		dto.setAttraction_seq(seq);
		
		//2. tblAttractionLocation 추가
		result = dao.addAttractionLocation(dto);
		
		//3. tblAttractionImg 추가
		dto.setImgList(new ArrayList<AttractionImgDTO>()); //첨부 파일 배열
		
		if (imgs[0].isEmpty()) {
			
			AttractionImgDTO idto = new AttractionImgDTO();
			
			idto.setImg("attraction.png");
			
			dto.getImgList().add(idto);
			
		} else {
			
			for (MultipartFile file : imgs) {
				
				try {
					
					UUID uuid = UUID.randomUUID();
					
					String filename = uuid + "_" + file.getOriginalFilename();
					
					file.transferTo(new File(req.getRealPath("/resources/files/activity/attraction") + "\\" + filename));
					
					//첨부파일 1개 > PicDTO 1개
					AttractionImgDTO idto = new AttractionImgDTO();
					idto.setImg(filename);
					
					dto.getImgList().add(idto);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}//if
		
		for (AttractionImgDTO idto : dto.getImgList()) {
			
			idto.setAttraction_seq(seq + "");
			
			result += dao.addAttractionImg(idto);
		}
		
		//테스트용 실제 경로 출력
//		System.out.println(req.getRealPath("/resources/files/activity/attraction"));
		
		return result;
	}

	
	
	/**
	 * 
	 * 어트랙션을 삭제하게 위해 DB에 접근하여 어트랙션 위치, 어트랙션 이미지, 어트랙션을 삭제하는 메서드
	 * 
	 * @param attraction_seq 어트랙션 번호
	 * @return 삭제된 행의 개수
	 */
	//어트랙션 삭제
	// - 1. 배열 돌면서 seq 뽑아내기
	// - 2. 해당하는 seq의 레코드 UPDATE
	public int delAttraction(String[] attraction_seq) {
		
		int result = 0;
		
		//1. 배열 돌면서 seq 뽑아내기
		for (String seq : attraction_seq) {
			
			//2. 해당하는 seq의 레코드 UPDATE > tblAttractionImg DELETE
			// - AttractionImg가 있으면 > 삭제
			// - AttractionImg가 없으면 > tblAttractionLocation DELETE
			int imgCount = dao.countAttractionImg(seq);
			
			if (imgCount > 0) { //Img 삭제
				
				dao.delAttractionImg(seq);
				
			}
			
			//3. 해당하는 seq의 레코드 UPDATE > tblAttractionLocation DELETE
			// - AttractionLocation이 있으면 > 삭제
			// - AttractionLocaion이 없으면 > tblAttraction UPDATE
			int locationCount = dao.countAttractionLocation(seq);
			
			if (locationCount > 0) { //Location 삭제
				
				dao.delAttractionLocation(seq);
				
			}
			
			//4. 해당하는 seq의 레코드 UPDATE > tblAttraction UPDATE
			result += dao.delAttraction(seq);
		}
		
		return result;
	}

	/**
	 * 
	 * 어트랙션명의 중복 검사를 위한 메서드
	 * 
	 * @param dto 어트랙션 dto 객체
	 * @return 중복된 레코드의 개수
	 */
	public int checkNameDuplication(AttractionDTO dto) {
		return dao.checkNameDuplication(dto);
	}

	/**
	 * 
	 * 어트랙션 수정을 위해 어트랙션, 어트랙션 위치, 어트랙션 이미지에 접근하는 메서드
	 * 
	 * @param dto 어트랙션 dto 객체
	 * @param imgs 추가한 멀티파일 객체 배열
	 * @param req HttpServletRequest 객체
	 * @param deleteImgSeq 삭제할 어트랙션 번호
	 * @return 수정된 행의 개수
	 */
	public int editAttraction(AttractionDTO dto, MultipartFile[] imgs, HttpServletRequest req, String[] deleteImgSeq) {

		//어트랙션 수정
		// - server측에서 이름 유효성검사(생략..)
		// - server측에서 위치 유효성검사(생략..)
		// 1. imgs[0].isEmpty 인가? YES: 추가 사진 X > deleteImgSeq 삭제 GO, NO: 추가 업로드 사진 INSERT 필요 > deleteImgSeq 삭제 GO
		// 2. deleteImgSeq.length > 0 인가? YES: 해당 seq 삭제, NO: 삭제 img 無
		// 3. 만일 기존에 이미지를 추가 안했다 > (attraction.png) > 근데 수정해서 이미지를 추가 했따. > 그럼 attraction.png 지우고 업로드한 이미지 추가 필요
		
		//파일 관련 경우의 수 7가지 Case
		//1. 기존 첨부 X + 수정 후 첨부 X: 'attraction.png' 유지
		//2. 기존 첨부 X + 수정 후 첨부 O: 'attraction.png' 삭제 및 첨부 파일 추가
		//3. 기존 첨부 O + 수정 후 첨부 X + 기존 파일 유지: 기존 AttractionImg 유지
		//4. 기존 첨부 O + 수정 후 첨부 X + 기존 파일 일부 삭제: 기존 파일 일부 삭제
		//5. 기존 첨부 O + 수정 후 첨부 X + 기존 파일 전체 삭제: 'attraction.png' 추가
		//6. 기존 첨부 O + 수정 후 첨부 O + 기존 파일 유지: 기존 AttractionImg 유지 및 첨부 파일 추가
		//7. 기존 첨부 O + 수정 후 첨부 O + 기존 파일 일부/전체 삭제: 기존 파일 삭제 및 첨부 파일 추가
		
//		System.out.println(dto.toString());
//		System.out.println(imgs[0].isEmpty());
//		System.out.println(imgs.length);
//		System.out.println(Arrays.toString(deleteImgSeq));
//		System.out.println(deleteImgSeq.length);
		
		String seq = dto.getAttraction_seq();
		int result = 0;
		
		//공통적으로 tblAttraction, tblAttractionLocation 수정 처리
		// - '제한사항'컬럼 미 입력 시 > '제한 없음' default 문구 추가
		if (dto.getRestriction() == null || dto.getRestriction().equals("")) {
			dto.setRestriction("제한 없음");
		}
		result = dao.editAttraction(dto);
		result = dao.editAttractionLocation(dto);
		
//		System.out.println(dto.getImg());

		//기존 첨부 O vs X 판단: dto.getImg().equalsIgnoreCase("attraction.png") > Case 1, Case 2
		if (dto.getImg() != null && dto.getImg().equalsIgnoreCase("attraction.png")) {
			
			if (!imgs[0].isEmpty()) { //2번 경우의 수
			
				//tblAttractionImg > 'attraction.png' 삭제
				dao.delAttractionImg(seq);
					
				// 첨부파일 추가 > AttracionImg
				// - 파일 이름 지정 및 imgList에 담기
				dto.setImgList(new ArrayList<AttractionImgDTO>()); //첨부 파일 배열
				
				for (MultipartFile file : imgs) {
					
					try {
						
						UUID uuid = UUID.randomUUID();
						
						String filename = uuid + "_" + file.getOriginalFilename();
						
						file.transferTo(new File(req.getRealPath("/resources/files/activity/attraction") + "\\" + filename));
						
						//첨부파일 1개 > PicDTO 1개
						AttractionImgDTO idto = new AttractionImgDTO();
						idto.setImg(filename);
						
						dto.getImgList().add(idto);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
				//imgList 보내서 DB에 저장하기
				for (AttractionImgDTO idto : dto.getImgList()) {
					
					idto.setAttraction_seq(seq);
					
					result += dao.addAttractionImg(idto);
				}
				
				return result;
			}
			
			return 1;//1번 경우의 수
		}
		
		//2. 수정 후 첨부 O vs X 판단: imgs[0].isEmpty > Case 3 ~ 7
		if (dto.getImg() != null && imgs[0].isEmpty()) { //Case 3, Case 4, Case 5(수정 후 첨부 X)
			
			//일부 삭제 or 전체 삭제 판단
			int imgCount = dao.countAttractionImg(seq);
			
			if (deleteImgSeq.length > 0 && deleteImgSeq.length < imgCount) { //일부 삭제
				
				for (String imgseq : deleteImgSeq) {
					result = dao.delAttractionImgByImgSeq(imgseq);
				}
				
			} else if (deleteImgSeq.length == imgCount) { //전체 삭제
				
				for (String imgseq : deleteImgSeq) {
					result = dao.delAttractionImgByImgSeq(imgseq);
				}
				
				//'attraction.png' 추가
				AttractionImgDTO idto = new AttractionImgDTO();
				idto.setAttraction_seq(seq);
				idto.setImg("attraction.png");
				
				dao.addAttractionImg(idto);
				
			} else if (deleteImgSeq.length == 0) {
				return 1; //3번 경우의 수
			}
			
			
		} else if (dto.getImg() != null && !imgs[0].isEmpty()) { //Case 6, Case 7
			
			if (deleteImgSeq.length > 0) { //기존 파일 일부 또는 전체 삭제
				
				for (String imgseq : deleteImgSeq) {
					result = dao.delAttractionImgByImgSeq(imgseq);
				}
				
			}
			
			//공통 첨부파일 추가
			dto.setImgList(new ArrayList<AttractionImgDTO>()); //첨부 파일 배열
			
			for (MultipartFile file : imgs) {
				
				try {
					
					UUID uuid = UUID.randomUUID();
					
					String filename = uuid + "_" + file.getOriginalFilename();
					
					file.transferTo(new File(req.getRealPath("/resources/files/activity/attraction") + "\\" + filename));
					
					//첨부파일 1개 > PicDTO 1개
					AttractionImgDTO idto = new AttractionImgDTO();
					idto.setImg(filename);
					
					dto.getImgList().add(idto);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			//imgList 보내서 DB에 저장하기
			for (AttractionImgDTO idto : dto.getImgList()) {
				
				idto.setAttraction_seq(seq);
				
				result += dao.addAttractionImg(idto);
			}
		}
		
		return result;
	}

	/**
	 * 
	 * 가장 최근에 추가된 어트랙션 번호를 구하는 메서드
	 * 
	 * @return 어트랙션 번호
	 */
	public String getAttractionSeq() {
		return dao.getAttractionSeq();
	}

	/**
	 * 
	 * 전체 어트랙션 이미지 목록을 가져오는 메서드
	 * 
	 * @return 어트랙션 이미지 dto 객체 list
	 */
	public List<AttractionImgDTO> getAllAttractionImgList() {
		return dao.getAllAttractionImgList();
	}

	/**
	 * 
	 * 선택한 시간대의 해당 어트랙션의 예약 가능 인원을 조회할 수 있는 메서드
	 * 
	 * @param dto 회원어트랙션예약 dto 객체
	 * @return 예약 가능 인원수
	 */
	public int checkAvailableCapacity(BookUserDTO dto) {
		return dao.checkAvailableCapacity(dto);
	}

	/**
	 * 
	 * 어트랙션 예약을 추가할 수 있는 메서드
	 * 
	 * @param dto 회원어트랙션예약 dto 객체
	 * @return 추가된 행의 개수
	 */
	public int addAttractionBook(BookUserDTO dto) {
		
		return dao.addAttractionBook(dto);
	}

	/**
	 * 
	 * 시간대별 예약 가능 인원을 가져오는 메서드
	 * 
	 * @param dto 회원어트랙션예약 dto 객체
	 * @return 시간대별 예약 가능 인원
	 */
	public int getAttractionBookCapacity(BookUserDTO dto) {
		return dao.getAttractionBookCapacity(dto);
	}

	/**
	 * 
	 * 어트랙션 예약 내역을 전체 조회할 수 있는 메서드
	 * 
	 * @return 회원어트랙션예약 dto 객체 list
	 */
	public List<BookUserDTO> getAttractionBookList() {
		return dao.getAttractionBookList();
	}

}
