package com.project.dd.communication.lost.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.dd.communication.lost.domain.LostPropertyDTO;
import com.project.dd.communication.lost.mapper.LostPropertyMapper;

@Controller
public class UserLostController {
	
	@Autowired
	private LostPropertyMapper mapper;
	
	@GetMapping(value = "/user/communication/lost-property/view.do")
	public String view(@RequestParam(defaultValue = "1") int page, Model model) {
		
		int pageSize = 10;
		
		int startIndex = (page - 1) * pageSize + 1;
		int endIndex = startIndex + pageSize - 1;
		
		Map<String, String> map = new HashMap<String, String>();

		map.put("startIndex", String.format("%d", startIndex));
		map.put("endIndex", String.format("%d", endIndex));
		
		int totalPosts = mapper.getTotalCount(map);
		int totalPages = (int)Math.ceil((double)totalPosts / pageSize);
		
		List<LostPropertyDTO> list = mapper.getLostPropertyList(map);
		
		for (LostPropertyDTO dto : list) {
			
			String lostDate = dto.getLost_property_date();
			
			lostDate = lostDate.substring(0, 10);
			
			dto.setLost_property_date(lostDate);
			
		}

		model.addAttribute("list", list);
		model.addAttribute("currentPage", page);
		model.addAttribute("map", map);
		model.addAttribute("totalPosts", totalPosts);
		model.addAttribute("totalPages", totalPages);

		return "user/communication/lost-property/view";

	}

}
