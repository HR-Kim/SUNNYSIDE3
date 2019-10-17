<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
</body>
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
					<div class="col-xl-6 col-lg-5">
						<form class="header-search-form">
							<input type="text" placeholder="Search on divisima ....">
							<button><i class="flaticon-search"></i></button>
						</form>
					</div>
					<div class="col-xl-4 col-lg-5">
						<div class="user-panel">
							<div class="up-item">
								<i class="flaticon-profile"></i>
								<a href="#">Sign</a> In or <a href="#">Create Account</a>
							</div>
							<div class="up-item">
								<div class="shopping-card">
									<i class="flaticon-bag"></i>
									<span>0</span>
								</div>
								<a href="#">Shopping Cart</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<nav class="main-navbar">
			<div class="container">
				<!-- menu -->
				<ul class="main-menu">
					<li><a href="/sunnyside/main/main.do">홈</a><li>
						<a href="/sunnyside/boxoffice/do_retrieve.do">영화</a>
						<ul class="sub-menu">
							<li><a href="/sunnyside/boxoffice/do_retrieve.do">박스오피스</a></li>
							<li><a href="/sunnyside/screening/do_retrieve.do">상영중</a></li>
							<li><a href="/sunnyside/planed/do_retrieve.do">개봉예정</a></li>
						</ul>
					</li>					
					<li>
						<a href="/sunnyside/store/do_retrieve.do">스토어</a>
						<ul class="sub-menu">
							<li><a href="/sunnyside/store/do_retrieve_popcorn.do">팝콘</a></li>
							<li><a href="/sunnyside/store/do_retrieve_drink.do">음료</a></li>
							<li><a href="/sunnyside/store/do_retrieve_movieticket.do">영화예매권</a></li>
						</ul>
					</li>
					<li><a href="http://www.seechu.co.kr/index.asp">VOD</a></li>
					<li><a href="#">고객센터</a></li>
					<li><a href="/sunnyside/login/login_view.do">로그인</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- Header section end -->
	
</html>