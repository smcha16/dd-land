<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>

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
	  width:1200px; 
	  height:300px;
	  aspect-ratio: 0.75;
	  padding: 0;
	  box-sizing: border-box;
	  min-width: 270px;
	  border: 1px solid #E1E1E1;
	  margin: 10px 45px 50px 45px;
	  border-radius: 10px;
	  /* transition: all 0.3s; */
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
	      display: block;
	      color: white;
	      position: absolute;
	      top: 0;
	      left: 0;
	      width: 100%;
	      height: 70%;
	      padding: 20px;
	      background-color: black;
	      /* opacity: 0.65; /* 투명도 조절 */ */
	      border-radius: 10px 10px 0 0;
	      z-index: 1; /* 다른 요소들보다 위에 위치하도록 설정 */
	}
	.stats-counter {
       background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("/dd/resources/files/guide/guide-map.jpg") center center;
       background-size: cover;
       background-attachment: fixed;
    }
    
    #main_box {
	border: 1px solid gainsboro;
	width: 1300px;
	text-align: center;
	padding-top: 5px;
	padding-bottom: 20px;
	margin: 0 auto;
}

#content_box {
	border: 1px solid black;
	align-items: center;
	width: 220px;
	height: 30px;
	padding: 5px;
	margin: 5px 10px;
	border-radius: 20px;
}
#content_box:hover{
	background-color: #CCC;
	cursor: pointer;
}
#convenient-map{
    display: flex;
    justify-content: center;
    margin-top: 60px;
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
    <!-- ======= Stats Counter Section ======= -->
    <section id="stats-counter" class="stats-counter">
      <div id="pagetitle" class="container" data-aos="zoom-out">
        <div class="gy-4" style="justify-content: center; width: 100%;">
          <div class="col-lg-3 col-md-6" style="width: 100%;">
            <div class="stats-item text-center w-100 h-100">
              <div id="title" style="font-size: 48px;
              display: block;
              color: #fff;
              font-weight: 700;">가이드맵</div>
              <p style="font-size:20px;">.D_D. 랜드를 한번에 보기! <i class="fa-regular fa-face-laugh-squint" style="color: #000;"></i></p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- End Stats Counter Section -->

    <!-- ======= Menu Section ======= -->
    <section id="menu" class="menu">
      <div class="container" data-aos="fade-up">
        <div class="tab-content" data-aos-delay="900">
          <div class="tab-pane fade active show" id="menu-starters" style="padding-bottom: 110px;">
            <div class="form-group">
            <div id="contents">
			<ul class="tab tab_red" id="webtong_tab_type04" style="width: 1300px; padding:0; margin-top:50px;">
				<li id="sel01" class="two on"><a href="#tab01" id="selTab01">어트랙션</a></li>
				<li id="sel02" class="two"><a href="#tab02" id="selTab02">편의시설</a></li>
				<li id="sel03" class="two"><a href="#tab03" id="selTab03">영화관</a></li>
				<li id="sel04" class="two"><a href="#tab04" id="selTab04">페스티벌</a></li>
				<li id="sel05" class="two"><a href="#tab05" id="selTab05">포토존</a></li>
				<li id="sel06" class="two"><a href="#tab06" id="selTab06">식당</a></li>
				<li id="sel07" class="two"><a href="#tab07" id="selTab07">기프트샵</a></li>
			</ul>
			</div>
			<div id="tab01" style="display: none;">
				<div id="main_box">
					<p style="text-align: center; font-weight:bold; margin-top:20px; padding-bottom: 20px;">현재 등록되어 있는 어트랙션입니다.</p>
					<hr>
					<div
						style="display: flex; flex-wrap: wrap; align-items: center; margin-left: 10px; justify-content: center;">
						<c:forEach items="${attrlist}" var="dto">
							<div id="content_box">
								<p style="text-align: center;">${dto.name}</p>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div id="tab02" style="display: none;">
				<div id="main_box">
					<p style="text-align: center; font-weight:bold; margin-top:20px; padding-bottom: 20px;">현재 등록되어 있는 편의시설입니다.</p>
					<hr>
					<div
						style="display: flex; flex-wrap: wrap; align-items: center; margin-left: 10px; justify-content: center;">
						<c:forEach items="${convlist}" var="dto">
							<div id="content_box">
								<p>${dto.name}</p>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div id="tab03" style="display: none;">
				<div id="main_box">
					<p style="text-align: center; font-weight:bold; margin-top:20px; padding-bottom: 20px;">현재 등록되어 있는 영화관입니다.</p>
					<hr>
					<div
						style="display: flex; flex-wrap: wrap; align-items: center; margin-left: 10px; justify-content: center;">
						<c:forEach items="${movielist}" var="dto">
							<div id="content_box">
								<p style="text-align: center;">${dto.name}</p>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div id="tab04" style="display: none;">
				<div id="main_box">
					<p style="text-align: center; font-weight:bold; margin-top:20px; padding-bottom: 20px;">현재 등록되어 있는 페스티벌입니다.</p>
					<hr>
					<div
						style="display: flex; flex-wrap: wrap; align-items: center; margin-left: 10px; justify-content: center;">
						<c:forEach items="${festlist}" var="dto">
							<div id="content_box">
								<p style="text-align: center;">${dto.name}</p>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div id="tab05" style="display: none;">
				<div id="main_box">
					<p style="text-align: center; font-weight:bold; margin-top:20px; padding-bottom: 20px;">현재 등록되어 있는 포토존입니다.</p>
					<hr>
					<div
						style="display: flex; flex-wrap: wrap; align-items: center; margin-left: 10px; justify-content: center;">
						<c:forEach items="${photolist}" var="dto">
							<div id="content_box">
								<p style="text-align: center;">${dto.name}</p>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div id="tab06" style="display: none;">
				<div id="main_box">
					<p style="text-align: center; font-weight:bold; margin-top:20px; padding-bottom: 20px;">현재 등록되어 있는 식당입니다.</p>
					<hr>
					<div
						style="display: flex; flex-wrap: wrap; align-items: center; margin-left: 10px; justify-content: center;">
						<c:forEach items="${foodlist}" var="dto">
							<div id="content_box">
								<p style="text-align: center;">${dto.name}</p>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<div id="tab07" style="display: none;">
				<div id="main_box">
					<p style="text-align: center; font-weight:bold; margin-top:20px; padding-bottom: 20px;">현재 등록되어 있는 기프트샵입니다.</p>
					<hr>
					<div
						style="display: flex; flex-wrap: wrap; align-items: center; margin-left: 10px; justify-content: center;">
						<c:forEach items="${giftlist}" var="dto">
							<div id="content_box">
								<p style="text-align: center;">${dto.name}</p>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>

		<div id="convenient-map">
			<div id="map" style="width: 1125px; height: 400px;"></div>
		</div>
            
        </div><!-- End Starter Menu Content -->
      </div>
     </div>
    </section><!-- End Menu Section -->
    
    
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c089ee6f3d885cfbe52b2f15d8f3f531"></script>
    <script>
    
	    const attrlist = [];  //어트랙션 마커들을 담을 배열
	    const convlist = [];  //편의시설 마커들을 담을 배열
	    const movielist=[];   //영화관 마커
	    const festlist=[];  //페스티벌 마커
	    const photolist=[];  //포토존 마커
	    const foodlist=[];  //식당 마커
	    const giftlist=[];  //기프트샵 마커
    
	    <c:forEach items="${attrlist}" var="dto" varStatus="status">
		    const a${status.count} = new kakao.maps.Marker({
		       position: new kakao.maps.LatLng(${dto.lat}, ${dto.lng})
		    });  //attrlist 위치 생성
		    attrlist.push(a${status.count});  //배열에 push
	    </c:forEach>
	    
	    <c:forEach items="${convlist}" var="dto" varStatus="status">
		    const c${status.count} = new kakao.maps.Marker({
		       position: new kakao.maps.LatLng(${dto.lat}, ${dto.lng})
		    });  //convlist 위치 생성
		    convlist.push(c${status.count});  //배열에 위치 push
	    </c:forEach>
		    
	    <c:forEach items="${movielist}" var="dto" varStatus="status">
		    const m${status.count} = new kakao.maps.Marker({
		       position: new kakao.maps.LatLng(${dto.lat}, ${dto.lng})
		    });  //movielist 위치 생성
		    movielist.push(m${status.count});  //배열에 위치 push
    	</c:forEach>
	    
   		<c:forEach items="${festlist}" var="dto" varStatus="status">
		    const f${status.count} = new kakao.maps.Marker({
		       position: new kakao.maps.LatLng(${dto.lat}, ${dto.lng})
		    });  //festlist 위치 생성
		    festlist.push(f${status.count});  //배열에 위치 push
		</c:forEach>
    
		<c:forEach items="${photolist}" var="dto" varStatus="status">
			const p${status.count} = new kakao.maps.Marker({
			   position: new kakao.maps.LatLng(${dto.lat}, ${dto.lng})
			});  //photolist 위치 생성
			photolist.push(p${status.count});  //배열에 위치 push
		</c:forEach>

		<c:forEach items="${foodlist}" var="dto" varStatus="status">
			const r${status.count} = new kakao.maps.Marker({
			   position: new kakao.maps.LatLng(${dto.lat}, ${dto.lng})
			});  //foodlist 위치 생성
			foodlist.push(r${status.count});  //배열에 위치 push
		</c:forEach>

		<c:forEach items="${giftlist}" var="dto" varStatus="status">
			const g${status.count} = new kakao.maps.Marker({
			   position: new kakao.maps.LatLng(${dto.lat}, ${dto.lng})
			});  //giftlist 위치 생성
			giftlist.push(g${status.count});  //배열에 위치 push
		</c:forEach>
	    
	    
		const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	    
	    const options = { //지도를 생성할 때 필요한 기본 옵션
	       center : new kakao.maps.LatLng(33.3808, 126.5450), //지도의 중심좌표.
	       level : 10, //지도의 레벨(확대, 축소 정도)
	       draggable : false, // 이동 금지
	       disableDoubleClick : true, // 더블클릭 확대 금지
	       scrollwheel : false  // 휠 확대/축소 금지
	    };
	
	    const map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	  	
	  	//
	    showTab("tab01", "attraction_marker2.png");  //페이지 초기화 값
		
		document.getElementById("selTab01").addEventListener("click", function() {  //tab01을 눌렀을때
			event.preventDefault();
	      	showTab("tab01", "attraction_marker2.png");
	      
	      	document.getElementById("sel01").classList.add("on");
	      	document.getElementById("sel02").classList.remove("on");
	      	document.getElementById("sel03").classList.remove("on");
	      	document.getElementById("sel04").classList.remove("on");
	      	document.getElementById("sel05").classList.remove("on");
	      	document.getElementById("sel06").classList.remove("on");
	      	document.getElementById("sel07").classList.remove("on");
	    });
	
	    document.getElementById("selTab02").addEventListener("click", function() {   //tab02을 눌렀을때
	    	event.preventDefault();
	      	showTab("tab02", "info.png");
	      
	      	document.getElementById("sel02").classList.add("on");
	      	document.getElementById("sel01").classList.remove("on");
	      	document.getElementById("sel03").classList.remove("on");
	      	document.getElementById("sel04").classList.remove("on");
	      	document.getElementById("sel05").classList.remove("on");
	      	document.getElementById("sel06").classList.remove("on");
	      	document.getElementById("sel07").classList.remove("on");
	    });
	    
	    document.getElementById("selTab03").addEventListener("click", function() {   //tab03을 눌렀을때
	    	event.preventDefault();
	      	showTab("tab03", "movie_marker.png");
	      
	      	document.getElementById("sel03").classList.add("on");
	      	document.getElementById("sel01").classList.remove("on");
	      	document.getElementById("sel02").classList.remove("on");
	      	document.getElementById("sel04").classList.remove("on");
	      	document.getElementById("sel05").classList.remove("on");
	      	document.getElementById("sel06").classList.remove("on");
	      	document.getElementById("sel07").classList.remove("on");
	    });
	    
	    document.getElementById("selTab04").addEventListener("click", function() {   //tab04을 눌렀을때
	    	event.preventDefault();
	      	showTab("tab04", "festival_marker3.png");
	      
	      	document.getElementById("sel04").classList.add("on");
	      	document.getElementById("sel01").classList.remove("on");
	      	document.getElementById("sel02").classList.remove("on");
	      	document.getElementById("sel03").classList.remove("on");
	      	document.getElementById("sel05").classList.remove("on");
	      	document.getElementById("sel06").classList.remove("on");
	      	document.getElementById("sel07").classList.remove("on");
	    });
	    
	    document.getElementById("selTab05").addEventListener("click", function() {   //tab05을 눌렀을때
	    	event.preventDefault();
	      	showTab("tab05", "photo_marker3.png");
	      
	      	document.getElementById("sel05").classList.add("on");
	      	document.getElementById("sel01").classList.remove("on");
	      	document.getElementById("sel03").classList.remove("on");
	      	document.getElementById("sel04").classList.remove("on");
	      	document.getElementById("sel02").classList.remove("on");
	      	document.getElementById("sel06").classList.remove("on");
	      	document.getElementById("sel07").classList.remove("on");
	    });
	    
	    document.getElementById("selTab06").addEventListener("click", function() {   //tab06을 눌렀을때
	    	event.preventDefault();
	      	showTab("tab06", "restaurant_marker2.png");
	      
	      	document.getElementById("sel06").classList.add("on");
	      	document.getElementById("sel01").classList.remove("on");
	      	document.getElementById("sel03").classList.remove("on");
	      	document.getElementById("sel04").classList.remove("on");
	      	document.getElementById("sel05").classList.remove("on");
	      	document.getElementById("sel02").classList.remove("on");
	      	document.getElementById("sel07").classList.remove("on");
	    });
	    
	    document.getElementById("selTab07").addEventListener("click", function() {   //tab07을 눌렀을때
	    	event.preventDefault();
	      	showTab("tab07", "shop_marker.png");
	      
	      	document.getElementById("sel07").classList.add("on");
	      	document.getElementById("sel01").classList.remove("on");
	      	document.getElementById("sel03").classList.remove("on");
	      	document.getElementById("sel04").classList.remove("on");
	      	document.getElementById("sel05").classList.remove("on");
	      	document.getElementById("sel06").classList.remove("on");
	      	document.getElementById("sel02").classList.remove("on");
	    });
	    
	    function resetMarkersOpacity() {
	    	  // 각 카테고리의 모든 마커의 투명도를 1로 설정
	    	  attrlist.forEach((marker) => {
	    	    marker.setOpacity(1);
	    	  });

	    	  convlist.forEach((marker) => {
	    	    marker.setOpacity(1);
	    	  });

	    	  movielist.forEach((marker) => {
	    	    marker.setOpacity(1);
	    	  });

	    	  festlist.forEach((marker) => {
	    	    marker.setOpacity(1);
	    	  });

	    	  photolist.forEach((marker) => {
	    	    marker.setOpacity(1);
	    	  });

	    	  foodlist.forEach((marker) => {
	    	    marker.setOpacity(1);
	    	  });

	    	  giftlist.forEach((marker) => {
	    	    marker.setOpacity(1);
	    	  });
	    	}

	    
	    function showTab(tabId, imgname) { 
	    	//마커 출력
	        let imageSrc; // 마커이미지의 주소
	        
	        resetMarkersOpacity();
	        
	        if (imgname === "attraction_marker2.png") {
	            imageSrc = '/dd/resources/files/marker/attraction_marker2.png';
	        } else if (imgname === "info.png") {
	            imageSrc = '/dd/resources/files/marker/info.png';
	        }else if (imgname === "movie_marker.png") {
	            imageSrc = '/dd/resources/files/marker/movie_marker.png';
	        }else if (imgname === "festival_marker3.png") {
	            imageSrc = '/dd/resources/files/marker/festival_marker3.png';
	        }else if (imgname === "photo_marker3.png") {
	            imageSrc = '/dd/resources/files/marker/photo_marker3.png';
	        }else if (imgname === "restaurant_marker2.png") {
	            imageSrc = '/dd/resources/files/marker/restaurant_marker2.png';
	        }else if (imgname === "shop_marker.png") {
	            imageSrc = '/dd/resources/files/marker/shop_marker.png';
	        } else {
	            // 기본 이미지 주소 또는 예외 처리
	            imageSrc = '/ddstudio/asset/image/marker/festival_marker4.png';
	        }
	        const imageSize = new kakao.maps.Size(40,40);
	        const option = {};
	        
	        //마커 설정 완료
	        const markerImg = new kakao.maps.MarkerImage(imageSrc, imageSize, option);
	         
	        if (tabId == 'tab01') {
	    		for (let i=0; i<attrlist.length; i++) {
	    			attrlist[i].setImage(markerImg);  //설정한 마커 넣어주고
	    			attrlist[i].setMap(map);  //지도에 찍기
	    		}
	    		for (let i=0; i<convlist.length; i++) {  //어트랙션 찍을때는 편의시설 마크 지워주기
	    			convlist[i].setMap(null);
	    		}
	    		for (let i=0; i<movielist.length; i++) {
	    			movielist[i].setMap(null);
	    		}
	    		for (let i=0; i<festlist.length; i++) {
	    			festlist[i].setMap(null);
	    		}
	    		for (let i=0; i<photolist.length; i++) {
	    			photolist[i].setMap(null);
	    		}
	    		for (let i=0; i<foodlist.length; i++) {
	    			foodlist[i].setMap(null);
	    		}
	    		for (let i=0; i<giftlist.length; i++) {
	    			giftlist[i].setMap(null);
	    		}
	    		
	    	} else if (tabId == 'tab02') { 
	    		for (let i=0; i<convlist.length; i++) {
	    			convlist[i].setImage(markerImg);
	    			convlist[i].setMap(map);
	    		}
	    		for (let i=0; i<attrlist.length; i++) {
	    			attrlist[i].setMap(null);
	    		}
	    		for (let i=0; i<movielist.length; i++) {
	    			movielist[i].setMap(null);
	    		}
	    		for (let i=0; i<festlist.length; i++) {
	    			festlist[i].setMap(null);
	    		}
	    		for (let i=0; i<photolist.length; i++) {
	    			photolist[i].setMap(null);
	    		}
	    		for (let i=0; i<foodlist.length; i++) {
	    			foodlist[i].setMap(null);
	    		}
	    		for (let i=0; i<giftlist.length; i++) {
	    			giftlist[i].setMap(null);
	    		}
	    	} else if (tabId == 'tab03'){
	    		for (let i=0; i<attrlist.length; i++) {
	    			attrlist[i].setMap(null);  
	    		}
	    		for (let i=0; i<convlist.length; i++) { 
	    			convlist[i].setMap(null);
	    		}
	    		for (let i=0; i<movielist.length; i++) {
	    			movielist[i].setImage(markerImg);  
	    			movielist[i].setMap(map);
	    		}
	    		for (let i=0; i<festlist.length; i++) {
	    			festlist[i].setMap(null);
	    		}
	    		for (let i=0; i<photolist.length; i++) {
	    			photolist[i].setMap(null);
	    		}
	    		for (let i=0; i<foodlist.length; i++) {
	    			foodlist[i].setMap(null);
	    		}
	    		for (let i=0; i<giftlist.length; i++) {
	    			giftlist[i].setMap(null);
	    		}
	    	} else if (tabId == 'tab04') {
	    		for (let i=0; i<attrlist.length; i++) {
	    			attrlist[i].setMap(null);  
	    		}
	    		for (let i=0; i<convlist.length; i++) { 
	    			convlist[i].setMap(null);
	    		}
	    		for (let i=0; i<movielist.length; i++) {
	    			movielist[i].setMap(null);
	    		}
	    		for (let i=0; i<festlist.length; i++) {
	    			festlist[i].setImage(markerImg);  
	    			festlist[i].setMap(map);
	    		}
	    		for (let i=0; i<photolist.length; i++) {
	    			photolist[i].setMap(null);
	    		}
	    		for (let i=0; i<foodlist.length; i++) {
	    			foodlist[i].setMap(null);
	    		}
	    		for (let i=0; i<giftlist.length; i++) {
	    			giftlist[i].setMap(null);
	    		}
	    	}else if (tabId == 'tab05') {
	    		for (let i=0; i<attrlist.length; i++) {
	    			attrlist[i].setMap(null);  
	    		}
	    		for (let i=0; i<convlist.length; i++) { 
	    			convlist[i].setMap(null);
	    		}
	    		for (let i=0; i<movielist.length; i++) {
	    			movielist[i].setMap(null);
	    		}
	    		for (let i=0; i<festlist.length; i++) {
	    			festlist[i].setMap(null);
	    		}
	    		for (let i=0; i<photolist.length; i++) {
	    			photolist[i].setImage(markerImg);  
	    			photolist[i].setMap(map);
	    		}
	    		for (let i=0; i<foodlist.length; i++) {
	    			foodlist[i].setMap(null);
	    		}
	    		for (let i=0; i<giftlist.length; i++) {
	    			giftlist[i].setMap(null);
	    		}
	    	}else if (tabId == 'tab06') {
	    		for (let i=0; i<attrlist.length; i++) {
	    			attrlist[i].setMap(null);  
	    		}
	    		for (let i=0; i<convlist.length; i++) { 
	    			convlist[i].setMap(null);
	    		}
	    		for (let i=0; i<movielist.length; i++) {
	    			movielist[i].setMap(null);
	    		}
	    		for (let i=0; i<festlist.length; i++) {
	    			festlist[i].setMap(null);
	    		}
	    		for (let i=0; i<photolist.length; i++) {
	    			photolist[i].setMap(null);
	    		}
	    		for (let i=0; i<foodlist.length; i++) {
	    			foodlist[i].setImage(markerImg);  
	    			foodlist[i].setMap(map);
	    		}
	    		for (let i=0; i<giftlist.length; i++) {
	    			giftlist[i].setMap(null);
	    		}
	    	}else if (tabId == 'tab07') {
	    		for (let i=0; i<attrlist.length; i++) {
	    			attrlist[i].setMap(null);  
	    		}
	    		for (let i=0; i<convlist.length; i++) { 
	    			convlist[i].setMap(null);
	    		}
	    		for (let i=0; i<movielist.length; i++) {
	    			movielist[i].setMap(null);
	    		}
	    		for (let i=0; i<festlist.length; i++) {
	    			festlist[i].setMap(null);
	    		}
	    		for (let i=0; i<photolist.length; i++) {
	    			photolist[i].setMap(null);
	    		}
	    		for (let i=0; i<foodlist.length; i++) {
	    			foodlist[i].setMap(null);
	    		}
	    		for (let i=0; i<giftlist.length; i++) {
	    			giftlist[i].setImage(markerImg);  
	    			giftlist[i].setMap(map);
	    		}
	    	}
	       
	    	
	    	// 모든 탭 숨기기
	        document.getElementById("tab01").style.display = "none";
	        document.getElementById("tab02").style.display = "none";
	        document.getElementById("tab03").style.display = "none";
	        document.getElementById("tab04").style.display = "none";
	        document.getElementById("tab05").style.display = "none";
	        document.getElementById("tab06").style.display = "none";
	        document.getElementById("tab07").style.display = "none";
	
	        // 선택한 탭 보이기
	        document.getElementById(tabId).style.display = "block";
	    	 
	      }
	    
	    document.addEventListener('DOMContentLoaded', function () {
	    	  const contentBoxes = document.querySelectorAll('#content_box');
			
	    	  contentBoxes.forEach((box, index) => {
	    	    box.addEventListener('click', function (event) {
	    	      const itemId = this.id;
	
	    	      contentBoxes.forEach((box) => {
	    	        box.style.backgroundColor = 'white'; // 모든 박스의 배경색 초기화
	    	      });
	    	      this.style.backgroundColor = 'gold'; // 클릭된 박스의 배경색 변경
	
	    	      // 클릭된 리스트 아이템에 대한 추가 동작
	    	      setMarkersOpacity(index, 1);
	    	    });
	    	  });
	    	});
	
	    	function setMarkersOpacity(clickedIndex, opacity) {
	    	  // 모든 마커의 투명도를 초기화
	    	  attrlist.forEach((marker) => {
	    	    marker.setOpacity(0.3);
	    	  });
	    	  convlist.forEach((marker) => {
	    	    marker.setOpacity(0.3);
	    	  });
	    	  movielist.forEach((marker) => {
		    	    marker.setOpacity(0.3);
		      });
	    	  festlist.forEach((marker) => {
		    	    marker.setOpacity(0.3);
		      });
	    	  photolist.forEach((marker) => {
		    	    marker.setOpacity(0.3);
		      });
	    	  foodlist.forEach((marker) => {
		    	    marker.setOpacity(0.3);
		      });
	    	  giftlist.forEach((marker) => {
		    	    marker.setOpacity(0.3);
		      });
	
	    	  // 클릭된 인덱스에 해당하는 마커의 투명도를 조절
	    	  if (document.getElementById('sel01').classList.contains('on')) {
	    	    // 어트랙션 탭이 선택된 경우
	    	    if (attrlist[clickedIndex]) {
	    	      attrlist[clickedIndex].setOpacity(opacity);
	    	    }
	    	  } else if (document.getElementById('sel02').classList.contains('on')) {
	    	    // 편의시설 탭이 선택된 경우
	    	    clickedIndex -= attrlist.length;
	    	    if (convlist[clickedIndex]) {
	    	      convlist[clickedIndex].setOpacity(opacity);
	    	    }
	    	  } else if (document.getElementById('sel03').classList.contains('on')) {
	    		  clickedIndex -= (attrlist.length + convlist.length);
		    	  if (movielist[clickedIndex]) {
		    	    movielist[clickedIndex].setOpacity(opacity);
		    	  }
		    	}else if (document.getElementById('sel04').classList.contains('on')) {
			    	clickedIndex -= (attrlist.length + convlist.length + movielist.length);
			    	if (festlist[clickedIndex]) {
			    	   festlist[clickedIndex].setOpacity(opacity);
			    	 }
			    }else if (document.getElementById('sel05').classList.contains('on')) {
				    clickedIndex -= (attrlist.length+convlist.length + movielist.length + festlist.length);
				    if (photolist[clickedIndex]) {
				    	 photolist[clickedIndex].setOpacity(opacity);
				    }
				}else if (document.getElementById('sel06').classList.contains('on')) {
					clickedIndex -= (attrlist.length+convlist.length + movielist.length + festlist.length + photolist.length);
					if (foodlist[clickedIndex]) {
					    foodlist[clickedIndex].setOpacity(opacity);
					}
				}else if (document.getElementById('sel07').classList.contains('on')) {
					clickedIndex -= (attrlist.length+convlist.length + movielist.length + festlist.length + photolist.length + foodlist.length);
					if (giftlist[clickedIndex]) {
						giftlist[clickedIndex].setOpacity(opacity);
					}
				}
	    	}
   

  </script>

<!-- 끝 -->