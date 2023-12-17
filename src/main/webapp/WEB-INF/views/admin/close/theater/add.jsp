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
    
    /* 달력 */

.date {
  position: relative;
  width: 300px;
  margin-left: 50px;
  margin-top: 100px;
}

#datepicker {
 font-size: 15px;
  color: #222222;
  width: 300px;
  border: none;
  border-bottom: solid #aaaaaa 1px;
  padding-bottom: 10px;
  text-align: center;
  position: relative;
  background: none;
  z-index: 5;
}

.datepicker::placeholder { color: #aaaaaa; }
.datepicker:focus { outline: none; }
 input#datepicker1::placeholder, input#datepicker2::placeholder {
	color: #aaaaaa;
}

input#datepicker1:focus, input#datepicker2:focus {
	outline: none;
}


.date span {
  display: block;
  position: absolute;
  bottom: 0;
  left: 50%;  /* right로만 바꿔주면 오 - 왼 */
  background-color: #666;
  width: 0;
  height: 2px;
  border-radius: 2px;
  transform: translateX(-50%);
  transition: 0.5s;
}

.date label {
position: absolute;
  color: #aaa;
  left: 10px;
  font-size: 20px;
  bottom: 8px;
  transition: all .2s;
}

input:focus ~ label, input:valid ~ label {
font-size: 16px;
bottom: 40px;
color: #666;
font-weight: bold;
}

input:focus ~ span, input:valid ~ span {
width: 100%;
}
#total-date{
	display: flex;
}

</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>운휴 일정 등록</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">등록</a></li>
          		<li class="breadcrumb-item active"><a href="/dd/admin/close/theater/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">

        		<div class="card">
          			<div class="card-body">
            			<h5 class="card-title">운휴 일정 입력</h5>

            			<form method="POST" action="/dd/admin/close/theater/addok.do">
            				<div class="row mb-3">
				                <label class="col-sm-2 col-form-label">영화관명</label>
				                <div class="col-sm-10">
				                  	<select class="form-select" name="theater_seq" aria-label="Default select example">
				                    	<c:forEach items="${theaterlist}" var="dto">
											<option value="${dto.theater_seq}" >${dto.name}</option>
										</c:forEach>
				                  	</select>
			                	</div>
			              	</div>
						
							<div class="row mb-3">
                				<label for="inputDate" class="col-sm-2 col-form-label">날짜</label>
                				<div class="col-sm-10">
                  					운휴 시작일 <input type="date" name="start_date" id="start" required>
                  					운휴 종료일 <input type="date" name="end_date" id="end" required>
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

<script>
$(document).ready(function () {	
	// #start 요소의 값이 변경될 때 이벤트 핸들러
    $('#start').on('change', function () {
        // 선택된 시작 날짜
        const selectedStartDate = new Date($('#start').val());

        // 최소 종료 날짜를 선택된 시작 날짜로 설정
        $('#end').attr('min', selectedStartDate.toISOString().split('T')[0]);

        // 만약 종료 날짜가 선택된 시작 날짜보다 이전이라면 종료 날짜를 최소 종료 날짜로 설정
        if ($('#end').val() && new Date($('#end').val()) < selectedStartDate) {
            $('#end').val(selectedStartDate.toISOString().split('T')[0]);
        }
    });

    // 오늘 날짜를 최소 시작 날짜로 설정
    const today = new Date();
    const formattedToday = today.toISOString().split('T')[0];
    $('#start').attr('min', formattedToday);
});

function submit(){
	if(!$('input[name="start_date"]').val().trim() || !$('input[name="end_date"]').val().trim()){
		alert('운휴시작일 또는 운휴종료일을 모두 선택해주세요.');
	}else {
		$('form').submit();
	}
}


</script>