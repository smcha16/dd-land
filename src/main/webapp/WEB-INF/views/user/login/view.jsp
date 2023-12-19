<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- list (1) Template -->
<!-- user > test > worldcup > view.jsp -->
<style>
body {
	background-color: #f5f6f7;
}

select {
	width: 85px;
	height: 25px;
	outline: none;
	background: #f5f6f7;
	border: 1px solid #999;
}

a {
	color: black;
	text-decoration: none;
}

.main {
	text-align: center;
	margin-top: 20px;
}

input {
	cursor: pointer
}

/*언어설정*/
.select-lang {
	text-align: right;
	padding-right: 10px;
}
/*네이버 로고설정*/
.image {
	height: 32px;
	padding: 20px;
}
/*input 아이디박스*/
.login-id-wrap {
	margin: 0px 10px 8px 10px;
	padding: 10px;
	border: solid 1px #dadada;
	background: #fff;
}
/*input 아이디 form*/
#input-id {
	border: none;
	outline: none;
	width: 100%;
}
/*input 패스워드박스*/
.login-pw-wrap {
	margin: 0px 10px 8px 10px;
	padding: 10px;
	border: solid 1px #dadada;
	background: #fff;
}
/*input 패스워드 form*/
#input-pw {
	border: none;
	outline: none;
	width: 100%;
}
/* 로그인버튼박스 */
.login-btn-wrap {
	width: 440px; /* 가로 크기 440px로 설정 */
	height: 54px; /* 세로 크기 54px로 설정 */
	margin: 0 auto 8px; /* 가운데 정렬을 위해 margin 수정 */
	border: solid 1px rgba(0, 0, 0, .1);
	background-color: #ce1212;
	color: #ce1212;
	cursor: pointer;
	text-align: center;
}

/* 로그인버튼 */
#login-btn {
	width: 100%;
	height: 100%; /* 부모 요소인 .login-btn-wrap의 높이에 맞게 설정 */
	background-color: #ce1212;
	border: none;
	color: #fff;
	font-size: 18px;
	outline: none;
	cursor: pointer;
}

/*로그인 아래 박스*/
.under-login {
	height: 50px;
	border-bottom: 1px solid gainsboro;
	margin: 0px 10px 35px 10px;
}
/*로그인상태유지*/
.stay-check {
	margin-left: 7px;
	float: left;
}
/*로그인상태유지 체크박스*/
.stay-check input[type="checkbox"] {
	/*기존 체크박스 숨기기*/
	position: absolute;
	width: 0px;
	position: absolute;
}

.stay-check input[type="checkbox"]+label {
	display: inline-block;
	position: relative;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

.stay-check input[type="checkbox"]+label::before {
	content: ' ';
	display: inline-block;
	width: 22px;
	height: 22px;
	line-height: 18px;
	margin: -2px 8px 0 0;
	text-align: center;
	vertical-align: middle;
	background: #fafafa;
	border: 1px solid #cacece;
	border-radius: 50%;
	box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px
		rgba(0, 0, 0, 0.05);
}

.stay-check input[type="checkbox"]:checked+label::before {
	content: '\2713';
	color: white;
	text-shadow: 1px 1px white;
	background: #ce1212;
	border-color: #03c75a;
	box-shadow: 0px 1px 2px rgba(0, 0, 0, 0.05), inset 0px -15px 10px -12px
		rgba(0, 0, 0, 0.05);
}
/*IP보안 표시X*/
#ip-check {
	display: none;
}

/*간편한 로그인 텍스트*/
.easy-login {
	font-size: 20px;
	font-weight: 500;
}

/*찾기 및 회원가입*/
.find-signup-wrap {
	height: 100px;
	grid-template-columns: 1fr 1fr 1fr;
}
/*찾기 및 회원가입 글자 희미하게 바꾸기*/
.find-signup-wrap a {
	color: rgb(150, 150, 150);
	text-decoration: none;
	font-weight: 500;
}

.find-signup-wrap span {
	color: rgb(150, 150, 150);
	font-weight: 500;
}
/*아이디 찾기*/
.find-id {
	margin: 3px;
	font-size: 14px;
	border-right: 1px solid gainsboro;
}
/*아이디 찾기-en모드*/
.find-id-en {
	font-size: 14px;
}
/*비밀번호 찾기*/
.find-pw {
	margin: 3px;
	font-size: 14px;
	border-right: 1px solid gainsboro;
}
/*회원가입*/
.sign-up {
	margin: 3px;
	font-size: 14px;
}
/*저작권 표시X*/
footer {
	display: none;
}

/*가로 800px 이상일때*/
@media ( min-width : 800px) {
	.main {
		width: 460px;
		margin: auto;
	}
}

/*회원가입 부분*/
.main-signup {
	text-align: center;
	width: 460px;
	margin: auto;
}

h3 {
	margin: 19px 0px 8px;
	text-align: left;
	font-size: 14px;
	font-family: Dotum, '돋움', Helvetica, sans-serif;
}

.signup-input {
	display: flex;
	/* margin: 0px 10px 8px 10px; */
	padding: 10px;
	border: solid 1px #dadada;
	background: #fff;
	cursor: pointer;
}

.signup-input-c {
	display: flex;
	/* margin: 0px 10px 8px 10px; */
	padding: 10px;
	border: solid 1px #dadada;
	background: #f5f6f7;
	cursor: pointer;
}

.signup-input-c input {
	background: #f5f6f7;
}

#signup-id, #signup-pw, #signup-pww {
	height: 29px;
	border: none;
	outline: none;
	width: 100%;
}

.signup-at {
	color: rgb(150, 150, 150);
	font-size: 15px;
	font-family: Dotum, '돋움', Helvetica, sans-serif;
	margin-top: 8px;
}

.pw-lock {
	/* content: ''; */
	/* display: inline-block; */
	top: 50%;
	right: 13px;
	width: 24px;
	height: 24px;
	margin-top: 5px;
	background-image:
		url(https://static.nid.naver.com/images/ui/join/m_icon_pw_step.png);
	background-size: 125px 75px;
	cursor: pointer;
}

.pww-lock {
	/* content: ''; */
	/* display: inline-block; */
	top: 50%;
	right: 13px;
	width: 24px;
	height: 24px;
	margin-top: 5px;
	background-image:
		url(https://static.nid.naver.com/images/ui/join/m_icon_pw_step.png);
	background-size: 125px 75px;
	cursor: pointer;
}

.signup-input-birth {
	display: block;
	position: relative;
	width: 100%;
	height: 51px;
	border: solid 1px #dadada;
	padding: 10px;
	background: #fff;
	box-sizing: border-box;
}

#signup-birth-yy, #signup-birth-mm, #signup-birth-dd {
	width: 113px;
	height: 29px;
	border: none;
	outline: none;
}

#signup-name, #signup-gender, #signup-email, #signup-country,
	#signup-phone, #signup-cnum {
	width: 100%;
	height: 29px;
	border: none;
	outline: none;
}

#signup-gender {
	background-color: white;
}

#signup-country {
	background-color: white;
}

#signup-birth-mm {
	background-color: white;
}
/*본인 확인 이메일*/
.choice {
	display: flex;
}

.choice span {
	margin-top: 20px;
	color: rgb(150, 150, 150);
	font-size: 13px;
	font-family: Dotum, '돋움', Helvetica, sans-serif;
}
/*회원가입버튼박스*/
.signup-btn-wrap {
	height: 52px;
	line-height: 55px;
	margin: 10px 0px 50px 0px;
	border: solid 1px rgba(0, 0, 0, .1);
	background-color: #03c75a;
	color: #fff;
	cursor: pointer;
}
/*회원가입버튼*/
#signup-btn {
	width: 100px;
	background-color: #03c75a;
	border: none;
	color: #fff;
	font-size: 18px;
	outline: none;
	cursor: pointer;
}
/*인증번호버튼박스*/
.cnum-btn-wrap {
	height: 52px;
	line-height: 55px;
	margin: 10px 0px 0px 10px;
	border: solid 1px rgba(0, 0, 0, .1);
	background-color: #03c75a;
	color: #fff;
	cursor: pointer;
}
/*인증번호버튼*/
#cnum-btn {
	width: 115px;
	background-color: #03c75a;
	border: none;
	color: #fff;
	font-size: 15px;
	outline: none;
	cursor: pointer;
}
</style>

<!-- 자동 로그인 버튼 -->
<style>
.form-buttons {
	display: flex !important;
	justify-content: space-around;
	align-items: center;
	margin-top: 20px;
}

.login.button {
	display: inline-block;
	padding: 10px 20px;
	font-size: 16px;
	font-weight: bold;
	text-align: center;
	text-decoration: none;
	cursor: pointer;
	border: none;
	border-radius: 5px;
	transition: background-color 0.3s, color 0.3s;
}

.login.button:hover {
	background-color: #222;
	color: #fff;
}

.login.button+.login.button {
	margin-left: 10px;
}

.login.button.admin {
	background-color: #ff6347;
	color: #fff;
}

.login.button.user {
	background-color: #4caf50;
	color: #fff;
}

.error-message {
        color: red;
        font-size: 14px;
    }
</style>



<title>로그인</title>
<div class="main">
	<!--웹페이지 상단-->
	<header>


		<!--NAVER LOGO-->
		<div class="logo" style="margin-bottom: 40px;">
			<a href="/dd/index.do" target="_self" title="네이버 홈페이지"><img
				src="/dd/resources/files/dd/DD.png" class="image"></a>
		</div>
	</header>

	<form:form method='POST' action="/dd/login"
		modelAttribute="loginDTO">

		<!--로그인 부분-->
		<section class="login-wrap">

			<div class="login-id-wrap">
				<input id="input-id" name="username" placeholder="아이디" type="text"></input>
			</div>

			<div class="login-pw-wrap">
				<input id="input-pw" name="password" placeholder="비밀번호" type="password"></input>
			</div>

			<div class="login-btn-wrap">
				<button id="login-btn" onclick="submit();">로그인</button>
			</div>
			<div class="under-login">
				<span class="stay-check"> <input id="stay-checkbox"
					type="checkbox"></input> <label for="stay-checkbox" id="stay-text">로그인 상태 유지</label>
					
					<form:errors path="email" cssClass="error-message" />	
					<form:errors path="pw" cssClass="error-message" />
					
					   <c:if test="${error}">
        <p style="color: red;">아이디 또는 비밀번호가 틀렸습니다.</p>
    </c:if>
				</span>
					
<!-- 
				<span id="ip-check"> <a
					href="https://nid.naver.com/login/ext/help_ip3.html"
					target="_blank" title="네이버 IP 보안">IP 보안</a> <input id="ip-checkbox"
					type="checkbox" style="display: none;" onclick="ipCheck()"></input>
					<label for="ip-checkbox"> <span id="ip-context">OFF</span>
				</label> IP 보안 <input class="ip-checkbox" type="checkbox" name="stay-btn" value="stay">
				</span> -->
			<span>
					
			</span>
					
					
				
			</div>
					


		</section>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
	</form:form>

	<!--class,PW 찾기 및 회원가입 부분-->
	<section class="find-signup-wrap">

		<div id="find-signup-wrap-ko">
			<span class="find-id"> <a
				href="/dd/user/login/findid.do"
				target="_self" title="QR코드 로그인">아이디 찾기</a>
			</span> <span class="find-pw"> <a
				href="/dd/user/login/changepw.do"
				target="_self" title="일회용번호 로그인">비밀번호 찾기</a>
			</span> <span class="sign-up"> <a href="/dd/user/register/view.do"
				target="_self" title="일회용번호 로그인">회원가입</a>
			</span>	
		</div>
<!-- 
		<div id="find-signup-wrap-en" style="display: none;">

			<span class="find-id-en"> <span>Forgot your</span> <a
				href="https://nid.naver.com/user2/help/idInquiry?lang=ko_KR"
				target="_blank" title="QR코드 로그인">Username</a>
			</span> <span class="find-pw"> <span>or</span> <a
				href="https://nid.naver.com/user2/help/pwInquiry?lang=ko_KR"
				target="_blank" title="일회용번호 로그인">Password?</a>
			</span> <span class="sign-up"> <a
				href="https://nid.naver.com/user2/V2Join?m=agree&lang=ko_KR"
				target="_blank" title="일회용번호 로그인">Sign up</a>
			</span>
		</div>
 -->

	</section>



</div>




<div class="form-buttons">

	
	<form method="POST" action="/dd/login">
		<input type="hidden" name="username" value="admin@naver.com">
		<input type="hidden" name="password" value="admin1111!">
		<button type="submit" class="login button admin">관리자</button>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
	</form>

	<form method="POST" action="/dd/login">
		<input type="hidden" name="username" value="park@naver.com"> <input
			type="hidden" name="password" value="park1111!">
		<button type="submit" class="login button user">박나래</button>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
	</form>

	<form method="POST" action="/dd/login">
		<input type="hidden" name="username" value="hwang@kakao.com">
		<input type="hidden" name="password" value="hwang1111!">
		<button type="submit" class="login button user">황주원</button>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
	</form>

	<form method="POST" action="/dd/login">
		<input type="hidden" name="username" value="cha@daum.net"> <input
			type="hidden" name="password" value="cha1111!">
		<button type="submit" class="login button user">차민재</button>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
	</form>

	<form method="POST" action="/dd/login">
		<input type="hidden" name="username" value="lee@kakao.com"> <input
			type="hidden" name="password" value="lee1111!">
		<button type="submit" class="login button user">이정은</button>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
	</form>

	<form method="POST" action="/dd/login">
		<input type="hidden" name="username" value="cha@msn.com"> <input
			type="hidden" name="password" value="cha1111!">
		<button type="submit" class="login button user">차수민</button>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
	</form>

	<form method="POST" action="/dd/login">
		<input type="hidden" name="username" value="lee@naver.com"> <input
			type="hidden" name="password" value="lee1111!">
		<button type="submit" class="login button user">이승원</button>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
	</form>

	<form method="POST" action="/dd/login">
		<input type="hidden" name="username" value="kim@kakao.com"> <input
			type="hidden" name="password" value="kim1111!">
		<button type="submit" class="login button user">김형우도도리</button>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
	</form>
</div>

<script>
	function submit() {
		$('form').submit();
	}

	var itemElements = document.querySelectorAll('.item');
	itemElements.forEach(function(item) {
		item.addEventListener('mouseover', function() {
			// 마우스 오버 시 hidden-div를 보이게 변경
			item.querySelector('.hidden-div').style.display = 'block';
		});

		item.addEventListener('mouseout', function() {
			// 마우스 아웃 시 hidden-div를 다시 숨김
			item.querySelector('.hidden-div').style.display = 'none';
		});
	});

	function chageLangSelect() {
		var langSelect = document.getElementById("id-lang");
		var selectValue = langSelect.options[langSelect.selectedIndex].value;

		if (selectValue == 'ko') {
			document.getElementById("input-id").placeholder = '아이디';
			document.getElementById("input-pw").placeholder = '비밀번호';
			document.getElementById("login-btn").textContent = '로그인';
			document.getElementById("stay-text").textContent = '로그인 상태 유지';
			document.getElementById("ip-check").style.display = '';
			document.getElementById("easy-login-text").textContent = '더욱 간편한 로그인';
			document.getElementById("easy-login-wrap").style.height = '90px';
			document.getElementById("easy-login-wrap-ko").style.display = '';
			document.getElementById("easy-login-wrap-en").style.display = 'none';
			document.getElementById("find-signup-wrap-ko").style.display = '';
			document.getElementById("find-signup-wrap-en").style.display = 'none';

		} else {
			document.getElementById("input-id").placeholder = 'Username';
			document.getElementById("input-pw").placeholder = 'Password';
			document.getElementById("login-btn").textContent = 'Sign in';
			document.getElementById("stay-text").textContent = 'Stay Signed in';
			document.getElementById("ip-check").style.display = 'none';
			document.getElementById("easy-login-text").textContent = 'Easier sign in';
			document.getElementById("easy-login-wrap").style.height = '215px';
			document.getElementById("easy-login-wrap-ko").style.display = 'none';
			document.getElementById("easy-login-wrap-en").style.display = '';
			document.getElementById("find-signup-wrap-ko").style.display = 'none';
			document.getElementById("find-signup-wrap-en").style.display = '';

		}
	}

	function ipCheck() {

		var ipcheck = document.getElementById("ip-context");

		if (ipcheck.textContent == 'OFF') {
			ipcheck.textContent = 'ON'
			ipcheck.style.color = '#03c75a';
		} else {
			ipcheck.textContent = 'OFF'
			ipcheck.style.color = 'rgb(160,160,160)';
		}
	}
</script>