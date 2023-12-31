<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- user > test > mbti > view.jsp -->
<style>
#title {
	font-size: 48px;
	display: block;
	color: #fff;
	font-weight: 700;
}

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

.item img {
    width: 100%;
    height: 300px;
    object-fit: cover;
    border-radius: 10px 10px 0 0;
}

.item:hover {
	cursor: pointer;
	box-shadow: 12px 12px 17px rgba(0, 0, 0, 0.20);
}

.item > div:nth-child(1) {
	height: 70%;
	background-color: transparent;
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
	border-radius: 10px 10px 0 0;
	position: relative;
}

.item > div:nth-child(1) > img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    border-radius: 10px 10px 0 0;
}

.item > div:nth-child(2) {
	height: 30%;
	display: flex;
	flex-direction: column;
	padding: 20px;
	font-size: 1.3rem;
	font-weight: bold;
	background: transparent;
	border-radius: 0 0 10px 10px;
	justify-content: center;
   	align-items: center;
}

.hidden-div {
	display: none;
	color: white;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 300px;
	padding: 20px;
	background-color: black;
	opacity: 0.65;
	border-radius: 10px 10px 0 0;
	z-index: 1;
}

.stats-counter {
	background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("/dd/resources/files/test/mbti/mbti_title.png") center center;
	background-size: cover;
	background-attachment: fixed;
}
</style>

<!-- Main Start -->
<section id="stats-counter" class="stats-counter">
	<div id="pagetitle" class="container" data-aos="zoom-out">
		<div class="gy-4" style="justify-content: center; width: 100%;">
			<div class="col-lg-3 col-md-6" style="width: 100%;">
				<div class="stats-item text-center w-100 h-100">
					<div id="title">MBTI 추천</div>
					<p>내 성격 유형에 맞는 어트랙션과 코스를 찾아보세요!</p>
				</div>
			</div>
		</div>
	</div>
</section>

<section id="menu" class="menu">
	<div class="container" data-aos="fade-up">
		<div class="tab-content" data-aos="fade-up" data-aos-delay="300">
			<div class="tab-pane fade active show" id="menu-starters">
				<div class="multi-content-container">
					<c:forEach items="${listMBTI}" var="dto">
						<div class="item" onclick="location.href= '/dd/user/test/mbti/detail.do?mbti_seq=' + ${dto.mbti_seq};">
							<img src="/dd/resources/files/test/mbti/${dto.mbti_img}" alt="출처: #어반브러시 #타미">
							<div>${dto.name}</div>
							<div class="hidden-div">${dto.result}</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 페이징 -->
	<nav id="page-bar" aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<c:forEach begin="1" end="${map.totalPages}" varStatus="pageStatus">
				<c:choose>
					<c:when test="${pageStatus.index == currentPage}">
						<li class="page-item active"><span class="page-link">${pageStatus.index}</span></li>
					</c:when>
					<c:otherwise>
						<li class="page-item"><a class="page-link"
							href="/dd/user/test/mbti/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
	</nav>
</section>

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
<!-- Main End -->
