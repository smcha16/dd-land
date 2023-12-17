<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- user > test > worldcup > course > view.jsp -->
<style>
#title+p {
	text-shadow: 0 2px 10px rgba(255, 255, 255, 0.8);
	padding: 5px 20px;
	color: #222222;
	font-size: 17px;
	background-color: rgba(255, 255, 255, 0.6);
	display: inline-block;
	border-radius: 50px;
}

#pagetitle {
	margin-top: 70px;
}

#title {
	margin-bottom: 20px;
}

.munti-content-container {
	/* display: flex; */
	flex-wrap: wrap;
	margin: 30px 50px 0 50px;
	padding: 0 !important;
}

.item {
	position: relative;
	width: 25.5%;
	aspect-ratio: 0.75;
	padding: 0;
	box-sizing: border-box;
	min-width: 270px;
	border: 1px solid #E1E1E1;
	margin: 10px 45px 50px 45px;
	border-radius: 10px;
	transition: all 0.3s;
}

.item:hover {
	cursor: pointer;
	box-shadow: 12px 12px 17px rgba(0, 0, 0, 0.20);
}

.item>div:nth-child(1) {
	height: 70%;
	background-color: transparent;
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
	border-radius: 10px 10px 0 0;
}

.item>div:nth-child(2) {
	height: 30%;
	display: flex;
	flex-direction: column;
	padding: 20px;
	font-size: 1.3rem;
	font-weight: bold;
	background: transparent;
	border-radius: 0 0 10px 10px;
}

.hidden-div {
	display: none;
	color: white;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 70%;
	padding: 20px;
	background-color: black;
	opacity: 0.65; /* 투명도 조절 */
	border-radius: 10px 10px 0 0;
	z-index: 1; /* 다른 요소들보다 위에 위치하도록 설정 */
}
</style>

<section id="stats-counter" class="stats-counter">
    <div id="pagetitle" class="container" data-aos="zoom-out">
        <div class="gy-4" style="justify-content: center; width: 100%;">
            <div class="col-lg-3 col-md-6" style="width: 100%;">
                <div class="stats-item text-center w-100 h-100">
                    <div id="title" style="font-size: 48px; display: block; color: #fff; font-weight: 700;">
                        코스 월드컵
                    </div>
                    <p>나에게 딱 맞는 코스를 찾아보세요!</p>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="menu" class="menu">
	<div class="container" data-aos="fade-up">
		<div class="tab-content" data-aos="fade-up" data-aos-delay="300">
			<div class="tab-pane fade active show" id="menu-starters">
				<div id="course-container" class="munti-content-container">
					<div id="remaining-course-count" style="text-align: center; margin-top: 20px; font-size: 18px;">남은 코스 수: ${testCount}</div>
					<div id="result-info"></div>
					<div id="worldcup-container" class="button-container">
						<!-- 코스 출력 -->
						<c:forEach var="course" items="${selectedTwoCourses}" varStatus="loop">
						    <div class="item" id="item${loop.index + 1}" onclick="selectCourse('${course.course_seq}')">
						        <div style="display:none" data-course-seq="${course.course_seq}"></div>
						        <div class="img-container" style="background-image: url('/dd/resources/files/test/worldcup/course/${course.img}');"></div>
						        <h3>${course.name}</h3>
						    </div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<script>
	let selectedTwoCourses;
	let remainingCourses;
	
	// CSRF token
	var csrfHeaderName = "${_csrf.headerName}";
	var csrfTokenValue = "${_csrf.token}";

	//페이지가 로드될 때 월드컵 세션 초기화
	$(document).ready(function() {
	    initializeSession();
	});

	function initializeSession() {
		$.ajax({
			type : 'GET',
			url : '/dd/user/test/worldcup/course/initialization.do',
			data : {
				'isNewSession' : true
			},
			success : function(data) {
				//console.log('세션 초기화');
			},
			error : function(a, b, c) {
				console.error(a, b, c);
			}
		});
	}

	function selectCourse(courseSeq) {
		// 첫 번째 코스의 course_seq
		const courseSeq1 = $('#item1 > div:nth-child(1)').data('course-seq');

		// 두 번째 코스의 course_seq
		const courseSeq2 = $('#item2 > div:nth-child(1)').data('course-seq');
		
		let winCourseSeq;
	    let lostCourseSeq;

	    if (courseSeq !== courseSeq1) {
	        winCourseSeq = courseSeq2;
	        lostCourseSeq = courseSeq1;
	    } else if (courseSeq !== courseSeq2) {
	        winCourseSeq = courseSeq1;
	        lostCourseSeq = courseSeq2;
	    } else {
	        console.error('No matching courseSeq found.');
	        return;
	    }
	    
		$.ajax({
			type: 'POST',
			url: '/dd/user/test/worldcup/course/view.do',
			data: {
				'winCourseSeq': winCourseSeq,
				'lostCourseSeq': lostCourseSeq
			},
		    dataType: 'json',
			success: function(data) {
				//console.log('선택한 코스:', data.selectedTwoCourses);
				//console.log('남은 코스:', data.remainingCourseSeqs);

				// 전역 변수에 할당
				selectedTwoCourses = data.selectedTwoCourses;
				remainingCourses = data.remainingCourses;

				// 코스 정보에 따라 화면 갱신
				if (selectedTwoCourses.length > 1) {
					refreshScreen();
					
					if (remainingCourses.length != 2) {
						$('#remaining-course-count').text('남은 코스 수: ' + remainingCourses.length);
					} else {
						$('#remaining-course-count').text('결승');
					}
					
				} else {
					resultScreen(selectedTwoCourses[0]);
					$('#remaining-course-count').text('');
					
					// 최종 우승 코스
                    updateCWCFinalWinCount(selectedTwoCourses[0].course_seq);
				}
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
			},
			error : function(a, b, c) {
				console.error(a, b, c);
			}
		});
	}
	
	function refreshScreen() {
		//console.log('refreshScreen 함수 호출');

		// 모든 코스를 화면에 갱신
		$('#worldcup-container').empty();

		if (selectedTwoCourses.length == 2) {
			for (let i = 0; i < selectedTwoCourses.length; i++) {
				const course = selectedTwoCourses[i];
				const imgUrl = course.img ? '/dd/resources/files/test/worldcup/course/' + course.img : 'course.png';

				// 동적으로 id 생성
				const itemId = 'item' + (i + 1);

				const item = $('<div class="item" id="' + itemId + '" onclick="selectCourse(' + course.course_seq + ')">')
					.append('<div style="display:none" data-course-seq=' + course.course_seq + '></div>')
					.append('<div class="img-container" style="background-image: url(\'' + imgUrl + '\');"></div>')
					.append('<h3>' + course.name + '</h3>')
				$('#worldcup-container').append(item);
			}
		} else {
			const course = selectedTwoCourses[0];
			const imgUrl = course.img ? '/dd/resources/files/test/worldcup/course/' + course.img : 'course.png';

			// 동적으로 id 생성
			const itemId = 'item3';

			const item = $('<div class="item" id="' + itemId + '>')
				.append('<div style="display:none" data-course-seq=' + course.course_seq + '></div>')
				.append('<div class="img-container" style="background-image: url(\'' + imgUrl + '\');"></div>')
				.append('<h3>' + course.name + '</h3>');
			$('#worldcup-container').append(item);
		}
	}

	function resultScreen(selectedCourse) {
		// 코스를 화면에 갱신
		$('#worldcup-container').empty();

		const resultContainer = $('<div class="item result-container" id="item3">');
		const imgContainer = $('<div class="img-container" style="background-image: url(\'/dd/resources/files/test/worldcup/course/' + selectedCourse.img + '\');"></div>');
		const infoname = $('<h3>' + selectedCourse.name + '</h3>');
		const message = $('<p id="result-message">최고의 코스죠!<br>[' + selectedCourse.name + ']</p>');

		// 메시지
		$('#result-info').append(message);

		// 최종 선택 코스
		resultContainer.append(imgContainer).append(infoname);

		// #worldcup-container에 추가
		$('#worldcup-container').append(resultContainer);
	}

	function updateCWCFinalWinCount(finalWinCourseSeq) {
	    $.ajax({
	        type: 'POST',
	        url: '/dd/user/test/worldcup/course/final.do',
	        data: {
	            'finalWinCourseSeq': finalWinCourseSeq
	        },
	        success: function(data) {
	            console.log('Final update completed:', data);
	        },
	        beforeSend: function(xhr) {
	            xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
	        },
	        error: function(a, b, c) {
	            console.error('Error during final update:', a, b, c);
	        }
	    });
	}
</script>

<!--  
<script>
	var itemElements = document.querySelectorAll('.item');
	itemElements.forEach(function(item) {
		item.addEventListener('mouseover', function() {
			// 마우스 오버 시 hidden-div를 보이게 변경
			item.querySelector('.hidden-div').style.display = 'block';
		});

		item.addEventListener('mouseout', function() {
			// 마우스 아웃 시 hidden-div를 다시 숨김
			item.querySelector('.hidden-div').style.display = 'none';
		});
	});
</script>
-->