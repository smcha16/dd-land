<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
#main h1 {
	font-size: 2rem !important;
	margin-top: 45px !important;
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

.breadcrumb a:hover {
	color: #0d6efd;
}

.table {
	text-align: center;
}

th {
	background-color: #f2f2f2 !important;
}

.table th:nth-child(1) { width: 10%; }
.table th:nth-child(2) { width: 30%; }
.table th:nth-child(3) { width: 25%; }
.table th:nth-child(4) { width: 25%; }
.table th:nth-child(5) { width: 10%; }

.table td i {
	color: #0d6efd;
	margin-top: 7px;
}

.table td a {
	color: #000;
}

.table td a:hover {
	color: #0d6efd;
}

.pagination {
	justify-content: center;
	margin-top: 40px;
}

.form-check {
	min-height: 0 !important;
}

.hidden-seq {
	display: none;
}

/* 모달 CSS */
#modal table.m-desc {
	width: 100%;
	font-size: 14px;
}

#modal table tr > th {
	width: 120px;
	text-align: left;
	font-weight: bold;
	background: #FFF !important;
	padding: 10px;
}

#modal table tr > td {
	padding: 10px;
}
</style>

<!-- ======= Main ======= -->
<main id="main" class="main">

	<div class="pagetitle">
		<h1>어트랙션 월드컵 관리</h1>
	</div>

	<section class="section">
		<div class="row">
			<div class="col-lg-8">
				<div class="row">
					<div class="col-12">

						<div id="search" class="header">
                  			<form method="GET" action="/dd/admin/test/worldcup/attraction/view.do" class="search-form d-flex align-items-center">
                    			<input type="text" name="word" id="search-field" placeholder="제목 또는 내용을 입력하세요." autocomplete="off">
                    			<button type="submit"><i class="bi bi-search"></i></button>
                  			</form>
              			</div>
						
						<!-- 어트랙션 월드컵 상세 모달 -->
						<div id="modal" class="modal fade show" tabindex="-1" aria-labelledby="exampleModalScrollableTitle" aria-modal="true" role="dialog">
						    <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
						        <div class="modal-content">
						            <div class="modal-header">
						                <h5 id="modal-name" class="modal-title"></h5>
						                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						            </div>
					                
						            <div class="modal-body">
						            	<table class="m-desc">
						            		<colgroup>
						            			<col style="width: 100px">
						            		</colgroup>
						            		<tbody>
						            			<tr>
						            				<th>최종우승횟수</th>
						            				<td class="m-awc_final_win_count"></td>
						            			</tr>
						            			<tr>
						            				<th>승리횟수</th>
						            				<td class="m-awc_win_count"></td>
						            			</tr>
						            			<tr>
						            				<th>1:1 대결수</th>
						            				<td class="m-awc_match_count"></td>
						            			</tr>
						            		</tbody>
						            	</table>
						            </div>
						        </div>
						    </div>
						</div>

						<div class="card">
							<div class="card-body">

								<nav class="d-flex justify-content-end">
									<ol class="breadcrumb">
										<li class="breadcrumb-item"><a href="/dd/admin/activity/attraction/view.do">어트랙션 관리</a></li>
									</ol>
								</nav>

								<table class="table">
									<thead>
										<tr>
											<!-- <th></th> -->
											<th>No</th>
											<th>이름</th>
											<th>우승비율 (우승횟수/게임횟수)</th>
											<th>승률 (승리횟수/대결수)</th>
											<th>테스트 채택</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${listAttraction}" var="dto" varStatus="status">
											<tr>
												<!-- <td><input type="checkbox"></td> -->
												<td>${map.totalPosts - status.index - map.startIndex + 1}</td>
												<td><a onclick="showModal('${dto.attraction_seq}', '${dto.name}', '${dto.awc_final_win_count}', '${dto.awc_win_count}', '${dto.awc_match_count}')"><c:out value="${fn:substring(dto.name, 0, 22)}${fn:length(dto.name) > 22 ? '...' : ''}" /></a></td>
												<td>
												    <div class="progress" style="height: 20px;">
												        <div class="progress-bar" role="progressbar"
												            style="width: ${dto.awc_final_win_count != 0 ? String.format('%.2f', (dto.awc_final_win_count / (awcFinalWinTotalCount / 5)) * 100) : '0'}%;"
												            aria-valuenow="${dto.awc_final_win_count != 0 ? String.format('%.2f', (dto.awc_final_win_count / (awcFinalWinTotalCount / 5)) * 100) : '0'}"
												            aria-valuemin="0" aria-valuemax="100"
												            data-bs-toggle="tooltip" data-bs-placement="top"
												            title="${dto.awc_final_win_count}/${awcFinalWinTotalCount}">
												            ${dto.awc_final_win_count != 0 ? String.format('%.2f', (dto.awc_final_win_count / awcFinalWinTotalCount) * 100) : '0'}%
												        </div>
												    </div>
												</td>

										        <td>
												   <div class="progress" style="height: 20px;">
													    <div class="progress-bar" role="progressbar"
													        style="width: ${dto.awc_win_count != 0 && dto.awc_match_count != 0 ? String.format('%.2f', (dto.awc_win_count / dto.awc_match_count) * 100) : '0'}%;"
													        aria-valuenow="${dto.awc_win_count != 0 && dto.awc_match_count != 0 ? String.format('%.2f', (dto.awc_win_count / dto.awc_match_count) * 100) : '0'}"
													        aria-valuemin="0" aria-valuemax="100"
													        data-bs-toggle="tooltip" data-bs-placement="top"
													        title="${dto.awc_win_count}/${dto.awc_match_count}">
													        ${dto.awc_win_count != 0 && dto.awc_match_count != 0 ? String.format('%.2f', (dto.awc_win_count / dto.awc_match_count) * 100) : '0'}%
													    </div>
													</div>
												</td>

												<td>
													<div class="d-flex justify-content-center">
														<div class="form-check form-switch">
															<c:choose>
																<c:when test="${dto.is_test eq 'Y'}">
																	<input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault" checked>
																</c:when>
																<c:otherwise>
																	<input class="form-check-input" type="checkbox" id="flexSwitchCheckDefault">
																</c:otherwise>
															</c:choose>
														</div>
													</div>
												</td>
												<td class="hidden-seq">${dto.attraction_seq}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

								<ul class="pagination justify-content-center">
									<c:forEach begin="1" end="${map.totalPages}"
										varStatus="pageStatus">
										<c:choose>
											<c:when test="${pageStatus.index == currentPage}">
												<li class="page-item active"><span class="page-link">${pageStatus.index}</span></li>
											</c:when>
											<c:otherwise>
												<li class="page-item"><a class="page-link"
													href="/dd/admin/test/worldcup/attraction/view.do?page=${pageStatus.index}">${pageStatus.index}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</main>

<script>
	// 문서가 완전히 로드 된 뒤에 실행
	$(document).ready(function() {
		// 체크박스 클릭 이벤트
		$(document).on('change', '.form-check-input', function() {
			// 테스트 채택
			var isTest = $(this).is(':checked') ? 'Y' : 'N';

			// 선택한 어트랙션 일련번호
			var attractionSeq = $(this).closest('tr').find('td:nth-child(6)').text();
			
			// CSRF token
			var csrfHeaderName = "${_csrf.headerName}";
			var csrfTokenValue = "${_csrf.token}";

			// 데이터베이스 업데이트
			$.ajax({
				type : 'POST',
				url : '/dd/admin/test/worldcup/attraction/view.do',
				data : {
					attractionSeq : attractionSeq,
					isTest : isTest
				},
				beforeSend : function(xhr) {
					xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
				},
				/*
				success: function(response) {
					// console.log(response); // 응답 처리
				},
				 */
				error : function(a, b, c) {
					console.error(a, b, c);
				}
			});
		});
	});

	<!-- 검색 -->
	<c:if test="${map.searchStatus == 'y'}">
		$('#search-field').val('${map.word}');
	</c:if>
	
	$(document).keydown(function(event) {
	    if (event.key === 'F5') {
			location.href='/dd/admin/test/worldcup/attraction/view.do';
	    }
	});

	// 어트랙션 월드컵 상세 모달
	function showModal(seq, name, awc_final_win_count, awc_win_count, awc_match_count) {
	    
		$('#modal-name').text(name);

	    $('.m-awc_final_win_count').text(awc_final_win_count);
	    $('.m-awc_win_count').text(awc_win_count);
	    $('.m-awc_match_count').text(awc_match_count);
	    
	    $('#modal').modal('show');
	}
</script>
