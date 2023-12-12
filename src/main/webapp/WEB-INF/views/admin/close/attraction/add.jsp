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
    }
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>운휴 일정 등록</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">등록</a></li>
          		<li class="breadcrumb-item active"><a href="/dd/admin/close/attraction/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">

        		<div class="card">
          			<div class="card-body">
            			<h5 class="card-title">운휴 일정 입력</h5>

            			<form>
            				<div class="row mb-3">
				                <label class="col-sm-2 col-form-label">어트랙션명</label>
				                <div class="col-sm-10">
				                  	<select class="form-select" aria-label="Default select example">
				                    	<c:forEach items="${attlist}" var="dto">
											<option value="${dto.attraction_seq}">${dto.name}</option>
										</c:forEach>
				                  	</select>
			                	</div>
			              	</div>
              				<div class="row mb-3">
                				<label for="inputDate" class="col-sm-2 col-form-label">Date</label>
                				<div class="col-sm-10">
                  					<input type="date" class="form-control">
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