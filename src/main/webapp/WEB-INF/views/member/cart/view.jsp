<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	
</style>

<!-- ======= Main ======= -->
<main id="main" class="main">

    <div class="pagetitle">
		<h1>장바구니</h1>
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
                      					<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="buy('/dd/member/purchase/view.do')">구매</a></li>
                      					<li class="breadcrumb-item active"><a href="javascript:void(0);" onclick="del('/dd/member/cart/del.do')">삭제</a></li>
                    				</ol>
								</nav>
                  
                  				<form id="del-form" method="POST">
	                  				<table class="table">
	                    				<thead>
	                      					<tr>
	                        					<th></th>
	                        					<th>No</th>
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
		                        					<td>${map.totalPosts - status.index - map.startIndex + 1}</td>
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
                  					
                				<!-- 어트랙션 상세 모달 -->
								<div id="modal" class="modal fade show" tabindex="-1" aria-labelledby="exampleModalScrollableTitle" aria-modal="true" role="dialog">
								    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
								        <div class="modal-content">
								            <div class="modal-header">
								                <h5 id="modal-name" class="modal-title"></h5>
								                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								            </div>
							                
							                
								            <div class="modal-body">
									            <!-- 모달 이미지 슬라이드-->
								                <div class="image-slider"></div>
								                <!-- 설명 -->
								            	<div class="m-info"></div>
								            	<!-- 상세 -->
								            	<table class="m-desc">
								            		<colgroup>
								            			<col style="width: 100px">
								            			<col>
								            		</colgroup>
								            		<tbody>
								            		</tbody>
								            	</table>
								            </div>
								        </div>
								    </div>
								</div>
               				</div>
             			</div>
            		</div>
				</div>
			</div>

		</div>
	</section>

</main>

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

			$('#del-form').action = action;
			
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
				
				$('#del-form').action = action;
				
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