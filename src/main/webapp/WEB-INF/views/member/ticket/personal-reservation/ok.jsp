<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="reservationSuccessDiv" style="display: none;">
    <script>
        // Check if the reservationSuccess variable is defined and not empty
        if (typeof reservationSuccess !== 'undefined' && reservationSuccess !== null && reservationSuccess !== '') {
            alert(reservationSuccess);
        }
    </script>
</div>