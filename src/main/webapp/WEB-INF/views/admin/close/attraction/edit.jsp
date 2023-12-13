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
          		<li class="breadcrumb-item active"><a href="/dd/admin/close/attraction/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">
        		<div class="card">
          			<div class="card-body">
            			<h5 class="card-title">운휴 일정 수정</h5>

            			<form method="POST" action="/dd/admin/close/attraction/editok.do">
							<div class="row mb-3">
								<label class="col-sm-2 col-form-label">어트랙션명</label>
								<div class="col-sm-10">
									<%-- <select name="attraction_seq" class="form-control">
											
											<option value="${dto.attraction_seq}">${dto.name}</option>
									
									</select> ??????????????--%>
								</div>
							</div>

							<div class="row mb-3">
                				<label for="inputDate" class="col-sm-2 col-form-label">날짜</label>
                				<div class="col-sm-10">
                  					운휴 시작일 <input type="date" name="start_date" id="start_date">
                  					운휴 종료일 <input type="date" name="end_date" id="end_date">
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
	const date = document.getElementById('start_date');
	
	$('select[name=attraction]').change(function() {
			for (let i=0; i<close_list.length; i++) {
				if (close_list[i].seq == $(this).val()) {
					selDate(i);
				}
			}
	});
	
	
	function selDate(i) {
		const now = new Date();  //현재날짜
		const nowStr = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' +  now.getDate();
		
		if (nowStr > close_list[i].start_date) {
			$('#start_date').val(close_list[i].start_date.substr(0,  10));
			$('#start_date').prop('readOnly', true);  //운휴가 현재날짜보다 이전이면 -> 운휴 이미 시작이므로 readonly.
		} else {
			$('#start_date').attr('min', nowStr);
			$('#start_date').val(close_list[i].start_date.substr(0,  10));
			$('#start_date').prop('readOnly', false);
		}
	
		changeDate(i);
	}
	
	
	function changeDate(i) {
		$('#end_date').attr('min', date.value);  //end_date는 재선택한 운휴시작일 넣어주기
		$('#end_date').val(close_list[i].end_date.substr(0,  10));
		$('#start_date').change(function() {
			$('#end_date').attr('min', date.value);
		});
	}
	
	
	const close_list = [];
	<c:forEach items="${list}" var="dto">
		close_list.push({
			seq: ${dto.attraction_close_seq},
			start_date:'${dto.start_date}',
			end_date:'${dto.end_date}'
		});
		
	</c:forEach>
	
	selDate(0);
	
</script>