<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
    .breadcrumb {
    	margin-right: 20px 15px 0 0;
  	}
    .breadcrumb a {
    	color: #012970;
  	}
  	.breadcrumb a:hover {
      	color: #ce1212;
    }
    .card-body {
      min-height: 600px;
    }
    
    label {
		font-weight: bold !important;
    }
    
    /* 필수 입력사항 required */
    .required::after  {
    	content: ' *';
    	color: #b71c1c;
    }
    
    /* 기존 첨부파일 목록 CSS */
    .attached {
    	padding: 10px 0;
    	display: inline-block;
    }
    
    .attached > span {
    	cursor: pointer;
    	display: inline-block;
    	vertical-align: middle;
    	font-weight: bold;
    	color: #777;
    }
    
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>영화 수정</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">수정</a></li>
          		<li class="breadcrumb-item active"><a href="/dd/admin/activity/movie/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">

        		<div class="card">
          			<div class="card-body">
            			<h5 class="card-title">영화 정보 입력</h5>

<!-- 영화명, 줄거리, 러닝타임, 포스터이미지, 영화예고편영상(null) -->
            			<form method="POST" action="/dd/admin/activity/movie/editok.do" enctype="multipart/form-data">
            			
            				<!-- 영화명 -->
              				<div class="row mb-3">
                				<label for="name" class="col-sm-2 col-form-label required">영화명</label>
                				<div class="col-sm-10">
                  					<input type="text" id="name" name="name" class="form-control" placeholder="영화명을 입력해주세요." value="${dto.name}" required>
                				</div>
              				</div>

							<!-- 줄거리 -->
              				<div class="row mb-3">
                				<label for="info" class="col-sm-2 col-form-label required" >줄거리</label>
                				<div class="col-sm-10">
                  					<textarea id="story" name="story" class="form-control" style="height: 100px" placeholder="영화의 줄거리를 입력해주세요." required><c:out value="${dto.story}"/></textarea>
                				</div>
              				</div>

							<!-- 러닝타임 -->
              				<div class="row mb-3">
                				<label for="capacity" class="col-sm-2 col-form-label required">러닝타임</label>
                				<div class="col-sm-10">
                  					<input type="number" id="runningtime" name="runningtime" class="form-control" placeholder="러닝타임(숫자(분))을 입력해주세요." value="${dto.runningtime}" required>
                				</div>
              				</div>

              				<!-- 포스터 이미지 -->
              				<div class="row mb-3">
                				<label for="formFile" class="col-sm-2 col-form-label">포스터 이미지</label>
                				<div class="col-sm-10">
                					<c:if test="${dto.img == 'movie.png'}">
                  						<input class="form-control" type="file" id="formFile" name="imgs">
                  					</c:if>
                					<c:if test="${dto.img != 'movie.png'}">
                  						<input class="form-control" type="file" id="formFile" name="imgs" disabled>
	                  					<div style="height: 30px;">
	                  						<div class="attached"><i class="bi bi-image"></i> <c:out value="${dto.img}" /> <span onclick="delAttached()">&times;</span></div>
	                  					</div>
                  					</c:if>
                				</div>
              				</div>
              				
            				<!-- 영화 예고편 영상 -->
              				<div class="row mb-3">
                				<label for="name" class="col-sm-2 col-form-label">영화 예고편</label>
                				<div class="col-sm-10">
                  					<input type="text" id="preview" name="preview" class="form-control" placeholder="영화 예고편 영상의 링크를 입력해주세요." value="${dto.preview}" required>
                				</div>
              				</div>
              				
							<!-- 영화 seq -->
							<input type="hidden" name="movie_seq" value="${dto.movie_seq}">
							
              				<!-- 토큰 -->
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
							
						</form>
			
					</div>
				</div>

      		</div>
    	</div>
	</section>

</main>

<!-- movie edit용 JavaScript -->
<script>

	function submit() {
		$('form').submit();
	}
	
	/* 기존 첨부한 첨부파일 삭제 시 > 지우고 input[type="file"] 태그 활성화 */
	function delAttached() {
		$(event.target).parent().remove();
		$('input[type="file"]').prop('disabled', false);
	}
	
	/* 비활성화된 input 태그 도움말 */
	$('input[type="file"]:disabled').hover(function() {
		
		$('input[type="file"]:disabled').attr('title', '신규 이미지를 등록하시려면 기존에 첨부한 첨부파일을 삭제해주세요.');
		
	});
	
	
	
</script>