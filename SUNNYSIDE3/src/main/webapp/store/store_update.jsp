<%@page import="kr.co.sunnyside.cmn.SearchVO"%>
<%@page import="kr.co.sunnyside.store.service.SEJ_StroreVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.sunnyside.code.service.CodeVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.sunnyside.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<%  
 	String category ="";

	SEJ_StroreVO vo = (SEJ_StroreVO)request.getAttribute("vo");
	if(null!=vo){
		category = StringUtil.nvl(vo.getCategory()+"","1");
	}else{
		category = Integer.toString(vo.getCategory());
	}
	//categoryList 삼항 연산자 
 	List<CodeVO> categoryList =(request.getAttribute("categoryList")==null)?(List<CodeVO>) new ArrayList<CodeVO>():(List<CodeVO>)request.getAttribute("categoryList");

%>
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
 <h2 style="margin-left: 40px;"> 상품정보 수정 </h2>
 <form id="storeForm" name="storeForm" method="post" enctype="multipart/form-data" action="do_update.do" style="margin-left: 40px">
 	<table border="1">
 	<tr style="display: none;">
 		<td><input id="productId" value="<c:out value=" ${vo.productId }"/>"></td>
 		<td class="category" id="category">${vo.category }</td>
 	</tr>
  	<tr>
  		<td>상품명</td>
  		<td><input type="text" name="productNm" id="productNm" value="<c:out value="${vo.productNm}"/>" /></td>
  	</tr>
  	<tr >
  		<td>분류</td>
  		<td>
  			<%=StringUtil.productMakeSelectBox(categoryList, "category", category, false) %>
  		</td>
  	</tr>
  	<tr>
  		<td>가격</td>
  		<td><input type="text" name="productCost" id="productCost" value="<c:out value="${vo.productCost}"/>"/>원</td>
  	</tr> 
  	<tr>
  		<td>상품설명</td>
  		<td><textarea rows="5" cols="60" name="productInfo" id="productInfo"><c:out value="${vo.productInfo}"/></textarea></td>
  	</tr>
  	<tr>
  		<td>상품이미지</td>
  		<td>
			<img width="340" height="300" src="${vo.saveFileNm }">
			<input type="file" name="productImage" id="productImage"/>
		</td>
  	</tr>
  	<tr>
  	 <td colspan="2" align="center">
  	 	<input type="submit" value="수정완료" id="doUpdate">
  	 </td>
  	</tr> 	
  </table> 
 </form>
 <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
 <script src="${context}/resources/js/bootstrap.min.js"></script>   
 <script type="text/javascript">


	//수정
		$("#doUpdate").on("click",function(){
			//validation
			console.log("productNm="+$("#productNm").val());
			console.log("productCost="+$("#productCost").val());
			console.log("productInfo="+$("#productInfo").val());
			console.log("productId="+$("#productId").val());
			
			if(confirm("수정하시겠습니까?")==false) return;
			
			//ajax
		       $.ajax({
		          type:"POST",
		          url:"${context}/store/do_update.do",		        
		          dataType:"html",
		       	  data:{
		       		"productId": $("#productId").val(),
		       		"productNm": $("#productNm").val(),
		       		"productCost": $("#productCost").val(),
		       		"productInfo": $("#productId").val()
		       			  
		       	  }, 
		       			       
		        success: function(data){ 
		        	 alert("수정되었습니다.");
		        	 location.href ="${context}/store/store_main.jsp";
		       },
		       complete:function(data){
		    	   //alert("수정되었습니다.");
		       },
		       error:function(xhr,status,error){
		           alert("error:"+error);
		       }
		      }); //--ajax  
			});
 
 
 
 $(document).ready(function(){
	 
  });

</script>
</body>
</html>