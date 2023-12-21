<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="/dd/resources/login/findid.css">
<style>
#header1 {
	/* padding-top: 10px; */
	/* padding-bottom: 20px; */
	text-align: center;
}

.box6 .n_id dd {
    color: #ce1212;
}
.process .on {
    color: #ce1212;
}
</style>
<script type="text/javascript" src="/inc/user/js/idPwInquiryAjax.js?20140106"></script>
<script type="text/javascript" src="https://nid.naver.com/js/clickcr.js"></script>
<script type="text/javascript" src="https://nid.naver.com/inc/common/js/commonUtil.js?20170214"></script>
<script type="text/javascript" src="https://nid.naver.com/inc/common/js/authUi.js?20230703"></script>
<script type="text/javascript" src="/inc/common/js/lcs_nclicks.js?r=20220411"></script>
<script type="text/javascript" src="https://nid.naver.com/inc/user/js/browser.js?20220411"></script>
<script type="text/javascript" src="https://nid.naver.com/inc/common/js/lua.js?r=20220411"></script>

<script type="text/javascript" src="https://nid.naver.com/inc/user/js/passwdAjax.js?20180530"></script>
<script type="text/javascript" src="https://nid.naver.com/inc/common/js/rsaAll.js"></script>
<script type="text/javascript" src="https://nid.naver.com/inc/user/js/soundCaptcha.js?20220411"></script>

<html>
<body onclick="clearDocs();gnbClose();">
	<div id="wrap" class="wrap_leave">
		<!-- 스킵네비게이션 : 웹접근성대응-->
		<div id="u_skip">
			<!-- [D] 주메뉴가 존재하는 페이지에 적용 -->
			<a href="#lnb"
				onclick="document.getElementById('lnb').tabIndex=-1;document.getElementById('lnb').focus();return false;"><span>주메뉴로
					바로가기</span></a> <a href="#content"
				onclick="document.getElementById('content').tabIndex=-1;document.getElementById('content').focus();return false;"><span>본문으로
					바로가기</span></a>
		</div>

		<div id="header1">
			<a href="/dd/index.do" target="_self" title="DD Studio"><img
				src="/dd/resources/files/dd/DD.png" id="logo"></a>
		</div>

		<div id="container">
			<!-- CONTENTS -->
			<!-- CONTENTS -->
			<div id="content" class="non_sign inquiry" onclick="clearDocs();">

				<div class="content_header">
					<h2>
						<img
							src="https://static.nid.naver.com/images/web/user/h_find_pw.gif"
							width="99" height="16" alt="비밀번호 재설정">
					</h2>
					<ol class="process">
						<!-- <li>01. 아이디 입력<span>&gt;</span></li>
						<li>02. 본인 확인<span>&gt;</span></li> -->
						<li class="on">비밀번호 재설정</li>
					</ol>
				</div>
				<p class="content_summary">
					비밀번호를 변경해 주세요.<br>다른 아이디나 사이트에서 사용한 적 없는 안전한 비밀번호로 변경해 주세요.
				</p>

				<form 
					action="/dd/user/login/findpw.do" method="post">
					

					<div class="section section_find">
						<div class="box6">
							<dl class="n_id">
								<dt> 아이디 :</dt>
								<dd>${dto.email}</dd>
							</dl>
							<div id="div_new_pw" class="input_box">
								<label id="lb_new_pw" for="new_pw" style="">새 비밀번호</label> <input
									id="new_pw" name="pw" type="password"
									maxlength="20"
									onkeyup="checkShiftUp(event);checkpwd_nologin('new_pw','id');"
									onkeypress="capslock(event);"
									onKeydown="checkShiftDown(event);"
									onFocus="checkpwd_nologin('new_pw','id');convertDiv('new_pw', 'new_conf_pw', 'none');"
									onBlur="showhelpmsg(-1);convertDiv('new_pw', 'new_conf_pw','block')"
									title="새 비밀번호 입력" class="input_txt">
								<!-- ///////////////////////////////////////////////////////////////////////////////////////// -->
								<div id="help1_1" class="help left h32"
									style="left: 305px; top: -1px; display: none;">
									<!-- 비밀번호 안전도 -->
									<div class="txt">
										<p>
											<strong>사용불가</strong> : 비밀번호 재작성 필요<br>
											<span class="ex">8~16 자의 영문 대소문자, 숫자 및 특수문자 사용</span>
										</p>
									</div>
									<!-- //비밀번호 안전도 -->
									<span class="arrow"></span>
								</div>
								<div id="help1_2" class="help left h32"
									style="left: 305px; top: -1px; display: none;">
									<!-- 비밀번호 안전도 -->
									<div class="txt">
										<p>
											비밀번호 안전도<em>|</em> <strong>낮음</strong> <img
												src="https://static.nid.naver.com/images/user/images/help/safetybar1.gif"
												width="47" height="3" alt=""><br>
											<span class="ex">안전도가 높은 비밀번호를 권장합니다.</span>
										</p>
									</div>
									<!-- //비밀번호 안전도 -->
									<span class="arrow"></span>
								</div>
								<div id="help1_3" class="help left h32"
									style="left: 305px; top: -1px; display: none;">
									<!-- 비밀번호 안전도 -->
									<div class="txt">
										<p>
											비밀번호 안전도<em>|</em> <strong>적정</strong> <img
												src="https://static.nid.naver.com/images/user/images/help/safetybar2.gif"
												width="47" height="3" alt=""><br>
											<span class="ex">안전하게 사용하실 수 있는 비밀번호 입니다.</span>
										</p>
									</div>
									<!-- //비밀번호 안전도 -->
									<span class="arrow"></span>
								</div>
								<div id="help1_4" class="help left h32"
									style="left: 305px; top: -1px; display: none;">
									<!-- 비밀번호 안전도 -->
									<div class="txt">
										<p>
											비밀번호 안전도<em>|</em> <strong>높음</strong> <img
												src="https://static.nid.naver.com/images/user/images/help/safetybar3.gif"
												width="47" height="3" alt=""><br>
											<span class="ex">예측하기 힘든 비밀번호로 더욱 안전합니다.</span>
										</p>
									</div>
									<!-- //비밀번호 안전도 -->
									<span class="arrow"></span>
								</div>
								<div id="help1_5" class="help left h32"
									style="left: 305px; top: -1px; display: none;">
									<!-- 비밀번호 안전도 -->
									<div class="txt">
										<p>
											비밀번호 안전도<em>|</em> <strong>낮음</strong> <img
												src="https://static.nid.naver.com/images/user/images/help/safetybar1.gif"
												width="47" height="3" alt=""><br> <span
												class="ex">이전에 사용하셨던 비밀번호입니다. <br>도용 예방을 위해 새로운
												비밀번호 <br>사용을 권장합니다.
											</span>
										</p>
										<p class="topline">
											비밀번호는 네이버 관리자도 알 수 없도록 <br>암호화하여 기존 비밀번호와의 <br>일치
											여부만을 확인하고 있습니다.
										</p>
									</div>
									<!-- //비밀번호 안전도 -->
									<span class="arrow"></span>
								</div>
								<div id="help1_6" class="help left h32"
									style="left: 305px; top: -1px; display: none;">
									<!-- 비밀번호 안전도 -->
									<div class="txt">
										<p>비밀번호는 8자 이상 입력하셔야 합니다.</p>
									</div>
									<!-- //비밀번호 안전도 -->
									<span class="arrow"></span>
								</div>
								<div id="help1_7" class="help left h32"
									style="left: 305px; top: -1px; display: none;">
									<div class="txt">
										<p>
											<strong>사용불가</strong> : 비밀번호 재작성 필요<br>
											<span>입력하신 비밀번호는 도용된 <br>비밀번호이므로 추가 도용 가능성이 <br>높아
												사용하실 수 없습니다.
											</span>
										</p>
										<p class="topline">
											비밀번호는 관리자도 알 수 없도록 <br>암호화하여 기존 비밀번호와의 <br>일치
											여부만을 확인하고 있습니다.
										</p>
									</div>
									<span class="arrow"></span>
								</div>
								<!-- 비밀번호 관련레이어 팝업 -->
								<div id="help2_1" class="help left h32"
									style="left: 305px; top: -1px; display: none;">
									<div class="txt">
										<!-- <p><span id="p01"><strong>사용불가</strong> : 비밀번호 재작성 필요 </span><br /> -->
										<p>
											<strong>사용불가</strong> : 비밀번호 재작성 필요 <br>
											<span id="p02" class="ex">8~16 자의 영문 대소문자, 숫자 및 <br>특수문자
												사용
											</span> <span class="ex2">키보드에<strong>CapsLock</strong>이 켜져
												있습니다.
											</span>
										</p>
									</div>
									<span class="arrow"></span>
								</div>
								<div id="help2_2" class="help left h32"
									style="left: 305px; top: -1px; display: none;">
									<div class="txt">
										<p>
											비밀번호 안전도<em>|</em> <strong>낮음</strong> <img alt=""
												src="https://static.nid.naver.com/images/user/images/help/safetybar1.gif"
												width="47" height="3"><br>
											<span class="ex">안전도가 높은 비밀번호를 권장합니다.</span><span class="ex2">키보드에<strong>CapsLock</strong>이
												켜져 있습니다.
											</span>
										</p>
									</div>
									<span class="arrow"></span>
								</div>
								<div id="help2_3" class="help left h32"
									style="left: 305px; top: -1px; display: none;">
									<div class="txt">
										<p>
											비밀번호 안전도<em>|</em> <strong>적정</strong> <img alt=""
												src="https://static.nid.naver.com/images/user/images/help/safetybar2.gif"
												width="47" height="3"><br>
											<span class="ex">안전하게 사용하실 수 있는 비밀번호 입니다.</span><span
												class="ex2">키보드에<strong>CapsLock</strong>이 켜져 있습니다.
											</span>
										</p>
									</div>
									<span class="arrow"></span>
								</div>
								<div id="help2_4" class="help left h32"
									style="left: 305px; top: -1px; display: none;">
									<div class="txt">
										<p>
											비밀번호 안전도<em>|</em> <strong>높음</strong> <img alt=""
												src="https://static.nid.naver.com/images/user/images/help/safetybar3.gif"
												width="47" height="3"><br>
											<span class="ex">예측하기 힘든 비밀번호로 더욱 안전합니다.</span><span
												class="ex2">키보드에<strong>CapsLock</strong>이 켜져 있습니다.
											</span>
										</p>
									</div>
									<span class="arrow"></span>
								</div>
								<div id="help2_5" class="help left h32"
									style="left: 305px; top: -1px; display: none;">
									<div class="txt">
										<p>
											비밀번호 안전도<em>|</em> <strong>낮음</strong> <img alt=""
												src="https://static.nid.naver.com/images/user/images/help/safetybar1.gif"
												width="47" height="3"><br>
											<span class="ex">이전에 사용하셨던 비밀번호입니다. <br>도용 예방을 위해
												새로운 비밀번호 <br>사용을 권장합니다.
											</span>
										</p>
										 <p>비밀번호 안전도 <em>|</em> <strong>중간</strong> <img src="https://static.nid.naver.com/images/user/images/help/safetybar2.gif" width="47" height="3" alt="" /></p>
												<p>비밀번호 안전도 <em>|</em> <strong>높음</strong> <img src="https://static.nid.naver.com/images/user/images/help/safetybar3.gif" width="47" height="3" alt="" /></p> 
										<p class="topline">
											비밀번호는 네이버 관리자도 알 수 없도록 <br>암호화하여 기존 비밀번호와의 <br>일치
											여부만을 확인하고 있습니다.
										</p>
									</div>
									<span class="arrow"></span>
								</div>
								<div id="help2_6" class="help left h32"
									style="left: 305px; top: -1px; display: none;">
									<div class="txt">
										<p>
											비밀번호는 8자 이상 입력하셔야 합니다.<span class="ex2">키보드에<strong>CapsLock</strong>이
												켜져 있습니다.
											</span>
										</p>
									</div>
									<span class="arrow"></span>
								</div>
								<div id="help2_7" class="help left h32"
									style="left: 305px; top: -1px; display: none;">
									<div class="txt">
										<p>
											<strong>사용불가</strong> : 비밀번호 재작성 필요<br>
											<span>입력하신 비밀번호는 도용된 <br>비밀번호이므로 추가 도용 가능성이 <br>높아
												사용하실 수 없습니다.
											</span>
										</p>
										<p class="topline">
											비밀번호는 네이버 관리자도 알 수 없도록 <br>암호화하여 기존 비밀번호와의 <br>일치
											여부만을 확인하고 있습니다. <span class="ex2">키보드에<strong>CapsLock</strong>이
												켜져 있습니다.
											</span>
										</p>
									</div>
									<span class="arrow"></span>
								</div>
							</div>

							<div id="div_new_conf_pw" class="input_box">
								<label id="lb_new_conf_pw" for="new_conf_pw" style="">새
									비밀번호 확인</label> <input id="new_conf_pw" name="new_conf_pw"
									type="password" value="" maxlength="20"
									onFocus="convertDiv('new_conf_pw', 'new_pw', 'none')"
									onBlur="convertDiv('new_conf_pw', 'new_pw','block')"
									title="새 비밀번호 확인" class="input_txt">
							</div>
							<!-- 이미지로 보기 -->
							<!-- <div id="captcha_image_legend" class="captcha"
								style="display: block">
								<p class="error_txt">아래 이미지를 보이는 대로 입력해주세요</p>
								<div id="image_captcha_div" class="error_v2">
									<span class="error_img"><img name='captchaImage'
										id='chptchaimg'
										src='/login/image/captcha/nhncaptchav4.gif?key=5eOoNlmlXO4zNerT&1'
										width='205' height='80' alt='자동 가입 방지'></span> <a
										href="javascript:reCaptcha('');" id="aReCaptcha" onclick=""
										class="btn_refresh"><span>새로고침</span></a> <a
										href="javascript:changeCaptchaMode('');"
										class="btn_sound"><span>음성으로 듣기</span></a> <span class="blind">
										자동입력방지문자 음성 안내입니다. 음성으로 듣기 버튼을 누르신 후 들려드리는 숫자 6자리를 자동입력 방지문자
										입력창에 입력해 주세요. 다시 듣고 싶으면, 키보드 컨트롤키와 알트키를 누르고 알파벳 R을 눌러주세요.</span>
								</div>
							</div>
							// 이미지로 보기
							[D]음성으로 듣기했을경우 display:block
							<div id="captcha_sound_legend" class="captcha error_sound"
								style="display: none">
								<p class="error_txt">스피커로 들리는 내용을 숫자로 입력해 주세요.</p>
								<div id="sound_captcha_div" class="error_v2"
									style="display: none">
									<span class="error_img">음성으로 안내되고 있습니다. (시작음 3회 반복 후 재생)</span>
									<a href="javascript:playSoundCaptcha('');" id="play_audio"
										class="btn_refresh"><span>새로고침</span></a> <a
										href="javascript:changeCaptchaMode('');" class="btn_sound"><span>음성으로 듣기
										
										</span></a>
								</div>
							</div> -->
							<!-- //음성으로 듣기 -->

							<!-- [D]focus시 클래스 focus 추가, label은 display:none시켜주세요 -->
							<!-- <div id="bdr_autoValue" class="input_box input_box_v2">
								<label id="lbCapcha" for="autoValue" class="lbl_in"
									style="display: block;">자동입력 방지문자</label> <span class="int_box"
									style="width: 289px"> 캡챠 어뷰징 방지를 위해 maxlength는 제거함 : 2014-02-11- euli0912
									<input type="text" id="autoValue" name="autoValue"
									onkeydown="checkNumber();" title="자동입력 방지문자"
									onfocus="hiddenText('lbCapcha');changeFocus('bdr_autoValue','on')"
									onblur="showText('lbCapcha','autoValue');changeFocus('bdr_autoValue','off')"
									class="int" style="width: 289px">
								</span>
							</div> -->
							<!-- //자동입력 방지문자-->
							<ul class="find_notice">
								<li>영문, 숫자, 특수문자를 함께 사용하면(8자 이상 16자 이하)보다 안전합니다.</li>
								<li>다른 사이트와 다른 <strong>DD 아이디만의 비밀번호</strong>를 만들어 주세요.
								</li>
								<li class="n_bu">
									<div id="divShowBlock" class="ip_che" style="display: none">
										<input type="checkbox" id="chkBlockIp" name="chkBlockIp"
											onclick="clickcr(this,'rst.ipblock','','',event);"
											class="input_chk" checked /> <label for="chkBlockIp">보안
											강화를 위해 해외 IP에서의 로그인 차단</label> <a href="javascript:toggle();"
											onclick="clickcr(this,'rst.ipblockhelp','','',event);"
											class="ico_help2"><span class="blind">도움말</span></a>
										<!-- 해외ip차단 도움말 -->
										<div id="tmpBlockIp" style="position: relative">
											<!-- [D] 레이어 팝업 비활성화시 style="display:none" -->
											<div id="blockIp" class="ly_pop_ab" style="display: none;">
												<span class="edge"></span>
												<p>
													해외에서 접속할 경우 회원정보에 등록된 이름/생일을 입력하여 본인 여부를 확인합니다. <br>기억나지
													않을 경우 등록된 사용자 연락처 정보로 로그인 하실 수 있습니다.
												</p>
												<p class="desc">해외 사용자이시거나, 여행, 출장 등으로 해외에서 체류할 경우 해제해
													주세요.</p>
											</div>
										</div>
									</div>
								</li>
							</ul>
						</div>
						<div class="btn_area">
							<a href="javascript:void(0);" id="changeSubmit"
								onclick=" submit();"
								class="btn_confirm2"><span class="blind">확인</span></a>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}">
						<input type="hidden"  name="user_seq" value="${dto.user_seq }">
				</form>
				<!-- [D] 상세내용 펼쳤을때 클래스 open 추가 -->
				<div class="find_dsc">
					<p>
						비밀번호를 바꾸면, 모바일 기기(휴대전화/태블릿 PC)와 외부 메일 프로그램(POP3)에서 모두 로그아웃 됩니다.<br>새로운
						비밀번호로 다시 로그인해 주세요.<!--  <a
							href="http://mail.naver.com/notice/10000000000022016772"
							class="btn_detail" target="_blank">자세히 보기<em class="ico_arr2"></em></a> -->
					</p>
				</div>
				</p>
			</div>
		</div>
	</div>
	<hr>

</body>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

<script>

function submit() {
	$('form').submit();
}

var gnb_option = { 
		gnb_service : "nid", 
		gnb_template : "gnb_utf8", 
		gnb_logout : encodeURIComponent("https://nid.naver.com/user2/help/idInquiry"),
		gnb_brightness : 2, 
		gnb_item_hide_option : 0  
	}

	lcs_do();

	function gnbClose(){
	    $('#wrap').click(function(e){
	        if( !$('#gnb').has(e.target).length ){
	            gnbAllLayerClose();
	        }
	    });
	} 
	//120919 win8 이슈 대응 : capslock 자동설정해제
	document.msCapsLockWarningOff = true;
	function setContainerHeight(height) {}
	function showMenu(obj, obj2){
		document.getElementById(obj).className = "on";
		document.getElementById(obj2).className = "";
	}

	var menuList = "idinquiry pwinquiry ";
	function hideMenu(obj){
		var otherMenu = menuList.split(" ");
		for (var i = 0; i < otherMenu.length - 1; i++) {
			document.getElementById(otherMenu[i]).className = "";
		}
		
		document.getElementById(obj).className = "on";
	}
	function goPage(obj, obj2){
		var url = "https://nid.naver.com/user2/help/" + obj + "?menu=" + obj2;
		location.href = url;
	}

	function clearDocs(){}
	
	
	//<![CDATA[
	//nClicks 전역변수
	 var nsc = "my.pwinqury";
	 var ccsrv = "cc.naver.com";
	 //]]>

	function convertDiv(obj, obj2, stat){
		e1 = document.getElementById("div_" + obj);
		e2 = document.getElementById("div_" + obj2);
		if(stat == "none"){
			document.getElementById("lb_" + obj).style.display="none";
			if(document.getElementById(obj2).value == ""){
				document.getElementById("lb_" + obj2).style.display="";
			}

			if(e1.className.indexOf(" focus") == -1) {
				e1.className = e1.className + " focus";
				e2.className = e2.className.replace(" focus", "");
			}
		}else{
			e1.className = e1.className.replace(" focus", "");
			if(document.getElementById(obj).value == ""){
				document.getElementById("lb_" + obj).style.display="";
			}

			e2.className = e2.className.replace(" focus", "");
			if(document.getElementById(obj2).value == ""){
				document.getElementById("lb_" + obj2).style.display="";
			}
		}
	}

	function input_submit() {
	    if(document.fm.new_pw.value == '') {
	        alert("새 비밀번호를 입력하세요.");
			document.fm.new_pw.focus();
			return false;
	    } else if(!isValid_passwd(document.fm.new_pw.value) ) {
			document.fm.new_pw.value = '';
		    document.fm.new_conf_pw.value = '';
		    document.fm.new_pw.focus();
		    return false;
		} else if(!pwdstat) {
			alert("안전도가 너무 낮습니다. 다른 비밀번호를 입력해 주세요.");
			document.fm.new_pw.value = '';
		    document.fm.new_conf_pw.value = '';
		    document.fm.new_pw.focus();
			return false;
		} else if(document.fm.new_conf_pw.value == '') {
	        alert("새 비밀번호 확인을 입력해 주세요.");
			document.fm.new_conf_pw.focus();
			return false;
		} else if(document.fm.new_pw.value != document.fm.new_conf_pw.value) {
			alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");

			document.fm.new_conf_pw.value = '';
			document.fm.new_conf_pw.focus();
			return false;
		} 
			else if(document.fm.autoValue.value == "") {
				alert("자동입력방지문자를 정확하게 입력해 주세요.");
				document.fm.autoValue.value = '';
				document.fm.autoValue.focus();
				
				return false;		
			}

		createRsaKey();
		
		lua_do('PWInquiry_PC_N' , arguments.callee.name,INFO_CHECK_POINT_SUBMIT, document.fm.token_help.value, true,'');
		
		document.fm.action = "/user2/help/pwInquiry?m=actionInputPasswd";
		document.fm.submit();
	}


	function toggle(){
	}

	function clearDocs(){
	}


	function getLenChar(texts) {
		texts = texts + "";
		return String.fromCharCode(texts.length);
	}

	function createRsaKey() {
		var rsa = new RSAKey();
		var sessionKey = "fk6oaHU8fIgBTDG0";
		var keyName = "100019322";
		var eValue = "bb3a64ec145907eeb04ead5f13eb39a8a00ccf7178c7f5aad86591ea563a10158e1c031c78fddc671f7f6ad16bc416920261b4fd1563b1c2cda9c0e43a6d5c2e663d82ac0a690399eb04d5ff66cdb981149b507c282616ac6b7a75a80e722da9112f0cebfc6ac424deab2efa21ab05d9bb12a799b0451449889ba5b09f358aed";
		var nValue = "010001";
		var id = "qjtjzj2733";
		rsa.setPublic(eValue, nValue);
	    
		if (sessionKey == "" || eValue == "" || nValue == "" || keyName == "") {
			alert("일시적인 오류입니다. 처음부터 다시 진행해 주세요.");
			reset();
		} else {
			var form = document.getElementById("fm");
			var comVal = getLenChar(sessionKey) + sessionKey + getLenChar(id) + id;
			form.encNewPasswd.value = rsa.encrypt(comVal + getLenChar(form.new_pw.value) + form.new_pw.value);
			form.encNm.value = keyName;
			reset();
		}
	}

	function reset() {
		document.fm.new_pw.value = "";
		document.fm.new_conf_pw.value = "";
	}

	function reCaptcha() {
		reCaptchaCount++;
		lua_do('PWInquiry_PC_N' , arguments.callee.name,INFO_CHECK_POINT_RETRY, document.fm.token_help.value, true,'');
		document.getElementById("chptchaimg").src = document.getElementById("chptchaimg").src + "1";
	}

	function hiddenText(objName){
		document.getElementById(objName).className = "blind";
	}

	function showText(objName, compName){
		if(document.getElementById(compName).value == ""){
			document.getElementById(objName).className = "lbl_in";
		}
	}

	function changeFocus(objName, ckValue){
		if(ckValue == "on"){
			if(objName == "bdr_autoValue"){
				document.getElementById(objName).className = "input_box input_box_v2 focus";
			}else{
				document.getElementById(objName).className = "input_box focus";
			} 
		}else{
			if(objName == "bdr_autoValue"){
				document.getElementById(objName).className = "input_box input_box_v2";
			}else{
				document.getElementById(objName).className = "input_box";
			} 	
		}
	}

	function checkNumber(){
		var captcha = document.getElementById('captcha_image_legend').style.display;
		
		if(captcha == "none"){
			check_num('autoValue', '1');
		}
	}

	var pwStime = 0;
	var pwEtime = 0;
	var time = 0;
	var totalTime = 0;
	var normalCount = 0;
	var reCaptchaCount = 0;

	var cStime = 0;
	var cEtime = 0;
	var ctime = 0;

	var reCaptchaTime = 0;
	var rtime = 0;
	jquery_changePw = jQuery.noConflict(true);

	jquery_changePw(document).ready(function() {
		lua_do2('PWInquiry_PC', arguments.callee.name, "", document.fm.token_help.value, true,'');        
		
		if(totalTime == 0) {
			totalTime  = new Date();
		}

	    jquery_changePw("#new_pw").on( "keydown", function(event){
	        if(pwStime == 0) {
	            pwStime = new Date();
	        }
	    });

	    jquery_changePw("#new_conf_pw").on( "keydown", function(event){
	        if(pwStime == 0) {
	            pwStime = new Date();
	        }
	    });
	    
	    jquery_changePw("#aReCaptcha").on( "click", function(event){
	            reCaptchaTime = new Date();
	    });

	    jquery_changePw("#new_pw").on( "blur", function(event){
	        if(jquery_changePw("#new_pw").val().length >= 6 && jquery_changePw("#new_conf_pw").val().length >= 6) {
	            pwEtime = new Date();
	        }        
	    });

	    jquery_changePw("#new_conf_pw").on( "blur", function(event){
	        if(jquery_changePw("#new_pw").val().length >= 6 && jquery_changePw("#new_conf_pw").val().length >= 6) {
	            pwEtime = new Date();
	        }
	    });


		jquery_changePw("#autoValue").on( "keydown", function(event){
	        if(cStime == 0) {
	            cStime = new Date();
	        }
	        
	        if (event.keyCode == 13 && cEtime == 0) {
	       		cEtime = new Date();
	        }        
	    });
	    
		jquery_changePw("#autoValue").on( "focus", function(event){
	        $.ajax({
		        url:"/user2/lua?m=checkApi&token=" + document.fm.token_help.value,
		        success: function(data, dataType){
		        }
		    }); 
	    });    
	        
		jquery_changePw("#autoValue").on( "blur", function(event){
	        cEtime = new Date();
	    });         
	    
	    jquery_changePw("#changeSubmit").on( "click", function(event){
	    	if(jquery_changePw("#new_pw").val().length >= 6 || jquery_changePw("#encNewPasswd").val()){
	    		normalCount = normalCount + 1;
	    	}
	    	
	    	if(jquery_changePw("#new_conf_pw").val().length >= 6 || jquery_changePw("#encNewPasswd").val()){
	    		normalCount = normalCount + 1;
	    	}
	    	
	        time = pwEtime - pwStime;
	        if(reCaptchaTime == 0){
	        	reCaptchaTime = cStime;
	        }
	        ctime = cEtime - cStime;
	        rtime = cEtime - reCaptchaTime;

	        nowTime = new Date();
	        ttime = nowTime - totalTime;
	        
	        lua_do2('PWInquiry_PC', arguments.callee.name, normalCount + "^" + time + "^" + ctime + "^" + rtime  + "^" + ttime + "^" + reCaptchaCount, document.fm.token_help.value, true,'');        
	        
	    });
	});
	

	
	var ua = window.navigator.userAgent.toLowerCase();
	var result = (/android+\s+((\d)\.(\d))(?:\.(\d))?/igm).exec(ua);
	var uad = navigator.userAgentData;
	var isMobile = (uad && uad.mobile) || (ua.indexOf('Mobi') !== -1) || (/windows ce/.test( ua ) && /polar/.test( ua )) || ( /mozilla/.test( ua ) && /natebrowser/.test( ua ) ) || ( /opera/.test( ua ) && (/windows ce/.test( ua ) || /skt/.test( ua )) ) || ( /iphone/.test( ua ) || /ipod/.test( ua ) ) || ( /android/.test( ua ) && !( /.*shw-m180(s|k|l|w).*/.test( ua ) ) && !( result != null && result.length > 0 && result[1] >=3.0 ) ) || ( /dolfin/.test( ua )) || ( /windows ce/.test( ua ) && /iemobile/.test( ua ) ) || ( /mozilla/.test( ua ) &&  /(wv[0-9]+)/.test( ua ) && /lgtelecom/.test( ua ) ) || ( (/mozilla/.test( ua ) && /((010|011|016|017|018|019)\d{3,4}\d{4}$)/.test( ua )) ) || ( /windows phone os/.test( ua ) && /iemobile/.test( ua ) );
	if(isMobile){
		document.getElementById('divMobileYn').style.display = "block";
	}else{
		document.getElementById('divMobileYn').style.display = "none";
	}

	getGNB();
	document.getElementById('pwinquiry').className = "on";

	var cur_container_height = Number(document.getElementById("container").clientHeight); // container 높이
	var min_container_height = 647;
	var header_height = 86;
	var footer_height = isMobile ? 160: 30;

	window.onload   = changeContentSize; // Window 창 로드시
	window.onresize = changeContentSize; // Window 창 크기를 조정할때마다

	function changeContentSize() {
		var container_height = min_container_height;
		var window_height = Number(document.documentElement.clientHeight) - header_height - footer_height; // Window 창 높이
		if (window_height > cur_container_height) {
			if (window_height > min_container_height) {
				container_height = window_height;
			}
		} else {
			if (cur_container_height > min_container_height) {
				container_height = cur_container_height;
			}
		}
		
	    if (navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) { // ie11 추가
	        document.getElementById("container").style.height = container_height + "px";
	    }else if (window.navigator.userAgent.indexOf("MSIE") == -1 || (document.all && window.XMLHttpRequest)) { // ie6 제외
	        document.getElementById("container").style.height = container_height + "px";
	    } else {
	        document.getElementById("container").style.height ="100%";
	    }
	}

	function setContainerHeight(height) {
		if (height >= 0) {
			cur_container_height = height;
		} else {
			cur_container_height = Number(document.getElementById("container").clientHeight);
		}
		changeContentSize();
	}

</script>
</html>

