<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>
  
<style>
    #title + div {
    	display: inline-block;
	    text-shadow: 0 2px 10px rgba(255, 255, 255, 0.8);
	    font-size: 17px;
	    color: #222222;
	    background-color: rgba(255, 255, 255, 0.6);
	    padding: 5px 20px;
	    border-radius: 50px;
    }
    .stats-counter {
    	background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("/dd/resources/files/communication/notice.jpg") center center;
    	background-size: cover;
    	background-attachment: fixed;
    }
    #pagetitle {
		margin-top: 70px;
    }
    #title {
		margin-bottom: 20px;
    }
	.select {
		background: transparent;
		border: 0;
		position: absolute;
		top: 7px;
    	left: 15px;
    }
    select:focus, input:focus {
    	outline: none;
    }
    #notice {
    	min-height: 700px;
		margin: 50px 0;
	}
	#notice-list {
		width: 75%;
		text-align: center;
		margin: 0 auto;
	}
	#notice-list thead tr {
		background-color: #EEE;
	}
	#notice-list th, #notice-list td {
		height: 60px;
		color: #444;
		padding: 10px;
		border-bottom: 1px solid #E1E1E1;
	}
	#notice-list th {
		font-size: 1.12rem;
		font-weight: bold;
	}
	#notice-list th:nth-child(1) {
		width: 17%;
	}
	#notice-list th:nth-child(2) {
		width: 60%;
	}
	#notice-list th:nth-child(3) {
		width: 23%;
	}
	#notice-list td {
		font-size: 1.05rem;
	}
	#notice-list td a {
		color: #444;
	}
	#notice-list td a:hover {
		font-weight: bold;
		color: #CE1212;
	}
	#notice-list td i {
		color: #CE1212;
		margin-top: 7px;
	}
	#page-bar {
		margin-top: 50px;
	}
	.page-link {
		color: #CE1212;
	}
	.active > .page-link, .page-link.active {
		z-index: 3;
	    color: var(--bs-pagination-active-color);
	    background-color: #CE1212;
	    border-color: #CE1212;
	}
</style>

<!-- ======= Title Section ======= -->

<section id="stats-counter" class="stats-counter">
	<div id="pagetitle" class="container" data-aos="zoom-out">
		<div class="gy-4" style="justify-content: center; width: 100%;">
			<div class="col-lg-3 col-md-6" style="width: 100%;">
				<div class="stats-item text-center w-100 h-100">
					<div id="title" style="padding: 0 !important; font-size: 48px; font-weight: 700; color: #fff;">공지사항</div>
						<div style="width: 400px; height: 40px; position: relative;">
						<form method="GET" action="/dd/user/communication/notice/view.do" id="search-form">
							<select name="category" id="category" class="select">
								<option value="subject">제목</option>
								<option value="content">내용</option>
							</select>
							<input type="text" name="word" id="search-field" autocomplete="off" style="width: 250px; background-color: transparent; border: 0; position: absolute; left: 93px;">
		                	<button type="submit" id="search-button" style="background: none; border: none; cursor: pointer; position: absolute; right: 10px; top: 6px;">
						        <i class="fa-solid fa-magnifying-glass" ></i>
						    </button>
		                </form>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- ======= Main Section ======= -->
    
<main id="notice">
	<table id="notice-list">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="dto" varStatus="numberStatus">
				<tr>
					<c:if test="${dto.fix == 'y'}">
						<td><i class="bi bi-bookmark-star-fill"></i></td>
					</c:if>
					
					<c:if test="${dto.fix == 'n'}">
						<td>${totalPosts - numberStatus.index - map.startIndex + 1}</td>
					</c:if>
					
		            <td><a href="/dd/user/communication/notice/detail.do?seq=${dto.notice_seq}"><c:out value="${dto.subject}" /></a></td>
		            <td>${dto.regdate}</td>
		        </tr>
			</c:forEach>
		</tbody>
	</table>
	
	<nav id="page-bar" aria-label="Page navigation example">
	    <ul class="pagination justify-content-center">
	        <c:forEach begin="1" end="${totalPages}" varStatus="pageStatus">
	            <c:choose>
	                <c:when test="${pageStatus.index == currentPage}">
	                    <li class="page-item active"><span class="page-link">${pageStatus.index}</span></li>
	                </c:when>
	                <c:otherwise>
	                    <li class="page-item"><a class="page-link" href="/dd/user/communication/notice/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
	                </c:otherwise>
	            </c:choose>
	        </c:forEach>
	    </ul>
	</nav>
</main>