<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.sunnyside.movie.service.LHJ_MovieVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<%
//페이지 사이즈
	List<LHJ_MovieVO> list = (request.getAttribute("list")==null)?
							  (List<LHJ_MovieVO>) new ArrayList<LHJ_MovieVO>():
							  (List<LHJ_MovieVO>)(request.getAttribute("list"));
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link href="../resources/css/store_main.css" rel="stylesheet" type="text/css">
<link href="../resources/css/movie_list.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>

<body>
	<div class="container "><div class="container_inner default_template_holder clearfix page_container_inner"><div class="vc_row wpb_row section vc_row-fluid" style=" padding-top:0px; text-align:left;"><div class=" full_section_inner clearfix"><div class="vc_col-sm-12 wpb_column vc_column_container"><div class="wpb_wrapper"><div class="projects_holder_outer v3 portfolio_with_space portfolio_standard "><div class="projects_holder clearfix v3 standard">
		<c:choose>
			<c:when test="${list.size()>0}">
				<c:forEach var="vo" items="${list}">					
					<article class="mix mix_all" style="display: inline-block; opacity: 1; ">
						<!-- 포스터 영역 -->
						<div class="image_holder">			
							<!-- 이미지  -->
							<span class="image">
								<img width="110" height="82" src="<c:out value='${vo.poster}'/>" alt="영화포스터">
							</span>
							<!--// 이미지  -->			
							<!-- 예매하기, 상세보기 버튼 -->
							<span class="text_holder"><span class="text_outer"><span class="text_inner"><span class="feature_holder"><span class="feature_holder_icons">
								<a class="lightbox qbutton small white" href="#">예매하기</a>
								<a class="lightbox qbutton small white" href="#">상세보기</a>
							</span></span></span></span></span>			
							<!--// 예매하기, 상세보기 버튼 -->			
						</div>
						<!--// 포스터 영역 -->
						
						<!-- 영화정보 영역 -->		
						<div class="portfolio_description ">
							<!-- 영화제목 -->
							<h5 class="portfolio_title">
								<a href="#"><c:out value="${vo.kortitle}"/></a>
							</h5>
							<!--// 영화제목 -->
							<!-- 영화정보 -->
							<span class="project_category">관람 평점 <c:out value="${vo.visitorRate}"/></span>
							<!--// 영화정보 -->
						</div>
						<!--// 영화정보 영역 -->
					</article>	
				</c:forEach>        			
			</c:when>        	
			<c:otherwise>
				<h1>데이터가 없습니다.</h1>
			</c:otherwise>
		</c:choose>
		
	</div></div></div></div></div></div></div></div>
</body>
</html>