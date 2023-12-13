
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

.table th:nth-child(1) { width: 6%; }
.table th:nth-child(2) { width: 9%; }
.table th:nth-child(3) { width: 63%; }
.table th:nth-child(4) { width: 22%; }

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
</style>

<!-- ======= Main ======= -->
<main id="main" class="main">

	<div class="pagetitle">
		<h1>코스 월드컵</h1>
	</div>

	<section class="section">
		<div class="row">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-12">

						<div id="search" class="header">
							<form class="search-form d-flex align-items-center" method="POST"
								action="#">
								<input type="text" name="query" placeholder="Search"
									title="Enter search keyword">
								<button type="submit" title="Search">
									<i class="bi bi-search"></i>
								</button>
							</form>
						</div>

						<div class="card">
							<div class="card-body">

								<nav class="d-flex justify-content-end">
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a
											href="/dd/admin/test/worldcup/course/add.do">추가</a></li>
										<li class="breadcrumb-item"><a
											href="/dd/admin/test/worldcup/course/edit.do">수정</a></li>
										<li class="breadcrumb-item active"><a href="#">삭제</a></li>
									</ol>
								</nav>

								<table class="table">
									<thead>
										<tr>
											<th></th>
											<th>No</th>
											<th>이름</th>
											<th>테스트 채택</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${listCourse}" var="dto">
											<tr>
												<td><input type="checkbox"></td>
												<td>${dto.course_seq}</td>
												<td>${dto.name}</td>
												<td>
													<div class="d-flex justify-content-center">
														<div class="form-check form-switch">
															<c:choose>
																<c:when test="${dto.is_test eq 'Y'}">
																	<input class="form-check-input" type="checkbox"
																		id="flexSwitchCheckDefault" checked>
																</c:when>
																<c:otherwise>
																	<input class="form-check-input" type="checkbox"
																		id="flexSwitchCheckDefault">
																</c:otherwise>
															</c:choose>
														</div>
													</div>
												</td>
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
												<li class="page-item"><a class="page-link"
													href="/dd/admin/test/worldcup/course/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
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
	// 문서가 완전히 로드 된 뒤에 실행
	$(document).ready(
			function() {
				// 체크박스 클릭 이벤트
				$(document).on(
						'change',
						'.form-check-input',
						function() {
							// 테스트 채택
							var isTest = $(this).is(':checked') ? 'Y' : 'N';
							//console.log(isTest);

							// 선택한 어트랙션 일련번호
							var courseSeq = $(this).closest('tr').find(
									'td:nth-child(2)').text();
							//console.log(courseSeq);

							// CSRF token
							var csrfHeaderName = "${_csrf.headerName}";
							var csrfTokenValue = "${_csrf.token}";
							//console.log(csrfHeaderName);
							//console.log(csrfTokenValue);

							// 데이터베이스 업데이트
							$.ajax({
								type : 'POST',
								url : '/dd/admin/test/worldcup/course/view.do',
								data : {
									courseSeq : courseSeq,
									isTest : isTest
								},
								beforeSend : function(xhr) {
									xhr.setRequestHeader(csrfHeaderName,
											csrfTokenValue);
								},
								/*
								success: function(response) {
									// console.log(response); // 응답 처리
								},
								 */
								error : function(a, b, c) {
									console.error(a, b, c);
								}
							});
						});
			});
</script>
