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
    .attached {
    	display: inline-block;
    	padding: 10px 5px;
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
		<h1>리뷰 수정</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">수정</a></li>
          		<li class="breadcrumb-item"><a href="/dd/admin/communication/review/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">
        		<div class="card">
          			<div class="card-body">
          			
            			<form method="POST" action="/dd/admin/communication/review/editok.do" enctype="multipart/form-data">
            				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            				<input type="hidden" name="review_seq" value="${dto.review_seq}" />
            			
            				<!-- 제목 -->
            				
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label required">제목</label>
                				<div class="col-sm-10">
                  					<input type="text" name="subject" class="form-control" value="${dto.subject}" required oninvalid="this.setCustomValidity(' ')">
                  					<span id="subject-message" class="message" style="display: none;"></span>
                				</div>
              				</div>
              				
              				<!-- 내용 -->
              				
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label option">내용</label>
                				<div class="col-sm-10">
                  					<textarea name="content" class="form-control" rows="16" required oninvalid="this.setCustomValidity(' ')">${dto.content}</textarea>
                					<span id="content-message" class="message" style="display: none;"></span>
                				</div>
              				</div>              				
              				
              				<!-- 첨부파일 -->
              				
              				<div class="row mb-3">
                				<label class="col-sm-2 col-form-label option">첨부파일</label>
                				<div class="col-sm-10">
                					<input type="file" name="doc" class="form-control" multiple disabled>
                  					
                					<c:if test="${not empty dto.imgList[0].review_img_seq}">
	                  					<div style="height: 30px;">
		                  					<c:forEach items="${dto.imgList}" var="dto">
		                  						<div class="attached">
		                  							<i class="bi bi-image"></i> <c:out value="${dto.img}" /> <span onclick="delAttached(${dto.review_img_seq})">&times;</span>
		                  						</div>
		                  					</c:forEach>
	                  					</div>
                  					</c:if>
                				</div>
              				</div>
              				
              				<input type="hidden" name="seqList">
						</form>
						
					</div>
				</div>
      		</div>
    	</div>
	</section>
</main>

<script>
	<!-- 개행 처리 -->
	$('textarea[name="content"]').val($('textarea[name="content"]').val().replace(/<br>/g, '\r\n'));

	var subject = $('input[name="subject"]');
	var content = $('textarea[name="content"]');
	
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
	
	<!-- 필수값(내용) -->
	
	content.blur(function () {
	    if (!content.val().trim()) {
	    	$('#content-message').css('display', 'block');
	    	$('#content-message').text('제목을 입력하세요.');
	    }
	});
	
	content.keydown(function () {
	    $('#content-message').hide();
	});
	
	<!-- 첨부파일 -->
	
	$('input[type="file"]:disabled').hover(function() {
		$('input[type="file"]:disabled').attr('title', '첨부파일을 삭제할 권한이 없습니다.');
	});
	
	let seqList = "";
	
	function delAttached(seq) {
		seqList += seq + ",";
		
		$(event.target).parent().remove();
	}
	
	<!-- 전송 -->

	function submit() {
	    if (!subject.val().trim()) {
	        $('#subject-message').css('display', 'block');
	        $('#subject-message').text('제목을 입력하세요.');
	    }
	    
	    if (!subject.val().trim()) {
	        $('#content-message').css('display', 'block');
	        $('#content-message').text('내용을 입력하세요.');
	    }

	    if (subject.val().trim() && content.val().trim()) {
	    	seqList = seqList.slice(0, -1);
	    	
			$('input[name="seqList"]').val(seqList);
	    	
	    	$('form').submit();
	    }
	}
</script>