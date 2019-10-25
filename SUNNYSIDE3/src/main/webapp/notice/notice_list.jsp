<%@page import="kr.co.sunnyside.notice.service.KYMBranchVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.sunnyside.code.service.CodeVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@page import="kr.co.sunnyside.cmn.SearchVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<%
	/** 페이지 사이즈 */
	String pageSize   = "10"  ; 	
	/** 페이지 번호 */
	String pageNum    = "1"  ;	
	/** 검색조건 */
	String searchDiv  = "" ;
	/** 검색어 */
	String searchWord = "" ;
	
	//SearchVO vo = (SearchVO)request.getAttribute("vo");
	SearchVO searchVO = (request.getAttribute("vo")==null)?null:(SearchVO)request.getAttribute("vo");
	if(null !=searchVO){
		pageSize   = StringUtil.nvl(searchVO.getPageSize()+"","10");
		pageNum    = StringUtil.nvl(searchVO.getPageNum()+"","1");
		searchDiv  = StringUtil.nvl(searchVO.getSearchDiv(),"");
		searchWord = StringUtil.nvl(searchVO.getSearchWord(),"");		
	}else{
		pageSize   = "10";
		pageNum    = "1";
		searchDiv  = "";
		searchWord = "";
	}
	
	//게시판 검색 구분
	List<CodeVO> listNoticeSearch=(request.getAttribute("listNoticeSearch")==null)?
			(List<CodeVO>)new ArrayList<CodeVO>():(List<CodeVO>)(request.getAttribute("listNoticeSearch"));
			
	//게시판 검색 구분
	List<KYMBranchVO> Branchlist=(request.getAttribute("Branchlist")==null)?
			(List<KYMBranchVO>)new ArrayList<KYMBranchVO>():(List<KYMBranchVO>)(request.getAttribute("Branchlist"));
		
	//paging 
	//maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName	
	int maxNum      = 0;//totalCnt
	int bottomCount = 10;
	int currPageNo  = 1;//pageNum
	int rowPerPage  = 10;//pageSize
	
	String url      = request.getContextPath()+"/notice/do_retrieve.do";
	String scriptName ="search_page";
	
	//totalCnt
	String tmpTotalCnt=(request.getAttribute("totalCnt")==null)?"0":request.getAttribute("totalCnt").toString();
	
	maxNum = Integer.valueOf(tmpTotalCnt);
	currPageNo = Integer.valueOf(pageNum);
	rowPerPage = Integer.valueOf(pageSize);
	

%>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>고객센터</title>

<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<!-- div container -->
	<div class="container">
		<!-- div title -->
		<div class="page-header">
			<h1>고객센터</h1>
		</div>
		<!--// div title -->
		
		<!-- 검색영역 -->
		<div class="row">
			<div class="col-md-12 text-right">
				<form class="form-inline" name="noticeFrm" id="noticeFrm" method="GET" />
					<input type="hidden" name="pageNum" id="pageNum" value="${vo.pageNum }"/>
					<input type="hidden" name="noticeId" id="noticeId" />
					<div class="form-group">
						<!-- 검색구분 --> 
						<%=StringUtil.makeSelectBox(listNoticeSearch, "searchDiv", searchDiv, true) %>
						<input type="text" class="form-control input-sm" id="searchWord" name="searchWord" placeholder="검색어"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-default btn-sm" id="doRetrieve">조회</button>
						<button type="button" class="btn btn-default btn-sm" id="doSave">글쓰기</button>	
		
					</div>
				</form>
			</div>
		</div>
		<!--// 검색영역 -->

		<!-- Grid영역 -->
		<div class="table-responsive">
			<table class="table  table-striped table-bordered table-hover" id="listTable">
				<thead class="bg-primary">
				    <th class="text-center col-md-1 col-xs-1" style="display:none;">notice_ID</th>
					<th class="text-center col-md-1 col-xs-1">번호</th>
					<th class="text-center col-md-1 col-xs-1">지점</th>
					<th class="text-center col-md-9 col-xs-6 ">제목</th>
					<th class="text-center col-md-1 col-xs-2">등록일</th>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${list.size()>0 }">
							<c:forEach var="vo" items="${list}">
								<tr>
									<td class="text-center" style="display:none;"><c:out value="${vo.noticeId }"/></td>
									<td class="text-center"><c:out value="${vo.num }"/></td>
									<td class="text-center"><c:out value="${vo.branchSNm }"/></td>
									<td class="text-left"><c:out value="${vo.title }"/>
										&nbsp;<c:if test="${not empty vo.fileId and '0'!=vo.fileId}">
												<img alt="img" src="<c:url  value='/resources/img/phototicket/file.gif'/>">
										</c:if> 
									</td>
									<td class="text-center" style="font-size:0.9em;"><c:out value="${vo.regDt }"/></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td class="text-center" colspan="99">등록된 게시물이 없습니다.</td></tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<!--// Grid영역 -->

		<!-- pagenation -->
		<div class="text-center">
			<%=StringUtil.renderPaing(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
		</div>
		<!--// pagenation -->


	</div>
	<!--// div container -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
		$("#doSave").on("click",function(e){
			e.preventDefault();
			

			var frm = document.noticeFrm;
			frm.action = "${context}/notice/notice_mng.do";
			frm.submit();
		
		});
	
		//grid click
		$("#listTable>tbody").on("click","tr",function(){
			//alert("listTable");
			var trs = $(this);
			var td  = trs.children();
			if(null==td || td.length==1)return;
			//alert("td.length:"+td.length);
			
			
			var noticeId = td.eq(0).text();
			//console.log("noticeId:"+noticeId);
			var frm = document.noticeFrm;
			frm.noticeId.value=noticeId;
			frm.action = "${context}/notice/do_selectOne.do";
			frm.submit();
			
		});
		
		//paging
		function search_page(url, pageNum){
			var frm = document.noticeFrm;
	    	frm.pageNum.value = pageNum;
	    	frm.action = url;
	    	frm.submit();
		}
		
		//검색어 Enter
	    $("#searchWord").keydown(function(key){
	    	//alert(key.keyCode);
	    	if(key.keyCode == 13){
	    		doRetrieve()
	    	}
	    });
	
	    function doRetrieve(){
	    	var frm = document.noticeFrm;
	    	frm.pageNum.value = 1;
	    	frm.action = "${context}/notice/do_retrieve.do";
	    	frm.submit();
	    }
	
		$("#doRetrieve").on("click",function(){
			doRetrieve();
			
		});
	
		$(document).ready(function() {
			//alert("ready");
		});
	</script>
</body>
</html>