<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  === view ver.2 (상세화면) Template -->

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
	padding: 0;
}

section:last-of-type {
	padding-top: 30px;
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
</style>

<!-- ======= Title & Image Section ======= -->
<section>
	<div class="container" data-aos="zoom-out">
		<div class="section-header">
			<h2>${dto.name}</h2>
		</div>
		<!-- Slick Slider -->
		<div class="image-slider">
			<c:forEach items="${list}" var="dto">
				<div>
					<img src="/dd/resources/files/restaurant/${dto.img}" alt="">
				</div>
			</c:forEach>
			<!-- End Slick Slider -->
		</div>
		<p class="section-info">
			<span style="font-size: 1.5rem; font-weight: 500;">대표메뉴</span> <br>
			${dto.menu }
		</p>
</section>
<!-- End Title & Image Section -->

<!-- 상세 정보 -->
<!-- 운휴일정, 운영시간, 탑승인원, 이용정보 -->
<div id="background">
	<div class="result-container">
		<div class="result-item">
			<img src="/dd/resources/files/icon/dininginfo_icon2.png" alt="Image"
				class="icon" />
			<div class="label">운영시간</div>
			<div class="value">${dto.time}</div>
		</div>
		<%-- <div class="result-item">
			<img src="/dd/resources/files/icon/dininginfo_icon6.png" alt="Image"
				class="icon" />
			<div class="label">수용인원</div>
			<div class="value">${dto.capacity}명</div>
		</div> --%>
		<div class="result-item">
			<img src="/dd/resources/files/icon/dininginfo_icon3.png" alt="Image"
				class="icon" />
			<div class="label">전화번호</div>
			<div class="value">${dto.tel}</div>
		</div>
	</div>
</div>

<!-- 위치 정보 -->
<section>
	<div class="location">
		<div class="label">위치 정보</div>
		<div class="value location">
			<div id="map" style="width: 950px; height: 400px;"></div>
		</div>
	</div>
</section>

<section>
	<div class="container">
		<button>목록보기</button>
	</div>
</section>

<!-- view2 Template 전용 JavaScript -->
<!-- Kakao Map Open API -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c089ee6f3d885cfbe52b2f15d8f3f531"></script>

<!-- Slick Slider -->
<script type="text/javascript"
	src="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

<script>
	/* 카카오 맵 */
	const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스

	const options = { //지도를 생성할 때 필요한 기본 옵션
		center : new kakao.maps.LatLng(33.361488, 126.529212), //지도의 중심좌표.
		level : 10, //지도의 레벨(확대, 축소 정도)
		//draggable : false, // 이동 금지
		//disableDoubleClick : true, // 더블클릭 확대 금지
		//scrollwheel : false // 휠 확대/축소 금지
	};

	const map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

	//마커 출력
	let imageSrc = '/dd/resources/files/marker/restaurant_marker2.png'; // 마커이미지의 주소
	const imageSize = new kakao.maps.Size(40,40);
	const option = {};

	//마커 설정
	const markerImg = new kakao.maps.MarkerImage(imageSrc, imageSize, option);

	const m1 = new kakao.maps.Marker({
		position: new kakao.maps.LatLng(${dto.lat}, ${dto.lng}), /* < 좌측 값은 테스트용 임시 ${location_dto.lat}, ${location_dto.lng} */
		image: markerImg
	});

	//마커 지도에 출력
	m1.setMap(map);


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