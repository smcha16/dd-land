<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>

<style>
    .stats-counter {
		background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("/dd/resources/files/communication/review.jpg") center center;
		background-size: cover;
		background-attachment: fixed;
    }
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
		content: "등록순";
		top: 27px;
		left: 33px;
	}
	
	.btn input::after {
		content: "추천순";
		top: 27px;
		right: 33px;
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
</style>

<!-- ======= Stats Counter Section ======= -->

<section id="stats-counter" class="stats-counter">
	<div id="pagetitle" class="container" data-aos="zoom-out">
		<div class="gy-4" style="justify-content: center; width: 100%;">
			<div class="col-lg-3 col-md-6" style="width: 100%;">
				<div class="stats-item text-center w-100 h-100">
					<div id="title">리뷰</div>
					<span class="btn btn-1">
						<input type="checkbox" id="order" name="order" onclick="check()" data-type="n">
						<label for="order"></label>
					</span>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- ======= Menu Section ======= -->

<section id="menu" class="menu">
	<div class="container" data-aos="fade-up">
		<div class="tab-content" data-aos="fade-up" data-aos-delay="300">
			<div class="tab-pane fade active show" id="menu-starters">
				<div class="munti-content-container">
					<c:forEach items="${list}" var="dto">
						<div class="item" onclick="location.href= '/dd/user/communication/review/detail.do?seq=${dto.review_seq}';">
							<div style="background-image: url('/dd/resources/files/communication/review/${dto.img}');"></div>
							<div>${dto.subject}</div>
							<div class="hidden-div"><%-- <c:out value="${dto.content}" /> --%>설명</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</section>

<script>
	var itemElements = document.querySelectorAll('.item');

	itemElements.forEach(function(item) {
		item.addEventListener('mouseover', function() {
			item.querySelector('.hidden-div').style.display = 'block';
		});

		item.addEventListener('mouseout', function() {
			item.querySelector('.hidden-div').style.display = 'none';
        });
    
	});

	const order = '${map.order}';

	if (order == 'y') {
		$('#order').prop('checked', true);
    } else {
        $('#order').prop('checked', false);
    }

	function check() {
		if ($('#order').prop('checked')) {
	        $('#order').data('type', 'y');
			location.href='/dd/user/communication/review/view.do?order=y';
	    } else {
        	$('#order').data('type', 'n');
			location.href='/dd/user/communication/review/view.do?order=n'
	    }
	}
</script>