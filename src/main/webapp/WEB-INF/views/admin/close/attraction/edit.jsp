<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link href="/dd/resources/air-datepicker/dist/css/datepicker.min.css" rel="stylesheet" type="text/css" media="all">
    <!-- Air datepicker css -->
    <script src="/dd/resources/air-datepicker/dist/js/datepicker.js"></script> <!-- Air datepicker js -->
    <script src="/dd/resources/air-datepicker/dist/js/i18n/datepicker.ko.js"></script> <!-- 달력 한글 추가를 위해 커스텀 -->


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
    	margin: 20px 15px 0 0;
  	}
    .breadcrumb a {
    	color: #012970;
  	}
  	.breadcrumb a:hover {
      	color: #ce1212;
    }
    .card {
    	margin-bottom: 15px;
    }
    .card-body {
      min-height: 600px;
    }
    
    /* 달력 */

.date {
  position: relative;
  width: 300px;
  margin-left: 50px;
  margin-top: 100px;
}

#datepicker {
 font-size: 15px;
  color: #222222;
  width: 300px;
  border: none;
  border-bottom: solid #aaaaaa 1px;
  padding-bottom: 10px;
  text-align: center;
  position: relative;
  background: none;
  z-index: 5;
}

#datepicker::placeholder { color: #aaaaaa; }
#datepicker:focus { outline: none; }

.date span {
  display: block;
  position: absolute;
  bottom: 0;
  left: 50%;  /* right로만 바꿔주면 오 - 왼 */
  background-color: #666;
  width: 0;
  height: 2px;
  border-radius: 2px;
  transform: translateX(-50%);
  transition: 0.5s;
}

.date label {
position: absolute;
  color: #aaa;
  left: 10px;
  font-size: 20px;
  bottom: 8px;
  transition: all .2s;
}

input:focus ~ label, input:valid ~ label {
font-size: 16px;
bottom: 40px;
color: #666;
font-weight: bold;
}

input:focus ~ span, input:valid ~ span {
width: 100%;
}
</style>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>운휴 일정 수정</h1>
		
		<nav class="d-flex justify-content-end">
      		<ol class="breadcrumb">
          		<li class="breadcrumb-item"><a href="javascript:void(0);" onclick="submit();">수정</a></li>
          		<li class="breadcrumb-item active"><a href="/dd/admin/close/attraction/view.do">취소</a></li>
      		</ol>
		</nav>
  	</div>

  	<section class="section">
    	<div class="row">
     		 <div class="col-lg-12">

        		<div class="card">
          			<div class="card-body">
            			<h5 class="card-title">운휴 일정 입력</h5>

            			<form method="POST" action="/dd/admin/close/attraction/editok.do">
            				<div class="row mb-3">
				                <label class="col-sm-2 col-form-label">어트랙션명</label>
				                <div class="col-sm-10">
				                  	<select class="form-select" aria-label="Default select example">
				                    	<c:forEach items="${attlist}" var="dto">
											<option value="${dto.attraction_seq}">${dto.name}</option>
										</c:forEach>
				                  	</select>
			                	</div>
			              	</div>

							<div class="form-group date">
								<input type="text" class="datepicker" data-language='ko' required id="datepicker1"> - 
								<input class="datepicker" type="text" data-language='ko' required id="datepicker2">
								<label>Date</label> <span></span>
							</div>

							<!-- 토큰 -->
              				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
						</form>
					</div>
				</div>
      		</div>
    	</div>
	</section>

</main>

<script>
//두개짜리 제어 연결된거 만들어주는 함수
datePickerSet($("#datepicker1"), $("#datepicker2"), true); //다중은 시작하는 달력 먼저, 끝달력 2번째

/*
    * 달력 생성기
    * @param sDate 파라미터만 넣으면 1개짜리 달력 생성
    * @example   datePickerSet($("#datepicker"));
    * 
    * 
    * @param sDate, 
    * @param eDate 2개 넣으면 연결달력 생성되어 서로의 날짜를 넘어가지 않음
    * @example   datePickerSet($("#datepicker1"), $("#datepicker2"));
    */
function datePickerSet(sDate, eDate, flag) {

    //시작 ~ 종료 2개 짜리 달력 datepicker	
    if (!isValidStr(sDate) && !isValidStr(eDate) && sDate.length > 0 && eDate.length > 0) {
        var sDay = sDate.val();
        var eDay = eDate.val();

        if (flag && !isValidStr(sDay) && !isValidStr(eDay)) { //처음 입력 날짜 설정, update...			
            var sdp = sDate.datepicker().data("datepicker");
            sdp.selectDate(new Date(sDay.replace(/-/g, "/")));  //익스에서는 그냥 new Date하면 -을 인식못함 replace필요

            var edp = eDate.datepicker().data("datepicker");
            edp.selectDate(new Date(eDay.replace(/-/g, "/")));  //익스에서는 그냥 new Date하면 -을 인식못함 replace필요
        }

        //시작일자 세팅하기 날짜가 없는경우엔 제한을 걸지 않음
        if (!isValidStr(eDay)) {
            sDate.datepicker({
                maxDate: new Date(eDay.replace(/-/g, "/"))
            });
        }
        sDate.datepicker({
            language: 'ko',
            autoClose: true,
            onSelect: function () {
                datePickerSet(sDate, eDate);
            }
        });

        //종료일자 세팅하기 날짜가 없는경우엔 제한을 걸지 않음
        if (!isValidStr(sDay)) {
            eDate.datepicker({
                minDate: new Date(sDay.replace(/-/g, "/"))
            });
        }
        eDate.datepicker({
            language: 'ko',
            autoClose: true,
            onSelect: function () {
                datePickerSet(sDate, eDate);
            }
        });

        //한개짜리 달력 datepicker
    } else if (!isValidStr(sDate)) {
        var sDay = sDate.val();
        if (flag && !isValidStr(sDay)) { //처음 입력 날짜 설정, update...			
            var sdp = sDate.datepicker().data("datepicker");
            sdp.selectDate(new Date(sDay.replace(/-/g, "/"))); //익스에서는 그냥 new Date하면 -을 인식못함 replace필요
        }

        sDate.datepicker({
            language: 'ko',
            autoClose: true
        });
    }


    function isValidStr(str) {
        if (str == null || str == undefined || str == "")
            return true;
        else
            return false;
    }
}
</script>