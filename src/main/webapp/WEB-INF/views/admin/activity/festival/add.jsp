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
    
    /* 유효성검사 CSS */
    .check-location-duplication, .check-time-validation {
    	padding: 10px 10px;
    }
    
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>페스티벌 등록</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">등록</a></li>
          		<li class="breadcrumb-item active"><a href="/dd/admin/activity/festival/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">

        		<div class="card">
          			<div class="card-body">
            			<h5 class="card-title">페스티벌 정보 입력</h5>

						<!-- 페스티벌명, 공연 시간, 소개, 공연 시작일, 공연 종료일, 위치, 이미지 -->
            			<form method="POST" action="/dd/admin/activity/festival/addok.do" enctype="multipart/form-data">
            			
            				<!-- 페스티벌명 -->
              				<div class="row mb-3">
                				<label for="name" class="col-sm-2 col-form-label required">페스티벌명</label>
                				<div class="col-sm-10">
                  					<input type="text" id="name" name="name" class="form-control" placeholder="페스티벌명을 입력해주세요." required>
                				</div>
              				</div>

							<!-- 공연 시간 -->
              				<div class="row mb-3">
                				<label for="time" class="col-sm-2 col-form-label required" >공연 시간</label>
                				<div class="col-sm-10">
                					<input type="text" id="time" name="time" class="form-control" placeholder="공연 시간(HH:MM)을 입력해주세요." required>
                					<div style="height: 30px;">
                  						<div class="check-time-validation"></div>
                  					</div>
                				</div>
              				</div>
              				
              				<!-- 페스티벌 소개 -->
              				<div class="row mb-3">
                				<label for="info" class="col-sm-2 col-form-label required" >소개</label>
                				<div class="col-sm-10">
                  					<textarea id="info" name="info" class="form-control" style="height: 100px" placeholder="페스티벌의 소개 & 특징을 입력해주세요." required></textarea>
                				</div>
              				</div>

							<!-- 공연 시작일 -->
              				<div class="row mb-3">
                				<label for="start_date" class="col-sm-2 col-form-label required">공연 시작일</label>
                				<div class="col-sm-10">
                  					<input type="date" id="start_date" name="start_date" class="form-control" required>
                				</div>
              				</div>

							<!-- 공연 종료일 -->
              				<div class="row mb-3">
                				<label for="end_date" class="col-sm-2 col-form-label required">공연 종료일</label>
                				<div class="col-sm-10">
                  					<input type="date" id="end_date" name="end_date" class="form-control" required>
                				</div>
              				</div>
              				
              				<!-- 위치 -->
              				<div class="row mb-3">
              					<label for="map" class="col-sm-2 col-form-label required">위치</label>
								<div class="col-sm-10">
									<div id="map" class="middle-flat" style="height: 380px; border-radius: var(--bs-border-radius);"></div>
									<div style="height: 30px;">
                  						<div class="check-location-duplication"></div>
                  					</div>
								</div>
							</div>
              				
              				<!-- 이미지 -->
              				<div class="row mb-3">
                				<label for="formFile" class="col-sm-2 col-form-label">이미지</label>
                				<div class="col-sm-10">
                  					<input class="form-control" type="file" id="formFile" name="imgs" multiple>
                				</div>
              				</div>
              				
							<!-- 선택한 위치(위도,경도) -->
							<input type="hidden" name="lng" id="lng">
							<input type="hidden" name="lat" id="lat">

              				<!-- 토큰 -->
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
							
						</form>
			
					</div>
				</div>

      		</div>
    	</div>
	</section>

</main>

<!-- Kakao Map Open API -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c089ee6f3d885cfbe52b2f15d8f3f531"></script>
<script>

	/* 필수 항목이 반드시 입력되어야만 submit 클릭 시 넘어가도록 */
	function submit() {
		
		if (!$('input[name="name"]').val().trim() || !$('textarea[name="info"]').val().trim()
				|| !$('input[name="time"]').val().trim()
				|| $('input[name="start_date"]').val() == "" || $('input[name="end_date"]').val() == ""
				|| $('.check-location-duplication').data('type') != 'y' || $('.check-time-validation').data('type') != 'y') {
			
			if ($('.check-location-duplication').data('type') == 'n') {
				alert('중복된 항목을 수정해주세요.');
			} else if ($('.check-time-validation').data('type') == 'n') {
				alert('올바른 시간 형식(HH:MM)으로 입력해주세요.');
			} else {
				alert('필수 항목을 입력해주세요.');
			}
			
		} else {
			$('form').submit();
		}
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
		
		/* Festival 위치 중복 검사 */
		let obj = {
				lat: evt.latLng.getLat(),
				lng: evt.latLng.getLng()
		};
		
		// CSRF token
        var csrfHeaderName = "${_csrf.headerName}";
        var csrfTokenValue = "${_csrf.token}";
		
		$.ajax({
			type: 'POST',
			url: '/dd/admin/activity/festival/location',
			headers: {'content-Type': 'application/json'},
			data: JSON.stringify(obj),
			dataType: 'json',
			success: function(result) {
				//alert('성공');
				if (result == 0) {
					$('.check-location-duplication').text('지정 가능한 위치입니다.');
					$('.check-name-duplication').css('color', '#212529');
					$('.check-location-duplication').data('type', 'y');
					console.log('지정 가능');
					console.log("check-location-duplication: " + $('.check-location-duplication').data('type'));
					
				} else {
					$('.check-location-duplication').text('중복된 위치입니다. 다른 위치를 선택해주세요.');
					$('.check-location-duplication').css('color', '#dc3545');
					$('.check-location-duplication').data('type', 'n');
					console.log('중복된 위치');
					console.log("check-location-duplication: " + $('.check-location-duplication').data('type'));
				}
			},
			beforeSend: function(xhr) {
            	xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
            },
			error: function(a,b,c) {
				console.log(a,b,c);
			}
			
		});
		
	});
	
	/* 공연 시간 입력 형식 유효성 검사 */
	const pattern = /^\d{2}:\d{2}$/;

	$('input[name="time"]').keyup(function() {
		
		let time = $('input[name="time"]').val();
		//console.log(time);
		
		if (pattern.test(time)) {
		    $('.check-time-validation').text('');
		    $('.check-time-validation').css('color', '#212529');
		    $('.check-time-validation').data('type', 'y');
		    
		} else {
		    $('.check-time-validation').text('올바른 형식(HH:MM)으로 입력해주세요.');
		    $('.check-time-validation').css('color', '#dc3545');
		    $('.check-time-validation').data('type', 'n');
		}
		
	});
	
	/* 날짜 입력 유효성 검사 */
	const startDate = document.getElementById('start_date');

	const now = new Date();
	
	const year = now.getFullYear();
	const month = now.getMonth() + 1;
	const date = now.getDate();
	
	const nowStr = `\${year}-\${month >= 10 ? month : '0' + month}-\${date >= 10 ? date : '0' + date}`;
	
	$('#start_date').attr('min', nowStr);//시작일은 최소 오늘 이후
	$('#end_date').attr('min', nowStr);//종료일은 최소 오늘 이후
	
	 
	function isValidEndDate() {
		$('#end_date').attr('min', startDate.value);
		
		//시작일을 변경 했을 경우, 시작일이 기존에 설정한 종료일보다 미래일 경우 종료일 값 초기화
		if ($('#start_date').val() > $('#end_date').val()) {
			$('#end_date').val('');
		}
	}
	 
	$('#start_date').change(function() {
		isValidEndDate();
	});
	
</script>