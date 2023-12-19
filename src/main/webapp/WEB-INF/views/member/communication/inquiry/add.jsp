<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>
  
<style>
    .stats-counter {
    	background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("/dd/resources/files/communication/inquiry.jpg") center center;
    	background-size: cover;
    	background-attachment: fixed;
    }
    #pagetitle {
		margin-top: 70px;
    }
    #title {
		margin-bottom: 20px;
    }
	#add-form {
		width: 70%;
		padding: 35px 40px 40px;
		border-collapse: separate;
		border-radius: 10px;
		margin: 50px auto 0;
		box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
	}
	#add-form th, #add-form td {
		height: 70px;
		color: #555;
	}
	#add-form th {
		width: 20%;
		font-size: 1.1rem;
	}
	#add-form .required::after {
		content: " *";
		color: #F00;
	}
	#add-form select, #add-form input, #add-form textarea {
		border : 1px solid #CCC;
	}
	#add-form tr:nth-child(7) td > div {
		height: 170px;
		padding: 20px 30px;
		overflow-y: scroll;
		overflow: auto;
	}
	#add-form tr:nth-child(7) td > div > div {
		text-align: right;
	}
	#add-form tr:nth-child(7) td > div > div > label {
		font-weight: bold;
		margin: 10px 10px 0;
	}
	#agree {
		margin-top: 14px;
	}
	.message {
		font-size: 14px;
		color: #F00;
		padding: 10px 5px;
	}
	#button {
		text-align: center;
	}
	#button button {
		background-color: #CE1212;
		border-color: #CE1212;
		margin: 70px 5px;
	}
	#button i {
		margin-right: 7px;
	}
</style>

<!-- ======= Title Section ======= -->

<section id="stats-counter" class="stats-counter" style="height: 468.4px">
	<div id="pagetitle" class="container" data-aos="zoom-out" style="margin-top: 115px;">
		<div class="gy-4" style="justify-content: center; width: 100%;">
			<div class="col-lg-3 col-md-6" style="width: 100%;">
				<div class="stats-item text-center w-100 h-100">
					<div id="title" style="padding: 0 !important; font-size: 48px; font-weight: 700; color: #fff; margin: 0">이용문의</div>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- ======= Main Section ======= -->
    
<main id="inquiry">
	<form method="POST" action="/dd/member/communication/inquiry/addok.do" enctype="multipart/form-data">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		
		<table id="add-form">
			<tr>
				<th class="required">문의유형</th>
				<td>
					<select name="type" class="form-select">
					    <option value="요금/혜택">요금/혜택</option>
					    <option value="어트랙션">어트랙션</option>
					    <option value="페스티벌">페스티벌</option>
					    <option value="영화">영화</option>
					    <option value="추천 기능">추천 기능</option>
					    <option value="기프트샵">기프트샵</option>
					    <option value="예매">예매</option>
					    <option value="기타">기타</option>
					 </select>
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" class="form-control" value="${name}" disabled></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" class="form-control" value="${email}" disabled></td>
			</tr>
			<tr>
				<th class="required">제목</th>
				<td>
					<input type="text" name="subject" class="form-control" required oninvalid="this.setCustomValidity('제목을 입력해주세요.')">
					<span id="subject-message" class="message" style="display: none;"></span>
				</td>
			</tr>
			<tr>
				<th class="required">내용</th>
				<td>
					<textarea name="content" class="form-control" rows="15" required oninvalid="this.setCustomValidity('내용을 입력해주세요.')"></textarea>
					<span id="content-message" class="message" style="display: none;"></span>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><input type="file" name="doc" class="form-control"></td>
			</tr>
			<tr>
				<th class="required">개인정보수집동의</th>
				<td>
					<div style="border-radius: 0.25rem; border: 1px solid #ced4da;">
                        본인은 DD-Land 홈페이지 이용을 위하여 다음과 같이 회사가 본인의 개인정보를 수집∙이용하는 것에 동의합니다.<br>
                        1. 개인정보 수집항목<br>
                        작성자 개인, 단체 정보 (이름, 단체명, 이메일주소, 핸드폰번호) 및 서비스 이용기록, 접속로그, 쿠키 등<br>
                        2. 개인정보 이용목적<br>
                        DD-Land 온라인 상담, 서비스 신청, 상품예약, 제휴문의, 인재채용 등 관련 소식 안내, DD-Land 홈페이지 이용 관련 통계 및 분석 등<br>
                        3. 개인정보 보유기간<br>
                        홈페이지 이용일로부터 1년<br>
                        DD-Land 온라인 상담, 서비스 신청, 상품예약, 제휴문의, 인재채용 등 운영을 위하여 필요한 최소한의 개인정보이므로 동의해 주셔야 홈페이지를 이용하실 수 있습니다. 또한 위 동의를 철회할 경우, 철회 이후 홈페이지 이용 과정에 불이익이 발생할 수 있습니다.
                        
                        <div><input type="checkbox" id="agree" class="form-check-input"><label for="agree">동의함</label></div>
					</div>
				</td>
			</tr>
		</table>
		
		<div id="button">
			<button type="submit" id="add-button" class="btn btn-primary"><i class="fa-solid fa-plus"></i>등록</button>
			<button type="button" id="back-button" class="btn btn-primary"><i class="fa-solid fa-circle-arrow-left"></i>취소</button>
		</div>
	</form>
</main>

<script>
	$('input[name="subject"]').blur(function () {
	    if (!$('input[name="subject"]').val().trim()) {
	    	$('#subject-message').css('display', 'block');
	    	$('#subject-message').text('제목을 입력하세요.');
	    }
	});
	
	$('input[name="subject"]').keydown(function () {
	    $('#subject-message').css('display', 'none');
	});
	
	$('textarea[name="content"]').blur(function () {
	    if (!$('textarea[name="content"]').val().trim()) {
	    	$('#content-message').css('display', 'block');
	    	$('#content-message').text('내용을 입력하세요.');
	    }
	});
	
	$('textarea[name="content"]').keydown(function () {
	    $('#content-message').css('display', 'none');
	});

	$('#add-button').click(function () {
		if (!$('input[name="subject"]').val().trim()) {
	    	$('#subject-message').css('display', 'block');
	    	$('#subject-message').text('제목을 입력하세요.');
	    }
		
		if (!$('textarea[name="content"]').val().trim()) {
	    	$('#content-message').css('display', 'block');
	    	$('#content-message').text('내용을 입력하세요.');
	    }
		
		if ($('input[name="subject"]').val().trim() && $('textarea[name="content"]').val().trim()) {
			if (!$('#agree').prop('checked')) {
				alert('개인정보수집 동의가 필요합니다.');
				$('#agree').focus();
				return false;
			}
			return true;
		}
	});
	
	$('#back-button').click(function () {
		location.href='/dd/index.do';
	});
</script>