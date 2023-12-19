<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  === list ver.3 (운영/운휴 토글버튼) Template -->
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" />

<!-- list 3 전용 style tag -->
<style>

*, *:before, *:after {-webkit-box-sizing: inherit;-moz-box-sizing: inherit;box-sizing: inherit}
html {-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;-ms-text-size-adjust:none; -webkit-text-size-adjust:none;height:100%}
body {height: 100%; font-family: 'Noto Sans KR', sans-serif; font-size: 16px; color: #454545;line-height: 1.5;background-color:#fff}
h1, h2, h3, h4, h5, h6 {font-weight:600}
html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite,
code, del, dfn, em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var, b, u, i, center, dl, dt, dd,
ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas,
details, embed, figure,  figcaption, footer, header, hgroup, menu, nav, output, ruby, section, summary, time, mark, audio, video, hr {margin:0; padding:0;border:0}
ul li, ol li {list-style: none}
em, address {font-style:normal}
a {text-decoration: none; cursor: pointer;color: #666}



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
    
    /* 텍스트 가운데 정렬 스타일 추가 */
    text-align: center;
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
	content: "일반혜택";
	left: 25px;
	
}

.btn input::after {
	content: "카드/통신사";
	right: 15px;
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
       background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("/dd/resources/files/benefit/benefit.jpg") center center;
       background-size: cover;
       background-attachment: fixed;
    }

#webtong_tab_type04 { display:table; width:100%; table-layout:fixed; border-left:1px solid #e7e7e7;position:relative;background-color: #f1f1f1}
#webtong_tab_type04 li { display:table-cell; height:50px}
#webtong_tab_type04 li a { display:block;position:relative;height:50px;border-top:1px solid #e7e7e7;border-bottom:1px solid #e7e7e7;line-height:50px;text-align:center;background-color: #fff}
#webtong_tab_type04 li a:after { content:''; display:block; position:absolute; top:0; right:0; bottom:0; width:1px; background:#e6e3df}
#webtong_tab_type04 li a:before, .webtong_tab_type04 li a:before{ content: '';display: block;position: absolute;left: 0;top: 0;height: 3px;width: 0;background-color: #fd7c82}
#webtong_tab_type04 li.on a:before, .webtong_tab_type04 li:hover a:before{ width: 100%}
#webtong_tab_type04 li.on a, .webtong_tab_type04 li:hover a {color: #fd7c82;font-weight: 600}

@media screen and (max-width: 780px) {
	#webtong_tab_type04 li {float: left;width: 33.33333%}
	#webtong_tab_type04 li:nth-child(n+4) {margin-top: -1px}
	#webtong_tab_type04:before {content: '';background-color: #e7e7e7;width: 1px;bottom: 0;right: 0;top: 0;position: absolute}
	#webtong_tab_type04:after {content: '';background-color: #e7e7e7;height: 1px;bottom: 0;right: 0;position: absolute;left: 0}
}
</style>

<!-- list3 Main Content -->
<!-- ======= Stats Counter Section ======= -->
<section id="stats-counter" class="stats-counter">
	<div id="pagetitle" class="container" data-aos="zoom-out">
		<div class="gy-4" style="justify-content: center; width: 100%;">

			<div class="col-lg-3 col-md-6" style="width: 100%;">
				<div class="stats-item text-center w-100 h-100">
					<div id="title">혜택</div>
					<!-- <span class="btn btn-1"> <input type="checkbox" id="close"
						value="close"> <label for="close"></label>
					</span> -->
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

					<ul class="tab tab_red" style="width: 1085px; margin-left:60px;"  id="webtong_tab_type04">
						<li id="sel01" class="two on"><a href="#tab01" id="selTab01">일반혜택</a></li>
						<li id="sel02" class="two"><a href="#tab02" id="selTab02">카드/통신사혜택</a></li>
					</ul>
					
					
					<div id="tab01" style="display: none;">
				<div id="main_box" style="margin-top:20px;">
					<div
						style="display: flex; flex-wrap: wrap; align-items: center; margin-left: 10px; justify-content: center;">
					
					<c:forEach items="${normalList}" var="dto">
						<div class="item" id="tab01" data-category="${dto.type}"  onclick="location.href= '/dd/user/pb/benefit/detail.do?seq=' + ${dto.benefit_seq};">
							<div
								style="background-image: url('/dd/resources/files/benefit/${dto.img}');"></div>
							<div>${dto.name}</div>
							<div>${dto.start_date}~${dto.end_date}</div>
							<div class="hidden-div">설명</div>
						</div>
					</c:forEach>
					</div>
				</div>
			</div>
			
		<div id="tab02" style="display: none;">
				<div id="main_box" style="margin-top:20px;">
					<div
						style="display: flex; flex-wrap: wrap; align-items: center; margin-left: 10px; justify-content: center;">
					
					<c:forEach items="${cardList}" var="dto">
						<div class="item" id="tab02" data-category="${dto.type}"  onclick="location.href= '/dd/user/pb/benefit/detail.do?seq=' + ${dto.benefit_seq};">
							<div
								style="background-image: url('/dd/resources/files/benefit/${dto.img}');"></div>
							<div>${dto.name}</div>
							<div>${dto.start_date}~${dto.end_date}</div>
							<div class="hidden-div">설명</div>
						</div>
					</c:forEach>
					</div>
				</div>
			</div>


				</div>
			</div>
		</div>
	</div>
</section>
<!-- End Menu Section -->

<!-- list3 전용 JavaScript -->



<script>

/* document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("close").addEventListener("click", function () {
        toggleLists();
    });

    function toggleLists() {
        var normalListElements = document.querySelectorAll('.item[data-category="normal"]');
        var cardListElements = document.querySelectorAll('.item[data-category="card"]');

        normalListElements.forEach(function (normalItem) {
            if (normalItem.style.display === 'none') {
                normalItem.style.display = 'block';
            } else {
                normalItem.style.display = 'none';
            }
        });

        cardListElements.forEach(function (cardItem) {
            if (cardItem.style.display === 'none') {
                cardItem.style.display = 'block';
            } else {
                cardItem.style.display = 'none';
            }
        });
    }
}); */



/* 

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
	
	 */
	
	
	
	
	
	
	showTab("tab01");

    /* document.getElementById("selTab00").addEventListener("click", function() {
        event.preventDefault();
        showTab("tab00");

        //document.getElementById("sel00").classList.add("on");
        document.getElementById("sel01").classList.remove("on");
        document.getElementById("sel02").classList.remove("on");
    }); */

    document.getElementById("selTab01").addEventListener("click", function() {
        event.preventDefault();
        showTab("tab01");

        document.getElementById("sel01").classList.add("on");
        document.getElementById("sel02").classList.remove("on");
        //document.getElementById("sel00").classList.remove("on");
    });

    document.getElementById("selTab02").addEventListener("click", function() {
        event.preventDefault();
        showTab("tab02");

        document.getElementById("sel02").classList.add("on");
        document.getElementById("sel01").classList.remove("on");
        //document.getElementById("sel00").classList.remove("on");
    });

    function showTab(tabId) {
        // 모든 탭 숨기기
       // document.getElementById("tab00").style.display = "none";
        document.getElementById("tab01").style.display = "none";
        document.getElementById("tab02").style.display = "none";

        // 선택한 탭 보이기
        document.getElementById(tabId).style.display = "block";
    } 
	
</script>