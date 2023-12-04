<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  list (2) Template -->
 <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>
  
<style>
    #title+div {
    	display: inline-block;
	    text-shadow: 0 2px 10px rgba(255, 255, 255, 0.8);
	    font-size: 17px;
	    color: #222222;
	    background-color: rgba(255, 255, 255, 0.6);
	    padding: 5px 20px;
	    border-radius: 50px;
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
    

    
    
    
    
    
    #notice {
				/* text-align: center; */
				margin-top: 50px;
			}
			#notice-list {
				width: 75%;
				text-align: center;
				border-top: 2px solid #000;
				margin: 0 auto;
			}
			#notice-list th, #notice-list td {
				height: 60px;
				color: #444;
				padding: 10px;
				border-bottom: 1px solid #E1E1E1;
			}
			#notice-list th {
				font-size: 1.02rem;
				font-weight: bold;
			}
			#notice-list th:nth-child(1) {
				width: 20%;
			}
			#notice-list th:nth-child(2) {
				width: 60%;
			}
			#notice-list th:nth-child(3) {
				width: 20%;
			}
			#notice-list td:nth-child(2) {
				font-size: 1.1rem;
			}
			#notice-list td:nth-child(2) a {
				font-weight: bold;
				color: #333;
			}
			#fixed {
				background-color: #D5F1EA;
			}
			#fix-icon {
				color: #FF0076;
				margin-top: 7px;
			}
</style>

<!-- ======= Stats Counter Section ======= -->

<section id="stats-counter" class="stats-counter">
	<div id="pagetitle" class="container" data-aos="zoom-out">
		<div class="gy-4" style="justify-content: center; width: 100%;">
			<div class="col-lg-3 col-md-6" style="width: 100%;">
				<div class="stats-item text-center w-100 h-100">
					<div id="title" style="padding: 0 !important; font-size: 48px; font-weight: 700; color: #fff;">공지사항</div>
						<div style="width: 400px; height: 40px; position: relative;">
						<select class="select">
							<option>제목</option>
							<option>내용</option>
						</select>
						<input type="text" style="width: 250px; background-color: transparent; border: 0; position: absolute; left: 93px;">
		                <i class="fa-solid fa-magnifying-glass" style="float: right; transform: translate(5px, 6px);"></i>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- ======= Menu Section ======= -->
    
<main id="notice">
			
	<table id="notice-list">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td>제목</td>
				<td>내용</td>
			</tr>
		</tbody>
	</table>
</main>
		
<div id="page-bar">${pageBar}</div>