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
    
    /* 유효성검사 CSS */
    .check-location-duplication {
    	padding: 10px 10px;
    }
    .check-name-duplication {
    	padding: 10px 10px;
    }
    
    /* 기존 첨부파일 목록 CSS */
    .attached {
    	padding: 10px 5px;
    	display: inline-block;
    }
    
    .attached > span {
    	cursor: pointer;
    	display: inline-block;
    	vertical-align: middle;
    	font-weight: bold;
    	color: #777;
    }
    
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>포토존 수정</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">수정</a></li>
          		<li class="breadcrumb-item active"><a href="/dd/admin/activity/photozone/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">

        		<div class="card">
          			<div class="card-body">
            			<h5 class="card-title">포토존 정보 입력</h5>

						<!-- 포토존명, 포토존소개, (운영시간 > 생략), 위치, 이미지 -->
            			<form method="POST" action="/dd/admin/activity/photozone/editok.do" enctype="multipart/form-data">
            			
            				<!-- 포토존명 -->
              				<div class="row mb-3">
                				<label for="name" class="col-sm-2 col-form-label required">포토존명</label>
                				<div class="col-sm-10">
                  					<input type="text" id="name" name="name" class="form-control" placeholder="포토존명을 입력해주세요." value="${dto.name}" required>
                  					<div style="height: 30px;">
                  						<div class="check-name-duplication"></div>
                  					</div>
                				</div>
              				</div>

							<!-- 포토존 소개 -->
              				<div class="row mb-3">
                				<label for="info" class="col-sm-2 col-form-label required" >소개</label>
                				<div class="col-sm-10">
                  					<textarea id="info" name="info" class="form-control" style="height: 100px" placeholder="포토존의 소개 & 특징을 입력해주세요." required><c:out value="${dto.info}"/></textarea>
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
                  					<c:if test="${dto.img == 'photozone.png'}">
	                  					<div style="height: 30px;">
	                  					</div>
                  					</c:if>
                  					<c:if test="${dto.img != 'photozone.png'}">
	                  					<div>
	                  						<c:forEach items="${dto.imgList}" var="dto">
	                  							<div class="attached"><i class="bi bi-image"></i> <c:out value="${dto.img}" /> <span onclick="delAttached(${dto.photozone_img_seq})">&times;</span></div>
	                  						</c:forEach>
	                  					</div>
                  					</c:if>
                				</div>
              				</div>
              				
              				<!-- 포토존 seq -->
              				<input type="hidden" name="photozone_seq" value="${dto.photozone_seq}">
              				
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

<!-- Photozone Edit JavaScript -->
<script>

	/* 페이지 로딩 시, 유효성 검사가 필요한 이름, 지도에 기본 data-type 부여 */
	$('.check-name-duplication').data('type', 'y');
	$('.check-location-duplication').data('type', 'y');
	
	/* 위치 수정 없을 시, 기본적으로 값 넘겨주기 */
	$('#lat').val(${dto.lat});
	$('#lng').val(${dto.lng})
	

	/* 필수 항목이 반드시 입력되어야만 submit 클릭 시 넘어가도록 */
	function submit() {
		
		if (!$('input[name="name"]').val().trim() || !$('textarea[name="info"]').val().trim()
				|| $('.check-name-duplication').data('type') != 'y' || $('.check-location-duplication').data('type') != 'y' ) {
			
			if ($('.check-name-duplication').data('type') == 'n' || $('.check-location-duplication').data('type') == 'n') {
				
				alert('중복된 항목을 수정해주세요.');
			} else {
				
				alert('필수 항목을 입력해주세요.');
			}
			
		} else {
			
			allDeleteImgSeq = allDeleteImgSeq.slice(0, -1);
			$('input[name="deleteImgSeq"]').val(allDeleteImgSeq);
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
    let imageSrc = '/dd/resources/files/marker/photo_marker3.png'; // 마커이미지의 주소
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
		
		/* Photozone 위치 중복 검사 */
		let obj = {
				lat: evt.latLng.getLat(),
				lng: evt.latLng.getLng()
		};
		
		// CSRF token
        var csrfHeaderName = "${_csrf.headerName}";
        var csrfTokenValue = "${_csrf.token}";
		
		$.ajax({
			type: 'POST',
			url: '/dd/admin/activity/photozone/location',
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
	
	/* Photozone 이름 중복 검사 (blur > keyup 변경) */
	$('input[name="name"]').keyup(function() {
		
		let obj = {
				name: $('input[name="name"]').val()
		};
		
		// CSRF token
        var csrfHeaderName = "${_csrf.headerName}";
        var csrfTokenValue = "${_csrf.token}";

        //수정 > 기존과 이름이 동일하지 않을 때만 중복 검사 진행
        const originalName = '${dto.name}';
        
        if ($('input[name="name"]').val() != originalName) {
        	
	        if ($('input[name="name"]').val().trim()) {
	        	
				$.ajax({
					type: 'POST',
					url: '/dd/admin/activity/photozone/name',
					headers: {'content-Type': 'application/json'},
					data: JSON.stringify(obj),
					dataType: 'json',
					success: function(result) {
						//alert(result);
						if (result == 0) {
							$('.check-name-duplication').text('사용 가능한 포토존명입니다.');
							$('.check-name-duplication').css('color', '#212529');
							$('.check-name-duplication').data('type', 'y');
							console.log("check-name-duplication: " + $('.check-name-duplication').data('type'));
							
						} else {
							$('.check-name-duplication').text('중복된 포토존명입니다. 다른 포토존명을 입력해주세요.');
							$('.check-name-duplication').css('color', '#dc3545');
							$('.check-name-duplication').data('type', 'n');
							console.log("check-name-duplication: " + $('.check-name-duplication').data('type'));
						}
					},
					beforeSend: function(xhr) {
		            	xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
		            },
					error: (a,b,c) => {
						console.log(a,b,c);
					}
					
				});
	        } else {
	        	$('.check-name-duplication').text('');
	        	$('.check-name-duplication').data('type', 'y');
	        	console.log("check-name-duplication: " + $('.check-name-duplication').data('type'));
	        }
		
        } else {
        	//기존 포토존명과 동일할 때
        	
        	$('.check-name-duplication').text('');
        	$('.check-name-duplication').removeAttr("data-type");
        	console.log("check-name-duplication: " + $('.check-name-duplication').data('type'));
        }
		
	});
	
	/* 기존 첨부한 첨부파일 삭제 */
	let allDeleteImgSeq = "";	
	
	function delAttached(seq) {
		//alert(seq);
		allDeleteImgSeq += seq + ",";
		$(event.target).parent().remove();
	}
	
	
</script>