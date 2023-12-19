<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>

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
	  height: 20%;
	  background-color: transparent;
	  background-size: cover;
	  background-position: center;
	  background-repeat: no-repeat;
	  border-radius: 10px 10px 0 0;
	  font-size: 24px;
	  text-align : center;
	  padding-top: 10px;
	  background-color: mistyrose;
	}
	
	.item>div:nth-child(2) {
	  height: 80%;
	  display: flex;
	  flex-direction: column;
	  flex-wrap: wrap;
	  align-items:left;
	  justify-content: center;
	  padding: 20px;
	  font-size: 1.3rem;
	  font-weight: bold;
	  background: transparent;
	  border-radius: 0 0 10px 10px;
	  margin-left: 50px;
	}
	
	img{
		width: 50px;
		height:50px;
	}
	
	.stats-counter {
       background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("/dd/resources/files/guide/use-guide.jpg") center center;
       background-size: cover;
       background-attachment: fixed;
    }
    li{
    	margin-bottom:10px;
    	color:#495057;
    }
    .warn > span{
    	font-size: 18px;
    	margin-left: 17px;
    	color:#495057;
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
              font-weight: 700;">파크 이용안내</div>
              <p>주의사항을 꼭 확인해주세요!</p>
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

          <div class="tab-pane fade active show" id="menu-starters">

            <div class="munti-content-container">
              <div class="item">
                <div style="color:#495057;"><i class="fa-solid fa-triangle-exclamation" style="color:#eb4d69;"></i>다음의 내용은 가지고 입장하실 수 없습니다!<i class="fa-solid fa-triangle-exclamation" style="color:#eb4d69;"></i></div>
                <div id="warn" style="font-size:18px;">
                	<div><img src="/dd/resources/files/guide/use-guide/dog.png"> <span>애완동물(안내견 제외)</span></div>
					<div><img src="/dd/resources/files/guide/use-guide/자전거.png"><span>자전거, 인라인, 킥보드 등</span></div>
					<div><img src="/dd/resources/files/guide/use-guide/주류.png"><span>주류 및 불법약품</span></div>
					<div><img src="/dd/resources/files/guide/use-guide/의자.png"><span>버너, 대형 돗자리</span></div>
					<div><img src="/dd/resources/files/guide/use-guide/카메라.png"><span>전문 촬영장비</span></div>
					<div><img src="/dd/resources/files/guide/use-guide/드론.png"><span>드론, 리모컨 조종 장난감</span></div>
					<div><img src="/dd/resources/files/guide/use-guide/총.png"><span>총, 칼을 휴대하는 행위</span></div>
					<div><img src="/dd/resources/files/guide/use-guide/스피커.png"><span>과도한 소음</span></div>
					<div><img src="/dd/resources/files/guide/use-guide/페인트.png"><span>페인트 등 시설에 해가 되는 물품</span></div>            
                </div>
              </div>
              <div class="item">
                <div style="color:#495057;"><i class="fa-solid fa-triangle-exclamation" style="color:#eb4d69;"></i>흡연은 흡연실에서만!<i class="fa-solid fa-triangle-exclamation" style="color:#eb4d69;"></i></div>
                <div style="align-items:center; color:#495057;">DD Studio 내 모든 구역은 금연입니다.<br>흡연을 하시고 싶으신 분은 <span style="color:#eb4d69; display: contents;" >"우도"</span>로 가시면 됩니다.<br>미성년자의 흡연은 금지되어 있습니다.</div>
              </div>
              <div class="item">
                <div><i class="fa-solid fa-triangle-exclamation" style="color:#eb4d69;"></i>다음의 행위들은 파크에서 금지되어 있습니다.<i class="fa-solid fa-triangle-exclamation" style="color:#eb4d69;"></i></div>
                <div style="align-items:left; font-size:18px;">
                	<li>혐오감을 줄 수 있는 과한 분장</li>
					<li>어드벤처 연기자, CAST와 똑같은 코스튬을 입고 CAST 행세를 하는 행위</li>
					<li>CAST들을 위협, 방해하거나 몸을 접촉하는 행위</li>
					<li>화장실에서 다른 의상으로 환복하는 행위로 다른 손님들에게 피해를 주는 경우</li>
					<li>전단지 배포, 설문조사, 손님 인터뷰 등 다른 손님들의 파크 이용에 불편을 끼치는 행위</li>
                </div>
              </div>
              <div class="item">
                <div><i class="fa-solid fa-triangle-exclamation" style="color:#eb4d69;"></i>손님 여러분의 안전을 위해 다음 내용들은 유의해 주시기 바랍니다.<i class="fa-solid fa-triangle-exclamation" style="color:#eb4d69;"></i></div>
                <div style="font-size:18px;">
                	<li>음식물은 다 드신 후에 이용해 주시기 바랍니다.</li>
					<li>탑승 중에는 카메라, 셀카봉, 핸드폰 사용을 삼가 주세요.</li>
					<li>어트랙션별 탑승기준을 준수해 주시기 바랍니다.</li>
					<li>음주 후 어트랙션 탑승은 불가합니다.</li>
					<li>어트랙션 탑승 도중 멈추더라도 당황하지 마시고 직원 안내를 기다려 주세요.</li>
                </div>
              </div>
            </div>
			</div><!-- End Starter Menu Content -->
      </div>
     </div>
    </section><!-- End Menu Section -->
    
