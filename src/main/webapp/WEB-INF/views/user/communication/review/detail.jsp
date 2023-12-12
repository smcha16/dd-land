<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>

<!-- Slick -->
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>

<style>
	* {
		font-family: 'SUIT-Regular';
    }
	section:first-of-type {
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    padding-top: 140px;
	    padding-bottom: 50px !important;
	}
	section h1 {
		font-size: 48px;
	    text-align: center;
	}
	#date {
		font-size: 1.05rem;
		text-align: center;
		margin-top: 40px;
	}
	#date span {
		font-weight: bold;
		color: #444;
		margin-left: 7px;
		margin-right: 12px;
	}
	#visit-date, #regdate {
		margin-right: 50px !important;
	}
	.section-image {
		display: flex;
		justify-content: center;
		padding-bottom: 30px;
	}
	.section-image > img {
		border-radius: 15px;
	}
	.image-slider {
		width: 700px;
		height: 350px;
		margin: 0 auto;
    }
	.image-slider div {
		width: 700px;
		height: 350px;
		margin: 0 auto;
		overflow: hidden;
	}
	.image-slider img {
		width: 100%;
		max-height: 100%;
		border-radius: 15px;
		object-fit: cover;
	}
	.slick-prev, .slick-next {
		border: 0;
		background: transparent;
		z-index: 100;
		position: absolute;
	}
	.slick-prev, .slick-next {
		top: 50%;
		left: 20px;
	}
	#review-detail {
		min-height: 400px;
		text-align: center;
	}
	#content {
		width: 75%;
	    font-size: 1.1rem;
	    color: #555;
	    text-align: left;
	    padding-top: 50px;
	    margin: 0 auto;
	}
	#button button {
		background-color: #CE1212;
		border-color: #CE1212;
		margin: 80px 0 50px;
	}
	#button i {
		margin-right: 7px;
	}
</style>

<!-- ======= Title Section ======= -->

<section id="gallery" class="gallery section-bg">
	<div class="container" data-aos="zoom-out">
	
		<div class="section-header">
			<h1><c:out value="${dto.subject}" /></h1>
			<div id="date">
	            <i class="bi bi-calendar-check"></i><span>방문일</span><span id="visit-date">${dto.visit_date}</span>
	            <i class="bi bi-calendar-check"></i><span>등록일</span><span id="regdate">${dto.regdate}</span>
	            <i class="bi bi-eye-fill"></i><span>조회수</span><span id="readcount">${dto.readcount}</span>
	        </div>
		</div>
		
		<div class="image-slider">
			<c:forEach items="${dto.imgList}" var="dto">
				<div>
					<img src="/dd/resources/files/communication/review/${dto.img}" alt="Image">
				</div>
			</c:forEach>
		</div>
		
	</div>
</section>
	
<!-- ======= Main Section ======= -->

<main id="review-detail">
	<div id="content"><c:out value="${dto.content}" /></div>
	
	<div id="button">
		<button type="button" id="back-button" class="btn btn-primary"><i class="bi bi-list"></i>목록</button>
	</div>
</main>

<script type="text/javascript" src="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

<script>
	$('#back-button').click(function () {
		location.href='/dd/user/communication/review/view.do';
	});

	$('.image-slider').slick({
		variableWidth : true,
		infinite : true,
		autoplay : true,
		autoplaySpeed : 5000,
		pauseOnHover : true,
		arrows : true,
		prevArrow : "<button type='button' class='slick-prev'>&#10094;</button>",
		nextArrow : "<button type='button' class='slick-next'>&#10095;</button>",
		draggable : true
	});
</script>