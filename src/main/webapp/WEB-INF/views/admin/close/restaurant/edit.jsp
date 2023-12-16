<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="/dd/resources/air-datepicker/dist/css/datepicker.min.css" rel="stylesheet" type="text/css" media="all">
    <!-- Air datepicker css -->
    <script src="/dd/resources/air-datepicker/dist/js/datepicker.js"></script> <!-- Air datepicker js -->
    <script src="/dd/resources/air-datepicker/dist/js/i18n/datepicker.ko.js"></script> <!-- 달력 한글 추가를 위해 커스텀 -->


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
		<h1>운휴 일정 수정</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">수정</a></li>
          		<li class="breadcrumb-item active"><a href="/dd/admin/close/restaurant/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">
        		<div class="card">
          			<div class="card-body">
            			<h5 class="card-title">운휴 일정 수정</h5>

            			<form method="POST" action="/dd/admin/close/restaurant/editok.do">
							<div class="row mb-3">
								<label class="col-sm-2 col-form-label">식당명</label>
								<div class="col-sm-10">
									<input type="text" id="name" name="name" value="${dto.name}" readOnly required>
								</div>
							</div>

							<div class="row mb-3">
                				<label for="inputDate" class="col-sm-2 col-form-label">날짜</label>
                				<div class="col-sm-10">
                  					운휴 시작일 <input type="date" name="start_date" id="start_date" value="${dto.start_date}">
                  					운휴 종료일 <input type="date" name="end_date" id="end_date"value="${dto.end_date}">
               					</div>
              				</div>

							<!-- 토큰 -->
              				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
              				
              				<input type="hidden" name="restaurant_close_seq" value="${dto.restaurant_close_seq}">
						</form>
					</div>
				</div>
      		</div>
    	</div>
	</section>
</main>

<script>
	const date = document.getElementById('start_date');
	const now = new Date();
	const nowStr = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' + now.getDate();
	
	function selDate() {
	    if (nowStr > start_date.value) {
	        $('#start_date').val(start_date.value.substr(0, 10));
	        $('#start_date').prop('disabled', true);
	    } else {
	        $('#start_date').attr('min', nowStr);
	        $('#start_date').val(start_date.value.substr(0, 10));
	        $('#start_date').prop('disabled', false);
	    }
	    
	    if (nowStr > end_date.value) {
            $('#end_date').prop('disabled', true);
        }
	    changeDate();
	}
	
	function changeDate() {
	    $('#end_date').attr('min', date.value);
	    $('#end_date').val(end_date.value.substr(0, 10));
	    $('#start_date').change(function () {
	        $('#end_date').attr('min', date.value);
	    });
	}
	
	selDate();
	
	$(document).ready(function () {	
		// #start 요소의 값이 변경될 때 이벤트 핸들러
	    $('#start_date').on('change', function () {
	        // 선택된 시작 날짜
	        const selectedStartDate = new Date($('#start_date').val());

	        // 최소 종료 날짜를 선택된 시작 날짜로 설정
	        $('#end_date').attr('min', selectedStartDate.toISOString().split('T')[0]);

	        // 만약 종료 날짜가 선택된 시작 날짜보다 이전이라면 종료 날짜를 최소 종료 날짜로 설정
	        if ($('#end_date').val() && new Date($('#end_date').val()) < selectedStartDate) {
	            $('#end_date').val(selectedStartDate.toISOString().split('T')[0]);
	        }
	    });

	    // 오늘 날짜를 최소 시작 날짜로 설정
	    const today = new Date();
	    const formattedToday = today.toISOString().split('T')[0];
	    $('#start_date').attr('min', formattedToday);
	});
	
	function submit(){
		if(!$('input[name="start_date"]').val().trim() || !$('input[name="end_date"]').val().trim()){
			alert('운휴시작일 또는 운휴종료일을 모두 선택해주세요.');
		}else {
			$('form').submit();
		}
	}
	
</script>