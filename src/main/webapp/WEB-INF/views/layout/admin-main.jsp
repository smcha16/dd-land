<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link href="resources/admin/img/apple-touch-icon.png"
	rel="apple-touch-icon">

<!-- Google Fonts -->
<link href="https://fonts.gstatic.com" rel="preconnect">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="resources/admin/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="resources/admin/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="resources/admin/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="resources/admin/vendor/quill/quill.snow.css"
	rel="stylesheet">
<link href="resources/admin/vendor/quill/quill.bubble.css"
	rel="stylesheet">
<link href="resources/admin/vendor/remixicon/remixicon.css"
	rel="stylesheet">
<link href="resources/admin/vendor/simple-datatables/style.css"
	rel="stylesheet">

<!-- Template Main CSS File -->
<link href="resources/admin/css/style.css" rel="stylesheet">

<style>
body {
	display: flex;
	flex-direction: column;
	min-height: 100vh;
	overflow-y: scroll;
}

main {
	flex: 1;
	background: url('/dd/resources/admin/img/놀이공원.jpg') center/cover
		no-repeat;
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
</style>


<style>
#reveal {
	width: 100%;
	height: 100vh;
	background-size: cover;
}

#reveal:before {
	content: "";
	background: rgba(5, 13, 24, 0.3);
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
	font-size: 64px;
	font-weight: 700;
	line-height: 56px;
	color: #fff;
}

#reveal p {
	color: #fff;
	margin-bottom: 50px;
	font-size: 26px;
	font-family: "Poppins", sans-serif;
}

#reveal p span {
	color: #fff;
	padding-bottom: 4px;
	letter-spacing: 1px;
	border-bottom: 3px solid #149ddd;
}


@media ( max-width : 768px) {
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
	<tiles:insertAttribute name="admin_header" />

	<tiles:insertAttribute name="admin_sidebar" />

	<tiles:insertAttribute name="admin_content" />
	<section id="reveal">
		<div class="reveal-container" data-aos="fade-in">
			<p>
				표시표시표시표시<span class="typed"
					data-typed-items="안녕하세요 관리자님, 안녕하세요 김수민님, 환영합니다"></span>
			</p>
		</div>
	</section>

	<!-- Vendor JS Files -->
	<script src="resources/admin/vendor/apexcharts/apexcharts.min.js"></script>
	<script
		src="resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="resources/admin/vendor/chart.js/chart.umd.js"></script>
	<script src="resources/admin/vendor/echarts/echarts.min.js"></script>
	<script src="resources/admin/vendor/quill/quill.min.js"></script>
	<script
		src="resources/admin/vendor/simple-datatables/simple-datatables.js"></script>
	<script src="resources/admin/vendor/tinymce/tinymce.min.js"></script>
	<script src="resources/admin/vendor/php-email-form/validate.js"></script>
	<script src="resources/admin/vendor/reveal/reveal.js"></script>

	<script>
		const typed = document.querySelector('.typed')
		if (typed) {
			let typed_strings = typed.getAttribute('data-typed-items')
			typed_strings = typed_strings.split(',')
			new Typed('.typed', {
				strings : typed_strings,
				loop : true,
				typeSpeed : 100,
				backSpeed : 50,
				backDelay : 2000
			});
		}
	</script>
	<!-- Template Main JS File -->
	<script src="resources/admin/js/main.js"></script>
</body>
</html>