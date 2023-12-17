<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>DD : 실패</title>
<link rel="stylesheet" href="new_main.css">
<style>
/* 레이아웃 틀 */
html {
	height: 100%;
}

body {
	margin: 0;
	height: 100%;
	background: #f5f6f7;
	font-family: Dotum, '돋움', Helvetica, sans-serif;
}

#logo {
	font-size: 28px;
	font-weight: 700;
	color: #000;
	margin: 0;
	font-family: var(- -font-secondary);
}

#header {
	padding-top: 62px;
	padding-bottom: 20px;
	text-align: center;
}

#wrapper {
	position: relative;
	height: 100%;
}

#content {
	position: absolute;
	left: 50%;
	transform: translate(-50%);
	width: 460px;
}

/* 입력폼 */
h3 {
	margin: 19px 0 8px;
	font-size: 14px;
	font-weight: 700;
}

.box {
	display: block;
	width: 100%;
	height: 51px;
	border: solid 1px #dadada;
	padding: 10px 14px 10px 14px;
	box-sizing: border-box;
	background: #fff;
	position: relative;
}

.int {
	display: block;
	position: relative;
	width: 100%;
	height: 29px;
	border: none;
	background: #fff;
	font-size: 15px;
}

input {
	font-family: Dotum, '돋움', Helvetica, sans-serif;
}

.box.int_id {
	padding-right: 110px;
}

.box.int_pass {
	padding-right: 40px;
}

.box.int_pass_check {
	padding-right: 40px;
}

.step_url {
	/*@naver.com*/
	position: absolute;
	top: 16px;
	right: 13px;
	font-size: 15px;
	color: #8e8e8e;
}

.pswdImg {
	width: 18px;
	height: 20px;
	display: inline-block;
	position: absolute;
	top: 50%;
	right: 16px;
	margin-top: -10px;
	cursor: pointer;
}

#bir_wrap {
	display: table;
	width: 100%;
}

#bir_yy {
	display: table-cell;
	width: 147px;
}

#bir_mm {
	display: table-cell;
	width: 147px;
	vertical-align: middle;
}

#bir_dd {
	display: table-cell;
	width: 147px;
}

#bir_mm, #bir_dd {
	padding-left: 10px;
}

select {
	width: 100%;
	height: 29px;
	font-size: 15px;
	background: #fff
		url(https://static.nid.naver.com/images/join/pc/sel_arr_2x.gif) 100%
		50% no-repeat;
	background-size: 20px 8px;
	-webkit-appearance: none;
	display: inline-block;
	text-align: start;
	border: none;
	cursor: default;
	font-family: Dotum, '돋움', Helvetica, sans-serif;
}

/* 에러메세지 */
.error_next_box {
	margin-top: 9px;
	font-size: 12px;
	color: red;
	display: none;
}

#alertTxt {
	position: absolute;
	top: 19px;
	right: 38px;
	font-size: 12px;
	color: red;
	display: none;
}

/* 버튼 */
.btn_area {
	margin: 30px 0 91px;
}

#btnJoin {
	width: 100%;
	padding: 21px 0 17px;
	border: 0;
	cursor: pointer;
	color: #fff;
	background-color: #ce1212;
	font-size: 20px;
	font-weight: 400;
	font-family: Dotum, '돋움', Helvetica, sans-serif;
}
button#checkDuplicateBtn {
    margin-left: 10px; /* 버튼을 아이디(이메일) 옆으로 이동 */
}
</style>
</head>
<body>
	<!-- header -->
	<div id="header">
		<a href="/dd/index.do" target="_self" title="DD Studio"><img
			src="/dd/resources/files/dd/DD.png" id="logo"></a>
	</div>

		<!-- wrapper -->
		<div id="wrapper">

			<!-- content-->
			<div id="content">

	

		<div class="text-center" style="display: flex; align-items: center; justify-content: center;">
  <svg xmlns="http://www.w3.org/2000/svg" width="70" height="70" fill="#ce1212" class="bi bi-exclamation-circle-fill" viewBox="0 0 16 16">
    <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8 4a.905.905 0 0 0-.9.995l.35 3.507a.552.552 0 0 0 1.1 0l.35-3.507A.905.905 0 0 0 8 4zm.002 6a1 1 0 1 0 0 2 1 1 0 0 0 0-2z"/>
  </svg>
  
</div>
<p style="display: flex; align-items: center; justify-content: center;"> 존재하지 않는 회원입니다.</p>
	


				

				<!-- JOIN BTN-->
				<div class="btn_area">
					<button type="button" id="btnJoin" >
						<span>확인</span>
					</button>
				</div>



			</div>
			<!-- content-->

		</div>
		<!-- wrapper -->
		

</body>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>


 <script>
 
 document.getElementById("btnJoin").onclick = function() {
     // 페이지 이동을 처리하는 코드
     window.location.href = "/dd/index.do";
 };

function submit() {
	$('form').submit();
}

	

</script>
 
</html>


