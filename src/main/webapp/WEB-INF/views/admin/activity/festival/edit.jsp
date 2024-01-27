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
		<h1>페스티벌 수정</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">수정</a></li>
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
            			<form method="POST" action="/dd/admin/activity/festival/editok.do" enctype="multipart/form-data">
            			
            				<!-- 페스티벌명 -->
              				<div class="row mb-3">
                				<label for="name" class="col-sm-2 col-form-label required">페스티벌명</label>
                				<div class="col-sm-10">
                  					<input type="text" id="name" name="name" class="form-control" placeholder="페스티벌명을 입력해주세요." value="${dto.name}" required>
                				</div>
              				</div>

							<!-- 공연 시간 -->
              				<div class="row mb-3">
                				<label for="time" class="col-sm-2 col-form-label required" >공연 시간</label>
                				<div class="col-sm-10">
                					<input type="text" id="time" name="time" class="form-control" placeholder="공연 시간(HH:MM)을 입력해주세요." value="${dto.time}" required>
                					<div style="height: 30px;">
                  						<div class="check-time-validation"></div>
                  					</div>
                				</div>
              				</div>
              				
              				<!-- 페스티벌 소개 -->
              				<div class="row mb-3">
                				<label for="info" class="col-sm-2 col-form-label required" >소개</label>
                				<div class="col-sm-10">
                  					<textarea id="info" name="info" class="form-control" style="height: 100px" placeholder="페스티벌의 소개 & 특징을 입력해주세요." required>${dto.info}</textarea>
                				</div>
              				</div>

							<!-- 공연 시작일 -->
              				<div class="row mb-3">
                				<label for="start_date" class="col-sm-2 col-form-label required">공연 시작일</label>
                				<div class="col-sm-10">
                  					<input type="date" id="start_date" name="start_date" class="form-control" value="${dto.start_date}" required>
                				</div>
              				</div>

							<!-- 공연 종료일 -->
              				<div class="row mb-3">
                				<label for="end_date" class="col-sm-2 col-form-label required">공연 종료일</label>
                				<div class="col-sm-10">
                  					<input type="date" id="end_date" name="end_date" class="form-control" value="${dto.end_date}" required>
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
                  					<c:if test="${dto.img == 'festival.png'}">
	                  					<div style="height: 30px;">
	                  					</div>
                  					</c:if>
                  					<c:if test="${dto.img != 'festival.png'}">
	                  					<div>
	                  						<c:forEach items="${dto.imgList}" var="dto">
	                  							<div class="attached"><i class="bi bi-image"></i> <c:out value="${dto.img}" /> <span onclick="delAttached(${dto.festival_img_seq})">&times;</span></div>
	                  						</c:forEach>
	                  					</div>
                  					</c:if>
                				</div>
              				</div>
              				
              				<!-- 페스티벌 seq -->
							<input type="hidden" name="festival_seq" value="${dto.festival_seq}">
              				
							<!-- 선택한 위치(위도,경도) -->
							<input type="hidden" name="lng" id="lng">
							<input type="hidden" name="lat" id="lat">
							
							<!-- 삭제할 이미지 seq -->
							<input type="hidden" name="deleteImgSeq">

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
	
	/* Festival Info 개행 처리 */
	$('textarea[name="info"]').val($('textarea[name="info"]').val().replace(/<br>/g, '\r\n'));

	/* 페이지 로딩 시, 유효성 검사가 필요한 시간, 지도에 기본 data-type 부여 */
	$('.check-time-validation').data('type', 'y');
	$('.check-location-duplication').data('type', 'y');
	
	/* 위치 수정 없을 시, 기본적으로 값 넘겨주기 */
	$('#lat').val(${dto.lat});
	$('#lng').val(${dto.lng});


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
	let lat = ${dto.lat};
	let lng = ${dto.lng};
	
	//마커 출력
    let imageSrc = '/dd/resources/files/marker/festival_marker3.png'; // 마커이미지의 주소
    const imageSize = new kakao.maps.Size(40,40);
    const option = {};
    
    //마커 설정
    const markerImg = new kakao.maps.MarkerImage(imageSrc, imageSize, option);
	
	
	m = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(lat, lng),
        image: markerImg
    });

	//마커 지도에 출력
    m.setMap(map);
	 
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
	
	/* 기존 첨부한 첨부파일 삭제 */
	let allDeleteImgSeq = "";	
	
	function delAttached(seq) {
		//alert(seq);
		allDeleteImgSeq += seq + ",";
		$(event.target).parent().remove();
	}
	
	/* 날짜 입력 유효성 검사 */
	const startDate = document.getElementById('start_date');
	
	//1. 기존 DB에 저장된 시작일, 종료일 불러오기
	const date = [];
	<c:forEach items="${dlist}" var="dto">
		date.push({
			start_date:'${dto.start_date}',
			end_date:'${dto.end_date}'
		});
	</c:forEach>
	
	//2. 시스템 날짜 불러오기
	const now = new Date();
	
	const getYear = now.getFullYear();
	const getMonth = now.getMonth() + 1;
	const getDate = now.getDate();
	
	const nowStr = `\${getYear}-\${getMonth >= 10 ? getMonth : '0' + getMonth}-\${getDate >= 10 ? getDate : '0' + getDate}`;

	//3. DB에 저장된 날짜와 시스템 날짜 비교하기
	$(document).ready(function() {
		
		$('#start_date').val(date[0].start_date);
		$('#end_date').val(date[0].end_date);
		
		if (date[0].start_date < nowStr) {
			//시작일이 sysdate보다 과거라면 수정 불가
			$('#start_date').prop('readOnly', true);
		}
		
		if (date[0].end_date < nowStr) {
			//종료일이 sysdate보다 과거라면 수정 불가
			$('#end_date').prop('readOnly', true);
		}
	
	});
	
	//4. 시작일 및 종료일 수정
	$('#start_date').attr('min', nowStr); //시작일은 최소 오늘 이후
	$('#end_date').attr('min', startDate.value); //종료일은 최소 시작일 이후
	
	$('#start_date').change(function() {
		
		$('#end_date').attr('min', startDate.value);
		
		//시작일을 변경 했을 경우, 시작일이 기존에 설정한 종료일보다 미래일 경우 종료일 값 초기화
		if ($('#start_date').val() > $('#end_date').val()) {
			$('#end_date').val('');
		}
		
	});
	 
</script>