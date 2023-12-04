<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	#login {
  		color: #fff; /* 글자 색상을 흰색으로 설정 */
  		background: var(--color-primary); /* 배경색을 프라이머리 컬러로 설정 */
  		border: 1px solid var(--color-primary); /* 테두리 색상을 흰색으로 설정 */
	}
	#join {
  		color: var(--color-primary); /* 글자 색상을 프라이머리 컬러로 설정 */
  		background: #fff; /* 배경색을 흰색으로 설정 */
  		border: 1px solid var(--color-primary); /* 테두리 색상을 프라이머리 컬러로 설정 */
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
          <li class="dropdown"><a href="#"><span>요금/혜택</span></a>
          	<ul>
              <li><a href="#">요금</a></li>
              <li><a href="#">혜택</a></li>
            </ul>
          </li>
          <li class="dropdown"><a href="#"><span>액티비티</span></a>
          	<ul>
              <li><a href="#">어트랙션</a></li>
              <li><a href="#">영화</a></li>
              <li><a href="#">페스티벌</a></li>
              <li><a href="#">포토존</a></li>
            </ul>
          </li>
          <li class="dropdown"><a href="#"><span>추천</span></a>
          	<ul>
              <li class="dropdown"><a href="#"><span>DD 월드컵 </span><i class="bi bi-chevron-down dropdown-indicator"></i></a>
              	<ul>
              		<li><a href="#">어트랙션 월드컵</a></li>
                  	<li><a href="#">코스 월드컵</a></li>
              	</ul>
              </li>
              <li><a href="#">MBTI 추천</a></li>
            </ul>
          </li>
          <li class="dropdown"><a href="#"><span>샵</span></a>
          	<ul>
              <li><a href="#">식당</a></li>
              <li><a href="#">기프트샵</a></li>
            </ul>
          </li>
          <li class="dropdown"><a href="#"><span>이용가이드</span></a>
          	<ul>
              <li><a href="/dd/user/guide/use-guide/view.do">파크 이용안내</a></li>
              <li><a href="#">편의시설</a></li>
              <li><a href="/dd/user/guide/location/view.do">오시는 길</a></li>
              <li><a href="/dd/user/guide/guide-map/view.do">가이드맵</a></li>
            </ul>
          </li>
          <li class="dropdown"><a href="#"><span>소통</span> </a>
            <ul>
              <li><a href="#">공지사항</a></li>
              <li class="dropdown"><a href="#"><span>고객소리함</span> <i class="bi bi-chevron-down dropdown-indicator"></i></a>
                <ul>
                  <li><a href="#">이용문의</a></li>
                  <li><a href="#">칭찬/불편/건의</a></li>
                  <li><a href="#">분실물 찾기</a></li>
                </ul>
              </li>
              <li><a href="#">FAQ</a></li>
              <li><a href="#">리뷰</a></li>
            </ul>
          </li>
          <li><a href="#">검색</a></li>
          <li class="dropdown"><a href="#">예매</a>
          	<ul>
              <li><a href="#">개인 예매</a></li>
              <li><a href="#">단체 예매</a></li>
            </ul>
          </li>
        </ul>
      </nav><!-- .navbar -->
	  <div>
      	<a class="btn-book-a-table" id="login" href="#">로그인</a>
      	<a class="btn-book-a-table" id="join" href="#">회원가입</a>
      </div>
      <i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
      <i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

    </div>
  </header><!-- End Header -->