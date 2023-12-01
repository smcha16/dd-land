<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- ======= Main ======= -->
	<main id="main" class="main">
	
		<section id="reveal">
	        <div class="reveal-container" data-aos="fade-in">
				<p><span class="typed" data-typed-items="안녕하세요 관리자님, 안녕하세요 김수민님, 환영합니다"></span></p>
	        </div>
    	</section>

    	<script>
        	const typed = document.querySelector('.typed')
        	
        	if (typed) {
            	let typed_strings = typed.getAttribute('data-typed-items')
            	typed_strings = typed_strings.split(',')
            	new Typed('.typed', {
	                strings: typed_strings,
	                loop: true,
	                typeSpeed: 100,
	                backSpeed: 50,
	                backDelay: 2000
				});
			}
    	</script>
    
		<!-- Code injected by live-server -->
		<script>
		   // <![CDATA[  <-- For SVG support
			if ('WebSocket' in window) {
				(function () {
					function refreshCSS() {
						var sheets = [].slice.call(document.getElementsByTagName("link"));
						var head = document.getElementsByTagName("head")[0];
						for (var i = 0; i < sheets.length; ++i) {
							var elem = sheets[i];
							var parent = elem.parentElement || head;
							parent.removeChild(elem);
							var rel = elem.rel;
							if (elem.href && typeof rel != "string" || rel.length == 0 || rel.toLowerCase() == "stylesheet") {
								var url = elem.href.replace(/(&|\?)_cacheOverride=\d+/, '');
								elem.href = url + (url.indexOf('?') >= 0 ? '&' : '?') + '_cacheOverride=' + (new Date().valueOf());
							}
							parent.appendChild(elem);
						}
					}
					var protocol = window.location.protocol === 'http:' ? 'ws://' : 'wss://';
					var address = protocol + window.location.host + window.location.pathname + '/ws';
					var socket = new WebSocket(address);
					socket.onmessage = function (msg) {
						if (msg.data == 'reload') window.location.reload();
						else if (msg.data == 'refreshcss') refreshCSS();
					};
					if (sessionStorage && !sessionStorage.getItem('IsThisFirstTime_Log_From_LiveServer')) {
						console.log('Live reload enabled.');
						sessionStorage.setItem('IsThisFirstTime_Log_From_LiveServer', true);
					}
				})();
			}
			else {
				console.error('Upgrade your browser. This Browser is NOT supported WebSocket for Live-Reloading.');
			}
		   // ]]>
		</script>
	
	</main>