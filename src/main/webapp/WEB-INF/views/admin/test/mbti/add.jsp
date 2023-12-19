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

.required::after {
	content: ' *';
	color: #b71c1c;
}

.check-location-duplication {
	padding: 10px 10px;
}

.check-name-duplication {
	padding: 10px 10px;
}
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>MBTI별 추천 등록</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item">
          			<a href="javascript:void(0);" onclick="submit();">등록</a>
          		</li>
          		<li class="breadcrumb-item active">
          			<a href="/dd/admin/test/mbti/view.do">취소</a>
          		</li>
      		</ol>
		</nav>
	</div>

	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">MBTI별 추천 정보 입력</h5>

						<!-- MBTI명, 결과, 어트랙션, 코스, 이미지 -->
						<form method="POST" action="/dd/admin/test/mbti/addok.do" enctype="multipart/form-data">

							<!-- MBTI명 -->
							<div class="row mb-3">
								<label for="name" class="col-sm-2 col-form-label required">MBTI명</label>
								
								<div class="col-sm-10">
									<input type="text" id="name" name="name" class="form-control" placeholder="MBTI명을 입력해주세요." required>
									<div style="height: 30px;">
										<div class="check-name-duplication"></div>
									</div>
								</div>
							</div>
							
							<!-- MBTI별 추천 결과 -->
              				<div class="row mb-3">
                				<label for="result" class="col-sm-2 col-form-label required" >결과</label>
                				<div class="col-sm-10">
                  					<textarea id="result" name="result" class="form-control" style="height: 100px" placeholder="MBTI별 추천 결과를 입력해주세요." required></textarea>
                				</div>
              				</div>
              				
              				<!-- 어트랙션 선택 -->
							<div class="row mb-3">
							    <label for="attraction" class="col-sm-2 col-form-label required">어트랙션 선택</label>
							    <div class="col-sm-10">
							        <select id="attraction" name="attraction_seq" class="form-control" required>
							            <option value="">-- 선택하세요 --</option>
							            <c:forEach items="${attractionList}" var="attraction">
							                <option value="${attraction.attraction_seq}">${attraction.name}</option>
							            </c:forEach>
							        </select>
							    </div>
							</div>
							
							<!-- 코스 선택 -->
							<div class="row mb-3">
							    <label for="course" class="col-sm-2 col-form-label required">코스 선택</label>
							    <div class="col-sm-10">
							        <select id="course" name="course_seq" class="form-control" required>
							            <option value="">-- 선택하세요 --</option>
							            <c:forEach items="${courseList}" var="course">
							                <option value="${course.course_seq}">${course.name}</option>
							            </c:forEach>
							        </select>
							    </div>
							</div>
              				
              				<!-- 이미지 -->
							<div class="row mb-3">
								<label for="formFile" class="col-sm-2 col-form-label">이미지</label>
								
								<div class="col-sm-10">
									<input class="form-control" type="file" id="formFile" name="image">
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
	function submit() {
		if (!$('input[name="name"]').val().trim() || !$('textarea[name="result"]').val().trim() ||
		        $('.check-name-duplication').data('type') != 'y' || !$('#attraction').val() || !$('#course').val()) {
			
			if ($('.check-name-duplication').data('type') == 'n') {
				alert('중복된 항목을 수정해주세요.');
			} else {
				alert('필수 항목을 입력해주세요.');
			}
			
		} else {
            // 데이터 전송
            $('form').submit();
        }
	}
	
	// mbti 이름 중복 검사
	$('input[name="name"]').keyup(function() {
		
		let obj = {
			name: $('input[name="name"]').val()
		};
		
		// CSRF token
        var csrfHeaderName = "${_csrf.headerName}";
        var csrfTokenValue = "${_csrf.token}";

        if ($('input[name="name"]').val().trim()) {
        	
			$.ajax({
				type: 'POST',
				url: '/dd/admin/test/mbti/name',
				headers: {
					'content-Type': 'application/json'
				},
				data: JSON.stringify(obj),
				dataType: 'json',
				success: function(result) {
					//alert(result);
					if (result == 0) {
						$('.check-name-duplication').text('사용 가능한 MBTI명입니다.');
						$('.check-name-duplication').css('color', '#212529');
						$('.check-name-duplication').data('type', 'y');
						console.log("check-name-duplication: " + $('.check-name-duplication').data('type'));
						
					} else {
						$('.check-name-duplication').text('중복된 MBTI명입니다. 다른 MBTI명을 입력해주세요.');
						$('.check-name-duplication').css('color', '#dc3545');
						$('.check-name-duplication').data('type', 'n');
						console.log("check-name-duplication: " + $('.check-name-duplication').data('type'));
					}
				},
				beforeSend: function(xhr) {
	            	xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
	            },
				error: (a,b,c) => {
					console.log(a,b,c);
				}
				
			});
        } else {
        	$('.check-name-duplication').text('');
        	$('.check-name-duplication').removeAttr("data-type");
        	console.log("check-name-duplication: " + $('.check-name-duplication').data('type'));
        }
	});
</script>
