<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.sunnyside.cmn.SearchVO"%>
<%@page import="kr.co.sunnyside.code.service.CodeVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/** 페이지 사이즈 */
	String pageSize   = "10"  ; 	
	/** 페이지 번호 */
	String pageNum    = "1"  ;	
	/** 검색조건 */
	String searchDiv  = "" ;
	/** 검색어 */
	String searchWord = "" ;
	/** 확장자 */
	String ext = "xls" ;	
	
	SearchVO vo = (SearchVO)request.getAttribute("vo");
	if(null !=vo){
		pageSize   = StringUtil.nvl(vo.getPageSize()+"","10");
		pageNum    = StringUtil.nvl(vo.getPageNum()+"","1");
		searchDiv  = StringUtil.nvl(vo.getSearchDiv(),"");
		searchWord = StringUtil.nvl(vo.getSearchWord(),"");		
	}else{
		pageSize   = "10";
		pageNum    = "1";
		searchDiv  = "";
		searchWord = "";
	}
	
	String extParam = (String)request.getAttribute("ext");
	if(extParam !=null) ext = extParam;
	List<CodeVO> tiketHistorySearch=(request.getAttribute("tiketHistorySearch")==null)?
			(List<CodeVO>)new ArrayList<CodeVO>():(List<CodeVO>)(request.getAttribute("tiketHistorySearch"));
			
	//paging 
	//maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName	
	int maxNum      = 0;//totalCnt
	int bottomCount = 10;
	int currPageNo  = 1;//pageNum
	int rowPerPage  = 10;//pageSize
	
	String url      = request.getContextPath()+"/userpage/do_tiketHistory.do";
	String scriptName ="search_page";
	
	//totalCnt
	String tmpTotalCnt=(request.getAttribute("totalCnt")==null)?"0"
			                                :request.getAttribute("totalCnt").toString();
	
	maxNum = Integer.valueOf(tmpTotalCnt);
	currPageNo = Integer.valueOf(pageNum);
	rowPerPage = Integer.valueOf(pageSize);
	

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<div class="table-responsive">	

	<form class="form-inline"  method="post" id="tiketHistoryList" >
		<table class="table table-striped table-bordered table-hover"  id="tiketHistoryTable" >
			<h3>예매/구매 내역</h3>
			<thead  class="bg-default" style="background-color: #333333; color: #f0f0f0">
					<tr>
				
						<th class="text-center col-md-5 col-xs-5">예매한 영화 이름</th>
						<th class="text-center col-md-2 col-xs-2">상영관 이름</th>
						<th class="text-center col-md-1 col-xs-1">예매일</th>
						<th class="text-center col-md-1 col-xs-1" style="display:none;">티켓코드</th>
						<th class="text-center col-md-3 col-xs-3">예매취소</th>
					</tr>
			</thead>
			<tbody>			
			
								
				
						
							
							
								
								
						
			</tbody>
			<!-- pagenation -->
		<div class="text-center">
			<%=StringUtil.renderPaing(maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName) %>
		</div>
		<!--// pagenation -->
		</table>
	</form>   
	</div>     



</body>


</html>