<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Title</title>
		<meta content="" name="description">
		<meta content="" name="keywords">
		
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  
		<!-- Favicons -->
		<link href="/dd/resources/main/img/favicon.png" rel="icon">
		<link href="/dd/resources/main/img/apple-touch-icon.png" rel="apple-touch-icon">

		<!-- Google Fonts -->
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Amatic+SC:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Inter:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet">
		<link href="https://fonts.gstatic.com" rel="preconnect">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

		<!-- Template Main CSS File -->
		<link href="/dd/resources/main/css/main.css" rel="stylesheet">

		<!-- Vendor CSS Files -->
		<link href="/dd/resources/admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="/dd/resources/admin/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
		<link href="/dd/resources/admin/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
		<link href="/dd/resources/admin/vendor/quill/quill.snow.css" rel="stylesheet">
		<link href="/dd/resources/admin/vendor/quill/quill.bubble.css" rel="stylesheet">
		<link href="/dd/resources/admin/vendor/remixicon/remixicon.css" rel="stylesheet">
		<link href="/dd/resources/admin/vendor/simple-datatables/style.css" rel="stylesheet">

		<!-- Template Main CSS File -->
		<link href="/dd/resources/admin/css/style.css" rel="stylesheet">

		<style>
		    #header {
				height: 90px !important;
				padding-left: 0;
		    }
			.logo {
				width: 68px;
			}
			body {
				display: flex;
				flex-direction: column;
				min-height: 100vh;
				overflow-y: scroll;
			}
			main {
				flex: 1;
				background: url('/dd/resources/admin/img/놀이공원.jpg') center/cover no-repeat;
				padding: 0 !important;
				margin-top: 90px !important;
				position: relative;
				color: #FFF;
			}
			main::before {
				content: "";
				position: absolute;
				top: 0;
				left: 0;
				width: 100%;
				height: 100%;
				background-color: #000;
				opacity: 0.6;
			}
			#reveal {
		        position: absolute;
		        top: 50%;
		        left: 50%;
		        transform: translate(-50%, -30%);
		    }
			#reveal:before {
				content: "";
				position: absolute;
				bottom: 0;
				top: 0;
				left: 0;
				right: 0;
				z-index: 1;
			}
			#reveal .reveal-container {
				position: relative;
				z-index: 2;
				min-width: 300px;
			}
			#reveal h1 {
				margin: 0 0 10px 0;
				font-size: 3rem;
				font-weight: 700;
				line-height: 56px;
				color: #fff;
			}
			#reveal p {
				color: #fff;
				margin-bottom: 50px;
				font-size: 26px;
				text-align: center;
				font-family: "Poppins", sans-serif;
			}
			#reveal p span {
				color: #fff;
				padding-bottom: 4px;
				letter-spacing: 1px;
			}
			@media (max-width : 768px) {
				#reveal h1 {
					font-size: 28px;
					line-height: 36px;
				}
				#reveal h2 {
					font-size: 18px;
					line-height: 24px;
					margin-bottom: 30px;
				}
			}
		</style>
	</head>
	<body>
		<tiles:insertAttribute name="header" />

		<tiles:insertAttribute name="mypage_sidebar" />
	
		<!-- Vendor JS Files -->
		<script src="/dd/resources/admin/vendor/apexcharts/apexcharts.min.js"></script>
		<script src="/dd/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		<script src="/dd/resources/admin/vendor/chart.js/chart.umd.js"></script>
		<script src="/dd/resources/admin/vendor/echarts/echarts.min.js"></script>
		<script src="/dd/resources/admin/vendor/quill/quill.min.js"></script>
		<script src="/dd/resources/admin/vendor/simple-datatables/simple-datatables.js"></script>
		<script src="/dd/resources/admin/vendor/tinymce/tinymce.min.js"></script>
		<script src="/dd/resources/admin/vendor/php-email-form/validate.js"></script>
		<script src="/dd/resources/admin/vendor/reveal/reveal.js"></script>
		
		<!-- Template Main JS File -->
		<script src="/dd/resources/admin/js/main.js"></script>
	
		<tiles:insertAttribute name="mypage_content" />

	</body>

</html>