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

  form {
            max-width: 400px;
            margin: 20px auto;
        }

        label,
        input {
            display: block;
            margin-bottom: 10px;
        }

        input[type="text"],
        input[type="password"] {
            width: calc(100% - 20px);
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 4px;
            background-color: #e74c3c;
            color: #fff;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #c0392b;
        }
        
        
        input,
        textarea {
            display: block;
            margin-bottom: 20px;
            width: calc(100% - 20px); /* 입력 필드 너비 조정 */
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ccc;
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

								<form action="/dd/member/mypage/unregister/unregister.do" method="POST">
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
						            
						            <input type="submit" value="회원 탈퇴">
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