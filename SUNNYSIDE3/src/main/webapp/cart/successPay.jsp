<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet"> 
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<!------ Include the above in your HEAD tag ---------->
<title>결제완료</title>
</head>
<hr>
<h2 style="margin-top: 100px; margin-bottom: 80px; font-weight: bold; text-align: center;">결제가 <h style="color: red;">완료</h>되었습니다.</h2>
<hr>
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-md-10 col-md-offset-1">
        <h4>결제 내역</h4>
            <table class="table table-bordered">
            	<c:choose>
	                 <c:when test="${list.size() >0 }">
		                 <c:otherwise>
		                  	<c:forEach items="${list }" var="vo">
					            <tr>
					            	<td>결제번호</td>
					           		<td><c:out value="${vo.payCode}"/></td>
					           	</tr>
					           	<tr>
					            	<td>상품명</td>
					           		<td><c:out value="${vo.productNm}"/></td>
					           	</tr>
					           	<tr>
					            	<td>결제금액</td>
					           		<td><c:out value="${vo.payCost}"/></td>
					           	</tr>
					           	<tr>
					            	<td>결제일</td>
					           		<td><c:out value="${vo.payDt}"/></td>
					           	</tr>
				       		</c:forEach>
				        </c:otherwise>
			       	</c:when>
	           	</c:choose>
           </table>
             <button type="button" class="btn btn-default" id="goHomeBtn" name="goHomeBtn" style="margin-left: 400px;">
				<span class="glyphicon glyphicon-home"></span> 메인으로 돌아가기
			 </button>
        </div>
    </div>
</div>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="${context}/resources/js/jquery-1.12.4.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${context}/resources/js/bootstrap.min.js"></script>   
<script type="text/javascript">

	// 메인 페이지로 이동
	$("#goHomeBtn").on("click",function(){
		alert("메인 페이지로 이동합니다.");
		location.href="${context}/store/do_main.do";
	});
	
$(document).ready(function(){	

});
</script>
</body>
</html>
