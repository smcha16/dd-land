<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>혜택 추가</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">추가</a></li>
          		<li class="breadcrumb-item active"><a href="/dd/admin/pb/benefit/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">

        		<div class="card">
          			<div class="card-body">
            			<h5 class="card-title">General Form Elements</h5> 

            			<form:form method="post" action="/dd/admin/pb/benefit/add.do"
							modelAttribute="benefitDTO">
            			
              				<div class="row mb-3">
                				<label for="inputText" class="col-sm-2 col-form-label">혜택명</label>
                				<div class="col-sm-10">
                  					<input type="text" name="name" class="form-control" >
                  					<form:errors path="name" cssClass="text-danger" />
                				</div>
              				</div>
              				
              				
              				
              				<fieldset class="row mb-3">
                				<legend class="col-form-label col-sm-2 pt-0">Radios</legend>
                				<div class="col-sm-10">
                  					<div class="form-check">
                    					<input class="form-check-input" type="radio" name="type" id="gridRadios1" value="일반" checked>
                    					<label class="form-check-label" for="gridRadios1">일반 혜택</label>
                    					
                  					</div>
				                  	<div class="form-check">
				                    	<input class="form-check-input" type="radio" name="type" id="gridRadios2" value="카드/통신사">
				                    	<label class="form-check-label" for="gridRadios2">카드/통신사 혜택</label>
				                  	</div>
				                  	
                				</div>
			              	</fieldset>
			              	
			              	<div class="row mb-3">
                				<label for="inputDate" class="col-sm-2 col-form-label">시작일</label>
                				<div class="col-sm-10">
                  					<input type="date" name="start_date" class="form-control" >
                  					<form:errors path="start_date" cssClass="text-danger" />
               					</div>
              				</div>
              				<div class="row mb-3">
                				<label for="inputDate" class="col-sm-2 col-form-label">종료일</label>
                				<div class="col-sm-10">
                  					<input type="date" name="end_date" class="form-control">
                  					<form:errors path="end_date" cssClass="text-danger" />
               					</div>
              				</div>
              				
              				
              				<div class="row mb-3">
                				<label for="inputText" class="col-sm-2 col-form-label">할인율</label>
                				<div class="col-sm-10">
                  					<input type="text" name="discount_rate" class="form-control">
                  					<form:errors path="discount_rate" cssClass="text-danger" />
                				</div>
              				</div>
              				
			              	
							
							<div class="row mb-3">
                				<label for="inputNumber" class="col-sm-2 col-form-label">이미지 업로드</label>
                				<div class="col-sm-10">
                  					<input class="form-control" type="file" id="formFile">
                				</div>
              				</div>
							
						</form:form>
			
					</div>
				</div>

      		</div>
    	</div>
	</section>

</main>
<script>
	function submit() {
		$('form').submit();
	}
</script>