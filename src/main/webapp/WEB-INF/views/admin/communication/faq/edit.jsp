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
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>FAQ 수정</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">수정</a></li>
          		<li class="breadcrumb-item"><a href="/dd/admin/communication/faq/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">
        		<div class="card">
          			<div class="card-body">
          			
            			<form method="POST" action="/dd/admin/communication/faq/editok.do" enctype="multipart/form-data">
            				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            				<input type="hidden" name="faq_seq" value="${dto.faq_seq}" />
            				
            				<!-- 카테고리 -->
            				
            				<div class="row mb-3">
				                <label class="col-sm-2 col-form-label required">카테고리</label>
				                <div class="col-sm-10">
				                  	<select name="type" class="form-select">
				                    	<option value="이용정보" ${dto.type == '이용정보' ? 'selected' : ''}>이용정보</option>
									    <option value="액티비티" ${dto.type == '액티비티' ? 'selected' : ''}>액티비티</option>
									    <option value="혜택" ${dto.type == '혜택' ? 'selected' : ''}>혜택</option>
									    <option value="예매" ${dto.type == '예매' ? 'selected' : ''}>예매</option>
									    <option value="기타" ${dto.type == '기타' ? 'selected' : ''}>기타</option>
				                  	</select>
			                	</div>
			              	</div>
            			
            				<!-- 질문 -->
            			
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label required">질문</label>
                				<div class="col-sm-10">
                  					<input type="text" name="question" class="form-control" value="${dto.question}" required oninvalid="this.setCustomValidity(' ')">
                  					<span id="question-message" class="message" style="display: none;"></span>
                				</div>
              				</div>
              				
              				<!-- 답변 -->
              				
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label required">답변</label>
                				<div class="col-sm-10">
                  					<textarea name="answer" class="form-control" rows="18" required oninvalid="this.setCustomValidity(' ')"><c:out value="${dto.answer}" /></textarea>
                					<span id="answer-message" class="message" style="display: none;"></span>
                				</div>
              				</div>
						</form>
						
					</div>
				</div>
      		</div>
    	</div>
	</section>
	
</main>

<script>
	var question = $('input[name="question"]');
	var answer = $('textarea[name="answer"]');
	
	<!-- 필수값(질문) -->
	
	question.blur(function () {
	    if (!question.val().trim()) {
	    	$('#question-message').css('display', 'block');
	    	$('#question-message').text('질문을 입력하세요.');
	    }
	});
	
	question.keydown(function () {
	    $('#question-message').hide();
	});

	<!-- 필수값(답변) -->

	answer.blur(function () {
	    if (!answer.val().trim()) {
	    	$('#answer-message').css('display', 'block');
	    	$('#answer-message').text('답변을 입력하세요.');
	    }
	});
	
	answer.keydown(function () {
	    $('#answer-message').hide();
	});

	<!-- 전송 -->

	function submit() {
	    if (!question.val().trim()) {
	        $('#question-message').css('display', 'block');
	        $('#question-message').text('질문을 입력하세요.');
	    }

	    if (!answer.val().trim()) {
	        $('#answer-message').css('display', 'block');
	        $('#answer-message').text('답변을 입력하세요.');
	    }

	    if (question.val().trim() && answer.val().trim()) {
	    	$('form').submit();
	    }
	}
</script>