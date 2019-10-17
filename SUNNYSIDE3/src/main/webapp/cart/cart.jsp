<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script> 
<!-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">  -->
<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet"> 
<meta charset="UTF-8">
<title>상품장바구니 목록</title>
</head>
<body>
	<h2>장바구니 확인</h2>
	${user.userId}
	<c:choose>
		<c:when test="${map.count == 0}">
			장바구니가 비어있습니다.
		</c:when>
		<c:otherwise>
		<form name="Cartform" id="Cartform" method="post" action="${context}/cart/do_update.do">
			<table border="1">
				<tr>
					<th>상품명</th>
					<th>단가</th>
					<th>수량</th>
					<th>금액</th>
					<th>취소</th>
				</tr>
				<c:forEach var="row" items="${map.list}" varStatus="i">
				<tr>
					<td>
						${row.productNm}
					</td>
					<td style="width: 80px" align="right">
						<fmt:formatNumber pattern="###,###,###" value="${row.oriProductCost}"/>
					</td>
					<td>
						<input type="number" style="width: 40px" name="amount" value="${row.count}" min="1">
						<input type="hidden" name="productId" value="${row.productId}">
					</td>
					<td style="width: 100px" align="right">
						<fmt:formatNumber pattern="###,###,###" value="${row.productCost}"/>
					</td>
					<td>
						<a href="${context}/cart/do_delete.do?cartId=${row.cartId}">삭제</a>
					</td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="5" align="right">
						장바구니 금액 합계 : <fmt:formatNumber pattern="###,###,###" value="${map.sumMoney}"/><br>
					</td>
				</tr>
			</table>
		</form>	
		</c:otherwise>
	</c:choose>
	<input type="hidden" name="count" value="${map.count}">
	<button type="submit" id="btnUpdate">수정</button>
	<button type="button" id="btnList">상품목록</button>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="${context}/resources/js/jquery-1.12.4.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${context}/resources/js/bootstrap.min.js"></script>   
<script type="text/javascript">
$(document).ready(function(){
	// 리스트 페이지로 이동
	$("#btnList").on("click",function(){
		alert("메인 페이지로 이동합니다.");
		location.href="${context}/store/store_main.do";
	});
});
</script>
</body>
</html>
