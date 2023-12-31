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
	.option::after {
		content: " *";
		color: #00F;
	}
    .message {
		font-size: 14px;
		color: #F00;
		padding: 10px 5px 0;
	}
    #radio {
    	margin-top: 0;
	}
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>공지사항 등록</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">등록</a></li>
          		<li class="breadcrumb-item"><a href="/dd/admin/communication/notice/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">
        		<div class="card">
          			<div class="card-body">
          			
            			<form method="POST" action="/dd/admin/communication/notice/addok.do" enctype="multipart/form-data">
            				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            			
            				<!-- 제목 -->
            			
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label required">제목</label>
                				<div class="col-sm-10">
                  					<input type="text" name="subject" class="form-control" required oninvalid="this.setCustomValidity(' ')">
                  					<span id="subject-message" class="message" style="display: none;"></span>
                				</div>
              				</div>
              				
              				<!-- 내용 -->
              				
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label option">내용</label>
                				<div class="col-sm-10">
                  					<textarea name="content" class="form-control" rows="16" required oninvalid="this.setCustomValidity(' ')"></textarea>
                				</div>
              				</div>
              				
              				<!-- 첨부파일 -->
              				
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label option">첨부파일</label>
                				<div class="col-sm-10">
                  					<input type="file" name="doc" class="form-control">
                  					<span id="content-message" class="message" style="display: none;"></span>
                				</div>
              				</div>
              				
              				<!-- 고정여부 -->
              				
              				<fieldset class="row mb-3">
                				<legend class="col-form-label col-sm-2 pt-0 required">고정여부</legend>
                				<div class="col-sm-10">
                  					<div id="radio" class="form-check">
                    					<input type="radio" name="fix" id="fix-y" class="form-check-input" value="y">
                    					<label for="fix-y">네</label>
                  					</div>
				                  	<div class="form-check">
				                    	<input type="radio" name="fix" id="fix-n" class="form-check-input" value="n" checked>
				                    	<label for="fix-n">아니오</label>
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
	var subject = $('input[name="subject"]');
	
	<!-- 필수값(제목) -->

	subject.blur(function () {
	    if (!subject.val().trim()) {
	    	$('#subject-message').css('display', 'block');
	    	$('#subject-message').text('제목을 입력하세요.');
	    }
	});
	
	subject.keydown(function () {
	    $('#subject-message').hide();
	});
	
	<!-- 필수값(내용 또는 첨부파일) -->

	$('textarea[name="content"], input[name="doc"]').on('input change', function () {
	    $('#content-message').hide();
	});
	
	<!-- 전송 -->

	function submit() {
	    if (!subject.val().trim()) {
	        $('#subject-message').css('display', 'block');
	        $('#subject-message').text('제목을 입력하세요.');
	    }

	    var content = document.querySelector('textarea[name="content"]');
	    var doc = document.querySelector('input[name="doc"]');

	    if (!content.value.trim() && !doc.files.length) {
	        $('#content-message').css('display', 'block');
	        $('#content-message').text('내용을 입력하거나 첨부파일을 선택하세요.');
	    }

	    if (subject.val().trim() && (content.value.trim() || doc.files.length)) {
	    	$('form').submit();
	    }
	}
</script>