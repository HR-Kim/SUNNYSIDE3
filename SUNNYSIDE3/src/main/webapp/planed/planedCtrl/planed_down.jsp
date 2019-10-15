<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.sunnyside.code.service.CodeVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@page import="kr.co.sunnyside.cmn.SearchVO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>        
<%--
  /**
  * @Class Name : list_template.jsp
  * @Description : bootstrap list
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2018.04.26            최초 생성
  *
  * author SIST 개발팀
  * since 2018.04.26
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>
<%
	/**페이지 사이즈*/
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
	
	List<CodeVO> listPageSize = (request.getAttribute("listPageSize")==null)?
							  (List<CodeVO>) new ArrayList<CodeVO>():
							  (List<CodeVO>)(request.getAttribute("listPageSize"));
	//게시판 검색 구분
	List<CodeVO> listBoardSearch = (request.getAttribute("listBoardSearch")==null)?
							  (List<CodeVO>) new ArrayList<CodeVO>():
							  (List<CodeVO>)(request.getAttribute("listBoardSearch"));						  
		
							  
	//maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName			  
	//페이징 변수
	int maxNum = 0;//총 건수 totalCnt
	int bottomCount = 10;
	int currPageNo = 1; //현재페이지
	int rowPerPage = 10; //페이지당 보여줄 건수
	String url = request.getContextPath()+"/planed/do_planedDown_retrieve.do";
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
	<title>개봉예정 리스트에서 제외</title>
	
	<!-- 부트스트랩 -->
	<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
	<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>

	<!-- div container -->
	<div class="container">
		<!-- div title --> 
		<div class="page-header">
			<h1>개봉예정 리스트에서 제외</h1>
		</div>
		<!--// div title -->
		<!-- 검색영역 -->
		<div class="row">
			<div class="col-md-12 text-right">
				<form class="form-inline" name="frm" id="frm" method="get">
					<input type="hidden" name="pageNum" id="pageNum" value="${vo.pageNum}"/>
					<input type="hidden" name="movieId" id="movieId"/>
					<div class="form-group">
						<!-- 페이지 사이즈 -->
						<%=StringUtil.makeSelectBox(listPageSize, "pageSize", pageSize, false) %>
						<!-- 검색구분 -->
						<%=StringUtil.makeSelectBox(listBoardSearch, "searchDiv", searchDiv, true) %>							
						<input type="text"  class="form-control input-sm" id="searchWord" name="searchWord" placeholder='검색' />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-default btn-sm" id="doRetrieve">조회</button> 
						<button type="button" class="btn btn-default btn-sm" id="doListRetrieve">목록</button><br><br>
					</div>
				</form>
			</div> 
		</div>
		<!--// 검색영역 -->  
		<!-- Grid영역 -->
		<div class="table-responsive">
			<table class="table  table-striped table-bordered table-hover" id="listTable">
				<thead class="bg-primary">
					<th class="text-center col-md-1 col-xs-1">영화ID</th>
					<th class="text-center col-md-4 col-xs-4">영화제목</th>
					<th class="text-center col-md-3 col-xs-3">감독</th>
					<th class="text-center col-md-3 col-xs-3">장르</th>
					<th class="text-center col-md-1 col-xs-1">관람연령</th>
					<th class="text-center col-md-1 col-xs-1">개봉일</th>
					<th class="text-center col-md-1 col-xs-1">제외</th>
				</thead>
				
				<tbody>
					<c:choose>
						<c:when test="${list.size()>0}">
							<c:forEach var="vo" items="${list}">
								<tr>
									<td class="text-center"><c:out value="${vo.movieId}"/></td>
									<td class="text-center"><c:out value="${StringUtil.col4Substring(vo.getKortitle())}"/></td>
									<td class="text-center"><c:out value="${StringUtil.col3Substring(vo.getDirector())}"/></td>
									<td class="text-center"><c:out value="${StringUtil.col3Substring(vo.getGenre())}"/></td>					
									<td class="text-center"><c:out value="${StringUtil.col1Substring(vo.getLimitage())}"/></td>
									<td class="text-center"><c:out value="${vo.relDate}"/></td>
									<td class="text-center"><input type="button" id="doDelete" name="doDelete" class="btn btn-default btn-sm doDelete" value="삭제" /></td>
								</tr>
							</c:forEach>        			
						</c:when>        	
						<c:otherwise>
							<tr><td class="text-center" colspan="99">등록된 데이터가 없습니다.</td></tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<!--// Grid영역 -->
      
		<!-- pagenation -->
		<div class="form-inline text-center">
			<%=StringUtil.renderPaing(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
		</div>
		<!--// pagenation -->
	</div>
   
   
	<!--// div container -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>    
	<script type="text/javascript">
		//paging
		function search_page(url, pageNum) {
			var frm = document.frm;
			frm.pageNum.value = pageNum;
			frm.action = url;
			frm.submit();
		}
		
		//개봉예정리스트로 돌아가기
		$("#doListRetrieve").on("click", function(){
			if(confirm("목록으로 이동 하시겠습니까?")==false)return;
			location.href="${context}/planed/do_retrieve.do"
		});
		
		function doRetrieve(){
			var frm = document.frm;
			frm.pageNum.value = 1;
			frm.submit();
		}
				
		//board/get_retrieve.do
		$("#doRetrieve").on("click",function(){
			//alert("doRetrieve");
			doRetrieve();
		});
		
		
		$('.doDelete').click(function(event) {
	 		var doDelete = $(this);
	 		var tr = doDelete.parent().parent();
	 		var td = tr.children();
	 		
	 		var movieId = td.eq(0).text();
	 		
	 		console.log("movieId:"+ movieId);	    
	 				
			var frm = document.frm;
			frm.movieId.value=movieId;

	 		//ajax
			$.ajax({
				type:"POST",
				url:"${context}/planed/do_update_planedDown.do",
				dataType:"html",
				data:{
					"movieId":$("#movieId").val()
				}, 
				success: function(data){
					var jData = JSON.parse(data);
					if(null != jData && jData.msgId=="1"){
// 						alert(jData.msgContents);
						location.href="${context}/planed/do_planedDown_retrieve.do";
					}else{
// 						alert(jData.msgId+"|"+jData.msgContents);
					}
            	},
				complete:function(data){
             
           		},
				error:function(xhr,status,error){
// 					alert("error:"+error);
            	}
			}); 
           //--ajax
		});
		
	
		
		function doSave(){
            var doSave = $(this);
            
            var tr = doSave.parent().parent();
            var td = tr.children();
            
            
            console.log("클릭한 Row의 모든 데이터 : "+tr.text());
            
            var no = td.eq(0).text();

		}
		  	
		$(document).ready(function(){
		//alert("ready");
		});
	</script>
</body>
</html>

















