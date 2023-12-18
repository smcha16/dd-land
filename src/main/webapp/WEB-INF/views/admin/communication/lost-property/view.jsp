<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
  	table {
    	text-align: center;
  	}
  	th {
    	background-color: #f2f2f2 !important;
  	}
  	th:nth-child(1) {
		width: 6%;
	}
	th:nth-child(2) {
		width: 8%;
	}
	th:nth-child(3) {
		width: 8%;
	}
	th:nth-child(4) {
		width: 24%;
	}
	th:nth-child(5) {
		width: 24%;
	}
	th:nth-child(6) {
		width: 15%;
	}
	th:nth-child(7) {
		width: 15%;
	}
  	td i {
		color: #0d6efd;
		margin-top: 7px;
	}
	td a {
		color: #000;
	}
	td a:hover {
		font-weight: bold !important;
      	color: #0d6efd !important;
    }
  	.pagination {
   		justify-content: center;
   		margin-top: 40px;
  	}
</style>

<main id="main" class="main">

    <div class="pagetitle">
		<h1>분실물 관리</h1>
    </div>

	<section class="section">
		<div class="row">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-12">

              			<div id="search" class="header">
                  			<form method="GET" action="/dd/admin/communication/lost-property/view.do" class="search-form d-flex align-items-center">
                    			<input type="text" name="word" id="search-field" placeholder="습득물 또는 습득장소를 입력하세요." autocomplete="off">
                    			<button type="submit"><i class="bi bi-search"></i></button>
                  			</form>
              			</div>

						<div class="card">
                			<div class="card-body">

                  				<nav class="d-flex justify-content-end">
                    				<ol class="breadcrumb">
                      					<li class="breadcrumb-item"><a href="/dd/admin/communication/lost-property/add.do">추가</a></li>
                      					<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="edit();">수정</a></li>
                      					<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="del();">삭제</a></li>
                    				</ol>
								</nav>
								
								<form method="POST" action="/dd/admin/communication/lost-property/del.do" id="del-form">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								
									<table class="table">
										<thead>
											<tr>
												<th></th>
												<th>No</th>
												<th>분류</th>
												<th>습득물</th>
												<th>습득장소</th>
												<th>습득일</th>
												<th>처리결과</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list}" var="dto" varStatus="numberStatus">
												<tr>
													<td><input type="checkbox" name="seqList" value="${dto.lost_property_seq}"></td>
													<td>${map.totalPosts - numberStatus.index - map.startIndex + 1}</td>
													<td>${dto.type}</td>
										            
										            <c:if test="${not empty dto.img}">
										            	<td><a onclick="showModal(`${dto.name}`,'${dto.img}')"><c:out value="${dto.name}" /></a></td>
										            </c:if>
										            
										            <c:if test="${empty dto.img}">
										            	<td><c:out value="${dto.name}" /></td>
										            </c:if>
										            
										            <td><c:out value="${dto.location}" /></td>
										            <td>${fn:substring(dto.lost_property_date, 0, 10)}</td>
		            								<td>${dto.result}</td>
										        </tr>
											</c:forEach>
										</tbody>
									</table>
								</form>
								
								<!-- 모달 -->
								
								<div id="modal" class="modal fade show" tabindex="-1" aria-labelledby="exampleModalScrollableTitle" aria-modal="true" role="dialog">
								    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
								        <div class="modal-content">
								            <div class="modal-header">
								                <h5 id="modal-name" class="modal-title"></h5>
								                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								            </div>
								            <div class="modal-body">
								                <div class="d-flex align-items-center justify-content-center">
								                    <img id="modal-image" src="" alt="Image" style="max-width: 100%;">
								                </div>
								            </div>
								        </div>
								    </div>
								</div>
								
								<!-- 페이징 -->
								
                 				<ul class="pagination justify-content-center">
									<c:forEach begin="1" end="${map.totalPages}" varStatus="pageStatus">
										<c:choose>
										    <c:when test="${pageStatus.index == currentPage}">
										        <li class="page-item active"><span class="page-link">${pageStatus.index}</span></li>
										    </c:when>
										    <c:otherwise>
										        <li class="page-item"><a class="page-link" href="/dd/admin/communication/lost-property/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
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
	<!-- 검색 -->
	
	<c:if test="${map.searchStatus == 'y'}">
		$('#search-field').val('${map.word}');
	</c:if>
	
	$(document).keydown(function(event) {
		if (event.key === 'F5') {
			location.href='/dd/admin/communication/lost-property/view.do';
		}
	});

	<!-- 수정 -->
	
	function edit() {
		var checked = $('input[type="checkbox"]:checked');
		
	    if (checked.length === 1) {
	        location.href = '/dd/admin/communication/lost-property/edit.do?seq=' + checked.val();
	    } else {
	        alert('하나의 항목을 선택하세요.');
	    }
	}
	
	<!-- 삭제 -->
	
	function del() {
		var checked = $('input[type="checkbox"]:checked');
		
        if (checked.length > 0) {
            if (confirm('선택한 항목을 삭제하시겠습니까?')) {
				$('#del-form').submit();
            }
        } else {
            alert('삭제할 항목을 선택하세요.');
        }
    }
	
	<!-- 모달 -->
	
	function showModal(name, image) {
	    $('#modal-name').text(name);
        $('#modal-image').attr('src', '/dd/resources/files/communication/lost/' + image);

        $('#modal').modal('show');
	}
</script>