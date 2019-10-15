<%@page import="kr.co.sunnyside.review.service.LHJ_ReviewVO"%>
<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath }" />    
<%
	/**페이지 사이즈*/
	String pageSize="10"; 
	/**페이지 번호*/
	String pageNum= "1";
	/**검색조건*/
	String searchDiv="";
	/**검색어*/
	String searchWord="";
	
	LHJ_ReviewVO vo = (LHJ_ReviewVO)request.getAttribute("searchVO");
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
  						  
	//maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName			  
	//페이징 변수
	int maxNum = 0;//총 건수 totalCnt
	int bottomCount = 10;
	int currPageNo = 1; //현재페이지
	int rowPerPage = 10; //페이지당 보여줄 건수
	String url = request.getContextPath()+"/movie/do_selectOne.do";
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
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<link rel="stylesheet" href="${context}/resources/css/fontawesome-stars.css">

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
			<h1>영화 상세보기</h1>
		</div>
		<!--// div title -->

		<!-- detail form -->
		<form class="form-horizontal">
			<div class="container"><div class="card border-0"><div class="row">
				<aside class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></aside>
				<aside class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
					<article class="gallery-wrap"> 
						<div class="img-big-wrap">
							<div><img src="<c:out value='${vo.poster}'/>" alt="영화포스터" style="height: 350px; width:250px;" onerror="this.src='../resources/image/layout/noImage.png'"></div>
						</div>
					</article>
				</aside>
				<aside class="col-xs-7 col-sm-7 col-md-7 col-lg-7">
					<article class="card-body m-0 pt-0 pl-5">
						<h1 class="title text-uppercase"><c:out value='${vo.kortitle}'/></h1>				
						<h6 class="title text-uppercase"><c:out value='${vo.engtitle}'/></h6>
						<hr>									
						<p>
							<!-- 별점 -->
							<select class="avgRate" id="avgRate" name="avgRate">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>	
							<!--// 별점 -->
							<b>개봉일</b> | <c:out value='${vo.relDate}'/><br>
							<b>감독</b> | <c:out value='${vo.director}'/><br>
							<b>출연진</b> | <c:out value='${vo.cast}'/><br>
							<b>장르</b> | <c:out value='${vo.genre}'/><br>
							<b>러닝타임</b> | <c:out value='${vo.runningTime}'/><br>
							<b>관람연령</b> | <c:out value='${vo.limitage}'/><br>					
						</p>						
						<hr>				
						<dl class="item-property">
							<dt>줄거리</dt>
							<dd><p><c:out value='${vo.synopsis}'/></p></dd>
						</dl>
					</article>
				</aside>
				<aside class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></aside>
			</div></div></div>		
		</form>		
		<!-- detail form -->
		<hr>
		<!-- review input form -->
		<form class="form-horizontal">
			<aside class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></aside>
			<aside class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
				<div class="table-responsive" style="overflow-x: hidden">
					
					<p>한줄평</p>
					<input type="text" name="userId" id="userId" value="유저01gaz"/>
<!-- 					<input type="text" name="visitorRate" id="visitorRate" value="3"/> -->
					<table class="table  table-striped table-bordered table-hover" id="listTable">
					<tbody>					
					<tr style="width: 100%;">
						<td style="width: 15%; text-align: center;">
							<select class="visitorRate" id="visitorRate" name="visitorRate">
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select>	
						</td>
						<td style="width: 70%;">
							<textarea class="form-control" id="reviewContents" name="reviewContents" rows="3"></textarea>
						</td>
						<td style="width: 15%;">
							<div class="form-group">
								<label for="reviewContents" class="col-sm-2 control-label"></label>
								<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
									<button type="button" class="btn btn-default btn-sm" id="doSave">등록</button>
								</div>
							</div>
						</td>
					</tr>
					</tbody>
					</table>
				</div>
			</aside>
			<aside class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></aside>
		</form>
		<!--// review input form -->
	</div>
	<!--// div container -->
	<hr>	
	<!-- review list form -->
	<form class="form-horizontal" id="reviewFrm" name="reviewFrm">
		<input type="hidden" name="movieId" id="movieId" value="${vo.movieId}"/>
		<input type="hidden" name="pageNum" id="pageNum" value="${searchVO.pageNum}"/>
		<!-- Grid영역 -->
		<div class="table-responsive">
			<aside class="col-xs-3 col-sm-3 col-md-3 col-lg-3"></aside>
			<aside class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
			<table class="table  table-striped table-bordered table-hover" id="listTable">
				<thead class="bg-primary">
					<th class="text-center col-md-1 col-xs-1" style="display:none;">영화ID</th>
					<th class="text-center col-md-2 col-xs-2">작성자ID</th>
					<th class="text-center col-md-4 col-xs-4">내용</th>
					<th class="text-center col-md-1 col-xs-1">평점</th>
					<th class="text-center col-md-2 col-xs-2">작성일</th>
					<th class="text-center col-md-1 col-xs-1">수정</th>
					<th class="text-center col-md-1 col-xs-1">삭제</th>
				</thead>
				
				<tbody>
					<c:choose>
						<c:when test="${list.size()>0}">
							<c:forEach var="listVO" items="${list}">
								<tr>
									<td class="text-center" style="display:none;"><c:out value="${listVO.movieId}"/></td>
									<td class="text-center"><c:out value="${listVO.userId}"/></td>
									<td class="text-center"><c:out value="${listVO.contents}"/></td>
									<td class="text-center"><c:out value="${listVO.visitorRate}"/></td>									
									<td class="text-center"><c:out value="${listVO.regDt}"/></td>
									<!-- 수정/삭제 버튼 -->									
									<c:set var="name" value="유저01gaz" />
									<c:choose>
										<c:when test="${name == listVO.userId}">
											<td class="text-center"><input type="button" id="doUpdate" name="doUpdate" class="btn btn-default btn-sm doUpdate" value="수정" /></td>
											<td class="text-center"><input type="button" id="doDelete" name="doDelete" class="btn btn-default btn-sm doDelete" value="삭제" /></td>
										</c:when>
										<c:otherwise>
											<td class="text-center"></td>
											<td class="text-center"></td>
										</c:otherwise>
									</c:choose>			
									<!--// 수정/삭제 버튼 -->							
								</tr>
							</c:forEach>        			
						</c:when>        	
						<c:otherwise>
							<tr><td class="text-center" colspan="99">등록된 데이터가 없습니다.</td></tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			</aside>
			<aside class="col-xs-3 col-sm-3 col-md-3 col-lg-3"></aside>
		</div>
		<!--// Grid영역 -->
		<!-- pagenation -->
		<div class="form-inline text-center">
			
			<%=StringUtil.renderPaing(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
		</div>
		<!--// pagenation -->
	</form>
	<!--// review list form -->
	

	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>  	
	<!-- 별점 -->
	<script type="text/javascript" src="../resources/js/jquery.barrating.min.js"></script>
	<script type="text/javascript">	
		//삭제
		$("#doDelete").on("click", function(){
			var movieId = $("#movieId").val();
			//valication
			if(confirm("삭제 하시겠습니까?")==false)return;
			
			//ajax
			$.ajax({
				type:"POST",
				url:"${context}/review/do_delete.do",
				dataType:"html",
				data:{
					"movieId":movieId,
					"userId":$("#userId").val()					
				}, 
				success: function(data){
					var jData = JSON.parse(data);
					if(null != jData && jData.msgId=="1"){
						alert(jData.msgContents);
						location.href="${context}/movie/do_selectOne.do?movieId="+movieId;
					}else{
						alert(jData.msgId+"|"+jData.msgContents);
					}
	        	},
				complete:function(data){
	         
	       		},
				error:function(xhr,status,error){
					alert("error:"+error);
	        	}
			}); 
	       //--ajax
		});
		
		//등록
		$("#doSave").on("click", function(){
			var movieId = $("#movieId").val();
			if(confirm("등록 하시겠습니까?")==false)return;
			
			//ajax
			$.ajax({
				type:"POST",
				url:"${context}/review/do_save.do",
				dataType:"html",
				data:{
					"movieId":movieId,
					"userId":$("#userId").val(),
					"contents":$("#reviewContents").val(),
					"visitorRate":$("#visitorRate").val()
				}, 
				success: function(data){
					var jData = JSON.parse(data);
					if(null != jData && jData.msgId=="1"){
						alert(jData.msgContents);
						location.href="${context}/movie/do_selectOne.do?movieId="+movieId;
					}else{
						alert(jData.msgId+"|"+jData.msgContents);
					}
	        	},
				complete:function(data){
	         
	       		},
				error:function(xhr,status,error){
					alert("error:"+error);
	        	}
			}); 
	       //--ajax
		});
	
		//paging
		function search_page(url, pageNum) {
			var frm = document.reviewFrm;
			frm.pageNum.value = pageNum;
			frm.movieId.value = $("#movieId").val();
			frm.action = url;
			frm.submit();
		}
		
		$('.avgRate').barrating({ 			
			theme: 'fontawesome-stars', 
			initialRating: 3,
			readonly: true
		});
		
		$('.visitorRate').barrating({ 			
			theme: 'fontawesome-stars', 
			readonly: false
		});
		
		
		$(document).ready(function(){
		//alert("ready");
		});
	</script>  
</body>
</html>