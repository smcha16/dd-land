<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<style>
#pagetitle {
	margin-top: 70px;
}

#title {
	font-size: 48px;
	display: block;
	color: #fff;
	font-weight: 700;
	margin-bottom: 20px;
}

#content {
	margin-top: 20px;
}

.containers {
	width: 55%;
	margin: 0 auto 50px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

form>#condition {
	box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.4);
	border-radius: 15px;
	text-align: center;
	color: #fff;
	background-color: #ce1212;
	opacity: 0.85;
	height: 70px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	margin-bottom: 15px;
}

.form-group {
	margin-bottom: 20px;
}

.form-group label {
	display: block;
	font-weight: bold;
	margin-bottom: 5px;
	font-size: 1.1rem;
}

.form-group input[type="text"], .form-group input[type="password"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 3px;
}

.form-group input[type="password"] {
	margin-bottom: 10px;
}

.form-group input[type="tel"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 3px;
}

.address-group {
	display: flex;
	flex-wrap: wrap;
	gap: 10px;
}

.address-group input[type="text"] {
	flex: 1;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 3px;
}

.btn-container {
	text-align: center;
	margin-top: 20px;
}

.btn {
	color: #fff;
	background: #ce1212;
	border: none;
	font-size: 14px;
	padding: 8px 20px;
	margin-left: 30px;
	border-radius: 15px;
	font-weight: bold;
}

.btn.cancel {
	background-color: #ccc;
}

#personnel {
	display: flex;
	flex-wrap: wrap;
}

#personnel>div {
	flex: 0 0 calc(33.33% - 20px);
	align-text: center;
	margin: 10px;
	justify-content: space-around;
	align-items: center;
}

ul {
	list-style-type: none;
	margin-block-start: 1em;
	margin-block-end: 1em;
	margin-inline-start: 0px;
	margin-inline-end: 0px;
	padding-inline-start: 40px;
	font-size: 14px;
}

.line {
	display: inline-block;
	width: 80%;
	position: relative;
	vertical-align: top;
}

.personnel ul li .txt_wrap {
	float: left;
	padding-bottom: 0;
	text-align: left;
	width: 53%;
	display: block;
}

.personnel ul li .txt_wrap .tit {
	margin: 0;
	padding: 0;
	border: 0;
	color: #505050;
}

p {
	display: block;
	margin-block-start: 1em;
	margin-block-end: 1em;
	margin-inline-start: 0px;
	margin-inline-end: 0px;
	margin-top: 0;
	margin-bottom: 1rem;
}

.personnel ul li .count_wrap {
	float: right;
	margin-top: 3px;
	/* width: 120px; */
}

table {
	height: 46px;
	margin: auto;
	font-size: 20px;
}

table td {
	width: 250px;
	margin: auto;
}

#ticket-type {
	height: 46px;
	text-align: center;
	font-size: 22px;
}

#ticket-type input {
	margin: 0px 10px;
}

#ticket-type label {
	display: inline-block;
}

.tab_red {
	position: relative;
	background-color: #3e3e4d !important;
	z-index: 1;
}

.tab {
	overflow: hidden;
	width: 100%;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-ms-box-sizing: border-box;
	background: url(/images/common/bg_tab.gif) repeat-x;
	box-shadow: 0 1px 5px rgba(0, 0, 0, .4);
}

.tab li {
	float: left;
}

.tab .two {
	width: 48%;
}

.tab_red .on {
	background-color: #2b72c9;
}

.tab li a {
	display: block;
	width: 86%;
	height: 48px;
	margin: 0 auto;
	line-height: 48px;
	color: #777;
	text-align: center;
	box-sizing: border-box;
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-ms-box-sizing: border-box;
	font-size: 20px;
}

.tab_red li a {
	padding: 0;
	color: #787883;
}

.tab_red .on a {
	color: white;
}

#tbl01, #tbl02 {
	text-align: center;
	justify-content: space-around;
	align-items: center;
}

.res_benefit {
	text-align: center;
	justify-content: space-around;
	align-items: center;
}

.res_benefit li.line {
	margin-bottom: 20px;
	border-bottom: 1px solid #e5e5e5;
}

.res_benefit li .tit {
	float: left;
	width: 100%;
	margin-bottom: 20px;
}

.res_benefit li .tit .tit_lt {
	position: relative;
	float: left;
	width: 60%;
	height: 51px;
	line-height: 51px;
	padding-left: 2%;
	background: #f7f7f7;
	border: 1px solid #e5e5e5;
	border-right: 0 none;
	box-sizing: border-box;
	font-size: 18px;
	font-weight: 400;
}

.res_benefit li .tit .tit_rt {
	float: left;
	width: 38%;
	height: 51px;
	line-height: 51px;
	padding-right: 2%;
	background: #eee;
	font-size: 18px;
	text-align: right;
	font-weight: 400;
}

.res_benefit li .cont {
	float: left;
	width: 60%;
	margin-bottom: 20px;
	border-right: 1px solid #e5e5e5;
	box-sizing: border-box;
}

.res_benefit li .none {
	float: left;
	width: 100%;
	margin-top: 10px;
	margin-bottom: 20px;
	font-size: 20px;
	box-sizing: border-box;
}

.res_benefit li .it {
	float: left;
	width: 36%;
	padding: 0 2%;
	margin-bottom: 6px;
}

.res_benefit li .cont .img {
	float: left;
	width: 35%;
}

.res_benefit li .cont .img .img_box {
	position: relative;
	height: 196px;
	border: 1px solid #e5e5e5;
	box-sizing: border-box;
}

.res_benefit li .cont .txt {
	float: left;
	width: 55%;
	padding: 0 5%;
	font-size: 17px;
}

.option {
	border-left: 1px solid #AAA;
	border-right: 1px solid #AAA;
	border-bottom: 1px solid #AAA;
	box-shadow: 0 1px 5px rgba(0, 0, 0, .4);
	padding-top: 5px;
}

.option p {
	margin-left: 15px;
}

.benefit-button {
	background-color: #007bcc;
	color: #fff;
	border: none;
	border-radius: 5px;
	padding: 10px 20px;
	font-size: 16px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	cursor: pointer;
	box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
}

.benefit-button:disabled {
	background-color: #003456;
}

.radio {
	opacity: 0;
}

.radio > label {
	padding: 5px 10px;
}

input[type=radio]:checked + label {
	color: red;
	font-size: 1.25rem;
}

/* list photo 변경 */
.stats-counter {
	background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
		url("/dd/resources/files/reservation/main-reservation.jpg") center center;
	background-size: cover;
	padding: 100px 0;
	background-attachment: fixed;
}
</style>

<section id="stats-counter" class="stats-counter">
	<div id="pagetitle" class="container" data-aos="zoom-out">
		<div class="gy-4" style="justify-content: center; width: 100%;">
			<div class="col-lg-3 col-md-6" style="width: 100%;">
				<div class="stats-item text-center w-100 h-100">
					<div id="title">Personal Reservation</div>
				</div>
			</div>
		</div>
	</div>
</section>

<div id="content">
	<div class="containers">
		<form action="/dd/member/ticket/personal-reservation/check.do"
			method="post">
			<div class="form-group" style="margin-bottom: 40px;">
				<label for="date">방문일</label> <input type="date" id="date"
					name="visit_date" required class="middle-flat">
			</div>
			<div class="form-group"
				style="margin-bottom: 40px; box-shadow: 0 1px 1px rgba(0, 0, 0, .2);">
				<label for="ticket-type">티켓 종류</label>
				<div id="ticket-type">
					<input type="radio" id="1Day" class="radio" name="ticket_type" value="1Day"
						checked><label for="1Day">1Day</label> <input type="radio" class="radio"
						id="After4" name="ticket_type" value="After4"
						style="margin-left: 100px;"><label for="After4">After4</label>
				</div>
			</div>
			<div class="form-group"
				style="margin-bottom: 40px; box-shadow: 0 1px 1px rgba(0, 0, 0, .2);">
				<label for="personnel">인원</label>
				<table>
					<tr>
						<td>성인 <select name="adult" class="personnel">
								<option value="0">0</option>
								<option value="1" selected>1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
						</select> 명
						</td>
						<td>청소년 <select name="teenager" class="personnel">
								<option value="0">0</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
								<option value="6">6</option>
						</select> 명
						</td>
					</tr>
				</table>
			</div>
			<div class="form-group">
				<label for="benefit">혜택 선택</label>
				<ul class="tab tab_red">
					<li id="sel01" class="two on"><a href="#tab01" id="selTab01">일반
							혜택</a></li>
					<li id="sel02" class="two"><a href="#tab02" id="selTab02">카드/통신사</a></li>
				</ul>
				<div id="tab01" style="display: none;">
					<ul class="res_benefit">
						<c:forEach items="${list}" var="dto">
							<li class="line">
								<div class="tit">
									<p class="tit_lt">${dto.name}</p>
									<p class="tit_rt">${dto.discount_rate}%</p>
								</div>
								<div class="cont">
									<div class="img">
										<p class="img_box">
											<img src="/dd/resources/files/benefit/${dto.img}"
												style="width: 100%; height: 100%;">
										</p>
									</div>
									<div class="txt">
										${dto.start_date}<br>~<br>${dto.end_date}
									</div>
								</div>
								<div class="it">
									<button type="button" class="benefit-button"
										onclick="changeBenefit(this, ${dto.benefit_seq}, '${dto.name}', ${dto.discount_rate});">선택</button>
								</div>
							</li>
						</c:forEach>
						<c:if test="${empty list}">
							<li class="line">
								<div class="none">혜택 없음</div>
							</li>
						</c:if>
					</ul>
				</div>
				<div id="tab02" style="display: none;">
					<ul class="res_benefit">
						<c:forEach items="${plist}" var="dto">
							<li class="line">
								<div class="tit">
									<p class="tit_lt">${dto.name}</p>
									<p class="tit_rt">${dto.discount_rate}%</p>
								</div>
								<div class="cont">
									<div class="img">
										<p class="img_box">
											<img src="/dd/resources/files/benefit/${dto.img}"
												style="width: 100%; height: 100%;">
										</p>
									</div>
									<div class="txt">
										${dto.start_date}<br>~<br>${dto.end_date}
									</div>
								</div>
								<div class="it">
									<button type="button" class="benefit-button"
										onclick="changeBenefit(this, ${dto.benefit_seq}, '${dto.name}', ${dto.discount_rate});">선택</button>
								</div>
							</li>
						</c:forEach>
						<c:if test="${empty plist}">
							<li class="line">
								<div class="none">혜택 없음</div>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
			<br>
			<div class="form-group option">
				<label for="ticket-type" style="margin-left: 5px;">이용 안내</label>
				<p>1. 어드벤처 예매페이지 및 모바일APP을 통한 티켓예매</p>
				<p>2. 카카오 알림톡(또는 문자메시지)으로 웹티켓 URL 발송</p>
				<p>3. 웹티켓을 게이트에 제시 후 빠른 입장</p>
			</div>

			<br>
			<div class="form-group option">
				<label for="ticket-type" style="margin-left: 5px;">취소/환불 안내</label>
				<p>온라인 예매 시 선택한 날짜에만 방문 및 이용이 가능하며, 미사용시에 해당날짜가 지나면 자동 취소 됩니다.
					(사용 후에는 취소가 불가능합니다.)</p>
				<ul>
					<li>본인+동반인 티켓 구매 후 동반인 티켓만 사용 시 본인 티켓 취소는 불가합니다.</li>
					<li>본인+동반인 티켓 구매 후 취소하실 경우 동반인 티켓을 먼저 취소하셔야합니다.</li>
					<li>시스템 자동 취소가 될 경우 제휴카드 실적은 은행영업일 기준 2~3일 후에 복구됩니다.</li>
					<li>별도의 취소 수수료는 없으나 구매 후 환불요청 시 각 카드사에 따라 수수료를 차감합니다.</li>
					<li>예매취소를 원하시는 경우 [마이페이지 > 결제내역]에서 취소하실 수 있습니다.</li>
				</ul>
				<p>예매 후에는 반드시 마이티켓을 통하여 예매내역을 확인해 주시기 바랍니다.</p>
			</div>

			<div class="btn-container">
				<button type="submit" class="btn">예매</button>
			</div>
			<input type="hidden" name="benefit_seq" id="benefit_seq" value="0">
			<input type="hidden" name="name" id="benefit_name"> <input
				type="hidden" name="discount_rate" id="discount_rate" value="0">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}"> <input type="hidden" name="user_seq"
				value="<sec:authentication property='principal.dto.user_seq'/>">
		</form>
	</div>

</div>


<script>
		showTab("tab01");
	
		document.getElementById("selTab01").addEventListener("click", function() {
			event.preventDefault();
	      showTab("tab01");
	      
	      document.getElementById("sel01").classList.add("on");
	      document.getElementById("sel02").classList.remove("on");
	    });

	    document.getElementById("selTab02").addEventListener("click", function() {
	    	event.preventDefault();
	      showTab("tab02");
	      
	      document.getElementById("sel02").classList.add("on");
	      document.getElementById("sel01").classList.remove("on");
	    });
	    
	    function showTab(tabId) {
	        // 모든 탭 숨기기
	        document.getElementById("tab01").style.display = "none";
	        document.getElementById("tab02").style.display = "none";

	        // 선택한 탭 보이기
	        document.getElementById(tabId).style.display = "block";
	      }
	    
	    function changeBenefit(button, seq, name, value) {
	    	const benefit_seq = document.getElementById("benefit_seq");
	    	const discount_rate = document.getElementById("discount_rate");
	    	const benefit_name = document.getElementById("benefit_name");
	    	const benefitButtons = document.querySelectorAll(".benefit-button");
	    	
	    	benefit_seq.value = seq;
	    	benefit_name.value = name;
	    	discount_rate.value = value;
	    	
	    	benefitButtons.forEach(btn => {
	            btn.disabled = false;
	            btn.innerText = "선택";
	        });

	        // 현재 클릭한 버튼만 비활성화
	        button.disabled = true;
	        button.innerText = "적용";
	    }
	    
	    const inputs = document.querySelectorAll('input[required]');
	    
	 // 모든 입력 요소에 대한 이벤트 리스너를 추가합니다.
	    inputs.forEach(input => {
	        input.addEventListener('input', function() {
	            let allFilled = true;
	            inputs.forEach(requiredInput => {
	                // 어느 하나의 input이 비어있다면 버튼을 비활성화합니다.
	                if (requiredInput.value === '') {
	                    allFilled = false;
	                }
	            });

	            // 모든 input이 채워졌다면 버튼을 활성화합니다.
	            const joinButton = document.getElementById('join');
	            if (allFilled) {
	                joinButton.disabled = false;
	            } else {
	                joinButton.disabled = true;
	            }
	        });
	    });
	</script>