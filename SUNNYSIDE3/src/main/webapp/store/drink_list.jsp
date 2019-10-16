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
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link href="../resources/css/store_main.css" rel="stylesheet" type="text/css">
<title>음료</title>
</head>
<body>
	<div class="container">
	    <h4 class="h4">음료</h4>
		    <div class="row">
			    <c:choose>
			    	<c:when test="${list.size()>0 }">
			    		<c:forEach var="vo" items="${list }">
					        <div class="col-md-3 col-sm-6">
					            <div class="product-grid">
					            <div style="display: none;" id="productId" class="productId"><c:out value="${vo.productId }"/></div>
					                <div class="product-image">
					                    <img class="pic-1" src="<c:out value="${vo.saveFileNm }"/>">		
					                    <ul class="social">
					                        <li><a href="do_selectOne.do?productId=${vo.productId }" data-tip="Quick View"><i class="fa fa-search"></i></a></li>
					                        <li><a href="" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
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
	 </div>
<script type="text/javascript">

	//상품 단건조회
	$('.productId').click(function(event) {
		alert("productId");
	});
	
	$(document).ready(function(){
		//alert("ready");
	});
</script>
</body>
</html>