<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- Slick -->
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>

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
		width: 6%;
	}
	th:nth-child(3) {
		width: 34%;
	}
	th:nth-child(4) {
		width: 13%;
	}
	th:nth-child(5) {
		width: 14%;
	}
	th:nth-child(6) {
		width: 15%;
	}
	th:nth-child(7) {
		width: 12%;
	}
	td {
    	vertical-align: middle;
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
  	.modal-body {
		padding: 0;
	}
	#modal-content {
		padding: 16px;
   		margin: 0 !important;
	}
	.modal-dialog-scrollable .modal-body {
	    overflow-y: visible;
	}
   .image-slider {
		width: 800px;
		height: 350px;
		margin-bottom: 16px;
    }
   .image-slider div {
		width: 800px;
		height: 350px;
		overflow: hidden;
	}
   .image-slider img {
		width: 100%;
		max-height: 100%;
		object-fit: cover;
	}
	.slick-prev, .slick-next {
		border: 0;
		background: transparent;
		z-index: 100;
		position: absolute;
	}
	.slick-prev {
		top: 50%;
		left: 20px;
	}
	.slick-next {
		top: 50%;
		right: 20px;
	}
</style>

<main id="main" class="main">

    <div class="pagetitle">
		<h1>리뷰 관리</h1>
    </div>

	<section class="section">
		<div class="row">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-12">

              			<div id="search" class="header">
                  			<form method="GET" action="/dd/admin/communication/review/view.do" class="search-form d-flex align-items-center">
                    			<input type="text" name="word" id="search-field" placeholder="작성자 이름 또는 이메일을 입력하세요." autocomplete="off">
                    			<button type="submit"><i class="bi bi-search"></i></button>
                  			</form>
              			</div>

						<div class="card">
                			<div class="card-body">

                  				<nav class="d-flex justify-content-end">
                    				<ol class="breadcrumb">
                      					<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="edit();">수정</a></li>
                      					<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="del();">삭제</a></li>
                    				</ol>
								</nav>
								
								<!-- 테이블 -->
								
								<form method="POST" action="/dd/admin/communication/review/del.do" id="del-form">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								
									<table class="table">
										<thead>
											<tr>
												<th></th>
												<th>No</th>
												<th>제목</th>
												<th>방문일</th>
												<th>등록일</th>
												<th>작성자</th>
												<th>조회수</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${list}" var="dto" varStatus="numberStatus">
												<tr>
													<td><input type="checkbox" name="seqList" value="${dto.review_seq}"></td>
													<td>${map.totalPosts - numberStatus.index - map.startIndex + 1}</td>
										            <td><a onclick="showModal('${dto.review_seq}', `${dto.subject}`, `${dto.content}`)"><c:out value="${dto.subject}" /></a></td>
										            <td>${fn:substring(dto.visit_date, 0, 10)}</td>
										            <td>${fn:substring(dto.regdate, 0, 10)}</td>
										            <td>${dto.name}(${dto.email})</td>
										            <td>${dto.readcount}</td>
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
								                <h5 id="modal-subject" class="modal-title"></h5>
								                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								            </div>
								            <div class="modal-body">
								            	<div class="mt-3" id="modal-content"></div>
								                <div class="image-slider"></div>
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
										        <li class="page-item"><a class="page-link" href="/dd/admin/communication/review/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
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

<script type="text/javascript" src="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

<script>
	<!-- 검색 -->
	
	<c:if test="${map.searchStatus == 'y'}">
		$('#search-field').val('${map.word}');
	</c:if>
	
	$(document).keydown(function(event) {
	    if (event.key === 'F5') {
			location.href='/dd/admin/communication/voc/view.do';
	    }
	});

	<!-- 수정 -->
	
	function edit() {
		var checked = $('input[type="checkbox"]:checked');
		
	    if (checked.length === 1) {
	        location.href = '/dd/admin/communication/review/edit.do?seq=' + checked.val();
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
	
	const imgList = ${imgList};
	
	function showModal(seq, subject, content) {
        $('#modal-subject').text(subject);
        $('#modal-content').html(content);

        $('.image-slider').empty();

        for (var i = 0; i < imgList.length; i++) {
        	if (imgList[i].review_seq == seq) {
				var imgSrc = "/dd/resources/files/communication/review/" + imgList[i].img;

				$('.image-slider').append(`
						<div>
							<img src="\${imgSrc}" alt="Image" class="modal-image">
						</div>
				`);
        	}
        }
        
        if ($('.image-slider').children().length === 0) {
            $('.image-slider').hide();
        }
        
        $('.image-slider').slick({
			variableWidth : true,
			infinite : true,
			autoplay : true,
			autoplaySpeed : 5000,
			pauseOnHover : true,
			arrows : true,
			prevArrow : "<button type='button' class='slick-prev'>&#10094;</button>",
			nextArrow : "<button type='button' class='slick-next'>&#10095;</button>",
			draggable : true
		});

        $("#modal").modal("show");
    }

	$(window).resize(function() {
	   
		if ($('.modal-content').css('width') == '800px') {
			$('.image-slider').css('width', '800px');
			$('.image-slider div').css('width', '800px');
		} else {
			$('.image-slider').css('width', '500px');
			$('.image-slider div').css('width', '500px');
		}
	});

	$('#modal').on('hidden.bs.modal', function () {
		$('.image-slider').slick('unslick');
	});
</script>