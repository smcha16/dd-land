<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Slick -->
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>

<style>
#pagetitle {
	margin-top: 70px;
}

#title {
	font-size: 48px;
	display: block;
	color: #fff;
	font-weight: 700;
	margin-bottom: 20px;
}

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
    	width: 70%;
    	margin: auto;
  	}
  	.card {
  		border: none;
  	}
  	.card-body {
    	min-height: 200px;
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
    	background-color: mistyrose !important;
  	}
  	.pagination {
		justify-content: center;
		margin-top: 40px;
	}
  	
  	/* 목록 커서 CSS */
  	table td:nth-child(3) a {
		cursor: pointer;
	}
  	table td:nth-child(5) i {
		cursor: pointer;
	}
  	table td:nth-child(6) i {
		cursor: pointer;
	}
	
	/* slick slider */
	/* .image-slider {
		width: 700px;
		height: 350px;
    } */
    
	.image-slider div {
		/* width: 700px;
		height: 350px; */
		overflow: hidden;
	}
	
	.image-slider img {
		width: 100%;
		max-height: 100%;
		object-fit: cover;
	}
	
	/* Slick Button Style */
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
	
	.table {
		text-align: center;
		margin: 0 auto;
	}
	.table thead tr {
		background-color: #EEE;
	}
	.table th, .table td {
		height: 60px;
		text-align: center;
		color: #444;
		padding: 20px;
		border-bottom: 1px solid #E1E1E1;
	}
	.table th {
		font-size: 1.12rem;
		font-weight: bold;
	}
	.table th:nth-child(1) {
		width: 10%;
	}
	.table th:nth-child(2) {
		width: 45%;
	}
	.table th:nth-child(3) {
		width: 30%;
	}
	.table th:nth-child(4) {
		width: 15%;
	}
	.table td {
		font-size: 1.05rem;
	}
	.table td a {
		color: #444;
	}
	.table td a:hover {
		font-weight: bold;
		color: #CE1212;
	}
	.table td i {
		color: #CE1212;
		margin-top: 7px;
	}
	
	/* 모달 CSS */
	#modal table.m-desc {
		width: 100%;
		font-size: 14px;
	}
	
	#modal table tr > th {
		/* width: 100px; */
		text-align: left;
		font-weight: bold;
		background: #FFF !important;
		padding: 10px;
	}
	
	#modal table tr > td {
		padding: 10px;
	}
	
	.m-info {
		padding: 10px;
	}
	
	/* list photo 변경 */
.stats-counter {
	background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
		url("/dd/resources/files/purchase/main-cart.jpg") center
		center;
	background-size: cover;
	padding: 100px 0;
	background-attachment: fixed;
}
.buy {
	padding: 0 0 60px 0;
}
</style>

<section id="stats-counter" class="stats-counter">
	<div id="pagetitle" class="container" data-aos="zoom-out">
		<div class="gy-4" style="justify-content: center; width: 100%;">
			<div class="col-lg-3 col-md-6" style="width: 100%;">
				<div class="stats-item text-center w-100 h-100">
					<div id="title">장바구니</div>
				</div>
			</div>
		</div>
	</div>
</section>

	<section class="section buy">
		<div class="row">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-12">

						<div class="card">
                			<div class="card-body">

                  				<nav class="d-flex justify-content-end">
                    				<ol class="breadcrumb">
                      					<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="buy('/dd/member/purchase/view.do')">구매</a></li>
                      					<li class="breadcrumb-item active"><a href="javascript:void(0);" onclick="del('/dd/member/cart/del.do')">삭제</a></li>
                    				</ol>
								</nav>
                  
                  				<form id="del-form" method="POST">
	                  				<table class="table">
	                    				<thead>
	                      					<tr>
	                        					<th></th>
	                        					<th>상품명</th>
	                        					<th>개별 가격</th>
	                        					<th>개수</th>
	                        					<!-- <th>이미지</th> -->
	                      					</tr>
	                    				</thead>
	                    				<tbody>
	                    					<c:forEach items="${list}" var="dto" varStatus="status">
		                      					<tr>
		                        					<td><input type="checkbox" name="cart_seq" value="${dto.cart_seq}"></td>
		                        					<td><a onclick="showModal('${dto.item_seq}')"><c:out value="${dto.name}" /></a></td>
		                        					<td>${dto.price } 원</td>
		                        					<td>${dto.ea } 개</td>
		                      					</tr>
	                      					</c:forEach>
	                   					</tbody>
                  					</table>
	                  					
                  					<!-- 토큰 -->
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                				</form>
               				</div>
             			</div>
            		</div>
				</div>
			</div>

		</div>
	</section>

<!-- Slick Slider -->
<script type="text/javascript" src="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script>

	/* 수정 시, 체크 박스 1개만 선택 하여 seq 전달 하기 */
	function buy(action) {
		
		/* 선택된 체크박수 개수 확인 */
		let checkedCount = $('input[type="checkbox"]:checked').length;
		
		/* 1개 이상? out! */
		if (checkedCount == 0) {
			alert('1개 이상의 상품을 선택 후, 구매 버튼을 눌러주세요.');
		} else {

			$('#del-form').attr('action', action);
			
			$('#del-form').submit();
						
		}
		
	}//function
	
	/* 삭제 시, 체크 박스 1개 이상 선택 하여 seq 전달하기 */
	/* 1. 체크박스 1개 2. 체크박스 1개 이상 */
	function del(action) {
		
		/* 선택된 체크박수 개수 확인 */
		let checkedCount = $('input[type="checkbox"]:checked').length;
		
		if (checkedCount == 0) {
			alert('1개 이상의 상품을 선택 후, 삭제 버튼을 눌러주세요.');
		} else {
			
			if (confirm('선택한 상품을 삭제하시겠습니까?')) {
				
				$('#del-form').attr('action', action);
				
				$('#del-form').submit();

			}
			
		} 
		
	}//function
	
	/* 어트랙션 상세 모달 */
	function showModal(seq) {
	    
		$('.image-slider').html('');
		addModalImg(seq);
		
		$('#modal-name').text(name);
        $('.m-info').text(info);
        $('.m-time').text(time);
        $('.m-restriction').text(restriction);
        
        $('#modal').modal('show');
	}
	
	function addModalImg(seq) {
		
		const filterImg = img_list.filter(obj => obj.item_seq == seq);
		
		if (filterImg.length > 0) {
			
			filterImg.forEach(obj => {
				
				let imgSrc = '/dd/resources/files/item/' + obj.img;
				
				$('.image-slider').append(`
				
					<div>
						<img class="modal-image" alt="Image" src="\${imgSrc}">
					</div>
						
				`);
			});
			
		}

		
		/* Slick Slider */
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
		
	}
	
	/* 모달이 닫힐 때 uslick 설정 */
	$('#modal').on('hidden.bs.modal', function () {
		$('.image-slider').slick('unslick');
	});
	

	</script>