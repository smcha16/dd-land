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
		<h1>어트랙션 예약내역</h1>
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

								<form action="/dd/member/mypage/attraction/delete.do"
									method="post" id="cancelForm">
									<table class="table">
										<thead>
											<tr>
												<th></th>
												<th>어트랙션</th>
												<th>예약날짜</th>
												<th>예약시간</th>
												<th>예약인원</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list}" var="dto">
												<tr>
													<td><input type="checkbox" name="selectedAttraction"
														value="${dto.book_user_seq}"></td>
													<td>${dto.name}</td>
													<td>${dto.regdate}</td>
													<td>${dto.book_time}</td>
													<td>${dto.capacity}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<button type="button" id="cancelBtn" onclick="deleteBook()">예약취소</button>
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}">
								</form>

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
								href="/dd/member/mypage/attraction/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		</nav>
	</section>

</main>

<script>
function deleteBook() {
	// 선택된 체크박스가 있다면
	if ($("input[name='selectedAttraction']:checked").length > 0) {

		var result = confirm("정말 예약을 취소하시겠습니까?");
		if (result) {

			// 삭제 form 설정 및 제출
			$('#cancelForm').attr('action',
					'/dd/member/mypage/attraction/delete.do');
			$('#cancelForm').submit();
		}
	} else{
		alert("취소할 어트랙션을 선택해주세요.");
	}
}
</script>