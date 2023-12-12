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
		<h1>요금 관리</h1>
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
                      					<li class="breadcrumb-item"><a href="/dd/admin/pb/price/edit.do" onclick="submit();" >수정</a></li>
                    				</ol>
								</nav>
                  
                  				<table class="table">
                    				<thead>
                      					<tr>
                        					<th></th>
                <th>No</th>
                <th>종류</th>
                <th>개인/단체</th>
                <th>구분</th>
                <th>가격</th>
                      					</tr>
                    				</thead>
                    				<tbody>
                      					 <c:forEach items="${list}" var="dto">
                <tr>
                   <%-- <td><input type="checkbox" name="attraction_checkbox" value="${dto.ticket_seq}"></td>  --%>
                  <td></td>
                  <td>${dto.ticket_seq}</td>
                  <td>${dto.ticket_type}</td>
                  <td>${dto.person_type }</td>
                  <td>${dto.age }</td>
                  <td>${dto.price }</td>
                </tr>
              </c:forEach>
                  					</table>

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
<script>
	function submit(){
		$('form').submit();
	}
</script>