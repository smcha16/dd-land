<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	#total{
		justify-content: center;
	}
	#reference{
		text-align:center;
		margin-bottom: 30px;
		font-size: 30px;
	}
	#reservationBox{
		background-color: cornflowerblue;
	}
	.chefs .chef-member .member-img {
		padding-bottom: 10px;
	}
	.chef-member {
 	   cursor: pointer;
	}
</style>
	<main id="main">
	
	<!-- ======= Events Section ======= -->

		<section id="events" class="events">
			<div class="container-fluid" data-aos="fade-up">

				<div class="section-header" style="margin-top: 58px;">
					<h2>Events</h2>
					<p>
						Share <span>Your Moments</span> In Our Restaurant
					</p>
				</div>

				<div class="slides-3 swiper" data-aos="fade-up" data-aos-delay="100">
					<div class="swiper-wrapper">

						<div
							class="swiper-slide event-item d-flex flex-column justify-content-end"
							style="background-image: url(resources/main/img/events-1.jpg)">
							<h3>Custom Parties</h3>
							<div class="price align-self-start">$99</div>
							<p class="description">Quo corporis voluptas ea ad.
								Consectetur inventore sapiente ipsum voluptas eos omnis facere.
								Enim facilis veritatis id est rem repudiandae nulla expedita
								quas.</p>
						</div>
						<!-- End Event item -->

						<div
							class="swiper-slide event-item d-flex flex-column justify-content-end"
							style="background-image: url(resources/main/img/events-2.jpg)">
							<h3>Private Parties</h3>
							<div class="price align-self-start">$289</div>
							<p class="description">In delectus sint qui et enim. Et ab
								repudiandae inventore quaerat doloribus. Facere nemo vero est ut
								dolores ea assumenda et. Delectus saepe accusamus aspernatur.</p>
						</div>
						<!-- End Event item -->

						<div
							class="swiper-slide event-item d-flex flex-column justify-content-end"
							style="background-image: url(resources/main/img/events-3.jpg)">
							<h3>Birthday Parties</h3>
							<div class="price align-self-start">$499</div>
							<p class="description">Laborum aperiam atque omnis minus
								omnis est qui assumenda quos. Quis id sit quibusdam. Esse
								quisquam ducimus officia ipsum ut quibusdam maxime. Non enim
								perspiciatis.</p>
						</div>
						<!-- End Event item -->

					</div>
					<div class="swiper-pagination"></div>
				</div>

			</div>
		</section>
		<!-- End Events Section -->
		
		<!-- ======= Stats Counter Section ======= -->
		<section id="stats-counter" class="stats-counter" style="padding: 20px;">
			<div class="container" data-aos="zoom-out">

				<div class="row gy-4">

					<div class="col-lg-3 col-md-6">
						<div class="stats-item text-center w-100 h-100">
							<span data-purecounter-start="0" data-purecounter-end="10"
								data-purecounter-duration="1.8" class="purecounter"></span>
							<span data-purecounter-start="0" data-purecounter-end="22"
								data-purecounter-duration="1.5" class="purecounter"></span>
						</div>
					</div>
					<!-- End Stats Item -->

				</div>

			</div>
		</section>
		<!-- End Stats Counter Section -->
	

		<!-- ======= Why Us Section ======= -->
		<section id="why-us" class="why-us section-bg">
		
		<div id="reference">추천3!</div>
			<div class="container" data-aos="fade-up">

				<div class="row gy-4" id="total">
					<div class="col-lg-8 d-flex align-items-center">
						<div class="row gy-4">

							<div class="col-xl-4" data-aos="fade-up" data-aos-delay="200">
								<div
									class="icon-box d-flex flex-column justify-content-center align-items-center">
									<i class="bi bi-clipboard-data"></i>
									<h4>Corporis voluptates officia eiusmod</h4>
									<p>Consequuntur sunt aut quasi enim aliquam quae harum
										pariatur laboris nisi ut aliquip</p>
								</div>
							</div>
							<!-- End Icon Box -->

							<div class="col-xl-4" data-aos="fade-up" data-aos-delay="300">
								<div
									class="icon-box d-flex flex-column justify-content-center align-items-center">
									<i class="bi bi-gem"></i>
									<h4>Ullamco laboris ladore pan</h4>
									<p>Excepteur sint occaecat cupidatat non proident, sunt in
										culpa qui officia deserunt</p>
								</div>
							</div>
							<!-- End Icon Box -->

							<div class="col-xl-4" data-aos="fade-up" data-aos-delay="400">
								<div
									class="icon-box d-flex flex-column justify-content-center align-items-center">
									<i class="bi bi-inboxes"></i>
									<h4>Labore consequatur incidid dolore</h4>
									<p>Aut suscipit aut cum nemo deleniti aut omnis. Doloribus
										ut maiores omnis facere</p>
								</div>
							</div>
							<!-- End Icon Box -->

						</div>
					</div>

				</div>

			</div>
		</section>
		<!-- End Why Us Section -->

		<!-- ======= 어트랙션 추천 ======= -->
		<section id="chefs" class="chefs section-bg">
			<div class="container" data-aos="fade-up">

				<div class="section-header">
					<h2>어트랙션 추천</h2>
					<p>오늘은 <span>어떻게</span> 놀아볼까?</p>
				</div>

				<div class="row gy-4">
					<c:forEach var="attraction" items="${TopThreeAttraction}" varStatus="status">
						<div class="col-lg-4 col-md-6 d-flex align-items-stretch" data-aos="fade-up" data-aos-delay="100">
							<div class="chef-member" onclick="location.href= '/dd/user/activity/attraction/detail.do?seq=' + ${attraction.attraction_seq};">
								<div class="member-img">
									<img src="/dd/resources/files/activity/attraction/${attraction.img}" class="img-fluid" alt="${attraction.img}">
								</div>
								<div class="member-info">
									<h4>${attraction.name}</h4>
									<span>${status.index + 1}위</span>
									<p>${attraction.info}</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</section>
		<!-- 어트랙션 추천 끝 -->

		<!-- ======= Gallery Section ======= -->
		<section id="gallery" class="gallery section-bg">
			<div class="container" data-aos="fade-up">

				<div class="section-header">
					<h2>gallery</h2>
					<p>
						Check <span>Our Gallery</span>
					</p>
				</div>

				<div class="gallery-slider swiper">
					<div class="swiper-wrapper align-items-center">
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="resources/main/img/gallery/gallery-1.jpg"><img
								src="resources/main/img/gallery/gallery-1.jpg" class="img-fluid"
								alt=""></a>
						</div>
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="resources/main/img/gallery/gallery-2.jpg"><img
								src="resources/main/img/gallery/gallery-2.jpg" class="img-fluid"
								alt=""></a>
						</div>
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="resources/main/img/gallery/gallery-3.jpg"><img
								src="resources/main/img/gallery/gallery-3.jpg" class="img-fluid"
								alt=""></a>
						</div>
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="resources/main/img/gallery/gallery-4.jpg"><img
								src="resources/main/img/gallery/gallery-4.jpg" class="img-fluid"
								alt=""></a>
						</div>
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="resources/main/img/gallery/gallery-5.jpg"><img
								src="resources/main/img/gallery/gallery-5.jpg" class="img-fluid"
								alt=""></a>
						</div>
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="resources/main/img/gallery/gallery-6.jpg"><img
								src="resources/main/img/gallery/gallery-6.jpg" class="img-fluid"
								alt=""></a>
						</div>
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="resources/main/img/gallery/gallery-7.jpg"><img
								src="resources/main/img/gallery/gallery-7.jpg" class="img-fluid"
								alt=""></a>
						</div>
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="resources/main/img/gallery/gallery-8.jpg"><img
								src="resources/main/img/gallery/gallery-8.jpg" class="img-fluid"
								alt=""></a>
						</div>
					</div>
					<div class="swiper-pagination"></div>
				</div>

			</div>
		</section>
		<!-- End Gallery Section -->
		
		<!-- start reservation  -->
		 <div class="col-lg-12 col-md-6 text-center bg-dark" style="padding: 2rem;" id="reservationBox">
       	 	<h1 class="text-uppercase text-light mb-4">더 빠른 탑승을 위한 최고의 선택!</h1>
        	<a href="/dd/user/activity/attraction/view.do" class="btn btn-primary py-3 px-5" style="background-color: #ec2727; color:#fff; border: 1px solid #ec2727">어트랙션 예약</a>
    	</div>
		
	</main>
	<!-- end reservation -->