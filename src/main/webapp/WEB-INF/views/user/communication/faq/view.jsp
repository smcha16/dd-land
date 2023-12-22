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
    	background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("/dd/resources/files/communication/faq.jpg") center center;
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
    #faq {
    	min-height: 700px;
		margin: 50px 0;
	}
	#type {
	    display: flex;
	    justify-content: space-around;
	    width: 65%;
	    margin: 0 auto 40px;
	}
	#type i {
		margin-right: 5px;
	}
	#type a {
		font-size: 1.4rem;
		font-weight: bold;
		color: #444;
		text-decoration: none;
		border: 0;
	}
	#type a:hover, #type a.selected {
	    color: #CE1212;
	}
	.faq-list {
	  	width: 75%;
		border-top: 2px solid #777;
		margin: 25px auto;
	}
	.faq {
		color: #444;
		text-align: left;
		padding: 25px;
		border-bottom: 1px solid #E1E1E1;
	  	overflow: hidden;
	  	position: relative;
	  	transition: 0.3s ease;
	}
	.question {
		display: flex;
		align-items: center;
		height: 50px;
		font-size: 1.13rem;
		margin-right: 50px;
	}
	.question i {
		font-size: 1.5rem;
		color: #CE1212;
		margin-right: 20px;
	}
	.answer {
	  	display: none;
	  	font-size: 1.02rem;
	  	margin: 30px 0 0;
	}
	.faq.toggle .answer {
	  	display: block;
	}
	.toggle-button {
	  	background-color: transparent;
	  	border: 0;
	  	position: absolute;
	  	top: 37px;
	  	right: 30px;
	  	cursor: pointer;
	}
	#up-button {
		display: none;
	}
	.toggle #down-button {
	  	display: none;
	}
	.toggle #up-button {
		display: block;
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
				
					<div id="title" style="padding: 0 !important; font-size: 48px; font-weight: 700; color: #fff;">FAQ</div>
					
					<div style="width: 400px; height: 40px; position: relative;">
					
						<form method="GET" action="/dd/user/communication/faq/view.do" id="search-form">
							<input type="hidden" name="type" value="${map.type}">
						
							<input type="text" name="word" id="search-field" autocomplete="off" style="width: 325px; background-color: transparent; border: 0; position: absolute; left: 25px;">
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
	    
<main id="faq">
	<div id="type">
	    <a href="/dd/user/communication/faq/view.do?type=이용정보" class="${map.type == '이용정보' ? 'selected' : ''}"><i class="bi bi-dot"></i>이용정보<i class="bi bi-dot"></i></a>
	    <a href="/dd/user/communication/faq/view.do?type=액티비티" class="${map.type == '액티비티' ? 'selected' : ''}"><i class="bi bi-dot"></i>액티비티<i class="bi bi-dot"></i></a>
	    <a href="/dd/user/communication/faq/view.do?type=혜택" class="${map.type == '혜택' ? 'selected' : ''}"><i class="bi bi-dot"></i>혜택<i class="bi bi-dot"></i></a>
	    <a href="/dd/user/communication/faq/view.do?type=예매" class="${map.type == '예매' ? 'selected' : ''}"><i class="bi bi-dot"></i>예매<i class="bi bi-dot"></i></a>
	    <a href="/dd/user/communication/faq/view.do?type=기타" class="${map.type == '기타' ? 'selected' : ''}"><i class="bi bi-dot"></i>기타<i class="bi bi-dot"></i></a>
	</div>

	<div class="faq-list">
		<c:forEach items="${list}" var="dto">
  			<div class="faq">
    			<span class="question"><i class="bi bi-question-circle"></i><c:out value="${dto.question}" /></span>
    			<span class="answer">${dto.answer}</span>
    			<button class="toggle-button">
    				<i id="down-button" class="fas fa-chevron-down"></i>
    				<i id="up-button" class="fas fa-chevron-up"></i>
    			</button>
  			</div>
		</c:forEach>
	</div>
    		
    <nav id="page-bar" aria-label="Page navigation example">
	    <ul class="pagination justify-content-center">
	        <c:forEach begin="1" end="${map.totalPages}" varStatus="pageStatus">
	            <c:choose>
	                <c:when test="${pageStatus.index == currentPage}">
	                    <li class="page-item active"><span class="page-link">${pageStatus.index}</span></li>
	                </c:when>
	                <c:otherwise>
	                    <li class="page-item"><a class="page-link" href="/dd/user/communication/faq/view.do?page=${pageStatus.index}&type=${map.type}">${pageStatus.index}</a></li>
	                </c:otherwise>
	            </c:choose>
	        </c:forEach>
	    </ul>
	</nav>
</main>
		
<script>
	<c:if test="${map.searchStatus == 'y'}">
		$('#search-field').val('${map.word}');
	</c:if>
	
	$(document).keydown(function(event) {
		if (event.key === 'F5') {
			location.href='/dd/user/communication/faq/view.do';
		}
	});

    document.addEventListener("DOMContentLoaded", () => {
        document.querySelectorAll(".faq").forEach((faq) => {
            faq.addEventListener("click", () => {
                faq.classList.toggle("toggle");
                faq.querySelector(".answer").style.display = faq.classList.contains("toggle") ? "block" : "none";

                document.querySelectorAll(".faq").forEach((otherFaq) => {
                    if (otherFaq !== faq) {
                        otherFaq.classList.remove("toggle");
                        otherFaq.querySelector(".answer").style.display = "none";
                    }
                });
            });
        });
    });
</script>