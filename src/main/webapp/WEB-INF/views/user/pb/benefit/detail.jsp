<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  === view ver.2 (상세화면) Template -->

<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>

<!-- Slick -->
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Roboto:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<link href="/dd/resources/price/vendor/aos/aos.css" rel="stylesheet">
<link href="/dd/resources/price/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="/dd/resources/price/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="/dd/resources/price/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="/dd/resources/price/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link href="/dd/resources/price/vendor/swiper/swiper-bundle.min.css"
	rel="stylesheet">

<link href="/dd/resources/price/css/style.css" rel="stylesheet">


<!-- view 2 전용 style tag -->
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
		background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("/dd/resources/files/activity/ferris-wheel-3656782_1280.jpg") center center;
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
	
	.result-container > .result-item:last-child > .value {
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
	
	/* slick slider */
	/* .image-slider {
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
	} */
	
	 .image-slider {
    width: 100%;
  }

  .image-slider div {
    width: auto; /* 이미지 크기에 맞게 조절 */
    max-width: 100%; /* 부모 요소 너비를 초과하지 않도록 설정 */
    text-align: center; /* 가운데 정렬 */
    transform: translateX(0); /* 레이아웃 초기화 */
    display: block;
  }

   .image-slider img {
    width: 100%; /* 이미지가 부모 요소에 꽉 차도록 설정 */
    height: auto; /* 비율 유지 */
    border-radius: 15px;
    min-height: 200px; /* 최소 높이 설정 */
    object-fit: contain; /* 이미지 비율 유지하며 가득 차도록 설정 */
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
		margin-top: 50px;
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
	div#reservation-btn > button {
		padding: 13px 15px;
		background: #b71c1c;
		border: #b71c1c;
		border-radius: 7px;
		color: #FFF;
		font-weight: bold;
		font-size: 17px;
	}
	
	.item>div:nth-child(1) {
    height: 70%;
    background-color: transparent;
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    border-radius: 10px 10px 0 0;
    display: flex;
    align-items: center;
    justify-content: center; /* 이미지를 가운데 정렬 */
}

.item>div:nth-child(1) {
    height: 70%;
    background-color: transparent;
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    border-radius: 10px 10px 0 0;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .item>div:nth-child(1) img {
    max-width: 100%;
    max-height: 100%;
    border-radius: 10px 10px 0 0;
    width: auto; /* 추가: 이미지의 원본 비율을 유지하면서 최대 너비에 맞추기 */
    height: auto; /* 추가: 이미지의 원본 비율을 유지하면서 최대 높이에 맞추기 */
    
  }
</style>
<body>
<!-- ======= Title & Image Section ======= -->
<section>
	<div class="container" data-aos="zoom-out">
		<div class="section-header">
			 <c:forEach items="${benefitInfoList}" var="dto"> 
			<h2>${dto.name}</h2>
		</div>
		<!-- Slick Slider -->
		<div class="image-slider">
				<div>
					<img src="/dd/resources/files/benefit/${dto.img}" alt="Image">
				</div>
			</c:forEach> 
		</div>
		<!-- End Slick Slider -->
		<p class="section-info">
			${dto.info}
		</p>
	</div>
</section>
<!-- End Title & Image Section -->

<!-- 어트랙션 예약 버튼 -->
<div id="reservation-btn">
	<button type="button" onclick="location.href='/dd/member/ticket/personal-reservation/view.do?seq=${dto.benefit_seq}'">티켓 예매하기 <i class="bi bi-hand-index-thumb-fill"></i></button>
</div>

<!-- ======= 상세정보 Section ======= -->
<!-- 운휴일정, 운영시간, 탑승인원, 이용정보 -->
<section>
	<div class="close-container">
		<div class="close-item">
			<div class="label">할인혜택</div>
			<div class="value">
				<%--  <c:if test="${dto.close== 'n'}">  --%>
				<c:forEach items="${benefitInfoList}" var="dto"> 
					<img src="/dd/resources/files/activity/calendar_icon.png" alt="Image" class="icon" />
					${dto.discount_rate}%할인 혜택 적용
				</c:forEach>
				<%-- </c:if> --%>
				<%-- <c:if test="${dto.close == 'y'}"> --%> 
					<img src="/dd/resources/files/activity/calendar_icon.png" alt="Image" class="icon" />
					개인 , 단체 혜택 적용
				 <%-- </c:if>   --%>
			</div>
		</div>
	</div>
	<!-- <div class="result-container"> -->
	
	<section id="pricing" class="pricing">
		<div class="container" data-aos="fade-up">

			

			<div class="row">

				<div class="col-lg-3 col-md-6" data-aos="fade-up"
					data-aos-delay="100">
					<div class="box">
						<h3>청소년</h3>
						<span class="advanced">1DAY</span>
						<div></div>
						<%-- <c:forEach items="${discountList1Day1}" var="discount"> --%>
						<h4>
							<sup>₩</sup> ${discountList1Day2}
						</h4>
						<%-- </c:forEach> --%>
						<ul>
							<c:forEach items="${list}" var="dto">
								<c:if test="${dto.ticket_type eq '1Day' && dto.age eq '청소년'}">
									<li class="na">정상가 ₩ ${dto.price}</li>
								</c:if>
							</c:forEach>
							<li>만 13세 이상 ~</li>
							<li>만 18세</li>
							<li>청소년 요금 적용</li>
							<li>(학교 및 학년 무관)</li>
						</ul>
						<div class="btn-wrap">
							<a href="/dd/member/ticket/personal-reservation/view.do" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-6 mt-4 mt-md-0" data-aos="fade-up"
					data-aos-delay="200">
					<div class="box">
						<h3>성인</h3>
						<span class="advanced">1DAY</span>
						<h4>
							<sup>₩</sup>${discountList1Day1}
						</h4>

						<ul>
							<c:forEach items="${list}" var="dto">
								<c:if test="${dto.ticket_type eq '1Day' && dto.age eq '성인'}">
									<li class="na">정상가 ₩ ${dto.price}</li>
								</c:if>
							</c:forEach>
							<li>만 19세 이상 ~</li>
							<li>만 64세</li>
							<li>성인 요금 적용</li>
							<li>(출생 및 인종 무관)</li>
						</ul>
						<div class="btn-wrap">
							<a href="/dd/member/ticket/personal-reservation/view.do" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-6 mt-4 mt-lg-0" data-aos="fade-up"
					data-aos-delay="300">
					<div class="box">
						<h3>청소년</h3>
						<span class="advanced">After4</span>


						<h4>
							<sup>₩</sup> ${discountListAfter42 }
						</h4>

						<ul>
							<c:forEach items="${list}" var="dto">
								<c:if test="${dto.ticket_type eq 'After4' && dto.age eq '청소년'}">
									<li class="na">정상가 ₩ ${dto.price}</li>
								</c:if>
							</c:forEach>
							<li>만 13세 이상 ~</li>
							<li>만 18세</li>
							<li>청소년 요금 적용</li>
							<li>(학교 및 학년 무관)</li>
						</ul>
						<div class="btn-wrap">
							<a href="/dd/member/ticket/personal-reservation/view.do" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-6 mt-4 mt-lg-0" data-aos="fade-up"
					data-aos-delay="400">
					<div class="box">
						<span class="advanced">After4</span>
						<h3>성인</h3>

						<h4>
							<sup>₩</sup> ${discountListAfter41}

						</h4>

						<ul>
							<c:forEach items="${list}" var="dto">
								<c:if test="${dto.ticket_type eq 'After4' && dto.age eq '성인'}">
									<li class="na">정상가 ₩ ${dto.price}</li>
								</c:if>
							</c:forEach>
							<li>만 19세 이상 ~</li>
							<li>만 64세</li>
							<li>성인 요금 적용</li>
							<li>(출생 및 인종 무관)</li>
						</ul>
						<div class="btn-wrap">
							<a href="/dd/member/ticket/personal-reservation/view.do" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>




			</div>

		</div>

<!--  단체 -->
	<section id="pricing" class="pricing">
		<div class="container" data-aos="fade-up">

			<!--  <div class="close-item">
				<h3>단체</h3>
				<p>어드벤처&매직아일랜드 입장 및 놀이시설 이용 (게임시설 등 유료시설 제외), 상기 이용권은 일반 할인 혜택
					적용이 불가하며 특수한 경우에 한하여 할인 혜택 적용이 가능합니다. (장애인 우대, 대관행사 등)</p>
			</div> -->
			<div class="row">

				<div class="col-lg-3 col-md-6" data-aos="fade-up"
					data-aos-delay="100">
					<div class="box">
						<h3>청소년</h3>
						<span class="advanced">1DAY</span>

						<div></div>
						<h4>
							<sup>₩</sup> ${groupDiscount1Day2}
						</h4>

						<ul>
							<c:forEach items="${groupList}" var="dto">
								<c:if test="${dto.ticket_type eq '1Day' && dto.age eq '청소년'}">
									<li class="na">정상가 ₩ ${dto.price}</li>
								</c:if>
							</c:forEach>
							<li>만 13세 이상 ~</li>
							<li>만 18세</li>
							<li>청소년 요금 적용</li>
							<li>(학교 및 학년 무관)</li>
						</ul>
						<div class="btn-wrap">
							<a href="/dd/member/ticket/group-reservation/view.do" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-6 mt-4 mt-md-0" data-aos="fade-up"
					data-aos-delay="200">
					<div class="box">
						<h3>성인</h3>
						<span class="advanced">1DAY</span>

						<h4>
							<sup>₩</sup> ${groupDiscount1Day1}
						</h4>
						<ul>
						<c:forEach items="${groupList}" var="dto">
								<c:if test="${dto.ticket_type eq '1Day' && dto.age eq '성인'}">
									<li class="na">정상가 ₩ ${dto.price}</li>
								</c:if>
							</c:forEach>
							<li>만 19세 이상 ~</li>
							<li>만 64세</li>
							<li>성인 요금 적용</li>
							<li>(출생 및 인종 무관)</li>
						</ul>
						<div class="btn-wrap">
							<a href="/dd/member/ticket/group-reservation/view.do" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-6 mt-4 mt-lg-0" data-aos="fade-up"
					data-aos-delay="300">
					<div class="box">
						<h3>청소년</h3>
						<span class="advanced">After4</span>

						<h4>
							<sup>₩</sup> ${groupDiscountAfter42}
						</h4>

						<ul>
						<c:forEach items="${groupList}" var="dto">
								<c:if test="${dto.ticket_type eq 'After4' && dto.age eq '청소년'}">
									<li class="na">정상가 ₩ ${dto.price}</li>
								</c:if>
							</c:forEach>
							<li>만 13세 이상 ~</li>
							<li>만 18세</li>
							<li>청소년 요금 적용</li>
							<li>(학교 및 학년 무관)</li>
						</ul>
						<div class="btn-wrap">
							<a href="/dd/member/ticket/group-reservation/view.do" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-6 mt-4 mt-lg-0" data-aos="fade-up"
					data-aos-delay="400">
					<div class="box">
						<span class="advanced">After4</span>
						<h3>성인</h3>

						<h4>
							<sup>₩</sup> ${groupDiscountAfter41}
						</h4>

						<ul>
						<c:forEach items="${groupList}" var="dto">
								<c:if test="${dto.ticket_type eq 'After4' && dto.age eq '성인'}">
									<li class="na">정상가 ₩ ${dto.price}</li>
								</c:if>
							</c:forEach>
							<li>만 19세 이상 ~</li>
							<li>만 64세</li>
							<li>성인 요금 적용</li>
							<li>(출생 및 인종 무관)</li>
						</ul>
						<div class="btn-wrap">
							<a href="/dd/member/ticket/group-reservation/view.do" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>




			</div>

		</div>

	</section>

	</section>
	


	<!-- </div> -->

</section>

	<!-- 목록보기 버튼 -->
<div id="button">
	<button type="button" id="back-button" class="btn btn-primary" onclick="location.href='/dd/user/pb/benefit/view.do';"><i class="bi bi-list"></i>목록</button>
</div>
</body>

<script>
//이미지 크기 조정 및 컨테이너 내 가운데 정렬 함수
function adjustImageSizeAndCenter(image) {
  var maxWidth = 100; // 원하는 최대 너비로 설정
  var maxHeight = 50; // 원하는 최대 높이로 설정
  console.log("Original Image Size:", image.width, "x", image.height);

  
  if (image.width > maxWidth || image.height > maxHeight) {
    var ratio = Math.min(maxWidth / image.width, maxHeight / image.height);
    image.style.width = image.width * ratio + "px";
    image.style.height = image.height * ratio + "px";
  }
  
  console.log("Adjusted Image Size:", image.width, "x", image.height);

  // 이미지를 컨테이너 내 가운데 정렬
  var container = image.parentNode;
  var containerWidth = container.offsetWidth;
  var containerHeight = container.offsetHeight;

  var marginX = (containerWidth - image.width) / 2;
  var marginY = (containerHeight - image.height) / 2;

  container.style.paddingLeft = marginX + 'px';
  container.style.paddingRight = marginX + 'px';
  container.style.paddingTop = marginY + 'px';
  container.style.paddingBottom = marginY + 'px';
}

// 이미지 로드 이벤트 처리 함수
function handleImageLoad() {
  console.log("이미지 로드 이벤트 발생");
  adjustImageSizeAndCenter(this);
}

document.addEventListener("DOMContentLoaded", function () {
  console.log("DOMContentLoaded 이벤트 발생");

  // 각 이미지를 개별적으로 선택 및 처리
  var imageElements = document.querySelectorAll('.item>div:nth-child(1) img');

  imageElements.forEach(function (image) {
    if (image.complete) {
      // 이미지가 이미 로드된 경우 즉시 처리
      handleImageLoad.call(image);
    } else {
      // 이미지가 로드되지 않은 경우 로드될 때까지 처리 대기
      image.onload = handleImageLoad;
    }
  });
});

</script>







<!-- view2 Template 전용 JavaScript -->
<!-- Kakao Map Open API -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c089ee6f3d885cfbe52b2f15d8f3f531"></script>

<!-- Slick Slider -->
<script type="text/javascript" src="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>



<!-- <script
		src="/dd/resources/price/vendor/purecounter/purecounter_vanilla.js"></script> -->
	<script src="/dd/resources/price/vendor/aos/aos.js"></script>
	<script
		src="/dd/resources/price/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="/dd/resources/price/vendor/glightbox/js/glightbox.min.js"></script>
	<script
		src="/dd/resources/price/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script src="/dd/resources/price/vendor/swiper/swiper-bundle.min.js"></script>
	<script
		src="/dd/resources/price/vendor/waypoints/noframework.waypoints.js"></script>
	<script src="/dd/resources/price/vendor/php-email-form/validate.js"></script>


	<script src="/dd/resources/price/js/main.js"></script>

<!-- 끝 -->



