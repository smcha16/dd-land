<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
#header {
	height: 90px !important;
	padding-left: 0;
}

.logo {
	width: 68px;
}

section {
	padding: 0;
}

#main h1 {
	margin-top: 45px !important;
}

h1 {
	font-size: 2rem !important;
	margin-left: 10px;
	text-align: center;
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

#page-bar {
	margin-top: 50px;
}

.page-link {
	color: #CE1212;
}

.active>.page-link, .page-link.active {
	z-index: 3;
	color: var(- -bs-pagination-active-color);
	background-color: #CE1212;
	border-color: #CE1212;
}

.table td a:hover {
	font-weight: bold !important;
    color: #0d6efd !important;
}
</style>

<!-- ======= Main ======= -->
<main id="main" class="main">

	<div class="pagetitle">
		<h1>리뷰 내역</h1>
	</div>

	<section class="section">
		<div class="row">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-12">

						<div class="card">
							<div class="card-body">

								<nav class="d-flex justify-content-end">
									<ol class="breadcrumb">
										<!-- <li class="breadcrumb-item"><a href="index.html">추가</a></li>
										<li class="breadcrumb-item"><a href="#">수정</a></li>
										<li class="breadcrumb-item active"><a href="#">삭제</a></li> -->
									</ol>
								</nav>

								<!-- <form action="/dd/member/mypage/review/delete.do" method="post"> -->
								<form id="reviewForm" method="post">
									<table class="table">
										<thead>
											<tr>
												<th></th>
												<th>제목</th>
												<th>등록일</th>
												<th>조회수</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list}" var="dto">
												<tr>
													<td><input type="checkbox" name="selectedReview"
														value="${dto.review_seq}"></td>
													<td><a onclick="showModal(`${dto.subject}`, `${dto.content}`, '${dto.imgList}')"><b><c:out value="${dto.subject}" /></b></a></td>
													<td>${dto.regdate}</td>
													<td>${dto.readcount}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<!-- <button type="submit" id="delete-button">리뷰 삭제</button> -->
									<!-- <button type="submit" id="modify-button">리뷰 수정</button> -->
									<button type="button" onclick="deleteReviews()">리뷰 삭제</button>
									<button type="button" onclick="modifyReviews()">리뷰 수정</button>
									<!-- <button type="button" onclick="location.href='/dd/member/mypage/review/delete.do?seq=${dto.review_seq}'">리뷰 삭제</button> -->
									<%-- <button type="button" onclick="location.href='/dd/member/mypage/review/edit.do?seq=${dto.review_seq}';">리뷰 수정</button> --%>
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}">
								</form>
								
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
								                <div class="d-flex align-items-center justify-content-center">
								                    <img id="modal-image" src="" alt="Image" style="max-width: 100%;">
								                </div>
								            </div>
								        </div>
								    </div>
								</div>


							</div>

						</div>
					</div>

				</div>
			</div>

		</div>
		<nav id="page-bar" aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<c:forEach begin="1" end="${map.totalPages}" varStatus="pageStatus">
					<c:choose>
						<c:when test="${pageStatus.index == currentPage}">
							<li class="page-item active"><span class="page-link">${pageStatus.index}</span></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="/dd/member/mypage/review/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</nav>
	</section>

</main>
<script>
	function deleteReviews() {
		// 선택된 체크박스가 있다면
		if ($("input[name='selectedReview']:checked").length > 0) {

			var result = confirm("정말 예매를 취소하시겠습니까?");
			if (result) {

				// 삭제 form 설정 및 제출
				$('#reviewForm').attr('action',
						'/dd/member/mypage/review/delete.do');
				$('#reviewForm').submit();
			}
		}
	}

	 function modifyReviews() {
		    // 선택된 체크박스들의 seq 값을 담을 배열
		    let selectedSeqs = [];

		    // 선택된 체크박스가 있다면
		    $("input[name='selectedReview']:checked").each(function() {
		        selectedSeqs.push($(this).val()); // 선택된 각 체크박스의 seq 값을 배열에 추가
		    });

		    // 배열에 값이 있는지 확인
		    if (selectedSeqs.length === 0) {
		        // 선택된 리뷰가 없는 경우
		        alert('수정할 리뷰를 선택해주세요.');
		    } else if (selectedSeqs.length === 1) {
		        // 선택된 리뷰가 1개인 경우
		        // 선택된 seq를 edit.do로 전송
		        location.href = '/dd/member/mypage/review/edit.do?seq=' + selectedSeqs[0];
		    } else {
		        // 선택된 리뷰가 2개 이상인 경우
		        alert('수정할 리뷰는 1개만 선택해주세요.');
		    }
		}
	 
	 <!-- 모달 -->
		
		function showModal(subject, content, image) {
		    $('#modal-subject').text(subject);
		    $('#modal-content').text(content);
		    
		    if (image) {
		        $('#modal-image').attr('src', '/dd/resources/files/review/' + image);
		    } else {
		    	$('#modal-image').hide();
		    }

		    $('#modal').modal('show');
		}
</script>