<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<style>
		.nav-heading {
			margin-top: 45px !important;
	    }
	</style>

	<!-- ======= Sidebar ======= -->
	<aside id="sidebar" class="sidebar">

  		<ul class="sidebar-nav" id="sidebar-nav">

    		<li class="nav-heading">menu</li>

    		<!-- 회원 -->
    		<li class="nav-item">
      			<a class="nav-link collapsed" data-bs-target="#member-nav" data-bs-toggle="collapse" href="#">
        			<i class="bi bi-grid"></i><span>회원</span><i class="bi bi-chevron-down ms-auto"></i>
      			</a>
      			<ul id="member-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
        			<li>
          				<a href="admin/activity/attraction/view.do">
            				<i class="bi bi-circle"></i><span>회원</span>
          				</a>
        			</li>
      			</ul>
    		</li>

    		<!-- 요금/혜택 -->
    		<li class="nav-item">
      			<a class="nav-link collapsed" data-bs-target="#pb-nav" data-bs-toggle="collapse" href="#">
        			<i class="bi bi-grid"></i><span>요금/혜택</span><i class="bi bi-chevron-down ms-auto"></i>
      			</a>
      			<ul id="pb-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>요금</span>
          				</a>
        			</li>
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>혜택</span>
          				</a>
        			</li>
      			</ul>
    		</li>

    		<!-- 액티비티 -->
    		<li class="nav-item">
      			<a class="nav-link collapsed" data-bs-target="#activity-nav" data-bs-toggle="collapse" href="#">
        			<i class="bi bi-grid"></i><span>액티비티</span><i class="bi bi-chevron-down ms-auto"></i>
      			</a>
      			<ul id="activity-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>어트랙션</span>
          				</a>
        			</li>
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>페스티벌</span>
          				</a>
        			</li>
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>포토존</span>
          				</a>
        			</li>
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>영화</span>
          				</a>
        			</li>
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>영화관</span>
          				</a>
        			</li>
      			</ul>
    		</li>

    		<!-- 추천 -->
    		<li class="nav-item">
      			<a class="nav-link collapsed" data-bs-target="#test-nav" data-bs-toggle="collapse" href="#">
        			<i class="bi bi-grid"></i><span>추천</span><i class="bi bi-chevron-down ms-auto"></i>
      			</a>
      			<ul id="test-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>DD 월드컵</span>
          				</a>
        			</li>
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>MBTI 추천</span>
          				</a>
        			</li>
      			</ul>
    		</li>

    		<!-- 샵 -->
    		<li class="nav-item">
      			<a class="nav-link collapsed" data-bs-target="#shop-nav" data-bs-toggle="collapse" href="#">
        			<i class="bi bi-grid"></i><span>샵</span><i class="bi bi-chevron-down ms-auto"></i>
      			</a>
      			<ul id="shop-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>식당</span>
          				</a>
        			</li>
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>기프트샵</span>
          				</a>
        			</li>
      			</ul>
    		</li>

    		<!-- 소통 -->
    		<li class="nav-item">
      			<a class="nav-link collapsed" data-bs-target="#communication-nav" data-bs-toggle="collapse" href="#">
        			<i class="bi bi-grid"></i><span>소통</span><i class="bi bi-chevron-down ms-auto"></i>
      			</a>
     			<ul id="communication-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>공지사항</span>
          				</a>
        			</li>
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>FAQ</span>
          				</a>
        			</li>
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>분실물</span>
          				</a>
        			</li>
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>리뷰</span>
          				</a>
        			</li>
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>이용문의</span>
          				</a>
        			</li>
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>칭찬/불편/건의</span>
          				</a>
        			</li>
      			</ul>
    		</li>

    		<!-- 운휴 -->
    		<li class="nav-item">
	      		<a class="nav-link collapsed" data-bs-target="#close-nav" data-bs-toggle="collapse" href="#">
	        		<i class="bi bi-grid"></i><span>운휴</span><i class="bi bi-chevron-down ms-auto"></i>
	      		</a>
	      		<ul id="close-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>운휴</span>
          				</a>
        			</li>
      			</ul>
    		</li>

		    <!-- 카테고리 -->
		    <li class="nav-item">
      			<a class="nav-link collapsed" data-bs-target="#category-nav" data-bs-toggle="collapse" href="#">
        			<i class="bi bi-grid"></i><span>카테고리</span><i class="bi bi-chevron-down ms-auto"></i>
      			</a>
      			<ul id="category-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>카테고리</span>
          				</a>
        			</li>
      			</ul>
    		</li>
    
		    <!-- 해시태그 -->
		    <li class="nav-item">
      			<a class="nav-link collapsed" data-bs-target="#hashtag-nav" data-bs-toggle="collapse" href="#">
        			<i class="bi bi-grid"></i><span>해시태그</span><i class="bi bi-chevron-down ms-auto"></i>
      			</a>
      			<ul id="hashtag-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
        			<li>
          				<a href="admin.do">
            				<i class="bi bi-circle"></i><span>해시태그</span>
          				</a>
        			</li>
      			</ul>
			</li>

		</ul>

	</aside>