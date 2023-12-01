<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://me2.do/5BvBFJ57">
<style>

</style>
</head>
<body>
	<!-- view.jsp -->
	
	<table>
	<c:forEach items="${list}" var="dto">
	<tr>
		<th>티켓종류</th>
		<td>${dto.ticket_type}</td>
		<th>개인/단체구분</th>
		<td>${dto.person_type}</td>
		<th>나이구분</th>
		<td>${dto.age}</td>
		<th>요금</th>
		<td>${dto.price}</td>
	</tr>
	</c:forEach>
	</table>
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script>
	
	</script>
</body>
</html>