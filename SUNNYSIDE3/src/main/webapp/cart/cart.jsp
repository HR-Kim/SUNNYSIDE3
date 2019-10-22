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
<title>장바구니</title>
</head>
<h2 style="margin-left: 450px;margin-top: 70px; margin-bottom: 40px; font-weight: bold">장바구니</h2>
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
	                             <td colspan="6">장바구니가 비었습니다.</td>
	                            </tr>
	                        </c:when>
	                        <c:otherwise>
	                     	   <c:forEach items="${list }" var="vo">	
				                    <tr>
				                        <td class="col-sm-8 col-md-5">
				                        <div class="media">
				                            <a class="thumbnail pull-left"> <img class="media-object" src="${vo.saveFileNm }" style="width: 100px; height: 100px;"> </a>
				                            <div class="media-body">
				                                <h4 class="media-heading"><a href="http://localhost:8080/sunnyside/store/do_selectOne.do?productId=${vo.productId }">${vo.productNm }</a></h4>                  
				                            </div>
				                        </div></td>
				                        <td class="col-sm-1 col-md-1" style="text-align: center" >				                     
				                       	   <input type="text" class="form-control" id="count" value="${vo.count }">
				                        </td>
				                        <td class="col-sm-1 col-md-1 text-center"><strong><fmt:formatNumber value="${vo.oriProductCost}" pattern="#,###,###"/>원</strong></td>
				                        <td style="display: none;" id="cartId"><strong>${vo.cartId }</strong></td>
				                        <td style="display: none;" id="productId"><strong>${vo.productId }</strong></td>
				                        <td style="display: none;" id="userId"><strong>${user.userId }</strong></td>
				                        <td class="col-sm-1 col-md-1 text-center"><strong><fmt:formatNumber value="${vo.productCost}" pattern="#,###,###"/>원</strong></td>
				                        <td class="col-sm-1 col-md-1" >
					                        <button type="button" class="btn btn-danger" id="deleteBtn"  style="margin-left: 5px; padding-bottom: 12px;">
					                            <span class="glyphicon glyphicon-remove"></span>
					                        </button>
					                         <button type="button" class="btn btn-success" id="updateBtn"  style="margin-left: 5px; padding-bottom: 12px;">
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
					                        <button type="button" class="btn btn-default" id="btnList" name="btnList">
					                            <span class="glyphicon glyphicon-shopping-cart"></span> 쇼핑계속하기
					                        </button></td>
					                        <td>
						                        <button type="button" class="btn btn-success" id="paybtn" name="paybtn">
						                           	 구매하기 <span class="glyphicon glyphicon-play"></span>
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

//리스트 삭제
$("[id^=deleteBtn]").on("click",function(){
	if(false==confirm('상품을 삭제하시겠습니까?')) return;
	var tr    = $(this).parent().parent();
	var tds   = tr.children();	
	console.log("tds="+tds);
	var cartId = tds.eq(3).text();
	var userId    = tds.eq(5).text();
	console.log("cartId="+cartId);
	console.log("userId="+userId);

		//ajax
	     $.ajax({
	        type:"POST",
	        url:"${context}/cart/do_delete.do",
	        dataType:"html",
	           data:{
	           "cartId":cartId
	          },   
	      success: function(data){ 
			  if(null != data && data.msgId=="1"){
				  alert("삭제되었습니다.");
				  var userId = $("#userId").text();
				  location.href="${context}/cart/do_retrieve.do?userId="+userId;
				
			  }else{
				alert("삭제되었습니다.");	
				  var userId = $("#userId").text();
				  location.href="${context}/cart/do_retrieve.do?userId="+userId;
			  }
	     },
	     complete:function(data){
	  	   
	     },
	     error:function(xhr,status,error){
	         alert("error:"+error);
	     }
	    }); //--ajax  
	
	 
});

//수량변경
$("[id^=updateBtn]").on("click",function(){
	if(false==confirm('상품 수량을 변경하시겠습니까?')) return;
	var tr    = $(this).parent().parent();
	var tds   = tr.children();	
	console.log("tds="+tds);
	var productId = tds.eq(4).text();
	var userId    = tds.eq(5).text();
	var count    = tds.eq(1).find("input").val();
	console.log("productId="+productId);
	console.log("userId="+userId);
	console.log("count="+count);

		//ajax
	     $.ajax({
	        type:"POST",
	        url:"${context}/cart/do_update.do",
	        dataType:"html",
	           data:{
	           "productId":productId,
	           "userId":userId,
	           "count":count
	          },   
	      success: function(data){ 
			  if(null != data && data.msgId=="1"){
				  alert("수량이 변경되었습니다.");
				  var userId = $("#userId").text();
				  location.href="${context}/cart/do_retrieve.do?userId="+userId;
				
			  }else{
				alert("수량이 변경되었습니다.");	
				  var userId = $("#userId").text();
				  location.href="${context}/cart/do_retrieve.do?userId="+userId;
			  }
	     },
	     complete:function(data){
	  	   
	     },
	     error:function(xhr,status,error){
	         alert("error:"+error);
	     }
	    }); //--ajax  
});

	// 리스트 페이지로 이동
	$("#btnList").on("click",function(){
		alert("메인 페이지로 이동합니다.");
		location.href="${context}/store/do_main.do";
	});

	
	// 구매하기 페이지로 이동
	$("#paybtn").on("click",function(){
		//alert("paybtn");
		if(false==confirm('주문하시겠습니까?')) return;
		var userId = $("#userId").text();
		location.href="${context}/cart/do_payRetrieve.do?userId="+userId;
	});
	
$(document).ready(function(){	

});
</script>
</body>
</html>
