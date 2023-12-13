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
		color: #F00;
	}
    .message {
		font-size: 14px;
		color: #F00;
		padding: 10px 5px 0;
	}
	.attached {
    	display: inline-block;
    	padding: 10px 0;
    }
    .attached span {
    	display: inline-block;
    	vertical-align: middle;
    	font-weight: bold;
    	color: #777;
    	cursor: pointer;
    }
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>분실물 수정</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">수정</a></li>
          		<li class="breadcrumb-item"><a href="/dd/admin/communication/lost-property/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">
        		<div class="card">
          			<div class="card-body">
          			
            			<form method="POST" action="/dd/admin/communication/lost-property/editok.do" enctype="multipart/form-data">
            				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            				<input type="hidden" name="lost_property_seq" value="${dto.lost_property_seq}" />
            				
            				<!-- 구분 -->
            				
            				<div class="row mb-3">
				                <label class="col-sm-2 col-form-label required">구분</label>
				                <div class="col-sm-10">
				                  	<select name="type" class="form-select">
				                    	<option value="귀금속" ${dto.type == '귀금속' ? 'selected' : ''}>귀금속</option>
									    <option value="카드" ${dto.type == '카드' ? 'selected' : ''}>카드</option>
									    <option value="지갑" ${dto.type == '지갑' ? 'selected' : ''}>지갑</option>
									    <option value="핸드폰" ${dto.type == '핸드폰' ? 'selected' : ''}>핸드폰</option>
									    <option value="가방" ${dto.type == '가방' ? 'selected' : ''}>가방</option>
									    <option value="의류" ${dto.type == '의류' ? 'selected' : ''}>의류</option>
									    <option value="카메라" ${dto.type == '카메라' ? 'selected' : ''}>카메라</option>
									    <option value="안경" ${dto.type == '안경' ? 'selected' : ''}>안경</option>
									    <option value="기타" ${dto.type == '기타' ? 'selected' : ''}>기타</option>
				                  	</select>
			                	</div>
			              	</div>
            			
            				<!-- 습득물 -->
            			
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label required">습득물</label>
                				<div class="col-sm-10">
                  					<input type="text" name="name" class="form-control" value="${dto.name}" required oninvalid="this.setCustomValidity(' ')">
                  					<span id="name-message" class="message" style="display: none;"></span>
                				</div>
              				</div>
              				
              				<!-- 습득장소 -->
              				
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label required">습득장소</label>
                				<div class="col-sm-10">
                  					<textarea name="location" class="form-control" rows="10" required oninvalid="this.setCustomValidity(' ')"><c:out value="${dto.location}" /></textarea>
                					<span id="location-message" class="message" style="display: none;"></span>
                				</div>
              				</div>
              				
              				<!-- 습득일 -->
              				
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label required">습득일</label>
                				<div class="col-sm-10">
                  					<input type="date" name="lost_property_date" class="form-control" value="${dto.lost_property_date}">
               					</div>
              				</div>
              				
              				<!-- 첨부파일 -->
              				
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label option">첨부파일</label>
                				<div class="col-sm-10">
                					<c:if test="${dto.img == null}">
                  						<input type="file" name="doc" class="form-control">
                  					</c:if>
                  					
                					<c:if test="${dto.img != null}">
                  						<input type="file" name="doc" class="form-control" disabled>
	                  					<div style="height: 30px;">
	                  						<div class="attached">
	                  							<i class="bi bi-image"></i> <c:out value="${dto.img}" /> <span onclick="delAttached()">&times;</span>
	                  						</div>
	                  					</div>
                  					</c:if>
                				</div>
              				</div>
              				
              				<!-- 처리결과 -->
              				
              				<fieldset class="row mb-3">
                				<legend class="col-form-label col-sm-2 pt-0 required">처리결과</legend>
                				<div class="col-sm-10">
                  					<div id="radio" class="form-check">
                    					<input type="radio" name="result" id="result-y" class="form-check-input" value="보관중" checked>
                    					<label for="result-y">보관중</label>
                  					</div>
				                  	<div class="form-check">
				                    	<input type="radio" name="result" id="result-n" class="form-check-input" value="수령완료">
				                    	<label for="result-n">수령완료</label>
				                  	</div>
                				</div>
			              	</fieldset>
						</form>
						
					</div>
				</div>
      		</div>
    	</div>
	</section>
</main>

<script>
	var property = $('input[name="name"]');
	var loc = $('textarea[name="location"]');
	
	<!-- 필수값(습득물) -->
	
	property.blur(function () {
	    if (!property.val().trim()) {
	    	$('#name-message').css('display', 'block');
	    	$('#name-message').text('습득물을 입력하세요.');
	    }
	});
	
	property.keydown(function () {
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
	
	<!-- 첨부파일 -->
	
	$('input[type="file"]:disabled').hover(function() {
		$('input[type="file"]:disabled').attr('title', '기존 첨부파일을 삭제하세요.');
	});
	
	function delAttached() {
		$(event.target).parent().remove();
		$('input[type="file"]').prop('disabled', false);
	}
	
	<!-- 전송 -->

	function submit() {
		if (!property.val().trim()) {
	        $('#name-message').css('display', 'block');
	        $('#name-message').text('습득물을 입력하세요.');
	    }

	    if (!loc.val().trim()) {
	        $('#location-message').css('display', 'block');
	        $('#location-message').text('습득장소를 입력하세요.');
	    }

	    if (property.val().trim() && loc.val().trim()) {
	    	$('form').submit();
	    }
	}
</script>