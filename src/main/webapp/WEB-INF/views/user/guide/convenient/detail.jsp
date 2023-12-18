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

p {
	margin-top: 10px;
	margin-bottom: 1rem;
	color: darkgray;
}

</style>

<!-- ======= Title & Image Section ======= -->
<section>
	<div class="container" data-aos="zoom-out" style="margin-bottom: 70px;">
		<div class="section-header">
			<h2>${dto.name}</h2>
		</div>
		<div style="text-align: center;">
			<img src="/dd/resources/files/guide/convenient/${dto.img }"
				alt="Image" style="width: 700px; height:350px; border-radius:15px;">
		</div>
	</div>
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


		<div class="result-item">
			<img src="/dd/resources/files/activity/calendar_icon.png" alt="Image"
				class="icon" />
			<div class="label">정상 운영</div>
		</div>

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
		<p>* 스크롤과 드래그로 지도를 움직일 수 있습니다. *</p>
		<div class="value location">
			<div id="map" style="width: 950px; height: 435px;"></div>
		</div>
		<div>
			<button onclick="setBounds()" id="comeback">원래대로 돌아가기</button>
		</div>
	</div>
</section>

<!-- 목록보기 버튼 -->
<div id="button">
	<button type="button" id="back-button" class="btn btn-primary"
		onclick="location.href='/dd/user/guide/convenient/view.do';">
		<i class="bi bi-list"></i>목록
	</button>
</div>

<!-- view2 Template 전용 JavaScript -->
<!-- Kakao Map Open API -->
<!-- Kakao Map Open API -->
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c089ee6f3d885cfbe52b2f15d8f3f531"></script>

<script>
/* 카카오 맵 */
const container = document.getElementById('map'); // 지도를 담을 영역의 DOM 레퍼런스

let options = {
  center: new kakao.maps.LatLng(33.361488, 126.529212), // 지도의 중심좌표.
  level: 10, // 지도의 레벨(확대, 축소 정도)
  draggable: true, // 이동 금지
  disableDoubleClick: false, // 더블클릭 확대 금지
  scrollwheel: true // 휠 확대/축소 금지
};

const map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴

// 마커 출력
let imageSrc = '/dd/resources/files/marker/info.png'; // 마커이미지의 주소
const imageSize = new kakao.maps.Size(40, 40);
const option = {};

// 마커 설정
const markerImg = new kakao.maps.MarkerImage(imageSrc, imageSize, option);

const m1 = new kakao.maps.Marker({
  position: new kakao.maps.LatLng(${dto.lat}, ${dto.lng}),
  image: markerImg
});

// 마커 지도에 출력
m1.setMap(map);

// LatLngBounds 객체 초기화
const defaultBounds = new kakao.maps.LatLngBounds();

// 고정된 지도의 중심좌표와 레벨
const fixedCenter = new kakao.maps.LatLng(33.361488, 126.529212);
const fixedLevel = 10;

// 이전의 지도 영역을 기억하기 위한 변수
let previousBounds;

// 버튼 클릭 시 실행할 함수
function setBounds() {
  console.log('setBounds clicked');

  // 이전 지도 영역을 고정된 좌표로 설정
  previousBounds = new kakao.maps.LatLngBounds(
    new kakao.maps.LatLng(fixedCenter.getLat(), fixedCenter.getLng()),
    new kakao.maps.LatLng(fixedCenter.getLat(), fixedCenter.getLng())
  );

  console.log('Previous Bounds:', previousBounds.toString());

  // 이전의 레벨을 고정된 레벨로 설정
  options = {
    center: fixedCenter,
    level: fixedLevel,
    draggable: true,
    disableDoubleClick: false,
    scrollwheel: true
  };

  map.setLevel(fixedLevel);
  map.setCenter(fixedCenter);

  // 이전의 레벨과 중심좌표로 지도를 초기화
  map.setOptions(options);
}

// 버튼 이벤트 등록
document.getElementById('comeback').addEventListener('click', setBounds);

// 드래그 이벤트 등록
kakao.maps.event.addListener(map, 'dragend', function () {
  // 이전 지도 영역을 기억
  previousBounds = map.getBounds();
});

// 지도 이벤트 등록
kakao.maps.event.addListener(map, 'bounds_changed', function () {
  // 지도의 중심좌표를 확인하고, 원래대로 돌아갈 수 있는 경우에 버튼을 활성화
  if (
    map.getLevel() !== fixedLevel ||
    !previousBounds.equals(map.getBounds())
  ) {
    document.getElementById('comeback').disabled = false;
  } else {
    document.getElementById('comeback').disabled = true;
  }
});
</script>
<!-- 끝 -->