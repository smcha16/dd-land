<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#wrap {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100dvh; /* 뷰포트 높이에 맞게 조절 */
}

.list_id {
	text-align: left;
	margin: 0 auto;
	display: inline-block;
}

.btn_area {
	text-align: center;
}
#header1 {
	/* padding-top: 10px; */
	/* padding-bottom: 20px; */
	text-align: center;
}


</style>
<link rel="stylesheet" type="text/css"
	href="/dd/resources/login/findid.css">
	
	
	
<div id="wrap">
	<div id="content">
	
	<div id="header1">
		<a href="/dd/index.do" target="_self" title="DD Studio"><img
			src="/dd/resources/files/dd/DD.png" id="logo"></a>
	</div>
	
		<div id="content" class="non_sign inquiry ">
			<div class="content_header">
				<h2>
					<img
						src="https://static.nid.naver.com/images/user/images/user/h_find_id.gif"
						width="67" height="16" alt="아이디 찾기">
				</h2>
			</div>


			<p class="content_summary">${dto.name}님의 정보와 일치하는 아이디 목록입니다.</p>
			<form name="fm1" action="" method="POST">
				<input type=hidden name="nv_id" value=""> <input type=hidden
					name="token_help" value="k3nabcmsUZ8XjUG2" /> <input type=hidden
					id="authType" name="authType" value="RM" />
				<div class="section section_find">
					<div class="box6" style="text-align: center">
						<ul class="list_id">
						
							
							<li><strong> 
							<input type="radio" name="select"
									id="select1" value="${dto.email}" /> <label
									for="select1" class="label_rd">${dto.email}</label>
							</strong> 
							<span>가입 : 2022.07.16</span> <input type=hidden name="nv_id1" value="p1nt3r3st"></li>
								
						</ul>
					</div>
					<!-- 버튼 영역 -->
					<div class="btn_area">
						<a href="/dd/user/login/view.do"
							onclick=""
							class="btn_login2"><span class="blind">로그인하기</span></a> <a
							href="/dd/user/login/findpw.do?user_seq=${dto.user_seq}"
							onclick="return checkSelectedId();"
							class="btn_findpw"><span class="blind">비밀번호 찾기</span></a>
					</div>
					<!-- 버튼 영역 -->
					<!-- 상세내용 영역 -->


					<div id="div_joinGuide_close" class="find_dsc">
						<h3>
							가입한 아이디가 보이지 않나요? <a
								href="javascript:showDetail('1','div_joinGuide_close');"
								onClick="clickcr(this,'uid.missed','','',event);">상세내용 펼치기<em
								class="ico_arr"></em></a>
						</h3>
						<div class="find_dsc_sub">
							<p>
								다른 방법으로 아이디 찾기를 해 보세요.<br>아이핀, 본인 명의 휴대전화를 이용하면 본인 명의로 가입된
								아이디를 찾을 수 있고,<br>회원정보에 등록된 정보로 아이디 찾기를 하면, 명의와는 관계 없이 등록한
								정보가 일치하는 아이디를 찾을 수 있습니다.
							</p>
							<p>
								다른 방법으로도 아이디를 찾지 못했다면 아이디가 원래 없었을 수 있습니다. <a target="_self"
									href="/dd/user/register/view.do"
								>아이디를 새로
									만드세요.</a>
							</p>
						</div>
					</div>
					<div id="div_joinGuide_open" class="find_dsc open"
						style="display: none">
						<h3>
							가입한 아이디가 보이지 않나요? <a
								href="javascript:showDetail('1','div_joinGuide_open');"
								onClick="clickcr(this,'uhl.missfold','','',event);">상세내용 접기<em
								class="ico_arr"></em></a>
						</h3>
						<div class="find_dsc_sub">
							<p>
								다른 방법으로 아이디 찾기를 해 보세요.<br>아이핀, 본인 명의 휴대전화를 이용하면 본인 명의로 가입된
								아이디를 찾을 수 있고,<br>회원정보에 등록된 정보로 아이디 찾기를 하면, 명의와는 관계 없이 등록한
								정보가 일치하는 아이디를 찾을 수 있습니다.
							</p>
							<p>
								다른 방법으로도 아이디를 찾지 못했다면 아이디가 원래 없었을 수 있습니다. <a target="_blank"
									href="https://nid.naver.com/user/join.html?url=http://www.naver.com"
									onClick="clickcr(this,'uhl.missjoin','','',event);">아이디를 새로
									만드세요.</a>
							</p>
						</div>
					</div>

					<div id="div_leaveGuide_close" class="find_dsc">
						<h3>
							직접 가입하지 않은 아이디를 탈퇴(삭제)하고 싶으신가요? <a
								href="javascript:showDetail('2','div_leaveGuide_close');"
								onClick="clickcr(this,'uid.notmine','','',event);">상세내용 펼치기<em
								class="ico_arr"></em></a>
						</h3>
					</div>
					<div id="div_leaveGuide_open" class="find_dsc open"
						style="display: none">
						<h3>
							직접 가입하지 않은 아이디를 탈퇴(삭제)하고 싶으신가요? <a
								href="javascript:showDetail('2','div_leaveGuide_open');"
								onClick="clickcr(this,'uhl.nomyfold','','',event);">상세내용 접기<em
								class="ico_arr"></em></a>
						</h3>
						<div class="find_dsc_sub">
							<!--휴대전화나 이메일로 인증시 문구-->
							<p>
								신고를 하고 싶으시다면 도움말을 참고해 주세요. <a target="_blank"
									href="https://help.naver.com/alias/membership/p.membership/p.membership_49.naver"
									onClick="clickcr(this,'uhl.nomyhelp','','',event);">도움말</a>
							</p>
						</div>
					</div>
					<!-- 상세내용 영역 -->
				</div>
			</form>
			
			
		</div>
		


		
	
	
	
	</div>
</div>



<script>

function checkSelectedId() {
    var elements = document.getElementsByName("select");
    var check = false;

    for (var i = 0; i < elements.length; i++) {
        if (elements[i].checked) {
            check = true;
            break;
        }
    }

    if (!check) {
        alert("아이디를 선택해 주세요.");
    }

    return check;
}

	var gnb_option = {
		gnb_service : "nid",
		gnb_template : "gnb_utf8",
		gnb_logout : encodeURIComponent("https://nid.naver.com/user2/help/idInquiry"),
		gnb_brightness : 2,
		gnb_item_hide_option : 0
	}



	function gnbClose() {
		$('#wrap').click(function(e) {
			if (!$('#gnb').has(e.target).length) {
				gnbAllLayerClose();
			}
		});
	}
	//120919 win8 이슈 대응 : capslock 자동설정해제
	document.msCapsLockWarningOff = true;
	function setContainerHeight(height) {
	}
	function showMenu(obj, obj2) {
		document.getElementById(obj).className = "on";
		document.getElementById(obj2).className = "";
	}

	var menuList = "idinquiry pwinquiry ";
	function hideMenu(obj) {
		var otherMenu = menuList.split(" ");
		for (var i = 0; i < otherMenu.length - 1; i++) {
			document.getElementById(otherMenu[i]).className = "";
		}

		document.getElementById(obj).className = "on";
	}
	function goPage(obj, obj2) {
		var url = "https://nid.naver.com/user2/help/" + obj + "?menu=" + obj2;
		location.href = url;
	}

	function clearDocs() {
	}
</script>
<style type="text/css">
/* GNB Common */
body, p, h1, h2, h3, h4, h5, h6, menu, ul, ol, li, dl, dt, dd, table, th,
	td, form, fieldset, legend, input, textarea, button, select {
	margin: 0;
	padding: 0
}

body, input, textarea, select, button, table {
	font-family: '돋움', Dotum, AppleGothic, sans-serif;
	font-size: 12px
}

img, fieldset {
	border: 0
}

menu, ul, ol {
	list-style: none
}

em, address {
	font-style: normal
}

a {
	text-decoration: none
}

a:hover, a:active, a:focus {
	text-decoration: underline
}

button {
	cursor: pointer
}

button.disabled {
	cursor: default
}

.blind {
	display: block;
	overflow: hidden;
	*position: absolute;
	top: 0;
	left: 0;
	width: 0;
	height: 0;
	border: 0;
	background: none;
	font-size: 0;
	line-height: 0
}

#gnb {
	float: right;
	right: 3px
}
</style>
<meta name="decorator" content="USER_INQUIRY">
<script type="text/javascript">
<!--
	//nClicks 전역변수
	var nsc = "my.idinqury";
	var ccsrv = "cc.naver.com";

	function login() {
		var elements = document.getElementsByName("select");
		var check = -1;

		for (var i = 0; i < elements.length; i++) {
			if (elements[i].checked) {
				check = 0;
				document.fm1.nv_id.value = eval("document.fm1.nv_id" + i
						+ ".value");
			}
		}

		if (check == -1) {
			alert("아이디를 선택해 주세요.");
			return;
		}

		location.href = "https://nid.naver.com/nidlogin.login?id="
				+ document.fm1.nv_id.value + "&url=http://www.naver.com";
	}

	function popup_change_passwd() {
		var elements = document.getElementsByName("select");
		var check = -1;
		var nvKey = '';

		for (var i = 0; i < elements.length; i++) {
			if (elements[i].checked) {
				check = 0;
				value = document.getElementsByName("select").item(i).id;
				nvKey = document.getElementsByName("select").item(i).value;
			}
		}

		if (check == -1) {
			alert("아이디를 선택해 주세요.");
			return;
		}

		// 비밀번호 찾기 팝업
		//var url = "/user2/help/idInquiry?m=viewChangePasswd&token_help=k3nabcmsUZ8XjUG2&nvKey=" + nvKey;
		//winOpenAtCenter(url, "auth", 400, 490, 'auto');

		// 비밀번호 찾기 화면
		var url = "/user2/help/idInquiry?m=viewChangePasswd&token_help=k3nabcmsUZ8XjUG2&nvKey="
				+ nvKey;
		location.href = url;
	}

	// 상세설명
	function showDetail(type, div) {
		if (type == '1') {
			if (div == 'div_joinGuide_open') { // 열려있을때 누르면
				document.getElementById("div_joinGuide_open").style.display = "none";
				document.getElementById("div_joinGuide_close").style.display = "block";
			} else {
				document.getElementById("div_joinGuide_open").style.display = "block";
				document.getElementById("div_joinGuide_close").style.display = "none";
			}
		}

		if (type == '2') {
			if (div == 'div_leaveGuide_open') { // 열려있을때 누르면
				document.getElementById("div_leaveGuide_open").style.display = "none";
				document.getElementById("div_leaveGuide_close").style.display = "block";
			} else {
				document.getElementById("div_leaveGuide_open").style.display = "block";
				document.getElementById("div_leaveGuide_close").style.display = "none";
			}
		}
	}

	function showGroupV2Detail() {
		var divGroupV2Guide = document.getElementById("div_groupV2Guide");
		var ancDtailGroup = document.getElementById("anc_detailGroup");

		if (divGroupV2Guide.className == 'find_dsc') {
			divGroupV2Guide.className = 'find_dsc open';
			ancDtailGroup.innerHTML = "상세내용 접기<em class='ico_arr'></em>";
		} else {
			divGroupV2Guide.className = 'find_dsc';
			ancDtailGroup.innerHTML = "상세내용 펼치기<em class='ico_arr'></em>";
		}
	}
//-->
</script>