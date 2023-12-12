<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- user > test > mbti > view.jsp -->

<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />

<!-- Slick -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />

<!-- view 2 전용 style tag -->
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

.item img {
	width: 100%;
	height: auto;
	vertical-align: baseline;
}

.item:hover {
	cursor: pointer;
	box-shadow: 12px 12px 17px rgba(0, 0, 0, 0.20);
}

.item>div:nth-child(1) {
	height: 70%;
	background-color: transparent;
	background-size: cover;
	/* background-position: center; */
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
	opacity: 0.65;
	border-radius: 10px 10px 0 0;
	z-index: 1;
}
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

section:nth-of-type(2) {
	background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
		url("/dd/resources/files/activity/ferris-wheel-3656782_1280.jpg")
		center center;
	/* background-color: transparent;
		background-repeat: no-repeat; */
	background-size: cover;
	padding: 0;
}

section:last-of-type {
	padding-top: 30px;
	padding-bottom: 30px;
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

.result-container>.result-item:last-child>.value {
	font-size: 1.1rem;
}

.label {
	font-weight: bold;
	font-size: 1.5rem;
	margin: 20px 10px;
	color: #000;
}

.value {
	margin: 10px 10px;
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
	padding: 20px;
}

div#reservation-btn>button {
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
			<h2>${mdto.mbti}</h2>
		</div>

		<div class="item">
			<img src="/dd/resources/files/test/MBTI/${mdto.mbti_img}"
				alt="출처: #어반브러시 #타미">
			<div>${mdto.mbti}</div>
			<div class="hidden-div">${mdto.result}</div>
		</div>

		<!-- Slick Slider -->
		<div class="image-slider">
			<c:forEach items="${adto.imgList}" var="dto">
				<div>
					<img src="/dd/resources/files/activity/attraction/${adto.img}"
						alt="Image">
				</div>
			</c:forEach>
		</div>
		<!-- End Slick Slider -->
		
		<p class="section-info">${adto.info}</p>
	</div>
</section>
<!-- End Title & Image Section -->

<!-- 목록보기 버튼 -->
<div id="button">
	<button type="button" id="back-button" class="btn btn-primary"
		onclick="location.href='/dd/user/test/mbti/view.do';">
		<i class="bi bi-list"></i>목록
	</button>
</div>


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