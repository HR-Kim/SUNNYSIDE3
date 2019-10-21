<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@page import="kr.co.sunnyside.cmn.SearchVO"%>
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
<%-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
<!-- 부트스트랩 -->
<!------ Include the above in your HEAD tag ---------->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" /> -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${context}/resources/css/headerStyle.css" rel="stylesheet" type="text/css"> 
<link href="../resources/css/store_main.css" rel="stylesheet" type="text/css">
<title>팝콘</title>
</head>
<body>
	<div class="container">
	    <h4 class="h4">팝콘</h4>
		    <div class="row">
			    <c:choose>
			    	<c:when test="${list.size()>0 }">
			    		<c:forEach var="vo" items="${list }">			    		
					        <div class="col-md-3 col-sm-6" >
					            <div class="product-grid">							       
					                <div class="product-image">					                    
					                    <img class="pic-1" style="height: 270px" width="270px" src='<c:out value="${vo.saveFileNm }"/>'>		
					                    <ul class="social">
					                        <li><a href="do_selectOne.do?productId=${vo.productId }" data-tip="Quick View"><i class="fa fa-search"></i></a></li>
					                        <li><a id="goCart" data-tip="Add to Cart"><i class="fa fa-shopping-cart"></i></a></li>
					                    </ul>
					                    <input type="hidden" id="count" name="count" value="1">
					                    <input type="hidden" id="userId" name="userId" value="${user.userId}">
					                    <input type="hidden" id="productId" name="productId" value="${vo.productId}">
					                </div>			 
					                <div class="product-content">					                  
					                    <h3 class="title"><c:out value="${vo.productNm }"/></h3>
					                    <div class="price">
					                        <span><fmt:formatNumber value="${vo.productCost }" pattern="#,###,###"/>원</span>
					                    </div>				          
					                </div>
					            </div>
					        </div>
					    </c:forEach>
			        </c:when>
			    </c:choose>
			</div>
	 </div>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="${context}/resources/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	//장바구니에 담기
	$("[id^=goCart]").on("click",function(){
		//alert("goCart");
		
		//로그인 시 이동가능 
		if("${user.userId}"!= ""){ //로그인 되어있으면 
			if(false==confirm('상품을 담으시겠습니까?')) return;
			 var tr    = $(this).parent().parent().parent();
			 var tds = tr.children(); 
			 productId = tds.eq(4).val();
			 //alert( productId );
			 addToCart(); //카트에 더하기 함수 
			
	     }else{// 로그인이 안되어있으면
			alert("회원만 사용가능한 서비스입니다.\n로그인을 해주세요.");
	    	location.href ="${context}/login/login_view.do";
	     }		
	});
	
	 function addToCart(){	
	
		 console.log("productId="+productId);
		 console.log("userId="+$("#userId").val());
		 console.log("count="+$("#count").val());
		 
			//ajax
		     $.ajax({
		        type:"POST",
		        url:"${context}/cart/do_save.do",
		        dataType:"html",
		           data:{                               
		        		   "productId":productId, 
		        		   "userId":$("#userId").val(),       
		        		   "count":$("#count").val()                              
		       
		          },   
		      success: function(data){ 
				  if(null != data && data.msgId=="1"){
					  alert("추가되었습니다.");
					  var userId = $("#userId").val();
					  location.href="${context}/cart/do_retrieve.do?userId="+userId;
					
				  }else{
					alert("추가되었습니다.");	
					  var userId = $("#userId").val();
					  location.href="${context}/cart/do_retrieve.do?userId="+userId;
				  }
		     },
		     complete:function(data){
		  	   
		     },
		     error:function(xhr,status,error){
		         alert("error:"+error);
		     }
		    }); //--ajax  
		 }



	$(document).ready(function(){
		//alert("ready");
	});
	
</script>
</body>
</html>