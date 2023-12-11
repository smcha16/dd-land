<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>
  
<style>
    #title + div {
    	display: inline-block;
	    text-shadow: 0 2px 10px rgba(255, 255, 255, 0.8);
	    font-size: 17px;
	    color: #222222;
	    background-color: rgba(255, 255, 255, 0.6);
	    padding: 5px 20px;
	    border-radius: 50px;
    }
    .stats-counter {
    	background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("/dd/resources/files/communication/lost.jpg") center center;
    	background-size: cover;
    	background-attachment: fixed;
    }
    #pagetitle {
		margin-top: 70px;
    }
    #title {
		margin-bottom: 20px;
    }
	.select {
		background: transparent;
		border: 0;
		position: absolute;
		top: 7px;
    	left: 15px;
    }
    select:focus, input:focus {
    	outline: none;
    }
    #lost-property {
		min-height: 700px;
		margin: 50px 0;
	}
	#search-date-form {
		display: flex;
        justify-content: flex-start;
        align-items: center;
		margin: 50px 185px 20px;
	}
	.form-control {
		width: 180px;
		border: 1px solid #CCC;
		margin: 0 10px;
		outline: none;
	}
	#lost-property-list {
		width: 75%;
		text-align: center;
		margin: 0 auto;
	}
	#lost-property-list thead tr {
		background-color: #EEE;
	}
	#lost-property-list th, #lost-property-list td {
		height: 60px;
		color: #444;
		padding: 10px;
		border-bottom: 1px solid #E1E1E1;
	}
	#lost-property-list th {
		font-size: 1.12rem;
		font-weight: bold;
	}
	#lost-property-list th:nth-child(1) {
		width: 12%;
	}
	#lost-property-list th:nth-child(2) {
		width: 12%;
	}
	#lost-property-list th:nth-child(3) {
		width: 23%;
	}
	#lost-property-list th:nth-child(4) {
		width: 23%;
	}
	#lost-property-list th:nth-child(5) {
		width: 15%;
	}
	#lost-property-list th:nth-child(6) {
		width: 15%;
	}
	#lost-property-list td {
		font-size: 1.05rem;
	}
	#lost-property-list td a {
		color: #444;
	}
	#lost-property-list td a:hover {
		font-weight: bold;
		color: #CE1212;
	}
	.modal-dialog {
		top: 50% !important;
        max-width: 50vw;
        margin: auto;
        transform: translateY(-50%) !important;
    }
    #modal-image {
    	display: block;
        max-width: 100%;
        max-height: 80vh;
        margin: auto;
    }
	#page-bar {
		margin-top: 50px;
	}
	.page-link {
		color: #CE1212;
	}
	.active > .page-link, .page-link.active {
		z-index: 3;
	    color: var(--bs-pagination-active-color);
	    background-color: #CE1212;
	    border-color: #CE1212;
	}
</style>

<!-- ======= Title Section ======= -->

<section id="stats-counter" class="stats-counter">
	<div id="pagetitle" class="container" data-aos="zoom-out">
		<div class="gy-4" style="justify-content: center; width: 100%;">
			<div class="col-lg-3 col-md-6" style="width: 100%;">
				<div class="stats-item text-center w-100 h-100">
				
					<div id="title" style="padding: 0 !important; font-size: 48px; font-weight: 700; color: #fff;">분실물 센터</div>
					
					<div style="width: 400px; height: 40px; position: relative;">
					
						<form method="GET" action="#" id="search-form">
							<select name="category" id="category" class="select">
								<option value="name">습득물</option>
								<option value="location">습득장소</option>
							</select>
							<input type="text" name="word" id="search-field" autocomplete="off" style="width: 230px; background-color: transparent; border: 0; position: absolute; left: 120px;">
		                	<button type="submit" id="search-button" style="background: none; border: none; cursor: pointer; position: absolute; right: 10px; top: 6px;">
						        <i class="fa-solid fa-magnifying-glass"></i>
						    </button>
		                </form>
		                
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- ======= Main Section ======= -->
    
<main id="lost-property">

	<form method="GET" action="#" id="search-date-form">
		<input type="date" id="start" class="form-control">
			~
		<input type="date" id="end" class="form-control">
	</form>

	<table id="lost-property-list">
		<thead>
			<tr>
				<th>번호</th>
				<th>분류</th>
				<th>습득물</th>
				<th>습득장소</th>
				<th>습득일</th>
				<th>처리결과</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="dto" varStatus="numberStatus">
				<tr>
		            <td>${map.totalPosts - numberStatus.index - map.startIndex + 1}</td>
		            <td>${dto.type}</td>
		            
		            <c:if test="${not empty dto.img}">
		            	<td><a onclick="showModal('/dd/resources/files/communication/lost/${dto.img}')"><c:out value="${dto.name}" /></a></td>
		            </c:if>
		            
		            <c:if test="${empty dto.img}">
		            	<td><c:out value="${dto.name}" /></td>
		            </c:if>
		            
		            <td><c:out value="${dto.location}" /></td>
		            <td>${dto.lost_property_date}</td>
		            <td>${dto.result}</td>
		        </tr>
			</c:forEach>
		</tbody>
	</table>

	<div id="modal" class="modal fade" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <img id="modal-image" src="" alt="Image">
                </div>
            </div>
        </div>
    </div>

	<nav id="page-bar" aria-label="Page navigation example">
	    <ul class="pagination justify-content-center">
	        <c:forEach begin="1" end="${map.totalPages}" varStatus="pageStatus">
	            <c:choose>
	                <c:when test="${pageStatus.index == currentPage}">
	                    <li class="page-item active"><span class="page-link">${pageStatus.index}</span></li>
	                </c:when>
	                <c:otherwise>
	                    <li class="page-item"><a class="page-link" href="/dd/user/communication/lost-property/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
	                </c:otherwise>
	            </c:choose>
	        </c:forEach>
	    </ul>
	</nav>
</main>

<script>
	$(document).ready(function () {	
	    $('#start').on('change', function () {
	        const selectedStartDate = new Date($('#start').val());
	        const minEndDate = new Date(selectedStartDate.getTime());
	        const formattedMinEndDate = minEndDate.toISOString().split('T')[0];
	
	        $('#end').attr('min', formattedMinEndDate);
	
	        if ($('#end').val() && new Date($('#end').val()) < selectedStartDate) {
	        	$('#end').val(formattedMinEndDate);
	        }
	    });
	});
	
	function showModal(imageUrl) {	
	    $('#modal-image').attr('src', imageUrl);
	
	    const modal = new bootstrap.Modal($('#modal'));
	
	    modal.show();
	}
</script>