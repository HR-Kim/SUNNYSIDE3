<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath }" />    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
<%-- <link href="${context}/resources/css/headerStyle.css" rel="stylesheet" type="text/css"> --%>
<link href="../resources/css/list.css" rel="stylesheet" type="text/css"> 
</head>
<body>
	<!-- Hero section -->
	<section class="hero-section" style="margin-bottom: 50px">
		<div class="hero-slider owl-carousel">
			<c:choose>
				<c:when test="${bannerList.size()>0}">
					<c:forEach var="vo" items="${bannerList}">
						<div class="hs-item set-bg" data-setbg="<c:out value='${vo.saveImgNm}'/>" style="text-align: right">
							<a href="#" class="site-btn sb-white" style="margin-top: 20px; margin-right: 30px;">Banner Edit</a>								
						</div>
					</c:forEach>        			
				</c:when>        	
				<c:otherwise>
					<div class="hs-item set-bg" data-setbg="http://www.dailypoem.kr/news/photo/201906/10311_10553_4134.jpg" style="text-align: right">
						<a href="#" class="site-btn sb-white" style="margin-top: 20px; margin-right: 30px;">Banner Edit</a>								
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="container">
			<div class="slide-num-holder" id="snh-1"></div>
		</div>
	</section>
	<!-- Hero section end -->
	
	<div class="container">
	<div class="container_inner default_template_holder clearfix page_container_inner"><div class="vc_row wpb_row section vc_row-fluid" style=" padding-top:0px; text-align:left;"><div class=" full_section_inner clearfix"><div class="vc_col-sm-12 wpb_column vc_column_container"><div class="wpb_wrapper"><div class="projects_holder_outer v3 portfolio_with_space portfolio_standard "><div class="projects_holder clearfix v3 standard">
		<div class="section-title" style="margin-bottom: 20px">
			<h2>BOXOFFICE</h2>
		</div>
		<c:choose>
			<c:when test="${boxofficeList.size()>0}">
				<c:forEach var="vo" items="${boxofficeList}">					
					<div class="col-lg-3">				
					<article class="mix mix_all" style="display: inline-block; opacity: 1; width:250px; height:500px;">
						<!-- 포스터 영역 -->
						<div class="image_holder" >		
							<!-- 이미지  -->			
							<span class="image">
								<img src="<c:out value='${vo.poster}'/>" alt="영화포스터" style="height: 350px; width:250px;" onerror="this.src='../resources/image/layout/noImage.png'">
							</span>
							<!--// 이미지  -->			
							<!-- 예매하기, 상세보기 버튼 -->
							<span class="text_holder"><span class="text_outer"><span class="text_inner" ><span class="feature_holder" ><span class="feature_holder_icons">
								<a class="lightbox qbutton small white" href="#">예매하기</a>
								<a class="lightbox qbutton small white" href="../movie/do_selectOne.do?movieId=<c:out value="${vo.movieId}"/>">상세보기</a>
								
							</span></span></span></span></span>			
							<!--// 예매하기, 상세보기 버튼 -->		
						</div>
						<!--// 포스터 영역 -->
						
						<!-- 영화정보 영역 -->		
						<div class="portfolio_description ">
							<!-- 영화제목 -->
							<h5 class="portfolio_title">
								<a href="../movie/do_selectOne.do?movieId=<c:out value="${vo.movieId}"/>"><c:out value="${vo.kortitle}"/></a>
							</h5>
							<!--// 영화제목 -->
							<!-- 영화정보 -->
							<span class="project_category">관람 평점 <c:out value="${vo.visitorRate}"/></span>
							<!--// 영화정보 -->
						</div>
						<!--// 영화정보 영역 -->								
					</article>	
					</div>
				</c:forEach>        			
			</c:when>        	
			<c:otherwise>
				<h1>데이터가 없습니다.</h1>
			</c:otherwise>
		</c:choose>		
	</div></div></div></div></div></div></div></div>
	
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${context}/resources/js/bootstrap.min.js"></script>   
</body>
</html>