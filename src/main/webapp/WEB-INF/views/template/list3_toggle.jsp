<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--  === list ver.3 (운영/운휴 토글버튼) Template -->
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>

<!-- list 3 전용 style tag -->
<style>
	#pagetitle {
		margin-top: 70px;
	}
	
	#title {
		font-size: 48px;
		display: block;
		color: #fff;
		font-weight: 700;
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
		width: 25.5%;
		aspect-ratio: 0.75;
		padding: 0;
		box-sizing: border-box;
		min-width: 270px;
		border: 1px solid #E1E1E1;
		margin: 10px 45px 50px 45px;
		border-radius: 10px;
		transition: all 0.3s;
	}
	
	.item:hover {
		cursor: pointer;
		box-shadow: 12px 12px 17px rgba(0, 0, 0, 0.20);
	}
	
	.item>div:nth-child(1) {
		height: 70%;
		background-color: transparent;
		background-size: cover;
		background-position: center;
		background-repeat: no-repeat;
		border-radius: 10px 10px 0 0;
	}
	
	.item>div:nth-child(2) {
		height: 30%;
		display: flex;
		flex-direction: column;
		padding: 20px;
		font-size: 1.3rem;
		font-weight: bold;
		background: transparent;
		border-radius: 0 0 10px 10px;
	}
	
	.hidden-div {
		display: none;
		color: white;
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 70%;
		padding: 20px;
		background-color: black;
		opacity: 0.65; /* 투명도 조절 */
		border-radius: 10px 10px 0 0;
		z-index: 1; /* 다른 요소들보다 위에 위치하도록 설정 */
	}
	
	/* 운영/운휴 셀렉박스 */
	.btn {
		position: relative;
		height: 56px;
		font-size: 16px !important;
		border: 0;
		cursor: default;
		width: 224px;
		margin: 0 auto;
	}
	
	.btn input {
		position: relative;
		width: 200px;
		height: 50px;
		border-radius: 25px;
		outline: none;
		cursor: pointer;
		appearance: none;
		font-weight: bold;
		box-shadow: 1px 6px 11px #000;
	}
	
	.btn input::before, .btn input::after {
		z-index: 2;
		position: absolute;
		top: 50%;
		transform: translateY(-50%);
		color: #111;
	}
	
	.btn input::before {
		content: "운영";
		left: 40px;
	}
	
	.btn input::after {
		content: "운휴";
		right: 40px;
	}
	
	.btn label {
		z-index: 1;
		position: absolute;
		top: 15px;
		bottom: 10px;
		border-radius: 20px;
		width: 86px;
	}
	
	.btn.btn-1 input {
		transition: 0.2s -0.1s;
	}
	
	.btn.btn-1 input:not(:checked) {
		background: rgba(255, 255, 255, 0.6);
	}
	
	.btn.btn-1 input:not(:checked):before {
		color: #111;
		transition: color 0.5s 0.2s;
	}
	
	.btn.btn-1 input:not(:checked):after {
		color: #111;
		transition: color 0.5s;
	}
	
	.btn.btn-1 input:not(:checked)+label {
		left: 24px;
		/* background: #D2AB21; */
		background: rgba(215, 62, 62, .7);
		transition: left 0.5s, right 0.4s 0.2s;
		bottom: 8px;
	}
	
	.btn.btn-1 input:checked {
		/* background: rgba(215, 62, 62, .7); */
		background: rgba(255, 255, 255, 0.6)
	}
	
	.btn.btn-1 input:checked::before {
		color: #111;
		transition: color 0.5s;
	}
	
	.btn.btn-1 input:checked::after {
		color: #1E1E1E;
		transition: color 0.5s 0.2s;
	}
	
	.btn.btn-1 input:checked+label {
		left: 114px;
		background: rgba(215, 62, 62, .7);
		transition: left 0.4s 0.2s, right 0.5s, background 0.35s -0.1s;
	}
	
	/* list photo 변경 */
    .stats-counter {

      background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("assets/img/barcelona-3960566_1280.jpg") center center;
      background-size: cover;
      padding: 100px 0;
      background-attachment: fixed;
    }
    
</style>

<!-- list3 Main Content -->
<!-- ======= Stats Counter Section ======= -->
<section id="stats-counter" class="stats-counter">
	<div id="pagetitle" class="container" data-aos="zoom-out">
		<div class="gy-4" style="justify-content: center; width: 100%;">

			<div class="col-lg-3 col-md-6" style="width: 100%;">
				<div class="stats-item text-center w-100 h-100">
					<div id="title">Attraction</div>
					<span class="btn btn-1"> <input type="checkbox" id="close"
						value="close"> <label for="close"></label>
					</span>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- End Stats Counter Section -->
<!-- ======= Menu Section ======= -->
<section id="menu" class="menu">
	<div class="container" data-aos="fade-up">
		<div class="tab-content" data-aos="fade-up" data-aos-delay="300">
			<div class="tab-pane fade active show" id="menu-starters">
				<div class="munti-content-container">
					<div class="item">
						<div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
						<div>어트랙션명</div>
						<div class="hidden-div">설명</div>
					</div>
					<div class="item">
						<div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
						<div>어트랙션명</div>
						<div class="hidden-div">설명</div>
					</div>
					<div class="item">
						<div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
						<div>어트랙션명</div>
						<div class="hidden-div">설명</div>
					</div>
					<div class="item">
						<div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
						<div>어트랙션명</div>
						<div class="hidden-div">설명</div>
					</div>
					<div class="item">
						<div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
						<div>어트랙션명</div>
						<div class="hidden-div">설명</div>
					</div>
					<div class="item">
						<div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
						<div>어트랙션명</div>
						<div class="hidden-div">설명</div>
					</div>
					<div class="item">
						<div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
						<div>어트랙션명</div>
						<div class="hidden-div">설명</div>
					</div>
					<div class="item">
						<div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
						<div>어트랙션명</div>
						<div class="hidden-div">설명</div>
					</div>
					<div class="item">
						<div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
						<div>어트랙션명</div>
						<div class="hidden-div">설명</div>
					</div>
					<div class="item">
						<div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
						<div>어트랙션명</div>
						<div class="hidden-div">설명</div>
					</div>
					<div class="item">
						<div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
						<div>어트랙션명</div>
						<div class="hidden-div">설명</div>
					</div>
					<div class="item">
						<div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
						<div>어트랙션명</div>
						<div class="hidden-div">설명</div>
					</div>
					<div class="item">
						<div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
						<div>어트랙션명</div>
						<div class="hidden-div">설명</div>
					</div>
					<div class="item">
						<div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
						<div>어트랙션명</div>
						<div class="hidden-div">설명</div>
					</div>
					<div class="item">
						<div style="background-image: url('assets/img/뛰뛰빵빵.jpeg');"></div>
						<div>어트랙션명</div>
						<div class="hidden-div">설명</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- End Menu Section -->

<!-- list3 전용 JavaScript -->
<script>
	var itemElements = document.querySelectorAll('.item');

	itemElements.forEach(function(item) {
		
		item.addEventListener('mouseover', function() {
			// 마우스 오버 시 hidden-div를 보이게 변경
			item.querySelector('.hidden-div').style.display = 'block';
		});

		item.addEventListener('mouseout', function() {
			// 마우스 아웃 시 hidden-div를 다시 숨김
			item.querySelector('.hidden-div').style.display = 'none';
        });
    
	});
</script>