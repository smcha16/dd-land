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
</style>

	<main id="main">
	
	<!-- ======= Events Section ======= -->
	
	<style>
		.events .event-item .description {
			margin-bottom: 40px;
		}
	</style>

		<section id="events" class="events">
			<div class="container-fluid" data-aos="fade-up">

				<div class="section-header" style="margin-top: 58px;">
					<p>
						Share <span>Your Moments</span> In Our Amusement Park
					</p>
				</div>

				<div class="slides-3 swiper" data-aos="fade-up" data-aos-delay="100">
					<div class="swiper-wrapper">

						<a href="/dd/user/activity/festival/view.do" class="swiper-slide event-item d-flex flex-column justify-content-end" style="background-image: url(resources/main/img/events-1.jpg)">
							<div class="price align-self-start">Festival</div>
							<p class="description">
								크리스마스를 맞이하여 펼쳐지는 환상적인 퍼레이드 축제!
							</p>
						</a>
						<!-- End Event item -->

						<a href="/dd/user/communication/review/view.do" class="swiper-slide event-item d-flex flex-column justify-content-end" style="background-image: url(resources/main/img/events-2.jpg)">
						    <div class="price align-self-start">Review</div>
						    <p class="description">
						    	DD-Land에서 있었던 따뜻한 추억을 리뷰로 공유해주세요!
						    </p>
						</a>

						<!-- End Event item -->

						<a href="/dd/user/shop/restaurant/view.do" class="swiper-slide event-item d-flex flex-column justify-content-end" style="background-image: url(resources/main/img/events-3.jpg)">
							<div class="price align-self-start">Restaurant</div>
							<p class="description">
								맛있는 휴식, 달콤한 상상! 가족·친구·연인과 함께 즐겨보세요!
							</p>
						</a>
						<!-- End Event item -->

					</div>
					<div class="swiper-pagination" style="padding-bottom: 65px;"></div>
				</div>

			</div>
		</section>
		<!-- End Events Section -->
		
		<style>
			#time {
				position: relative;
				left: 450px;
			}
			#time span:first-child {
				font-size: 2rem;
				padding-left: 110px;
				padding-bottom: 10px;
			}
			#time span:not(:first-child) {
			    font-size: 4rem;
			}
			#interval {
				margin: 0 15px;
			}
		</style>
		
		<!-- ======= Stats Counter Section ======= -->
		<section id="stats-counter" class="stats-counter" style="padding: 30px 30px 20px;">
		    <div class="container" data-aos="zoom-out" style="white-space: nowrap;">
		
		        <div class="row gy-4">
		
		            <div class="col-lg-3 col-md-6">
		                <div id="time" class="stats-item text-center w-100 h-100">
		                	<span>운영시간</span>
		                    <span style="display: inline-block;" data-purecounter-start="0" data-purecounter-end="9" data-purecounter-duration="1.8" class="purecounter"></span>
		                    <span style="display: inline-block;">: 00</span>
		                    <span id="interval" style="display: inline-block;">-</span>
		                    <span style="display: inline-block;" data-purecounter-start="0" data-purecounter-end="22" data-purecounter-duration="1.5" class="purecounter"></span>
		                    <span style="display: inline-block;">: 00</span>
		                </div>
		            </div>
		
		            <!-- End Stats Item -->
		
		        </div>
		
		    </div>
		</section>
		<!-- End Stats Counter Section -->

		<!-- ======= Chefs Section ======= -->
		<section id="chefs" class="chefs section-bg" style="padding-top: 150px;">
			<div class="container" data-aos="fade-up">

				<div class="section-header">
					<h2>추천</h2>
					<p>
						오늘은 <span>어떻게</span> 놀아볼까?
					</p>
				</div>

				<div class="row gy-4">

					<div class="col-lg-4 col-md-6 d-flex align-items-stretch"
						data-aos="fade-up" data-aos-delay="100">
						<div class="chef-member">
							<div class="member-img">
								<img src="resources/main/img/chefs/chefs-1.jpg" class="img-fluid"
									alt="">
								<div class="social">
									<a href=""><i class="bi bi-twitter"></i></a> <a href=""><i
										class="bi bi-facebook"></i></a> <a href=""><i
										class="bi bi-instagram"></i></a> <a href=""><i
										class="bi bi-linkedin"></i></a>
								</div>
							</div>
							<div class="member-info">
								<h4>Walter White</h4>
								<span>Master Chef</span>
								<p>Velit aut quia fugit et et. Dolorum ea voluptate vel
									tempore tenetur ipsa quae aut. Ipsum exercitationem iure minima
									enim corporis et voluptate.</p>
							</div>
						</div>
					</div>
					<!-- End Chefs Member -->

					<div class="col-lg-4 col-md-6 d-flex align-items-stretch"
						data-aos="fade-up" data-aos-delay="200">
						<div class="chef-member">
							<div class="member-img">
								<img src="resources/main/img/chefs/chefs-2.jpg" class="img-fluid"
									alt="">
								<div class="social">
									<a href=""><i class="bi bi-twitter"></i></a> <a href=""><i
										class="bi bi-facebook"></i></a> <a href=""><i
										class="bi bi-instagram"></i></a> <a href=""><i
										class="bi bi-linkedin"></i></a>
								</div>
							</div>
							<div class="member-info">
								<h4>Sarah Jhonson</h4>
								<span>Patissier</span>
								<p>Quo esse repellendus quia id. Est eum et accusantium
									pariatur fugit nihil minima suscipit corporis. Voluptate sed
									quas reiciendis animi neque sapiente.</p>
							</div>
						</div>
					</div>
					<!-- End Chefs Member -->

					<div class="col-lg-4 col-md-6 d-flex align-items-stretch"
						data-aos="fade-up" data-aos-delay="300">
						<div class="chef-member">
							<div class="member-img">
								<img src="resources/main/img/chefs/chefs-3.jpg" class="img-fluid"
									alt="">
								<div class="social">
									<a href=""><i class="bi bi-twitter"></i></a> <a href=""><i
										class="bi bi-facebook"></i></a> <a href=""><i
										class="bi bi-instagram"></i></a> <a href=""><i
										class="bi bi-linkedin"></i></a>
								</div>
							</div>
							<div class="member-info">
								<h4>William Anderson</h4>
								<span>Cook</span>
								<p>Vero omnis enim consequatur. Voluptas consectetur unde
									qui molestiae deserunt. Voluptates enim aut architecto porro
									aspernatur molestiae modi.</p>
							</div>
						</div>
					</div>
					<!-- End Chefs Member -->

				</div>

			</div>
		</section>
		<!-- End Chefs Section -->

		<!-- ======= Gallery Section ======= -->
		<section id="gallery" class="gallery section-bg">
			<div class="container" data-aos="fade-up">

				<div class="section-header">
					<p>
						Check <span>Our Attraction</span>
					</p>
				</div>

				<div class="gallery-slider swiper">
					<div class="swiper-wrapper align-items-center">
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="/dd/resources/files/activity/attraction/너구리 대작전.jpg"><img
								src="/dd/resources/files/activity/attraction/너구리 대작전.jpg" class="img-fluid"
								alt=""></a>
						</div>
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="/dd/resources/files/activity/attraction/벼랑 기차.jpeg"><img
								src="/dd/resources/files/activity/attraction/벼랑 기차.jpeg" class="img-fluid"
								alt=""></a>
						</div>
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="/dd/resources/files/activity/attraction/우리는 빙글빙글2.jpeg"><img
								src="/dd/resources/files/activity/attraction/우리는 빙글빙글2.jpeg" class="img-fluid"
								alt=""></a>
						</div>
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="/dd/resources/files/activity/attraction/니모이야기2.jpeg"><img
								src="/dd/resources/files/activity/attraction/니모이야기2.jpeg" class="img-fluid"
								alt=""></a>
						</div>
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="/dd/resources/files/activity/attraction/후렌치레볼루션.jpeg"><img
								src="/dd/resources/files/activity/attraction/후렌치레볼루션.jpeg" class="img-fluid"
								alt=""></a>
						</div>
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="/dd/resources/files/activity/attraction/거북이 그네.jpeg"><img
								src="/dd/resources/files/activity/attraction/거북이 그네.jpeg" class="img-fluid"
								alt=""></a>
						</div>
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="/dd/resources/files/activity/attraction/지트란티스.jpeg"><img
								src="/dd/resources/files/activity/attraction/지트란티스.jpeg" class="img-fluid"
								alt=""></a>
						</div>
						<div class="swiper-slide">
							<a class="glightbox" data-gallery="images-gallery"
								href="/dd/resources/files/activity/attraction/우리는 빙글빙글1.jpeg"><img
								src="/dd/resources/files/activity/attraction/우리는 빙글빙글1.jpeg" class="img-fluid"
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
        	<a href="/ddstudio/activity/attraction.do" class="btn btn-primary py-3 px-5" style="background-color: #ec2727; color:#fff; border: 1px solid #ec2727">어트랙션 예약</a>
    	</div>
		
		
		<!-- end reservation -->