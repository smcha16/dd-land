<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	margin: 10px 15px 10px 0;
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

.table {
	text-align: center;
}

th {
	background-color: #f2f2f2 !important;
}

.pagination {
	justify-content: center;
	margin-top: 40px;
}
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>DD 월드컵</h1>
	</div>

	<nav class="d-flex justify-content-end">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.html">추가</a></li>
			<li class="breadcrumb-item"><a href="#">수정</a></li>
			<li class="breadcrumb-item active"><a href="#">삭제</a></li>
		</ol>
	</nav>

	<section class="section">
		<div class="row">
			<div class="col-lg-6">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">어트랙션</h5>

						<table class="table">
							<thead>
								<tr>
									<th>No</th>
									<th>이름</th>
									<th>버튼</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td></td>
									<td>
										<div class="d-flex justify-content-center">
											<div class="form-check form-switch">
												<input class="form-check-input" type="checkbox"
													id="flexSwitchCheckDefault">
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>

						<ul class="pagination pagination-sm">
							<li class="page-item active" aria-current="page"><span
								class="page-link">1</span></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">4</a></li>
							<li class="page-item"><a class="page-link" href="#">5</a></li>
						</ul>

					</div>
				</div>

			</div>

			<div class="col-lg-6">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">코스</h5>

						<table class="table">
							<thead>
								<tr>
									<th>No</th>
									<th>이름</th>
									<th>버튼</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td></td>
									<td>
										<div class="d-flex justify-content-center">
											<div class="form-check form-switch">
												<input class="form-check-input" type="checkbox"
													id="flexSwitchCheckDefault">
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>

						<ul class="pagination pagination-sm">
							<li class="page-item active" aria-current="page"><span
								class="page-link">1</span></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">4</a></li>
							<li class="page-item"><a class="page-link" href="#">5</a></li>
						</ul>

					</div>
				</div>

			</div>
		</div>
	</section>

</main>