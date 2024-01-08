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
	color: #012970;
}

.active>.page-link, .page-link.active {
	z-index: 3;
	color: var(- -bs-pagination-active-color);
	background-color: #012970;
	border-color: #012970;
}

.answer-complete:hover {
	color: red; /* 변경할 색상 설정 */
	cursor: pointer; /* 마우스 커서 모양 변경 */
}

.modal-body {
	padding: 20px; /* 내용과 모달 경계 간격 설정 */
	background-color: #f7f7f7; /* 모달 내부 배경색 설정 */
	border-radius: 10px; /* 모달 모서리 둥글게 설정 */
}

.modal-content {
	border-radius: 10px;
}

/* content와 answer 사이의 구분선 스타일 */
.modal-separator {
	height: 1px;
	background-color: #ddd; /* 구분선의 색상 설정 */
	margin: 20px 0; /* 구분선과 내용 사이의 여백 설정 */
}

#cancelBtn {
    display: block;
    margin: 20px auto 0;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    color: #fff;
    background-color: #007bff;
    margin-top: 80px;
}

#cancelBtn:hover {
    background-color: #0056b3;
}
</style>

<!-- ======= Main ======= -->
<main id="main" class="main">

	<div class="pagetitle">
		<h1>이용문의 내역</h1>
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

								<form id="cancelForm"
									action="/dd/member/mypage/inquiry/inquiry/delete.do"
									method="post">
									<table class="table">
										<thead>
											<tr>
												<th></th>
												<th>문의 유형</th>
												<th>문의 제목</th>
												<th>등록일</th>
												<th>답변 상태</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list}" var="dto">
												<tr>
													<td><input type="checkbox" name="selectedInquiry"
														value="${dto.inquiry_seq}"></td>
													<td>${dto.type}</td>
													<td>${dto.subject}</td>
													<td>${dto.regdate}</td>
													<td><c:choose>
															<c:when test="${dto.answer != null}">
																<a
																	onclick="showModal(`${dto.subject}`, `${dto.content}`, `${dto.answer}`)"
																	class="answer-complete"><b>답변완료</b></a>
															</c:when>
															<c:otherwise>
										                    답변대기
										                </c:otherwise>
														</c:choose></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<button type="button" id="cancelBtn" onclick="confirmCancel()">문의삭제</button>
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}">
								</form>

								<!-- 모달 -->

								<div id="modal" class="modal fade show" tabindex="-1"
									aria-labelledby="exampleModalScrollableTitle" aria-modal="true"
									role="dialog">
									<div
										class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
										<div class="modal-content">
											<div class="modal-header">
												<h5 id="modal-subject" class="modal-title"></h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<div class="mt-3" id="modal-content"
													style="margin-bottom: 30px;"></div>
												<!-- <div class="modal-separator"></div> content와 answer 사이의 구분선 -->
											</div>
											<div class="modal-header">
												<h5 id="modal-subject" class="modal-title">답변</h5>
											</div>
											<div class="modal-body">
												<div class="mt-3" id="modal-answer"
													style="margin-bottom: 30px;"></div>
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
								href="/dd/member/mypage/inquiry/inquiry/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</nav>
	</section>

</main>

<script>
function confirmCancel() {
	// 선택된 체크박스가 있다면
	if ($("input[name='selectedInquiry']:checked").length > 0) {

		var result = confirm("정말 문의를 삭제하시겠습니까?");
		if (result) {

			// 삭제 form 설정 및 제출
			$('#cancelForm').attr('action',
					'/dd/member/mypage/inquiry/inquiry/delete.do');
			$('#cancelForm').submit();
		}
	} else{
		alert("삭제할 문의를 선택해주세요.");
	}
}
    
   
	//모달
	function showModal(subject, content, answer) {
	    $('#modal-subject').text(subject);
	    $('#modal-content').text(content);
	    $('#modal-answer').text(answer);
	    
	    $('#modal').modal('show');
	}
</script>