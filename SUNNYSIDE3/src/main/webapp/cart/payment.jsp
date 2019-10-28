<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
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
<!-- 주문/결제 -->
<title><spring:message code="message.cart.readyPay"/></title>
</head>
<!-- 주문/결제 -->
<h2 style="margin-left: 450px;margin-top: 70px; margin-bottom: 40px; font-weight: bold"><spring:message code="message.cart.readyPay"/> </h2>
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-10 col-md-offset-1">  
      
            <table class="table table-hover">
                <thead>
                    <tr> 
                    	<!-- 상품정보 -->
                        <th><spring:message code="message.cart.productInfo"/></th>
                        <!-- 수량 -->
                        <th><spring:message code="message.store.count"/></th>
                        <!-- 주문금액 -->
                        <th class="text-center"><spring:message code="message.cart.cartCost"/></th>
                    </tr>
                </thead>
                <tbody id="tbody">
	                <c:choose>
	                        <c:when test="${list.size() ==0 }">
	                            <tr>
	                            <!-- 주문내역이 없습니다. -->
	                             <td colspan="6"><spring:message code="message.cart.emptyorder"/></td>
	                            </tr>
	                        </c:when>
	                        <c:otherwise>
	                     	   <c:forEach items="${list }" var="vo">	
				                    <tr>
				                        <td class="col-sm-8 col-md-8">
					                        <div class="media">
					                            <a class="thumbnail pull-left"> <img class="media-object" src="${vo.saveFileNm }" style="width: 72px; height: 72px;"> </a>
					                            <div>
					                                <h4 style="font-size:15px; font-weight:bold; color: black;"><a href="http://localhost:8080/sunnyside/store/do_selectOne.do?productId=${vo.productId }">${vo.productNm }</a></h4>  					                                 
					                            </div>
					                        </div>
				                        </td>
				                        <!-- 개 -->
				                        <td class="col-sm-4 col-md-2" id="strCount" >${vo.count }<spring:message code="message.store.measure"/></td>
				                        <td style="display: none;" id="cartId"><strong>${vo.cartId }</strong></td>
				                        <td style="display: none;" id="userName"><strong>${user.userName }</strong></td>
				                        <td style="display: none;" id="productId"><strong>${vo.productId }</strong></td>
				                        <td style="display: none;" id="userId"><strong>${user.userId }</strong></td>
				                        <td style="display: none;" id="payCode"><strong>${vo.payCode }</strong></td>
				                        <!-- 원 -->
				                        <td class="col-sm-4 col-md-10 text-center"><strong><fmt:formatNumber value="${vo.productCost}" pattern="#,###,###"/><spring:message code="message.cart.won"/></strong></td>	
				                    </tr>			                      			                        				                   
                          		</c:forEach>			        		                       
					                    <tr>
					                        <td>   </td>
					                        <!-- 결제금액 -->
					                        <td  class=" text-right"><h3><spring:message code="message.cart.payCost"/></h3></td>
					                        <td class=" text-right"><h3><strong><input type="hidden" value="${totalCost}" id="tatalCost"><fmt:formatNumber value="${totalCost}" pattern="#,###,###"/><spring:message code="message.cart.won"/></strong></h3></td>
					                    </tr> 
					               </c:otherwise>
                		</c:choose> 
				</table>

			<table>                   
                    <tr>					                   	
                        <td>   </td>
                        <td> 
                        <button type="button" class="btn btn-default" id="cancleBtn" name="cancleBtn" style="margin-left: 380px; margin-top: 20px; margin-bottom: 40px;">
                           <!-- 주문취소 -->
                            <span class="glyphicon"></span><spring:message code="message.cart.cancleCart"/>
                        </button></td>
                        <td>
                        	<!-- 결제  -->
	                        <button type="button" class="btn btn-success" id="paybtn" style="margin-left: 20px; margin-top: 20px; margin-bottom: 40px;">
	                           	 <spring:message code="message.cart.readypay"/>
	                        </button>
	                   </td>
                     </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="${context}/resources/js/jquery-1.12.4.js"></script>
<!-- 결제를 위한 플러그인-->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${context}/resources/js/bootstrap.min.js"></script>   
<script type="text/javascript">
	//결제
	$("#paybtn").on("click",function(){
		userId = $("#userId").text();
		tatalCost = $("#tatalCost").val();
		userName =$("#userName").text();	
		payProduct();	 
		//goPayList() //api없이 결제할 경우 이걸 주석칠 것
	});
	
	function payProduct(){
		var IMP = window.IMP; // 생략가능
		IMP.init('imp74230965'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
		var msg;
		
		IMP.request_pay({
		    pg : 'kakaopay', // version 1.1.0부터 지원. 
		    pay_method : 'card',
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : 'SUNNYSIDE',
		    amount : tatalCost,
		    buyer_name : userName,
		}, function(rsp) {
		    if ( rsp.success ) {
		    	//[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
                jQuery.ajax({
                    url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        imp_uid : rsp.imp_uid
                        //기타 필요한 데이터가 있으면 추가 전달
                    }
                }).done(function(data) {
                    //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                    if ( everythings_fine ) {
                        msg = '결제가 완료되었습니다.';
                        msg += '\n고유ID : ' + rsp.imp_uid;
                        msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                        msg += '\결제 금액 : ' + rsp.paid_amount;
                        msg += '카드 승인번호 : ' + rsp.apply_num;
                        alert(msg);
                    } else {
                        //[3] 아직 제대로 결제가 되지 않았습니다.
                        //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                    }
				});
                //성공시 이동할 페이지
                goPayList();
                
			 } else {
		        msg = '결제에 실패하였습니다.  \n';
		        msg += '에러내용 : ' + rsp.error_msg;
		        //실패시 이동할 페이지
		        location.href="${context}/cart/do_retrieve.do?userId="+userId;
		        alert(msg);
		        alert("장바구니로 이동합니다.");
   			 }
			});
		}

	function goPayList(){
		 
	    var tbody = $('#tbody');
        var tr = tbody.children();
        var payCode = tr.children('#payCode').text();
        var productId = tr.children('#productId').text();
        var strCount = tr.children('#strCount').text();
        
        console.log("payCode="+payCode); 
        console.log("productId="+productId); 
        console.log("userId="+$("#userId").text()); 
        console.log("strCount="+strCount); 
        console.log("tatalCost="+$("#tatalCost").text()); 

      //ajax
	     $.ajax({
	        type:"POST",
	        url:"${context}/cart/do_payComplete.do",
	        dataType:"html",
	           data:{                               
	        		   "payCode":payCode, 
	        		   "productId":productId, 
	        		   "userId":$("#userId").text(),       
	        		   "strCount":strCount,
	        		   "tatalCost":$("#tatalCost").text()
	          },   
	      success: function(data){ 
			  if(null != data && data.msgId=="1"){
				  alert("결제가 완료되었습니다.");
				  var userId = $("#userId").text();
				  var payCode = $("#payCode").text();
				  location.href="${context}/cart/do_payCompleteList.do?userId="+userId+"&&payCode="+payCode;
				
			  }else{
				alert("결제가 완료되었습니다.");	
				  var userId = $("#userId").text();
				  var payCode = $("#payCode").text();
				  location.href="${context}/cart/do_payCompleteList.do?userId="+userId+"&&payCode="+payCode;
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
