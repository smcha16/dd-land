<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  === view ver.1 (상세화면) Template -->

<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />

<!-- view 전용 style tag -->
<style>

/* 폰트는 테스트용 임시 */
* {
	font-family: 'SUIT-Regular';
}
/* 폰트 테스트 끝 */
section {
	padding: 140px 0;
}

.section-header h2 {
	font-size: 48px;
	font-family: 'SUIT-Regular';
	color: #000;
}

.section-header p {
	font-size: 15px;
	font-family: 'SUIT-Regular';
	margin: 5px;
}

#hashtag {
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
	margin: 20px auto;
}

#hashtag>i {
	font-size: 2rem;
	margin-right: 10px;
	color: darkred;
}

#hashtag>div {
	margin: 3px 5px;
	font-size: 1rem;
	border: 0.8px solid #999;
	border-radius: 20px;
	padding: 5px 8px;
}

#background {
	background-image: url('assets/img/detail_background_half_trans.png');
	background-color: transparent;
	background-repeat: no-repeat;
	background-position: center;
	background-size: 100%
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

/* .result-container > .result-item:nth-child(1) > .value {
		font-size: 1.3rem;
	} */
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

.close-container {
	display: flex;
	flex-direction: column;
	align-items: center;
	padding-top: 50px;
}

.close-item {
	width: 950px;
	/* border: 1px solid #999; */
	border-radius: 5px;
	background-color: #FFFFFF;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.close-time>.value {
	margin: 0 auto;
}

.icon {
	width: 60px;
	height: 60px;
	margin: 20px 10px;
}

@font-face {
	font-family: 'SUIT-Regular';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_suit@1.0/SUIT-Regular.woff2')
		format('woff2');
	font-weight: normal;
	font-style: normal;
}
</style>
<!-- ======= Gallery Section ======= -->
<section id="gallery" class="gallery section-bg">
	<div class="container" data-aos="fade-up">

		<!-- 상세 목록 > 제목, 설명, 태그 -->
		<div class="section-header">
			<h2>${dto.name}</h2>
			<p>${dto.menu}</p>
			<!-- <div id="hashtag">
            <i class="fa-solid fa-tag fa-rotate-90"></i>
            <div>어트랙션</div>
            <div>신나는</div>
          </div> -->
		</div>
		<!-- End of 상세 목록 > 제목, 설명, 태그 -->

		<div id="carouselExampleControls" class="carousel slide"
			data-bs-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="assets/img/지브리특급2.jpeg" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="assets/img/지브리특급2.jpeg" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="assets/img/지브리특급2.jpeg" class="d-block w-100" alt="...">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleControls" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleControls" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>

	</div>
</section>
<!-- End Gallery Section -->

<!-- 상세 정보 -->
<!-- 운휴일정, 운영시간, 탑승인원, 이용정보 -->
<div id="background">
	<div class="close-container">
		<div class="close-item">
			<div class="label">운휴일정</div>
			<div class="value">
				<img src="assets/img/calendar_icon.png" alt="Image" class="icon" />
				<c:if test="${dto.close == 'y'}">
								금일 은휴
							</c:if>
				<c:if test="${dto.close == 'n'}">
								정상 운영
							</c:if>
			</div>
		</div>
	</div>
	<div class="result-container">
		<div class="result-item">
			<img src="assets/img/time_icon.png" alt="Image" class="icon" />
			<div class="label">운영시간</div>
			<div class="value">${dto.time}</div>
		</div>
		<div class="result-item">
			<img src="assets/img/people_icon.png" alt="Image" class="icon" />
			<div class="label">수용인원</div>
			<div class="value">${dto.capacity}명</div>
		</div>
		<div class="result-item">
			<img src="assets/img/info_icon.png" alt="Image" class="icon" />
			<div class="label">전화번호</div>
			<div class="value">${dto.tel}</div>
		</div>
	</div>
</div>

<!-- 위치 정보 -->
<div class="location">
	<div class="label">위치 정보</div>
	<div class="value location">
		<div id="map" style="width: 1125px; height: 400px;"></div>
	</div>
</div>

<div class="container" style="margin-top: 50px;">
	<button type="button" class="btn"
		onclick="location.href='/ddstudio/shop/restaurant.do'">목록</button>
</div>
 
<!-- 끝 -->

<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ae4c975e0553221a835879cdf6246a66"></script>
<script>
	const container = document.getElementById('map');
	const options = {
		center : new kakao.maps.LatLng(33.3808, 126.5450),
		level : 10,
		draggable : false, // 이동 금지
		disableDoubleClick : true, // 더블클릭 확대 금지
		scrollwheel : false
	// 휠 확대/축소 금지
	};

	const map = new kakao.maps.Map(container, options);

	const lat = $
	{
		dto.lat
	};
	const lng = $
	{
		dto.lng
	};

	const m = new kakao.maps.Marker({
		position : new kakao.maps.LatLng(lat, lng)
	});

	const imageSrc = '/ddstudio/asset/image/marker/restaurant.png';
	const imageSize = new kakao.maps.Size(40, 40);
	const option = {};

	const markerImg = new kakao.maps.MarkerImage(imageSrc, imageSize, option);

	m.setImage(markerImg);
	m.setMap(map);
</script>