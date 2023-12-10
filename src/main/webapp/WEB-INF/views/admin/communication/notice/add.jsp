<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	#main h1 {
		font-size: 2rem !important;
   		margin-top: 45px !important;
   		margin-left: 10px;
  	}
  	form div:nth-child(1) {
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
      	color: #ce1212;
    }
    .card {
    	margin-bottom: 15px;
    }
    .card-body {
		min-height: 600px;
		padding-bottom: 0;
    }
    .message {
		font-size: 14px;
		color: #F00;
		padding: 10px 5px;
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
          		<li id="add-button" class="breadcrumb-item"><a href="#">추가</a></li>
          		<li class="breadcrumb-item active"><a href="/dd/admin/communication/notice/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">
        		<div class="card">
          			<div class="card-body">
            			<form method="POST" action="/dd/admin/communication/notice/addok.do" enctype="multipart/form-data">
              				<div class="row mb-3">
                				<label for="subject" class="col-sm-2 col-form-label">제목</label>
                				<div class="col-sm-10">
                  					<input type="text" name="subject" class="form-control" required oninvalid="this.setCustomValidity(' ')">
                  					<span id="subject-message" class="message" style="display: none;"></span>
                				</div>
              				</div>
              				<div class="row mb-3">
                				<label for="content" class="col-sm-2 col-form-label">내용</label>
                				<div class="col-sm-10">
                  					<textarea name="content" class="form-control" rows="16" required oninvalid="this.setCustomValidity(' ')"></textarea>
                					<span id="content-message" class="message" style="display: none;"></span>
                				</div>
              				</div>
              				<div class="row mb-3">
                				<label for="inputNumber" class="col-sm-2 col-form-label">첨부파일</label>
                				<div class="col-sm-10">
                  					<input type="file" name="doc" class="form-control">
                				</div>
              				</div>
              				<fieldset class="row mb-3">
                				<legend class="col-form-label col-sm-2 pt-0">고정여부</legend>
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
	$('input[name="subject"]').blur(function () {
	    if (!$('input[name="subject"]').val().trim()) {
	    	$('#subject-message').css('display', 'block');
	    	$('#subject-message').text('제목을 입력하세요.');
	    }
	});
	
	$('input[name="subject"]').keydown(function () {
	    $('#subject-message').css('display', 'none');
	});
	
	$('textarea[name="content"]').blur(function () {
	    if (!$('textarea[name="content"]').val().trim()) {
	    	$('#content-message').css('display', 'block');
	    	$('#content-message').text('내용을 입력하세요.');
	    }
	});
	
	$('textarea[name="content"]').keydown(function () {
	    $('#content-message').css('display', 'none');
	});

	$('#add-button').click(function () {
		if (!$('input[name="subject"]').val().trim()) {
	    	$('#subject-message').css('display', 'block');
	    	$('#subject-message').text('제목을 입력하세요.');
	    }
		
		if (!$('textarea[name="content"]').val().trim()) {
	    	$('#content-message').css('display', 'block');
	    	$('#content-message').text('내용을 입력하세요.');
	    }
	});
</script>