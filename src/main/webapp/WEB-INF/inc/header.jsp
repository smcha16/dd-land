<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style>
	#login, #mypage{
  		color: #fff; /* 글자 색상을 흰색으로 설정 */
  		background: var(--color-primary); /* 배경색을 프라이머리 컬러로 설정 */
  		border: 1px solid var(--color-primary); /* 테두리 색상을 흰색으로 설정 */
	}
	#join {
  		color: var(--color-primary); /* 글자 색상을 프라이머리 컬러로 설정 */
  		background: #fff; /* 배경색을 흰색으로 설정 */
  		border: 1px solid var(--color-primary); /* 테두리 색상을 프라이머리 컬러로 설정 */
	}
	
	span {
		cursor: pointer;
		color: black;
	}
	.header{
		box-shadow: 2px 2px 3px 2px #cccccc4a;
	}
</style>
  <!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">
    <div class="container d-flex align-items-center justify-content-between">

      <a href="/dd/index.do" class="logo d-flex align-items-center me-auto me-lg-0">
        <!-- Uncomment the line below if you also wish to use an image logo -->
        <!-- <img src="resources/img/logo.png" alt=""> -->
        <h1><span>.</span>D_D<span>.</span></h1>
      </a>

      <nav id="navbar" class="navbar">
        <ul>
          <li class="dropdown"><a><span>요금/혜택</span></a>
          	<ul>
              <li><a href="/dd/user/pb/price/view.do">요금</a></li>
              <li><a href="/dd/user/pb/benefit/view.do">혜택</a></li>
            </ul>
          </li>
          <li class="dropdown"><a><span>액티비티</span></a>
          	<ul>
              <li><a href="/dd/user/activity/attraction/view.do">어트랙션</a></li>
              <li><a href="/dd/user/activity/movie/view.do">영화</a></li>
              <li><a href="/dd/user/activity/festival/view.do">페스티벌</a></li>
              <li><a href="/dd/user/activity/photozone/view.do">포토존</a></li>
            </ul>
          </li>
          <li class="dropdown"><a><span>추천</span></a>
          	<ul>
              <li class="dropdown"><a href="#"><span>DD 월드컵</span><i class="bi bi-chevron-down dropdown-indicator"></i></a>
              	<ul>
              		<li><a href="/dd/user/test/worldcup/attraction/view.do">어트랙션 월드컵</a></li>
                  	<li><a href="/dd/user/test/worldcup/course/view.do">코스 월드컵</a></li>
              	</ul>
              </li>
              <li><a href="/dd/user/test/mbti/view.do">MBTI 추천</a></li>
            </ul>
          </li>
          <li class="dropdown"><a><span>샵</span></a>
          	<ul>
              <li><a href="/dd/user/shop/restaurant/view.do">식당</a></li>
              <li><a href="/dd/user/shop/gift-shop/view.do">기프트샵</a></li>
            </ul>
          </li>
          <li class="dropdown"><a><span>이용가이드</span></a>
          	<ul>
              <li><a href="/dd/user/guide/use-guide/view.do">파크 이용안내</a></li>
              <li><a href="/dd/user/guide/convenient/view.do">편의시설</a></li>
              <li><a href="/dd/user/guide/location/view.do">오시는 길</a></li>
              <li><a href="/dd/user/guide/guide-map/view.do">가이드맵</a></li>
            </ul>
          </li>
          <li class="dropdown"><a><span>소통</span> </a>
            <ul>
              <li><a href="/dd/user/communication/notice/view.do">공지사항</a></li>
              <li class="dropdown"><a href="#"><span>고객소리함</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
                <ul>
                  <li><a href="/dd/member/communication/inquiry/add.do">이용문의</a></li>
                  <li><a href="/dd/member/communication/voc/add.do">칭찬/불편/건의</a></li>
                  <li><a href="/dd/user/communication/lost-property/view.do">분실물 찾기</a></li>
                </ul>
              </li>
              <li><a href="/dd/user/communication/faq/view.do">FAQ</a></li>
              <li><a href="/dd/user/communication/review/view.do">리뷰</a></li>
            </ul>
          </li>
          <li class="dropdown"><a><span>예매</span></a>
          	<ul>
              <li><a href="/dd/member/ticket/personal-reservation/view.do">개인 예매</a></li>
              <li><a href="/dd/member/ticket/group-reservation/view.do">단체 예매</a></li>
            </ul>
          </li>
          	<li class="dropdown"><a href="/dd/user/chat/view.do"><span>OpenChat</span></a></li>
        </ul>
      </nav><!-- .navbar -->
	  <div>
	  	<sec:authorize access="isAnonymous()">
      	<a class="btn-book-a-table" id="login" href="/dd/member/mypage/view.do">LogIn</a>
      	<a class="btn-book-a-table" id="join" href="/dd/user/register/view.do">Join</a>
      	</sec:authorize>
      	<sec:authorize access="isAuthenticated()">
      	<form method="post" action="/dd/logout.do">
      		<a class="btn-book-a-table" id="mypage" href="/dd/user/login/view.do">MyPage</a>
      		<button class="btn-book-a-table" type="submit">LogOut</button>
      		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
      	</form>
      	</sec:authorize>
      </div>
      <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
      <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

    </div>
  </header><!-- End Header -->
  
	
	
  
  
  
  