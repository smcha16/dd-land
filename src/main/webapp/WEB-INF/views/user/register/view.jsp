<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>DD : 회원가입</title>
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

	<form method='POST' action="/dd/user/register/view.do">
		<!-- wrapper -->
		<div id="wrapper">

			<!-- content-->
			<div id="content">

				<!-- ID -->
				<div>
					<h3 class="join_title">
						<label for="id">아이디(이메일)</label>
					</h3>
					<span class="box int_id"> <input type="text" name="email"
						id="id" class="int" maxlength="20">
					</span> <span class="error_next_box" id="emailErrorBox"
						style="color: red;"></span>
				</div>


				<!-- PW1 -->
				<div>
					<h3 class="join_title">
						<label for="pswd1">비밀번호</label>
					</h3>
					<span class="box int_pass"> <input type="text" name="pw"
						id="pswd1" class="int" maxlength="20"> <span id="alertTxt">사용불가</span>
						<img src="/dd/resources/files/dd/m_icon_pass.png" id="pswd1_img1"
						class="pswdImg">
					</span> <span class="error_next_box"></span>
				</div>

				<!-- PW2 -->
				<div>
					<h3 class="join_title">
						<label for="pswd2">비밀번호 재확인</label>
					</h3>
					<span class="box int_pass_check"> <input type="text"
						id="pswd2" class="int" maxlength="20"> <img
						src="/dd/resources/files/dd/m_icon_check_disable.png"
						id="pswd2_img1" class="pswdImg">
					</span> <span class="error_next_box"></span>
				</div>

				<!-- NAME -->
				<div>
					<h3 class="join_title">
						<label for="name">이름</label>
					</h3>
					<span class="box int_name"> <input type="text" name="name"
						id="name" class="int" maxlength="20">
					</span> <span class="error_next_box"></span>
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

				<!-- 주소 -->
				<div>
					<h3 class="join_title">
						<label for="post-code">주소<span class="optional"></span></label>
					</h3>
					<div class="address">
						<input type="text" name="post-code" id="post-code"
							class="int half-flat" placeholder="우편번호">
						<button type="button" class="button check"
							onclick="execDaumPostcode()">우편번호 검색</button>
					</div>
					<span class="error_next_box">주소를 다시 확인해주세요.</span>
				</div>

				<div>
					<div class="address">
						<input type="text" name="address-basis" id="address-basis"
							class="int middle-flat" placeholder="기본주소">
					</div>
				</div>

				<div>
					<div class="address">
						<input type="text" name="address-detail" id="address-detail"
							class="int middle-flat" placeholder="상세주소">
					</div>
				</div>






				<!-- MOBILE -->
				<div>
					<h3 class="join_title">
						<label for="phoneNo">휴대전화</label>
					</h3>
					<span class="box int_mobile"> <input type="tel" id="mobile"
						name="tel" class="int" maxlength="16" placeholder="전화번호 입력">
					</span> <span class="error_next_box"></span>
				</div>


				<!-- JOIN BTN-->
				<div class="btn_area">
					<button type="button" id="btnJoin" onclick="submit();">
						<span>가입하기</span>
					</button>
				</div>



			</div>
			<!-- content-->

		</div>
		<!-- wrapper -->
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
	</form>

</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
	function submit() {
		$('form').submit();
	}

	/*변수 선언*/

	var id = document.querySelector('#id');

	var pw1 = document.querySelector('#pswd1');
	var pwMsg = document.querySelector('#alertTxt');
	var pwImg1 = document.querySelector('#pswd1_img1');

	var pw2 = document.querySelector('#pswd2');
	var pwImg2 = document.querySelector('#pswd2_img1');
	var pwMsgArea = document.querySelector('.int_pass');

	var userName = document.querySelector('#name');

	var yy = document.querySelector('#yy');
	var mm = document.querySelector('#mm');
	var dd = document.querySelector('#dd');

	var gender = document.querySelector('#gender');

	var email = document.querySelector('#email');

	var mobile = document.querySelector('#mobile');

	var error = document.querySelectorAll('.error_next_box');

	/*이벤트 핸들러 연결*/

	id.addEventListener("focusout", checkId);
	pw1.addEventListener("focusout", checkPw);
	pw2.addEventListener("focusout", comparePw);
	userName.addEventListener("focusout", checkName);
	yy.addEventListener("focusout", isBirthCompleted);
	mm.addEventListener("focusout", isBirthCompleted);
	dd.addEventListener("focusout", isBirthCompleted);
	gender.addEventListener("focusout", function() {
		if (gender.value === "성별") {
			error[5].style.display = "block";
		} else {
			error[5].style.display = "none";
		}
	})
	email.addEventListener("focusout", isEmailCorrect);
	mobile.addEventListener("focusout", checkPhoneNum);

	/*콜백 함수*/

	function checkId() {
		var idPattern = /[a-zA-Z0-9_-]{5,20}/;
		if (id.value === "") {
			error[0].innerHTML = "필수 정보입니다.";
			error[0].style.display = "block";
		} else if (!idPattern.test(id.value)) {
			error[0].innerHTML = "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
			error[0].style.display = "block";
		} else {
			error[0].innerHTML = "멋진 아이디네요!";
			error[0].style.color = "#08A600";
			error[0].style.display = "block";
		}
	}

	function checkPw() {
		var pwPattern = /[a-zA-Z0-9~!@#$%^&*()_+|<>?:{}]{8,16}/;
		if (pw1.value === "") {
			error[1].innerHTML = "필수 정보입니다.";
			error[1].style.display = "block";
		} else if (!pwPattern.test(pw1.value)) {
			error[1].innerHTML = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";
			pwMsg.innerHTML = "사용불가";
			pwMsgArea.style.paddingRight = "93px";
			error[1].style.display = "block";

			pwMsg.style.display = "block";
			pwImg1.src = "/dd/resources/files/dd/m_icon_not_use.png";
		} else {
			error[1].style.display = "none";
			pwMsg.innerHTML = "안전";
			pwMsg.style.display = "block";
			pwMsg.style.color = "#03c75a";
			pwImg1.src = "/dd/resources/files/dd/m_icon_safe.png";
		}
	}

	function comparePw() {
		if (pw2.value === pw1.value && pw2.value != "") {
			pwImg2.src = "/dd/resources/files/dd/m_icon_check_enable.png";
			error[2].style.display = "none";
		} else if (pw2.value !== pw1.value) {
			pwImg2.src = "/dd/resources/files/dd/m_icon_check_disable.png";
			error[2].innerHTML = "비밀번호가 일치하지 않습니다.";
			error[2].style.display = "block";
		}

		if (pw2.value === "") {
			error[2].innerHTML = "필수 정보입니다.";
			error[2].style.display = "block";
		}
	}

	function checkName() {
		var namePattern = /[a-zA-Z가-힣]/;
		if (userName.value === "") {
			error[3].innerHTML = "필수 정보입니다.";
			error[3].style.display = "block";
		} else if (!namePattern.test(userName.value)
				|| userName.value.indexOf(" ") > -1) {
			error[3].innerHTML = "한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가)";
			error[3].style.display = "block";
		} else {
			error[3].style.display = "none";
		}
	}

	function isBirthCompleted() {
		var yearPattern = /[0-9]{4}/;

		if (!yearPattern.test(yy.value)) {
			error[4].innerHTML = "태어난 년도 4자리를 정확하게 입력하세요.";
			error[4].style.display = "block";
		} else {
			isMonthSelected();
		}

		function isMonthSelected() {
			if (mm.value === "월") {
				error[4].innerHTML = "태어난 월을 선택하세요.";
			} else {
				isDateCompleted();
			}
		}

		function isDateCompleted() {
			if (dd.value === "") {
				error[4].innerHTML = "태어난 일(날짜) 2자리를 정확하게 입력하세요.";
			} else {
				isBirthRight();
			}
		}
	}

	function isBirthRight() {
		var datePattern = /\d{1,2}/;
		if (!datePattern.test(dd.value) || Number(dd.value) < 1
				|| Number(dd.value) > 31) {
			error[4].innerHTML = "생년월일을 다시 확인해주세요.";
		} else {
			checkAge();
		}
	}

	function checkAge() {
		if (Number(yy.value) < 1920) {
			error[4].innerHTML = "정말이세요?";
			error[4].style.display = "block";
		} else if (Number(yy.value) > 2020) {
			error[4].innerHTML = "미래에서 오셨군요. ^^";
			error[4].style.display = "block";
		} else if (Number(yy.value) > 2005) {
			error[4].innerHTML = "만 14세 미만의 어린이는 보호자 동의가 필요합니다.";
			error[4].style.display = "block";
		} else {
			error[4].style.display = "none";
		}
	}

	function isEmailCorrect() {
		var emailPattern = /[a-z0-9]{2,}@[a-z0-9-]{2,}\.[a-z0-9]{2,}/;

		if (email.value === "") {
			error[6].style.display = "none";
		} else if (!emailPattern.test(email.value)) {
			error[6].style.display = "block";
		} else {
			error[6].style.display = "none";
		}

	}

	function checkPhoneNum() {
		var isPhoneNum = /([01]{2})([01679]{1})([0-9]{3,4})([0-9]{4})/;

		if (mobile.value === "") {
			error[7].innerHTML = "필수 정보입니다.";
			error[7].style.display = "block";
		} else if (!isPhoneNum.test(mobile.value)) {
			error[7].innerHTML = "형식에 맞지 않는 번호입니다.";
			error[7].style.display = "block";
		} else {
			error[7].style.display = "none";
		}

	}
</script>

<script
	src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- 주소 입력 폼 -->
<script>
	function execDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				var addr = '';
				var extraAddr = '';

				if (data.userSelectedType === 'R') { // 도로명 주소 선택
					addr = data.roadAddress;
				} else { // 지번 주소 선택
					addr = data.jibunAddress;
				}

				if (data.userSelectedType === 'R') {
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					if (extraAddr !== '') {
						extraAddr = ' (' + extraAddr + ')';
					}
				} else {
					document.getElementById("address-basis").value = '';
				}

				// 우편번호와 주소 정보를 input 박스에 삽입
				document.getElementById('post-code').value = data.zonecode;
				document.getElementById("address-basis").value = addr;
				document.getElementById("address-basis").value += extraAddr;
				document.getElementById("address-detail").focus(); // 상세주소로 포커스 이동
			}
		}).open();
	}
</script>


<!-- 생략... -->

<script>
	$(document)
			.ready(
					function() {
						var idInput = $("#id");
						var errorBox = $(".error_next_box");

						idInput
								.on(
										"focusout",
										function() {
											var id = idInput.val();
											var idPattern = /[a-zA-Z0-9_-]{5,20}/;

											if (id === "") {
												displayError("emailErrorBox",
														"필수 정보입니다.");
											} else if (!idPattern.test(id)) {
												displayError("emailErrorBox",
														"5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.");
											} else {
												displaySuccess("emailErrorBox",
														"멋진 아이디네요!");
												// 이메일 형식 유효성 검사
												var email = idInput.val();
												if (!isValidEmail(email)) {
													displayError(
															"emailErrorBox",
															"올바른 이메일 형식이 아닙니다.");
													return;
												}
												// 중복 검사 수행
												$
														.ajax({
															type : "GET",
															url : "/dd/user/register/checkEmailDuplicate",
															data : {
																email : email
															},
															success : function(
																	response) {
																if (response === "DUPLICATED") {
																	displayError(
																			"emailErrorBox",
																			"이미 사용 중인 이메일입니다.");
																} else {
																	displaySuccess(
																			"emailErrorBox",
																			"사용 가능한 이메일입니다.");
																}
															},
															error : function() {
																console
																		.error("Error during email duplicate check.");
															}
														});
											}
										});

						// 이메일 형식 유효성 검사 함수 (필요 시 추가)
						function isValidEmail(email) {
							var emailPattern = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}/;
							return emailPattern.test(email);
						}

						// 에러 메시지를 표시하는 함수
						function displayError(errorBoxId, message) {
							$("#" + errorBoxId).text(message).css("color",
									"red").show();
						}

						// 성공 메시지를 표시하는 함수
						function displaySuccess(errorBoxId, message) {
							$("#" + errorBoxId).text(message).css("color",
									"#08A600").show();
						}
					});
</script>


<!-- 생략... -->

</html>