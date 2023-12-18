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
		<h1>어트랙션 예약 관리</h1>
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
                      					<li class="breadcrumb-item"><a href="/dd/admin/activity/attraction/reservation/add.do">추가</a></li>
                      					<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="edit()">수정</a></li>
                      					<li class="breadcrumb-item active"><a href="javascript:void(0);" onclick="del()">삭제</a></li>
                    				</ol>
								</nav>
                  
                  				<form id="del-form" method="POST" action="/dd/admin/activity/attraction/reservation/del.do">
	                  				<table class="table">
	                    				<thead>
	                      					<tr>
	                        					<th></th>
	                        					<th>No</th>
	                        					<th>어트랙션명</th>
	                        					<th>예약일</th>
	                        					<th>예약 인원</th>
	                        					<th>예약자 성함(아이디)</th>
	                      					</tr>
	                    				</thead>
	                    				<tbody>
	                    					<c:forEach items="${list}" var="dto" varStatus="status">
		                      					<tr>
		                        					<td><input type="checkbox" name="book_user_seq" value="${dto.book_user_seq}"></td>
		                        					<td>${map.totalPosts - status.index - map.startIndex + 1}</td>
		                        					<td>${dto.attraction_name}</td>
		                        					<td>${dto.regdate}</td>
		                        					<td>${dto.capacity}</td>
		                        					<td>${dto.user_name}(${dto.email})</td>
		                        					<%-- <td><a onclick="showModal('${dto.attraction_seq}', `${dto.name}`,`${dto.info}`,'${dto.capacity}', `${dto.time}`,`${dto.restriction}`)"><c:out value="${dto.name}" /></a></td> --%>
		                      					</tr>
	                      					</c:forEach>
	                   					</tbody>
                  					</table>
	                  					
                  					<!-- 토큰 -->
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                				</form>
                  					
                				<%-- <!-- 어트랙션 상세 모달 -->
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
								</div> --%>
                				
								<!-- 페이징 -->
								<nav id="page-bar" aria-label="Page navigation example">
									<ul class="pagination justify-content-center">
										<c:forEach begin="1" end="${map.totalPages}" varStatus="pageStatus">
											<c:choose>
												<c:when test="${pageStatus.index == currentPage}">
													<li class="page-item active"><span class="page-link">${pageStatus.index}</span></li>
												</c:when>
												<c:otherwise>
													<li class="page-item"><a class="page-link" href="/dd/admin/activity/attraction/reservationView.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
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
	
	
</script>