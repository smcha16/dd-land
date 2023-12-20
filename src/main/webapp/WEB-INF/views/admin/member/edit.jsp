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
    <style>
    .text-danger {
        color: red; /* 빨간색으로 지정, 필요에 따라 스타일을 조절할 수 있습니다. */
    }
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>회원 수정</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">수정</a></li>
          		<li class="breadcrumb-item active"><a href="/dd/admin/member/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">

        		<div class="card">
          			<div class="card-body">
            			<h5 class="card-title">General Form Elements</h5> 

            			<form:form method="post" action="/dd/admin/member/edit.do"
							modelAttribute="MemberDTO" >
            			
              				<div class="row mb-3">
                				<label for="inputText" class="col-sm-2 col-form-label">이름</label>
                				<div class="col-sm-10">
                  					<input type="text" name="name" class="form-control" value="${dto.name}" >
                  					<form:errors path="name" cssClass="text-danger" />
                				</div>
              				</div>
              				
              				<div class="row mb-3">
                				<label for="inputText" class="col-sm-2 col-form-label">이메일</label>
                				<div class="col-sm-10">
                  					<input type="text" name="email" class="form-control" value="${dto.email}" >
                  					<form:errors path="email" cssClass="text-danger" />
                				</div>
              				</div>
              				
              				
              				<div class="row mb-3">
                				<label for="inputText" class="col-sm-2 col-form-label">전화번호</label>
                				<div class="col-sm-10">
                  					<input type="text" name="tel" class="form-control" value="${dto.tel}" >
                  					<form:errors path="tel" cssClass="text-danger" />
                				</div>
              				</div>
              				
              				<div class="row mb-3">
                				<label for="inputText" class="col-sm-2 col-form-label">생년월일</label>
                				<div class="col-sm-10">
                  					<input type="text" name="birth" class="form-control" value="${dto.birth}" >
                  					<form:errors path="birth" cssClass="text-danger" />
                				</div>
              				</div>
              				
              				<div class="row mb-3">
                				<label for="inputText" class="col-sm-2 col-form-label">주소</label>
                				<div class="col-sm-10">
                  					<input type="text" name="address" class="form-control" value="${dto.address}" >
                  					<form:errors path="address" cssClass="text-danger" />
                				</div>
              				</div>
              				
              				<div class="row mb-3">
                				<label for="inputText" class="col-sm-2 col-form-label">활동여부</label>
                				<div class="col-sm-10">
                  					<input type="text" name="ing" class="form-control" value="${dto.ing}" >
                  					<form:errors path="ing" cssClass="text-danger" />
                				</div>
              				</div>
              				
              				
              				
              				<input type="hidden" name = "user_seq" value="${dto.user_seq }">
              				<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}">
							
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