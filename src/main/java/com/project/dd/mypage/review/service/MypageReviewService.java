package com.project.dd.mypage.review.service;

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

import com.google.gson.Gson;
import com.project.dd.activity.attraction.domain.AttractionImgDTO;
import com.project.dd.mypage.review.domain.ReviewDTO;
import com.project.dd.mypage.review.domain.ReviewImgDTO;
import com.project.dd.mypage.review.repository.MypageReviewDAO;

@Service
public class MypageReviewService {

	@Autowired
	private MypageReviewDAO dao;

	/**
     * 회원의 리뷰 목록을 가져오는 메서드입니다.
     *
     * @param map   페이지 정보를 담은 Map 객체
     * @return      회원의 리뷰 목록
     */
	public List<ReviewDTO> list(Map<String, String> map) {

		return dao.list(map);
	}

	/**
     * 리뷰 페이징 처리를 위한 메서드입니다.
     *
     * @param page  현재 페이지 번호
     * @return      페이징 정보를 담은 Map 객체
     */
	public Map<String, String> paging(int page) {

		int pageSize = 9; // 나타났으면 하는 개수

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

	/**
     * 리뷰를 삭제하는 메서드입니다.
     *
     * @param selectedReview    삭제할 리뷰의 ID 배열
     * @return                  삭제된 리뷰의 수
     */
	public int delete(String[] selectedReview) {

		int totalDeleted = 0;

		for (String seq : selectedReview) {

			int deleted = dao.delete(seq);
			totalDeleted += deleted;

		}

		return totalDeleted;
	}

	 /**
     * 리뷰 이미지를 삭제하는 메서드입니다.
     *
     * @param selectedReview    삭제할 리뷰의 ID 배열
     * @return                  삭제된 리뷰 이미지의 수
     */
	public int imgDelete(String[] selectedReview) {

		int totalDeleted = 0;

		for (String seq : selectedReview) {

			int deleted = dao.imgDelete(seq);
			totalDeleted += deleted;

		}

		return totalDeleted;
	}

	/**
     * 리뷰를 추가하는 메서드입니다.
     *
     * @param dto               추가할 리뷰 정보를 담은 DTO 객체
     * @param imgs              리뷰에 첨부할 이미지 파일 배열
     * @param req               HTTP 요청 정보를 담은 HttpServletRequest 객체
     * @return                  추가된 리뷰 및 이미지 수
     */
	public int add(ReviewDTO dto, MultipartFile[] imgs, HttpServletRequest req) {

		int result = 0;

		result += dao.add(dto);

		// 방금 등록한 review seq 가져오기
		int seq = dao.getReviewSeq();
		dto.setReview_seq(seq + "");

		dto.setImgList(new ArrayList<ReviewImgDTO>()); // 첨부 파일 배열

		System.out.println(dto);

		if (!imgs[0].isEmpty()) {

			for (MultipartFile file : imgs) {

				try {

					UUID uuid = UUID.randomUUID();

					String filename = uuid + "_" + file.getOriginalFilename();

					file.transferTo(new File(req.getRealPath("/resources/files/communication/review") + "\\" + filename));

					// 첨부파일 1개 > PicDTO 1개
					ReviewImgDTO idto = new ReviewImgDTO();
					idto.setImg(filename);

					dto.getImgList().add(idto);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} // if

		for (ReviewImgDTO idto : dto.getImgList()) {

			idto.setReview_seq(seq + "");

			result += dao.addReviewImg(idto);
		}

		return result;
	}

	/**
     * 특정 리뷰의 상세 정보를 가져오는 메서드입니다.
     *
     * @param seq   조회할 리뷰의 ID
     * @return      조회된 리뷰 정보를 담은 DTO 객체
     */
	public ReviewDTO get(String seq) {

		return dao.get(seq);
	}

	 /**
     * 리뷰를 수정하는 메서드입니다.
     *
     * @param dto   수정할 리뷰 정보를 담은 DTO 객체
     * @return      수정 결과 (수정된 행의 수)
     */
	public int edit(ReviewDTO dto) {

		return dao.edit(dto);
	}

	/**
     * 리뷰 목록에서 이미지 정보를 가져오는 메서드입니다.
     *
     * @param list  리뷰 목록
     * @return      리뷰 목록에 포함된 이미지 정보를 JSON 형태로 반환
     */
	public String getReviewImgList(List<ReviewDTO> list) {

		List<ReviewImgDTO> iList = new ArrayList<ReviewImgDTO>();

		for (ReviewDTO dto : list) {

			for (ReviewImgDTO img_dto : dto.getImgList()) {

				if (img_dto.getImg() != null) {

					iList.add(img_dto);

				}

			}

		}

		Gson gson = new Gson();

		String imgList = gson.toJson(iList);

		return imgList;

	}

}
