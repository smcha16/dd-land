package com.project.dd.test.worldcup.course.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.dd.test.worldcup.course.domain.CourseDTO;
import com.project.dd.test.worldcup.course.service.WorldCupCourseService;

@Controller
@RequestMapping("/admin/test/worldcup/course")
public class AdminWorldCupCourseController {

	@Autowired
	private WorldCupCourseService cwcService;

	@GetMapping(value = "/list.do")
	public String list(String word, @RequestParam(defaultValue = "1") int page, Model model) {

		String solting = "admin";
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";

		Map<String, String> map = cwcService.paging(solting, searchStatus, word, page); // 페이징
		
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("listCourse", cwcService.getAllCourse(map));

		return "admin/test/worldcup/course/list";

	}
	
	@GetMapping(value = "/view.do")
	public String view(String word, @RequestParam(defaultValue = "1") int page, Model model) {

		String solting = "admin";
		String searchStatus = (word == null || word.equals("")) ? "n" : "y";

		Map<String, String> map = cwcService.paging(solting, searchStatus, word, page); // 페이징
		
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("listCourse", cwcService.getAllCourse(map));
		model.addAttribute("cwcFinalWinTotalCount", cwcService.getCWCFinalWinTotalCount());

		return "admin/test/worldcup/course/view";

	}
	
	@PostMapping(value = "/view.do")
	public String updateCourseStatus(@RequestParam String courseSeq, @RequestParam String isTest, Model model) {
		// System.out.println("seq:" + courseSeq + " check:" + isTest);

		Map<String, String> map = new HashMap<>();
		map.put("isTest", isTest);
		map.put("courseSeq", courseSeq);

		cwcService.updateCourseStatus(map);

		return "redirect:/admin/test/worldcup/course/view.do";
	}

	@GetMapping(value = "/admin/test/worldcup/course/add.do")
	public String add(Model model) {

		return "admin/test/worldcup/course/add";
	}

	@PostMapping(value = "/addok.do")
	public String addok(Model model, CourseDTO dto, MultipartFile image, HttpServletRequest req) {
		// System.out.println("DTO: " + dto.toString());
		// System.out.println("Image File Name: " + image.getOriginalFilename());
		
		int result = cwcService.addCourse(dto, image, req);
		
		if (result > 0) {
			String courseSeq = cwcService.getCourseSeq();
			//System.out.println(courseSeq);
			
			cwcService.addCWC(dto, courseSeq);
			cwcService.addCWCWin(dto, courseSeq);
			cwcService.addCWCFinalWin(dto, courseSeq);
			
			return "redirect:/admin/test/worldcup/course/list.do";
		} else {
			model.addAttribute("alertMessage", "코스 추가에 실패했습니다.");
			return "redirect:/admin/test/worldcup/course/add.do";
		}
		
	}
	
	@GetMapping(value = "/edit.do")
	public String edit(Model model, String seq) {
		
		CourseDTO dto = cwcService.getCourse(seq);
		String img = dto.getImg();
		
		//UUID 제거
		if (img.length() > 37 && img.contains("-") && img.contains("_")) {
			String originalFileName = img.substring(img.indexOf("_") + 1);
			dto.setImg(originalFileName);
		}
		
		model.addAttribute("dto", dto);
		
		return "admin/test/worldcup/course/edit";
	}
	
	@PostMapping(value = "/editok.do")
	public String editok(Model model, CourseDTO dto, MultipartFile image, HttpServletRequest req) {

		int result = cwcService.editCourse(dto, image, req);
		
		if (result > 0) {
			return "redirect:/admin/test/worldcup/course/list.do";
		} else {
			return "redirect:/admin/test/worldcup/course/edit.do";
		}
	}
	
	@PostMapping(value = "/admin/test/worldcup/course/del.do")
	public String del(Model model, String[] course_seq) {

		cwcService.delCWC(course_seq);
		cwcService.delCWCWin(course_seq);
		cwcService.delCWCFinalWin(course_seq);
		
		int result = cwcService.delCourse(course_seq);
		
		if (result > 0) {
			return "redirect:/admin/test/worldcup/course/list.do";
		} else {
			model.addAttribute("alertMessage", "코스 삭제에 실패했습니다.");
			return "redirect:/admin/test/worldcup/course/list.do";
		}
	}
	
}
