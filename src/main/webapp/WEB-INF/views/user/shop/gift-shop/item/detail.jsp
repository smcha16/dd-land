<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="/dd/resources/air-datepicker/dist/css/datepicker.min.css" rel="stylesheet" type="text/css" media="all">
    <!-- Air datepicker css -->
    <script src="/dd/resources/air-datepicker/dist/js/datepicker.js"></script> <!-- Air datepicker js -->
    <script src="/dd/resources/air-datepicker/dist/js/i18n/datepicker.ko.js"></script> <!-- 달력 한글 추가를 위해 커스텀 -->

<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />

<!-- Slick -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />

<!-- view 2 전용 style tag -->
<style>
/* 폰트는 테스트용 임시 */
@font-face {
	font-family: 'SUIT-Regular';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_suit@1.0/SUIT-Regular.woff2')
		format('woff2');
	font-weight: normal;
	font-style: normal;
}

* {
	font-family: 'SUIT-Regular';
}





/* 폰트 테스트 끝 */
body {
	background: #EEE;
}

.section-header h2 {
	font-size: 48px;
	color: #000;
}

.section-info {
	text-align: center;
	margin: 2rem 0;
	font-size: 22px;
}

.section-image {
	display: flex;
	justify-content: center;
	padding-bottom: 30px;
}

.section-image>img {
	border-radius: 15px;
}

section:first-of-type {
	padding-top: 140px;
	padding-bottom: 20px;
}

#background {
	background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
		url("/dd/resources/files/restaurant/back-restaurant.jpg") center
		center;
	background-color: transparent;
	background-repeat: no-repeat;
	background-size: cover;
	padding: 70px 0;
}

.close-container {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.close-item {
	width: 950px;
	border-radius: 5px;
	background-color: #FFFFFF;
	display: flex;
	flex-direction: column;
	align-items: center;
	margin: 30px 30px 0 30px;
}

.close-time>.value {
	margin: 0 auto;
}

.result-container {
	display: flex;
	flex-direction: row;
	justify-content: center;
}

.result-item {
	display: flex;
	flex-direction: column;
	/* justify-content: center; */
	align-items: center;
	border: 0px solid #888;
	width: 276px;
	height: 370px;
	padding: 30px;
	margin: 30px;
	/* background-color: #FFFBD0; */
	background-color: #FFF;
	border-radius: 5px;
}

.label {
	font-weight: bold;
	font-size: 1.5rem;
	margin: 20px 10px;
	color: #000;
}

.value {
	margin: 20px 10px;
	font-size: 1.2rem;
}

.value.location {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.location {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.location .label {
	margin-bottom: 0;
}

.icon {
	width: 60px;
	height: 60px;
	margin: 20px 10px;
}

.image-slider {
	width: 700px;
	height: 350px;
	margin: 0 auto;
}

.image-slider div {
	width: 700px;
	height: 350px;
	overflow: hidden;
	margin: 0 auto;
}

.image-slider img {
	width: 100%;
	max-height: 100%;
	border-radius: 15px;
	object-fit: cover;
}

/* Slick Button Style */
.slick-prev, .slick-next {
	border: 0;
	background: transparent;
	z-index: 100;
	position: absolute;
}

.slick-prev {
	top: 50%;
	left: 20px;
}

.slick-next {
	top: 50%;
	right: 20px;
}

/* 버튼 */
	#button {
		display: flex;
		justify-content: center;
		margin-top: 50px;
		margin-bottom: 50px;
	}
	#back-button {
		background-color: #CE1212;
		border-color: #CE1212;
	}
	#back-button i {
		margin-right: 7px;
	}
	div#reservation-btn {
		display: flex;
		justify-content: center;
		padding: 0 0 50px 0;
	}	
	div#reservation-btn > button {
		padding: 13px 15px;
		background: #b71c1c;
		border: #b71c1c;
		border-radius: 7px;
		color: #FFF;
		font-weight: bold;
		font-size: 17px;
	}
</style>

<!-- ======= Title & Image Section ======= -->
<section>
	<div class="container" data-aos="zoom-out">
		<div class="section-header">
			<h2>${dto.name}</h2>
		</div>
		<!-- Slick Slider -->
		<div class="image-slider">
			<c:forEach items="${list }" var="dto">
				<div>
					<img src="/dd/resources/files/item/${dto.img }" alt="">
				</div>
			</c:forEach>
			<!-- End Slick Slider -->
		</div>
	</div>
</section>
<!-- End Title & Image Section -->


<!-- 상세 정보 -->
<!-- 운휴일정, 운영시간, 탑승인원, 이용정보 -->
<div id="background">
	<div class="result-container">
		<div class="result-item">
			<div class="label">상품 정보</div>
			<div class="value">${dto.info }</div>
		</div>
		<div class="result-item">
		<div class="label">상품 가격</div>
			<div class="value">${dto.price} 원</div>
		</div>
		<div class="result-item">
			<div class="label">상품 개수</div>
			<div class="value">몇개 살래?</div>
			<div>
				<button>장바구니</button>
				<button>바로구매</button>
			</div>
			<div>
				<input type="text" id="datepicker" data-language='ko'>
				<label>Date</label>
				<span></span>
			</div>
			<div class="datepicker-here" data-language='ko'></div>
			<input type="text"
    data-range="true"
    data-multiple-dates-separator=" - "
    data-language="ko"
    class="datepicker-here"/>
		</div>
	</div>
</div>

<!-- 목록보기 버튼 -->
<div id="button">
	<button type="button" id="back-button" class="btn btn-primary" onclick="location.href='/dd/user/shop/gift-shop/item/view.do';"><i class="bi bi-list"></i>목록</button>
</div>

<script>
    $("#datepicker").datepicker({
    	language: 'ko'
    }); 
</script>

<!-- view2 Template 전용 JavaScript -->
<!-- Kakao Map Open API -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c089ee6f3d885cfbe52b2f15d8f3f531"></script>

<!-- Slick Slider -->
<script type="text/javascript"
	src="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

<script>


	/* Slick Slider */
	$('.image-slider').slick({
		variableWidth : true,
		infinite : true,
		autoplay : true,
		autoplaySpeed : 5000,
		pauseOnHover : true,
		arrows : true,
		prevArrow : "<button type='button' class='slick-prev'>&#10094;</button>",
		nextArrow : "<button type='button' class='slick-next'>&#10095;</button>",
		draggable : true
	});
</script>
<!-- 끝 -->