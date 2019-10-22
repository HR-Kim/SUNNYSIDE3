<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="description" content=" Divisima | eCommerce Template">
	<meta name="keywords" content="divisima, eCommerce, creative, html">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Favicon -->
	<link href="../../sunnyside/resources/img/divisima/favicon.ico" rel="shortcut icon"/>
	
	<!-- Google Font -->
	<link href="https://fonts.googleapis.com/css?family=Josefin+Sans:300,300i,400,400i,700,700i" rel="stylesheet">
	
	
	<!-- Stylesheets -->
<!-- 	<link rel="stylesheet" href="../../sunnyside/resources/css/divisima/bootstrap.min.css"/> -->
	<link rel="stylesheet" href="../../sunnyside/resources/css/divisima/font-awesome.min.css"/>
	<link rel="stylesheet" href="../../sunnyside/resources/css/divisima/flaticon.css"/>
	<link rel="stylesheet" href="../../sunnyside/resources/css/divisima/slicknav.min.css"/>
	<link rel="stylesheet" href="../../sunnyside/resources/css/divisima/jquery-ui.min.css"/>
	<link rel="stylesheet" href="../../sunnyside/resources/css/divisima/owl.carousel.min.css"/>
	<link rel="stylesheet" href="../../sunnyside/resources/css/divisima/animate.css"/>
	<link rel="stylesheet" href="../../sunnyside/resources/css/divisima/style.css"/>
	<link rel="stylesheet" type="text/css" href="../../sunnyside/resources/css/login/main.css">
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
						<a href="/sunnyside/main/main.do" class="site-logo">
							<img src="../../sunnyside/resources/img/divisima/logo.png" alt="">
						</a>
					</div>
					<div class="col-xl-5 col-lg-4">
						
					</div>
					<div class="col-xl-4 col-lg-5">
						<div class="user-panel">
   							<!-- 로그인 하면 아이디 보임 -->
							<c:choose>
								<c:when test="${not empty user.userId}">
									<!-- 님 환영합니다. -->
									${user.userName} <spring:message code="message.header.welcome"/>
								</c:when>
								<c:otherwise>
									<div class="up-item">
<!-- 										<i class="flaticon-profile"></i> -->
<!-- 										<a href="../../sunnyside/login/login.jsp">Sign</a> In or <a href="#">Create Account</a> -->
									</div>
								</c:otherwise>
							</c:choose>
				
    						<!-- 로그인 했을 때만 장바구니 표시 -->
							<c:choose>
 								<c:when test="${not empty user.userId}">
									<div class="up-item" >
										<div class="shopping-card">
											<i class="flaticon-bag"></i>
										</div>
										<!-- 장바구니 -->
										<a href="/sunnyside/cart/do_retrieve.do?userId=<c:out value="${user.userId}"/>"><spring:message code="message.header.cart"/></a>
									</div>
								</c:when>
								<c:otherwise>
									<div></div>
								</c:otherwise>
							</c:choose>
							
						</div>
					</div>
					<div class="col-xl-1 col-lg-1">
						<select name="lang" id="lang">
					        <option value="ko" <c:if test="${lang == 'ko'}">selected</c:if> >한글</option>
							<option value="en" <c:if test="${lang == 'en'}">selected</c:if> >English</option>
					     </select>
					</div>
				</div>
			</div>
		</div>
		<nav class="main-navbar">
			<div class="container">
				<!-- menu -->
				<ul class="main-menu">
						<!-- 홈 -->
					<li><a href="/sunnyside/main/main.do"><spring:message code="message.header.home"/></a><li>
						<!-- 영화 -->
						<a href="/sunnyside/boxoffice/do_retrieve.do"><spring:message code="message.header.movie"/></a>
						<ul class="sub-menu">
							<!-- 박스오피스 -->
							<li><a href="/sunnyside/boxoffice/do_retrieve.do"><spring:message code="message.header.boxoffice"/></a></li>
							<!-- 상영중 -->
							<li><a href="/sunnyside/screening/do_retrieve.do"><spring:message code="message.header.screening"/></a></li>
							<!-- 개봉예정 -->
							<li><a href="/sunnyside/planed/do_retrieve.do"><spring:message code="message.header.planed"/></a></li>
						</ul>
					</li>					
					<li>
						<!-- 스토어 -->
						<a href="/sunnyside/store/do_main.do"><spring:message code="message.header.store"/></a>
						<ul class="sub-menu">
							<!-- 팝콘 -->
							<li><a href="/sunnyside/store/do_retrieve_popcorn.do"><spring:message code="message.header.popcon"/></a></li>
							<!-- 음료 -->
							<li><a href="/sunnyside/store/do_retrieve_drink.do"><spring:message code="message.header.drink"/></a></li>
							<!-- 영화예매권 -->
							<li><a href="/sunnyside/store/do_retrieve_movieticket.do"><spring:message code="message.header.ticket"/></a></li>
						</ul>
					</li>
					<li><a href="http://www.seechu.co.kr/index.asp">VOD</a></li>
					<!-- 고객센터 -->
					<li><a href="#"><spring:message code="message.header.customer"/></a></li>
					
  					<!-- 로그인한 상태면 로그아웃 출력 -->
					<c:choose>
  						<c:when test="${not empty user.userId}">
							<li><a href="/sunnyside/login/logout.do">로그아웃</a></li>
							<!-- (임시)회원정보수정 -->
							<li><a href="/sunnyside/usermypage/tmp_update_view.do"><spring:message code="message.header.userpage"/></a></li>
						</c:when>
						
						<c:otherwise>
							<!-- 로그인 -->
							<li><a href="/sunnyside/login/login_view.do"><spring:message code="message.header.login"/></a></li>
						</c:otherwise>
						
					</c:choose>
					
					<!-- 관리자로 로그인한 상태면 지점, 상영관 EDIT 출력 -->
					<c:choose>
  						<c:when test="${not empty user.userId && user.userId == 'admin'}">
  						<li>
  							<!-- 극장관리 -->
							<a href="#"><spring:message code="message.header.theatermanagement"/></a>
							<ul class="sub-menu">
								<!-- 극장편성 -->
								<li><a href="/sunnyside/branchInfo/do_retrieve.do"><spring:message code="message.header.theater"/></a></li>
								<!-- 영화편성 -->
								<li><a href="/sunnyside/screenInfo/do_goingPage.do"><spring:message code="message.header.moviemanagement"/></a></li>							</ul>
						</li>				
						</c:when>

						<c:otherwise>
						</c:otherwise>			
					</c:choose>
					
				</ul>
			</div>
		</nav>
	</header>
</body>
</html>