<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
	* {
		font-family: 'SUIT-Regular';
    }
	section {
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    height: 368.4px;
	    padding: 100px 0 7px;
	}
	section h1 {
		font-size: 48px;
	    text-align: center;
	}
	#regdate {
		font-size: 1.05rem;
		text-align: center;
		margin-top: 40px;
	}
	#regdate span {
		font-weight: bold;
		color: #444;
		margin-left: 7px;
		margin-right: 15px;
	}
	#notice-detail {
		min-height: 918.8px;
		text-align: center;
	}
	#detail {
		width: 75%;
		margin: 30px auto 0;
	}
	#content {
	    font-size: 1.03rem;
	    color: #555;
	    text-align: left;
	    padding: 40px;
	}
	#attach {
	    padding: 30px 0;
	}
	#button button {
		background-color: #CE1212;
		border-color: #CE1212;
		margin: 50px 0;
	}
	#button i {
		margin-right: 7px;
	}
</style>

<!-- ======= Title Section ======= -->

<section id="gallery" class="gallery section-bg">
    <div>
        <h1><c:out value="${dto.subject}" /></h1>
        <div id="regdate">
            <i class="bi bi-calendar-check"></i><span>등록일</span>${dto.regdate}
        </div>
    </div>
</section>
	
<!-- ======= Main Section ======= -->

<main id="notice-detail">
	<table id="detail">
		<c:if test="${dto.content != null && !dto.content.trim().equals('')}">
			<tr>
				<td id="content"><c:out value="${dto.content}" /></td>
			</tr>
		</c:if>
		
		<c:if test="${dto.attach != null}">
			<tr>
				<td id="attach"><img src="/dd/resources/files/communication/notice/${dto.attach}" alt="Image"></td>
			</tr>
		</c:if>
	</table>
	
	<div id="button">
		<button type="button" id="back-button" class="btn btn-primary"><i class="bi bi-list"></i>목록</button>
	</div>
</main>

<script>
	$('#back-button').click(function () {
		location.href='/dd/user/communication/notice/view.do';
	});
</script>