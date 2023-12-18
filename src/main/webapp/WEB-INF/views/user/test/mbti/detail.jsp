<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	width: 40%;
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
	padding:10px;
	height: auto;
	vertical-align: baseline;
}

.munti-content-container {
    justify-content: center;
}

.item>div:nth-child(1) {
	height: 100%;
	background-color: transparent;
	background-size: cover;
	background-repeat: no-repeat;
	border-radius: 10px 10px 0 0;
}

.stats-counter {
	background-image: url('/dd/resources/files/test/mbti/mbti_title.png');
}

#overlay-div {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 458px;
	background-color: black;
	opacity: 0.45;
	z-index: 0;
}
</style>

<section id="stats-counter" class="stats-counter">
	<div id="overlay-div"></div>
	<div id="pagetitle" class="container" data-aos="zoom-out">
		<div class="gy-4" style="justify-content: center; width: 100%;">

			<div class="col-lg-3 col-md-6" style="width: 100%;">
				<div class="stats-item text-center w-100 h-100">
					<div id="title" style="font-size: 48px; display: block; color: #fff; font-weight: 700;">${dto.name}</div>
					<p>${dto.result}</p>
				</div>
			</div>
		</div>
	</div>
</section>

<section id="menu" class="menu">
	<div class="container" data-aos="fade-up">
		<div class="tab-content" data-aos-delay="900">
			<div class="tab-pane fade active show" id="menu-starters">
				<div class="munti-content-container">
					<div class="item">
						<img src="/dd/resources/files/test/mbti/${dto.mbti_img}" alt="출처: #어반브러시 #타미">
					</div>
					<div class="item">
						<img src="/dd/resources/files/activity/attraction/${dto.attraction_img}">
					</div>
					<div class="item">
						<img src="/dd/resources/files/test/worldcup/course/${dto.course_img}">
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
