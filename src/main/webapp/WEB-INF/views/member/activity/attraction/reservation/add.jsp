<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	#title+p {
		text-shadow: 0 2px 10px rgba(255, 255, 255, 0.8);
		padding: 5px 20px;
		color: #222222;
		font-size: 17px;
		background-color: rgba(255, 255, 255, 0.6);
		display: inline-block;
		border-radius: 50px;
	}
	
	#pagetitle {
		margin-top: 70px;
	}
	#title {
	  margin-bottom: 20px;
	}
	
	.munti-content-container {
	  display: flex;
	  flex-wrap: wrap;
	  margin: 30px 50px 0 50px;
	  padding: 0 !important;
	}
	
	.item {
	  position: relative;
	  width: 25.5%;
	  aspect-ratio: 0.75;
	  padding: 0;
	  box-sizing: border-box;
	  min-width: 270px;
	  border: 1px solid #E1E1E1;
	  margin: 10px 45px 50px 45px;
	  border-radius: 10px;
	  transition: all 0.3s;
	}
	
	.item:hover {
	  cursor: pointer;
	  box-shadow: 12px 12px 17px rgba(0, 0, 0, 0.20);
	}
	
	.item>div:nth-child(1) {
	  height: 70%;
	  background-color: transparent;
	  background-size: cover;
	  background-position: center;
	  background-repeat: no-repeat;
	  border-radius: 10px 10px 0 0;
	}
	
	.item>div:nth-child(2) {
	  height: 30%;
	  display: flex;
	  flex-direction: column;
	  padding: 20px;
	  font-size: 1.3rem;
	  font-weight: bold;
	  background: transparent;
	  border-radius: 0 0 10px 10px;
	}
	.hidden-div {
	      display: none;
	      color: white;
	      position: absolute;
	      top: 0;
	      left: 0;
	        width: 100%;
	        height: 70%;
	        padding: 20px;
	        background-color: black;
	        opacity: 0.65; /* 투명도 조절 */
	        border-radius: 10px 10px 0 0;
	        z-index: 1; /* 다른 요소들보다 위에 위치하도록 설정 */
	}
	
	/* 제목 영역 CSS */
	section:nth-of-type(2) {
		padding-top: 20px;
	}
	
	h1 {
		text-align: center;
	}
	
	/* 예약 영역 CSS */
	.reservation-container {
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	
	.reservation-box {
		display: flex;
		align-items: center;
		margin: 20px;
	}
	
	.reservation-box > div:first-child {
		width: 100px;
		vertical-align: center;
	}
	
	.reservation-box > div:last-child {
		width: 500px;
	}
	
	.btn-container {
		/* display: flex;
		flex-direction: column;
		align-items: center; */
	}
	
	.btn-container > div {
		/* display: flex;
		justify-content: center; */
	}
	
	.time-btn {
		margin: 5px 7px;
		border: 0;
		border-radius: 5px;
		padding: 10px;
		font-weight: bold;
	}
	
	input[name="capacity"] {
		width: 150px;
	}
	
	.check-available-status {
		display: inline-block;
		padding: 0 10px;
	}
	
	/* 버튼 영역 CSS */
	#reservation-content {
		display: flex;
		justify-content: center;
	}
	
</style>
<!-- ======= Stats Counter Section ======= -->
<section id="stats-counter" class="stats-counter">
  <div id="pagetitle" class="container" data-aos="zoom-out">
    <div class="gy-4" style="justify-content: center; width: 100%;">

      <div class="col-lg-3 col-md-6" style="width: 100%;">
        <div class="stats-item text-center w-100 h-100">
          <div id="title" style="font-size: 48px;
          display: block;
          color: #fff;
          font-weight: 700;">어트랙션 예약</div>
          <p>어트랙션의 빠른 탑승을 예약해보세요!</p>
        </div>
      </div>
    </div>
  </div>
</section>
<!-- End Stats Counter Section -->

<!-- ======= Menu Section ======= -->
<section id="menu" class="menu">
	<div class="container" data-aos="fade-up">
		<div class="tab-content" data-aos="fade-up" data-aos-delay="300">
		
			<!-- 제목 영역 -->
			<h1>${dto.name}</h1>
		
			<!-- 예약 영역 -->
			<form method="POST" action="/dd/member/activity/attraction/reservation/addok.do">
				<div class="reservation-container">
					<div class="reservation-box">
						<div class="label">예약 시간</div>
						<div class="btn-container">
							<div>
								<c:forEach var="i" begin="10" end="15">
									<button type="button" class="time-btn" value="${i-9}" >${i}:00</button>
								</c:forEach>
							</div>
							<div>
								<c:forEach var="i" begin="16" end="21">
									<button type="button" class="time-btn" value="${i-9}">${i}:00</button>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="reservation-box">
						<div class="label">예약 인원</div>
						<div>
							<input type="number" name="capacity" min="1" max="3" placeholder="인원(숫자)" required>
							<div class="check-available-status"></div>
						</div>
					</div>
				</div>
				
				<!-- 어트랙션 seq -->
				<input type="hidden" name="attraction_seq" value="${dto.attraction_seq}">

				<!-- 어트랙션 예약 seq -->
				<input type="hidden" name="attraction_book_seq">
				
				<!-- 토큰 -->
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				
			</form>
			
			<!-- 버튼 영역 -->
			<div id="reservation-content">
				<div class="btn">
					<button type="button" onclick="submit()"><i class="fa-solid fa-calendar-check"></i> 예약</button>
					<button type="button" onclick="location.href= '/dd/user/activity/attraction/detail.do?seq=' + ${dto.attraction_seq};"><i class="fa-solid fa-circle-arrow-left"></i> 취소</button>
				</div>
			</div>
			
			
		</div>
	</div>
</section><!-- End Menu Section -->

<!-- Attraction Reservation add -->
<script>
	
	/* 화면 로딩 시, 현재 시간 확인 하여 이전 시간은 'disabled' 처리 */
	let currentDate = new Date();
	
	/* 필수 항목이 반드시 입력되어야만 submit 클릭 시 넘어가도록 */
	function submit() {
		
		let countTime = $('[data-type="y"]').length;
		console.log('y: ' + countTime);
		console.log('n: ' + $('[data-type="n"]').length);
		
		if (!$('input[name="capacity"]').val().trim()
				|| countTime != 1) {
			
				alert('필수 항목을 입력해주세요.');
		} else {
			$('form').submit();
		}
	}
	
	/* 예약 시간 선택 시, 예약 가능 인원 출력(ajax) */
	// CSRF token
    var csrfHeaderName = "${_csrf.headerName}";
    var csrfTokenValue = "${_csrf.token}";
	
	$('.time-btn').click(function() {
		
		//선택한 버튼만 CSS 변경 + 나머지 초기화
		$('.time-btn').css('background-color', '#F0F0F0');
		$('.time-btn').data('type', 'n');
		$(this).css('background-color', 'gold');
		$(this).data('type', 'y');
		console.log($(this).data('type'));
		
		//선택한 버튼의 값 전송할 태그의 value로 넣기
		$('input[name="attraction_book_seq"]').val($(this).val());
		console.log($('input[name="attraction_book_seq"]').val());
		
		let obj = {
				attraction_book_seq: $('input[name="attraction_book_seq"]').val(),
				attraction_seq: $('input[name="attraction_seq"]').val()
		};
		
		$.ajax({
			type:'POST',
			url: '/dd/activity/attraction/reservation',
			headers: {'content-Type': 'application/json'},
			data: JSON.stringify(obj),
			dataType: 'json',
			success: function(result) {
				$('.check-available-status').html('');
				$('.check-available-status').append('<i class="bi bi-person-check-fill"></i> ');
				$('.check-available-status').append(' 예약 가능 인원: ' + result + '명');
			},
			beforeSend: function(xhr) {
            	xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
            },
			error: function(a,b,c) {
				console.log(a,b,c);
			}
		});
		
	});


</script>