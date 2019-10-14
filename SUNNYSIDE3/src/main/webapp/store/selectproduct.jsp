<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@page import="kr.co.sunnyside.cmn.SearchVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<%

	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link href="../resources/css/store_main.css" rel="stylesheet" type="text/css">
<title>상품상세</title>
</head>
<body>
<h3 class="h4"  style="margin-left: 70px; margin-top: 30px">상품 상세정보</h3>
<table style="margin-left: 70px;">
	<tr>
		<td style="display: none;" id="productId" class="productId">${vo.productId }</td>
		<td style="display: none;" id="category" class="category" >${vo.category }</td>
		<td>
			<img width="340" height="300" src="${vo.saveFileNm }">
		</td>
	<td>
		<table border="1" style="height: 300px; width: 400px;">
			<tr align="center">
				<td>상품명</td>
				<td>${vo.productNm }</td>
			</tr>
			<tr align="center">
				<td>가격</td>
				<td>${vo.productCost} 원</td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<form action="do_selectOne.do" method="get">
						수량: <select name="amount">
							<c:forEach begin="1" end="10" var="i">
								<option value="${i}"> ${i}</option>
							</c:forEach>
						</select> &nbsp; 개		
					</form>				
						<input style="margin-top: 30px; margin-right: 30px" id="goCart" name="goCart" type="submit" value="장바구니에 담기" >					
						<input id="pay" name="pay" type="button" value="바로 결제하기" >
				</td>
			</tr>			
		</table>
	</td>
	</tr>
</table>
<hr>
<table style="margin-left: 70px; margin-top: 20px">
	<tr>
		<td >상품소개</td><tr>
		<td>${vo.productInfo}</td>
	</tr>
</table>
<hr>
<div style="margin-left: 900px;">
	<input type="submit" value="수정" id="updatebtn" name="updatebtn">
	<input type="button" value="삭제" id="delbtn" name="delbtn">
</div>
		
<script type="text/javascript">
	//수정
	$("#updatebtn").on("click",function(){
		//alert("updatebtn");
		if(false==confirm('상품을 수정 하시겠습니까?')) return;
		var productId = $("#productId").text();
		//alert("productId=="+productId);
		location.href ="${context}/store/do_selectOneToUpdate.do?productId="+productId;
	
	});
	
	//삭제
	$("#delbtn").on("click",function(){
		//alert("delbtn");
		if(false==confirm('상품을 삭제 하시겠습니까?')) return;
		
		$.ajax({
	         type:"POST",
	         url:"${context}/store/do_delete.do",
	         dataType:"html",// JSON
	         data:{
	         	"productId":$("#productId").val()
	         },
	         success: function(data){//통신이 성공적으로 이루어 졌을때 받을 함수
	         	console.log(data); 
	         	//{"msgId":"1","msgMsg":"삭제되었습니다.","totalCnt":0,"num":0}
	         	var parseData= $.parseJSON(data);
	         	if(parseData.msgId =="1"){
	         		alert(parseData.msgMsg);
	         		location.href ="${context}/store/do_retrieve.do";
	         		
	         	}else{
	         		alert(parseData.msgMsg);
	         	}
	  
	         
	         },
	         complete: function(data){//무조건 수행
	        		location.href ="${context}/store/do_retrieve.do";
	         },
	         error: function(xhr,status,error){
	          
	         }
	    });
	});


	$(document).ready(function(){
		//alert("ready");
	});

</script>
</body>
</html>