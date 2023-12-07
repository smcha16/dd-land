<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
  	
</style>

<!-- ======= Main ======= -->
<main id="main" class="main">

    <div class="pagetitle">
		<h1>어트랙션 예약 내역</h1>
    </div>

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

                  				<nav class="d-flex justify-content-end">
                    				<ol class="breadcrumb">
                      					<li class="breadcrumb-item"><a href="index.html">추가</a></li>
                      					<li class="breadcrumb-item"><a href="#">수정</a></li>
                      					<li class="breadcrumb-item active"><a href="#">삭제</a></li>
                    				</ol>
								</nav>
                  
                  				<c:forEach items="${list}" var ="dto">
                  				<table class="table">
                    				<thead>
                      					<tr>
                        					<th></th>
                        					<th>No</th>
                        					<th>어트랙션</th>
                        					<th>예약날짜</th>
                        					<th>예약시간</th>
                        					<th>예약인원</th>
                      					</tr>
                    				</thead>
                    				<tbody>
                      					<tr>
                        					<td><input type="checkbox" name="attraction_checkbox"></td>
                        					<td>${dto.book_user_seq}</td>
                        					<td>${dto.name}</td>
                        					<td>${dto.regdate}</td>
                        					<td>${dto.book_time}</td>
                        					<td>${dto.capacity}</td>
                      					</tr>
                    					</tbody>
                  					</table>
                  					</c:forEach>


                  					<ul class="pagination pagination-sm">
					                    <li class="page-item active" aria-current="page">
					                    	<span class="page-link">1</span>
					                    </li>
										<li class="page-item"><a class="page-link" href="#">2</a></li>
										<li class="page-item"><a class="page-link" href="#">3</a></li>
										<li class="page-item"><a class="page-link" href="#">4</a></li>
										<li class="page-item"><a class="page-link" href="#">5</a></li>
                  					</ul>
                				</div>

              				</div>
            			</div>

 					</div>
				</div>

			</div>
	</section>

</main>