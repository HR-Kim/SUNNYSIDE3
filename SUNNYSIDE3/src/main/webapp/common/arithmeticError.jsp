<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>arithmeticError</title>
</head>
<body>
	<div>
		<h3>요청과정에서 에러 발생</h3>
		<br/>
		<br/>
		<hr/>
		<!-- IllegalArgumentException -->
		에러타입:<%=exception.getClass().getName() %><br/>
		에러메시지:<%=exception.getMessage() %>
	</div>
</body>
</html>