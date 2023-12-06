<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>BizLand Bootstrap Template - Index</title>
<meta content="" name="description">
<meta content="" name="keywords">


<link href="/dd/resources/price/img/favicon.png" rel="icon">
<link href="/dd/resources/price/img/apple-touch-icon.png"
	rel="apple-touch-icon">

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

<!-- =======================================================
  * Template Name: BizLand
  * Updated: Sep 18 2023 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/bizland-bootstrap-business-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>
<!--   처음 사진부분 -->
<section id="stats-counter" class="stats-counter">
      <div id="pagetitle" class="container" data-aos="zoom-out">
        <div class="gy-4" style="justify-content: center; width: 100%;">

          <div class="col-lg-3 col-md-6" style="width: 100%;">
            <div class="stats-item text-center w-100 h-100">
              <div id="title" style="font-size: 48px;
              display: block;
              color: #fff;
              font-weight: 700;">티 켓 요 금</div>
              <p>다양한 옵션으로 즐거운 시간을 즐기세요!</p>
            </div>
          </div>
        </div>
      </div>
    </section>
    


	<!-- ======= Pricing Section 개인요금 ======= -->
	<section id="pricing" class="pricing">
		<div class="container" data-aos="fade-up">

			<div class="section-title">
				<h3>개인</h3>
				<p> 어드벤처&매직아일랜드 입장 및 놀이시설 이용 (게임시설 등 유료시설 제외),
					민속박물관 관람 (입장마감 18시),
					어드벤처&매직아일랜드와 민속박물관은 미이용에 따른 분리 환불 불가능</p>
			</div>

			<div class="row">

				<div class="col-lg-3 col-md-6" data-aos="fade-up"
					data-aos-delay="100">
					<div class="box">
						<h3>청소년</h3>
						<span class="advanced">1DAY</span>
						<c:forEach items="${personTypeList}" var="dto">
							<c:if test="${dto.ticket_type eq '1Day' && dto.age eq '청소년'}">
								<div></div>
								<h4>
									<sup>$</sup>${dto.price}
								</h4>
							</c:if>
						</c:forEach>

						<ul>
							<li>만 13세 이상 ~</li>
							<li> 만 18세</li>
							<li>청소년 요금 적용</li>
							<li>(학교 및 학년 무관)</li>
							<li class="na">Massa ultricies mi</li>
						</ul>
						<div class="btn-wrap">
							<a href="#" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-6 mt-4 mt-md-0" data-aos="fade-up"
					data-aos-delay="200">
					<div class="box">
						<h3>성인</h3>
						<span class="advanced">1DAY</span>
						<c:forEach items="${personTypeList}" var="dto">
							<c:if test="${dto.ticket_type eq '1Day' && dto.age eq '성인'}">
								<h4>
									<sup>$</sup>${dto.price}
								</h4>
							</c:if>
						</c:forEach>
						<ul>
							<li>만 19세 이상 ~</li>
							<li> 만 64세</li>
							<li>성인 요금 적용</li>
							<li>(출생 및 인종 무관)</li>
							<li class="na">Massa ultricies mi</li>
						</ul>
						<div class="btn-wrap">
							<a href="#" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-6 mt-4 mt-lg-0" data-aos="fade-up"
					data-aos-delay="300">
					<div class="box">
						<h3>청소년</h3>
						<span class="advanced">After4</span>
						<c:forEach items="${personTypeList}" var="dto">
							<c:if test="${dto.ticket_type eq 'After4' && dto.age eq '청소년'}">
								<h4>
									<sup>$</sup>${dto.price }<span>
								</h4>
							</c:if>
						</c:forEach>
						<ul>
							<li>만 13세 이상 ~</li>
							<li> 만 18세</li>
							<li>청소년 요금 적용</li>
							<li>(학교 및 학년 무관)</li>
							<li class="na">Massa ultricies mi</li>
						</ul>
						<div class="btn-wrap">
							<a href="#" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-6 mt-4 mt-lg-0" data-aos="fade-up"
					data-aos-delay="400">
					<div class="box">
						<span class="advanced">After4</span>
						<h3>성인</h3>
						<c:forEach items="${personTypeList}" var="dto">
							<c:if test="${dto.ticket_type eq 'After4' && dto.age eq '성인'}">
						<h4>
							<sup>$</sup>${dto.price }<span>
						</h4>
						</c:if>
						</c:forEach>
						<ul>
							<li>만 19세 이상 ~</li>
							<li> 만 64세</li>
							<li>성인 요금 적용</li>
							<li>(출생 및 인종 무관)</li>
							<li class="na">Massa ultricies mi</li>
						</ul>
						<div class="btn-wrap">
							<a href="#" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>




			</div>

		</div>

	</section>
	<!-- End Pricing Section -->


<!-- ======= Pricing Section 단체요금 ======= -->
	<section id="pricing" class="pricing">
		<div class="container" data-aos="fade-up">

			<div class="section-title">
				<h3>단체</h3>
				<p>어드벤처&매직아일랜드 입장 및 놀이시설 이용 (게임시설 등 유료시설 제외),
	 상기 이용권은 일반 할인 혜택 적용이 불가하며 특수한 경우에 한하여 할인 혜택 적용이 가능합니다. (장애인 우대, 대관행사 등)</p>
			</div>

			<div class="row">

				<div class="col-lg-3 col-md-6" data-aos="fade-up"
					data-aos-delay="100">
					<div class="box">
						<h3>청소년</h3>
						<span class="advanced">1DAY</span>
						<c:forEach items="${groupTypeList}" var="dto">
							<c:if test="${dto.ticket_type eq '1Day' && dto.age eq '청소년'}">
								<div></div>
								<h4>
									<sup>$</sup>${dto.price}
								</h4>
							</c:if>
						</c:forEach>

						<ul>
							<li>만 13세 이상 ~</li>
							<li> 만 18세</li>
							<li>청소년 요금 적용</li>
							<li>(학교 및 학년 무관)</li>
							<li class="na">Massa ultricies mi</li>
						</ul>
						<div class="btn-wrap">
							<a href="#" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-6 mt-4 mt-md-0" data-aos="fade-up"
					data-aos-delay="200">
					<div class="box">
						<h3>성인</h3>
						<span class="advanced">1DAY</span>
						<c:forEach items="${groupTypeList}" var="dto">
							<c:if test="${dto.ticket_type eq '1Day' && dto.age eq '성인'}">
								<h4>
									<sup>$</sup>${dto.price}
								</h4>
							</c:if>
						</c:forEach>
						<ul>
							<li>만 19세 이상 ~</li>
							<li> 만 64세</li>
							<li>성인 요금 적용</li>
							<li>(출생 및 인종 무관)</li>
							<li class="na">Massa ultricies mi</li>
						</ul>
						<div class="btn-wrap">
							<a href="#" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-6 mt-4 mt-lg-0" data-aos="fade-up"
					data-aos-delay="300">
					<div class="box">
						<h3>청소년</h3>
						<span class="advanced">After4</span>
						<c:forEach items="${groupTypeList}" var="dto">
							<c:if test="${dto.ticket_type eq 'After4' && dto.age eq '청소년'}">
								<h4>
									<sup>$</sup>${dto.price }<span>
								</h4>
							</c:if>
						</c:forEach>
						<ul>
							<li>만 13세 이상 ~</li>
							<li> 만 18세</li>
							<li>청소년 요금 적용</li>
							<li>(학교 및 학년 무관)</li>
							<li class="na">Massa ultricies mi</li>
						</ul>
						<div class="btn-wrap">
							<a href="#" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-6 mt-4 mt-lg-0" data-aos="fade-up"
					data-aos-delay="400">
					<div class="box">
						<span class="advanced">After4</span>
						<h3>성인</h3>
						<c:forEach items="${groupTypeList}" var="dto">
							<c:if test="${dto.ticket_type eq 'After4' && dto.age eq '성인'}">
						<h4>
							<sup>$</sup>${dto.price }<span>
						</h4>
						</c:if>
						</c:forEach>
						<ul>
							<li>만 19세 이상 ~</li>
							<li> 만 64세</li>
							<li>성인 요금 적용</li>
							<li>(출생 및 인종 무관)</li>
							<li class="na">Massa ultricies mi</li>
						</ul>
						<div class="btn-wrap">
							<a href="#" class="btn-buy">Buy Now</a>
						</div>
					</div>
				</div>




			</div>

		</div>

	</section>
	<!-- End Pricing Section -->

	<!-- 단체 요금 -->


	</main>
	<!-- End #main -->

	<!-- 
  <div id="preloader"></div>
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
 -->

	<script
		src="/dd/resources/price/vendor/purecounter/purecounter_vanilla.js"></script>
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

</body>

</html>