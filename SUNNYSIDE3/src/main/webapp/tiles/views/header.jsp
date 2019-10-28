<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="description" content=" Divisima | eCommerce Template">
	<meta name="keywords" content="divisima, eCommerce, creative, html">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Favicon -->
	<link href="${context}/resources/img/divisima/favicon.ico" rel="shortcut icon"/>
	
	<!-- Google Font -->
	<link href="https://fonts.googleapis.com/css?family=Josefin+Sans:300,300i,400,400i,700,700i" rel="stylesheet">
	
	
	<!-- Stylesheets -->
<!-- 	<link rel="stylesheet" href="${context}/resources/css/divisima/bootstrap.min.css"/> -->
	<link rel="stylesheet" href="${context}/resources/css/divisima/font-awesome.min.css"/>
	<link rel="stylesheet" href="${context}/resources/css/divisima/flaticon.css"/>
	<link rel="stylesheet" href="${context}/resources/css/divisima/slicknav.min.css"/>
	<link rel="stylesheet" href="${context}/resources/css/divisima/jquery-ui.min.css"/>
	<link rel="stylesheet" href="${context}/resources/css/divisima/owl.carousel.min.css"/>
	<link rel="stylesheet" href="${context}/resources/css/divisima/animate.css"/>
	<link rel="stylesheet" href="${context}/resources/css/divisima/style.css"/>
	<link rel="stylesheet" type="text/css" href="${context}/resources/css/login/main.css">
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>
	<!-- Header section -->
	<header class="header-section">
		<div class="header-top">
			<div class="container">
				<div class="row">
					<div class="col-lg-2 text-center text-lg-left">
						<!-- logo -->
						<a href="${context}/main/main.do" class="site-logo">
							<img src="${context}/resources/img/divisima/logo.png" alt="">
						</a>
					</div>
					<div class="col-xl-8 col-lg-8 text-right">
						<div class="user-panel">
						
   							<!-- 로그인 시 헤더 모습 -->
							<c:choose>
								<c:when test="${not empty user.userId}">
									<!-- 님 환영합니다. -->
									<i class="flaticon-profile"></i>
									${user.userName} <spring:message code="message.header.welcome"/>
									(<a href="${context}/login/logout.do"><spring:message code="message.header.logout"/></a>)
									
									&nbsp &nbsp
									<a href="${context}/userpage/do_userpage.do"><spring:message code="message.usermypage.usermypage"/></a>
								</c:when>
								<c:otherwise> <!-- 로그아웃 시 헤더 모습 -->
									<i class="flaticon-profile"></i>
									<a href="${context}/login/login_view.do"><spring:message code="message.login.login"/></a> / <a href="${context}/login/join_view.do"><spring:message code="message.login.join"/></a>
								</c:otherwise>
							</c:choose>
				
    						<!-- 로그인 했을 때만 장바구니 표시 -->
							<c:choose>
 								<c:when test="${not empty user.userId}">
 									&nbsp &nbsp
 									<!-- 장바구니 -->
									<i class="flaticon-bag"></i>
									<a href="${context}/cart/do_retrieve.do?userId=<c:out value="${user.userId}"/>"><spring:message code="message.header.cart"/></a>
									&nbsp
								</c:when>
								<c:otherwise>
									<div></div>
								</c:otherwise>
							</c:choose>
							
						</div>
					</div>
					<div class="col-xl-1 col-lg-1">
						<div class="row">
						<form class="form-inline" name="langFrm" id="langFrm">
							<input type="hidden" id="lang" name="lang"/>
							<button type="button" id="koBtn" name="koBtn" class="btn btn-default btn-sm" style="padding: 0px;"><img class="btn-img" width="35px" src="${context}/resources/img/icon/kor.png"></button>
							<button type="button" id="enBtn" name="enBtn" class="btn btn-default btn-sm" style="padding: 0px;"><img class="btn-img" width="35px" src="${context}/resources/img/icon/usa.png"></button>
						</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<nav class="main-navbar">
			<div class="container">
				<!-- menu -->
				<ul class="main-menu">
						<!-- 홈 -->
					<li><a href="${context}/main/main.do"><spring:message code="message.header.home"/></a>
						<li>
							<!-- 영화 -->
							<a href="${context}/boxoffice/do_retrieve.do"><spring:message code="message.header.movie"/></a>
							<ul class="sub-menu">
								<!-- 박스오피스 -->
								<li><a href="${context}/boxoffice/do_retrieve.do"><spring:message code="message.header.boxoffice"/></a></li>
								<!-- 상영중 -->
								<li><a href="${context}/screening/do_retrieve.do"><spring:message code="message.header.screening"/></a></li>
								<!-- 개봉예정 -->
								<li><a href="${context}/planed/do_retrieve.do"><spring:message code="message.header.planed"/></a></li>
							</ul>
						</li>	
						<li>
							<!-- 영화관 -->
							<a href="${context}/theater/hongdae_info.do"><spring:message code="message.header.theaterbranch"/></a>
							<ul class="sub-menu">
								<!-- 홍대점 -->
								<li><a href="${context}/theater/hongdae_info.do"><spring:message code="message.header.theaterhongdae"/></a></li>
								<!-- 강남점 -->
								<li><a href="${context}/theater/kangnam_info.do"><spring:message code="message.header.theaterkangnam"/></a></li>
							</ul>
						</li>				
						<li>
							<!-- 스토어 -->
							<a href="${context}/store/do_main.do"><spring:message code="message.header.store"/></a>
							<ul class="sub-menu">
								<!-- 팝콘 -->
								<li><a href="${context}/store/do_retrieve_popcorn.do"><spring:message code="message.header.popcon"/></a></li>
								<!-- 음료 -->
								<li><a href="${context}/store/do_retrieve_drink.do"><spring:message code="message.header.drink"/></a></li>
								<!-- 영화예매권 -->
								<li><a href="${context}/store/do_retrieve_movieticket.do"><spring:message code="message.header.ticket"/></a></li>
							</ul>
						</li>
						<li><a href="http://www.seechu.co.kr/index.asp">VOD</a></li>
						<li>
							<!-- 고객센터 -->
							<a href="${context}/notice/do_retrieve.do"><spring:message code="message.header.customer"/></a>
							<ul class="sub-menu">
								<!-- 공지사항 -->
								<li><a href="${context}/notice/do_retrieve.do"><spring:message code="message.header.notice"/></a></li>
								<!-- 1:1문의 -->
								<li><a href="${context}/qna/do_retrieve.do"><spring:message code="message.header.questions"/></a></li>
								<!-- 자주하는 질문 -->								
								<li><a href="${context}/faq/do_retrieve.do"><spring:message code="message.header.faq"/></a></li>								
							</ul>
						</li>
					
						<!-- 관리자로 로그인한 상태면 지점, 상영관 EDIT 출력 -->
						<c:choose>
	  						<c:when test="${not empty user.userId && user.userId == 'admin'}">
	  						<li>
	  							<!-- 극장관리 -->
								<a href="#"><spring:message code="message.header.theatermanagement"/></a>
								<ul class="sub-menu">
									<!-- 극장편성 -->
									<li><a href="${context}/branchInfo/do_retrieve.do"><spring:message code="message.header.theater"/></a></li>
									<!-- 영화편성 -->
									<li><a href="${context}/screenInfo/do_goingPage.do"><spring:message code="message.header.moviemanagement"/></a></li>							</ul>
							</li>				
							</c:when>
							<c:otherwise>
							</c:otherwise>			
						</c:choose>
						
					
				</ul>
			</div>
		</nav>
	</header>
    <script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<script type="text/javascript">
		/** 다국어처리 */
		$("#koBtn").on("click",function(){
			var frm = document.langFrm;
			var lang = 'ko';
			
	    	frm.lang.value = lang;
	    	frm.action = "${context}/locale/change_locale.do";
	    	frm.submit();
	    });
		
		$("#enBtn").on("click",function(){
			var frm = document.langFrm;
			var lang = 'en';
			
	    	frm.lang.value = lang;
	    	frm.action = "${context}/locale/change_locale.do";
	    	frm.submit();
	    });
	</script>
</body>
</html>