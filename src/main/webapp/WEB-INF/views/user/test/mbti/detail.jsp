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

.multi-content-container {
	display: flex;
	padding: 0 !important;
	align-items: stretch;
	justify-content: center;
	text-align: center;
}

.item {
    position: relative;
    width: 100%;
    padding: 0;
    box-sizing: border-box;
    min-width: 270px;
    margin: 0px 20px 0px 20px;
    border-radius: 10px;
    transition: all 0.3s;
    display: flex;
    flex-direction: column;
    cursor: pointer;
}

.item img {
	width: 100%;
	height: 100%;
	object-fit: cover;
	border-radius: 15px;
	flex-grow: 1;
}

.item .item-content {
    position: relative;
}

.item .message {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -100%);
    color: #fff;
    text-align: center;
    font-weight: bold;
    font-size: 18px;
    pointer-events: none;
    opacity: 0;
    transition: opacity 0.3s;
}

.item:hover .message {
    opacity: 1;
}

.item:hover img {
    filter: brightness(70%);
}

.item>div {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    border: 0 !important;
}

.item>div:nth-child(1) {
	height: 100%;
	background-color: transparent;
	background-size: cover;
	background-repeat: no-repeat;
	border-radius: 10px 10px 0 0;
}

.multi-content-container .item {
    flex: 1;
}

.text-container {
    text-align: center;
    font-size: 28px;
    font-weight: bold;
    margin-bottom: 35px;
    color: #333;
}

.result-name {
    position: relative;
    font-size: 22px;
    margin-top: 10px;
    color: #555;
    font-weight: bold;
    margin-bottom: 3px;
    background-color: #f4f4f4;
    display: inline-block;
    padding: 5px 10px;
    border-radius: 8px;
}

#message-container {
    position: fixed;
    background-color: rgba(255, 255, 255, 0.8);
    padding: 10px;
    border-radius: 5px;
    display: none;
    pointer-events: none;
}

underline {
	border-bottom: 2px solid #555;
    padding-bottom: 2px;
}

underline:hover {
    color: #FF5733;
}

.stats-counter {
	background-image: url('/dd/resources/files/test/mbti/mbti_title.png');
	background-size: cover;
	background-attachment: fixed;
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
			
					<div class="text-container">
					    ${dto.name}에게 추천하는 <underline>어트랙션</underline>과 <underline>코스</underline>입니다.
					</div>
				
				<div class="multi-content-container">
					<div class="item" onclick="redirectToAttractionDetail(${dto.attraction_seq})">
				        <img src="/dd/resources/files/activity/attraction/${dto.attraction_img}">
				        <div class="result-name">${dto.attraction_name}</div>
				        <div class="message">클릭 시 해당 어트랙션 페이지로 이동합니다.</div>
					</div>
					
					<div class="item">
						<img src="/dd/resources/files/test/worldcup/course/${dto.course_img}">
						<div class="result-name">${dto.course_name}
					</div></div>
				</div>
			</div>
		</div>
	</div>
</section>

<script>
	function redirectToAttractionDetail(seq) {
	    var url = '/dd/user/activity/attraction/detail.do?seq=' + seq;
	    
	    window.location.href = url;
	}
</script>
