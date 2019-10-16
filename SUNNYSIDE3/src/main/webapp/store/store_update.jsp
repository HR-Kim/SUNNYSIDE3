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
<%--     <link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet"> --%>
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
 <form id="boardEditFrm" name="boardEditFrm" method="post" enctype="multipart/form-data" action="do_update.do" style="margin-left: 40px">
 	<table border="1">
 	<tr style="display: none;">
 		<td><input id="productId" name="productId" value="<c:out value="${vo.productId }"/>"></td>
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
  	 	<input type="submit" value="수정" id="doUpdate">
  	 	<input type="button" value="취소" id="backBtn">
  	 </td>
  	</tr> 	
  </table> 
 </form>
 <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${context}/resources/js/jquery.validate.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<%--  <script src="${context}/resources/js/bootstrap.min.js"></script>    --%>
 <script type="text/javascript">
	//취소
	$("#backBtn").on("click",function(){
		//alert("backBtn");
		if(confirm("수정을 취소하시겠습니까?")==false) return;
		if(confirm("메인페이지로 이동합니다.")==false) return;
		location.href="${context}/store/store_main.do";	
	});

	//수정
		   $("#doUpdate").on("click",function(e){
			if(confirm("수정하시겠습니까?")==false) return;
			console.log("productNm="+$("#productNm").val());
			console.log("productCost="+$("#productCost").val());
			console.log("productInfo="+$("#productInfo").val());
			console.log("productId="+$("#productId").val());
			e.preventDefault();//현재 이벤트가 상위로 전파되지 않도록 중단한다
			//validation
	        if($("#boardEditFrm").valid()==false)return;
			
			doFileUpload();		
			});
 
	 function doFileUpload(){
		 var form = $('form')[0]; //저장된 파일은 위에서부터 1번, 2번 이므로 이렇게 꺼냄
 		 var formData = new FormData(form); //form 데이터
 		console.log("formData="+formData);
 		 
		//ajax
	     $.ajax({
	        type:"POST",
	        url:"${context}/store/do_update.do",		        
	        contentType:false,
	        async:false,
	        cache:false,
	        processData:false,
	        enctype:"multipart/form-data",
	        data:formData,  
	        success: function(data){ 
			  if(null != data && data.msgId=="1"){
				  alert("수정되었습니다.");		
				  location.href="${context}/store/store_main.do";
				
			  }else{
				alert(data.msgId+"|"+data.msgMsg);
				//location.href="${context}/store/store_main.do";
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
	//form validate
		$("#boardEditFrm").validate({

			rules: {
						productNm: {
								required: true,
								minlength: 2								
								},
						productCost: {
								required: true,
								minlength: 2
							},
						productInfo: {
								required: true,
								minlength: 2
							},
						productImage: {
								required: true,
								minlength: 2
							}
			},
			messages: {						
						productNm: {
								required: "상품명을 입력하세요",
								minlength: $.validator.format("{0}자 이상 입력하세요.") 									
						},
						productCost: {
							required: "상품금액을 입력하세요",
							minlength: $.validator.format("{0}자 이상 입력하세요.")
						},
						productInfo: {
							required: "상품정보를 입력하세요",
							minlength: $.validator.format("{0}자 이상 입력하세요.")
						},
						productImage: {
							required: "이미지를 추가해주세요"
						},
			},
			errorPlacement : function(error, element) {
							        //do nothing
							       },
							       invalidHandler : function(form, validator) {
							        var errors = validator.numberOfInvalids();
							       		 if (errors) {
							        				 alert(validator.errorList[0].message);
							      				     validator.errorList[0].element.focus();
										 }
									 }

		  })
});

</script>
</body>
</html>