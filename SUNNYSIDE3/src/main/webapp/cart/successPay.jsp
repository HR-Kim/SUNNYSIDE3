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
<link href="${context}/resources/css/headerStyle.css" rel="stylesheet" type="text/css"> 
<!-- <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"> -->
<!------ Include the above in your HEAD tag ---------->
<!-- 결제완료 -->
<title><spring:message code="message.cart.finishpay"/></title>
</head>
<hr>
<!-- 결제가 완료 되었습니다. -->
<h2 style="margin-top: 100px; margin-bottom: 90px; font-weight: bold; text-align: center;"><spring:message code="message.cart.payis"/></h2>
<hr>
<div class="container">
    <div class="row"> 
        <div class="col-sm-8 col-md-6 col-md-offset-1" style="margin-left: 300px;">
        <!-- 결제 내역 -->
        <h4><spring:message code="message.cart.payList"/></h4>
            <table class="table table-bordered">
            	<c:choose>
				    <c:when test="${list.size() == 1 }">
				         <c:forEach items="${list }" var="vo">
				            <tr>
				            <!-- 결제번호 -->
				               <td align="center"><spring:message code="message.cart.payCode"/></td>
				               <td><c:out value="${vo.payCode}"/></td>
				            </tr>
				            <tr>
				            <!-- 상품이름 -->
				               <td align="center"><spring:message code="message.cart.payName"/></td>
				               <td><c:out value="${vo.productNm}"/></td>
				            </tr>
				            <tr>
				            <!-- 결제금액 -->
				               <td align="center"><spring:message code="message.cart.payCost"/></td>
				               <td><fmt:formatNumber value="${vo.totalCost}" pattern="#,###,###"/>원</td>
				            </tr>
				            <tr>
				            <!-- 결제일 -->
				               <td align="center"><spring:message code="message.cart.payDate"/></td>
				               <td><c:out value="${vo.payDt}"/></td>
				            </tr>
				         </c:forEach>
				   </c:when>
				   <c:when test="${list.size() >0 }">
				      <c:forEach items="${list }" var="vo" begin="0" end="0">
				         <tr>
				           <!-- 결제번호 -->
				            <td align="center"><spring:message code="message.cart.payCode"/></td>
				            <td><c:out value="${vo.payCode}"/></td>
				         </tr>
				         <tr>
				            <!-- 상품이름 -->
				            <td align="center"><spring:message code="message.cart.payName"/></td>
				            <td><c:out value="${vo.productNm}"/> 외 <c:out value="${list.size()-1}"/> 건</td>
				         </tr>
				         <tr>
				            <!-- 결제금액 -->
				            <td align="center"><spring:message code="message.cart.payCost"/></td>
				            <!-- 원 -->
				            <td><fmt:formatNumber value="${vo.totalCost}" pattern="#,###,###"/><spring:message code="message.cart.won"/></td>
				         </tr>
				         <tr>
				         	<!-- 결제일 -->
				            <td align="center"><spring:message code="message.cart.payDate"/></td>
				            <td><c:out value="${vo.payDt}"/></td>
				         </tr>
				      </c:forEach>
				   </c:when>
				   <c:otherwise></c:otherwise>                
				</c:choose>
           </table>
             <button type="button" class="btn btn-default" id="goHomeBtn" name="goHomeBtn" style="margin-left: 180px; margin-bottom: 50px; margin-top: 10px;">
             	<!-- 메인으로 돌아가기 -->
				<span class="glyphicon glyphicon-home"></span> <spring:message code="message.cart.backToMain"/>
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
		location.href="${context}/main/main.do";
	});
	
$(document).ready(function(){	

});
</script>
</body>
</html>
