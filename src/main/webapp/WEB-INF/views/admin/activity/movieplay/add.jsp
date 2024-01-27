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
    
    /* 유효성검사 CSS */
    .check-time-validation {
    	padding: 10px 10px;
    }
    
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>영화 상영 등록</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">등록</a></li>
          		<li class="breadcrumb-item active"><a href="/dd/admin/activity/movieplay/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">

        		<div class="card">
          			<div class="card-body">
            			<h5 class="card-title">영화 상영 정보 입력</h5>

						<!-- 영화명, 상영 시간, 상영 시작일, 상영 종료일, 상영 영화관 -->
            			<form method="POST" action="/dd/admin/activity/movieplay/addok.do">
            			
            				<!-- 영화명 -->
              				<div class="row mb-3">
                				<label for="movie_seq" class="col-sm-2 col-form-label required">영화명</label>
                				<div class="col-sm-10">
                					<select name="movie_seq" id="movie_seq" class="form-select" aria-label="Default select example">
                						<option value="title" selected>영화를 선택해주세요.</option>
                						<c:forEach items="${mlist}" var="dto">
                							<option value="${dto.movie_seq}"><c:out value="${dto.name}" /></option>
                						</c:forEach>
                					</select>
                				</div>
              				</div>

							<!-- 상영 시간 -->
              				<div class="row mb-3">
                				<label for="time" class="col-sm-2 col-form-label required" >상영 시간</label>
                				<div class="col-sm-10">
                					<input type="text" id="time" name="time" class="form-control" placeholder="상영 시간(HH:MM)을 입력해주세요." required>
                					<div style="height: 30px;">
                  						<div class="check-time-validation"></div>
                  					</div>
                				</div>
              				</div>

							<!-- 상영 시작일 -->
              				<div class="row mb-3">
                				<label for="start_date" class="col-sm-2 col-form-label required">상영 시작일</label>
                				<div class="col-sm-10">
                  					<input type="date" id="start_date" name="start_date" class="form-control" required>
                				</div>
              				</div>

							<!-- 상영 종료일 -->
              				<div class="row mb-3">
                				<label for="end_date" class="col-sm-2 col-form-label required">상영 종료일</label>
                				<div class="col-sm-10">
                  					<input type="date" id="end_date" name="end_date" class="form-control" required>
                				</div>
              				</div>

              				<!-- 상영 영화관 -->
              				<div class="row mb-3">
                				<label for="theater_seq" id="theater_seq" class="col-sm-2 col-form-label required">상영 영화관</label>
                				<div class="col-sm-10">
                					<select name="theater_seq" class="form-select" aria-label="Default select example">
                						<option value="title" selected>상영 영화관을 선택해주세요.</option>
                						<c:forEach items="${tlist}" var="dto">
                							<option value="${dto.theater_seq}"><c:out value="${dto.name}" /></option>
                						</c:forEach>
                					</select>
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

<!-- MoviePlay용 JavaScript -->
<script>

	/* 필수 항목이 반드시 입력되어야만 submit 클릭 시 넘어가도록 */
	function submit() {
		
		if (!$('input[name="time"]').val().trim()
				|| $('input[name="start_date"]').val() == "" || $('input[name="end_date"]').val() == ""
				|| $('select[name="movie_seq"]').val() == 'title' || $('select[name="theater_seq"]').val() == 'title'
				|| $('.check-time-validation').data('type') != 'y') {
			
			if ($('.check-time-validation').data('type') == 'n') {
				alert('올바른 시간 형식(HH:MM)으로 입력해주세요.');
			} else {
				alert('필수 항목을 입력해주세요.');
			}
			
		} else {
			$('form').submit();
		}
	}
	
	/* 상영 시간 입력 형식 유효성 검사 */
	const pattern = /^\d{2}:\d{2}$/;

	$('input[name="time"]').keyup(function() {
		
		let time = $('input[name="time"]').val();
		//console.log(time);
		
		if (pattern.test(time)) {
		    $('.check-time-validation').text('');
		    $('.check-time-validation').css('color', '#212529');
		    $('.check-time-validation').data('type', 'y');
		    
		} else {
		    $('.check-time-validation').text('올바른 형식(HH:MM)으로 입력해주세요.');
		    $('.check-time-validation').css('color', '#dc3545');
		    $('.check-time-validation').data('type', 'n');
		}
		
	});
	
	/* 날짜 입력 유효성 검사 */
	const startDate = document.getElementById('start_date');
	
	const now = new Date();
	 
	const year = now.getFullYear();
	const month = now.getMonth() + 1;
	const date = now.getDate();
	
	const nowStr = `\${year}-\${month >= 10 ? month : '0' + month}-\${date >= 10 ? date : '0' + date}`;
	
	$('#start_date').attr('min', nowStr);//시작일은 최소 오늘 이후
	$('#end_date').attr('min', nowStr);//종료일은 최소 오늘 이후
	
	function isValidEndDate() {
		$('#end_date').attr('min', startDate.value);
		
		//시작일을 변경 했을 경우, 시작일이 기존에 설정한 종료일보다 미래일 경우 종료일 값 초기화
		if ($('#start_date').val() > $('#end_date').val()) {
			$('#end_date').val('');
		}
	}
	
	$('#start_date').change(function() {
		isValidEndDate();
	});
	
</script>