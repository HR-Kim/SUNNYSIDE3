<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta charset="UTF-8">
<title>NAVER LOGIN TEST</title>
</head>
<body>
	<h1>After Login Main</h1>
	<hr>
	<br>
	<center>
		<h2> 네이버 아이디 로그인 성공하셨습니다!! </h2>
		<h3>'${id}' 님 환영합니다! </h3>
		<h3><a href="logout.do">로그아웃</a></h3>
	</center>
	
	
	
	
	
	
	<!-- JavaScript -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
	
	
	</script>
	
</body>
</html>