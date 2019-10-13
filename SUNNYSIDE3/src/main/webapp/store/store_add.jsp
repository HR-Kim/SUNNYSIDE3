<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 부트스트랩 -->
    <link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>상품등록</title>

    <!-- 부트스트랩 -->
    <!--<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">  -->

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
 <h2 style="margin-left: 40px;"> 상품등록 </h2>
 <form id="storeForm" name="storeForm" method="post" enctype="multipart/form-data" action="do_save.do" style="margin-left: 40px">
 	<table border="1">
  	<tr>
  		<td>상품명</td>
  		<td><input type="text" name="productName" id="productName" /></td>
  	</tr>
  	<tr>
  		<td>분류</td>
  		<td>
	  		<select name="category" id="category">
	  			<option value="1">팝콘</option>
	  			<option value="2">음료</option>
	  			<option value="3">영화예매권</option>
	  		</select>
  		</td>
  	</tr>
  	<tr>
  		<td>가격</td>
  		<td><input type="text" name="productCost" id="productCost" />원</td>
  	</tr> 
  	<tr>
  		<td>상품설명</td>
  		<td><textarea rows="5" cols="60" name="productInfo" id="productInfo"></textarea></td>
  	</tr>
  	<tr>
  		<td>상품이미지</td>
  		<td><input type="file" name="productImage" id="productImage"  /></td>
  	</tr>
  	<tr>
  	 <td colspan="2" align="center">
  	 	<input type="submit" value="등록" id="addBtn">
  	 </td>
  	</tr> 	
  </table> 
 </form>
 <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
 <script src="${context}/resources/js/bootstrap.min.js"></script>   
 <script type="text/javascript">
 
	 /**등록*/ 
	 $("#addBtn").on("click",function(){
		  if(confirm("등록하시겠습니까?")==false) return;
		  
		//ajax
	       $.ajax({
	          type:"POST",
	          url:"${context}/store/do_save.do",
	          data:$("#storeForm").serialize(), 
	          dataType:"html",
	       
	        success: function(data){ 
	        	 alert("등록되었습니다.");
		    	 window.close();
	       },
	       complete:function(data){
	    	   alert("등록되었습니다.");
	    	   window.close();
	       },
	       error:function(xhr,status,error){
	          // alert("error:"+error);
	       }
	      }); //--ajax  
		});
	 
 
 $(document).ready(function(){
	  
  });

</script>
</body>
</html>