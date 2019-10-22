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
	
	<div class="container">
		<form id="updateForm" name="updateForm" method="post">
			<div><input type="text" id="userId" name="userId" value="${user.userId}" ></div>
		</form>
		
		<button id="updateView" name="updateView" >회원정보수정</button>	
	</div>


	<!-- JavaScript -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
	
		$("#updateView").on("click",function(){
			var frm = document.updateForm;
			frm.userId.value=$("#userId").val();
			frm.action = "${context}/usermypage/do_selectOne.do"
			frm.submit();
		});
		
		
	</script>
	
	
</body>
</html>