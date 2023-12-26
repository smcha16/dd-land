package com.project.dd.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
//import javax.websocket.server.ServerEndpointConfig;
import org.springframework.stereotype.Component;
//import org.springframework.web.socket.server.standard.SpringConfigurator;


import com.google.gson.Gson;
import com.project.dd.server.domain.Message;

/**
 * 채팅의 서버 역할을 하는 클래스
 * @author leeje
 *
 */

//@Component
@ServerEndpoint(value = "/chatserver.do")/* , configurator = com.project.dd.server.ServerEndpointConfig.class */
public class ChatServer {
	
	//현재 채팅 서버에 접속 중인 클라이언트 목록
	//@Inject
	private static List<Session> sessionList = new ArrayList<Session>();
	
	//클라이언트 접속
	@OnOpen
	public void handleOpen(Session session) {
		System.out.println("클라이언트가 접속했습니다.");
		
		sessionList.add(session); //현재 접속자의 정보를 배열에 추가
		
		//현재 대화방에 누가 있는지?
		checkSessionList();
		
		//대화방에 혹시라도 연결이 끊긴 사람이 있으면 제거!
		clearSessionList();
		
	}
	
	//클라이언트로부터 메시지 전달
	@OnMessage
	public void handleMessage(String msg, Session session) {
		System.out.println(msg);
		
		//JSON 형식의 문자열 -> 자바 클래스 객체로 변환
		Gson gson = new Gson();
		
		Message message = gson.fromJson(msg, Message.class);
		
		if (message.getCode().equals("1")) {
			
			for (Session s : sessionList) {
				
				//모든 접속자 중에서 방금 메시지를 보낸 세션을 제외하고 검색
				if (s != session) {
					//본인 이외의 세션(소켓)에게 현재 접속자를 알리는 메시지 전달
					try {
						s.getBasicRemote().sendText(msg);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		} else if (message.getCode().equals("2")) {
			
			//기존 접속자가 대화방을 나갔습니다.
			sessionList.remove(session);
			
			for (Session s : sessionList) {
				try {
					//'누군가가 나갔습니다.'라는 메시지를 남아있는 모든 사람에게..
					s.getBasicRemote().sendText(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (message.getCode().equals("3")) {
			
			//대화 메시지
			//- 보낸 사람 빼고 나머지 사람에게 전달
			for (Session s : sessionList) {
				if (s != session) { //방금 메세지 보낸 사람 빼고 나머지 사람들을 찾아서 메세지 전달하기
					try {
						s.getBasicRemote().sendText(msg);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			
		} else if (message.getCode().equals("4")) {
			
			//대화 메시지
			//- 보낸 사람 빼고 나머지 사람에게 전달
			for (Session s : sessionList) {
				if (s != session) { //방금 메세지 보낸 사람 빼고 나머지 사람들을 찾아서 메세지 전달하기
					try {
						s.getBasicRemote().sendText(msg);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	//어떤 접속자들(세션들)이 접속해 있는지 확인하는 메서드
	private void checkSessionList() {
		System.out.println("Session List");
		for (Session session : sessionList ) {
			System.out.println();
			System.out.println(session.getId());
		}
		System.out.println();
	}
	
	private void clearSessionList() {
		
		//List 계열의 컬렉션은 향상된 for문 내에서 요소 추가/삭제 하는 행동이 불가능하다.
		//따라서 해당 업무를 위해서는
		//1. 일반 for문 사용
		//2. Iterator 사용
		//방법이 있다.
		
		
		Iterator<Session> iter = sessionList.iterator();
		
		while (iter.hasNext()) {
			
			if (!(iter.next()).isOpen()) {
				//혹시 연결이 끊어진 세션이 있으면..
				iter.remove(); //리스트에서 제거
			}
		}
	}
	
}
