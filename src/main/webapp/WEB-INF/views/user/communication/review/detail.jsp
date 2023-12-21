<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
	.section-header {
	    padding-bottom: 0;
	}
	<c:if test="${dto.imgList[0].img != null}">
	    .section-header {
			padding-bottom: 30px;
	    }
  	</c:if>
	section h1 {
		font-size: 48px;
	    text-align: center;
	}
	#info {
		font-size: 1.05rem;
		text-align: center;
		margin-top: 40px;
	}
	#person, .readcount, #date span {
		font-weight: bold;
		color: #444;
		margin-left: 7px;
	}
	#person {
		margin-right: 50px !important;
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
	.slick-prev {
		top: 50%;
		left: 20px;
	}
	.slick-next {
		top: 50%;
		right: 20px;
	}
	#review-detail {
		min-height: 400px;
		text-align: center;
	}
	#date {
		width: 72%;
		text-align: left;
		margin: 50px auto 15px;
	}
	#visit-date {
		margin-right: 30px !important;
	}
	#content {
		width: 75%;
	    font-size: 1.1rem;
	    color: #555;
	    text-align: left;
	    padding: 30px 15px 0;
	    border-top: 1px solid #333;
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
			<div id="info">
				<i class="bi bi-person-fill"></i><span id="person">${dto.name} (${dto.email})</span>
				<i class="bi bi-eye-fill"></i><span class="readcount">조회수</span><span class="readcount">${dto.readcount}</span>
			</div>
		</div>
		
		<c:if test="${dto.imgList[0].img != null}">
			<div class="image-slider">
				<c:forEach items="${dto.imgList}" var="dto">
					<div>
						<img src="/dd/resources/files/communication/review/${dto.img}" alt="Image">
					</div>
				</c:forEach>
			</div>
		</c:if>
		
	</div>
</section>
	
<!-- ======= Main Section ======= -->

<main id="review-detail">
	<div id="date">
        <i class="bi bi-calendar-check"></i><span>방문일</span><span id="visit-date">${fn:substring(dto.visit_date, 0, 10)}</span>
        <i class="bi bi-calendar-check"></i><span>등록일</span><span id="regdate">${fn:substring(dto.regdate, 0, 10)}</span>
    </div>
    
	<div id="content">${dto.content}</div>
	
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