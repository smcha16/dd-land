<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
	
	.m-story {
		padding: 10px;
	}
	
	.modal-body {
		padding: 0;
	}
	
	#preview-modal .modal-content {
		position: relative;
		display: flex;
		flex-direction: column;
		width: unset;
		color: var(--bs-modal-color);
		pointer-events: auto;
		background-color: var(--bs-modal-bg);
		background-clip: padding-box;
		border: var(--bs-modal-border-width) solid var(--bs-modal-border-color);
		border-radius: var(--bs-modal-border-radius);
		outline: 0;
	}
	
</style>

<!-- ======= Main ======= -->
<main id="main" class="main">

    <div class="pagetitle">
		<h1>영화 관리</h1>
    </div>

	<section class="section">
		<div class="row">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-12">

						<!-- 검색 -->
              			<div id="search" class="header">
                  			<form method="GET" action="/dd/admin/activity/movie/view.do" class="search-form d-flex align-items-center">
                    			<input type="text" name="word" placeholder="영화명 또는 줄거리를 입력하세요." autocomplete="off">
                    			<button type="submit" title="Search"><i class="bi bi-search"></i></button>
                    			
                    			<!-- 토큰 -->
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                  			</form>
              			</div>

						<div class="card">
                			<div class="card-body">

                  				<nav class="d-flex justify-content-end">
                    				<ol class="breadcrumb">
                      					<li class="breadcrumb-item"><a href="/dd/admin/activity/movie/add.do">추가</a></li>
                      					<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="edit()">수정</a></li>
                      					<li class="breadcrumb-item active"><a href="javascript:void(0);" onclick="del()">삭제</a></li>
                    				</ol>
								</nav>
                  
                  				<form id="del-form" method="POST" action="/dd/admin/activity/movie/del.do">
	                  				<table class="table">
	                    				<thead>
	                      					<tr>
	                        					<th></th>
	                        					<th>No</th>
	                        					<th>이름</th>
	                        					<th>줄거리</th>
	                        					<th>예고편</th>
	                      					</tr>
	                    				</thead>
	                    				<tbody>
	                    					<c:forEach items="${list}" var="dto" varStatus="status">
		                      					<tr>
		                        					<td><input type="checkbox" name="movie_seq" value="${dto.movie_seq}"></td>
		                        					<td>${map.totalPosts - status.index - map.startIndex + 1}</td>
		                        					<td><a onclick="showModal('${dto.movie_seq}', `${dto.name}`,`${dto.story}`,'${dto.runningtime}', `${dto.img}`)"><c:out value="${dto.name}" /></a></td>
		                        					<td><c:out value="${fn:substring(dto.story, 0, 30)}" />
		                        						<c:if test="${fn:length(dto.story) > 30}">
		                        							...
		                        						</c:if>
		                        					</td>
		                        					<c:if test="${dto.preview == null || dto.preview == ''}">
		                        						<td></td>
	                        						</c:if>
		                        					<c:if test="${dto.preview != null && dto.preview != ''}">
		                        						<td><a onclick="showPreviewModal('${dto.movie_seq}', `${dto.name}`)"><i class="bi bi-file-earmark-play"></i></a></td>
	                        						</c:if>
		                      					</tr>
	                      					</c:forEach>
	                   					</tbody>
                  					</table>
	                  					
                  					<!-- 토큰 -->
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                				</form>
                  					
                				<!-- 영화 상세 모달 -->
								<div id="modal" class="modal fade show" tabindex="-1" aria-labelledby="exampleModalScrollableTitle" aria-modal="true" role="dialog">
								    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
								        <div class="modal-content">
								            <div class="modal-header">
								                <h5 id="modal-name" class="modal-title"></h5>
								                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								            </div>
							                
							                
								            <div class="modal-body">
								            	<!-- 포스터 -->
								            	<div class="d-flex align-items-center justify-content-center">
								            		<img id="m-image" alt="Poster" src="" style="max-width: 100%;">
								            	</div>
								                <!-- 줄거리 -->
								            	<div class="m-story"></div>
								            	<!-- 상세 -->
								            	<table class="m-desc">
								            		<colgroup>
								            			<col style="width: 100px">
								            			<col>
								            		</colgroup>
								            		<tbody>
								            			<tr>
								            				<th>러닝타임</th>
								            				<td class="m-runningtime"></td>
								            			</tr>
								            		</tbody>
								            	</table>
								            </div>
								        </div>
								    </div>
								</div>
                				
                				<!-- 영화 예고편 모달 -->
								<div id="preview-modal" class="modal fade show" tabindex="-1" aria-labelledby="exampleModalScrollableTitle" aria-modal="true" role="dialog">
								    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
								        <div class="modal-content">
								            <div class="modal-header">
								                <h5 id="preview-modal-name" class="modal-title"></h5>
								                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								            </div>
							                
								            <div class="modal-body">
								            	<div class="preview" style="max-width: 100%; display: flex; justify-content: center; padding: 10px;"></div>
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

<script>

	/* 수정 시, 체크 박스 1개만 선택 하여 seq 전달 하기 */
	function edit() {
		
		//선택된 체크박수 개수 확인
		let checkedCount = $('input[type="checkbox"]:checked').length;
		
		//1개 이상? out!
		if (checkedCount > 1 || checkedCount < 1) {
			alert('1개의 영화를 선택 후, 수정 버튼을 눌러주세요.');
		} else {
	
			const seq = $('input[type="checkbox"]:checked').val();
			
			location.href='/dd/admin/activity/movie/edit.do?seq=' + seq;
		}
		
	}

	/* 삭제 시, 체크 박스 1개 이상 선택 하여 seq 전달하기 */
	function del() {
		
		//선택된 체크박수 개수 확인
		let checkedCount = $('input[type="checkbox"]:checked').length;
		
		if (checkedCount == 0) {
			alert('1개 이상의 영화를 선택 후, 삭제 버튼을 눌러주세요.');
		} else {
			
			if (confirm('선택한 영화를 삭제하시겠습니까?')) {
				$('#del-form').submit();
			}
		} 
		
	}
	
	/* 영화 상세 모달 */
	function showModal(seq, name, story, runningtime, img) {
	    
		$('#modal-name').text(name);
		$('#m-image').attr('src', '/dd/resources/files/activity/movie/' + img);
        $('.m-story').html(story);
        $('.m-runningtime').text(runningtime);
        
        $('#modal').modal('show');
	}
	
	/* 영화 예고편 모달 */
	function showPreviewModal(seq, name) {
		
		$('#preview-modal-name').text(name);
		
		let result = moviePreview.find(obj => obj.movie_seq == seq);
		
		$('.preview').html(result.preview);

		$('#preview-modal').modal('show');
	}
	
	/* 영화 예고편 태그 배열에 넣기 */
	const moviePreview = new Array();
	<c:forEach items="${list}" var="dto">
		moviePreview.push({
			movie_seq: ${dto.movie_seq},
			preview: `${dto.preview}`
		});
	</c:forEach>
	
	/* 검색 */
	<c:if test="${map.searchStatus == 'y'}">
		$('#search-field').val('${map.word}');
	</c:if>
	
	$(document).keydown(function(event) {
	    if (event.key === 'F5') {
			location.href='/dd/admin/activity/movie/view.do';
	    }
	});
	
	
</script>