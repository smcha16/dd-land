<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
	color: #0d6efd;
}

.table {
	text-align: center;
}

th {
	background-color: #f2f2f2 !important;
}

th:nth-child(1) { width: 8%; }
th:nth-child(2) { width: 8%; }
th:nth-child(3) { width: 10%; }
th:nth-child(4) { width: 30%; }
th:nth-child(5) { width: 22%; }
th:nth-child(6) { width: 22%; }

.table td i {
	color: #0d6efd;
	margin-top: 7px;
}

.table td a {
	color: #000;
}

.table td a:hover {
	color: #0d6efd;
}

.pagination {
	justify-content: center;
	margin-top: 40px;
}

.form-check {
	min-height: 0 !important;
}

/* 모달 CSS */
#modal table.m-desc {
	width: 100%;
	font-size: 14px;
}

#modal table tr>th {
	width: 120px;
	text-align: left;
	font-weight: bold;
	background: #FFF !important;
	padding: 10px;
}

#modal table tr>td {
	padding: 10px;
}
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>MBTI별 추천 관리</h1>
	</div>

	<section class="section">
		<div class="row">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-12">

						<div id="search" class="header">
                  			<form method="GET" action="/dd/admin/test/mbti/view.do" class="search-form d-flex align-items-center">
                    			<input type="text" name="word" id="search-field" placeholder="제목 또는 내용을 입력하세요." autocomplete="off">
                    			<button type="submit"><i class="bi bi-search"></i></button>
                  			</form>
              			</div>
						
						<!-- MBTI별 추천 상세 모달 -->
						<div id="modal" class="modal fade show" tabindex="-1" aria-labelledby="exampleModalScrollableTitle" aria-modal="true" role="dialog">
						    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
						        <div class="modal-content">
						            <div class="modal-header">
						                <h5 id="modal-name" class="modal-title"></h5>
						                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						            </div>
					                
						            <div class="modal-body">
						            	<table class="m-desc">
						            		<colgroup>
						            			<col style="width: 100px">
						            		</colgroup>
						            		<tbody>
						            			<tr>
						            				<th>이미지
						            				<td>
								            			<div class="d-flex">
									                    	<img id="modal-image" src="" alt="Image" style="max-width: 100%;">
									                	</div>
								                	</td>
						            			</tr>
						            			<tr>
						            				<th>
						            				<td class="m-mbti_img"></td>
						            			</tr>
						            			<tr>
						            				<th>결과</th>
						            				<td class="m-result"></td>
						            			</tr>
						            			<tr>
						            				<th>어트랙션</th>
						            				<td class="m-attraction_name"></td>
						            			</tr>
						            			<tr>
						            				<th>코스</th>
						            				<td class="m-course_name"></td>
						            			</tr>
						            		</tbody>
						            	</table>
						            </div>
						        </div>
						    </div>
						</div>

						<div class="card">
							<div class="card-body">

								<nav class="d-flex justify-content-end">
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="/dd/admin/test/mbti/add.do">추가</a></li>
										<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="edit()">수정</a></li>
										<li class="breadcrumb-item active"><a href="javascript:void(0);" onclick="del()">삭제</a></li>
									</ol>
								</nav>

								<form id="del-form" method="POST" action="/dd/admin/test/mbti/del.do">
									<table class="table">
										<thead>
											<tr>
												<th></th>
												<th>No</th>
												<th>이름</th>
												<th>결과</th>
												<th>어트랙션</th>
												<th>코스</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${listMBTI}" var="dto" varStatus="status">
												<tr>
													<td><input type="checkbox" name="mbti_seq" value="${dto.mbti_seq}"></td>
													<td>${map.totalPosts - status.index - map.startIndex + 1}</td>
													<td><a onclick="showModal('${dto.name}','${dto.result}', '${dto.mbti_img}', '${dto.attraction_name}', '${dto.course_name}')"><c:out value="${dto.name}" /></a></td>
													<td>${fn:substring(dto.result, 0, 20)}${fn:length(dto.result) > 20 ? '...' : ''}</td>
													<td>${fn:substring(dto.attraction_name, 0, 15)}${fn:length(dto.attraction_name) > 15 ? '...' : ''}</td>
													<td>${fn:substring(dto.course_name, 0, 15)}${fn:length(dto.course_name) > 15 ? '...' : ''}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
			
									<ul class="pagination justify-content-center">
										<c:forEach begin="1" end="${map.totalPages}"
											varStatus="pageStatus">
											<c:choose>
												<c:when test="${pageStatus.index == currentPage}">
													<li class="page-item active"><span class="page-link">${pageStatus.index}</span></li>
												</c:when>
												<c:otherwise>
													<li class="page-item"><a class="page-link" href="/dd/admin/test/mbti/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</ul>

									<!-- 토큰 -->
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</main>

<script>
	function edit() {
		// 선택된 체크박수 개수 확인
	    let checkboxes = $('input[type="checkbox"][name="mbti_seq"]');
	    
	    if (checkboxes.filter(':checked').length === 1) {
	        const seq = checkboxes.filter(':checked').val();
	        location.href = '/dd/admin/test/mbti/edit.do?seq=' + seq;
	    } else {
	        alert('1개의 MBTI별 추천을 선택 후, 수정 버튼을 눌러주세요.');
	    }
	}
	
	function del() {
		// 선택된 체크박수 개수 확인
	    let checkboxes = $('input[type="checkbox"][name="mbti_seq"]');
		
	    if (checkboxes.filter(':checked').length >= 1) {
	    	if (confirm(checkboxes.filter(':checked').length + "개의 MBTI별 추천을 삭제하시겠습니까?")) {
	            $('#del-form').submit();
	        }
	    } else {
	        alert('1개 이상의 MBTI별 추천을 선택 후, 삭제 버튼을 눌러주세요.');
	    }
	}

	// 검색
	<c:if test="${map.searchStatus == 'y'}">
		$('#search-field').val('${map.word}');
	</c:if>
	
	$(document).keydown(function(event) {
	    if (event.key === 'F5') {
			location.href='/dd/admin/test/worldcup/course/list.do';
	    }
	});
	
	// 어트랙션 월드컵 상세 모달
	function showModal(name, result, mbti_img, attraction_name, course_name) {
	    
		$('#modal-name').text(name);
		$('#modal-image').attr('src', '/dd/resources/files/test/mbti/' + mbti_img);
        
	    var imgText = mbti_img;
	    if (mbti_img.includes('_')) {
	    	imgText = mbti_img.substring(mbti_img.indexOf('_') + 1);
	    }
	    $('.m-mbti_img').text(imgText);

	    $('.m-result').text(result);
	    $('.m-attraction_name').text(attraction_name);
	    $('.m-course_name').text(course_name);
	    
	    $('#modal').modal('show');
	}
</script>