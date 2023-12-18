<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Slick -->
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>

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
  	.col-12 {
    	margin-top: 15px;
  	}
  	.col-lg-8 {
    	width: 100%;
  	}
  	.card-body {
    	min-height: 600px;
  	}
  	div.header {
    	height: 60px;
    	border-radius: 5px;
  	}
  	#search {
    	margin-bottom: 15px;
    	padding: 7px;
  	}
  	.search-form {
    	width: 100%;
    	margin: 0;
  	}
  	.header .search-form input {
    	border: 0;
   		height: 50px;
  	}
  	.header .search-form input:focus, .header .search-form input:hover {
    	outline: none;
    	border: none;
    	box-shadow: none;
    	transition: none;
  	}
  	.card-body .header {
    	display: flex;
    	align-items: center;
    	justify-content: space-between;
  	}
  	.breadcrumb {
    	margin-right: 15px;
    	margin-top: 30px;
   	 	margin-bottom: 10px;
  	}
  	.breadcrumb a {
    	color: #012970;
  	}
  	.breadcrumb a:hover {
      	color: #ce1212;
    }
  	.table {
    	text-align: center;
  	}
  	th {
    	background-color: #f2f2f2 !important;
  	}
  	.pagination {
		justify-content: center;
		margin-top: 40px;
	}
  	
  	/* 목록 커서 CSS */
  	table td:nth-child(3) a {
		cursor: pointer;
	}
  	table td:nth-child(5) i {
		cursor: pointer;
	}
  	table td:nth-child(6) i {
		cursor: pointer;
	}
	
	/* slick slider */
	/* .image-slider {
		width: 700px;
		height: 350px;
    } */
    
	.image-slider div {
		/* width: 700px;
		height: 350px; */
		overflow: hidden;
	}
	
	.image-slider img {
		width: 100%;
		max-height: 100%;
		object-fit: cover;
	}
	
	/* Slick Button Style */
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
	
	/* 모달 CSS */
	#modal table.m-desc {
		width: 100%;
		font-size: 14px;
	}
	
	#modal table tr > th {
		/* width: 100px; */
		text-align: left;
		font-weight: bold;
		background: #FFF !important;
		padding: 10px;
	}
	
	#modal table tr > td {
		padding: 10px;
	}
	
	.m-info {
		padding: 10px;
	}
	
</style>

<!-- ======= Main ======= -->
<main id="main" class="main">

    <div class="pagetitle">
		<h1>레스토랑 관리</h1>
    </div>

	<section class="section">
		<div class="row">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-12">

              			<div id="search" class="header">
                  			<form class="search-form d-flex align-items-center" method="POST" action="#">
                    			<input type="text" name="query" placeholder="Search" title="Enter search keyword">
                    			<button type="submit" title="Search"><i class="bi bi-search"></i></button>
                    			
                    			<!-- 토큰 -->
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                  			</form>
              			</div>

						<div class="card">
                			<div class="card-body">

                  				<nav class="d-flex justify-content-end">
                    				<ol class="breadcrumb">
                      					<li class="breadcrumb-item"><a href="/dd/admin/shop/gift-shop/add.do">추가</a></li>
                      					<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="edit()">수정</a></li>
                      					<li class="breadcrumb-item active"><a href="javascript:void(0);" onclick="del()">삭제</a></li>
                    				</ol>
								</nav>
                  
                  				<form id="del-form" method="POST" action="/dd/admin/shop/gift-shop/del.do">
	                  				<table class="table">
	                    				<thead>
	                      					<tr>
	                        					<th></th>
	                        					<th>No</th>
	                        					<th>이름</th>
	                        					<!-- <th>이미지</th> -->
	                        					<th>위치</th>
	                      					</tr>
	                    				</thead>
	                    				<tbody>
	                    					<c:forEach items="${list}" var="dto" varStatus="status">
		                      					<tr>
		                        					<td><input type="checkbox" name="shop_seq" value="${dto.shop_seq}"></td>
		                        					<td>${map.totalPosts - status.index - map.startIndex + 1}</td>
		                        					<td><a onclick="showModal('${dto.shop_seq}', `${dto.name}`,`${dto.info}`, `${dto.time}`,`${dto.tel}`)"><c:out value="${dto.name}" /></a></td>
		                        					
		                        					<%-- <c:if test="${dto.img == 'attraction.png'}">
		                        						<td></td>
		                        					</c:if>
		                        					<c:if test="${dto.img != 'attraction.png'}">
		                        						<td><i class="bi bi-image"></i></td>
		                        					</c:if> --%>
		                        					<td><a onclick="showLocationModal(`${dto.name}`, '${dto.lat}', '${dto.lng}')"><i class="bi bi-geo-alt"></i></a></td>
		                      					</tr>
	                      					</c:forEach>
	                   					</tbody>
                  					</table>
	                  					
                  					<!-- 토큰 -->
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                				</form>
                  					
                				<!-- 어트랙션 상세 모달 -->
								<div id="modal" class="modal fade show" tabindex="-1" aria-labelledby="exampleModalScrollableTitle" aria-modal="true" role="dialog">
								    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
								        <div class="modal-content">
								            <div class="modal-header">
								                <h5 id="modal-name" class="modal-title"></h5>
								                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								            </div>
							                
							                
								            <div class="modal-body">
									            <!-- 모달 이미지 슬라이드-->
								                <div class="image-slider"></div>
								                <!-- 설명 -->
								            	<div class="m-info"></div>
								            	<!-- 상세 -->
								            	<table class="m-desc">
								            		<colgroup>
								            			<col style="width: 100px">
								            			<col>
								            		</colgroup>
								            		<tbody>
								            			<tr>
								            				<th>시간</th>
								            				<td class="m-time"></td>
								            			</tr>
								            			<tr>
								            				<th>전화번호</th>
								            				<td class="m-restriction"></td>
								            			</tr>
								            		</tbody>
								            	</table>
								            </div>
								        </div>
								    </div>
								</div>
                				
                				<!-- 어트랙션 위치 모달 -->
								<div id="location-modal" class="modal fade show" tabindex="-1" aria-labelledby="exampleModalScrollableTitle" aria-modal="true" role="dialog">
								    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
								        <div class="modal-content">
								            <div class="modal-header">
								                <h5 id="location-modal-name" class="modal-title"></h5>
								                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								            </div>
							                
								            <div class="modal-body">
								            	<div id="map" style="height: 380px; border-radius: var(--bs-border-radius);"></div>
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
													<li class="page-item"><a class="page-link" href="/dd/admin/shop/gift-shop/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</ul>
								</nav>
               				</div>

             			</div>
            		</div>
				</div>
			</div>

		</div>
	</section>

</main>

<!-- admin > activity > attraction > view JavaScript -->
<!-- Kakao Map Open API -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c089ee6f3d885cfbe52b2f15d8f3f531"></script>

<!-- Slick Slider -->
<script type="text/javascript" src="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script>

	/* 수정 시, 체크 박스 1개만 선택 하여 seq 전달 하기 */
	function edit() {
		
		/* 선택된 체크박수 개수 확인 */
		let checkedCount = $('input[type="checkbox"]:checked').length;
		
		/* 1개 이상? out! */
		if (checkedCount > 1 || checkedCount < 1) {
			alert('1개의 레스토랑을 선택 후, 수정 버튼을 눌러주세요.');
		} else {

			const seq = $('input[type="checkbox"]:checked').val();
			
			location.href='/dd/admin/shop/gift-shop/edit.do?seq=' + seq;
						
		}
		
	}//function
	
	/* 삭제 시, 체크 박스 1개 이상 선택 하여 seq 전달하기 */
	/* 1. 체크박스 1개 2. 체크박스 1개 이상 */
	function del() {
		
		/* 선택된 체크박수 개수 확인 */
		let checkedCount = $('input[type="checkbox"]:checked').length;
		
		if (checkedCount == 0) {
			alert('1개 이상의 어트랙션을 선택 후, 삭제 버튼을 눌러주세요.');
		} else {
			
			if (confirm('선택한 어트랙션을 삭제하시겠습니까?')) {
				
				$('#del-form').submit();

			}
			
		} 
		
	}//function
	
	/* 어트랙션 상세 모달 */
	function showModal(seq, name, info, time, restriction) {
	    
		$('.image-slider').html('');
		addModalImg(seq);
		
		$('#modal-name').text(name);
        $('.m-info').text(info);
        $('.m-time').text(time);
        $('.m-restriction').text(restriction);
        
        $('#modal').modal('show');
	}
	
	function addModalImg(seq) {
		
		const filterImg = img_list.filter(obj => obj.shop_seq == seq);
		
		if (filterImg.length > 0) {
			
			filterImg.forEach(obj => {
				
				let imgSrc = '/dd/resources/files/giftshop/' + obj.img;
				
				$('.image-slider').append(`
				
					<div>
						<img class="modal-image" alt="Image" src="\${imgSrc}">
					</div>
						
				`);
			});
			
		}

		
		/* Slick Slider */
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
		
	}
	
	/* 모달이 닫힐 때 uslick 설정 */
	$('#modal').on('hidden.bs.modal', function () {
		$('.image-slider').slick('unslick');
	});
	

	</script>
	
	<script>
	
	/* 어트랙션 위치 모달 */
	function showLocationModal(name, lat, lng) {
		
		$('#location-modal-name').text(name);
		
		m = new kakao.maps.Marker({
	        position: new kakao.maps.LatLng(lat, lng),
	        image: markerImg
	    });

		//마커 지도에 출력
	    m.setMap(map);
		
		$('#location-modal').modal('show');
	}
	
	/* 어트랙션 위치 모달이 열릴 때 카카오맵 relayout 설정 */
	$('#location-modal').on('shown.bs.modal', function () {
		map.relayout();
		map.setLevel(10);
		map.setCenter(new kakao.maps.LatLng(33.361488, 126.529212));
	});
	
	/* 모달이 닫힐 때 카카오맵 관련 reset 설정 */
	$('#location-modal').on('hidden.bs.modal', function () {
		m.setMap(null);
	});
	
	/* 카카오맵 */
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
	
	//마커 출력
    let imageSrc = '/dd/resources/files/marker/shop_marker.png'; // 마커이미지의 주소
    const imageSize = new kakao.maps.Size(40,40);
    const option = {};
    
    //마커 설정
    const markerImg = new kakao.maps.MarkerImage(imageSrc, imageSize, option);
	
	
	/* 모달 슬라이드용 img 배열에 넣기 */
	const img_list = new Array();
	<c:forEach items="${ilist}" var="dto">
		img_list.push({
			shop_img_seq: ${dto.shop_img_seq},
			img: `${dto.img}`,
			shop_seq: ${dto.shop_seq}
		});
	</c:forEach>
	
	/* 반응형 슬릭 이미지 조절 테스트 */
	$('#modal').on('shown.bs.modal', function () {
		if ($('.modal-content').css('width') == '800px') {
			$('.slick-slide').css('width', '800px');
		} else {
			$('.slick-slide').css('width', '500px');
		}
		
		setTimeout($('.image-slider').css('display', 'display'), 500);
		
	});

	$(window).resize(function() {
	
		if ($('.modal-content').css('width') == '800px') {
			$('.slick-slide').css('width', '800px');
		} else {
			$('.slick-slide').css('width', '500px');
		}
	});
	
	$(window).resize(function() {
		$('.image-slider')[0].slick.refresh();
	});
	
	
</script>