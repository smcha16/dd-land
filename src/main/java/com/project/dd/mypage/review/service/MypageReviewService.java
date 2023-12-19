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

	public List<ReviewDTO> list(Map<String, String> map) {

		return dao.list(map);
	}

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

	public int delete(String[] selectedReview) {

		int totalDeleted = 0;

		for (String seq : selectedReview) {

			int deleted = dao.delete(seq);
			totalDeleted += deleted;

		}

		return totalDeleted;
	}

	public int imgDelete(String[] selectedReview) {

		int totalDeleted = 0;

		for (String seq : selectedReview) {

			int deleted = dao.imgDelete(seq);
			totalDeleted += deleted;

		}

		return totalDeleted;
	}

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

					file.transferTo(new File(req.getRealPath("/resources/files/review") + "\\" + filename));

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

	public ReviewDTO get(String seq) {

		return dao.get(seq);
	}

	public int edit(ReviewDTO dto) {

		return dao.edit(dto);
	}

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
