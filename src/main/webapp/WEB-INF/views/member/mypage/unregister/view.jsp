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

  form label {
    display: block;
    margin-bottom: 10px;
    color: #333;
    font-weight: bold;
}

form input[type="text"],
form input[type="password"],
form textarea,
form select {
    width: calc(100% - 20px);
    padding: 10px;
    border-radius: 4px;
    border: 1px solid #ccc;
    margin-bottom: 20px;
    font-size: 16px;
    box-sizing: border-box;
}

/* 선택 필드 디자인 */
form select {
    appearance: none;
    background-image: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M7.41 8.59L12 13.17l4.59-4.58L18 10l-6 6-6-6 1.41-1.41z"/></svg>');
    background-repeat: no-repeat;
    background-position: right 10px top 50%;
    padding-right: 30px;
}

/* 입력 버튼 스타일 */
form input[type="submit"] {
    width: 100%;
    padding: 12px;
    border: none;
    border-radius: 4px;
    background-color: tomato;
    color: #fff;
    font-size: 16px;
    font-weight: bold;
    text-transform: uppercase;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-top: 80px;
}

form input[type="submit"]:hover {
    background-color: #005aa3;
}
</style>

<!-- ======= Main ======= -->
<main id="main" class="main">

	<div class="pagetitle">
		<h1>회원 탈퇴</h1>
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

								<form id="deleteForm" action="/dd/member/mypage/unregister/unregister.do" method="POST">
						            <label for="email">이메일:</label>
						            <input type="text" id="email" name="email" required>
						            
						            <label for="reason">탈퇴 사유:</label>
						            <select id="reason" name="reason" required>
						                <option value="" disabled selected>탈퇴 사유를 선택하세요</option>
						                <option value="personal_reason">개인적인 이유</option>
						                <option value="not_satisfied">서비스에 대한 불만족</option>
						                <option value="found_alternative">대체 서비스 발견</option>
						                <option value="privacy_concerns">개인정보 보호 우려</option>
						                <option value="other">기타</option>
						            </select>
						            
						            <label for="otherReason" style="margin-top: 30px;">기타 사유:</label>
						            <textarea id="otherReason" name="otherReason" rows="4"></textarea>
						            
						            <input type="submit" value="회원 탈퇴" onclick="confirmDelete()">
						            <input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}">
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
    function confirmDelete() {
        var result = confirm("정말 회원 탈퇴를 진행하시겠습니까?");
        if (result) {
            // '확인'을 클릭한 경우
            document.getElementById("deleteForm").submit(); // 실제 탈퇴 작업 수행
        } else {
            // '취소'를 클릭한 경우
            // 아무 작업도 수행하지 않거나 필요한 작업을 추가하세요.
        }
    }
    
    <c:if test="${invalidEmail}">
    alert("이메일이 일치하지 않습니다.");
	</c:if>
</script>