<%@page import="kr.co.sunnyside.movie.service.LHJ_MovieVO"%>
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
<link href="${context}/resources/css/headerStyle.css" rel="stylesheet"> 
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
						<input type="text" id="starVisitorRate" name="starVisitorRate" value="${vo.visitorRate}"/>						
						<hr>								
						<p>
							<!-- 별점 -->
							<select class="avgRate" id="avgRate" name="avgRate">
								<option value="1" <c:if test="${vo.visitorRate < 1.5}"><c:out value='selected'/></c:if>>1</option>
								<option value="2" <c:if test="${vo.visitorRate >= 1.5 && vo.visitorRate < 2.5}"><c:out value='selected'/></c:if>>2</option>
								<option value="3" <c:if test="${vo.visitorRate >= 2.5 && vo.visitorRate < 3.5}"><c:out value='selected'/></c:if>>3</option>
								<option value="4" <c:if test="${vo.visitorRate >= 3.5 && vo.visitorRate < 4.5}"><c:out value='selected'/></c:if>>4</option>
								<option value="5" <c:if test="${vo.visitorRate >= 4.5 }"><c:out value='selected'/></c:if>>5</option>
								
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
		<form class="form-horizontal" id="reviewForm" name="reviewForm">
			<aside class="col-xs-1 col-sm-1 col-md-1 col-lg-1"></aside>
			<aside class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
				<div class="table-responsive" style="overflow-x: hidden">
					
					<p>한줄평</p>
					<input type="hidden" name="movieId" id="movieId" value="${vo.movieId}"/>
					<input type="text" name="userId" id="userId" value="${user.userId}"/>
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
									<button type="button" class="btn btn-default btn-sm doSave" id="doSave">등록</button>
									<button type="button" class="btn btn-default btn-sm doUpdate" id="doUpdate" style="display:none;">수정</button>
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
	<form class="form-horizontal" id="reviewListFrm" name="reviewListFrm">
		<input type="hidden" name="movieId" id="movieId" value="${vo.movieId}"/>
		<input type="hidden" name="pageNum" id="pageNum" value="${searchVO.pageNum}"/>
		<!-- Grid영역 -->
		<div class="table-responsive">
			<aside class="col-xs-3 col-sm-3 col-md-3 col-lg-3"></aside>
			<aside class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
			<c:choose>
				<c:when test="${list.size()>0}">
					<c:forEach var="listVO" items="${list}">
						<div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
							<div class="panel panel-default">
								<div class="panel-heading" style="height: 50px">
									<span class="list_movieId" style="display:none;"><c:out value="${listVO.movieId}"/></span>
									<strong class="list_userId"><c:out value="${listVO.userId}"/></strong>
									<span class="text-muted list_regDt"><c:out value="${listVO.regDt}"/></span>
									<span class="text-muted list_visitorRate">
<!-- 									<select class="visitorRate" id="visitorRate" name="visitorRate"> -->
<!-- 										<option value="1">1</option> -->
<!-- 										<option value="2">2</option> -->
<!-- 										<option value="3">3</option> -->
<!-- 										<option value="4">4</option> -->
<!-- 										<option value="5">5</option> -->
<!-- 									</select>	 -->
									
									
									<c:out value="${listVO.visitorRate}"/></span>
<!-- 									수정/삭제 버튼									 -->
									<c:set var="name" value="${user.userId}" />
									<c:choose>
										<c:when test="${name == listVO.userId}">
											<span class="text-muted"><input type="button" id="doUpdateSelect" name="doUpdateSelect" class="btn btn-default btn-sm doUpdateSelect" value="수정" /></span>
											<span class="text-muted"><input type="button" id="doDelete" name="doDelete" class="btn btn-default btn-sm doDelete" value="삭제" /></span>
										</c:when>
									</c:choose>
								</div>
								<div class="panel-body list_contents">
									<c:out value="${listVO.contents}"/>
								</div><!-- /panel-body -->
							</div><!-- /panel panel-default -->
						</div><!-- /col-sm-5 -->						
					</c:forEach>        			
					</c:when>        	
						<c:otherwise>
				
						</c:otherwise>
					</c:choose>
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
		//리뷰리스트의 수정 버튼 클릭시 리뷰 입력창에서 수정할 수 있도록 하는 기능
		$("#doUpdateSelect").on("click", function(){
			var doUpdateSelect = $(this);
			var div = doUpdateSelect.parent().parent();
			var list_movieId = div.children('.list_movieId').text(); //수정할 댓글의 영화ID
			var list_userId = div.children('.list_userId').text(); //수정할 댓글의 사용자ID
			var list_regDt = div.children('.list_regDt').text(); //수정할 댓글의 작성일
			var list_visitorRate = div.children('.list_visitorRate').text(); //수정할 댓글의 사용자평점
			var list_contents = div.parent().children('.list_contents').text().trim(); //수정할 댓글의 리뷰내용
						
			var reviewContents = document.getElementById('reviewContents'); //리뷰 작성란의 contents
			
			document.getElementById("doSave").style.display ='none';//저장버튼 숨김
			document.getElementById("doUpdate").style.display ='block';//수정버튼 보이게함
		
			console.log(list_movieId);
			console.log(list_userId);
			console.log(list_regDt);
			console.log(list_visitorRate);
			console.log(list_contents);
			
			
			//수정버튼 클릭시 리뷰 작성란의 contents에 수정할 리뷰의 내용이 전달된다.
			reviewContents.innerHTML = list_contents;
		});
	
		//등록
		$("#doUpdate").on("click", function(){
			var movieId = $("#movieId").val();
			if(confirm("수정 하시겠습니까?")==false)return;
			
			//ajax
			$.ajax({
				type:"POST",
				url:"${context}/review/do_update.do",
				dataType:"html",
				data:{
					"movieId":$("#movieId").val(),
					"userId":$("#userId").val(),
					"contents":$("#reviewContents").val(),
					"visitorRate":$("#visitorRate").val()
				}, 
				success: function(data){
					var jData = JSON.parse(data);
					if(null != jData && jData.msgId=="1"){
						alert(jData.msgMsg);
						location.href="${context}/movie/do_selectOne.do?movieId="+movieId
					}else{
						alert(jData.msgId+"|"+jData.msgMsg);
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
					"movieId":$("#movieId").val(),
					"userId":$("#userId").val(),
					"contents":$("#reviewContents").val(),
					"visitorRate":$("#visitorRate").val()
				}, 
				success: function(data){
					var jData = JSON.parse(data);
					if(null != jData && jData.msgId=="1"){
						alert(jData.msgMsg);
						location.href="${context}/movie/do_selectOne.do?movieId="+movieId
					}else{
						alert(jData.msgId+"|"+jData.msgMsg);
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
		
		//삭제
		$("#doDelete").on("click", function(){
			var movieId = $("#movieId").val();
			//valication
// 			if(confirm("삭제 하시겠습니까?")==false)return;
			
			//ajax
			$.ajax({
				type:"POST",
				url:"${context}/review/do_delete.do",
				dataType:"html",
				data:{
					"movieId":$("#movieId").val(),
					"userId":$("#userId").val()
				}, 
				success: function(data){
					var jData = JSON.parse(data);
					if(null != jData && jData.msgId=="1"){
						alert(jData.msgMsg);
						location.href="${context}/movie/do_selectOne.do?movieId="+movieId
					}else{
						alert(jData.msgId+"|"+jData.msgMsg);
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
			var frm = document.reviewListFrm;
			frm.pageNum.value = pageNum;
			frm.movieId.value = $("#movieId").val();
			frm.action = url;
			frm.submit();
		}
		
		$('.avgRate').barrating({ 		
// 			var rate = $("#starVisitorRate").val();
// 			console.log(rate);
			theme: 'fontawesome-stars',
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