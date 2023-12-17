<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- list (1) Template -->
<!-- user > test > view.jsp -->
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

<!-- ======= Stats Counter Section ======= -->
<section id="stats-counter" class="stats-counter">
    <div id="pagetitle" class="container" data-aos="zoom-out">
        <div class="gy-4" style="justify-content: center; width: 100%;">
            <div class="col-lg-3 col-md-6" style="width: 100%;">
                <div class="stats-item text-center w-100 h-100">
                    <div id="title" style="font-size: 48px; display: block; color: #fff; font-weight: 700;">
                        어트랙션 월드컵
                    </div>
                    <p>설명(나에게 딱 맞는 어트랙션을 찾아보세요!)</p>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- End Stats Counter Section -->

<section id="menu" class="menu">
	<div class="container" data-aos="fade-up">
		<div class="tab-content" data-aos="fade-up" data-aos-delay="300">
			<div class="tab-pane fade active show" id="menu-starters">
				<div id="attraction-container" class="munti-content-container">
					<div id="result-info"></div>
					<div id="worldcup-container" class="button-container">
						<!-- 어트랙션 출력 -->
						<c:forEach var="attraction" items="${selectedTwoAttractions}" varStatus="loop">
						    <div class="item" id="item${loop.index + 1}" onclick="selectAttraction('${attraction.attraction_seq}')">
						        <div style="display:none" data-attraction-seq="${attraction.attraction_seq}"></div>
						        <div class="img-container" style="background-image: url('/dd/resources/files/activity/attraction/${attraction.img}');"></div>
						        <h3>${attraction.name}</h3>
						    </div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<script>
	let selectedTwoAttractions;
	let remainingAttractions;
	
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
			url : '/dd/user/test/worldcup/attraction/initialization.do',
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

	function selectAttraction(attractionSeq) {
		// 첫 번째 어트랙션의 attraction_seq
		const attractionSeq1 = $('#item1 > div:nth-child(1)').data('attraction-seq');

		// 두 번째 어트랙션의 attraction_seq
		const attractionSeq2 = $('#item2 > div:nth-child(1)').data('attraction-seq');

		
		let winAttractionSeq;
	    let lostAttractionSeq;

	    if (attractionSeq !== attractionSeq1) {
	        winAttractionSeq = attractionSeq2;
	        lostAttractionSeq = attractionSeq1;
	    } else if (attractionSeq !== attractionSeq2) {
	        winAttractionSeq = attractionSeq1;
	        lostAttractionSeq = attractionSeq2;
	    } else {
	        console.error('No matching attractionSeq found.');
	        return;
	    }
	    
		$.ajax({
			type: 'POST',
			url: '/dd/user/test/worldcup/attraction/view.do',
			data: {
				'winAttractionSeq': winAttractionSeq,
				'lostAttractionSeq': lostAttractionSeq
			},
		    dataType: 'json',
			success: function(data) {
				//console.log('선택한 어트랙션:', data.selectedTwoAttractions);
				//console.log('남은 어트랙션:', data.remainingAttractionSeqs);

				// 전역 변수에 할당
				selectedTwoAttractions = data.selectedTwoAttractions;
				remainingAttractions = data.remainingAttractions;

				// 어트랙션 정보에 따라 화면 갱신
				if (selectedTwoAttractions.length > 1) {
					refreshScreen();
				} else {
					resultScreen(selectedTwoAttractions[0]);
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

		// 모든 어트랙션을 화면에 갱신
		$('#worldcup-container').empty();

		if (selectedTwoAttractions.length == 2) {
			for (let i = 0; i < selectedTwoAttractions.length; i++) {
				const attraction = selectedTwoAttractions[i];
				const imgUrl = attraction.img ? '/dd/resources/files/activity/attraction/'
						+ attraction.img
						: 'attraction.png';

				// 동적으로 id 생성
				const itemId = 'item' + (i + 1);

				const item = $('<div class="item" id="' + itemId + '" onclick="selectAttraction(' + attraction.attraction_seq + ')">')
					.append('<div style="display:none" data-attraction-seq=' + attraction.attraction_seq + '></div>')
					.append('<div class="img-container" style="background-image: url(\'' + imgUrl + '\');"></div>')
					.append('<h3>' + attraction.name + '</h3>')
				$('#worldcup-container').append(item);
			}
		} else {
			const attraction = selectedTwoAttractions[0];
			const imgUrl = attraction.img ? '/dd/resources/files/activity/attraction/'
					+ attraction.img
					: 'attraction.png';

			// 동적으로 id 생성
			const itemId = 'item3';

			const item = $('<div class="item" id="' + itemId + '" onclick="selectAttraction(' + attraction.attraction_seq + ')">')
				.append('<div style="display:none" data-attraction-seq=' + attraction.attraction_seq + '></div>')
				.append('<div class="img-container" style="background-image: url(\'' + imgUrl + '\');"></div>')
				.append('<h3>' + attraction.name + '</h3>');
			$('#worldcup-container').append(item);
		}
	}

	function resultScreen(selectedAttraction) {
		// 어트랙션을 화면에 갱신
		$('#worldcup-container').empty();

		const resultContainer = $('<div class="item result-container" id="item3">');
		const imgContainer = $('<div class="img-container" style="background-image: url(\'/dd/resources/files/activity/attraction/'
				+ selectedAttraction.img + '\');"></div>');
		const infoname = $('<h3>' + selectedAttraction.name + '</h3>');
		const message = $('<p id="result-message">최고의 어트랙션이죠!<br>['
				+ selectedAttraction.name + ']</p>'
				+ '<p id="attinfo">클릭시 해당 어트랙션 페이지로 이동합니다.</p>');

		// 메시지
		$('#result-info').append(message);

		// 최종 선택 어트랙션
		resultContainer.append(imgContainer).append(infoname);

		// 클릭 이벤트 처리
		resultContainer.click(function() {
			// 어트랙션 상세 페이지로 이동
			window.location.href = '/dd/user/activity/attraction/detail.do?seq=' + selectedAttraction.attraction_seq;
			
			updateAWCFinalWinCount(selectedAttraction.attraction_seq);
		});

		// #worldcup-container에 추가
		$('#worldcup-container').append(resultContainer);
	}

	function updateAWCFinalWinCount(finalWinAttractionSeq) {
	    $.ajax({
	        type: 'POST',
	        url: '/dd/user/test/worldcup/attraction/final.do',
	        data: {
	            'finalWinAttractionSeq': finalWinAttractionSeq
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