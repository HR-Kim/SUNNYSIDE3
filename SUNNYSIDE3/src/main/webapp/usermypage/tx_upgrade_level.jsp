<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<button id="txUpLevel" name="txUpLevel" >자동등업</button>	
	
	
	
	
	<!-- JavaScript -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
	
		$("#txUpLevel").on("click",function(){
			//ajax
			$.ajax({
				   type:"POST",
				   url:"${context}/usermypage/tx_upgradeLevels.do",
				   dataType:"html",
				   data:{
				  }, 
				success: function(data){
				  alert("자동등업 완료");
				},
				complete:function(data){
				 
				},
				error:function(xhr,status,error){
					alert("error:"+error);
				}
			}); 
			//--ajax 
		});
		
		
		
	</script>
	
</body>
</html>