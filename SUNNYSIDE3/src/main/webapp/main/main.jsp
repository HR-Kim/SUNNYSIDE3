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
<style>
.btn-sq-lg {
  width: 150px !important;
  height: 150px !important;
}

.btn-sq {
  width: 100px !important;
  height: 100px !important;
  font-size: 10px;
}

.btn-sq-sm {
  width: 50px !important;
  height: 50px !important;
  font-size: 10px;
}

.btn-sq-xs {
  width: 25px !important;
  height: 25px !important;
  padding:2px;
}

</style>
<body>
	<!-- Hero section -->
	<section class="hero-section" style="margin-bottom: 50px">
		<div class="hero-slider owl-carousel">
			<c:choose>
				<c:when test="${bannerList.size()>0}">
					<c:forEach var="vo" items="${bannerList}">
						<div class="hs-item set-bg" data-setbg="<c:out value='${vo.saveImgNm}'/>" style="text-align: right">
							<c:choose>
								<c:when test="${'admin' == user.userLevel}">
									<a href="#" onclick="popup('do_banner_retrieve.do','베너 이미지 수정',1200,800,100,400,'no');" class="site-btn sb-white" style="margin-top: 20px; margin-right: 30px;">Banner Edit</a>
								</c:when>
							</c:choose>								
						</div>
					</c:forEach>        			
				</c:when>        	
				<c:otherwise>
					<div class="hs-item set-bg" data-setbg="http://www.dailypoem.kr/news/photo/201906/10311_10553_4134.jpg" style="text-align: right">
						<a href="@" class="site-btn sb-white" style="margin-top: 20px; margin-right: 30px;">Banner Edit</a>								
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="container">
			<div class="slide-num-holder" id="snh-1"></div>
		</div>
	</section>
	<!-- Hero section end -->
	
	<div class="container" style="margin-bottom: 40px">
	<div class="container_inner default_template_holder clearfix page_container_inner"><div class="vc_row wpb_row section vc_row-fluid" style=" padding-top:0px; text-align:left;"><div class=" full_section_inner clearfix"><div class="vc_col-sm-12 wpb_column vc_column_container"><div class="wpb_wrapper"><div class="projects_holder_outer v3 portfolio_with_space portfolio_standard "><div class="projects_holder clearfix v3 standard">
		<div class="section-title" style="margin-bottom: 20px">
			<a href="/sunnyside/boxoffice/do_retrieve.do"><h2>박스오피스</h2></a>
		</div>
		<c:choose>
			<c:when test="${boxofficeList.size()>0}">
				<c:forEach var="vo" items="${boxofficeList}">					
					<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">				
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
	
	<div class="container" style="margin-bottom: 70px">
		<aside class="col-md-6">
			<div class="section-title" style="margin-bottom: 10px">
				<a href="#">
					<h3 style="font-size: 22px; letter-spacing: 1px;">공지사항</h3>
				</a>
			</div>
			<table class="table table-condensed">
				<thead style="border: none">
					<tr>
						<th class="text-center col-md-1 col-xs-1">분류</th>
						<th class="text-center col-md-3 col-xs-3">작성일</th>
						<th class="text-center col-md-8 col-xs-8">제목</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="text-center col-md-1 col-xs-1">전체</td>
						<td class="text-center col-md-3 col-xs-3">2019.10.22</td>
						<td class="text-left col-md-8 col-xs-8">영화관 공지사항</td>
					</tr>
					<tr>
						<td class="text-center col-md-1 col-xs-1">전체</td>
						<td class="text-center col-md-3 col-xs-3">2019.10.22</td>
						<td class="text-left col-md-8 col-xs-8">영화관 공지사항</td>
					</tr>
					<tr>
						<td class="text-center col-md-1 col-xs-1">전체</td>
						<td class="text-center col-md-3 col-xs-3">2019.10.22</td>
						<td class="text-left col-md-8 col-xs-8">영화관 공지사항</td>
					</tr>
					<tr>
						<td class="text-center col-md-1 col-xs-1">전체</td>
						<td class="text-center col-md-3 col-xs-3">2019.10.22</td>
						<td class="text-left col-md-8 col-xs-8">영화관 공지사항</td>
					</tr>
					<tr>
						<td class="text-center col-md-1 col-xs-1">전체</td>
						<td class="text-center col-md-3 col-xs-3">2019.10.22</td>
						<td class="text-left col-md-8 col-xs-8">영화관 공지사항</td>
					</tr>
				</tbody>
			</table>
		</aside>
		<aside class="col-md-6">
			<div class="section-title">
				<a href="#">
					<h3 style="font-size: 22px; letter-spacing: 1px;">고객센터</h3>
				</a>
				<hr>
			</div>
			<div class="container"><div class="row"><div class="col-md-6 col-lg-6"><div style="text-align: center">
				<p style="text-align: center">
				<a href="#" class="btn btn-sq-lg col-md-6 col-lg-6" style="background-color: #f6f6f6; margin-left: 90px;">
					<i class="fa fa-pencil-square-o fa-5x" style="margin-top: 20px; margin-left: 15px"></i><br/>1:1문의</a>
				<a href="#" class="btn btn-sq-lg col-md-6 col-lg-6" style="background-color: #f6f6f6; margin-left: 30px;">
					<i class="fa fa-question-circle-o fa-5x" style="margin-top: 20px;"></i><br/>자주하는 질문</a>
				</p>
			</div></div></div></div>
		</aside>
	</div>
	
	
    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${context}/resources/js/bootstrap.min.js"></script>   
    <script type="text/javascript">
		function popup(url,name,width,height,top,left,location){
	        var option = "width="+width+",height="+height+",top="+top+",left="+left+",resizeable=no";
	        
	        window.open(url, name, option);
	    }

		$(document).ready(function() {

        });
	</script> 
	</body>
</html>