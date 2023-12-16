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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.repository.AttractionDAO;

@Service
public class AttractionService {

	@Autowired
	AttractionDAO dao;

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
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		return map;
		
	}
	
	public List<AttractionDTO> getAttractionList(Map<String, String> map) {
		return dao.getAttractionList(map);
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


	public int checkLocationDuplication(AttractionDTO dto) {
		return dao.checkLocationDuplication(dto);
	}

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

	public int checkNameDuplication(AttractionDTO dto) {
		return dao.checkNameDuplication(dto);
	}

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

	public int getAttractionSeq() {
		return dao.getAttractionSeq();
	}

	public List<AttractionImgDTO> getAllAttractionImgList() {
		return dao.getAllAttractionImgList();
	}

}
