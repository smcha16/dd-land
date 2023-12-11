<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	#main h1 {
		font-size: 2rem !important;
   		margin-top: 45px !important;
   		margin-left: 10px;
  	}
  	form .row.mb-3 {
	    margin-top: 20px;
	}
    .d-md-block {
    	margin-right: 15px;
  	}
  	.pagetitle {
    	margin-top: 10px;
  	}
    .breadcrumb {
    	margin: 20px 15px 0 0;
  	}
    .breadcrumb a {
    	color: #012970;
  	}
  	.breadcrumb a:hover {
      	color: #0d6efd;
    }
    .card {
    	margin-bottom: 15px;
    }
    .card-body {
		min-height: 600px;
		padding-bottom: 0;
    }
    .col-form-label {
    	font-weight: bold;
    }
    .required::after {
		content: " *";
		color: #FF6347;
	}
    .message {
		font-size: 14px;
		color: #F00;
		padding: 10px 5px 0;
	}
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>분실물 등록</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">등록</a></li>
          		<li class="breadcrumb-item"><a href="/dd/admin/communication/lost-property/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">
        		<div class="card">
          			<div class="card-body">
          			
            			<form method="POST" action="/dd/admin/communication/lost-property/addok.do" enctype="multipart/form-data">
            				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            				
            				<!-- 구분 -->
            				
            				<div class="row mb-3">
				                <label class="col-sm-2 col-form-label required">구분</label>
				                <div class="col-sm-10">
				                  	<select name="type" class="form-select">
				                    	<option value="귀금속">귀금속</option>
									    <option value="카드">카드</option>
									    <option value="지갑">지갑</option>
									    <option value="핸드폰">핸드폰</option>
									    <option value="가방">가방</option>
									    <option value="의류">의류</option>
									    <option value="카메라">카메라</option>
									    <option value="안경">안경</option>
									    <option value="기타">기타</option>
				                  	</select>
			                	</div>
			              	</div>
            			
            				<!-- 습득물 -->
            			
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label required">습득물</label>
                				<div class="col-sm-10">
                  					<input type="text" name="name" class="form-control" required oninvalid="this.setCustomValidity(' ')">
                  					<span id="name-message" class="message" style="display: none;"></span>
                				</div>
              				</div>
              				
              				<!-- 습득장소 -->
            			
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label required">습득장소</label>
                				<div class="col-sm-10">
                  					<input type="text" name="location" class="form-control" required oninvalid="this.setCustomValidity(' ')">
                  					<span id="location-message" class="message" style="display: none;"></span>
                				</div>
              				</div>
              				
              				<!-- 습득일 -->
              				
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label required">습득일</label>
                				<div class="col-sm-10">
                  					<input type="date" name="lost_property_date" class="form-control">
               					</div>
              				</div>
              				
              				<!-- 첨부파일 -->
              				
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label">첨부파일</label>
                				<div class="col-sm-10">
                  					<input type="file" name="doc" class="form-control">
                				</div>
              				</div>
						</form>
						
					</div>
				</div>
      		</div>
    	</div>
	</section>
</main>

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>

<script>
	<!-- 기본값(습득일) -->
	
	$(document).ready(function () {
	    $('input[name="lost_property_date"]').val(moment().format('YYYY-MM-DD'));
	});

	var name = $('input[name="name"]');
	var loc = $('input[name="location"]');
	
	<!-- 필수값(습득물) -->
	
	name.blur(function () {
	    if (!name.val().trim()) {
	    	$('#name-message').css('display', 'block');
	    	$('#name-message').text('습득물을 입력하세요.');
	    }
	});
	
	name.keydown(function () {
	    $('#name-message').hide();
	});

	<!-- 필수값(습득장소) -->

	loc.blur(function () {
	    if (!loc.val().trim()) {
	    	$('#location-message').css('display', 'block');
	    	$('#location-message').text('습득장소를 입력하세요.');
	    }
	});
	
	loc.keydown(function () {
	    $('#location-message').hide();
	});
	
	<!-- 전송 -->

	function submit() {
		if (!name.val().trim()) {
	        $('#name-message').css('display', 'block');
	        $('#name-message').text('습득물을 입력하세요.');
	    }

	    if (!loc.val().trim()) {
	        $('#location-message').css('display', 'block');
	        $('#location-message').text('습득장소를 입력하세요.');
	    }

	    if (name.val().trim() && loc.val().trim()) {
	    	$('form').submit();
	    }
	}
</script>