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
<!-- <script src="//code.jquery.com/jquery-1.11.1.min.js"></script> -->
<!-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"> 
<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${context}/resources/css/headerStyle.css" rel="stylesheet" type="text/css"> 
<!------ Include the above in your HEAD tag ---------->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" /> -->
<link href="../resources/css/store_main.css" rel="stylesheet" type="text/css">
<title>스토어</title>
</head>
<body>
<div class="container" style="margin-right: 420px;">
	<div class="productadd" id="productadd" style="display: none;">
		<input type="button" value="상품등록" id="addbtn" class="btn btn-outline-dark" style="margin-top: 50px;">
	</div>
</div>
   	<div class="container">
	    <h3 class="h3">팝콘</h3>
	    		 <div class="col-md-12 col-xs-12 col-sm-12" style="text-align: right;">
			   		<a href="do_retrieve_popcorn.do" class="btn_popcorn_product"><i class="fa fa-plus"></i> 더보기</a> <!-- style="margin-left: 1100px; margin-bottom: 50px;" -->
			    </div>
		    <div class="row">
			    <c:choose>
			    	<c:when test="${popcornlist.size()>0 }">
			    		<c:forEach var="vo" items="${popcornlist }">			    		
					        <div class="col-md-3 col-sm-6" >
					            <div class="product-grid">							       
					                <div class="product-image">					                    
					                    <img class="pic-1" style="height: 270px" width="250px" src='<c:out value="${vo.saveFileNm }"/>'>		
					                    <ul class="social">
					                        <li><a href="do_selectOne.do?productId=${vo.productId }" data-tip="Quick View"><i class="fa fa-search" style="margin-top: 10px;"></i></a></li>
					                        <li><a id="goCart" data-tip="Add to Cart"><i class="fa fa-shopping-cart" style="margin-top: 10px;"></i></a></li>
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
<hr>
	<div class="container">
	    <h3 class="h3">음료</h3>
		    <div class="row">
		    	<div class="col-md-12 col-xs-12 col-sm-12" style="text-align: right;">
		             <a href="do_retrieve_drink.do" class="btn_drink_product"><i class="fa fa-plus"></i> 더보기</a> 
		        </div>
			    <c:choose>
			    	<c:when test="${drinklist.size()>0 }">
			    		<c:forEach var="vo" items="${drinklist }">
					        <div class="col-md-3 col-sm-6">
					            <div class="product-grid"><%-- 
					            	<div  id="productId" class="productId"><c:out value="${vo.productId }"/></div> --%>
					                <div class="product-image">
					                    <img class="pic-1" src="<c:out value="${vo.saveFileNm }"/>">		
					                    <ul class="social">
					                        <li><a href="do_selectOne.do?productId=${vo.productId }" data-tip="Quick View"><i class="fa fa-search" style="margin-top: 10px;"></i></a></li>
					                        <li><a id="goCart" data-tip="Add to Cart"><i class="fa fa-shopping-cart" style="margin-top: 10px;"></i></a></li>
					                    </ul>
					                    <input type="hidden" id="count" name="count" value="1">
					                    <input type="hidden" id="userId" name="userId" value="${user.userId}">
					                    <input type="hidden"  id="productId" name="productId" value="${vo.productId}">
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
<hr>
<div class="container" style="margin-bottom: 30px;">
	   <h3 class="h3">영화관람권</h3>
		    <div class="row">
		    	<div class="col-md-12 col-xs-12 col-sm-12" style="text-align: right;">
		 		   <a href="do_retrieve_movieticket.do" class="btn_movieticket_product"><i class="fa fa-plus"></i> 더보기</a>
		 		</div>
			    <c:choose>
			    	<c:when test="${ticketlist.size()>0 }">
			    		<c:forEach var="vo" items="${ticketlist }">
					        <div class="col-md-3 col-sm-6">
					            <div class="product-grid">
					                <div class="product-image">
					                    <img class="pic-1" style="height: 270px" width="250px" src="<c:out value="${vo.saveFileNm }"/>">		
					                    <ul class="social">
					                        <li><a href="do_selectOne.do?productId=${vo.productId }" data-tip="Quick View"><i class="fa fa-search" style="margin-top: 10px;"></i></a></li>
					                        <li><a id="goCart" data-tip="Add to Cart"><i class="fa fa-shopping-cart" style="margin-top: 10px;"></i></a></li>
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
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${context}/resources/js/bootstrap.min.js"></script>   
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
 
	
	//등록
	$("#addbtn").on("click",function(){
		//alert("addbtn");
		if(false==confirm('새 상품을 등록 하시겠습니까?')) return;
		popup();
		
	});
	
	 function popup(){
         var url = "store_add.jsp";
         var name = "상품등록";
         var option = "width = 800, height = 520, top = 100, left = 400, location = no"
         window.open(url, name, option);
     }

	$(document).ready(function(){
		//admin일때만 작성가능
		if("${user.userId}"=="admin"){
	            $("#productadd").css("display", "inline ");//보이게하기
	         }
		
	});



</script>
</body>
</html>