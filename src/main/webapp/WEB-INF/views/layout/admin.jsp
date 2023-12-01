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
		
		<!-- Favicons -->
		<link href="resources/admin/img/favicon.png" rel="icon">
		<link href="resources/admin/img/apple-touch-icon.png" rel="apple-touch-icon">
		
		<!-- Google Fonts -->
		<link href="https://fonts.gstatic.com" rel="preconnect">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
		
		<!-- Vendor CSS Files -->
		<link href="resources/admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="resources/admin/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
		<link href="resources/admin/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
		<link href="resources/admin/vendor/quill/quill.snow.css" rel="stylesheet">
		<link href="resources/admin/vendor/quill/quill.bubble.css" rel="stylesheet">
		<link href="resources/admin/vendor/remixicon/remixicon.css" rel="stylesheet">
		<link href="resources/admin/vendor/simple-datatables/style.css" rel="stylesheet">
		
		<!-- Template Main CSS File -->
		<link href="resources/admin/css/style.css" rel="stylesheet">
	
		<style>
			#main h1 {
     			margin-top: 45px !important;
    		}
    		h1 {
      			font-size: 2rem !important;
      			margin-left: 10px;
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
      			margin-top: 40px;
    		}
		</style>
	</head>

	<body>
		<tiles:insertAttribute name="admin_header" />
		
		<tiles:insertAttribute name="admin_sidebar" />
		
		<tiles:insertAttribute name="admin_content" />
		
		<!-- Vendor JS Files -->
		<script src="resources/admin/vendor/apexcharts/apexcharts.min.js"></script>
		<script src="resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		<script src="resources/admin/vendor/chart.js/chart.umd.js"></script>
		<script src="resources/admin/vendor/echarts/echarts.min.js"></script>
		<script src="resources/admin/vendor/quill/quill.min.js"></script>
		<script src="resources/admin/vendor/simple-datatables/simple-datatables.js"></script>
		<script src="resources/admin/vendor/tinymce/tinymce.min.js"></script>
		<script src="resources/admin/vendor/php-email-form/validate.js"></script>
		
		<!-- Template Main JS File -->
		<script src="resources/admin/js/main.js"></script>
	</body>
</html>