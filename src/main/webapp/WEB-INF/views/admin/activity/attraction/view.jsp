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
</style>

<!-- ======= Main ======= -->
<main id="main" class="main">

    <div class="pagetitle">
		<h1>어트랙션 관리</h1>
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
                      					<li class="breadcrumb-item"><a href="/dd/admin/activity/attraction/add.do">추가</a></li>
                      					<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="edit()">수정</a></li>
                      					<li class="breadcrumb-item active"><a href="javascript:void(0);" onclick="del()">삭제</a></li>
                    				</ol>
								</nav>
                  
                  				<form id="del-form" method="POST" action="/dd/admin/activity/attraction/del.do">
	                  				<table class="table">
	                    				<thead>
	                      					<tr>
	                        					<th></th>
	                        					<th>No</th>
	                        					<th>이름</th>
	                        					<th>수용인원</th>
	                        					<th>이미지</th>
	                        					<th>위치</th>
	                      					</tr>
	                    				</thead>
	                    				<tbody>
	                    					<c:forEach items="${list}" var="dto" varStatus="status">
		                      					<tr>
		                        					<td><input type="checkbox" name="attraction_seq" value="${dto.attraction_seq}"></td>
		                        					<td>${map.totalPosts - status.index - map.startIndex + 1}</td>
		                        					<td><a onclick="showModal('${dto.attraction_seq}', `${dto.name}`,`${dto.info}`,'${dto.capacity}', `${dto.time}`,`${dto.restriction}`)"><c:out value="${dto.name}" /></a></td>
		                        					<td>${dto.capacity}</td>
		                        					<c:if test="${dto.img == 'attraction.png'}">
		                        						<td></td>
		                        					</c:if>
		                        					<c:if test="${dto.img != 'attraction.png'}">
		                        						<td><i class="bi bi-image"></i></td>
		                        					</c:if>
		                        					<td><i class="bi bi-geo-alt"></i></td>
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
								            <!-- 이미지 슬라이드가 들어갈 자리 -->
							                <!-- <div class="d-flex align-items-center justify-content-center">
							                    <img id="modal-image" src="" alt="Image" style="max-width: 100%;">
							                </div> -->
							                <div class="image-slider">
													<!-- <div>
														<img id="modal-image" src="" alt="Image">
													</div> -->
											</div>
							                
								            <div class="modal-body">
								            	<div class="m-info"></div>
								            	<table class="m-desc">
								            		<colgroup>
								            			<col style="width: 100px">
								            			<col>
								            		</colgroup>
								            		<tbody>
								            			<tr>
								            				<th>수용인원</th>
								            				<td class="m-capacity"></td>
								            			</tr>
								            			<tr>
								            				<th>시간</th>
								            				<td class="m-time"></td>
								            			</tr>
								            			<tr>
								            				<th>제한사항</th>
								            				<td class="m-restriction"></td>
								            			</tr>
								            		</tbody>
								            	</table>
								            </div>
								        </div>
								    </div>
								</div>
<!-- 								<div id="modal" class="modal fade show" tabindex="-1" aria-labelledby="exampleModalScrollableTitle" aria-modal="true" role="dialog">
								    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
								        <div class="modal-content">
								            <div class="modal-header">
								                <h5 id="modal-name" class="modal-title"></h5>
								                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								            </div>
								            <div class="modal-body">
								                <div class="d-flex align-items-center justify-content-center">
								                    <img id="modal-image" src="" alt="Image" style="max-width: 100%;">
								                </div>
								            </div>
								        </div>
								    </div>
								</div> -->

								<!-- 페이징 -->
								<nav id="page-bar" aria-label="Page navigation example">
									<ul class="pagination justify-content-center">
										<c:forEach begin="1" end="${map.totalPages}" varStatus="pageStatus">
											<c:choose>
												<c:when test="${pageStatus.index == currentPage}">
													<li class="page-item active"><span class="page-link">${pageStatus.index}</span></li>
												</c:when>
												<c:otherwise>
													<li class="page-item"><a class="page-link" href="/dd/admin/activity/attraction/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
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
			alert('1개의 어트랙션을 선택 후, 수정 버튼을 눌러주세요.');
		} else {

			const seq = $('input[type="checkbox"]:checked').val();
			
			location.href='/dd/admin/activity/attraction/edit.do?seq=' + seq;
						
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
	
	/* 모달 */
	function showModal(seq, name, info, capacity, time, restriction) {
	    
		addModalImg(seq);
		
		$('#modal-name').text(name);
        $('.m-info').text(info);
        $('.m-capacity').text(capacity);
        $('.m-time').text(time);
        $('.m-restriction').text(restriction);

        $('#modal').modal('show');
	}
	
	function addModalImg(seq) {
		
		const filterImg = img_list.filter(obj => obj.attraction_seq == seq);
		console.log(filterImg);
		
		if (filterImg.length > 0) {
			
			filterImg.forEach(obj => {
				console.log(obj.img);
				$('image-slider').append(`
				
					<div>
						<img class="modal-image" src="/dd/resources/files/activity/attraction/" + obj.img alt="Image">
					</div>
						
				`);
				$('modal-body').append(`
				
					<div>
						<img class="modal-image" src="/dd/resources/files/activity/attraction/" + obj.img alt="Image">
					</div>
						
				`);
				console.log('bye');
			});
			
		}
			
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
	
	/* $(".slider").not('.slick-initialized').slick() */
	
	/* 모달 슬라이드용 img 배열에 넣기 */
	const img_list = new Array();
	<c:forEach items="${ilist}" var="dto">
		img_list.push({
			attraction_img_seq: ${dto.attraction_img_seq},
			img: `${dto.img}`,
			attraction_seq: ${dto.attraction_seq}
		});
	</c:forEach>
	
</script>