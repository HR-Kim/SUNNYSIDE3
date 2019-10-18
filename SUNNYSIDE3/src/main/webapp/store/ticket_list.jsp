<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@page import="kr.co.sunnyside.cmn.SearchVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" /> --%>
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${context}/resources/css/headerStyle.css" rel="stylesheet" type="text/css">
<link href="../resources/css/store_main.css" rel="stylesheet" type="text/css">
<title>영화관람권</title>
</head>
<body>
	<div class="container">
	   <h4 class="h4">영화관람권</h4>
	     <form name="ticketFrm" id="ticketFrm"  method="get">
		    <div class="row">
			    <c:choose>
			    	<c:when test="${list.size()>0 }">
			    		<c:forEach var="vo" items="${list }">
					        <div class="col-md-3 col-sm-6">
					            <div class="product-grid">
					                <div class="product-image">
					                    <img class="pic-1" style="height: 270px" width="270px" src="<c:out value="${vo.saveFileNm }"/>">		
					                    <ul class="social">
					                        <li><a href="do_selectOne.do?productId=${vo.productId }" data-tip="Quick View"><i class="fa fa-search"></i></a></li>
					                        <li><a id="goCart" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
					                    </ul>
					                </div>
					                <div class="product-content">
					                    <h3 class="title"><c:out value="${vo.productNm }"/></h3>
					                    <div class="price">
					                        <span><c:out value="${vo.productCost }"/>원</span>
					                    </div>
					                    <a class="add-to-cart" href="">+ Add To Cart</a>
					                </div>
					            </div>
					        </div>
					    </c:forEach>
			        </c:when>
			    </c:choose>
			</div>
		</form>
	 </div>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="${context}/resources/js/jquery-1.12.4.js"></script>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<!--     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="${context}/resources/js/bootstrap.min.js"></script>   
	<script type="text/javascript">

	
	
	//페이징
	function search_page(url,pageNum){
		var frm = document.ticketFrm;
		frm.pageNum.value = pageNum; //139 데이터 넘기기
		frm.action =url;
		frm.submit();
	}
	
	$(document).ready(function(){
		//alert("ready");
	});
</script>
</body>
</html>