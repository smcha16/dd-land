package com.project.dd.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *  오픈채팅 관련 요청을 처리하는 Controller 클래스
 * @author leeje
 *
 */

@Controller
public class ChatController {
	
	/**
     * 닉네임을 적어 채팅방에 들어갈 수 있는 페이지를 보여주는 메서드
     *
     * @param model Model 객체
     * @return 오픈채팅 view
     */
	@GetMapping(value = "/user/chat/view.do")
	public String view(Model model) {

		return "user/chat/view";
		
	}

	/**
     * 실제 채팅을 하는 창을 보여주는 메서드
     *
     * @param model Model 객체
     * @return 채팅 창 view
     */
	@GetMapping(value = "/user/chat/chat.do")
	public String chat(Model model) {

		return "user/chat/chat";
		
	}
	
}
