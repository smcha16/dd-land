<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
.required::after {
	content: ' *';
	color: #b71c1c;
}

/* placeholder CSS */
#name {
	
}

/* 유효성검사 CSS */
.check-location-duplication {
	padding: 10px 10px;
}

.check-name-duplication {
	padding: 10px 10px;
}
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>코스 등록</h1>
		
		<nav class="d-flex justify-content-end">
			<ol class="breadcrumb">
				<li class="breadcrumb-item">
					<a href="javascript:void(0);" onclick="submit();">등록</a>
				</li>
				<li class="breadcrumb-item active">
					<a href="/dd/admin/test/worldcup/course/view.do">취소</a>
				</li>
			</ol>
		</nav>
	</div>

	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">코스 정보 입력</h5>

						<!-- 코스명, 이미지 -->
						<form method="POST" action="/dd/admin/test/worldcup/course/addok.do" enctype="multipart/form-data">

							<!-- 코스명 -->
							<div class="row mb-3">
								<label for="name" class="col-sm-2 col-form-label required">코스명</label>
								
								<div class="col-sm-10">
									<input type="text" id="name" name="name" class="form-control" placeholder="코스명을 입력해주세요." required>
									<div style="height: 30px;">
										<div class="check-name-duplication"></div>
									</div>
								</div>
							</div>
							
							<!-- 이미지 -->
							<div class="row mb-3">
								<label for="formFile" class="col-sm-2 col-form-label">이미지</label>
								
								<div class="col-sm-10">
									<input class="form-control" type="file" id="formFile" name="img">
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
	//console.log("check-name-duplication: " + $('.check-name-duplication').data('type'));

	function submit() {
		// console.log($('textarea[name="info"]').val());

		if (!$('input[name="name"]').val().trim() || $('.check-name-duplication').data('type') != 'y') {
			
			if ($('.check-name-duplication').data('type') == 'n') {
				alert('중복된 항목을 수정해주세요.');
			} else {
				alert('필수 항목을 입력해주세요.');
			}
			
		} else {
            // 전송 데이터 확인
            /*
            console.log("전송 데이터:");
            console.log("Name: " + $('input[name="name"]').val());
            console.log("Image Files: " + $('#formFile')[0].files);
            var files = $('#formFile')[0].files;
            for (var i = 0; i < files.length; i++) {
                console.log("Image File Name: " + files[i].name);
            }
            */
            
            // 데이터 전송
            $('form').submit();
        }
	}
	
	// course 이름 중복 검사
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
				url: '/dd/admin/test/worldcup/course/name',
				headers: {
					'content-Type': 'application/json'
				},
				data: JSON.stringify(obj),
				dataType: 'json',
				success: function(result) {
					//alert(result);
					if (result == 0) {
						$('.check-name-duplication').text('사용 가능한 코스명입니다.');
						$('.check-name-duplication').css('color', '#212529');
						$('.check-name-duplication').data('type', 'y');
						console.log("check-name-duplication: " + $('.check-name-duplication').data('type'));
						
					} else {
						$('.check-name-duplication').text('중복된 코스명입니다. 다른 코스명을 입력해주세요.');
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