<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<title>Title</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="/dd/resources/main/img/favicon.png" rel="icon">
<link href="/dd/resources/main/img/apple-touch-icon.png"
	rel="apple-touch-icon">

<!-- Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Amatic+SC:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Inter:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="/dd/resources/main/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="/dd/resources/main/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="/dd/resources/main/vendor/aos/aos.css" rel="stylesheet">
<link href="/dd/resources/main/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link href="/dd/resources/main/vendor/swiper/swiper-bundle.min.css"
	rel="stylesheet">

<!-- Template Main CSS File -->
<link href="/dd/resources/main/css/main.css" rel="stylesheet">

<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="/dd/resources/admin/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="/dd/resources/admin/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="/dd/resources/admin/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="/dd/resources/admin/vendor/quill/quill.snow.css"
	rel="stylesheet">
<link href="/dd/resources/admin/vendor/quill/quill.bubble.css"
	rel="stylesheet">
<link href="/dd/resources/admin/vendor/remixicon/remixicon.css"
	rel="stylesheet">
<link href="/dd/resources/admin/vendor/simple-datatables/style.css"
	rel="stylesheet">

<!-- Template Main CSS File -->
<link href="/dd/resources/admin/css/style.css" rel="stylesheet">

<!-- 마이페이지 -->
<!-- Favicons -->
<link href="/dd/resources/mypage/img/favicon.png" rel="icon">
<link href="/dd/resources/mypage/img/apple-touch-icon.png"
	rel="apple-touch-icon">

<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="/dd/resources/mypage/vendor/aos/aos.css" rel="stylesheet">
<link href="/dd/resources/mypage/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/dd/resources/mypage/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="/dd/resources/mypage/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="/dd/resources/mypage/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link href="/dd/resources/mypage/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="/dd/resources/mypage/css/style.css" rel="stylesheet">

<style>
#header {
	height: 90px !important;
	padding-left: 0;
}

.nav-heading {
	margin: 10px 0 15px 0 !important;
}

.nav-heading, h1 {
	margin-top: 45px !important;
}

h1 {
	font-size: 2rem !important;
	margin-left: 10px;
}

.container h1 {
	font-size: 28px !important;
	margin-top: 0 !important;
}

.container h1 p {
	font-size: 28px;
}

.logo span {
	font-size: 30px;
}

.d-md-block {
	margin-right: 15px;
}

.pagetitle {
	margin-top: 10px;
}

.col-12 {
	margin-top: 15px;
}

.col-lg-8 {
	width: 100%;
}

.card-body {
	min-height: 600px;
}

div.header {
	height: 60px;
	border-radius: 5px;
}

#search {
	margin-bottom: 15px;
	padding: 7px;
}

.search-form {
	width: 100%;
	margin: 0;
}

.header .search-form input {
	border: 0;
	height: 50px;
}

.header .search-form input:focus, .header .search-form input:hover {
	outline: none;
	border: none;
	box-shadow: none;
	transition: none;
}

.card-body .header {
	display: flex;
	align-items: center;
	justify-content: space-between;
}
/* .buttons button {
      margin-left: 7px;
    }
    .buttons button:last-child {
      margin-right: 20px;
    } */
.breadcrumb {
	margin-right: 15px;
	margin-top: 30px;
	margin-bottom: 10px;
}

.breadcrumb a {
	color: #012970;
}

.table {
	text-align: center;
}

th {
	background-color: #f2f2f2 !important;
}

.pagination {
	justify-content: center;
	margin-top: 30px;
}

.logo {
	width: 68px;
}
</style>
</head>

<body>
	<tiles:insertAttribute name="header" />

	<tiles:insertAttribute name="admin_sidebar" />

	<tiles:insertAttribute name="mypage_content" />

	<tiles:insertAttribute name="footer" />

	<!-- Vendor JS Files -->
	<script src="/dd/resources/admin/vendor/apexcharts/apexcharts.min.js"></script>
	<script
		src="/dd/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="/dd/resources/admin/vendor/chart.js/chart.umd.js"></script>
	<script src="/dd/resources/admin/vendor/echarts/echarts.min.js"></script>
	<script src="/dd/resources/admin/vendor/quill/quill.min.js"></script>
	<script
		src="/dd/resources/admin/vendor/simple-datatables/simple-datatables.js"></script>
	<script src="/dd/resources/admin/vendor/tinymce/tinymce.min.js"></script>
	<script src="/dd/resources/admin/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="/dd/resources/admin/js/main.js"></script>

	<!-- Vendor JS Files -->
	<script
		src="/dd/resources/main/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="/dd/resources/main/vendor/aos/aos.js"></script>
	<script src="/dd/resources/main/vendor/glightbox/js/glightbox.min.js"></script>
	<script
		src="/dd/resources/main/vendor/purecounter/purecounter_vanilla.js"></script>
	<script src="/dd/resources/main/vendor/swiper/swiper-bundle.min.js"></script>
	<script src="/dd/resources/main/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="/dd/resources/main/js/main.js"></script>



	<!-- 마이페이지 -->


	<!-- Vendor JS Files -->
	<script src="/dd/resources/mypage/vendor/purecounter/purecounter_vanilla.js"></script>
	<script src="/dd/resources/mypage/vendor/aos/aos.js"></script>
	<script src="/dd/resources/mypage/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="/dd/resources/mypage/vendor/glightbox/js/glightbox.min.js"></script>
	<script src="/dd/resources/mypage/vendor/isotope-layout/isotope.pkgd.min.js"></script>
	<script src="/dd/resources/mypage/vendor/swiper/swiper-bundle.min.js"></script>
	<script src="/dd/resources/mypage/vendor/typed.js/typed.umd.js"></script>
	<script src="/dd/resources/mypage/vendor/waypoints/noframework.waypoints.js"></script>
	<script src="/dd/resources/mypage/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="/dd/resources/mypage/js/main.js"></script>

</body>

</html>