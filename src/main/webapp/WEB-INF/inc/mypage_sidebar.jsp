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
         			<a href="/dd/member/mypage/ticket/view.do">
           				<i class="bi bi-circle"></i><span>예매내역</span>
         			</a>
         			<a href="/dd/member/mypage/ticket/pview.do">
           				<i class="bi bi-circle"></i><span>이전 예매내역</span>
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
         			<a href="/dd/member/mypage/attraction/pview.do">
           				<i class="bi bi-circle"></i><span>이전 어트랙션 예약내역</span>
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
         			<a href="/dd/member/mypage/buy/view.do">
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
         			<a href="/dd/member/mypage/inquiry/inquiry/view.do">
           				<i class="bi bi-circle"></i><span>이용문의</span>
         			</a>
       			</li>
       			<li>
         			<a href="/dd/member/mypage/inquiry/voc/view.do">
           				<i class="bi bi-circle"></i><span>칭찬/불편/건의</span>
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
         			<a href="/dd/member/mypage/review/view.do">
           				<i class="bi bi-circle"></i><span>리뷰</span>
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
         			<a href="/dd/member/mypage/modify/view.do">
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
         			<a href="/dd/member/mypage/unregister/view.do">
           				<i class="bi bi-circle"></i><span>회원탈퇴</span>
         			</a>
       			</li>
     		</ul>
   		</li>

	</ul>

</aside>

<script>
// 페이지가 로드된 후에 실행되도록 코드 수정
document.addEventListener("DOMContentLoaded", function () {
    var currentUrl = window.location.href;

    // 주어진 URL을 기반으로 해당 메뉴 아이템을 찾아 활성화합니다.
    var menuItem = document.querySelector('a[href="' + currentUrl + '"]');
    
    if (menuItem) {
        // 해당 메뉴 아이템의 부모 요소인 nav-item을 찾아 활성 클래스를 추가합니다.
        menuItem.closest(".nav-item").classList.add("show");
        
        // 부모 요소인 nav-item의 collapse를 활성화하여 하위 메뉴가 열리도록 합니다.
        var collapseElement = menuItem.closest(".collapse");
        collapseElement.classList.add("show");

        // 부모 요소인 collapse의 모든 조상 요소에 대해 show 클래스를 추가합니다.
        var parentCollapses = [];
        var parentCollapse = collapseElement.closest(".collapse");
        while (parentCollapse) {
            parentCollapses.push(parentCollapse);
            parentCollapse = parentCollapse.closest(".collapse");
        }
        parentCollapses.forEach(function (element) {
            element.classList.add("show");
        });
    }
});
</script>

