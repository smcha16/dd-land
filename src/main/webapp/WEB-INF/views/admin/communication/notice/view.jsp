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
      	color: #0d6efd;
    }
  	.table {
    	text-align: center;
  	}
  	th {
    	background-color: #f2f2f2 !important;
  	}
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
</style>

<!-- ======= Main ======= -->
<main id="main" class="main">

    <div class="pagetitle">
		<h1>공지사항 관리</h1>
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
                      					<li class="breadcrumb-item"><a href="/dd/admin/communication/notice/add.do">추가</a></li>
                      					<li class="breadcrumb-item"><a href="#">수정</a></li>
                      					<li class="breadcrumb-item active"><a href="#">삭제</a></li>
                    				</ol>
								</nav>
								
								<table class="table">
									<thead>
										<tr>
											<th></th>
											<th>No</th>
											<th>제목</th>
											<th>등록일</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list}" var="dto" varStatus="numberStatus">
											<tr>
												<td><input type="checkbox"></td>
												<c:if test="${dto.fix == 'y'}">
													<td><i class="bi bi-bookmark-star-fill"></i></td>
												</c:if>
												
												<c:if test="${dto.fix == 'n'}">
													<td>${map.totalPosts - numberStatus.index - map.startIndex + 1}</td>
												</c:if>
												
									            <td><a href="/dd/user/communication/notice/detail.do?seq=${dto.notice_seq}"><c:out value="${dto.subject}" /></a></td>
									            <td>${dto.regdate}</td>
									        </tr>
										</c:forEach>
									</tbody>
								</table>
                  
                 				<ul class="pagination justify-content-center">
									<c:forEach begin="1" end="${map.totalPages}" varStatus="pageStatus">
										<c:choose>
										    <c:when test="${pageStatus.index == currentPage}">
										        <li class="page-item active"><span class="page-link">${pageStatus.index}</span></li>
										    </c:when>
										    <c:otherwise>
										        <li class="page-item"><a class="page-link" href="/dd/admin/communication/notice/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
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