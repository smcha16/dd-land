package com.project.dd.activity.attraction.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.activity.attraction.domain.AttractionDTO;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.activity.attraction.domain.BookUserDTO;
import com.project.dd.activity.attraction.repository.AttractionDAO;
import com.project.dd.activity.attraction.repository.AttractionRepository;
import com.project.dd.login.domain.CustomUser;


/**
 * 
 * 어트랙션 페이지의 비즈니스 로직을 담당하는 Service 클래스입니다.
 * 
 * @author 박나래
 *
 */
@Service
public class AttractionService {

	@Autowired
	private AttractionDAO dao;
	
	@Autowired
	private AttractionRepository repo;

	public Map<String, String> userPaging(int page) {
		
		//User 페이지 노출 목록 개수 설정
		int pageSize = 9;
		
		//페이지별로 가져올 index 번호
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;
		
		//페이징용 Map 생성
		Map<String, String> map = new HashMap<String, String>();
	
		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = dao.getUserPagingTotalPosts();
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		return map;
	}

	/**
	 * 
	 * 페이지 번호를 출력하기 위해 페이지당 노출 목록 개수 설정 및 검색 결과값의 개수를 조회하는 메서드입니다.
	 * 
	 * @param searchStatus 검색여부
	 * @param word 검색어
	 * @param page 페이지 번호
	 * @return 위의 정보가 담긴 map 객체
	 */
	public Map<String, String> adminPaging(String searchStatus, String word, int page) {
		
		//Admin 페이지 노출 목록 개수 설정
		int pageSize = 10;
		
		//페이지별로 가져올 index 번호
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;
		
		//페이징용 Map 생성
		Map<String, String> map = new HashMap<String, String>();

		map.put("searchStatus", searchStatus);
		map.put("word", word);
		
		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = dao.getAdminPagingTotalPosts(map);
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
	public List<AttractionDTO> getOpenAttractionList(Map<String, String> map) {
		
		List<AttractionDTO> list = dao.getOpenAttractionList(map);
		
		for (AttractionDTO dto : list) {
			
			//DB 내 태그 비활성화 처리 '&gt;, &lt;' 처리
			String newInfo = dto.getInfo(); 
			newInfo = newInfo.replace("<", "&lt");
			newInfo = newInfo.replace(">", "&gt");
			
			String newRestriction = dto.getRestriction();
			newRestriction = newRestriction.replace("<", "&lt");
			newRestriction = newRestriction.replace(">", "&gt");
			
			//DB 개행 -> '<br>' 태그 처리
			newInfo = newInfo.replaceAll("(\r\n|\r|\n)", "<br>");
			newRestriction = newRestriction.replaceAll("(\r\n|\r|\n)", "<br>");
			
			dto.setInfo(newInfo);
			dto.setRestriction(newRestriction);
			
		}
		
		return list;
		
	}

	public List<AttractionDTO> getCloseAttractionList() {
		
		List<AttractionDTO> list = dao.getCloseAttractionList();
		
		for (AttractionDTO dto : list) {
			
			//DB 내 태그 비활성화 처리 '&gt;, &lt;' 처리
			String newInfo = dto.getInfo(); 
			newInfo = newInfo.replace("<", "&lt");
			newInfo = newInfo.replace(">", "&gt");
			
			String newRestriction = dto.getRestriction();
			newRestriction = newRestriction.replace("<", "&lt");
			newRestriction = newRestriction.replace(">", "&gt");
			
			//DB 개행 -> '<br>' 태그 처리
			newInfo = newInfo.replaceAll("(\r\n|\r|\n)", "<br>");
			newRestriction = newRestriction.replaceAll("(\r\n|\r|\n)", "<br>");
			
			dto.setInfo(newInfo);
			dto.setRestriction(newRestriction);
			
		}
		
		return list;
	}

	/**
	 * 
	 * 전체 어트랙션 목록을 가져오는 메서드입니다. 
	 * 
	 * @param map 페이징을 위한 Map 객체
	 * @return AttractionDTO 객체 List
	 */
	public List<AttractionDTO> getAllAttractionList(Map<String, String> map) {
		
		List<AttractionDTO> list = dao.getAllAttractionList(map);
		
		for (AttractionDTO dto : list) {
			
			//DB 내 태그 비활성화 처리 '&gt;, &lt;' 처리
			String newInfo = dto.getInfo(); 
			newInfo = newInfo.replace("<", "&lt");
			newInfo = newInfo.replace(">", "&gt");
			
			String newRestriction = dto.getRestriction();
			newRestriction = newRestriction.replace("<", "&lt");
			newRestriction = newRestriction.replace(">", "&gt");
			
			//DB 개행 -> '<br>' 태그 처리
			newInfo = newInfo.replaceAll("(\r\n|\r|\n)", "<br>");
			newRestriction = newRestriction.replaceAll("(\r\n|\r|\n)", "<br>");
			
			dto.setInfo(newInfo);
			dto.setRestriction(newRestriction);
			
		}
		
		return list;
	}

	/**
	 * 
	 * 특정 번호의 어트랙션 정보를 가져오는 메서드
	 * 
	 * @param seq 어트랙션 번호
	 * @return 어트랙션 dto 객체
	 */
	public AttractionDTO getAttraction(String seq) {
		
		AttractionDTO dto = dao.getAttraction(seq);
		
		//DB 내 태그 비활성화 처리 '&gt;, &lt;' 처리
		String newInfo = dto.getInfo(); 
		newInfo = newInfo.replace("<", "&lt");
		newInfo = newInfo.replace(">", "&gt");
		
		String newRestriction = dto.getRestriction();
		newRestriction = newRestriction.replace("<", "&lt");
		newRestriction = newRestriction.replace(">", "&gt");
		
		//DB 개행 -> '<br>' 태그 처리
		newInfo = newInfo.replaceAll("(\r\n|\r|\n)", "<br>");
		newRestriction = newRestriction.replaceAll("(\r\n|\r|\n)", "<br>");
		
		dto.setInfo(newInfo);
		dto.setRestriction(newRestriction);
			
		return dto;
		
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
			closeCount++;
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
	 * 어트랙션을 삭제하기 위해 DB에 접근하여 어트랙션 위치, 어트랙션 이미지, 어트랙션을 삭제하는 메서드
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
	 * @param map 
	 * 
	 * @return 회원어트랙션예약 dto 객체 list
	 */
	public List<BookUserDTO> getAttractionBookList(Map<String, String> map) {
		return dao.getAttractionBookList(map);
	}

	public List<Map<String, Object>> searchAttraction(String word) {
		
		try {
			
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			
//			RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("172.19.66.47", 9200, "http")));
			RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
			
			//인덱스 선택
			SearchRequest searchRequest = new SearchRequest("attraction");
			
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(100);
			
			//*** 검색 쿼리
			//- 검색에서 가장 흔한 패턴
			//- bool query (must(match 검색어) + should(match_phrase 검색어)) :: 잘 모르겠다면 이 검색 사용하기 > 무난한 검색)
			searchSourceBuilder.query(
				QueryBuilders.boolQuery()
					.must(QueryBuilders.matchQuery("name", word))
					.should(QueryBuilders.matchPhraseQuery("name", word))
			
			);
			
			searchRequest.source(searchSourceBuilder);
			
			//실제 검색 요청
			SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
			
			SearchHits searchHits = searchResponse.getHits();
			
			for (SearchHit hit : searchHits) {
				
				Map<String, Object> map = hit.getSourceAsMap();
				map.put("id", hit.getId());
				map.put("score", hit.getScore());
				list.add(map);
				
			}
			
			System.out.println("이곳은 서비스측: " + list.toString());
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Map<String, String> reservationAdminPaging(String searchStatus, String word, int page) {
		
		//Admin 페이지 노출 목록 개수 설정
		int pageSize = 10;
		
		//페이지별로 가져올 index 번호
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;
		
		//페이징용 Map 생성
		Map<String, String> map = new HashMap<String, String>();

		map.put("searchStatus", searchStatus);
		map.put("word", word);
		
		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = dao.getReservationAdminPagingTotalPosts(map);
		int totalPages = (int) Math.ceil((double) totalPosts / pageSize);
		
		map.put("totalPosts", String.format("%d", totalPosts));
		map.put("totalPages", String.format("%d", totalPages));
		
		return map;
	}

}
