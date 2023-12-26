<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  === view ver.2 (상세화면) Template -->

<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>

<style>
	/* 폰트는 테스트용 임시 */
	@font-face {
		font-family: 'SUIT-Regular';
		src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_suit@1.0/SUIT-Regular.woff2')format('woff2');
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
		margin: 2rem auto;
		width: 800px;
	}
	
	.section-image {
		display: flex;
		justify-content: center;
		padding-bottom: 30px;
	}
	
	.section-image>img {
		border-radius: 15px;
		/* 화질 깨져서 고민해보기 */
		/* width: 500px;
		height: 600px; */
	}
	
	.section-preview {
		display: flex;
		justify-content: center;
		padding: 20px 0;
	}
	
	section:first-of-type {
		padding-top: 140px;
		padding-bottom: 20px;
	}
	
	section:nth-of-type(2) {
		background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("/dd/resources/files/activity/movie_detail_background.jpg") center center;
		/* background-color: transparent;
		background-repeat: no-repeat; */
		background-size: cover;
		padding: 0;
	}
	
	section:last-of-type {
		padding-top: 30px;
		padding-bottom: 30px;
	}
	
	.date-container {
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	
	.date-item {
		width: 950px;
		border-radius: 5px;
		background: #FFF;
		display: flex;
		flex-direction: column;
		align-items: center;
		padding-bottom: 10px;
	}
	
	.date-item .value {
		font-weight: bold;
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
		margin: 10px 10px;
		font-size: 1.2rem;
		color: #333;
	}
	
	.value.location {
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	
	.location-container {
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	
	.location-item {
		width: 950px;
		border-radius: 5px;
		background: #FFF;
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	
	.location-item .label {
		margin: 20px 10px 0 10px;
	}
	
	.icon {
		width: 60px;
		height: 60px;
		margin: 20px 10px;
	}
	
	/* 버튼 */
	#button {
		display: flex;
		justify-content: center;
		margin: 50px;
	}
	#back-button {
		background-color: #CE1212;
		border-color: #CE1212;
	}
	#back-button i {
		margin-right: 7px;
	}
	
	/* 상영 영화관 Tab 버튼 CSS */
	ul { 
		list-style: none;
	}
	.tabmenu {
		max-width: 600px;
		margin: 0 auto;
		position: relative;
		display: none;
	}
	.tabmenu ul {
		position: relative;
		margin-bottom: 0;
		display: flex;
		justify-content: center;
	}
	.tabmenu ul li {
		display:  inline-block;
		width:auto; 
		text-align:center; 
		background :#f9f9f9;
		line-height:40px;
		border-radius: 5px 5px 0 0;
	}
	.tabmenu label {
		display:block;
		width:100%; 
		height:40px;
		line-height:40px;
		padding: 0 10px;
		cursor: pointer;
		border-radius: 5px 5px 0 0;
	}
	.tabmenu input{
		display:none;
	}
	.tabCon{
		display:none; 
		width: 100%;
		text-align:left; 
		padding: 20px;
		position:absolute; 
		left:0; top:40px; 
		box-sizing: border-box; 
		border : 5px solid #f9f9f9;
	}
	.tabmenu input:checked ~ label{
		background:#ccc;
	}
	.tabmenu input:checked ~ .tabCon{
		display:block;
	}
	
	/* 카카오맵 지도 주변 태그 CSS */
	.location-item p {
		margin-top: 10px;
		margin-bottom: 1rem;
		color: darkgray;
	}
	
	button#comeback {
		border: 0;
		border-radius: 50px;
		width: 50px;
		background-color: #FFF;
		color: #000;
		position: absolute;
		z-index: 100;		
		padding: 10px;
	    right: 10px;
	    bottom: 10px;
	}
</style>

<!-- ======= 영화 기본 정보 Section ======= -->
<section>
	<div class="container" data-aos="zoom-out">
		<div class="section-header">
			<h2>${dto.name}</h2>
		</div>
		<!-- 포스터 -->
		<div class="section-image">
			<img src="/dd/resources/files/activity/movie/${dto.img}" alt="Image">
		</div>
		<!-- 줄거리 -->
		<p class="section-info">${dto.story}</p>
		<!-- 예고편 -->
		<c:if test="${dto.preview != null || dto.preview != ''}">
			<div class="section-preview">${dto.preview}</div>
		</c:if>
	</div>
</section>
<!-- End Title & Image Section -->

<!-- 상영 영화관 Tab Section -->
<div class="tabmenu out-tabmenu">
	<ul>
		<c:forEach items="${dto.moviePlayList}" var="dto" varStatus="status">
			<li id="tab${status.count}" class="tabBtn">
				<c:if test="${status.first}">
				<input type="radio" id="tabmenu${status.count}" name="tabmenu" value="${dto.movie_play_seq}" checked>
				</c:if>
				<c:if test="${!status.first}">
				<input type="radio" id="tabmenu${status.count}" name="tabmenu" value="${dto.movie_play_seq}">
				</c:if>
				<label for="tabmenu${status.count}">${dto.theater_name}</label>
				<div class="tabContent"></div>
			</li>
		</c:forEach>
	</ul>
</div>
<!-- 러닝타임 및 상영 영화관 정보 Section -->
<section>
	<!-- 상영 일자 -->
	<div class="date-container">
		<div class="date-item">
			<!-- <img src="/dd/resources/files/activity/calendar_icon.png" alt="Image" class="icon" /> -->
			<div class="label">상영 일자</div>
		 	<div id="date" class="value"></div>
		</div>
	</div>
	
	<!-- 상영 정보 -->
	<div class="result-container">
		 <div class="result-item">
		 	<img src="/dd/resources/files/activity/info_icon.png" alt="Image" class="icon" />
		 	<div class="label">러닝타임</div>
		 	<div id="runningtime" class="value"></div>
		 </div>
		<div class="result-item">
			<img src="/dd/resources/files/activity/time_icon.png" alt="Image" class="icon" />
			<div class="label">상영 시간</div>
			<div id="time" class="value"></div>
		</div>
		<div class="result-item">
			<img src="/dd/resources/files/activity/movie_icon.png" alt="Image" class="icon" />
			<div class="label">상영 영화관</div>
			<div id="theater_name" class="value"></div>
		</div>
	</div>
	
	<!-- 위치 정보 -->
	<div class="location-container">
		<div class="location-item">
			<div class="label">위치 정보</div>
			<p>* 스크롤과 드래그로 지도를 움직일 수 있습니다. *</p>
			<div class="value location">
				<div id="map" style="width: 920px; height: 370px; border-radius: 5px;">
					<button onclick="setBounds()" id="comeback"><i class="fa-solid fa-rotate-left"></i></button>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- 목록보기 버튼 -->
<div id="button">
	<button type="button" id="back-button" class="btn btn-primary" onclick="location.href='/dd/user/activity/movie/view.do';"><i class="bi bi-list"></i>목록</button>
</div>

<!-- view2 Template 전용 JavaScript -->
<!-- Kakao Map Open API -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c089ee6f3d885cfbe52b2f15d8f3f531"></script>

<script>
	/* 카카오 맵 */
	const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스

	let options = { //지도를 생성할 때 필요한 기본 옵션
		center : new kakao.maps.LatLng(33.361488, 126.529212), //지도의 중심좌표.
		level : 10, //지도의 레벨(확대, 축소 정도)
		/* draggable : false, // 이동 금지
		disableDoubleClick : true, // 더블클릭 확대 금지
		scrollwheel : false // 휠 확대/축소 금지 */
	};

	const map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

	//마커 출력
	let imageSrc = '/dd/resources/files/marker/movie_marker.png'; // 마커이미지의 주소
	const imageSize = new kakao.maps.Size(40,40);
	const option = {};

	/* 지도 원래대로 돌아가기 버튼 */
	// LatLngBounds 객체 초기화
	const defaultBounds = new kakao.maps.LatLngBounds();

	// 고정된 지도의 중심좌표와 레벨
	const fixedCenter = new kakao.maps.LatLng(33.361488, 126.529212);
	const fixedLevel = 10;

	// 이전의 지도 영역을 기억하기 위한 변수
	let previousBounds;

	// 버튼 클릭 시 실행할 함수
	function setBounds() {

		// 이전 지도 영역을 고정된 좌표로 설정
		previousBounds = new kakao.maps.LatLngBounds(
			new kakao.maps.LatLng(fixedCenter.getLat(), fixedCenter.getLng()),
			new kakao.maps.LatLng(fixedCenter.getLat(), fixedCenter.getLng())
		);

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
		if (map.getLevel() !== fixedLevel || !previousBounds.equals(map.getBounds())) {
			document.getElementById('comeback').disabled = false;
		} else {
			document.getElementById('comeback').disabled = true;
		}
	});

	
	/* 상영 영화관이 1개 이상일 경우에만 탭 메뉴 출력하기 */
	if (${plistNum != 1}) {
		$('.tabmenu').css('display', 'block');
	}
	
	/* 상영 영화관 ajax로 불러오기 */
	//영화 상영 번호
	let obj = {
		movie_play_seq: $('input[type="radio"]:checked').val(),
		date: '${date}'
	};
	
	let m = null;
	
	function getMoviePlayListBySeq(obj) {
		
		//이전 마커 삭제
		if (m != null) {
			m.setMap(null);
		}
		
		// CSRF token
        var csrfHeaderName = "${_csrf.headerName}";
        var csrfTokenValue = "${_csrf.token}";
		
		$.ajax({
			type: 'POST',
			url: '/dd/user/activity/movieplay',
			headers: {'content-Type': 'application/json'},
			data: JSON.stringify(obj),
			dataType: 'json',
			success: function(result) {
				
				$('#runningtime').text(result.runningtime + '(분)');
				$('#time').text(result.time);
				$('#theater_name').text(result.theater_name);
				
				//마커 설정
				const markerImg = new kakao.maps.MarkerImage(imageSrc, imageSize, option);

				m = new kakao.maps.Marker({
					position: new kakao.maps.LatLng(result.lat, result.lng),
					image: markerImg
				});
				
				
				//마커 지도에 출력
				m.setMap(map);
				
			},
			beforeSend: function(xhr) {
            	xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
            },
			error: function(a,b,c) {
				console.log(a,b,c);
			}
            
		});
		
	}
	
	/* 상영 영화관 버튼 Click Event */
	$('input[type="radio"]').click(function() {
		
		obj = {
			movie_play_seq: $(this).val(),
			date: '${date}'
		}
		
		getMoviePlayListBySeq(obj);
		
	});
	
	/* 초기 진입 시 ajax 응답 받기 처리 */
	getMoviePlayListBySeq(obj);
	
	/* 상영 일자 출력 */
	if ('${date}' == 'sysdate') {
		
		let currentDate = new Date();
		
		let year = currentDate.getFullYear();
		let month = currentDate.getMonth() + 1;
		let day = currentDate.getDate();
		
		$('#date').text(year + '년 ' + month + '월 ' + day + '일');
	} else {
		
		let specificDate = '${date}'
		
		let specificYear = specificDate.slice(0,4);
		let specificMonth = specificDate.slice(5,7);
		let specificDay = specificDate.slice(8,10);
		
		$('#date').text(specificYear + '년 ' + specificMonth + '월 ' + specificDay + '일');
	}

</script>
<!-- 끝 -->