<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- list (1) Template -->
<!-- user > test > worldcup > view.jsp -->
<style>
#title+p {
	text-shadow: 0 2px 10px rgba(255, 255, 255, 0.8);
	padding: 5px 20px;
	color: #222222;
	font-size: 17px;
	background-color: rgba(255, 255, 255, 0.6);
	display: inline-block;
	border-radius: 50px;
}

#pagetitle {
	margin-top: 70px;
}

#title {
	margin-bottom: 20px;
}

.munti-content-container {
	display: flex;
	flex-wrap: wrap;
	margin: 30px 50px 0 50px;
	padding: 0 !important;
}

.item {
	position: relative;
	width: 25.5%;
	aspect-ratio: 0.75;
	padding: 0;
	box-sizing: border-box;
	min-width: 270px;
	border: 1px solid #E1E1E1;
	margin: 10px 45px 50px 45px;
	border-radius: 10px;
	transition: all 0.3s;
}

.item:hover {
	cursor: pointer;
	box-shadow: 12px 12px 17px rgba(0, 0, 0, 0.20);
}

.item>div:nth-child(1) {
	height: 70%;
	background-color: transparent;
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
	border-radius: 10px 10px 0 0;
}

.item>div:nth-child(2) {
	height: 30%;
	display: flex;
	flex-direction: column;
	padding: 20px;
	font-size: 1.3rem;
	font-weight: bold;
	background: transparent;
	border-radius: 0 0 10px 10px;
}

.hidden-div {
	display: none;
	color: white;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 70%;
	padding: 20px;
	background-color: black;
	opacity: 0.65; /* 투명도 조절 */
	border-radius: 10px 10px 0 0;
	z-index: 1; /* 다른 요소들보다 위에 위치하도록 설정 */
}
</style>

<!-- 자동 로그인 버튼 -->
<style>
	.form-buttons {
	    display: flex !important;
	    justify-content: space-around;
	    align-items: center;
	    margin-top: 20px;
	}
	
	.login.button {
	    display: inline-block;
	    padding: 10px 20px;
	    font-size: 16px;
	    font-weight: bold;
	    text-align: center;
	    text-decoration: none;
	    cursor: pointer;
	    border: none;
	    border-radius: 5px;
	    transition: background-color 0.3s, color 0.3s;
	}
	
	.login.button:hover {
	    background-color: #222;
	    color: #fff;
	}
	
	.login.button + .login.button {
	    margin-left: 10px;
	}
	
	.login.button.admin {
	    background-color: #ff6347;
	    color: #fff;
	}
	
	.login.button.user {
	    background-color: #4caf50;
	    color: #fff;
	}
</style>

<!-- ======= Stats Counter Section ======= -->
<section id="stats-counter" class="stats-counter">
	<div id="pagetitle" class="container" data-aos="zoom-out">
		<div class="gy-4" style="justify-content: center; width: 100%;">

			<div class="col-lg-3 col-md-6" style="width: 100%;">
				<div class="stats-item text-center w-100 h-100">
					<div id="title"
						style="font-size: 48px; display: block; color: #fff; font-weight: 700;">로그인</div>
					<p>설명(나에게 딱 맞는 어트랙션을 찾아보세요!)</p>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- End Stats Counter Section -->

<form method="POST" action="/dd/login">
	<div>
		<input type="text" name="username" placeholder="ID" required>
	</div>
	<div>
		<input type="password" name="password" placeholder="Password" required>
	</div>
	<div>
		<button class="in">로그인</button>
	</div>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>

<div class="form-buttons">
    <form method="POST" action="/dd/login">
        <input type="hidden" name="username" value="admin@naver.com">
        <input type="hidden" name="password" value="admin1111!">
        <button type="submit" class="login button admin">관리자</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>

    <form method="POST" action="/dd/login">
        <input type="hidden" name="username" value="park@naver.com">
        <input type="hidden" name="password" value="park1111!">
        <button type="submit" class="login button user">박나래</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
    
    <form method="POST" action="/dd/login">
        <input type="hidden" name="username" value="hwnag@kakao.com">
        <input type="hidden" name="password" value="hwang1111!">
        <button type="submit" class="login button user">황주원</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
    
    <form method="POST" action="/dd/login">
        <input type="hidden" name="username" value="cha@daum.net">
        <input type="hidden" name="password" value="cha1111!">
        <button type="submit" class="login button user">차민재</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
    
    <form method="POST" action="/dd/login">
        <input type="hidden" name="username" value="lee@kakao.com">
        <input type="hidden" name="password" value="lee1111!">
        <button type="submit" class="login button user">이정은</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
    
    <form method="POST" action="/dd/login">
        <input type="hidden" name="username" value="cha@msn.com">
        <input type="hidden" name="password" value="cha1111!">
        <button type="submit" class="login button user">차수민</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
    
    <form method="POST" action="/dd/login">
        <input type="hidden" name="username" value="lee@naver.com">
        <input type="hidden" name="password" value="lee1111!">
        <button type="submit" class="login button user">이승원</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
    
    <form method="POST" action="/dd/login">
        <input type="hidden" name="username" value="kim@kakao.com">
        <input type="hidden" name="password" value="kim1111!">
        <button type="submit" class="login button user">김형우도도리</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</div>

<script>
	var itemElements = document.querySelectorAll('.item');
	itemElements.forEach(function(item) {
		item.addEventListener('mouseover', function() {
			// 마우스 오버 시 hidden-div를 보이게 변경
			item.querySelector('.hidden-div').style.display = 'block';
		});

		item.addEventListener('mouseout', function() {
			// 마우스 아웃 시 hidden-div를 다시 숨김
			item.querySelector('.hidden-div').style.display = 'none';
		});
	});
</script>