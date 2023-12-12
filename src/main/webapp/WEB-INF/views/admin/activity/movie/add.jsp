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
    
    /* placeholder CSS */
    #name {
    	
    }
    
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>영화 등록</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">등록</a></li>
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
            			<form method="POST" action="/dd/admin/activity/movie/addok.do" enctype="multipart/form-data">
            			
            				<!-- 영화명 -->
              				<div class="row mb-3">
                				<label for="name" class="col-sm-2 col-form-label required">영화명</label>
                				<div class="col-sm-10">
                  					<input type="text" id="name" name="name" class="form-control" placeholder="영화명을 입력해주세요." required>
                  					<div style="height: 30px;">
                  						<div class="check-duplication"></div>
                  					</div>
                				</div>
              				</div>

							<!-- 줄거리 -->
              				<div class="row mb-3">
                				<label for="info" class="col-sm-2 col-form-label required" >줄거리</label>
                				<div class="col-sm-10">
                  					<textarea id="story" name="story" class="form-control" style="height: 100px" placeholder="영화의 줄거리를 입력해주세요." required></textarea>
                				</div>
              				</div>

							<!-- 러닝타임 -->
              				<div class="row mb-3">
                				<label for="capacity" class="col-sm-2 col-form-label required">러닝타임</label>
                				<div class="col-sm-10">
                  					<input type="number" id="runningtime" name="runningtime" class="form-control" placeholder="러닝타임(숫자(분))을 입력해주세요." required>
                				</div>
              				</div>

              				<!-- 포스터 이미지 -->
              				<div class="row mb-3">
                				<label for="formFile" class="col-sm-2 col-form-label">포스터 이미지</label>
                				<div class="col-sm-10">
                  					<input class="form-control" type="file" id="formFile" name="imgs">
                				</div>
              				</div>
              				
            				<!-- 영화 예고편 영상 -->
              				<div class="row mb-3">
                				<label for="name" class="col-sm-2 col-form-label">영화 예고편</label>
                				<div class="col-sm-10">
                					<textarea id="preview" name="preview" class="form-control" style="height: 100px" placeholder="영화 예고편 영상의 태그를 입력해주세요."></textarea>
                				</div>
              				</div>
              				
              				<!-- 토큰 -->
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
							
						</form>
			
					</div>
				</div>

      		</div>
    	</div>
	</section>

</main>

<!-- movie add용 JavaScript -->
<script>

	/* 필수 항목이 반드시 입력되어야만 submit 클릭 시 넘어가도록 */
	function submit() {
	
	//console.log($('textarea[name="info"]').val());
	//console.log($('textarea[name="info"]').val().trim());
	
	
	if (!$('input[name="name"]').val().trim() || !$('textarea[name="story"]').val().trim()
			|| !$('input[name="runningtime"]').val().trim()) {
			
		alert('필수 항목을 입력해주세요.');
		
	} else {
		$('form').submit();
	}
	//console.log($('input[name="name"]').val().trim());
	}
	
</script>