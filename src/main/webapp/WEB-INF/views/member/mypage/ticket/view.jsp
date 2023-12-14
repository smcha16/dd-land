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

/* 라디오 버튼을 체크박스로 변경 */
.table input[type="radio"] {
	-webkit-appearance: none; /* 기본 스타일링 제거 */
	-moz-appearance: none;
	appearance: none;
	width: 12px; /* 기본 체크박스 크기와 비슷하게 설정 */
	height: 12px;
	border: 1px solid #747576; /* 테두리 스타일 지정 */
	border-radius: 3px; /* 라운드 처리 */
	outline: none;
	vertical-align: middle; /* 수직 정렬 */
	position: relative; /* 위치 설정 */
}

/* 체크된 상태의 라디오 버튼 스타일 */
.table input[type="radio"]:checked {
	background-color: #0075ff; /* 선택 시 배경색 변경 */
}

/* 체크 표시 모양 */
.table input[type="radio"]:checked::after {
	content: '\2713'; /* 체크 모양 아이콘 */
	font-size: 12px; /* 아이콘 크기 */
	color: #fff; /* 체크 색상 */
	position: absolute; /* 위치 설정 */
	top: 50%; /* 수직 정렬 */
	left: 50%; /* 수평 정렬 */
	transform: translate(-50%, -50%); /* 가운데 정렬 */
}
</style>

<!-- ======= Main ======= -->
<main id="main" class="main">



	<section class="section">
		<div class="row">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-12">

						<!-- <div id="search" class="header">
                  			<form class="search-form d-flex align-items-center" method="POST" action="#">
                    			<input type="text" name="query" placeholder="Search" title="Enter search keyword">
                    			<button type="submit" title="Search"><i class="bi bi-search"></i></button>
                  			</form>
              			</div> -->

						<div class="card">
							<div class="card-body">
								<div class="pagetitle">
									<h1>티켓 예매내역</h1>
								</div>

								<nav class="d-flex justify-content-end">
									<ol class="breadcrumb">
									</ol>
								</nav>


								<form id="cancelForm"
									action="/dd/member/mypage/ticket/delete.do" method="post">
									<table class="table">
										<thead>
											<tr>
												<th></th>
												<th>예매일</th>
												<th>티켓종류</th>
												<th>개인/단체</th>
												<th>연령</th>
												<th>방문예정일</th>
												<th>구매수량</th>
												<th>결제금액</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list}" var="dto">
												<tr>
													<td><input type="checkbox" name="selectedTickets"
														value="${dto.user_book_seq}"></td>

													<td>${dto.book_date}</td>
													<td>${dto.ticket_type}</td>
													<td>${dto.person_type}</td>
													<td>${dto.age}</td>
													<td>${dto.visit_date}</td>
													<td>${dto.ea}</td>
													<td>${dto.total_price}</td>

												</tr>
											</c:forEach>
										</tbody>
									</table>
									<button type="submit" id="delete-button">예매취소</button>
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}">
								</form>

								<div class="pagetitle">
									<h1>이전 티켓 예매내역</h1>
								</div>
								<form id="reviewForm" action="/dd/member/mypage/review/add.do"
									method="post">
									<table class="table">
										<thead>
											<tr>
												<th></th>
												<th>예매일</th>
												<th>티켓종류</th>
												<th>개인/단체</th>
												<th>연령</th>
												<th>방문예정일</th>
												<th>구매수량</th>
												<th>결제금액</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${plist}" var="dto">
												<tr>
													<td><input type="radio" name="selectedReview"
														value="${dto.user_book_seq}"
														data-type="${dto.reviewCount}"></td>

													<td>${dto.book_date}</td>
													<td>${dto.ticket_type}</td>
													<td>${dto.person_type}</td>
													<td>${dto.age}</td>
													<td>${dto.visit_date}</td>
													<td>${dto.ea}</td>
													<td>${dto.total_price}</td>

												</tr>
											</c:forEach>
										</tbody>
									</table>
									<button type="button" onclick="submitReview()">리뷰작성</button>
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}">
								</form>

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
									href="/dd/member/mypage/ticket/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</nav>
	</section>

</main>

<script>
	$('#delete-button').click(function(e) {
		var result = confirm("정말 예매를 취소하시겠습니까?");
		if (!result) {
			e.preventDefault(); // 확인을 누르지 않으면 기본 동작(폼 제출)을 막음
		}
	});

	function submitReview() {
		// 선택된 체크박스의 개수 확인
		var checkedBoxes = document
				.querySelectorAll('input[name="selectedReview"]:checked').length;

		// 선택된 체크박스가 없는 경우 알림창 띄우기
		if ($('input[name="selectedReview"]:checked').data('type') == 1) {
			alert("이미 작성한 리뷰가 존재합니다.");
		} else {
			if (checkedBoxes === 0) {
				alert("리뷰를 작성할 티켓 예매내역을 선택해주세요.");
			} else {
				// 폼 제출
				document.forms["reviewForm"].submit();
			}
		}

	}
</script>