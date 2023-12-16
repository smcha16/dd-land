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

.card-body {
	min-height: 400px; /* 카드 높이 설정 */
}

.form-group {
	margin-bottom: 20px; /* 폼 그룹 간격 조정 */
}

label {
	display: block; /* 라벨 디스플레이 타입 변경 */
	margin-bottom: 5px; /* 라벨 마진 조정 */
}

input[type="text"], textarea {
	width: calc(100% - 12px); /* 입력 필드 너비 조정 */
	padding: 8px;
	border: 1px solid #ccc; /* 테두리 스타일 설정 */
	border-radius: 4px; /* 테두리 둥글게 */
}

button {
	padding: 10px 20px; /* 버튼 패딩 설정 */
	background-color: #007bff; /* 배경색 설정 */
	color: #fff; /* 텍스트 색상 설정 */
	border: none; /* 테두리 없애기 */
	border-radius: 4px; /* 테두리 둥글게 */
	cursor: pointer;
}

button:hover {
	background-color: #0056b3; /* 호버 시 배경색 변경 */
}

#content{
height: 200px;
}
</style>

<!-- ======= Main ======= -->
<main id="main" class="main">

	<div class="pagetitle">
		<h1>리뷰 수정</h1>
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

								<form action="/dd/member/mypage/review/editok.do" method="post">
									<div class="form-group">
										<label for="title">제목:</label> 
										<input type="text" id="subject" name="subject" value="${dto.subject}" required>
									</div>
									<div class="form-group">
										<label for="content">내용:</label>
										<textarea id="content" name="content" required>${dto.content}</textarea>
									</div>
									<button type="submit">작성</button>
									
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}">
									<input type="hidden" name="review_seq"
										value="${dto.review_seq}">
								</form>


							</div>

						</div>
					</div>

				</div>
			</div>

		</div>
	</section>

</main>