<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style>
	#header {
		height: 90px !important;
    }
    #list {
		margin-left: -10px;
    }
    #logo {
		margin: 0 0 0 25px;
    }
    .header .logo h1 {
		font-size: 28px !important;
		font-weight: 700;
		color: #000;
		margin: 0;
		font-family: var(--font-secondary);
    }
	.header .logo span {
		color: #ce1212;
    }
    .profile-section {
    	font-size: 17px;
		margin-left: auto;
		margin-right: 30px;
    }
    #user {
    	width: 40px !important;
    	heigth: 40px !important;
    	margin-right: 10px;
    }
    .d-md-block {
    	margin-right: 0;
    }
    .logout {
   	    position: absolute;
	    width: 120px;
	    height: 50px;
	    left: 20px;
	    background-color: transparent;
	    border: 0;
    }
</style>

<!-- ======= Header ======= -->
<header id="header" class="header fixed-top d-flex align-items-center">

	<i id="list" class="bi bi-list toggle-sidebar-btn"></i> 

	<a id="logo" href="/dd/admin/index.do" class="logo d-flex align-items-center me-auto me-lg-0">
		<span>.</span><h1>D_D<span>.</span></h1>
    </a>

    <div class="profile-section">

		<a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
			<!-- <img id="user" src="/dd/resources/admin/img/profile-img.jpg" alt="Profile" class="rounded-circle"> -->
	        <span class="d-none d-md-block dropdown-toggle ps-2">관리자</span>
	    </a>
	
	    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
			
			<li class="dropdown-header">
				<sec:authorize access="isAuthenticated()">
		      	<form method="post" action="/dd/logout.do">
					<a class="dropdown-item d-flex align-items-center" href="index.do">
			            <button class="logout" type="submit"><i class="bi bi-box-arrow-right"></i>LogOut</button>
					</a>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				</form>
      			</sec:authorize>
			</li>
	
		</ul>

	</div>

</header>