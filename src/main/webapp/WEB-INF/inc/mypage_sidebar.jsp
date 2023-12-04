<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	.nav-heading {
		margin: 45px 0 15px 0 !important;
    }
</style>

<!-- ======= Sidebar ======= -->
<aside id="sidebar" class="sidebar">

 	<ul class="sidebar-nav" id="sidebar-nav">

   		<li class="nav-heading">menu</li>
   		
   		<!-- 예매내역 -->
   		<li class="nav-item">
     		<a class="nav-link collapsed" data-bs-target="#ticket-nav" data-bs-toggle="collapse" href="#">
       			<i class="bi bi-grid"></i><span>예매내역</span><i class="bi bi-chevron-down ms-auto"></i>
     		</a>
    		<ul id="ticket-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
       			<li>
         			<a href="admin.do">
           				<i class="bi bi-circle"></i><span>예매내역</span>
         			</a>
       			</li>
     		</ul>
   		</li>

   		<!-- 어트랙션 예약내역 -->
   		<li class="nav-item">
     		<a class="nav-link collapsed" data-bs-target="#attraction-nav" data-bs-toggle="collapse" href="#">
       			<i class="bi bi-grid"></i><span>어트랙션 예약내역</span><i class="bi bi-chevron-down ms-auto"></i>
     		</a>
     		<ul id="attraction-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
       			<li>
         			<a href="/dd/member/mypage/attraction/view.do">
           				<i class="bi bi-circle"></i><span>어트랙션 예약내역</span>
         			</a>
       			</li>
     		</ul>
   		</li>
   		
   		<!-- 리뷰 -->
   		<li class="nav-item">
     		<a class="nav-link collapsed" data-bs-target="#review-nav" data-bs-toggle="collapse" href="#">
       			<i class="bi bi-grid"></i><span>리뷰</span><i class="bi bi-chevron-down ms-auto"></i>
     		</a>
     		<ul id="review-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
       			<li>
         			<a href="admin.do">
           				<i class="bi bi-circle"></i><span>리뷰</span>
         			</a>
       			</li>
     		</ul>
   		</li>

   		<!-- 구매내역 -->
   		<li class="nav-item">
     		<a class="nav-link collapsed" data-bs-target="#buy-nav" data-bs-toggle="collapse" href="#">
       			<i class="bi bi-grid"></i><span>구매내역</span><i class="bi bi-chevron-down ms-auto"></i>
     		</a>
     		<ul id="buy-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
       			<li>
         			<a href="admin.do">
           				<i class="bi bi-circle"></i><span>구매내역</span>
         			</a>
       			</li>
     		</ul>
   		</li>

   		<!-- 문의내역 -->
   		<li class="nav-item">
     		<a class="nav-link collapsed" data-bs-target="#inquiry-nav" data-bs-toggle="collapse" href="#">
       			<i class="bi bi-grid"></i><span>문의내역</span><i class="bi bi-chevron-down ms-auto"></i>
     		</a>
     		<ul id="inquiry-nav" class="nav-content collapse" data-bs-parent="#sidebar-nav">
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

   		<!-- 회원정보수정 -->
   		<li class="nav-item">
     		<a class="nav-link collapsed" data-bs-target="#modify-nav" data-bs-toggle="collapse" href="#">
       			<i class="bi bi-grid"></i><span>회원정보수정</span><i class="bi bi-chevron-down ms-auto"></i>
     		</a>
     		<ul id="modify-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
       			<li>
         			<a href="admin.do">
           				<i class="bi bi-circle"></i><span>회원정보수정</span>
         			</a>
       			</li>
     		</ul>
   		</li>

   		<!-- 회원탈퇴 -->
   		<li class="nav-item">
      		<a class="nav-link collapsed" data-bs-target="#unregister-nav" data-bs-toggle="collapse" href="#">
        		<i class="bi bi-grid"></i><span>회원탈퇴</span><i class="bi bi-chevron-down ms-auto"></i>
      		</a>
      		<ul id="unregister-nav" class="nav-content collapse " data-bs-parent="#sidebar-nav">
       			<li>
         			<a href="admin.do">
           				<i class="bi bi-circle"></i><span>회원탈퇴</span>
         			</a>
       			</li>
     		</ul>
   		</li>

	</ul>

</aside>