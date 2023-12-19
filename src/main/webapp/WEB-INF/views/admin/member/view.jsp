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
	color: #ce1212;
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
</style>

<!-- ======= Main ======= -->
<main id="main" class="main">

	<div class="pagetitle">
		<h1>회원 관리</h1>
	</div>

	<section class="section">
		<div class="row">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-12">

						  <div id="search" class="header">
                  			<form class="search-form d-flex align-items-center" method="POST" action="/dd/admin/member/view.do">
                    			 <input type="text" name="query" placeholder="Search" title="Enter search keyword">
                    			 <input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}">
                    			<button type="submit" title="Search"><i class="bi bi-search"></i></button> 
                  			</form>
              			</div>  

						<div class="card">
							<div class="card-body">

								<nav class="d-flex justify-content-end">
									<ol class="breadcrumb">
										<li class="breadcrumb-item">
										<a href="javascript:void(0);" onclick="edit();" >수정</a></li>
										<li class="breadcrumb-item active">
										<a href="javascript:void(0);" onclick="del();">삭제</a></li>
									</ol>
									
								</nav>
								
				<form id="del-form" method="POST" action="/dd/admin/member/del.do">

								<table class="table">
									<thead>
										<tr>
											 <th></th> 
											<th>No</th>
											<th>이름</th>
											<th>이메일</th>
											<th>전화번호</th>
											<th>생년월일</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="dto">
											<tr>
												<td><input type="checkbox" name="user_seq"onclick="handleCheckboxClick(this)" value="${dto.user_seq}"></td> 
												<td>${dto.user_seq }</td>
												<td>${dto.name}</td>
												<td>${dto.email}</td>
												<td>${dto.tel }</td>
												<td>${dto.birth}</td>
											</tr>
										</c:forEach>
								</table>

<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}">
		
								</form>
								
			<ul class="pagination justify-content-center">
    <!-- 처음 페이지로 이동하는 버튼 -->
    <c:if test="${currentPage > 10}">
        <li class="page-item"><a class="page-link" href="/dd/admin/member/view.do?page=1">&lt;&lt;</a></li>
    </c:if>

    <!-- 이전 페이지로 이동하는 버튼 -->
    <c:if test="${currentPage > 10}">
        <li class="page-item"><a class="page-link" href="/dd/admin/member/view.do?page=${startPage - 1}">&lt;</a></li>
    </c:if>

    <!-- 페이지 번호를 10개씩 표시 -->
    <c:forEach begin="${startPage}" end="${endPage}" varStatus="pageStatus">
        <c:choose>
            <c:when test="${pageStatus.index <= map.totalPages}">
                <c:choose>
                    <c:when test="${pageStatus.index == currentPage}">
                        <li class="page-item active"><span class="page-link">${pageStatus.index}</span></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="/dd/admin/member/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
    </c:forEach>

    <!-- 다음 페이지로 이동하는 버튼 -->
    <c:if test="${endPage < map.totalPages}">
        <li class="page-item"><a class="page-link" href="/dd/admin/member/view.do?page=${endPage + 1}">&gt;</a></li>
    </c:if>

    <!-- 마지막 페이지로 이동하는 버튼 -->
    <c:if test="${endPage < map.totalPages}">
        <li class="page-item"><a class="page-link" href="/dd/admin/member/view.do?page=${map.totalPages}">&gt;&gt;</a></li>
    </c:if>
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
	function submit(){
		$('form').submit();
	}
	
	function handleCheckboxClick(clickedCheckbox) {
	      const checkboxes = document.querySelectorAll('input[name="attraction_checkbox"]');
	      
	      checkboxes.forEach(checkbox => {
	        if (checkbox !== clickedCheckbox) {
	          checkbox.checked = false;
	        }
	      });
	    }
	
	
	/* 삭제 시, 체크 박스 1개 이상 선택 하여 seq 전달하기 */
	/* 1. 체크박스 1개 2. 체크박스 1개 이상 */
	function del() {
		
		/* 선택된 체크박수 개수 확인 */
		let checkedCount = $('input[type="checkbox"]:checked').length;
		
		if (checkedCount == 0) {
			alert('1명 이상의 회원을 선택 후, 삭제 버튼을 눌러주세요.');
		} else {
			
			if (confirm('선택한 회원을 삭제하시겠습니까?')) {
				
				$('#del-form').submit();

			}
			
		} 
		
	}//function
	
	/* 수정 시, 체크 박스 1개만 선택 하여 seq 전달 하기 */
	function edit() {
		
		/* 선택된 체크박수 개수 확인 */
		let checkedCount = $('input[type="checkbox"]:checked').length;
		
		/* 1개 이상? out! */
		if (checkedCount > 1 || checkedCount < 1) {
			alert('1명의 회원을 선택 후, 수정 버튼을 눌러주세요.');
		} else {

			const seq = $('input[type="checkbox"]:checked').val();
			
			location.href='/dd/admin/member/edit.do?seq=' + seq;
						
		}
		
	}//function
</script>