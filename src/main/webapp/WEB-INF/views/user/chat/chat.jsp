<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	html, body {
		padding: 0 !important;
		margin: 0 !important;
		background-color: #FFF !important; 
		display: block;
		overflow: hidden;
	}
	
	body > div {
		margin: 0; 
		padding: 0; 
	}

	#main {
		width: 400px;
		height: 510px;
		margin: 3px;
		display: grid;
		grid-template-rows: repeat(12, 1fr);
	}
	
	
	#header > h2 {		
		margin: 0px;
		margin-bottom: 10px;
		padding: 5px;
	}

	#list {
		border: 1px solid var(--border-color);
		box-sizing: content-box;
		padding: .5rem;
		grid-row-start: 2;
		grid-row-end: 12;
		font-size: 14px;
		overflow: auto;
	}
	
	#msg {
		margin-top: 3px;
	}
	
	#list .item {
		font-size: 14px;
		margin: 15px 0;
	}
	
	#list .item > div:first-child {
		display: flex;
	}
	
	#list .item.me > div:first-child {
		justify-content: flex-end;
	}
	
	#list .item.other > div:first-child {
		justify-content: flex-end;
		flex-direction: row-reverse;
	}
	
	#list .item > div:first-child > div:first-child {
		font-size: 10px;
		color: #777;
		margin: 3px 5px;
	}
	
	#list .item > div:first-child > div:nth-child(2) {
		border: 1px solid var(--border-color);
		display: inline-block;
		min-width: 100px;
		max-width: 250px;
		text-align: left;
		padding: 3px 7px;
	}
	
	#list .state.item > div:first-child > div:nth-child(2) {
		background-color: #EEE;
	}
	
	#list .item > div:last-child {
		font-size: 10px;
		color: #777;
		margin-top: 5px;
	}
	
	#list .me {
		text-align: right;
	}
	
	#list .other {
		text-align: left;
	}
	
	#list .msg.me.item > div:first-child > div:nth-child(2) {
		background-color: rgba(255, 99, 71, .2);
	}
	
	#list .msg.other.item > div:first-child > div:nth-child(2) {
		background-color: rgba(100, 149, 237, .2);
	}
	
	#list .msg img {
		width: 150px;
	}
	span{
		color : #ce1212;
	}
</style>
	<main>
	<!-- chat.jsp -->
	
	<div id="main">
		<div id="header"><h2><span>.</span>D_D<span>.</span> <small>홍길동</small></h2></div>
		<div id="list"></div>
		<input type="text" id="msg" placeholder="대화 내용을 입력하세요.">
	</div>
	</main>

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script>
	
		/*
			메시지 규칙
			- code: 상태코드
				- 1: 새로운 유저가 들어옴
				- 2: 기존 유저가 나감
				- 3: 메시지 전달
			- sender: 보내는 유저명
			- receiver: 받는 유저명
			- content: 대화 내용
			- regdate: 날짜/시간
				
				
		*/
	
		let name;
		let ws;
		const url = 'ws://localhost:8100/dd/chatserver.do';
		/* cmd -> ipconfig해서 나오는 ip주소를 url에 적어야 다른 컴퓨터에서 이 주소를 ip를 입력한 주소를 통해 들어올 수 있다. */
		
		function connect(name) {
			
			window.name = name;
			$('#header small').text(name);
			
			//연결하기 > 소켓 생성
			ws = new WebSocket(url);
			
			//연결 후 작업
			ws.onopen = function(evt) {
				log('서버 연결 성공');
				
				//메시지 규칙
				//ws.send('1|홍길동'); //상태코드(1): 본인대화명
				//ws.send('2|1|2|점심 뭐먹어?'); //상태코드(2): 서로간에 주고받는 대화 > 상태코드|보내는사람|받는사람|대화내용
				//ws.send('3|홍길동'); //상태코드(3): 대화방을 나가는 사람
				
				let message = {
						code: '1',
						sender: window.name,
						receiver: '',
						content: '',
						regdate: new Date().toLocaleString()
				};
				
				ws.send(JSON.stringify(message));
				print('', '대화방에 참여했습니다.', 'me', 'state', message.regdate);
				
				$('#msg').focus();
				
			};
			
			//서버에서 클라이언트에게 전달한 메시지
			ws.onmessage = function(evt) {
				log('메시지 수신');
				
				//console.log(evt.data);
				
				let message = JSON.parse(evt.data);
				//console.log(message);
				
				if (message.code == '1') {
					print('',`[\${message.sender}]님이 들어왔습니다.`, 'other', 'state', message.regdate);
				} else if (message.code == '2') {
					print('',`[\${message.sender}]님이 나갔습니다.`, 'other', 'state', message.regdate);
				} else if (message.code == '3') { //남이 나한테 보낸것을 화면에 출력하기
					print(message.sender, message.content, 'other', 'msg', message.regdate);
				} else if (message.code == '4') { //남이 나한테 보낸것을 화면에 출력하기
					printEmoticon(message.sender, message.content, 'other', 'msg', message.regdate);
				}
				
			}
		}//connect
		
		function log(msg) {
			console.log(`[\${new Date().toLocaleTimeString()}] \${msg}`);
		}
		
		//대화창 출력
		function print(name, msg, side, state, time) {
			
			let temp = `
			
			<div class="item \${state} \${side}">
				<div>
					<div>\${name}</div>
					<div>\${msg}</div>
				</div>
				<div>\${time}</div>
			</div>
			
			`;
			
			
			$('#list').append(temp);
			
			//새로운 내용 추가 + 스크롤을 바닥으로 내림
			scrollList();
			
		}
		
		
		//이모티콘 출력
		function printEmoticon(name, msg, side, state, time) {
			
			let temp = `
			
			<div class="item \${state} \${side}">
				<div>
					<div>\${name}</div>
					<div style="background-color: #FFF; border: 0;"><img src="/dd/resources/files/emoticon\${msg}.png"></div>
				</div>
				<div>\${time}</div>
			</div>
			
			`;
			
			
			$('#list').append(temp);
			
			//이모티콘이 추가된 후 + 스크롤을 바닥으로 내림
			setTimeout(scrollList, 100);
			
		}
		
		
		
		//창이 닫히기 바로 직전 발생
		$(window).on('beforeunload', function() {
			disconnect();
		});
		
		function disconnect() {
			
			//대화방에서 나가기 > 다른 사람에게 알리기
			let message = {
					code: '2',
					sender: window.name,
					receiver: '',
					content: '',
					regdate: new Date().toLocaleString()
			};
			
			ws.send(JSON.stringify(message));
		}
		
		$('#msg').keydown(function(evt) {
			
			if (evt.keyCode == 13) {
				
				//입력한 대화 내용을 서버로 전달
				//ws.send('전달 내용'); > 이렇게 막무가내로 전달하면 서버가 어떤 내용인지 구분하지 못한다.
				//따라서 아래와 같이 object 틀에 맞춰서 전달한다.
				let message = {
						code: '3',
						sender: window.name,
						receiver: '',
						content: $('#msg').val(),
						regdate: new Date().toLocaleString()
				};
				
				//일반대화 vs 이모티콘인지 구분하여 서버로 전달하는 과정
				if ($('#msg').val().startsWith('/')) {
					//대화(X) > 이모티콘(O)
					message.code = '4';
					//alert(message.content);
				}
				
				
				//객체를 보낼수는 없으므로 주고 받기 쉬운 JSON 형태의 format으로 변경하여 전달
				ws.send(JSON.stringify(message));
				
				//다시 대화를 입력해야하니까 초기화 시키고 focus 잡아주기
				$('#msg').val('').focus();
				
				//CSS 적용을 위해 'me', 'msg'등이 붙은 것!(쌤이 기존에 만드신 CSS 적용)
				if (message.code == '3') {
					print(window.name, message.content, 'me', 'msg', message.regdate);
				} else if (message.code == '4') {
					printEmoticon(window.name, message.content, 'me', 'msg', message.regdate);
				}
				
			}
			
		});
		
		function scrollList() {
			$('#list').scrollTop($('#list').outerHeight() + 300);
			//큰 값을 주면 움직이긴 하나, 대화의 길이가 길어지면 해당 값 이후로는 스크롤바가 움직이지 않으므로 절대값이 아닌 상대값을 준다. > 대화창의 높이를 가져온다.
		}
	
	</script>
