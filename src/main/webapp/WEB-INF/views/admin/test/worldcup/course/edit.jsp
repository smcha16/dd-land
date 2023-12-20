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

.attached {
	padding: 10px 0;
	display: inline-block;
}

.attached>span {
	cursor: pointer;
	display: inline-block;
	vertical-align: middle;
	font-weight: bold;
	color: #777;
}
</style>

<!-- Main Start -->
<main id="main" class="main">

	<div class="pagetitle">
		<h1>코스 수정</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">수정</a></li>
          		<li class="breadcrumb-item active"><a href="/dd/admin/test/worldcup/course/list.do">취소</a></li>
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
						<form method="POST" action="/dd/admin/test/worldcup/course/editok.do" enctype="multipart/form-data">

							<!-- 코스명 -->
							<div class="row mb-3">
								<label for="name" class="col-sm-2 col-form-label required">코스명</label>
								
								<div class="col-sm-10">
									<input type="text" id="name" name="name" class="form-control" placeholder="코스명을 입력해주세요." value="${dto.name}" required>
									<div style="height: 30px;">
										<div class="check-name-duplication"></div>
									</div>
								</div>
							</div>
              				
              				<!-- 이미지 -->
							<div class="row mb-3">
								<label for="formFile" class="col-sm-2 col-form-label">이미지</label>
								
								<div class="col-sm-10">
                					<c:if test="${dto.img == 'course.png'}">
                  						<input class="form-control" type="file" id="formFile" name="image">
                  					</c:if>
                					<c:if test="${dto.img != 'course.png'}">
                  						<input class="form-control" type="file" id="formFile" name="image" disabled>
	                  					<div style="height: 30px;">
	                  						<div class="attached">
	                  							<i class="bi bi-image"></i>
	                  							<c:out value="${dto.img}" />
	                  							<span onclick="delAttached()">&times;</span>
	                  						</div>
	                  					</div>
                  					</c:if>
                				</div>
							</div>
							
							<!-- 코스 seq -->
							<input type="hidden" name="course_seq" value="${dto.course_seq}">
							
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
	$('.check-name-duplication').data('type', 'y');

	function submit() {
		if (!$('input[name="name"]').val().trim() || $('.check-name-duplication').data('type') != 'y') {
			
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
	
	// 기존 첨부한 첨부파일 삭제 시 지우고 input[type="file"] 태그 활성화
	function delAttached() {
		$(event.target).parent().remove();
		$('input[type="file"]').prop('disabled', false);
	}
	
	// 비활성화된 input 태그 도움말
	$('input[type="file"]:disabled').hover(function() {
		
		$('input[type="file"]:disabled').attr('title', '신규 이미지를 등록하시려면 기존에 첨부한 첨부파일을 삭제해주세요.');
		
	});
	
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
<!-- Main End -->
