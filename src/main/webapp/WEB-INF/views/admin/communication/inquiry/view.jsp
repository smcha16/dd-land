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
  	table {
    	text-align: center;
    	margin-top: 30px;
  	}
  	th {
    	background-color: #f2f2f2 !important;
  	}
	th:nth-child(1) {
		width: 10%;
	}
	th:nth-child(2) {
		width: 13%;
	}
	th:nth-child(3) {
		width: 35%;
	}
	th:nth-child(4) {
		width: 15%;
	}
	th:nth-child(5) {
		width: 15%;
	}
	th:nth-child(6) {
		width: 12%;
	}
	td {
    	vertical-align: middle;
	}
  	td i {
		color: #0d6efd;
		margin-top: 7px;
	}
	td a {
		color: #000;
	}
	td a:hover {
		font-weight: bold !important;
      	color: #0d6efd !important;
    }
  	.pagination {
   		justify-content: center;
   		margin-top: 40px;
  	}
</style>

<main id="main" class="main">

    <div class="pagetitle">
		<h1>이용문의 관리</h1>
    </div>

	<section class="section">
		<div class="row">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-12">
					
						<!-- 검색 -->

              			<div id="search" class="header">
                  			<form method="GET" action="#" class="search-form d-flex align-items-center">
                    			<input type="text" name="query" placeholder="Search">
                    			<button type="submit"><i class="bi bi-search"></i></button>
                  			</form>
              			</div>

						<div class="card">
                			<div class="card-body">
                			
                			<!-- 테이블 -->
								
								<table class="table">
									<thead>
										<tr>
											<th>No</th>
											<th>문의유형</th>
											<th>제목</th>
											<th>작성자</th>
											<th>등록일</th>
											<th>답변상태</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="dto" varStatus="numberStatus">
											<tr>
												<td>${map.totalPosts - numberStatus.index - map.startIndex + 1}</td>
												<td>${dto.type}</td>
									            <td>
									            	<a onclick="showModal('${dto.inquiry_seq}', `${dto.subject}`, `${dto.content}`, '${dto.attach}', `${dto.answer}`)">
									            		<c:out value="${fn:substring(dto.subject, 0, 20)}" />
                                                		${fn:length(dto.subject) > 20 ? '...' : ''}
                                                	</a>
                                                </td>
									            <td>${dto.name}(${dto.email})</td>
									            <td>${fn:substring(dto.regdate, 0, 10)}</td>
									            <td>${empty dto.answer ? '미완료' : '완료'}</td>
									        </tr>
										</c:forEach>
									</tbody>
								</table>
								
								<!-- 모달 -->
								
								<div id="modal" class="modal fade show" tabindex="-1" aria-labelledby="exampleModalScrollableTitle" aria-modal="true" role="dialog">
								    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
								    
								        <div class="modal-content">
								            <div class="modal-header">
								                <h5 id="modal-subject" class="modal-title"></h5>
								                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								            </div>
								            
								            <div class="modal-body">
								            	<div class="mt-3" id="modal-content" style="margin-bottom: 30px;"></div>
								                <div class="d-flex justify-content-center">
								                    <img id="modal-image" src="" alt="Image" style="max-width: 100%; margin-bottom: 50px;">
								                </div>
								                
							                    <form method="POST" action="/dd/admin/communication/inquiry/answer.do" id="answer-form">
							                    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
							                    	<input type="hidden" name="inquiry_seq" id="inquiry_seq">
							                    	
											        <div class="mb-3">
											            <label for="modal-answer" class="col-form-label"><i class="bi bi-send"></i> 답변</label>
											            <textarea name="answer" id="modal-answer" class="form-control"></textarea>
											        </div>
												</form>
											</div>
											
										    <div class="modal-footer">
										        <button type="button" id="add-button" class="btn btn-primary" onclick="submit()">답변 등록</button>
    											<button type="button" id="edit-button" class="btn btn-primary" onclick="submit()">답변 수정</button>
										    </div>
								        </div>
									</div>
								</div>
								
								<!-- 페이징 -->
								
                 				<ul class="pagination justify-content-center">
									<c:forEach begin="1" end="${map.totalPages}" varStatus="pageStatus">
										<c:choose>
										    <c:when test="${pageStatus.index == currentPage}">
										        <li class="page-item active"><span class="page-link">${pageStatus.index}</span></li>
										    </c:when>
										    <c:otherwise>
										        <li class="page-item"><a class="page-link" href="/dd/admin/communication/inquiry/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
										    </c:otherwise>
										</c:choose>
									</c:forEach>
								</ul>
								
               				</div>
             			</div>
             			
           			</div>
				</div>
			</div>
		</div>
	</section>
	
</main>

<script>
	<!-- 모달 -->
	
	function showModal(seq, subject, content, image, answer) {
	    $('#inquiry_seq').val(seq);
	    $('#modal-subject').text(subject);
	    $('#modal-content').text(content);
	    $('#modal-answer').val(answer);
	    
	    if (image) {
	        $('#modal-image').attr('src', '/dd/resources/files/communication/inquiry/' + image);
	    } else {
	    	$('#modal-image').hide();
	    }
	    
	    if (answer) {
	    	$('#edit-button').show();
	        $('#add-button').hide();
	    } else {
	    	$('#add-button').show();
	    	$('#edit-button').hide();
	    }

	    $('#modal').modal('show');
	}
	
	<!-- 전송 -->
	
	function submit() {
        $('#answer-form').submit();
    }
</script>