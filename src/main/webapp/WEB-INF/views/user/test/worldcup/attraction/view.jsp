<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 구글 폰트 로드 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap" rel="stylesheet">

<!-- user > test > worldcup > attraction > view.jsp -->
<style>
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&display=swap');

#title {
	font-size: 48px;
	display: block;
	color: #fff;
	font-weight: 700;
}

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

.item {
	position: relative;
	width: 25.5%;
	aspect-ratio: 0.75;
	padding: 0;
	box-sizing: border-box;
	min-width: 500px;
	border: 1px solid #E1E1E1;
	margin: 10px 45px 50px 45px;
	border-radius: 10px;
	transition: all 0.3s;
}

.item:hover {
	cursor: pointer;
	box-shadow: 12px 12px 17px rgba(0, 0, 0, 0.20);
}

.item {
	width: 50%;
	height: 600px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	background-color: transparent;
	border-radius: 8px;
	margin: 10px;
	padding: 20px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
	transition: all 0.35s ease;
	transform-origin: center bottom;
	cursor: pointer;
	font-size: 40px;
	font-weight: 600;
	color: #333;
	position: relative;
	overflow: hidden;
}

#result-message {
	padding-top: 20px;
	color: white;
	text-shadow: 0px 1px 5px black;
}

#worldcup-container {
	width: 100%;
	display: flex;
	padding-bottom: 20px;
	justify-content: center;
}

#attinfo {
	font-size : 18px;
	text-align: center;
	color: #444;
	font-weight: bold;
	margin-bottom: 3px;
}

.item div.img-container {
	width: 100%;
	height: 100%;
	background-size: cover;
	background-position: center;
}

.item>div:nth-child(1) {
	background-color: transparent;
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
	border-radius: 10px 10px 0 0;
}

.item>div:nth-child(2) {
	display: flex;
	flex-direction: column;
	padding: 20px;
	font-size: 1.3rem;
	font-weight: bold;
	border-radius: 10px 10px 10px 10px;
}

.attraction-container {
    text-align: center;
}

.remain-test {
    width: 140px;
    padding: 5px;
    text-align: center;
    font-size: 30px;
    margin-bottom: 20px;
    margin-top: -20px;
    color: #fff;
    font-weight: bold;
    background: linear-gradient(135deg, #3498db, #8e44ad);
    border: 1px solid #2980b9;
    border-radius: 70px;
    display: inline-block;
    transition: background 0.3s ease, border-color 0.3s ease, box-shadow 0.3s ease, color 0.3s ease;
}

.test-name {
	padding: 30px !important;
    font-size: 40px !important;
	text-align: center;
	position: absolute;
	color: white;
	text-shadow: 0px 1px 5px black;
}

.vs {
    position: absolute;
    font-family: 'Black Han Sans', sans-serif;
    font-size: 100px;
    font-style: italic;
    color: white;
    transform: translateY(162%);
    z-index: 1;
    transition: all 0.3s;
    letter-spacing: 5px;
    text-shadow: 3px 3px 10px rgba(0, 0, 0, 0.3);
}

.stats-counter {
	background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url("/dd/resources/files/test/worldcup/attraction/attraction_worldcup_title.png") center center;
	background-size: cover;
	background-attachment: fixed;
}

/* 모바일 적응형 설정 */
@media screen and (max-width: 600px) {
    .item {
        width: 90%;
        min-width: auto;
        height: auto;
        font-size: 18px;
    }

    .item>div:nth-child(2) {
        padding: 10px;
        font-size: 1rem;
    }

    #result-message {
        font-size: 14px;
    }

	.test-name {
		font-size: 18px !important;
	}
	
    #worldcup-container {
        padding-bottom: 10px;
    }

    #remaining-attractions-count {
        font-size: 14px;
        margin-bottom: 10px;
    }

    .vs {
        font-size: 30px;
        transform: translateY(245%);
    }
}
</style>

<!-- Main Start -->
<section id="stats-counter" class="stats-counter">
    <div id="pagetitle" class="container" data-aos="zoom-out">
        <div class="gy-4" style="justify-content: center; width: 100%;">
            <div class="col-lg-3 col-md-6" style="width: 100%;">
                <div class="stats-item text-center w-100 h-100">
                    <div id="title">어트랙션 월드컵</div>
                    <p>나에게 딱 맞는 어트랙션을 찾아보세요!</p>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="menu" class="menu">
	<div class="container" data-aos="fade-up">
		<div class="tab-content" data-aos="fade-up" data-aos-delay="300">
			<div class="tab-pane fade active show" id="menu-starters">
				<div class="attraction-container">
                    <div id="remaining-attractions-count" class="remain-test">1 / ${testCount - 1}</div>
					<div id="result-info"></div>
					<div id="worldcup-container" class="button-container">
						<!-- 어트랙션 출력 -->
						<c:forEach var="attraction" items="${selectedTwoAttractions}" varStatus="loop">
						    <div class="item" id="item${loop.index + 1}" onclick="selectAttraction('${attraction.attraction_seq}')">
						        <div style="display:none" data-attraction-seq="${attraction.attraction_seq}"></div>
						        <div class="img-container" style="background-image: url('/dd/resources/files/activity/attraction/${attraction.img}');"></div>
						        <div class="test-name">${attraction.name}</div>
						    </div>
						</c:forEach>
						<div class="vs">VS</div>
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
					
					if (remainingAttractions.length != 2) {
						$('#remaining-attractions-count').text(${testCount - 1} - (remainingAttractions.length - 2) + ' / ' + ${testCount - 1});
					} else {
						$('#remaining-attractions-count').text('결승');
					}
					
				} else {
					resultScreen(selectedTwoAttractions[0]);
					$('#remaining-attractions-count').text('');
					
					// 최종 우승 어트랙션
                    updateAWCFinalWinCount(selectedTwoAttractions[0].attraction_seq);
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
				const imgUrl = attraction.img ? '/dd/resources/files/activity/attraction/' + attraction.img : 'attraction.png';

				// 동적으로 id 생성
				const itemId = 'item' + (i + 1);

				const item = $('<div class="item" id="' + itemId + '" onclick="selectAttraction(' + attraction.attraction_seq + ')">')
					.append('<div style="display:none" data-attraction-seq=' + attraction.attraction_seq + '></div>')
					.append('<div class="img-container" style="background-image: url(\'' + imgUrl + '\');"></div>')
					.append('<div class="test-name">' + attraction.name + '</div>')
				$('#worldcup-container').append(item);

				$('#worldcup-container').append('<div class="vs">VS</div>');
			}
		} else {
			const attraction = selectedTwoAttractions[0];
			const imgUrl = attraction.img ? '/dd/resources/files/activity/attraction/' + attraction.img : 'attraction.png';

			// 동적으로 id 생성
			const itemId = 'item3';

			const item = $('<div class="item" id="' + itemId + '" onclick="selectAttraction(' + attraction.attraction_seq + ')">')
				.append('<div style="display:none" data-attraction-seq=' + attraction.attraction_seq + '></div>')
				.append('<div class="img-container" style="background-image: url(\'' + imgUrl + '\');"></div>')
				.append('<div class="test-name">' + attraction.name + '</div>');
			$('#worldcup-container').append(item);
		}
	}

	function resultScreen(selectedAttraction) {
		// 어트랙션을 화면에 갱신
		$('#worldcup-container').empty();
		$('.attraction-container').css({
		    'margin-top': '0px',
		    'text-align': 'center',
		    'font-weight': 'bold',
		    'font-size': '30px',
		    'background-color': '#ecf0f1',
		    'border-radius': '10px',
		    'box-shadow': '0 4px 6px rgba(0, 0, 0, 0.1)',
		    'transition': 'background-color 0.3s ease'
		});
		
		$('.remain-test').remove();
		
		const resultContainer = $('<div class="item result-container" id="item3">');
		const imgContainer = $('<div class="img-container" style="background-image: url(\'/dd/resources/files/activity/attraction/' + selectedAttraction.img + '\');"></div>');
		const message = $('<p id="result-message">최고의 어트랙션이죠!<br>[' + selectedAttraction.name + ']</p>'
				+ '<p id="attinfo">클릭시 해당 어트랙션 페이지로 이동합니다.</p>');

		// 메시지
		$('#result-info').append(message);

		// 최종 선택 어트랙션
		resultContainer.append(imgContainer);

		// 클릭 시 어트랙션 상세 페이지로 이동
		resultContainer.click(function() {
			window.location.href = '/dd/user/activity/attraction/detail.do?seq=' + selectedAttraction.attraction_seq;
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
	        	// console.log('최종 우승 업데이트 완료: ', data);
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
<!-- Main End -->
