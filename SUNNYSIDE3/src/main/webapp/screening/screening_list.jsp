<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.sunnyside.movie.service.LHJ_MovieVO"%>
<%@page import="kr.co.sunnyside.cmn.SearchVO"%>
<%@page import="kr.co.sunnyside.code.service.CodeVO"%>
<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath }" />
<%
//**페이지 사이즈*/
	String pageSize="10"; 
	/**페이지 번호*/
	String pageNum= "1";
	/**검색조건*/
	String searchDiv="";
	/**검색어*/
	String searchWord="";
	
	SearchVO vo = (SearchVO)request.getAttribute("vo");
	if(null != vo){
		pageSize = StringUtil.nvl(vo.getPageSize()+"","10");
		pageNum = StringUtil.nvl(vo.getPageNum()+"","1");
		searchDiv = StringUtil.nvl(vo.getSearchDiv(),"");
		searchWord = StringUtil.nvl(vo.getSearchWord(),"");
	}else{
		pageSize = "10";
		pageNum = "1";
		searchDiv = "";
		searchWord = "";
	}
	
	
	//페이지 사이즈
	List<CodeVO> listPageSize = (request.getAttribute("listPageSize")==null)?
							  (List<CodeVO>) new ArrayList<CodeVO>():
							  (List<CodeVO>)(request.getAttribute("listPageSize"));

							  
	//maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName			  
	//페이징 변수
	int maxNum = 0;//총 건수 totalCnt
	int bottomCount = 10;
	int currPageNo = 1; //현재페이지
	int rowPerPage = 10; //페이지당 보여줄 건수
	String url = request.getContextPath()+"/screening/do_retrieve.do";
	String scriptName = "search_page";
	
	//totalCnt
	String tmpTotalCnt = (request.getAttribute("totalCnt")==null)?"0":request.getAttribute("totalCnt").toString();
	
	maxNum = Integer.valueOf(tmpTotalCnt);
	currPageNo = Integer.valueOf(pageNum);
	rowPerPage = Integer.valueOf(pageSize);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->

<!------ Include the above in your HEAD tag ---------->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/css/list.css" rel="stylesheet" type="text/css"> 

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>	
	<div class="container ">	
		<!-- div title --> 
		<div class="page-header row">
			<h1>상영중</h1>
			<c:choose>
				<c:when test="${'admin' == user.userLevel}">
					<button type="button" class="btn btn-default btn-sm" onclick="popup('do_screenUp_retrieve.do','개봉예정 리스트에 등록',1200,800,100,400,'no');">등록</button>
					<button type="button" class="btn btn-default btn-sm" onclick="popup('do_screenDown_retrieve.do','개봉예정 리스트에 등록',1200,800,100,400,'no');">삭제</button>
				</c:when>
			</c:choose>			
		</div>
		<!--// div title -->
		<!-- form -->
		<form class="form-inline" name="screening" id="screening" method="get">
			<input type="hidden" name="pageNum" id="pageNum" value="${vo.pageNum}"/>
			<div class="container_inner default_template_holder clearfix page_container_inner"><div class="vc_row wpb_row section vc_row-fluid" style=" padding-top:0px; text-align:left;"><div class=" full_section_inner clearfix"><div class="vc_col-sm-12 wpb_column vc_column_container"><div class="wpb_wrapper"><div class="projects_holder_outer v3 portfolio_with_space portfolio_standard "><div class="projects_holder clearfix v3 standard">
				<c:choose>
					<c:when test="${list.size()>0}">
						<c:forEach var="vo" items="${list}">					
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
										<a href="#"><c:out value="${vo.kortitle}"/></a>
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
			</div></div></div></div></div></div></div>
		</form>
		<!--// form -->		
	</div>
	<div class="container">	
		<!-- pagenation -->
		<div class="form-inline text-center">
				<%=StringUtil.renderPaing(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
		</div>
		<!--// pagenation -->
	</div>
	

	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>  	
	<script type="text/javascript">
		function popup(url,name,width,height,top,left,location){
	        var option = "width="+width+",height="+height+",top="+top+",left="+left+",location="+location;
	        window.open(url, name, option);
	    }
		//paging
		function search_page(url, pageNum) {
			var frm = document.screening;
			frm.pageNum.value = pageNum;
			frm.action = url;
			frm.submit();
		}
		
		$("#doUpdate").click(function(){
			
	    });
		  	
		$(document).ready(function() {

        });
	</script>  
</body>
</html>