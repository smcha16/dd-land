<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
		<h1>기프트샵 운휴 관리</h1>
    </div>

	<section class="section">
		<div class="row">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-12">

              			<div id="search" class="header">
                  			<form class="search-form d-flex align-items-center" method="POST" action="#">
                    			<input type="text" name="query" placeholder="Search" title="Enter search keyword">
                    			<button type="submit" title="Search"><i class="bi bi-search"></i></button>
                  			</form>
              			</div>

						<div class="card">
                			<div class="card-body">

                  				<nav class="d-flex justify-content-end">
                    				<ol class="breadcrumb">
                      					<li class="breadcrumb-item"><a href="/dd/admin/close/gift-shop/add.do">추가</a></li>
                      					<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="edit()">수정</a></li>
                      					<li class="breadcrumb-item active"><a href="javascript:void(0);" onclick="del()">삭제</a></li>
                    				</ol>
								</nav>
                  
                  				<form id="del-form" method="POST" action="/dd/admin/close/gift-shop/del.do">
                  				<table class="table">
                    				<thead>
                      					<tr>
                        					<th></th>
                        					<th>No</th>
                        					<th>이름</th>
                        					<th>운휴시작일</th>
                        					<th>운휴종료일</th>
                      					</tr>
                    				</thead>
                    				<tbody>
                    					<c:forEach items="${list}" var="dto" varStatus="numberStatus">
                      					<tr>
                        					<td><input type="checkbox" name="closeShop_seq" value="${dto.shop_close_seq}"></td>
                        					<td>${map.totalPosts - numberStatus.index - map.startIndex + 1}</td>
                        					<td>${dto.name}</td>
                        					<td>${dto.start_date}</td>
                        					<td>${dto.end_date}</td>
                      					</tr>
                      					</c:forEach>
                    					</tbody>
                  					</table>
                  					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                  					</form>

									<!-- 페이징 -->
                  					<ul class="pagination justify-content-center">
									<c:forEach begin="1" end="${map.totalPages}" varStatus="pageStatus">
										<c:choose>
										    <c:when test="${pageStatus.index == currentPage}">
										        <li class="page-item active"><span class="page-link">${pageStatus.index}</span></li>
										    </c:when>
										    <c:otherwise>
										        <li class="page-item"><a class="page-link" href="/dd/admin/close/gift-shop/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
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
	function edit() {  //수정
		let checkedCount = $('input[type="checkbox"]:checked').length;  //체크된 체크박스 개수
		
		if (checkedCount !== 1) {
			alert('1개의 기프트샵을 선택 후, 수정 버튼을 눌러주세요.');
		} else {
			const seq = $('input[type="checkbox"]:checked').val();
			location.href='/dd/admin/close/gift-shop/edit.do?seq=' + seq;  //1개 골랐으면 수정 페이지로 이동
		}
	}
	
	function del() {  //삭제
		let checkedCount = $('input[type="checkbox"]:checked').length;
		
		if (checkedCount == 0) {
			alert('1개 이상의 기프트샵을 선택 후, 삭제 버튼을 눌러주세요.');
		} else {
			if (confirm('선택한 기프트샵을 삭제하시겠습니까?')) {
				$('#del-form').submit();
			}
		} 
	}

</script>


