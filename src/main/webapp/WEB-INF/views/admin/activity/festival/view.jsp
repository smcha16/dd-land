<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
  	table td:nth-child(7) i {
		cursor: pointer;
	}
	
	/* slick slider */
	.image-slider {
		width: 800px;
		height: 350px;
    }
    
	.image-slider div {
		width: 800px;
		height: 350px;
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
	
	.modal-body {
		padding: 0;
	}
</style>

<!-- ======= Main ======= -->
<main id="main" class="main">

     <div class="pagetitle">
		<h1>페스티벌 관리</h1>
    </div>

	<section class="section">
		<div class="row">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-12">

              			<div id="search" class="header">
                  			<form method="GET" action="/dd/admin/activity/festival/view.do" class="search-form d-flex align-items-center">
                    			<input type="text" name="word" placeholder="페스티벌명 또는 소개를 입력하세요." autocomplete="off">
                    			<button type="submit" title="Search"><i class="bi bi-search"></i></button>
                  			</form>
              			</div>

						<div class="card">
                			<div class="card-body">

                  				<nav class="d-flex justify-content-end">
                    				<ol class="breadcrumb">
                      					<li class="breadcrumb-item"><a href="/dd/admin/activity/festival/add.do">추가</a></li>
                      					<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="edit()">수정</a></li>
                      					<li class="breadcrumb-item active"><a href="javascript:void(0);" onclick="del()">삭제</a></li>
                    				</ol>
								</nav>
                  
                  				<form id="del-form" method="POST" action="/dd/admin/activity/festival/del.do">
	                  				<table class="table">
	                    				<thead>
	                      					<tr>
	                        					<th></th>
	                        					<th>No</th>
	                        					<th>이름</th>
	                        					<th>시간</th>
	                        					<th>시작일</th>
	                        					<th>종료일</th>
	                        					<th>위치</th>
	                      					</tr>
	                    				</thead>
	                    				<tbody>
	                    					<c:forEach items="${list}" var="dto" varStatus="status">
		                      					<tr>
		                        					<td><input type="checkbox" name="festival_seq" value="${dto.festival_seq}"></td>
		                        					<td>${map.totalPosts - status.index - map.startIndex + 1}</td>
		                        					<td><a onclick="showModal('${dto.festival_seq}', `${dto.name}`,'${dto.time}', `${dto.info}`,'${dto.start_date}', '${dto.end_date}')"><c:out value="${dto.name}" /></a></td>
		                        					<td>${dto.time}</td>
		                        					<td>${dto.start_date}</td>
		                        					<td>${dto.end_date}</td>
		                        					<td><a onclick="showLocationModal(`${dto.name}`, '${dto.lat}', '${dto.lng}')"><i class="bi bi-geo-alt"></i></a></td>
		                      					</tr>
	                      					</c:forEach>
	                   					</tbody>
                  					</table>
	                  					
                  					<!-- Token -->
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                				</form>
                  					
                				<!-- Festival Detail Modal -->
								<div id="modal" class="modal fade show" tabindex="-1" aria-labelledby="exampleModalScrollableTitle" aria-modal="true" role="dialog">
								    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
								        <div class="modal-content">
								            <div class="modal-header">
								                <h5 id="modal-name" class="modal-title"></h5>
								                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								            </div>
							                
							                
								            <div class="modal-body">
									            <!-- Modal Image Slide -->
								                <div class="image-slider"></div>
								                <!-- info -->
								            	<div class="m-info"></div>
								            	<!-- description -->
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
								            				<th>일정</th>
								            				<td class="m-date"></td>
								            			</tr>
								            		</tbody>
								            	</table>
								            </div>
								        </div>
								    </div>
								</div>
                				
                				<!-- Festival Location Modal -->
								<div id="location-modal" class="modal fade show" tabindex="-1" aria-labelledby="exampleModalScrollableTitle" aria-modal="true" role="dialog">
								    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
								        <div class="modal-content">
								            <div class="modal-header">
								                <h5 id="location-modal-name" class="modal-title"></h5>
								                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								            </div>
							                
								            <div class="modal-body">
								            	<div id="map" style="height: 380px;"></div>
								            </div>
								        </div>
								    </div>
								</div>

								<!-- paging -->
								<nav id="page-bar" aria-label="Page navigation example">
									<ul class="pagination justify-content-center">
										<c:forEach begin="1" end="${map.totalPages}" varStatus="pageStatus">
											<c:choose>
												<c:when test="${pageStatus.index == currentPage}">
													<li class="page-item active"><span class="page-link">${pageStatus.index}</span></li>
												</c:when>
												<c:otherwise>
													<li class="page-item"><a class="page-link" href="/dd/admin/activity/festival/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
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

<!-- Kakao Map Open API -->
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c089ee6f3d885cfbe52b2f15d8f3f531"></script>

<!-- Slick Slider -->
<script type="text/javascript" src="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script>

	function edit() {
		
		let checkedCount = $('input[type="checkbox"]:checked').length;
		
		if (checkedCount > 1 || checkedCount < 1) {
			alert('1개의 페스티벌을 선택 후, 수정 버튼을 눌러주세요.');
		} else {

			const seq = $('input[type="checkbox"]:checked').val();
			
			location.href='/dd/admin/activity/festival/edit.do?seq=' + seq;
						
		}
		
	}//function
	
	function del() {
		
		let checkedCount = $('input[type="checkbox"]:checked').length;
		
		if (checkedCount == 0) {
			alert('1개 이상의 페스티벌을 선택 후, 삭제 버튼을 눌러주세요.');
		} else {
			
			if (confirm('선택한 페스티벌을 삭제하시겠습니까?')) {
				$('#del-form').submit();
			}
			
		} 
		
	}//function
	
	/* Festival Detail Modal */
	function showModal(seq, name, time, info, start_date, end_date) {
	    
		$('.image-slider').html('');
		addModalImg(seq);
		
		$('#modal-name').text(name);
        $('.m-info').html(info);
        $('.m-time').text(time);
        $('.m-date').text(start_date + " ~ " + end_date);
        
        $('#modal').modal('show');
	}
	
	function addModalImg(seq) {
		
		const filterImg = img_list.filter(obj => obj.festival_seq == seq);
		
		if (filterImg.length > 0) {
			
			filterImg.forEach(obj => {
				
				let imgSrc = '/dd/resources/files/activity/festival/' + obj.img;
				
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
	
	/* when modal was hidden, unslick setting */
	$('#modal').on('hidden.bs.modal', function () {
		$('.image-slider').slick('unslick');
	});
	
	/* Festival Location Modal */
	function showLocationModal(name, lat, lng) {
		
		$('#location-modal-name').text(name);
		
		m = new kakao.maps.Marker({
	        position: new kakao.maps.LatLng(lat, lng),
	        image: markerImg
	    });

		//mark the marker on the map
	    m.setMap(map);
		
		$('#location-modal').modal('show');
	}
	
	/* When location modal was shown, relayout Kakaomap setting */
	$('#location-modal').on('shown.bs.modal', function () {
		map.relayout();
		map.setLevel(10);
		map.setCenter(new kakao.maps.LatLng(33.361488, 126.529212));
	});
	
	/* when location modal was hidden, Kakaomap reset marker setting */
	$('#location-modal').on('hidden.bs.modal', function () {
		m.setMap(null);
	});
	
	/* Kakaomap */
	const container = document.getElementById('map');

	const options = {
		center : new kakao.maps.LatLng(33.361488, 126.529212),
		level : 10
		/* draggable : false, // 이동 금지
		disableDoubleClick : true, // 더블클릭 확대 금지
		scrollwheel : false // 휠 확대/축소 금지 */
	};
	
	const map = new kakao.maps.Map(container, options);
	
	let m = null;
	
	//mark the marker
    let imageSrc = '/dd/resources/files/marker/festival_marker3.png';
    const imageSize = new kakao.maps.Size(40,40);
    const option = {};
    
    //marker setting
    const markerImg = new kakao.maps.MarkerImage(imageSrc, imageSize, option);
	
	/* FestivalImg Array for Modal */
	const img_list = new Array();
	<c:forEach items="${ilist}" var="dto">
		img_list.push({
			festival_img_seq: ${dto.festival_img_seq},
			img: `${dto.img}`,
			festival_seq: ${dto.festival_seq}
		});
	</c:forEach>
	
	/* Slick responsive setting */
	$(window).resize(function() {
	
		if ($('.modal-content').css('width') == '800px') {
			$('.image-slider').css('width', '800px');
			$('.image-slider div').css('width', '800px');
		} else {
			$('.image-slider').css('width', '500px');
			$('.image-slider div').css('width', '500px');
		}
	});
	
	/* Search */
	<c:if test="${map.searchStatus == 'y'}">
		$('#search-field').val('${map.word}');
	</c:if>
	
	$(document).keydown(function(event) {
	    if (event.key === 'F5') {
			location.href='/dd/admin/activity/festival/view.do';
	    }
	});


</script>