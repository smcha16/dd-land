<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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

.breadcrumb {
	margin-right: 20px 15px 0 0;
}

.breadcrumb a {
	color: #012970;
}

.breadcrumb a:hover {
	color: #ce1212;
}

.card-body {
	min-height: 600px;
}
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>Form Elements</h1>

		<nav class="d-flex justify-content-end">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="javascript:void(0);"
					onclick="submit();">수정</a></li>
				<li class="breadcrumb-item active"><a
					href="/dd/admin/pb/price/view.do">취소</a></li>
			</ol>
		</nav>
	</div>

	<section class="section">
		<div class="row">
			<div class="col-lg-12">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">General Form Elements</h5>
						<form:form method="post" action="/dd/admin/pb/price/edit.do"
							modelAttribute="priceDTO">
							<%-- <form action="/dd/admin/pb/price/edit.do" method="POST"> --%>

							<div class="row mb-3">
								<label class="col-sm-2 col-form-label">개인/단체</label>
								<div class="col-sm-10">
									<select name="person_type" class="form-select"
										aria-label="Default select example">
										<option selected>개인/단체</option>
										<option value="개인">개인</option>
										<option value="단체">단체</option>
									</select>
								</div>
							</div>



							<div class="row mb-3">
								<label class="col-sm-2 col-form-label">구분</label>
								<div class="col-sm-10">
									<select name="age" class="form-select"
										aria-label="Default select example">
										<option selected>구분</option>
										<option value="성인">성인</option>
										<option value="청소년">청소년</option>
									</select>
								</div>
							</div>


							<div class="row mb-3">
								<label class="col-sm-2 col-form-label">종류</label>
								<div class="col-sm-10">
									<select name="ticket_type" class="form-select"
										aria-label="Default select example">
										<option selected>종류</option>
										<option value="1Day">1Day</option>
										<option value="After4">After4</option>
									</select>
								</div>
							</div>



							<div class="row mb-3">
								<label for="inputText" class="col-sm-2 col-form-label">가격</label>
								<div class="col-sm-10">
									<input type="text" name="price" class="form-control">
									<form:errors path="price" cssClass="text-danger" />
								</div>
							</div>


							<div>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}">
							</div>

						</form:form>
						<%-- </form> --%>

					</div>
				</div>

			</div>
		</div>
	</section>

</main>
<script>
	function submit() {
		$('form').submit();
	}
</script>