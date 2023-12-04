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
		<link href="/dd/resources/admin/img/favicon.png" rel="icon">
		<link href="/dd/resources/admin/img/apple-touch-icon.png" rel="apple-touch-icon">
		
		<!-- Google Fonts -->
		<link href="https://fonts.gstatic.com" rel="preconnect">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
		
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
	</head>

	<body>
		<tiles:insertAttribute name="admin_header" />
		
		<tiles:insertAttribute name="admin_sidebar" />
		
		<tiles:insertAttribute name="admin_content" />
		
		<!-- Vendor JS Files -->
		<script src="/dd/resources/admin/vendor/apexcharts/apexcharts.min.js"></script>
		<script src="/dd/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
		<script src="/dd/resources/admin/vendor/chart.js/chart.umd.js"></script>
		<script src="/dd/resources/admin/vendor/echarts/echarts.min.js"></script>
		<script src="/dd/resources/admin/vendor/quill/quill.min.js"></script>
		<script src="/dd/resources/admin/vendor/simple-datatables/simple-datatables.js"></script>
		<script src="/dd/resources/admin/vendor/tinymce/tinymce.min.js"></script>
		<script src="/dd/resources/admin/vendor/php-email-form/validate.js"></script>
		
		<!-- Template Main JS File -->
		<script src="/dd/resources/admin/js/main.js"></script>
	</body>
</html>