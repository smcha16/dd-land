<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
       background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("/dd/resources/files/guide/location.jpg") center center;
       background-size: cover;
       background-attachment: fixed;
    }
    .label {
	font-weight: bold;
	font-size: 1.5rem;
	margin: 20px 10px;
	color: #000;
}

.value {
	margin: 20px 10px;
	font-size: 1.2rem;
}

.value.location {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.location {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.location .label {
	margin-bottom: 0;
}
p {
	margin-top: 10px;
	margin-bottom: 1rem;
	color: darkgray;
}

#menu{
	padding-top:0;
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
              font-weight: 700;">오시는 길</div>
              <p>여러분~ 환상의 나라 DD랜드로 어서 오세요! </p>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- End Stats Counter Section -->

<!-- 위치 정보 -->
<section style="padding-bottom : 30px;">
	<div class="location">
		<div class="label">위치 정보</div>
		<p>* 스크롤과 드래그로 지도를 움직일 수 있습니다. *</p>
		<div class="value location">
			<div id="map" style="width: 950px; height: 435px;"></div>
		</div>
		<div>
			<button onclick="setBounds()" id="comeback">원래대로 돌아가기</button>
		</div>
	</div>
</section>

    <!-- ======= Menu Section ======= -->
    <section id="menu" class="menu">
      <div class="container" data-aos="fade-up">

        <div class="tab-content" data-aos-delay="900">

          <div class="tab-pane fade active show" id="menu-starters">

            <div class="munti-content-container">
              <div class="item">
                <div style="background-image: url('/dd/resources/files/guide/location/plane.jpg');"></div>
                <div>비행기 이용시</div>
              </div>
              <div class="item">
                <div style="background-image: url('/dd/resources/files/guide/location/ship.jpg');"></div>
                <div>배 이용시</div>
              </div>
            </div>
        </div><!-- End Starter Menu Content -->
      </div>
     </div>
    </section><!-- End Menu Section -->
    
    
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c089ee6f3d885cfbe52b2f15d8f3f531"></script>
    <script>
    /* 카카오 맵 */
    const container = document.getElementById('map'); // 지도를 담을 영역의 DOM 레퍼런스

    let options = {
      center: new kakao.maps.LatLng(33.361488, 126.529212), // 지도의 중심좌표.
      level: 10, // 지도의 레벨(확대, 축소 정도)
      draggable: true, 
      disableDoubleClick: false, 
      scrollwheel: true 
    };

    const map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴

    // 마커 출력
    let imageSrc = '/dd/resources/files/marker/놀이공원.png'; // 마커이미지의 주소
    const imageSize = new kakao.maps.Size(80, 80);
    const option = {};

    // 마커 설정
    const markerImg = new kakao.maps.MarkerImage(imageSrc, imageSize, option);

    const m1 = new kakao.maps.Marker({
      position: new kakao.maps.LatLng(33.3808, 126.5450),
      image: markerImg
    });

    // 마커 지도에 출력
    m1.setMap(map);

    // LatLngBounds 객체 초기화
    const defaultBounds = new kakao.maps.LatLngBounds();

    // 고정된 지도의 중심좌표와 레벨
    const fixedCenter = new kakao.maps.LatLng(33.361488, 126.529212);
    const fixedLevel = 10;

    // 이전의 지도 영역을 기억하기 위한 변수
    let previousBounds;

    // 버튼 클릭 시 실행할 함수
    function setBounds() {
      console.log('setBounds clicked');

      // 이전 지도 영역을 고정된 좌표로 설정
      previousBounds = new kakao.maps.LatLngBounds(
        new kakao.maps.LatLng(fixedCenter.getLat(), fixedCenter.getLng()),
        new kakao.maps.LatLng(fixedCenter.getLat(), fixedCenter.getLng())
      );

      console.log('Previous Bounds:', previousBounds.toString());

      // 이전의 레벨을 고정된 레벨로 설정
      options = {
        center: fixedCenter,
        level: fixedLevel,
        draggable: true,
        disableDoubleClick: false,
        scrollwheel: true
      };

      map.setLevel(fixedLevel);
      map.setCenter(fixedCenter);

      // 이전의 레벨과 중심좌표로 지도를 초기화
      map.setOptions(options);
    }

    // 버튼 이벤트 등록
    document.getElementById('comeback').addEventListener('click', setBounds);

    // 드래그 이벤트 등록
    kakao.maps.event.addListener(map, 'dragend', function () {
      // 이전 지도 영역을 기억
      previousBounds = map.getBounds();
    });

    // 지도 이벤트 등록
    kakao.maps.event.addListener(map, 'bounds_changed', function () {
      // 지도의 중심좌표를 확인하고, 원래대로 돌아갈 수 있는 경우에 버튼을 활성화
      if (
        map.getLevel() !== fixedLevel ||
        !previousBounds.equals(map.getBounds())
      ) {
        document.getElementById('comeback').disabled = false;
      } else {
        document.getElementById('comeback').disabled = true;
      }
    });

  </script>

<!-- 끝 -->