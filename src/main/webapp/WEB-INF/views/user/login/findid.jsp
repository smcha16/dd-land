<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>DD : 아이디찾기</title>
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

	<form:form method='POST' action="/dd/user/login/findid.do" modelAttribute="MemberDTO">
		<!-- wrapper -->
		<div id="wrapper">

			<!-- content-->
			<div id="content">

	


				

				<!-- NAME -->
				<div>
					<h3 class="join_title">
						<label for="name">이름</label>
					</h3>
					<span class="box int_name"> <input type="text" name="name"
						id="name" class="int" maxlength="20">
					</span> 
					<form:errors path="name" cssClass="error-message" />
					<span class="error_next_box"></span>
				</div>

				<!-- BIRTH -->
				<div>
					<h3 class="join_title">
						<label for="yy">생년월일</label>
					</h3>

					<div id="bir_wrap">
						<!-- BIRTH_YY -->
						<div id="bir_yy">
							<span class="box"> <input type="text" name="birth" id="yy"
								class="int" maxlength="4" placeholder="년(4자)">
							</span>
							<form:errors path="birth" cssClass="error-message" />
						</div>

						<!-- BIRTH_MM -->
						<div id="bir_mm">
							<span class="box"> <select id="mm" class="sel" name="mm">
									<option>월</option>
									<option value="01">1</option>
									<option value="02">2</option>
									<option value="03">3</option>
									<option value="04">4</option>
									<option value="05">5</option>
									<option value="06">6</option>
									<option value="07">7</option>
									<option value="08">8</option>
									<option value="09">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
							</select>
							</span>
						</div>

						<!-- BIRTH_DD -->
						<div id="bir_dd">
							<span class="box"> <input type="text" id="dd" class="int"
								name="dd" maxlength="2" placeholder="일">
							</span>
						</div>

					</div>
					<span class="error_next_box"></span>
				</div>

				




				<!-- MOBILE -->
				<div>
					<h3 class="join_title">
						<label for="phoneNo">휴대전화</label>
					</h3>
					<span class="box int_mobile"> <input type="tel" id="mobile"
						name="tel" class="int" maxlength="16" placeholder="전화번호 입력">
					</span> 
					<form:errors path="tel" cssClass="error-message" />
					<span class="error_next_box"></span>
				</div>


				<!-- JOIN BTN-->
				<div class="btn_area">
					<button type="button" id="btnJoin" onclick="submit();">
						<span>아이디 찾기</span>
					</button>
				</div>



			</div>
			<!-- content-->

		</div>
		<!-- wrapper -->
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
	</form:form>

</body>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>


 <script>

function submit() {
	$('form').submit();
}

	

</script>
 
</html>


