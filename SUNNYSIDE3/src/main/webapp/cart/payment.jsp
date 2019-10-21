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
<title>주문하기</title>
</head>
<h2 style="margin-left: 450px;margin-top: 70px; margin-bottom: 40px; font-weight: bold">주문하기</h2>
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-10 col-md-offset-1">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>상품명</th>
                        <th>수량</th>
                        <th class="text-center">가격</th>
                        <th class="text-center">금액</th>
                        <th> </th>
                    </tr>
                </thead>
                <tbody>
	                <c:choose>
	                        <c:when test="${list.size() ==0 }">
	                            <tr>
	                             <td colspan="6">주문내역이 없습니다.</td>
	                            </tr>
	                        </c:when>
	                        <c:otherwise>
	                     	   <c:forEach items="${list }" var="vo">	
				                    <tr>
				                        <td class="col-sm-8 col-md-6">
				                        <div class="media">
				                            <a class="thumbnail pull-left"> <img class="media-object" src="${vo.saveFileNm }" style="width: 72px; height: 72px;"> </a>
				                            <div class="media-body">
				                                <h4 class="media-heading"><a href="http://localhost:8080/sunnyside/store/do_selectOne.do?productId=${vo.productId }">${vo.productNm }</a></h4>                  
				                            </div>
				                        </div></td>
				                        <td class="col-sm-1 col-md-1" style="text-align: center" >				                     
				                       	   <a class="form-control" id="count "><c:out value="${vo.count }"/></a>
				                        </td>
				                        <td class="col-sm-1 col-md-1 text-center"><strong><fmt:formatNumber value="${vo.oriProductCost}" pattern="#,###,###"/>원</strong></td>
				                        <td style="display: none;" id="cartId"><strong>${vo.cartId }</strong></td>
				                        <td style="display: none;" id="productId"><strong>${vo.productId }</strong></td>
				                        <td style="display: none;" id="userId"><strong>${user.userId }</strong></td>
				                        <td class="col-sm-1 col-md-1 text-center"><strong><fmt:formatNumber value="${vo.productCost}" pattern="#,###,###"/>원</strong></td>
				                        <td class="col-sm-1 col-md-1" >
					                        <button type="button" class="btn btn-danger" id="deleteBtn"  style="margin-left: 5px;">
					                            <span class="glyphicon glyphicon-remove"></span>
					                        </button>
					                         <button type="button" class="btn btn-success" id="updateBtn"  style="margin-left: 5px;">
					                            <span class="glyphicon glyphicon-ok"></span>
					                        </button>
				                        </td>				                        				                   
                          		</c:forEach>
			        		                       
					                    <tr>
					                        <td>   </td>
					                        <td>   </td>
					                        <td>   </td>
					                        <td><h3>총금액</h3></td>
					                        <td class="text-right"><h3><strong><fmt:formatNumber value="${totalCost}" pattern="#,###,###"/>원</strong></h3></td>
					                    </tr>                         
					                    <tr>
					                        <td>   </td>
					                        <td>   </td>
					                        <td>   </td>
					                        <td>
					                        <button type="button" class="btn btn-default" id="cancleBtn" name="cancleBtn">
					                            <span class="glyphicon"></span>취소
					                        </button></td>
					                        <td>
						                        <button type="button" class="btn btn-success" id="paybtn">
						                           	 결제 <span class="glyphicon glyphicon-play"></span>
						                        </button></td>
					                     </tr>
                  			  </c:otherwise>
                		</c:choose> 
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="${context}/resources/js/jquery-1.12.4.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${context}/resources/js/bootstrap.min.js"></script>   
<script type="text/javascript">


$(document).ready(function(){	
	// 리스트 페이지로 이동
	$("#cancleBtn").on("click",function(){
		if(false==confirm('결제를 취소하시겠습니까?')) return;
		alert("상품목록화면으로 이동합니다");
		location.href="${context}/store/do_main.do";
	});
});
</script>
</body>
</html>
