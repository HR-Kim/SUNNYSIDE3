<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--::header part start::-->
	<header class="main_menu home_menu">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-12">
					<nav class="navbar navbar-expand-lg navbar-light">					
						<!-- 로고 아이콘 -->
						<a class="navbar-brand" href="/sunnyside/main/main.do"> <img src="../../sunnyside/resources/image/layout/logo.png" alt="logo"> </a>
<!-- 						<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" -->
<!-- 								aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"> -->
<!-- 							<span class="menu_icon"><i class="fas fa-bars"></i></span> -->
<!-- 						</button> -->
						<!--// 로고 아이콘 -->
						
						<div class="collapse navbar-collapse main-menu-item" id="navbarSupportedContent">
							<ul class="navbar-nav">
								<li class="nav-item dropdown">
									<a class="nav-link dropdown-toggle" href="/sunnyside/boxoffice/do_retrieve.do">영화</a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdownpro">
										<a class="dropdown-item" href="/sunnyside/boxoffice/do_retrieve.do">박스오피스</a>
										<a class="dropdown-item" href="/sunnyside/screening/do_retrieve.do">상영중</a>
										<a class="dropdown-item" href="/sunnyside/planed/do_retrieve.do">개봉예정</a>
									</div>
								</li>
								<li class="nav-item dropdown">
									<a class="nav-link dropdown-toggle" href="/sunnyside/store/do_retrieve.do">스토어</a>
									<div class="dropdown-menu" aria-labelledby="navbarDropdownpro">
										<a class="dropdown-item" href="/sunnyside/store/do_retrieve_popcorn.do">팝콘</a>
										<a class="dropdown-item" href="/sunnyside/store/do_retrieve_drink.do">음료</a>
										<a class="dropdown-item" href="/sunnyside/store/do_retrieve_movieticket.do">영화예매권</a>
									</div>										
								</li>									
								<li class="nav-item">
									<a class="nav-link" href="http://www.seechu.co.kr/index.asp">VOD</a>
								</li>
								<li class="nav-item">
									<a class="nav-link" href="#">고객센터</a>
								</li>
								<li class="nav-item">
									<a class="nav-link" href="/sunnyside/login/login_view.do">로그인</a>
								</li>									
							</ul>
						</div>
						<div class="dropdown cart">
							<a class="dropdown-toggle" href="#" id="navbarDropdown3" role="button" data-toggle="dropdown"
							   aria-haspopup="true" aria-expanded="false">
								<i class="flaticon-bag"></i>
							</a>						
						</div>
					</nav>
				</div>
			</div>
		</div>
	</header>
	<!-- Header part end-->

</body>

</html>