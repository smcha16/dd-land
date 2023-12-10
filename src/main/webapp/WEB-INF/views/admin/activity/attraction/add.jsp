<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	#main h1 {
		font-size: 2rem !important;
   		margin-top: 45px !important;
   		margin-left: 10px;
  	}
    .d-md-block {
    	margin-right: 15px;
  	}
  	.pagetitle {
    	margin-top: 10px;
  	}
    .breadcrumb {
    	margin-right: 20px 15px 0 0;
  	}
    .breadcrumb a {
    	color: #012970;
  	}
  	.breadcrumb a:hover {
      	color: #ce1212;
    }
    .card-body {
      min-height: 600px;
    }
    
    label {
		font-weight: bold !important;
    }
    
    /* 필수 입력사항 required */
    .required::after  {
    	content: ' *';
    	color: #b71c1c;
    }
    
    /* placeholder CSS */
    #name {
    	
    }
    
    
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>어트랙션 등록</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">등록</a></li>
          		<li class="breadcrumb-item active"><a href="/dd/admin/activity/attraction/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">

        		<div class="card">
          			<div class="card-body">
            			<h5 class="card-title">어트랙션 정보 입력</h5>

<!-- 어트랙션명, 어트랙션소개, 탑승인원, (운영시간 > 생략) 제한사항, 위치, 이미지 -->
            			<form method="POST" action="/dd/admin/activity/attraction/addok.do" enctype="multipart/form-data">
            			
            				<!-- 어트랙션명 -->
              				<div class="row mb-3">
                				<label for="name" class="col-sm-2 col-form-label required">어트랙션명</label>
                				<div class="col-sm-10">
                  					<input type="text" id="name" name="name" class="form-control" placeholder="어트랙션명을 입력해주세요." required>
                				</div>
              				</div>

							<!-- 어트랙션 소개 -->
              				<div class="row mb-3">
                				<label for="info" class="col-sm-2 col-form-label required" >소개</label>
                				<div class="col-sm-10">
                  					<textarea id="info" name="info" class="form-control" style="height: 100px" placeholder="어트랙션의 소개 & 특징을 입력해주세요." required></textarea>
                				</div>
              				</div>

							<!-- 탑승 인원 -->
              				<div class="row mb-3">
                				<label for="capacity" class="col-sm-2 col-form-label required">탑승 인원</label>
                				<div class="col-sm-10">
                  					<input type="number" id="capacity" name="capacity" class="form-control" placeholder="탑승 인원(숫자)을 입력해주세요." required>
                				</div>
              				</div>

							<!-- 제한사항 -->
              				<div class="row mb-3">
                				<label for="restriction" class="col-sm-2 col-form-label required">제한사항</label>
                				<div class="col-sm-10">
                  					<textarea id="restriction" name="restriction" class="form-control" style="height: 100px" placeholder="해당 어트랙션의 제한사항(키, 임산부, 노약자 등)을 입력해주세요." required></textarea>
                				</div>
              				</div>
              				
              				<!-- 위치 -->
              				<div class="row mb-3">
              					<label for="map" class="col-sm-2 col-form-label required">위치</label>
								<div class="col-sm-10">
									<div id="map" class="middle-flat" style="height: 380px; border-radius: var(--bs-border-radius);"></div>
								</div>
							</div>
              				
              				<!-- 이미지 -->
              				<div class="row mb-3">
                				<label for="formFile" class="col-sm-2 col-form-label">이미지</label>
                				<div class="col-sm-10">
                  					<input class="form-control" type="file" id="formFile" name="imgs" multiple>
                				</div>
              				</div>
              				
              				<!-- 토큰 -->
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
							
							<!-- 선택한 위치(위도,경도) -->
							<input type="hidden" name="lng" id="lng">
							<input type="hidden" name="lat" id="lat">
						</form>
			
					</div>
				</div>

      		</div>
    	</div>
	</section>

</main>

<!-- Kakao Map Open API -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c089ee6f3d885cfbe52b2f15d8f3f531"></script>

<!-- Attraction add용 JavaScript -->
<script>

	function submit() {
		$('form').submit();
	}
	
	/* 선택한 위치 추가 */
	const latInput = document.getElementById('lat');
	const lngInput = document.getElementById('lng');

	const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스

	const options = { //지도를 생성할 때 필요한 기본 옵션
		center : new kakao.maps.LatLng(33.361488, 126.529212), //지도의 중심좌표.
		level : 10 //지도의 레벨(확대, 축소 정도)
		/* draggable : false, // 이동 금지
		disableDoubleClick : true, // 더블클릭 확대 금지
		scrollwheel : false // 휠 확대/축소 금지 */
	};
	
	const map = new kakao.maps.Map(container, options);
	
	let m = null;
	let lat = null;
	let lng = null;
	
	 
	kakao.maps.event.addListener(map, 'click', function(evt) {
		lat = evt.latLng.getLat();
		lng = evt.latLng.getLng();

		if (m != null) {
			// 기존 마커 제거
			m.setMap(null);
		}

		m = new kakao.maps.Marker({
			position : new kakao.maps.LatLng(lat, lng)
		});

		m.setMap(map);

		latInput.value = lat;
		lngInput.value = lng;
		
		let location = {
				lat: evt.latLng.getLat(),
				lng: evt.latLng.getLng()
		}
		
		/* $.ajax({
			type: 'GET',
			url: '/dd/admin/activity/attraction/add',
			data: JSON.stringify(location),
			contentType: "application/json; charset=UTF-8;",
			dataType: 'json',
			success: function(result) {
				alert('성공');
			},
			error: function(a,b,c) {
				console.log(a,b,c);
			}
			
		}); */
		
		
		
		
	});
	
	/* Attraction 위치 중복 검사 */
	
	
	
	
</script>